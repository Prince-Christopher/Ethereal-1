package com.example.ethereal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Fragments.MoodsFragment;

public class InsightsActivity extends AppCompatActivity {

    MaterialCardView insightsback;
    TextView happycount, relaxedcount, neutralcount, sadcount, angrycount, fearcount, proudcount, sickcount, sillycount;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser fUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insights);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        insightsback = findViewById(R.id.insightsback);
        insightsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InsightsActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference("Users").child(fUser.getUid());

        happycount = findViewById(R.id.happycount);
        databaseReference.child("Moods").child("happycard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                happycount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        relaxedcount = findViewById(R.id.relaxedcount);
        databaseReference.child("Moods").child("relaxedcard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                relaxedcount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        neutralcount = findViewById(R.id.neutralcount);
        databaseReference.child("Moods").child("neutralcard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                neutralcount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sadcount = findViewById(R.id.sadcount);
        databaseReference.child("Moods").child("sadcard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                sadcount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        angrycount = findViewById(R.id.angrycount);
        databaseReference.child("Moods").child("angrycard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                angrycount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fearcount = findViewById(R.id.fearcount);
        databaseReference.child("Moods").child("fearcard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                fearcount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        proudcount = findViewById(R.id.proudcount);
        databaseReference.child("Moods").child("proudcard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                proudcount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sickcount = findViewById(R.id.sickcount);
        databaseReference.child("Moods").child("sickcard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                sickcount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sillycount = findViewById(R.id.sillycount);
        databaseReference.child("Moods").child("sillycard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue(String.class);
                sillycount.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}