package by.bstu.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {

    EditText mailBox;
    EditText phoneBox;
    EditText addressBox;
    EditText linkBox;
    CheckBox favoriteBox;
    Button delButton;
    Button saveButton;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        init();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            mailBox.setText(userCursor.getString(2));
            phoneBox.setText(userCursor.getString(3));
            addressBox.setText(userCursor.getString(4));
            linkBox.setText(userCursor.getString(5));
            if (userCursor.getString(6).equals("1")) favoriteBox.setChecked(true);

            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }
    public void init(){
        mailBox = (EditText) findViewById(R.id.emailEditText);
        phoneBox = (EditText) findViewById(R.id.phoneEditText);
        addressBox = (EditText) findViewById(R.id.locationEditText);
        linkBox = (EditText) findViewById(R.id.socialNetworkEditText);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        favoriteBox = (CheckBox) findViewById(R.id.favorite);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();
    }
    public void save(View view){
        int favor=0;
        if(favoriteBox.isChecked()) favor=1;

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_MAIL, mailBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_PHONE, phoneBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_ADDRESS, addressBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_LINK, linkBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_FAVORITE, favor);

        if (userId > 0) {
            db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
        } else {
            db.insert(DatabaseHelper.TABLE, null, cv);
        }
        goHome();
    }
    public void delete(View view){
        db.delete(DatabaseHelper.TABLE, "_id = ?", new String[]{String.valueOf(userId)});
        goHome();
    }
    private void goHome(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}