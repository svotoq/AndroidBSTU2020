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

    public void onCalculateClicked(View view) {
        EditText ageInput = findViewById(R.id.edit_age);
        EditText weightInput = findViewById(R.id.edit_weight);
        EditText heightInput = findViewById(R.id.edit_height);

        Calculator calculator = new Calculator();
        calculator.age = Integer.parseInt(ageInput.getText().toString());
        calculator.weight = Double.parseDouble(weightInput.getText().toString());
        calculator.height = Double.parseDouble(heightInput.getText().toString());
        calculator.gender = getGender();
        calculator.activity = getActivity();
        double result = calculator.calculate();

        Toast toast = Toast.makeText(this, getString(R.string.result_toast, String.valueOf(result)), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 400);
        toast.show();
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
        RadioGroup activityRadio =findViewById(R.id.activity_radios);
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

    //todo: validation
//    private double Validate(double value) {
//        if (value > 0 && value < 300) {
//            return value;
//        } else {
//        }
//    }
}