package psy.fit.bstu.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import psy.fit.bstu.lab5.db.DatabaseContactController;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    public void onSaveContact(View view) {
        Log.d("form", "onCreateContact");
        Contact newContact = getContact();
        saveContact(newContact);
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
        boolean createSuccessful = new DatabaseContactController(this).create(contact);
        if (createSuccessful) {
            Toast.makeText(this, R.string.contact_created, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.contact_create_error, Toast.LENGTH_SHORT).show();
        }
    }

    public void onNavBack(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mainIntent);
    }
}