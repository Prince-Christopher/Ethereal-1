package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.Navigation;
import android.util.Log;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.ethereal.HelperClasses.ActivitiesHealthAdapter;
import com.example.ethereal.HelperClasses.ActivitiesMediAdapter;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import Model.Health;
import Model.Meditation;

public class ActivitiesActivity extends AppCompatActivity {

//    List<Meditation> medi;
//    List<Health> health;
    RecyclerView medirv, healthrv;
    RecyclerView.Adapter adapter1, adapter2;
    MaterialCardView activitiesback;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ActivitiesActivity.this, HomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("TAG", "onCreateView: okay cool");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        medirv = findViewById(R.id.meditationrv);
        medirv.setHasFixedSize(true);
        LinearLayoutManager medilinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        medilinearLayoutManager.setStackFromEnd(true);
        medilinearLayoutManager.setReverseLayout(true);
        medirv.setLayoutManager(medilinearLayoutManager);
        healthrv = findViewById(R.id.healthcourserv);
        LinearLayoutManager healthlinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        healthlinearLayoutManager.setStackFromEnd(true);
        healthlinearLayoutManager.setReverseLayout(true);
        activitiesback = findViewById(R.id.activitiesback);
        activitiesback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivitiesActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        meditation();
        health();
    }


    private void meditation() {
            Log.e("TAG1", "tf is wrong smh#1");
            ArrayList<Meditation> meditation = new ArrayList<>();
            meditation.add(new Meditation("Mindful Meditation", "10 mins.", R.drawable.mediactivity1));
            meditation.add(new Meditation("Guided Meditation", "30 mins.", R.drawable.mediactivity2));
            meditation.add(new Meditation("Relaxation Meditation", "1 hour.", R.drawable.mediactivity3));

            adapter1 = new ActivitiesMediAdapter(meditation);
            medirv.setLayoutManager(new GridLayoutManager(this, 2));
            medirv.setAdapter(adapter1);
        }

    private void health() {
        Log.e("TAG2", "tf is wrong smh#2");
        ArrayList<Health> health = new ArrayList<>();
        health.add(new Health("For Health \nAnxiety", "5 exercises.", R.drawable.activities_1));
        health.add(new Health("Improve Self Esteem", "7 exercises.", R.drawable.activities_2));
        health.add(new Health("Essential \nWellness Pack", "5 exercises.", R.drawable.activities_3));

        adapter2 = new ActivitiesHealthAdapter(health);
        healthrv.setLayoutManager(new GridLayoutManager(this, 2));
        healthrv.setAdapter(adapter2);
    }


    }
//        healthcourse();
//            medi = new ArrayList<>();
//            medi.add(new Meditation("Mindful Meditation", "10 mins.", R.drawable.mediactivity1));
//            medi.add(new Meditation("Guided Meditation", "30 mins.", R.drawable.mediactivity2));
//            medi.add(new Meditation("Relaxation Meditation", "1 hour.", R.drawable.mediactivity3));
//
//            RecyclerView medirv = findViewById(R.id.meditationrv);
//            ActivitiesMediAdapter myAdapter1 = new ActivitiesMediAdapter(this, medi);
//            medirv.setLayoutManager(new GridLayoutManager(this, 2));
//            medirv.setAdapter(myAdapter1);
//
//
//            health = new ArrayList<>();
//            health.add(new Health("For Health \nAnxiety", "5 exercises.", R.drawable.activities_1));
//            health.add(new Health("Improve Self Esteem", "7 exercises.", R.drawable.activities_2));
//            health.add(new Health("Essential \nWellness Pack", "5 exercises.", R.drawable.activities_3));
//
//            RecyclerView healthrv = findViewById(R.id.healthcourserv);
//            ActivitiesHealthAdapter myAdapter2 = new ActivitiesHealthAdapter(this, health);
//            healthrv.setLayoutManager(new GridLayoutManager(this,2));
//            healthrv.setAdapter(myAdapter2);



//    private void healthcourse() {
//    }

