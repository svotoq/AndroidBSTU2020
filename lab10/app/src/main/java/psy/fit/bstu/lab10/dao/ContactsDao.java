package psy.fit.bstu.lab10.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import psy.fit.bstu.lab10.model.Contact;

@Dao
public interface ContactsDao {
    @Query("SELECT * FROM Contact")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact getById(long id);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}