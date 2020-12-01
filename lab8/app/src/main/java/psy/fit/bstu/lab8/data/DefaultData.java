package psy.fit.bstu.lab8.data;

import java.util.ArrayList;

import psy.fit.bstu.lab8.models.Contact;

public class DefaultData {
    public static ArrayList<Contact> getMockedContacts() {
        ArrayList<Contact> mockedContacts = new ArrayList<>();

        Contact firstContact = new Contact();
        firstContact.setId(1);
        firstContact.setName("Oleg");
        firstContact.setPhone("+12345683");
        firstContact.setLocation("Minsk");
        firstContact.setVkLink("o.ryazin");

        Contact secondContact = new Contact();
        secondContact.setId(2);
        secondContact.setName("Igor");
        secondContact.setPhone("+919853256");
        secondContact.setLocation("Minsk");
        secondContact.setVkLink("ihar");

        Contact thirdContact = new Contact();
        thirdContact.setId(3);
        thirdContact.setName("Kolya");
        thirdContact.setPhone("+7893513782");
        thirdContact.setLocation("Slutsk");
        thirdContact.setVkLink("kolya");

        Contact fourthContact = new Contact();
        fourthContact.setId(4);
        fourthContact.setName("Dima");
        fourthContact.setPhone("+349876386");
        fourthContact.setLocation("Minsk");
        fourthContact.setVkLink("dima");

        mockedContacts.add(firstContact);
        mockedContacts.add(secondContact);
        mockedContacts.add(thirdContact);
        mockedContacts.add(fourthContact);

        return mockedContacts;
    }
}
