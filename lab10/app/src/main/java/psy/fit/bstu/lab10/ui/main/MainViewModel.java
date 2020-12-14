package psy.fit.bstu.lab10.ui.main;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import psy.fit.bstu.lab10.model.Contact;
import psy.fit.bstu.lab10.repository.ContactRepository;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Contact>> contacts;
    private ContactRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        contacts = repository.getContacts();
    }


    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public void insert(Contact contact) {
        repository.insert(contact);
    }

    public void update(Contact contact) {
        repository.update(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

    public Contact getById(long id) {
        return repository.getById(id);
    }
}