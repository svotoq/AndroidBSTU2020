package psy.fit.bstu.lab5.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import psy.fit.bstu.lab5.Contact;
import psy.fit.bstu.lab5.R;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Contact> contactsList;
    private boolean[] checkBoxState;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> contacts) {
        super(context, resource, contacts);
        this.contactsList = contacts;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        checkBoxState = new boolean[contacts.size()];
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Contact contact = contactsList.get(position);

        viewHolder.phoneItem.setText(contact.getPhoneNumber());
        viewHolder.emailItem.setText(contact.getEmail());
        viewHolder.locationItem.setText(contact.getLocation());
        viewHolder.selectItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("main", String.valueOf(((CheckBox) view).isChecked()));
                checkBoxState[position] = ((CheckBox) view).isChecked();
            }
        });

        return convertView;
    }

    public Contact getItem(int position) {
        return contactsList.get(position);
    }

    private String formatValue(int count, String unit) {
        return count + " " + unit;
    }

    public ArrayList<Contact> getSelectedContacts() {
        ArrayList<Contact> selectedContacts = new ArrayList<Contact>();
        for (int i = 0; i < checkBoxState.length; i++) {
            if (checkBoxState[i]) {
                selectedContacts.add(contactsList.get(i));
            }
        }
        return selectedContacts;
    }

    private class ViewHolder {
        final TextView phoneItem, emailItem, locationItem;
        final CheckBox selectItem;

        ViewHolder(View view) {
            phoneItem = view.findViewById(R.id.phoneItem);
            emailItem = view.findViewById(R.id.emailItem);
            locationItem = view.findViewById(R.id.locationItem);
            selectItem = view.findViewById(R.id.selectItemCheckBox);
        }
    }
}
