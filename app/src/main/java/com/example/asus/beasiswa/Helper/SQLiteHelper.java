package com.example.asus.beasiswa.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "beasiswa";
    private static final int DATABASE_VERSION =1;

    public SQLiteHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlUser = "create table user(id integer primary key AUTOINCREMENT not null, username text not null," +
                "email text not null, password text not null);";

        sqLiteDatabase.execSQL(sqlUser);

        String sqlScholar = "create table scholar(id integer primary key, nama text not null," +
                "destination text not null, level text not null);";

        sqLiteDatabase.execSQL(sqlScholar);

        String sqlApplied = "create table applied(id integer primary key AUTOINCREMENT not null, nama text not null);";

        sqLiteDatabase.execSQL(sqlApplied);

        String inScholar = "insert into scholar(id,nama,destination,level) values(1,'LPDP','Italy','S1')," +
                "(2,'Erasmus','Prancis','S2'), (3,'Chievning','Inggris','S3'), (4,'Jepang Goverment','Jepang','S3');";

        sqLiteDatabase.execSQL(inScholar);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
