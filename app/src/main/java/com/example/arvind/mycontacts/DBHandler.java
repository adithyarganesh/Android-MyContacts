package com.example.arvind.mycontacts;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private String DATABASE = "CONTACT.db";
    private String NAME = "NAME";
    private String EMAIL = "EMAIL";
    private String DOB = "DOB";
    private String PHONE = "PHONE";

    public DBHandler(Context context) {
        super(context,"CONTACT",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CONTACT( NAME VARCHAR(30), EMAIL VARCHAR(30), PHONE NUMBER(10), DOB VARCHAR(10) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void addContact(String name,String email,String phone,String dob){
        ContentValues cv = new ContentValues();
        cv.put(NAME,name);
        cv.put(EMAIL,email);
        cv.put(PHONE,phone);
        cv.put(DOB,dob);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("CONTACT", null, cv);
    }

    ArrayList<String> getNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT NAME,EMAIL FROM CONTACT", null);
        ArrayList<String> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add( cursor.getString(0));
            cursor.moveToNext();
        }
        return arrayList;
    }

    String getDetail(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String s ;
        Cursor cursor = db.rawQuery("SELECT * FROM CONTACT WHERE NAME LIKE " + "\"%" + name + "%\"" ,null);
        cursor.moveToFirst();
       // while(!c.isAfterLast()) {
            s = "NAME: " + cursor.getString(0) + "\nEMAIL: " +cursor.getString(1) + "\nPHONE: " +cursor.getString(2) + "\nDOB: " + cursor.getString(3);
        // c.getString(0) + c.getString(1) +  c.getString(2)  + c.getString(3) ;
        //}c.moveToNext();
        return s;
        //

    }


    void delete(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM CONTACT WHERE NAME = \"" + s +"\"");
    }
}
