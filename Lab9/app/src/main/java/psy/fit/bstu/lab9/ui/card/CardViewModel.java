package psy.fit.bstu.lab9.ui.card;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import psy.fit.bstu.lab9.Contact;

public class CardViewModel extends ViewModel {

    private MutableLiveData<Contact> contact;

    public CardViewModel() {
    }
    public LiveData<Contact> getContact() {
        if (contact == null) {
            contact = new MutableLiveData<Contact>();
            loadContact();
        }
        return contact;
    }

    private void loadContact() {
        contact.setValue(Contact.initializeData().get(0));
    }
}