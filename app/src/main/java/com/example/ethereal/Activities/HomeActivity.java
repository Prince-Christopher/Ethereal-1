package com.example.ethereal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ethereal.HelperClasses.ActivitiesAdapter;
import com.example.ethereal.HelperClasses.ActivitiesHelperClass;
import com.example.ethereal.HelperClasses.MeditationAdapter;
import com.example.ethereal.HelperClasses.MeditationHelperClass;
import com.example.ethereal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import Model.User;
import io.github.muddz.styleabletoast.StyleableToast;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private TextView heyusername, displayscore, hpdescription;
    private FirebaseUser fUser;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String profileId, score;
    public MaterialCardView addgoalcard, mediall, healthall, viewgoalcard, bookasessioncard, soscard;
    RecyclerView medichant_recycler;
    RecyclerView.Adapter mediadapter;
    RecyclerView activities_recycler;
    RecyclerView.Adapter actiadapter;

    private long backPressedTime;
    private StyleableToast backToast;

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            finishAffinity();
            return;
        } else {
            backToast = StyleableToast.makeText(getBaseContext(), "Press back again to exit", R.style.customtoast);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.pale_grey));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            soscard = findViewById(R.id.soscard);
            soscard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(HomeActivity.this, SosActivity.class);
                    startActivity(i);
                }
            });
            viewgoalcard = findViewById(R.id.viewgoalcard);
            viewgoalcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(HomeActivity.this, ViewGoalsActivity.class);
                    startActivity(i);

//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, new NotificationsFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();;
                }
            });

            mediall = findViewById(R.id.mediall);
            mediall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(HomeActivity.this, ActivitiesActivity.class);
                    startActivity(i);
//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                fragmentTransaction.replace(R.id.fragment_container, new ActivitiesFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

                }
            });
            bookasessioncard = findViewById(R.id.bookasessioncard);
            bookasessioncard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent j = new Intent(HomeActivity.this, TherapistsActivity.class);
                    startActivity(j);
//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                fragmentTransaction.replace(R.id.fragment_container, new TherapistsFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

                }
            });
            healthall = findViewById(R.id.healthall);
            healthall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent k = new Intent(HomeActivity.this, ActivitiesActivity.class);
                    startActivity(k);

//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                fragmentTransaction.replace(R.id.fragment_container, new ActivitiesFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

                }
            });
            addgoalcard = findViewById(R.id.addgoalcard);
            addgoalcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(HomeActivity.this, AddRemainderActivity.class);
                    startActivity(intent);

                }
            });

            fUser = FirebaseAuth.getInstance().getCurrentUser();
            profileId = fUser.getUid();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("Survey");
            hpdescription = findViewById(R.id.hpdescription);
            displayscore = findViewById(R.id.displayscore);
//            Log.e("TAG2", "displaying scores in home");
//            score = getIntent().getStringExtra("currentscore");
//            displayscore.setText(score);
            heyusername = findViewById(R.id.heyusername);
            medichant_recycler = findViewById(R.id.medichant_recycler);
            medichant_recycler.setHasFixedSize(true);
            LinearLayoutManager medilinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            medilinearLayoutManager.setStackFromEnd(true);
            medilinearLayoutManager.setReverseLayout(true);
            medichant_recycler.setLayoutManager(medilinearLayoutManager);

            activities_recycler = findViewById(R.id.activities_recycler);
            activities_recycler.setHasFixedSize(true);
            LinearLayoutManager actilinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            actilinearLayoutManager.setStackFromEnd(true);
            actilinearLayoutManager.setReverseLayout(true);
            activities_recycler.setLayoutManager(actilinearLayoutManager);

            medichant_recycler();
            activities_recycler();
            helloUser();
            displayscore();
        }

    private void displayscore() {
        databaseReference.child("SurveyScore").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                displayscore.setText(data);
                hpdescription.setText("Based on your overall health test,\npoints you have secured is " +data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
            meditationChants.add(new MeditationHelperClass(R.drawable.mediactivity3, "Relaxation Meditation", "1 hour."));
            meditationChants.add(new MeditationHelperClass(R.drawable.mediactivity2, "Guided Meditation", "30 mins."));
            meditationChants.add(new MeditationHelperClass(R.drawable.mediactivity1, "Mindful Meditation", "10 mins."));

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

            bottomNavigationView.setSelectedItemId(R.id.home);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
//                        case R.id.home:
//                            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
//                            break;
                        case R.id.activities:
                            startActivity(new Intent(HomeActivity.this, ActivitiesActivity.class));
                            break;
                        case R.id.nancy:
                            startActivity(new Intent(HomeActivity.this, NancyActivity.class));
                            break;
                        case R.id.therapists:
                            startActivity(new Intent(HomeActivity.this, TherapistsActivity.class));
                            break;
                        case R.id.profile:
                            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                            break;
                    }

                    return true;
                }
        });

    }
}
