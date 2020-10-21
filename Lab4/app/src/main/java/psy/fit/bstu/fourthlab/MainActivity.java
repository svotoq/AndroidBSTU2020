package psy.fit.bstu.fourthlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import psy.fit.bstu.fourthlab.Listeners.OnContactClickListener;
import psy.fit.bstu.fourthlab.adapter.ContactAdapter;
import psy.fit.bstu.fourthlab.db.DatabaseContactController;

public class MainActivity extends AppCompatActivity {

    private ListView contactsList;
    private DatabaseContactController contactController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactController = new DatabaseContactController(this);
        contactsList = findViewById(R.id.contactsListView);
        contactsList.setOnItemClickListener(new OnContactClickListener());
        loadContacts();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("main", "onrestart");

        loadContacts();

    }

    private void loadContacts() {
        ArrayList<Contact> contacts = (ArrayList<Contact>) contactController.read();
        if(contacts.size() < 1) {
            //show empty message
            return;
        }
        ContactAdapter adapter = new ContactAdapter(this, R.layout.contact_list, contacts);
        contactsList.setAdapter(adapter);
    }

    public void onCreateContact(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }
}