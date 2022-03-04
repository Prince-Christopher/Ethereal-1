package com.example.ethereal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.ethereal.HelperClasses.TherapistRetrieverAdapter;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Therapist;

public class TherapistsActivity extends AppCompatActivity {

    MaterialCardView therapistback;
    RecyclerView therapistsrv;
    DatabaseReference databaseReference;
    TherapistRetrieverAdapter therapistRetrieverAdapter;
    ArrayList<Therapist> list;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TherapistsActivity.this, HomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapists);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.pale_pink));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        therapistsrv = findViewById(R.id.therapistsrv);
        databaseReference= FirebaseDatabase.getInstance().getReference("Therapists");
        therapistsrv.setLayoutManager(new LinearLayoutManager(this));
        therapistsrv.setHasFixedSize(true);

        therapistback = findViewById(R.id.therapistback);
        therapistback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TherapistsActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        list = new ArrayList<>();
        therapistRetrieverAdapter = new TherapistRetrieverAdapter(this, list);
        therapistsrv.setAdapter(therapistRetrieverAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Therapist therapist = dataSnapshot.getValue(Therapist.class);
                    list.add(therapist);
                }
                therapistRetrieverAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}