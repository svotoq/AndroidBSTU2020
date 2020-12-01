package psy.fit.bstu.lab8;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import psy.fit.bstu.lab8.data.DefaultData;
import psy.fit.bstu.lab8.models.Contact;

public class MainActivity extends FragmentActivity implements ContactsListFragment.OnItemSelected {
    private ArrayList<Contact> contactList;
    private Contact selectedContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = DefaultData.getDefaultContacts();

        ContactsListFragment contactListFragment = new ContactsListFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("contactList", this.contactList);
        contactListFragment.setArguments(bundle);

        this.getSupportFragmentManager().beginTransaction()
                .add(isLandscapeMode() ? R.id.contactListFragment : R.id.mainLayout, contactListFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            fragmentManager
                    .beginTransaction()
                    .commit();
        } else {
            Toast.makeText(this, "No previous fragment states found", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }

    @Override
    public void getSelectedContact(Contact contact) {
        this.selectedContact = contact;
        this.renderFragment();
    }

    private void renderFragment() {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager()
                .beginTransaction();

        if (this.isLandscapeMode()) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("contactInfo", this.selectedContact);

            ContactInfoFragment contactInfo = new ContactInfoFragment();
            contactInfo.setArguments(bundle);

            contactInfo.setArguments(bundle);
            fragmentTransaction
                    .replace(R.id.contactInfoFragment, contactInfo)
                    .addToBackStack(null)
                    .commit();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("contactInfo", this.selectedContact);

        ContactInfoFragment contactInfoFragment = new ContactInfoFragment();
        contactInfoFragment.setArguments(bundle);

        fragmentTransaction.replace(
                isLandscapeMode() ? R.id.contactInfoFragment : R.id.mainLayout,
                contactInfoFragment
        );

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private boolean isLandscapeMode() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}