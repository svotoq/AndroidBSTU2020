package psy.fit.bstu.lab8.data;

import java.util.ArrayList;

import psy.fit.bstu.lab8.models.Contact;

public class DefaultData {
    public static ArrayList<Contact> getDefaultContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        Contact firstContact = new Contact();
        firstContact.setId(1);
        firstContact.setName("Stas");
        firstContact.setPhone("+375442331241");
        firstContact.setLocation("Minsk");
        firstContact.setVkLink("isvoq");

        Contact secondContact = new Contact();
        secondContact.setId(2);
        secondContact.setName("Lexa");
        secondContact.setPhone("+375291234123");
        secondContact.setLocation("Minsk");
        secondContact.setVkLink("lexik");

        Contact thirdContact = new Contact();
        thirdContact.setId(3);
        thirdContact.setName("Vova");
        thirdContact.setPhone("+3752942233418");
        thirdContact.setLocation("Gomel");
        thirdContact.setVkLink("vov4ik");

        contacts.add(firstContact);
        contacts.add(secondContact);
        contacts.add(thirdContact);

        return contacts;
    }
}
