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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization
        noteField = findViewById(R.id.noteField);
        CalendarView calendarView = findViewById(R.id.calendarView);
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
        } else {
            writeToJson();
        }
        Toast.makeText(getApplicationContext(), R.string.note_deleted, Toast.LENGTH_SHORT).show();
    }

    public void onExternalMode(View view) {
        if (externalMode) {
            readFromJson();
        } else {
            readFromExternalJson();
        }
        externalMode = !externalMode;
    }

    public void writeToJson() throws IOException {
        FileOutputStream outputStream;
        outputStream = openFileOutput(NOTES_FILE_NAME, MODE_PRIVATE);
        outputStream.write(new Gson().toJson(notes).getBytes());
        outputStream.close();
    }

    private void writeToExternalJson() throws IOException {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/" + NOTES_FILE_NAME);
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(myDir);
        outputStream.write(new Gson().toJson(notes).getBytes());
        outputStream.close();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromExternalJson() {
        try {
           String path = getExternalFilesDir(Environment.DIRECTORY_DCIM + "/" + NOTES_FILE_NAME).getAbsolutePath();
            FileInputStream inputStream = openFileInput(path);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}