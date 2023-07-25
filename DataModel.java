package com.example.deniksqllite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DataModel extends SQLiteOpenHelper {
    protected static final String DB_DATABAZE = "dbKknihy";
    protected static final int DB_VERZE = 1;
    protected static final String DB_TABULKA = "tblKnihy";

    //atributy sloupce tabulky
    public static final String ATR_ID = "_id";
    public static final String ATR_AUTOR = "autor";
    public static final String ATR_KNIHA = "kniha";
    public static final String ATR_DATUM = "datum";
    public static final String ATR_HODNOCENI = "hodnoceni";

    public DataModel (Context ctx) {
        super(ctx, "DB_DATABAZE",null,DB_VERZE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_TABULKA
                + "(" +ATR_ID + " INTEGER PRIMARY KEY,"
                + ATR_AUTOR + " TEXT , "
                + ATR_KNIHA + " TEXT,"
                + ATR_DATUM + " TEXT ,"
                + ATR_HODNOCENI + " TEXT "
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DB_TABULKA;
        db.execSQL(query);
        onCreate(db);

    }public long vlozZaznam(HashMap<String,String> atributy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put (ATR_AUTOR, atributy.get(ATR_AUTOR));
        val.put(ATR_KNIHA, atributy.get(ATR_KNIHA));
        val.put(ATR_DATUM, atributy.get(ATR_DATUM));
        val.put(ATR_HODNOCENI, atributy.get(ATR_HODNOCENI));
        long id = db.insert(DB_TABULKA, null, val);
        db.close();
        return id;
    }
    public int aktualizujZaznam(HashMap<String,String> atributy){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put (ATR_AUTOR, atributy.get(ATR_AUTOR));
        val.put(ATR_KNIHA, atributy.get(ATR_KNIHA));
        val.put(ATR_DATUM, atributy.get(ATR_DATUM));
        val.put(ATR_HODNOCENI, atributy.get(ATR_HODNOCENI));
        return db.update(DB_TABULKA, val, ATR_ID +"= ?",
                new String[]{atributy.get(ATR_ID)});
    }

    public void vymazZaznam (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + DB_TABULKA + " WHERE _id='" + id + "'";
        db.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String,String>> dajZaznamy (){
        ArrayList<HashMap<String,String>> alVysledky;
        alVysledky = new ArrayList<HashMap<String,String>>();
        String sSQL = "SELECT * FROM "+ DB_TABULKA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sSQL, null);

        // presun na prvy zaznam
        if ( cursor.moveToFirst()){
            do {
                HashMap<String,String> hm = new HashMap<String,String>();
                hm.put(ATR_ID,cursor.getString(0));
                hm.put(ATR_AUTOR, cursor.getString(1));
                hm.put(ATR_KNIHA, cursor.getString(2));
                hm.put(ATR_DATUM, cursor.getString(3));
                hm.put(ATR_HODNOCENI, cursor.getString(4));
                alVysledky.add(hm);
            } while (cursor.moveToNext()); // kurzor na dalsi zaznam
        }
        return alVysledky;
    }

    public HashMap<String,String> dajZaznam(String id) {
        HashMap<String,String> hm = new HashMap<String,String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sSQL = "SELECT * FROM " + DB_TABULKA + " WHERE _id='" +id+"'";
        Cursor cursor = db.rawQuery(sSQL, null);
        if (cursor.moveToFirst()) {
            do {
                hm.put(ATR_AUTOR, cursor.getString(1));
                hm.put(ATR_KNIHA, cursor.getString(2));
                hm.put(ATR_DATUM, cursor.getString(3));
                hm.put(ATR_HODNOCENI, cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return hm;
    }
}
