package com.example.a2study_projektkurs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseAdapter{

    DatabaseHelper1 helper;
    SQLiteDatabase db;
    Context context;

    public DatabaseAdapter(Context context){

        helper = new DatabaseHelper1(context);
        db = helper.getWritableDatabase();
        this.context = context;
    }

    public long insertData(String bezeichnung, String datum, long dateinmilis)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper1.KEY_BEZEICHNUNG, bezeichnung);
        contentValues.put(DatabaseHelper1.KEY_DATUM, datum);
        contentValues.put(DatabaseHelper1.KEY_DATEINMILIS, dateinmilis);
        long id = db.insert(DatabaseHelper1.TABLE_NAME, null, contentValues);
        return id;
    }

}

class DatabaseHelper1 extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "mydb.db";
    public static final String TABLE_NAME = "events";

    private static final int DATABASE_VERSION = 12;
    public static final String KEY_ROWID = "_id";
    public static final String KEY_BEZEICHNUNG = "bezeichnung";
    public static final String KEY_DATUM = "datum";
    public static final String KEY_DATEINMILIS = "dateinmilis";
    private static final String CREATE_TABLE = "create table if not exists "+ TABLE_NAME+
            " ("+ KEY_ROWID+" integer primary key autoincrement, "+ KEY_BEZEICHNUNG + " text, "+ KEY_DATUM+ " text, "+ KEY_DATEINMILIS+ " text)";
    private static final String DROP_TABLE = "drop table if exists "+ DATABASE_NAME;
    private Context context;

    public DatabaseHelper1(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        }catch (SQLException e){
            Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (SQLException e){
            Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
        }
    }
}
