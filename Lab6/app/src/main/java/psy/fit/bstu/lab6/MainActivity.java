package psy.fit.bstu.lab6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import psy.fit.bstu.lab6.adapter.ContactAdapter;
import psy.fit.bstu.lab6.db.DatabaseContactController;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter adapter;
    private ArrayList<Contact> contacts;
    private ListView contactsList;
    private DatabaseContactController contactController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactController = new DatabaseContactController(this);
        contactsList = findViewById(R.id.contactsListView);
        registerForContextMenu(contactsList);
        loadContacts();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadContacts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() != R.id.deleteItem) {
            return super.onOptionsItemSelected(item);
        }
        ArrayList<Contact> selectedContacts = adapter.getSelectedContacts();
        if (selectedContacts.size() < 1) {
            Toast.makeText(this, R.string.select_to_remove, Toast.LENGTH_SHORT).show();
            return true;
        }
        for (Contact contact : selectedContacts) {
            contactController.delete(contact.getID());
        }
        Toast.makeText(this, getString(R.string.remove_selected, selectedContacts.size()), Toast.LENGTH_SHORT).show();
        loadContacts();
        return true;
    }
    

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Contact contact = contacts.get(info.position);
        switch (item.getItemId()) {
            case R.id.contextMenuDeleteItem: {
                if (contactController.delete(contact.getID())) {
                    Toast.makeText(this, getString(R.string.contact_deleted, contact.getPhoneNumber()), Toast.LENGTH_SHORT).show();
                    loadContacts();
                }
                return true;
            }
            case R.id.contextMenuEditItem: {
                Intent intent = new Intent(this, FormActivity.class);
                intent.putExtra("id", contact.getID());
                intent.putExtra("mode", "Edit");
                startActivity(intent);
                return true;
            }
            case R.id.contextMenuViewItem: {
                Intent intent = new Intent(this, ViewContactActivity.class);
                intent.putExtra("id", contact.getID());
                startActivity(intent);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }

    private void loadContacts() {
        contacts = (ArrayList<Contact>) contactController.read();
        adapter = new ContactAdapter(this, R.layout.contact_list, contacts);
        contactsList.setAdapter(adapter);
    }

    public void onCreateContact(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }
}