package psy.fit.bstu.lab8.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Serializable  {
    private int id;
    private String name;
    private String phone;
    private String location;
    private String vkLink;

    @NonNull
    @Override
    public String toString() {
        return this.name + " " + this.phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setVkLink(String vkLink) {
        this.vkLink = vkLink;
    }

    public String getVkLink() {
        return vkLink;
    }
}
