package com.example.sqlps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Contacts DB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACT = "Contacts";
    private static final String Conatact_name = "Name";
    private static final String Contact_ID = "ID";
    private static final String Contact_number = "Number";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_CONTACT + "(" + Contact_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Conatact_name
        + " TEXT," + Contact_number + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACT);
        onCreate(db);
    }
    public void addcontact(String name,String phone_number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Conatact_name,name);
        values.put(Contact_number,phone_number);
        db.insert(TABLE_CONTACT,null,values);
    }
    public ArrayList<structure> fetchcont(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_CONTACT,null);
        ArrayList<structure> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            structure structure = new structure();
            structure.id = cursor.getInt(0);
            structure.name = cursor.getString(1);
            structure.number = cursor.getString(2);
            arrayList.add(structure);

        }
        return arrayList;
    }
    public void updatecontacts(structure structure){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Contact_number,structure.number);
        database.update(TABLE_CONTACT,cv,Contact_ID +" = "+structure.id,null);
    }
    public void DeleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT,Contact_ID+ " = ? ",new String[]{String.valueOf(id)});
    }
}
