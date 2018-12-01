package com.skyk2011.auctiontemplate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by akira on 2018-01-23.
 */

public class MemoOpenHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "myTemplate.db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_TABLE =
            "create table templates (" +
                    "_id integer primary key autoincrement, " +
                    "title text, " +
                    "body text, " +
                    "created datetime default current_timestamp," +
                    "updated datetime default current_timestamp)";

    public static final String INIT_TABLE =
            "insert into templates (title, body) values " +
                    "('sample title', 'sample text')";

    public static final String DROP_TABLE =
            "drop table if exists templates";



    public MemoOpenHelper(Context c){
        super(c, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(INIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
