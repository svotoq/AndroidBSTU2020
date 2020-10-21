package psy.fit.bstu.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import psy.fit.bstu.lab5.db.DatabaseContactController;

public class FormActivity extends AppCompatActivity {

    private String mode = "Create";
    private DatabaseContactController contactController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        contactController = new DatabaseContactController(this);
        checkMode();
    }

    private void checkMode() {
        Intent intent = getIntent();
        Contact contact = contactController.readSingleRecord(intent.getIntExtra("id", 0));
        String mode = intent.getStringExtra("mode");
        if (mode != null && mode.equals("Edit")) {
            this.mode = mode;
            showContactInfo(contact);
        }
    }

    private void showContactInfo(Contact contact) {
        TextView emailTextView = findViewById(R.id.emailEditText);
        TextView phoneTextView = findViewById(R.id.phoneEditText);
        TextView locationTextView = findViewById(R.id.locationEditText);
        TextView socialNetworkTextView = findViewById(R.id.socialNetworkEditText);

        emailTextView.setText(contact.getEmail());
        phoneTextView.setText(contact.getPhoneNumber());
        locationTextView.setText(contact.getLocation());
        socialNetworkTextView.setText(contact.getSocialNetwork());
    }

    public void onSaveContact(View view) {
        Contact newContact = getContact();
        if (mode.equals("Create")) {
            saveContact(newContact);
        } else if (mode.equals("Edit")) {
            updateContact(newContact);
        }
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mainIntent);
    }

    private Contact getContact() {
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        EditText locationEditText = findViewById(R.id.locationEditText);
        EditText socialNetworkEditText = findViewById(R.id.socialNetworkEditText);

        return new Contact(0, emailEditText.getText().toString(),
                phoneEditText.getText().toString(),
                locationEditText.getText().toString(),
                socialNetworkEditText.getText().toString());
    }

    private void saveContact(Contact contact) {
        boolean createSuccessful = contactController.create(contact);
        if (createSuccessful) {
            Toast.makeText(this, R.string.contact_created, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.contact_create_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateContact(Contact contact) {
        Intent intent = getIntent();
        contact.setID(intent.getIntExtra("id", 0));
        boolean createSuccessful = contactController.update(contact);
        if (createSuccessful) {
            Toast.makeText(this, R.string.contact_updated, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.contact_update_error, Toast.LENGTH_SHORT).show();
        }
    }

    public void onNavBack(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mainIntent);
    }
}