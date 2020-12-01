package psy.fit.bstu.lab8;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import psy.fit.bstu.lab8.models.Contact;


public class ContactInfoFragment extends Fragment {
    private TextView contactName;
    private TextView contactPhone;
    private TextView contactLocation;
    private TextView contactVkLink;

    private Contact contact;

    public ContactInfoFragment() { }

    public ContactInfoFragment setContacts(Contact contact) {
        this.contact = contact;
        this.renderSelectedContactInfo();

        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_info, container, false);

        this.initializeElements(view);

        if (getArguments() != null) {
            this.contact = (Contact)getArguments().getSerializable("contactInfo");
        }

        if (this.contact != null) {
            this.renderSelectedContactInfo();
        }

        return view;
    }

    private void initializeElements(View view) {
        this.contactName = view.findViewById(R.id.contactName);
        this.contactPhone = view.findViewById(R.id.contactPhone);
        this.contactLocation = view.findViewById(R.id.contactLocation);
        this.contactVkLink = view.findViewById(R.id.contactLink);
    }

    private void renderSelectedContactInfo() {
        this.contactName.setText(this.contact.getName());
        this.contactPhone.setText(this.contact.getPhone());
        this.contactLocation.setText(this.contact.getLocation());
        this.contactVkLink.setText(this.contact.getVkLink());
    }
}