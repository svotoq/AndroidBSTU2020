package psy.fit.bstu.lab10.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import psy.fit.bstu.lab10.dao.ContactsDao;
import psy.fit.bstu.lab10.db.ContactDatabase;
import psy.fit.bstu.lab10.model.Contact;

public class ContactRepository {
    private ContactsDao contactsDao;
    private LiveData<List<Contact>> contacts;

    public ContactRepository(Application application) {
        ContactDatabase database = ContactDatabase.getInstance(application);
        contactsDao = database.contactsDao();
        contacts = contactsDao.getAllContacts();
    }

    public void insert(Contact contact) {
        new InsertNoteAsyncTask(contactsDao).execute(contact);
    }

    public void update(Contact contact) {
        new UpdateNoteAsyncTask(contactsDao).execute(contact);
    }

    public void delete(Contact contact) {
        new DeleteNoteAsyncTask(contactsDao).execute(contact);
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public Contact getById(long id) {
        return contactsDao.getById(id);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Contact, Void, Void> {
        private final ContactsDao contactsDao;

        private InsertNoteAsyncTask(ContactsDao contactsDao) {
            this.contactsDao = contactsDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactsDao.insert(contacts[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Contact, Void, Void> {
        private final ContactsDao contactsDao;

        private UpdateNoteAsyncTask(ContactsDao contactsDao) {
            this.contactsDao = contactsDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactsDao.update(contacts[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Contact, Void, Void> {
        private final ContactsDao contactsDao;

        private DeleteNoteAsyncTask(ContactsDao contactsDao) {
            this.contactsDao = contactsDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactsDao.delete(contacts[0]);
            return null;
        }
    }
}
