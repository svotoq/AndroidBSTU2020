package psy.fit.bstu.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import psy.fit.bstu.lab10.ui.main.AddContactFragment;

public class add_contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AddContactFragment.newInstance())
                    .commitNow();
        }
    }
}