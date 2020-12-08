package psy.fit.bstu.lab9.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import psy.fit.bstu.lab9.Contact;
import psy.fit.bstu.lab9.R;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Contact> contacts;

    @NotNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public void onBindViewHolder(@NotNull ViewAdapter.ViewHolder holder, int position) {
        if(contacts != null) {
            Contact phone = contacts.get(position);
            holder.nameView.setText(phone.getName());
            holder.phoneView.setText(phone.getPhone());
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, phoneView;

        ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.idName);
            phoneView = (TextView) view.findViewById(R.id.idPhone);
        }
    }
}
