package psy.fit.bstu.lab10.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String phone;
    private String name;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public long getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(long id) {
        this.id = id;
    }


}
