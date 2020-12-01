package psy.fit.bstu.lab8.data;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import psy.fit.bstu.lab8.models.Contact;

import static android.content.Context.MODE_PRIVATE;

public class FileHandler {
    private final String fileName = "contacts.json";
    private final Activity mainActivity;

    public FileHandler(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void saveToStorage(ArrayList<Contact> contacts) {
        Gson gson = new Gson();
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = this.mainActivity.openFileOutput(this.fileName, MODE_PRIVATE);
            fileOutputStream.write(gson.toJson(contacts).getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<Contact> readFromStorage() {
        Gson gson = new Gson();
        FileInputStream fileInputStream;
        InputStreamReader streamReader;
        try {
            fileInputStream = this.mainActivity.openFileInput(this.fileName);
            streamReader = new InputStreamReader(fileInputStream);

            return gson.fromJson(streamReader, new TypeToken<List<Contact>>() {}.getType());
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}