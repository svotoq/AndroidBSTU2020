package psy.fit.bstu.lab5.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import psy.fit.bstu.lab5.Contact;
import psy.fit.bstu.lab5.ViewContactActivity;

public class OnContactClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Context context = view.getContext();
        Contact contact = (Contact) adapterView.getItemAtPosition(position);
        adapterView.setSelection(position);
    }

}
