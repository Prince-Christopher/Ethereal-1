package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ethereal.Activities.AddRemainderActivity;
import com.example.ethereal.Activities.ViewGoalsActivity;
import com.example.ethereal.HelperClasses.ActivitiesAdapter;
import com.example.ethereal.HelperClasses.ActivitiesHelperClass;
import com.example.ethereal.HelperClasses.MeditationAdapter;
import com.example.ethereal.HelperClasses.MeditationHelperClass;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;
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
    public MaterialCardView addgoalcard, mediall, healthall, viewgoalcard, bookasessioncard;
    RecyclerView medichant_recycler;
    RecyclerView.Adapter mediadapter;
    NavHostFragment navHostFragment;
    NavController navController;
    RecyclerView activities_recycler;
    RecyclerView.Adapter actiadapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        viewgoalcard = view.findViewById(R.id.viewgoalcard);
        viewgoalcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), ViewGoalsActivity.class);
                startActivity(i);

//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, new NotificationsFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();;
            }
        });

        mediall = view.findViewById(R.id.mediall);
        mediall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_activitiesFragment);
//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                fragmentTransaction.replace(R.id.fragment_container, new ActivitiesFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

            }
        });
        bookasessioncard = view.findViewById(R.id.bookasessioncard);
        bookasessioncard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_therapistsFragment);
//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                fragmentTransaction.replace(R.id.fragment_container, new TherapistsFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

            }
        });
        healthall = view.findViewById(R.id.healthall);
        healthall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_activitiesFragment);

//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                fragmentTransaction.replace(R.id.fragment_container, new ActivitiesFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

            }
        });
        addgoalcard = view.findViewById(R.id.addgoalcard);
        addgoalcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), AddRemainderActivity.class);
                startActivity(intent);

            }
        });

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

                heyusername.setText("Hey, " + user.getName() + "!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

