package psy.fit.bstu.lab10.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import psy.fit.bstu.lab10.R;
import psy.fit.bstu.lab10.adapter.ViewAdapter;
import psy.fit.bstu.lab10.model.Contact;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = (RecyclerView) fragment.findViewById(R.id.idRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        viewAdapter = new ViewAdapter();
        recyclerView.setAdapter(viewAdapter);

        ((FloatingActionButton) fragment.findViewById(R.id.idAddContact)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, AddContactFragment.newInstance())
                        .commitNow();
            }
        });

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getContacts().observe(getViewLifecycleOwner(), contacts -> {
            viewAdapter.setContacts(contacts);
        });

        registerForContextMenu(recyclerView);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = viewAdapter.getPosition();
        Contact contact = viewAdapter.getContact(position);
        switch (item.getItemId()) {
            case R.id.update: {
                //make bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", contact);
                //create fragment
                AddContactFragment addContactFragment = AddContactFragment.newInstance();
                addContactFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, addContactFragment)
                        .commitNow();
                return true;
            }
            case R.id.delete: {
                mViewModel.delete(contact);
                return true;
            }
            default: {
                return super.onContextItemSelected(item);
            }
        }
    }


}