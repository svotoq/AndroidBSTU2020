package psy.fit.bstu.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private String mode = "Create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        checkMode();
    }

    private void checkMode() {
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        if (mode != null && mode.equals("Edit")) {
            this.mode = mode;
            showContactInfo(intent);
        }
    }

    private void showContactInfo(Intent intent) {
        TextView emailTextView = findViewById(R.id.emailEditText);
        TextView phoneTextView = findViewById(R.id.phoneEditText);
        TextView locationTextView = findViewById(R.id.locationEditText);
        TextView socialNetworkTextView = findViewById(R.id.socialNetworkEditText);

        emailTextView.setText(intent.getStringExtra("email"));
        phoneTextView.setText(intent.getStringExtra("phone"));
        locationTextView.setText(intent.getStringExtra("location"));
        socialNetworkTextView.setText(intent.getStringExtra("social"));
    }

    public void onSaveContact(View view) {
        Contact contact = getContact();
        Intent data = new Intent();
        data.putExtra("email", contact.email);
        data.putExtra("phone", contact.phone);
        data.putExtra("location", contact.location);
        data.putExtra("social", contact.social);
        if (mode.equals("Edit")) {
            data.putExtra("pos", getIntent().getIntExtra("pos", 0));
        }
        setResult(RESULT_OK, data);
        finish();
    }

    private Contact getContact() {
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        EditText locationEditText = findViewById(R.id.locationEditText);
        EditText socialNetworkEditText = findViewById(R.id.socialNetworkEditText);

        return new Contact(emailEditText.getText().toString(),
                phoneEditText.getText().toString(),
                locationEditText.getText().toString(),
                socialNetworkEditText.getText().toString());
    }

    public void onNavBack(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}