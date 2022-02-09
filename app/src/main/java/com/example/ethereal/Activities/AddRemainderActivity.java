package com.example.ethereal.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ethereal.AlarmDatabase.DatabaseClass;
import com.example.ethereal.AlarmDatabase.EntityClass;
import com.example.ethereal.HelperClasses.AlarmBroadcast;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.github.muddz.styleabletoast.StyleableToast;



public class AddRemainderActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public MaterialCardView remainderback;
//    private AppCompatSpinner spinner_goals;
    private EditText edit_custom_goal;
    private ImageView record;
    private TextView tvdate, tvtime;
    private Button setgoal;
    String timeToNotify;
    DatabaseClass databaseClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remainder);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        remainderback = findViewById(R.id.remainderback);
        remainderback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        spinner_goals = findViewById(R.id.spinner_goals);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ethereal_goals_array, R.layout.spinner_color_layout);
//        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//        spinner_goals.setAdapter(adapter);
//        spinner_goals.setOnItemSelectedListener(this);
        record = findViewById(R.id.record);
        edit_custom_goal = findViewById(R.id.edit_custom_goal);
        tvdate = findViewById(R.id.tv_date);
        tvtime = findViewById(R.id.tv_time);
        tvdate.setOnClickListener(this);
        tvtime.setOnClickListener(this);
        edit_custom_goal.setOnClickListener(this);
        record.setOnClickListener(this);
        setgoal = findViewById(R.id.setgoal);
        setgoal.setOnClickListener(this);
        databaseClass = DatabaseClass.getDatabase(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        if (view == record){
            recordSpeech();
        }
        else if (view == tvdate){
            selectDate();
        }
        else if (view == tvtime){
            selectTime();
        }
        else {
            submit();
        }
    }
    private void recordSpeech(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-us");
        try {
            startActivityForResult(intent, 1);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK && data != null){
                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                edit_custom_goal.setText(text.get(0));
            }
        }
    }
    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeToNotify = i + ":" + i1;
                tvtime.setText(FormatTime(i, i1));
            }
        }, hour, minute, false);
        timePickerDialog.show();

    }
    public String FormatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }
    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                tvdate.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void setAlarm(String text, String date, String time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), AlarmBroadcast.class);
        intent.putExtra("event", text);
        intent.putExtra("time", date);
        intent.putExtra("date", time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + timeToNotify;
        DateFormat formatter = new SimpleDateFormat("dd-MM-yy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();

    }
    private void submit() {
        String text = edit_custom_goal.getText().toString().trim();
        if (text.isEmpty()) {
            StyleableToast.makeText(this, "Please Enter or record the text", R.style.customtoast).show();
        } else {
            if (tvtime.getText().toString().isEmpty() || tvdate.getText().toString().isEmpty()) {
                StyleableToast.makeText(this, "Please select date and time", R.style.customtoast).show();
            } else {
                EntityClass entityClass = new EntityClass();
                String value = (edit_custom_goal.getText().toString().trim());
                String date = (tvdate.getText().toString().trim());
                String time = (tvtime.getText().toString().trim());
                entityClass.setEventdate(date);
                entityClass.setEventname(value);
                entityClass.setEventtime(time);
                databaseClass.EventDao().insertAll(entityClass);
                setAlarm(value, date, time);
                finish();
            }
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        StyleableToast.makeText(this, adapterView.getSelectedItem().toString(), R.style.customtoast).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}