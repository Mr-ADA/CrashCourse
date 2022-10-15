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

/*
@File Name:sysAdmin_DatabaseHelper.java
@Brief: Database Controller Class
@Team:Crash Course
@Author:Sean Yeo Degen [7564880]
@Date: 12 - 10 - 22
 */

public class sysAdmin_DatabaseHelper extends SQLiteOpenHelper {

    //Static Variables to Database Tables -- For Future Use
    //Table Shortcut Variables
    public static final String USER_TABLE = "USER_TABLE";
    public static final String USER_PROFILE_TABLE = "USER_PROFILE_TABLE";

    //Attributes Shortcut
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_F_NAME = "fName";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USER_PROFILE = "userProfile";



    public sysAdmin_DatabaseHelper(@Nullable Context context) {
        super(context, "crashCourse.db", null, 1);
    }

    //Will be called when the application is started. Code to Create DB
    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQL COMMAND to CREATE USER_TABLE
        String createTableStatement = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_USER_NAME + " TEXT," + COLUMN_F_NAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_USER_PROFILE + " TEXT)";
        db.execSQL(createTableStatement);

        //SQL COMMAND TO CREATE USER_PROFILE_TABLE
        String createUPTableStatement = "CREATE TABLE " + USER_PROFILE_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_USER_PROFILE + " TEXT)";
        db.execSQL(createUPTableStatement);

        //SQL COMMAND TO INSERT User Profile Default Values
        String insertUPValuesStatement = "INSERT INTO " + USER_PROFILE_TABLE + " (" + COLUMN_ID + "," + COLUMN_USER_PROFILE + ") VALUES (1,'System Admin'), (2,'Author'), (3,'Reviewer'), (4,'Conference Chair')";
        db.execSQL(insertUPValuesStatement);

    }

    //Forward compatibility for upcoming versions.
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
    /*
    @Function: createUser(User user)
    @User Story: 03
    @Brief: System Admin to Create User Account
    @Team:Crash Course
    @Author:Sean Yeo Degen [7564880]
    @Date: 12 - 10 - 22
    */
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

    /*
    @Function: getuserP()
    @Brief: Auto Populate Spinner with User Profile items from Sqlite DB - USER_PROFILE_TABLE -
    userprofile.
    @Team:Crash Course
    @Author:Sean Yeo Degen [7564880]
    @Date: 12 - 10 - 22
     */
    public List<String> getuserP(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + USER_PROFILE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //0 == id column in User_Profile_table
                //1 == userProfile column in User_Profile Table
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // Closing connection
        cursor.close();
        db.close();

        // Return all User Profile into a List
        return list;
    }

}
