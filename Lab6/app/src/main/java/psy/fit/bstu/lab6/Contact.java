package psy.fit.bstu.lab6;

import org.json.JSONException;
import org.json.JSONObject;

public class Contact {
    public String email, phone, location, social;

    public Contact(String email, String phoneNumber, String location, String socialNetwork) {
        this.email = email;
        this.phone = phoneNumber;
        this.location = location;
        this.social = socialNetwork;
    }
}
