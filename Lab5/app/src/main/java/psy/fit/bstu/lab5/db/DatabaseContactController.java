package psy.fit.bstu.lab5.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import psy.fit.bstu.lab5.Contact;

public class DatabaseContactController extends DatabaseHandler {
    public DatabaseContactController(Context context) {
        super(context);
    }

    public boolean create(Contact contact) {
        ContentValues values = GetValues(contact, false);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessful = db.insert("contacts", null, values) > 0;
        db.close();
        return createSuccessful;
    }

    public List<Contact> read() {
        List<Contact> recordsList = new ArrayList<>();
        String sql = "SELECT * FROM contacts ORDER BY id DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                recordsList.add(CreateContact(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordsList;
    }

    public Contact readSingleRecord(int contactId) {
        Contact contact = null;
        String sql = "SELECT * FROM contacts WHERE id = " + contactId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            contact = CreateContact(cursor);
        }
        cursor.close();
        db.close();
        return contact;
    }

    public boolean update(Contact contact) {
        ContentValues values = GetValues(contact, true);
        String where = "id = ?";
        String[] whereArgs = {Integer.toString(contact.getID())};
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update("contacts", values, where, whereArgs) > 0;
        db.close();
        return updateSuccessful;
    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;
        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("contacts", "id ='" + id + "'", null) > 0;
        db.close();
        return deleteSuccessful;
    }

    private Contact CreateContact(Cursor cursor) {
        return new Contact(
                Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))),
                cursor.getString(cursor.getColumnIndex("email")),
                cursor.getString(cursor.getColumnIndex("phone")),
                cursor.getString(cursor.getColumnIndex("location")),
                cursor.getString(cursor.getColumnIndex("socialNetwork"))
        );
    }

    private ContentValues GetValues(Contact contact, boolean isUpdate) {
        ContentValues values = new ContentValues();
        values.put("email", contact.getEmail());
        values.put("phone", contact.getPhoneNumber());
        values.put("location", contact.getLocation());
        values.put("socialNetwork", contact.getSocialNetwork());
        return values;
    }
}