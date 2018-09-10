package com.example.asus.beasiswa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.beasiswa.Helper.SQLiteHelper;

public class RegistrationPage extends AppCompatActivity {

    EditText userN, pass, email;
    Button signUp;
    protected Cursor cursor;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        userN = (EditText) findViewById(R.id.editText3);
        pass = (EditText) findViewById(R.id.editText5);
        email = (EditText) findViewById(R.id.editText4);
        signUp = (Button) findViewById(R.id.button2);

        sqLiteHelper = new SQLiteHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanData();
            }
        });
    }

    public void simpanData(){
        if (userN.getText().toString().isEmpty() || pass.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Periksa kembali Tabel Isian", Toast.LENGTH_SHORT).show();
        }else {
            String nama = userN.getText().toString();
            String psw = pass.getText().toString();
            String mail = email.getText().toString();

            SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
            db.execSQL("insert into user(username,email,password) values ('"+nama+"','"+mail+"','"+psw+"')");

            startActivity(new Intent(getApplicationContext(),LoginPage.class));
            finish();

        }
    }
}
