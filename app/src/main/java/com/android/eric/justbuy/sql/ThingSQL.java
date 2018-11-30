package com.android.eric.justbuy.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.eric.justbuy.model.Thing;

import java.util.ArrayList;
import java.util.List;

public class ThingSQL {
    private static final String TAG = "ThingSQL";
    private static ThingSQL thingSQL;
    private SQLiteDbHelper sqLiteDbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ThingSQL(Context context) {
        sqLiteDbHelper = new SQLiteDbHelper(context);
        sqLiteDatabase = sqLiteDbHelper.getWritableDatabase();
    }
    public static ThingSQL getInstance(Context context) {
        if (thingSQL == null) {
            thingSQL = new ThingSQL(context.getApplicationContext());
        }
        return thingSQL;
    }

    public void add(Thing thing) {
        Log.i(TAG, "add: " + thing.getName());
        ContentValues values = new ContentValues();
        values.put("thing_name", thing.getName());
        values.put("thing_img", thing.getImage());
        values.put("thing_num", thing.getNumber());
        values.put("thing_price", thing.getUnitPrice());
        values.put("thing_tax", thing.getTax());
        values.put("thing_discount", thing.getDiscount());
        values.put("local_rate", thing.getLocalRate());
        values.put("purchase_time", thing.getPurchaseTime());
        sqLiteDatabase.insert(Common.TABLE_NAME, null, values);
    }

    public void delete(Thing thing) {
        sqLiteDatabase.execSQL("delete from " + Common.TABLE_NAME + " where thing_name = " + "\"" + thing.getName() + "\"");
//  sqLiteDatabase.execSQL("delete from " + Common.TABLE_NAME + " where thing_name = " + thing.getName() + " and purchase_time = " + thing.getPurchaseTime());
    }

    public List<Thing> queryAll() {
        List<Thing> things = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + Common.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Thing thing = new Thing();
            thing.setName(cursor.getString(cursor.getColumnIndex("thing_name")));
            thing.setImage(cursor.getBlob(cursor.getColumnIndex("thing_img")));
            thing.setNumber(cursor.getInt(cursor.getColumnIndex("thing_num")));
            thing.setUnitPrice(cursor.getFloat(cursor.getColumnIndex("thing_price")));
            thing.setTax(cursor.getFloat(cursor.getColumnIndex("thing_tax")));
            thing.setLocalRate(cursor.getFloat(cursor.getColumnIndex("local_rate")));
            thing.setDiscount(cursor.getFloat(cursor.getColumnIndex("thing_discount")));
            thing.setPurchaseTime(cursor.getString(cursor.getColumnIndex("purchase_time")));
            things.add(thing);
        }
        return things;
    }
}
