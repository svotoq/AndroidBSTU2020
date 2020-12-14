package psy.fit.bstu.lab9;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private String phone;
    private String name;
    public Contact(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public static ArrayList<Contact> initializeData() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("+375298639126", "Stanislav"));
        contacts.add(new Contact("+375324324324", "Alex"));
        contacts.add(new Contact("+375298633242", "Yahor"));
        return contacts;
    }
}
