package com.example.kudzai.EasySec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class Db_Operations extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Zaoga.db";
    public static final String TABLE_NAME = "Users";
    public static final String TABLE_NAME2 = "Expenses";
    public static final String TABLE_NAME3 = "Subscriptions";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
    public static final String COL_5 = "USER_TYPE";


    public Db_Operations(Context context) {
        super(context, DATABASE_NAME, null, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PASSWORD TEXT,USER_TYPE TEXT)");
        db.execSQL("create table " + TABLE_NAME2 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,AMOUNT INTEGER,DESCRIPTION TEXT)");
        db.execSQL("create table " + TABLE_NAME3 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FULL_NAME TEXT,DISTRICT TEXT,AMOUNT INTEGER,PURPOSE TEXT,PAYMODE TEXT,REFERENCE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        onCreate(db);
    }

    public boolean insertData(String table_name,String name,String email,String password,int usertype) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        contentValues.put(COL_5,usertype);
        long result = db.insert(table_name,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insertExp(String table_name ,String date,int amount,String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE",date);
        contentValues.put("AMOUNT",amount);
        contentValues.put("DESCRIPTION",description);
        long result = db.insert(table_name,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertSubscription(String table_name ,String date,String full_name,String district,int amount,String purpose,String paymode, String reference) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE",date);
        contentValues.put("FULL_NAME",full_name);
        contentValues.put("DISTRICT",district);
        contentValues.put("AMOUNT",amount);
        contentValues.put("PURPOSE",purpose);
        contentValues.put("PAYMODE",paymode);
        contentValues.put("REFERENCE",reference);
        long result = db.insert(table_name,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData(String table_name ) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res = db.rawQuery("select * from "+table_name,null);

       Cursor res = db.rawQuery("SELECT * FROM " + table_name  ,null);

        return res;
    }


    public Cursor GetFromTodata(String table_name , String from ,String to) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + table_name + " WHERE DATE  BETWEEN "+from+" AND "+to+" " ,null);
        return res;
    }

    public Cursor getLoginData(String table_name ,String email ,String password) {


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + table_name + " WHERE " +COL_3 + " = '" + email+"' AND "+COL_4+ "= '"+password+"'" ,null);

        return res;

    }
    public Cursor GetSumOfColumns(String table_name ,String Column) {


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT SUM(amount) AS TOTAL  FROM " + table_name,null);
        return res;
    }
    public Cursor IsAdmin(String table_name ,String email ,int usertype) {


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + table_name + " WHERE " +COL_3 + " = '" + email+"' AND "+COL_5+ "= '"+usertype+"'" ,null);
        return res;
    }

    public Cursor GetPayModeTotals(String table_name ,String Column ,String paymode ) {


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT SUM(amount) AS TOTAL FROM " + table_name + " WHERE " +Column + " = '" + paymode+"'" ,null);
        return res;
    }

    public boolean updateData(String id,String name,String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String table_name , String id) {
        SQLiteDatabase db = this.getWritableDatabase();
       // return db.delete(table_name, "ID = ?",new String[] {id});
        return db.delete(table_name, "ID = ?",new String[] {id});
    }
    public Integer SettleAccount (String table_name ) {
        SQLiteDatabase db = this.getWritableDatabase();
        // return db.delete(table_name, "ID = ?",new String[] {id});
        return db.delete(table_name, null,null);
    }
}