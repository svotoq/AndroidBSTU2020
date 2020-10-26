package psy.fit.bstu.lab6;

import org.json.JSONException;
import org.json.JSONObject;

public class Contact {
    public static String FILE_NAME = "contacts.json";
    private String email, phoneNumber, location, socialNetwork;
    private int ID;

    public Contact(int ID, String email, String phoneNumber, String location, String socialNetwork) {
        this.ID = ID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.socialNetwork = socialNetwork;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public JSONObject getJSONContact() {
        JSONObject jsonContact = new JSONObject();
        try {
            jsonContact.put("Email", this.email);
            jsonContact.put("Phone", this.phoneNumber);
            jsonContact.put("Location", this.location);
            jsonContact.put("SocialNetwork", this.socialNetwork);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonContact;
    }

}
