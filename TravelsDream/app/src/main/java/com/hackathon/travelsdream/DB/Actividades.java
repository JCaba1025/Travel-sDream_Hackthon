package com.hackathon.travelsdream.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Actividades extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB_Actividades.sqlite";
    private static final int DB_VERSION = 1;

    private static final String ACTIVIDADES_TABLE_CREATE = "CREATE TABLE actividades(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nick TEXT, name TEXT, departamento TEXT, municipio TEXT, place TEXT, descrip TEXT, picture TEXT, precio TEXT, tipo TEXT)";


    public Actividades(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ACTIVIDADES_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData (String sentence){

        boolean success = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sentence);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        return success;
    }

    public Cursor getData (String sentence, String[] params) {

        SQLiteDatabase GetDB = getReadableDatabase();
        Cursor cu = GetDB.rawQuery(sentence, params);
        return cu;

    }

    public int deleteData(String table, String whereClause, String[] params) {
        SQLiteDatabase db = getWritableDatabase();
        int nRows = db.delete(table, whereClause, params);
        return nRows;
    }

    public int updateData(String table, ContentValues cv, String whereClause, String[] params) {
        SQLiteDatabase db = getWritableDatabase();
        int nRows = db.update(table, cv, whereClause, params);
        return nRows;
    }

}


