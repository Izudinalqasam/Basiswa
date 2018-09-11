package com.example.asus.beasiswa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.asus.beasiswa.Helper.SQLiteHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerClick{

    RecyclerView listBeasiswa;
    Spinner level,destination;
    Button cari;
    String uLogin;
    AddapterBeasiswa adapter;
    protected Cursor cursor;
    SQLiteHelper sqLiteHelper;
    ArrayList<ModelBeasiswa> beasiswas = new ArrayList<>();
    ArrayList<ModelBeasiswa> hasilS = new ArrayList<>();
    boolean statusDetail =false;

    String [] levelText  = {"Level Pendidikan","S1","S2","S3"};
    String [] destiText = {"Destination","Indonesia","Jepang","Inggris","Prancis","Amerika","Arab","Italy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listBeasiswa = (RecyclerView) findViewById(R.id.listBeasiswa);
        level =(Spinner) findViewById(R.id.spinner);
        destination = (Spinner) findViewById(R.id.spinner3);
        cari = (Button) findViewById(R.id.button);

        uLogin = getIntent().getStringExtra("uLogin");

        sqLiteHelper = new SQLiteHelper(this);
        listBeasiswa.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listBeasiswa.setLayoutManager(layoutManager);

        ArrayAdapter<String> adLevel = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,levelText);
        ArrayAdapter<String> adDestination = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,destiText);

        level.setAdapter(adLevel);
        destination.setAdapter(adDestination);

        initialBeasiswa();

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cariBeasiswa();
            }
        });
    }

    public void cariBeasiswa(){
        SQLiteDatabase db =sqLiteHelper.getReadableDatabase();
        String denTo = destination.getItemAtPosition(destination.getSelectedItemPosition()).toString();
        String levelTo = level.getItemAtPosition(level.getSelectedItemPosition()).toString();

        cursor = db.rawQuery("select * from scholar",null);
        cursor.moveToFirst();

        for (int i=0; i <cursor.getCount(); i++){
            cursor.moveToPosition(i);
            if (cursor.getString(3).toString().equalsIgnoreCase(levelTo) && !denTo.equalsIgnoreCase("Destination")
                    && cursor.getString(2).toString().equalsIgnoreCase(denTo) && !levelTo.equalsIgnoreCase("Level Pendidikan")){
                ModelBeasiswa m = new ModelBeasiswa();
                m.setNama(cursor.getString(1).toString());
                m.setDestination(cursor.getString(2).toString());
                m.setLevel(cursor.getString(3).toString());

                hasilS.add(m);
            }
        }
        adapter.swap(hasilS);
        statusDetail = true;

    }

    public void initialBeasiswa(){
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from scholar",null);
        cursor.moveToFirst();

        for (int i=0; i<cursor.getCount(); i++){
            ModelBeasiswa b = new ModelBeasiswa();
            cursor.moveToPosition(i);
            b.setNama(cursor.getString(1).toString());
            b.setDestination(cursor.getString(2).toString());
            b.setLevel(cursor.getString(3).toLowerCase());

            beasiswas.add(b);
        }
        Log.d("scholar",beasiswas.size()+"");
        adapter = new AddapterBeasiswa(beasiswas,MainActivity.this);
        listBeasiswa.setAdapter(adapter);
        statusDetail = false;
        cursor.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.pageR :
                startActivity(new Intent(getApplicationContext(),RegisteredUser.class));
                return true;
            case R.id.singOut :
                startActivity(new Intent(getApplicationContext(),LoginPage.class));
                finish();
                return true;
             default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCLick(View view, int position) {
        if (statusDetail){
            Intent intent = new Intent(getApplicationContext(),ApplyBeasiswa.class);
            intent.putExtra("beasiswaObj",hasilS.get(position));
            intent.putExtra("LoginUn",uLogin);
            startActivity(intent);
        }else {
            Intent intent = new Intent(getApplicationContext(),ApplyBeasiswa.class);
            intent.putExtra("beasiswaObj",beasiswas.get(position));
            intent.putExtra("LoginUn",uLogin);
            startActivity(intent);
        }
    }
}
