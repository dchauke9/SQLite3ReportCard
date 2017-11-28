package com.example.ekasilabalexcdtb.sqlite3reportcard.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ekasilabalexcdtb.sqlite3reportcard.ReportClasses.AppClass;

import java.util.ArrayList;

/**
 * Created by eKasiLab Alex CDTB on 20 Nov 2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    //Log in table
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "report.db";
    private static final String TABLE_NAME = "AppReportCard";
    private final static String COLUMN_ID = "id";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_STUDNO = "email";
    public final static String COLUMN_PASSWORD = "password";
    public final static String COLUMN_SUB1 = "sub1";
    public final static String COLUMN_SUB2 = "sub2";
    public final static String COLUMN_SUB3 = "sub3";
    public final static String COLUMN_SUB4 = "sub4";
    public final static String COLUMN_SUB5 = "sub5";
    public final static String COLUMN_SUB6 = "sub6";



    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO Auto-generated method stub
        db.execSQL("create table person " +
                "(id Integer primary key AutoIncrement, name text, email text, sub1 text, sub2 text, sub3 text," +
                "sub4 text, sub5 text, sub6 text);"
        );
        Log.v("Database operation", "Database Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertInformation(String id, String name, String email, String password, String sub1, String sub2, String sub3, String sub4, String sub5, String sub6) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id", id);
        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("sub1", sub1);
        cv.put("sub2", sub2);
        cv.put("sub3", sub3);
        cv.put("sub4", sub4);
        cv.put("sub5", sub5);
        cv.put("sub6", sub6);

        db.insert(TABLE_NAME, null, cv);
    }

    public void putInformation(DBHelper dbHelper, String name, String password) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD, password);
        long k = sqLiteDatabase.insert(TABLE_NAME, null, values);
        Log.v("Database operation", "One row inserted");
    }

    public Cursor getInformation(DBHelper dbHelper) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String[] columns = {COLUMN_NAME, COLUMN_PASSWORD};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }


    public ArrayList<AppClass> getAllApps() {
        ArrayList<AppClass> arrayList = new ArrayList<AppClass>();
        String query = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        //String name, String email, String password, String sub1,
        //String sub2, String sub3, String sub4, String sub5, String sub6
        while (cursor.isAfterLast()) {

            arrayList.add(new AppClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)));
            cursor.moveToNext();
        }

        return arrayList;
    }

    public boolean updateApp(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);

        db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{String.valueOf((id))});
        return true;
    }

    public Integer deleteApp(Integer id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "id = ?", new String[]{Integer.toString(id)});
    }

    public Cursor getApp(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_STUDNO + "=" + email + "", null);

        return cursor;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }
}


