package psy.fit.bstu.lab6.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.SimpleCursorAdapter;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import psy.fit.bstu.lab6.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DatabaseVersion = 1;
    public static final String DatabaseName = "contacts.db";
    public static final String TableName = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_SOCIAL = "social";
    public static final String KEY_LOCATION = "location";

    private static final String sqlDbCreation = "create table " + TableName + " (" + KEY_ID + " integer primary key," + KEY_EMAIL + " text, "
            + KEY_PHONE + " text,"+ KEY_SOCIAL + " text, " + KEY_LOCATION + " text )";
    private static final String sqlDbRemove = "drop table if exists " + TableName;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlDbCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(sqlDbRemove);
        onCreate(sqLiteDatabase);
    }
}