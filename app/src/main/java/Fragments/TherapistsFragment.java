package Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class TherapistsFragment extends Fragment {
    MaterialCardView therapistback;
    RecyclerView therapistsrv;
    DatabaseReference databaseReference;
    TherapistRetrieverAdapter therapistRetrieverAdapter;
    ArrayList<Therapist> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View view = inflater.inflate(R.layout.fragment_therapists, container, false);
        therapistsrv = view.findViewById(R.id.therapistsrv);
        databaseReference= FirebaseDatabase.getInstance().getReference("Therapists");
        therapistsrv.setLayoutManager(new LinearLayoutManager(getContext()));
        therapistsrv.setHasFixedSize(true);

        therapistback = view.findViewById(R.id.therapistback);
        therapistback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_therapistsFragment_to_homeFragment);
            }
        });

        list = new ArrayList<>();
        therapistRetrieverAdapter = new TherapistRetrieverAdapter(getContext(), list);
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
        return view;
    }
}