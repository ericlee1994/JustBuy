package com.android.eric.justbuy.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLiteDbHelper";



    public SQLiteDbHelper(Context context) {
        super(context, Common.DB_NAME, null, Common.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSql = "create table if not exists " + Common.TABLE_NAME
                + "(id integer primary key,"
                + "thing_name varchar,"
                + "thing_img blob,"
                + "thing_num integer,"
                + "thing_price float,"
                + "thing_tax float,"
                + "thing_discount float,"
                + "thing_category varchar,"
                + "local_rate float,"
                + "purchase_time varchar)";
        db.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
