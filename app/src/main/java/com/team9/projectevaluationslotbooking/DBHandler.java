package com.team9.projectevaluationslotbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context) {
        super(context, "SlotBooking.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(email_address text primary key, password text, fname text, lname text, sex text, phone_number text, branch text, roll_number text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists student");
    }

    public boolean registerStudent(String fname, String lname, String sex, String phone, String branch, String roll, String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname",fname);
        contentValues.put("lname",lname);
        contentValues.put("sex",sex);
        contentValues.put("phone_number",phone);
        contentValues.put("roll_number",roll);
        contentValues.put("email_address",email);
        contentValues.put("password",pass);
        contentValues.put("branch",branch);

        long result = db.insert("student",null,contentValues);
        if(result==-1)  return false;
        else            return true;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from student where email_address = ?", new String[] {username});
        if(cursor.getCount()>0)     return true;
        else                        return false;
    }

    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from student where email_address = ? and password =?", new String[] {username,password});
        if(cursor.getCount()>0)     return true;
        else                        return false;
    }
}
