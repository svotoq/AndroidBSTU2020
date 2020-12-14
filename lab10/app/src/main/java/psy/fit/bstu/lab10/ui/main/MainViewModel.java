package psy.fit.bstu.lab10.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import psy.fit.bstu.lab10.model.Contact;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Contact>> contacts;

    public LiveData<List<Contact>> getContacts() {
        if (contacts == null) {
            contacts = new MutableLiveData<>();
            loadContacts();
        }
        return contacts;
    }

    private void loadContacts() {
        contacts = new MutableLiveData<>();
        contacts.setValue(Contact.initializeData());
    }
}