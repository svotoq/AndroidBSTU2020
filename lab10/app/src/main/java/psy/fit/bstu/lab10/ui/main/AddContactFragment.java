package psy.fit.bstu.lab10.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import psy.fit.bstu.lab10.R;
import psy.fit.bstu.lab10.model.Contact;

public class AddContactFragment extends Fragment {

    private MainViewModel mViewModel;
    private EditText nameEditText;
    private EditText phoneEditText;
    private Contact contact;

    public static AddContactFragment newInstance() {
        return new AddContactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.add_fragment, container, false);
        nameEditText = fragment.findViewById(R.id.idName);
        phoneEditText = fragment.findViewById(R.id.idPhone);

        ((Button) fragment.findViewById(R.id.idSaveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });
        if (getArguments() != null) {
            this.contact = (Contact) getArguments().getSerializable("contact");
            nameEditText.setText(this.contact.getName());
            phoneEditText.setText(this.contact.getPhone());
        }
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    }

    private void saveContact() {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(getContext(), getText(R.string.required_fields), Toast.LENGTH_SHORT).show();
            return;
        }
        if (this.contact != null) {
            this.contact.setName(name);
            this.contact.setPhone(phone);
            mViewModel.update(this.contact);
        } else {
            mViewModel.insert(new Contact(name, phone));
        }
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow();
    }
}