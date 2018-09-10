package com.example.asus.beasiswa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.beasiswa.Helper.SQLiteHelper;

public class LoginPage extends AppCompatActivity {

    EditText userName,pass;
    Button login;
    TextView regis;
    protected Cursor cursor;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userName = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button3);
        regis = (TextView) findViewById(R.id.regisBtn);

        sqLiteHelper = new SQLiteHelper(this);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegistrationPage.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekLogin();
            }
        });
    }

    public void cekLogin(){

        if (userName.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Login gagal Password atau Username Kosong",Toast.LENGTH_SHORT).show();
        }else {

            String uN = userName.getText().toString();
            String pS = pass.getText().toString();

            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
            cursor = db.rawQuery("select * from user",null);

            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++){
                cursor.moveToPosition(i);
                if (cursor.getString(1).toString().equals(uN) && cursor.getString(3).toString().equals(pS)){
                    Intent inet = new Intent(getApplicationContext(),MainActivity.class);
                    inet.putExtra("uLogin",cursor.getString(1).toString());
                    startActivity(inet);
                    break;
                }
            }
            cursor.close();

        }
    }
}
