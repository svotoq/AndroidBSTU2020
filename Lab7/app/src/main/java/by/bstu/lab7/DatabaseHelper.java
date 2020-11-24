package by.bstu.lab7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "contacts"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MAIL = "mail";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_FAVORITE = "favorite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE contacts (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MAIL
                + " TEXT, " + COLUMN_PHONE
                + " TEXT, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_LINK
                + " TEXT, "+ COLUMN_FAVORITE +" INTEGER);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_MAIL
                + ", " + COLUMN_PHONE
                + ", " + COLUMN_ADDRESS  + ", " + COLUMN_LINK
                + ", "+ COLUMN_FAVORITE +") VALUES ('Stanislau', 'stanislau.pyrkin@gmail.com', '+375298639126', 'Minsk Belarus', 'vk.com/isvoq', 1);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

}
