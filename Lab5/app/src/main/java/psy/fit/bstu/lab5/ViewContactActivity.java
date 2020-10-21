package psy.fit.bstu.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import psy.fit.bstu.lab5.db.DatabaseContactController;

public class ViewContactActivity extends AppCompatActivity {

    private DatabaseContactController contactController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        contactController = new DatabaseContactController(this);
        loadContact();
    }

    private void loadContact() {
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView socialNetworkTextView = findViewById(R.id.socialNetworkTextView);

        Intent intent = getIntent();
        Contact contact = contactController.readSingleRecord(intent.getIntExtra("id", 0));

        emailTextView.setText(contact.getEmail());
        phoneTextView.setText(contact.getPhoneNumber());
        locationTextView.setText(contact.getLocation());
        socialNetworkTextView.setText(contact.getSocialNetwork());
    }

    public void onShowLocation(View view) {
        Intent locationIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=" + ((TextView) view).getText().toString()));
        startActivity(locationIntent);
    }

    public void onOpenSocialNetwork(View view) {
        Intent socialNetworkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(((TextView) view).getText().toString()));
        startActivity(socialNetworkIntent);
    }

    public void onOpenPhone(View view) {
        Intent phoneIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + ((TextView) view).getText().toString()));
        startActivity(phoneIntent);
    }

    public void onOpenEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + ((TextView) view).getText().toString()));
        startActivity(emailIntent);
    }

    public void onNavBack(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mainIntent);
    }
}