package psy.fit.bstu.lab9.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import psy.fit.bstu.lab9.Contact;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Contact>> contacts;

    public HomeViewModel() {
    }

    public LiveData<List<Contact>> getContacts() {
        if (contacts == null) {
            contacts = new MutableLiveData<List<Contact>>();
            loadContacts();
        }
        return contacts;
    }

    private void loadContacts() {
        contacts = new MutableLiveData<>();
        contacts.setValue(Contact.initializeData());
    }
}