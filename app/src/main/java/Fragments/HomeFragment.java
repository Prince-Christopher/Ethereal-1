package Fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ethereal.HelperClasses.ActivitiesAdapter;
import com.example.ethereal.HelperClasses.ActivitiesHelperClass;
import com.example.ethereal.HelperClasses.MeditationAdapter;
import com.example.ethereal.HelperClasses.MeditationHelperClass;
import com.example.ethereal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.User;

public class HomeFragment extends Fragment {

    private TextView heyusername;
    private FirebaseUser fUser;
    private String profileId;

    RecyclerView medichant_recycler;
    RecyclerView.Adapter mediadapter;

    RecyclerView activities_recycler;
    RecyclerView.Adapter actiadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        fUser = FirebaseAuth.getInstance().getCurrentUser();
        profileId = fUser.getUid();
        heyusername = view.findViewById(R.id.heyusername);
        medichant_recycler = view.findViewById(R.id.medichant_recycler);
        medichant_recycler.setHasFixedSize(true);
        LinearLayoutManager medilinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        medilinearLayoutManager.setStackFromEnd(true);
        medilinearLayoutManager.setReverseLayout(true);
        medichant_recycler.setLayoutManager(medilinearLayoutManager);

        activities_recycler = view.findViewById(R.id.activities_recycler);
        activities_recycler.setHasFixedSize(true);
        LinearLayoutManager actilinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        actilinearLayoutManager.setStackFromEnd(true);
        actilinearLayoutManager.setReverseLayout(true);
        activities_recycler.setLayoutManager(actilinearLayoutManager);

        medichant_recycler();
        activities_recycler();
        helloUser();
        return view;
    }

    private void activities_recycler() {
        ArrayList<ActivitiesHelperClass> activities = new ArrayList<>();
        activities.add(new ActivitiesHelperClass(R.drawable.activities_3, "Essential Wellness Pack", "5 exercises."));
        activities.add(new ActivitiesHelperClass(R.drawable.activities_2, "Improve Self Esteem", "7 exercises."));
        activities.add(new ActivitiesHelperClass(R.drawable.activities_1, "For Health Anxiety", "5 exercises."));

        actiadapter = new ActivitiesAdapter(activities);
        activities_recycler.setAdapter(actiadapter);
    }

    private void medichant_recycler() {

        ArrayList<MeditationHelperClass> meditationChants = new ArrayList<>();
        meditationChants.add(new MeditationHelperClass(R.drawable.meditation_3, "Relaxation Meditation", "1 hour."));
        meditationChants.add(new MeditationHelperClass(R.drawable.meditation_2, "Guided Meditation", "30 mins."));
        meditationChants.add(new MeditationHelperClass(R.drawable.meditation_1, "Mindful Meditation", "10 mins."));

        mediadapter = new MeditationAdapter(meditationChants);
        medichant_recycler.setAdapter(mediadapter);


    }


    private void helloUser() {

            FirebaseDatabase.getInstance().getReference().child("Users").child(profileId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);

                    heyusername.setText("Hey, " +user.getName()+"!");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
