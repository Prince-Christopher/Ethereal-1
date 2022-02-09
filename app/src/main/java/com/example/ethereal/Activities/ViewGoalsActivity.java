package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.ethereal.AlarmDatabase.DatabaseClass;
import com.example.ethereal.AlarmDatabase.EntityClass;
import com.example.ethereal.HelperClasses.AlarmEventAdapter;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ViewGoalsActivity extends AppCompatActivity {

    RecyclerView viewgoalsrv;
    AlarmEventAdapter alarmEventAdapter;
    MaterialCardView allgoalsback;
    DatabaseClass dbclass;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goals);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        allgoalsback = findViewById(R.id.allgoalsback);
        allgoalsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewgoalsrv = findViewById(R.id.viewgoalsrv);
        dbclass = DatabaseClass.getDatabase(getApplicationContext());

    }

    private void setAlarmEventAdapter() {
        List<EntityClass> classList = dbclass.EventDao().getAllData();
        alarmEventAdapter = new AlarmEventAdapter(this, classList);
        viewgoalsrv.setAdapter(alarmEventAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setAlarmEventAdapter();

    }
}