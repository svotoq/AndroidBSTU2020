package psy.fit.bstu.lab10.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import psy.fit.bstu.lab10.dao.ContactsDao;
import psy.fit.bstu.lab10.model.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase instance;

    public abstract ContactsDao contactsDao();

    public static synchronized ContactDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class, "contact_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ContactsDao contactsDao;

        private PopulateDbAsyncTask(ContactDatabase db) {
            this.contactsDao = db.contactsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactsDao.insert(new Contact("Stas", "+375444526771"));
            contactsDao.insert(new Contact("Vasya", "+375298613243"));
            contactsDao.insert(new Contact("Egor", "+375214124214"));
            return null;
        }
    }
}
