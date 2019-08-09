package com.tarlochan.c0751142_mad3125_midterm;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBUser
{
    public static final String TABLE_NAME = "tblUser";
    public static final String USER_ID = "uid";
    public static final String USER_EMAIL = "uemail";
    public static final String USER_PASSWORD = "upass";
    private DBHelper dbHelper;
    public DBUser(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void insert(User user)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(USER_ID, user.getId());
        cv.put(USER_EMAIL, user.getEmail());
        cv.put(USER_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null,cv);

        db.close();

    }

    public void update(User user)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(USER_ID, user.getId());
        cv.put(USER_EMAIL, user.getEmail());
        cv.put(USER_PASSWORD, user.getPassword());

        db.update(TABLE_NAME,cv,
                USER_ID + "=?",
                new String[]
                        {
                                String.valueOf(user.getId())
                        });

        db.close();

    }

    public void updateByEmail(User user)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(USER_ID, user.getId());
        cv.put(USER_EMAIL, user.getEmail());
        cv.put(USER_PASSWORD, user.getPassword());

        db.update(TABLE_NAME,cv,
                USER_EMAIL + "=?",
                new String[]
                        {
                                user.getEmail()
                        });

        db.close();

    }

    public void deleteByID(int userID)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, USER_ID + "=?",
                new String[]
                        {
                                String.valueOf(USER_ID)
                        });
        db.close();
    }

    public void deleteByEmail(String studentEmail)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, USER_EMAIL + "=?",
                new String[]
                        {
                                studentEmail
                        });
        db.close();
    }

    public ArrayList<User> getAllUsers()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Cursor mCursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<User> mUsers = new ArrayList<>();
        if(mCursor != null)
        {
            if(mCursor.getCount() != 0)
            {
                mCursor.moveToFirst();
                while (!mCursor.isAfterLast())
                {
                    User mUser = new User();
                    mUser.setId(Integer.parseInt(mCursor.getString(0)));
                    mUser.setEmail(mCursor.getString(1));
                    mUser.setPassword(mCursor.getString(2));

                    mUsers.add(mUser);

                    mCursor.moveToNext();
                }
            }
        }

        db.close();
        return mUsers;
    }

    public ArrayList<User> getUserByEmail(String userEmail)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Cursor mCursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        Cursor mCursor = db.query(TABLE_NAME, null, USER_EMAIL + "=?", new String[]{String.valueOf(userEmail)}, null, null, null, null);

        ArrayList<User> mUsers = new ArrayList<>();
        if(mCursor != null)
        {
            if(mCursor.getCount() != 0)
            {
                mCursor.moveToFirst();
                while (!mCursor.isAfterLast())
                {
                    User mUser = new User();
                    mUser.setEmail(mCursor.getString(1));
                    mUser.setPassword(mCursor.getString(2));

                    mUsers.add(mUser);

                    mCursor.moveToNext();
                }
            }
        }

        db.close();
        return mUsers;
    }
}