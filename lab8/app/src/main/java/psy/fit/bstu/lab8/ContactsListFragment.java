package psy.fit.bstu.lab8;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import psy.fit.bstu.lab8.models.Contact;

public class ContactsListFragment extends Fragment {
    public ContactsListFragment() {
        // Required empty public constructor
    }
    public interface OnItemSelected {
        void getSelectedContact(Contact contact);
    }

    private ListView contactsListView;
    private OnItemSelected onItemSelectedCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        this.registerListEvents(view);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelected) {
            onItemSelectedCallback = (OnItemSelected)context;
        }
    }

    private void registerListEvents(View view) {
        this.contactsListView = view.findViewById(R.id.contactsListView);

        if (getArguments() != null) {
            ArrayList<Contact> contacts = (ArrayList<Contact>) getArguments().getSerializable("contactList");

            ArrayAdapter<Contact> contactArrayAdapter = new ArrayAdapter<>(
                    view.getContext(),
                    android.R.layout.simple_list_item_1,
                    contacts
            );
            this.contactsListView.setAdapter(contactArrayAdapter);

            this.contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Contact contact = (Contact) ContactsListFragment.this.contactsListView.getItemAtPosition(i);
                    onItemSelectedCallback.getSelectedContact(contact);
                }
            });
        }
    }
}