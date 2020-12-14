package psy.fit.bstu.lab10.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import psy.fit.bstu.lab10.R;
import psy.fit.bstu.lab10.adapter.ViewAdapter;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;

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

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mViewModel.getContacts().observe(getViewLifecycleOwner(), contacts -> {
            final ViewAdapter recViewAdapter = new ViewAdapter();
            recViewAdapter.setContacts(contacts);
            recyclerView.setAdapter(recViewAdapter);
        });

    }

}