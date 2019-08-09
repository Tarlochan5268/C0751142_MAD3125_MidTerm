package com.tarlochan.c0751142_mad3125_midterm.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "dbUser";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        String UserTable = "CREATE TABLE " + DBUser.TABLE_NAME
                + "(" + DBUser.USER_ID + " INT,"
                + DBUser.USER_EMAIL + " TEXT,"
                + DBUser.USER_PASSWORD + " TEXT)";

        sqLiteDatabase.execSQL(UserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE " + DBUser.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
