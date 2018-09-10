package com.example.asus.beasiswa;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.beasiswa.Helper.SQLiteHelper;

public class ApplyBeasiswa extends AppCompatActivity {

    TextView namaB, levelB, destiB;
    Button apli;
    SQLiteHelper sqLiteHelper;
    String userNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_beasiswa);

        namaB = (TextView) findViewById(R.id.textView2);
        levelB = (TextView) findViewById(R.id.textView5);
        destiB = (TextView) findViewById(R.id.textView3);
        apli = (Button) findViewById(R.id.buttonApply);

        sqLiteHelper = new SQLiteHelper(this);

        userNow = getIntent().getStringExtra("LoginUn");

        ModelBeasiswa m = getIntent().getParcelableExtra("beasiswaObj");
        namaB.setText(m.getNama());
        levelB.setText(m.getLevel());
        destiB.setText(m.getDestination());

        apli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftarin();
            }
        });
    }

    public void daftarin(){
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        db.execSQL("insert into applied(nama) values ('"+userNow+"')");
    }
}
