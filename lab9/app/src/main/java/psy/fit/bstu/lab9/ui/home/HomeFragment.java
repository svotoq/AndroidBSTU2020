package psy.fit.bstu.lab9.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import psy.fit.bstu.lab9.Contact;
import psy.fit.bstu.lab9.R;
import psy.fit.bstu.lab9.adapters.ViewAdapter;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    private boolean layoutType = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.idRecycleView);
        final ViewAdapter recViewAdapter = new ViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recViewAdapter);
        recViewAdapter.setContacts(Contact.initializeData());

        ((CompoundButton)root.findViewById(R.id.idSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    LinearLayoutManager lim = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(lim);
                }
                else {
                    GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(glm);
                }
            }
        });
        return root;
    }
}