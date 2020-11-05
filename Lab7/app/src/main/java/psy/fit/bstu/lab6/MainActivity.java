package psy.fit.bstu.lab6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import psy.fit.bstu.lab6.adapter.ContactAdapter;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter adapter;
    private ArrayList<Contact> contacts;
    private ArrayList<String> listKeys;
    private ListView contactsList;
    private DatabaseReference contactsRef;

    private FirebaseAuth mAuth;
    private static final int CREATE_CONTACT = 1;
    private static final int EDIT_CONTACT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialization
        contactsList = findViewById(R.id.contactsListView);
        mAuth = FirebaseAuth.getInstance();
        contacts = new ArrayList<Contact>();
        listKeys = new ArrayList<>();

        contactsRef = FirebaseDatabase.getInstance().getReference(mAuth.getCurrentUser()
                .getUid()).child("contacts");

        adapter = new ContactAdapter(this, R.layout.contact_list, contacts);
        contactsList.setAdapter(adapter);


        addChildEventListener();
        registerForContextMenu(contactsList);

    }

    private void addChildEventListener() {
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Contact contact = new Contact(snapshot.child("email").getValue().toString(),
                        snapshot.child("phone").getValue().toString(),
                        snapshot.child("location").getValue().toString(),
                        snapshot.child("social").getValue().toString());
                adapter.add(contact);
                listKeys.add(snapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != -1) {
                    contacts.remove(index);
                    listKeys.remove(index);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        contactsRef.addChildEventListener(childEventListener);
    }

    public void addContact(String email, String phone, String location, String social) {
        String key = contactsRef.push().getKey();
        Contact contact = new Contact(email, phone, location, social);
        contactsRef.child(key).setValue(contact);

        adapter.notifyDataSetChanged();
    }

    public void updateContact(String email, String phone, String location, String social, int pos) {
        String key = listKeys.get(pos);
        Contact contact = new Contact(email, phone, location, social);
        contactsRef.child(key).setValue(contact);
        contacts.set(pos, contact);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, AuthActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else {
            displayUserInf(currentUser);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteItem: {
                ArrayList<Integer> selectedPositions = adapter.getSelectedContactsPos();
                if (selectedPositions.size() < 1) {
                    Toast.makeText(this, R.string.select_to_remove, Toast.LENGTH_SHORT).show();
                    return true;
                }
                for (int pos : selectedPositions) {
                    contactsRef.child(listKeys.get(pos)).removeValue();
                }
                adapter.clearSelectedContactsPos();
                Toast.makeText(this, getString(R.string.remove_selected, selectedPositions.size()), Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.logoutItem: {
                mAuth.signOut();
                Intent intent = new Intent(this, AuthActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
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
        switch (item.getItemId()) {
            case R.id.contextMenuDeleteItem: {
                String key = listKeys.get(info.position);
                contactsRef.child(key).removeValue();
                return true;
            }
            case R.id.contextMenuEditItem: {
                Intent intent = new Intent(this, FormActivity.class);
                intent.putExtra("mode", "Edit");
                intent.putExtra("pos", info.position);
                Contact contact = contacts.get(info.position);
                intent.putExtra("email", contact.email);
                intent.putExtra("phone", contact.phone);
                intent.putExtra("location", contact.location);
                intent.putExtra("social", contact.social);
                startActivityForResult(intent, EDIT_CONTACT);
                return true;
            }
            case R.id.contextMenuViewItem: {
                Intent intent = new Intent(this, ViewContactActivity.class);
                Contact contact = contacts.get(info.position);
                intent.putExtra("email", contact.email);
                intent.putExtra("phone", contact.phone);
                intent.putExtra("location", contact.location);
                intent.putExtra("social", contact.social);
                startActivity(intent);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }


    public void onCreateContact(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        startActivityForResult(intent, CREATE_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CREATE_CONTACT) {
            if (resultCode == RESULT_OK) {
                addContact(data.getStringExtra("email"),
                        data.getStringExtra("phone"),
                        data.getStringExtra("location"),
                        data.getStringExtra("social"));
                Toast.makeText(this, R.string.contact_created, Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == EDIT_CONTACT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, R.string.contact_updated, Toast.LENGTH_SHORT).show();
                updateContact(data.getStringExtra("email"),
                        data.getStringExtra("phone"),
                        data.getStringExtra("location"),
                        data.getStringExtra("social"),
                        data.getIntExtra("pos", 0));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void displayUserInf(FirebaseUser user) {
        getSupportActionBar().setTitle(user.getEmail());
    }
}