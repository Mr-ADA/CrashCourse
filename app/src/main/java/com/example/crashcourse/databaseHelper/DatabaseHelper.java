package com.example.crashcourse.databaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "crashCourse.db", null, 1);
    }

    //Will be called when the application is started. Code to Create DB
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    //Forward compatibility for upcoming versions.
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
