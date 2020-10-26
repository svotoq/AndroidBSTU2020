package psy.fit.bstu.lab6.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "ContactDatabase";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contacts " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, " +
                "phone TEXT, " +
                "socialNetwork TEXT, " +
                "location TEXT)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS contacts";
        db.execSQL(sql);
        onCreate(db);
    }
}