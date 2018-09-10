package com.example.asus.beasiswa;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asus.beasiswa.Helper.SQLiteHelper;

public class RegisteredUser extends AppCompatActivity {


    RecyclerView listStatus;
    SQLiteHelper sqLiteHelper;
    protected Cursor cursorUB,cursorUR;
    String [] Ubiasa = new String[99];
    String [] Uregistered = new String[99];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_user);

        listStatus = (RecyclerView) findViewById(R.id.listRegistered);
        sqLiteHelper = new SQLiteHelper(this);

        aksesData();

        listStatus.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        listStatus.setLayoutManager(manager);
    }

    public void aksesData(){
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        cursorUB = db.rawQuery("select * from user",null);
        cursorUR = db.rawQuery("select * from applied",null);

        cursorUR.moveToFirst();
        cursorUB.moveToFirst();

        for (int i=0; i<cursorUB.getCount(); i++){
            cursorUB.moveToPosition(i);
            Ubiasa[i] = cursorUB.getString(1).toString();
        }

        for (int i=0; i<cursorUR.getCount(); i++){
            cursorUR.moveToPosition(i);
            Uregistered [i] = cursorUR.getString(1).toString();
        }

        AdapterStatus adapter = new AdapterStatus(Ubiasa,Uregistered);
        listStatus.setAdapter(adapter);

    }
}
