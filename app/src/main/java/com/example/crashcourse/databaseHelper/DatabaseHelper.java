package com.example.crashcourse.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.crashcourse.EntityClass.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Static Variables to Database Tables -- For Future Use
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_F_NAME = "fName";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USER_PROFILE = "userProfile";
    public static final String USER_PROFILE_TABLE = "USER_PROFILE_TABLE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "crashCourse.db", null, 1);
    }

    //Will be called when the application is started. Code to Create DB
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_USER_NAME + " TEXT," + COLUMN_F_NAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, " + COLUMN_EMAIL + " TEXT, "
                + COLUMN_USER_PROFILE + " TEXT)";
        db.execSQL(createTableStatement);
    }

    //Forward compatibility for upcoming versions.
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public boolean createUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, user.getUserName());
        cv.put(COLUMN_PASSWORD, user.getPassword());
        cv.put(COLUMN_F_NAME, user.getfName());
        cv.put(COLUMN_EMAIL, user.getEmail());
        cv.put(COLUMN_USER_PROFILE, user.getUserProfile());

        long insert = db.insert(USER_TABLE,null,cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public List<String> getuserP(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + USER_PROFILE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

}
