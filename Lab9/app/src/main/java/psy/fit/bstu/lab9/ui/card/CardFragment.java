package psy.fit.bstu.lab9.ui.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import psy.fit.bstu.lab9.Contact;
import psy.fit.bstu.lab9.R;

public class CardFragment extends Fragment {

    private CardViewModel cardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cardViewModel =
                new ViewModelProvider(this).get(CardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_card, container, false);
        final TextView name = root.findViewById(R.id.idTextName);
        final TextView phone = root.findViewById(R.id.idTextPhone);
        cardViewModel.getContact().observe(getViewLifecycleOwner(), new Observer<Contact>() {
            @Override
            public void onChanged(@Nullable Contact contact) {
                name.setText(contact.getName());
                phone.setText(contact.getPhone());
            }
        });
        return root;
    }
}