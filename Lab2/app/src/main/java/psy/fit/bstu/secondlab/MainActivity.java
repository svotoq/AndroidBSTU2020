package psy.fit.bstu.secondlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int getInteger(int id) {
        return getResources().getInteger(id);
    }

    public void onCalculateClicked(View view) {
        EditText ageInput = findViewById(R.id.edit_age);
        EditText weightInput = findViewById(R.id.edit_weight);
        EditText heightInput = findViewById(R.id.edit_height);
        if (!validateEditText(ageInput, weightInput, heightInput)) {
            return;
        }
        Calculator calculator = new Calculator();
        calculator.age = Integer.parseInt(ageInput.getText().toString());
        calculator.weight = Double.parseDouble(weightInput.getText().toString());
        calculator.height = Double.parseDouble(heightInput.getText().toString());
        calculator.gender = getGender();
        calculator.activity = getActivity();
        double result = calculator.calculate();

        String resultMessage = getString(R.string.result_message, String.valueOf(result));
        showInformationToast(resultMessage);
    }

    private Gender getGender() {
        RadioGroup genderRadio = findViewById(R.id.gender_radios);
        switch (genderRadio.getCheckedRadioButtonId()) {
            case R.id.gender_male: {
                return Gender.Male;
            }
            case R.id.gender_female: {
                return Gender.Female;
            }
        }
        return Gender.Male;
    }

    private PhysActivity getActivity() {
        RadioGroup activityRadio = findViewById(R.id.activity_radios);
        switch (activityRadio.getCheckedRadioButtonId()) {
            case R.id.activity_none: {
                return PhysActivity.None;
            }
            case R.id.activity_regular: {
                return PhysActivity.Regular;
            }
            case R.id.activity_intensive: {
                return PhysActivity.Intensive;
            }
        }
        return PhysActivity.None;
    }

    private void showInformationToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 400);
        toast.show();
    }

    private boolean validateEditText(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (!validation(editText)) {
                showValidationErrorToast(editText);
                return false;
            }
        }
        return true;
    }

    private boolean validation(EditText editText) {
        String editTextValue = editText.getText().toString();
        if (editTextValue.equals("")) {
            return false;
        }
        switch (editText.getId()) {
            case R.id.edit_age: {
                int age = Integer.parseInt(editTextValue);

                if (age < getInteger(R.integer.age_min) || age > getInteger(R.integer.age_max)) {
                    return false;
                }
                break;
            }
            case R.id.edit_height: {
                int height = Integer.parseInt(editTextValue);
                if (height < getInteger(R.integer.height_min) || height > getInteger(R.integer.height_max)) {
                    return false;
                }
            }
            case R.id.edit_weight: {
                double weight = Double.parseDouble(editTextValue);
                if (weight < getInteger(R.integer.weight_min) || weight > getInteger(R.integer.weight_max)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showValidationErrorToast(EditText editText) {
        String errorMessage = "";
        switch (editText.getId()) {
            case R.id.edit_age: {
                errorMessage = getString(R.string.age_validation_error, getInteger(R.integer.age_min), getInteger(R.integer.age_max));
                break;
            }
            case R.id.edit_height: {
                errorMessage = getString(R.string.height_validation_error, getInteger(R.integer.height_min), getInteger(R.integer.height_max));
                break;
            }
            case R.id.edit_weight: {
                errorMessage = getString(R.string.weight_validation_error, getInteger(R.integer.weight_min), getInteger(R.integer.weight_max));
                break;
            }
        }
        showInformationToast(errorMessage);
    }
}