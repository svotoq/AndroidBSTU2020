package psy.fit.bstu.lab10.adapter;


import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import psy.fit.bstu.lab10.R;
import psy.fit.bstu.lab10.model.Contact;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> implements View.OnCreateContextMenuListener{
    private LayoutInflater inflater;
    private List<Contact> contacts = new ArrayList<>();
    private int position;

    @NotNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        view.setOnCreateContextMenuListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewAdapter.ViewHolder holder, int position) {
        if (contacts != null) {
            Contact phone = contacts.get(position);
            holder.nameView.setText(phone.getName());
            holder.phoneView.setText(phone.getPhone());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    setPosition(position);
                    return false;
                }
            });
        }
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public Contact getContact(int position) {
        return contacts.get(position);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, R.id.update,
                Menu.NONE, R.string.update);
        menu.add(Menu.NONE, R.id.delete,
                Menu.NONE, R.string.delete);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
