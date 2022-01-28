package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ethereal.Activities.CheckActivity;
import com.example.ethereal.Activities.EditActivity;
import com.example.ethereal.Activities.SplashActivity;
import com.example.ethereal.Activities.StartActivity;
import com.example.ethereal.HelperClasses.ViewPagerAdapter;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class ProfileFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView edit;
    private ImageView logout;
    private FirebaseUser fUser;
    private DatabaseReference reference;
    private String profileId;
    private TextView profilename;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        fUser = FirebaseAuth.getInstance().getCurrentUser();
        profileId = fUser.getUid();
        profilename = view.findViewById(R.id.profilename);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), StartActivity.class));
                getActivity().finish();
            }
        });
        edit = view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
        profileName();
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setUpViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new MoodsFragment(), "Mood");
        adapter.addFragment(new NotificationsFragment(), "Notifications");
        adapter.addFragment(new JournalFragment(), "Journal");
        viewPager.setAdapter(adapter);

    }

    private void profileName() {

        FirebaseDatabase.getInstance().getReference().child("Users").child(profileId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                profilename.setText(user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}