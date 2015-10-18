package com.mazinaltokhais.WorkingLogs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazoo_000 on 08/09/2015.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "FingerPrints";

    // Contacts table name
    private static final String TABLE_CONTACTS = "logsIn_Out";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_Day = "day";
    private static final String KEY_Month = "month";
    private static final String KEY_Year = "year";
    private static final String KEY_Time = "time";
    private static final String KEY_Type = "type";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Day + " TEXT,"+ KEY_Month + " TEXT,"+ KEY_Year + " TEXT,"
                + KEY_Time + " TEXT," +  KEY_Type + " INTEGER )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addFPLogs(FPlogs contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Day, contact.getDay()); // Contact Name
        values.put(KEY_Month, contact.getMonth());
        values.put(KEY_Year, contact.getYear());
        values.put(KEY_Time, contact.getTime()); // Contact Phone
        values.put(KEY_Type, contact.getType()); //

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    FPlogs getFPlogs(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_Day,KEY_Month,KEY_Year, KEY_Time, KEY_Type }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        FPlogs contact = new FPlogs(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4),Integer.parseInt(cursor.getString(5)));
        // return contact
        return contact;
    }

    // Getting single contactBydate
    List<FPlogs> getFPlogsByMonthAndtype(String date, String type) {
        List<FPlogs> contactList = new ArrayList<FPlogs>();
        SQLiteDatabase db = this.getReadableDatabase();
      //  String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;


        //String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS+ "WHERE "+KEY_Date + "=? AND " +KEY_Type +"=?";

        // db = this.getWritableDatabase();
      //  Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_Day,KEY_Month,KEY_Year, KEY_Time, KEY_Type }, KEY_Month + "=? AND " +KEY_Type +"=?",
                new String[] { date,type }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                FPlogs FPlogs = new FPlogs();
                FPlogs.setId(Integer.parseInt(cursor.getString(0)));
                FPlogs.setDay(cursor.getString(1));
                FPlogs.setMonth(cursor.getString(2));
                FPlogs.setYear(cursor.getString(3));
                FPlogs.setTime(cursor.getString(4));
                FPlogs.setType(Integer.parseInt(cursor.getString(5)));
                // Adding contact to list
                contactList.add(FPlogs);
            } while (cursor.moveToNext());
        }
       // FPlogs contact = new FPlogs(Integer.parseInt(cursor.getString(0)),
         //       cursor.getString(1), cursor.getString(2),Integer.parseInt(cursor.getString(3)));
        // return contact
        return contactList;
    }
    List<FPlogs> getFPlogsByDateAndtype(String day,String month, String type) {
        List<FPlogs> contactList = new ArrayList<FPlogs>();
        SQLiteDatabase db = this.getReadableDatabase();
        //  String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;


        //String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS+ "WHERE "+KEY_Date + "=? AND " +KEY_Type +"=?";

        // db = this.getWritableDatabase();
        //  Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_Day,KEY_Month,KEY_Year, KEY_Time, KEY_Type }, KEY_Month + "=? AND "+ KEY_Day + "=? AND " +KEY_Type +"=?",
                new String[] { month,day,type }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                FPlogs FPlogs = new FPlogs();
                FPlogs.setId(Integer.parseInt(cursor.getString(0)));
                FPlogs.setDay(cursor.getString(1));
                FPlogs.setMonth(cursor.getString(2));
                FPlogs.setYear(cursor.getString(3));
                FPlogs.setTime(cursor.getString(4));
                FPlogs.setType(Integer.parseInt(cursor.getString(5)));
                // Adding contact to list
                contactList.add(FPlogs);
            } while (cursor.moveToNext());
        }
        // FPlogs contact = new FPlogs(Integer.parseInt(cursor.getString(0)),
        //       cursor.getString(1), cursor.getString(2),Integer.parseInt(cursor.getString(3)));
        // return contact
        return contactList;
    }
    // Getting All Contacts
    public List<FPlogs> getAllFPlogs() {
        List<FPlogs> contactList = new ArrayList<FPlogs>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FPlogs FPlogs = new FPlogs();
                FPlogs.setId(Integer.parseInt(cursor.getString(0)));
                FPlogs.setDay(cursor.getString(1));
                FPlogs.setMonth(cursor.getString(2));
                FPlogs.setYear(cursor.getString(3));
                FPlogs.setTime(cursor.getString(4));
                FPlogs.setType(Integer.parseInt(cursor.getString(5)));
                // Adding contact to list
                contactList.add(FPlogs);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    // Updating single contact
    public int updateFPlogs(FPlogs FPlogs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Day, FPlogs.getDay());
        values.put(KEY_Month, FPlogs.getMonth());
        values.put(KEY_Year, FPlogs.getYear());
        values.put(KEY_Time, FPlogs.getTime());
        values.put(KEY_Type, FPlogs.getType());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(FPlogs.getId()) });
    }
    // Deleting single contact
    public void deleteFPlogs(FPlogs contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
    // Getting contacts Count
    public int getFPlogsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
