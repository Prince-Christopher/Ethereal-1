package Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ethereal.HelperClasses.ActivitiesHealthAdapter;
import com.example.ethereal.HelperClasses.ActivitiesMediAdapter;
import com.example.ethereal.HelperClasses.MeditationAdapter;
import com.example.ethereal.R;

import java.util.ArrayList;
import java.util.List;

import Model.Health;
import Model.Meditation;

public class ActivitiesFragment extends Fragment {

    List<Meditation> medi;
    List<Health> health;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        medi = new ArrayList<>();
        medi.add(new Meditation("Mindful Meditation", "10 mins.", R.drawable.mediactivity1));
        medi.add(new Meditation("Guided Meditation", "30 mins.", R.drawable.mediactivity2));
        medi.add(new Meditation("Relaxation Meditation", "1 hour.", R.drawable.mediactivity3));

        RecyclerView medirv = view.findViewById(R.id.meditationrv);
        ActivitiesMediAdapter myAdapter1 = new ActivitiesMediAdapter(getContext(), medi);
        medirv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        medirv.setAdapter(myAdapter1);

        health = new ArrayList<>();
        health.add(new Health("For Health \nAnxiety", "5 exercises.", R.drawable.activities_1));
        health.add(new Health("Improve Self Esteem", "7 exercises.", R.drawable.activities_2));
        health.add(new Health("Essential \nWellness Pack", "5 exercises.", R.drawable.activities_3));

        RecyclerView healthrv = view.findViewById(R.id.healthcourserv);
        ActivitiesHealthAdapter myAdapter2 = new ActivitiesHealthAdapter(getContext(), health);
        healthrv.setLayoutManager(new GridLayoutManager(getContext(),2));
        healthrv.setAdapter(myAdapter2);
        return view;




    }
}