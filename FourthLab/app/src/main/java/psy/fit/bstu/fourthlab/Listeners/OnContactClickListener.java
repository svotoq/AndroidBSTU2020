package psy.fit.bstu.fourthlab.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import psy.fit.bstu.fourthlab.Contact;
import psy.fit.bstu.fourthlab.ViewContactActivity;
import psy.fit.bstu.fourthlab.db.DatabaseContactController;

public class OnContactClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Context context = view.getContext();
        Contact contact = (Contact)adapterView.getItemAtPosition(position);
        Intent intent = new Intent(context, ViewContactActivity.class);
        intent.putExtra("id", contact.getID());
        context.startActivity(intent);
    }
}
