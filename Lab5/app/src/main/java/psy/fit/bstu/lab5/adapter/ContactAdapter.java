package psy.fit.bstu.lab5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import psy.fit.bstu.lab5.Contact;
import psy.fit.bstu.lab5.R;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Contact> contactsList;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> contacts) {
        super(context, resource, contacts);
        this.contactsList = contacts;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

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

        return convertView;
    }

    public Contact getItem(int position) {
        return contactsList.get(position);
    }

    private String formatValue(int count, String unit) {
        return String.valueOf(count) + " " + unit;
    }

    private class ViewHolder {
        final TextView phoneItem, emailItem, locationItem;

        ViewHolder(View view) {
            phoneItem = (TextView) view.findViewById(R.id.phoneItem);
            emailItem = (TextView) view.findViewById(R.id.emailItem);
            locationItem = (TextView) view.findViewById(R.id.locationItem);
        }
    }
}
