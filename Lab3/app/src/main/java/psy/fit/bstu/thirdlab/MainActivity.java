package psy.fit.bstu.thirdlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.telephony.IccOpenLogicalChannelResponse;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public final static String NOTES_FILE_NAME = "notes.json";
    public Map<String, String> notes = new HashMap<>();
    private EditText noteField;
    private String selectedDate;
    private boolean externalMode = false;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization
        noteField = findViewById(R.id.noteField);
        calendarView = findViewById(R.id.calendarView);
        //Listeners
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month++;//The month starts at 0.
                selectedDate = year + "." + month + "." + day;
                noteField.setText(notes.get(selectedDate));
            }
        });
        if (!externalMode) {
            readFromJson();
        } else {
            readFromExternalJson();
        }
    }

    public void onSave(View view) throws IOException {
        String note = noteField.getText().toString();
        if (note.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.empty_note_field, Toast.LENGTH_SHORT).show();
            return;
        }

        notes.put(selectedDate, note);

        if (externalMode) {
            writeToExternalJson();
        } else {
            writeToJson();
        }
        Toast.makeText(getApplicationContext(), R.string.note_added, Toast.LENGTH_SHORT).show();
    }

    public void onDelete(View view) throws IOException {
        String note = noteField.getText().toString();
        if (note.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.note_is_not_exists, Toast.LENGTH_SHORT).show();
            return;
        }

        notes.remove(selectedDate);
        noteField.setText("");
        if (externalMode) {
            writeToExternalJson();
            readFromExternalJson();
        } else {
            writeToJson();
            readFromJson();
        }
        Toast.makeText(getApplicationContext(), R.string.note_deleted, Toast.LENGTH_SHORT).show();
    }

    public void onExternalMode(View view) {
        externalMode = !externalMode;
        if (externalMode) {
            readFromExternalJson();
        } else {
            readFromJson();
        }
    }

    public void writeToJson() throws IOException {
        FileOutputStream outputStream;
        outputStream = openFileOutput(NOTES_FILE_NAME, MODE_PRIVATE);
        outputStream.write(new Gson().toJson(notes).getBytes());
        outputStream.close();

    }

    private void writeToExternalJson() throws IOException {
        if (isExternalStorageAvailable()) {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File myFile = new File(folder, NOTES_FILE_NAME);
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(myFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            outputStream.write(new Gson().toJson(notes).getBytes());
            outputStream.close();
        } else {
            Toast.makeText(this, "storage unvaliable WRITE", Toast.LENGTH_SHORT).show();

        }
    }

    public void readFromJson() {
        try {
            FileInputStream inputStream = openFileInput(NOTES_FILE_NAME);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader buffReader = new BufferedReader(inputReader);
            StringBuilder strBuffer = new StringBuilder();
            String lines;
            while ((lines = buffReader.readLine()) != null) {
                strBuffer.append(lines).append("\n");
            }
            Type dataType = new TypeToken<Map<String, String>>() {
            }.getType();
            notes = new Gson().fromJson(strBuffer.toString(), dataType);
            updateSelectedDate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromExternalJson() {
        if (isExternalStorageAvailable()) {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File myFile = new File(folder, NOTES_FILE_NAME);
            if (!myFile.exists()) {
                notes.clear();
            } else {
                getData(myFile);

            }

        } else {
            Toast.makeText(this, "storage unvaliable", Toast.LENGTH_SHORT).show();
        }
    }

    boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals((state));
    }

    private void getData(File myFile) {
        try {
            FileInputStream inputStream = new FileInputStream(myFile);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader buffReader = new BufferedReader(inputReader);
            StringBuilder strBuffer = new StringBuilder();
            String lines;
            while ((lines = buffReader.readLine()) != null) {
                strBuffer.append(lines).append("\n");
            }
            Type dataType = new TypeToken<Map<String, String>>() {
            }.getType();
            notes = new Gson().fromJson(strBuffer.toString(), dataType);
            updateSelectedDate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateSelectedDate() {
        if (notes.containsKey(selectedDate)) {
            noteField.setText(notes.get(selectedDate));
        } else {
            noteField.setText("");
        }
    }
}