package Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ethereal.Activities.InsightsActivity;
import com.example.ethereal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MoodsFragment extends Fragment {
    public int happyCounter, relaxedCounter, neutralCounter, sadCounter, angryCounter, fearCounter, proudCounter, sickCounter, sillyCounter;
    FirebaseDatabase firebaseDatabase;
    private FirebaseUser fUser;
    DatabaseReference databaseReference;
    MaterialCardView happycard, relaxedcard, neutralcard, sadcard, angrycard, fearcard, proudcard, sickcard, sillycard;
    ImageView insights;

    String hCounter, rCounter, nCounter, sCounter, aCounter, fCounter, pCounter, sicCounter, silCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_moods, container, false);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users").child(fUser.getUid());
        happycard = view.findViewById(R.id.happycard);
        relaxedcard = view.findViewById(R.id.relaxedcard);
        neutralcard = view.findViewById(R.id.neutralcard);
        sadcard = view.findViewById(R.id.sadcard);
        angrycard = view.findViewById(R.id.angrycard);
        fearcard = view.findViewById(R.id.fearcard);
        proudcard = view.findViewById(R.id.proudcard);
        sickcard = view.findViewById(R.id.sickcard);
        sillycard = view.findViewById(R.id.sillycard);

        getdata();

        insights = view.findViewById(R.id.insights);
        insights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), InsightsActivity.class);
                startActivity(i);
            }
        });



        happycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.happyyellow);
                dialog.show();
                happyCounter++;
                String hCounter;
                hCounter = String.valueOf(happyCounter);
                Log.e("MOOD", "value is set");
                databaseReference.child("Moods").child("happycard").setValue(hCounter);
            }
    });

        relaxedcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.relaxedcerulean);
                dialog.show();
                relaxedCounter++;
                String rCounter;
                rCounter = String.valueOf(relaxedCounter);
                databaseReference.child("Moods").child("relaxedcard").setValue(rCounter);
            }
        });

        neutralcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.neutralpink);
                dialog.show();
                neutralCounter++;
                String nCounter;
                nCounter = String.valueOf(neutralCounter);
                databaseReference.child("Moods").child("neutralcard").setValue(nCounter);
            }
        });

        sadcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.sadpurple);
                dialog.show();
                sadCounter++;
                String saCounter;
                saCounter = String.valueOf(sadCounter);
                databaseReference.child("Moods").child("sadcard").setValue(saCounter);
            }
        });

        angrycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.angryred);
                dialog.show();
                angryCounter++;
                String aCounter;
                aCounter = String.valueOf(angryCounter);
                databaseReference.child("Moods").child("angrycard").setValue(aCounter);
            }
        });

        fearcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.fearycyan);
                dialog.show();
                fearCounter++;
                String fCounter;
                fCounter = String.valueOf(fearCounter);
                databaseReference.child("Moods").child("fearcard").setValue(fCounter);
            }
        });

        proudcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.proudtangerine);
                dialog.show();
                proudCounter++;
                String pCounter;
                pCounter = String.valueOf(proudCounter);
                databaseReference.child("Moods").child("proudcard").setValue(pCounter);
            }
        });

        sickcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.sickviolet);
                dialog.show();
                sickCounter++;
                String sicCounter;
                sicCounter = String.valueOf(sickCounter);
                databaseReference.child("Moods").child("sickcard").setValue(sicCounter);
            }
        });

        sillycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.moodsdialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.sillyred);
                dialog.show();
                sillyCounter++;
                String silCounter;
                silCounter = String.valueOf(sillyCounter);
                databaseReference.child("Moods").child("sillycard").setValue(silCounter);
            }
        });

    return view;

}
    private void getdata() {
        ProgressDialog pd=new ProgressDialog(getActivity());
        pd.setMessage("Loading Data");
        pd.show();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users").child(fUser.getUid());
        databaseReference.child("Moods").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                pd.cancel();
                if (task.getResult().exists()) {
                    for (DataSnapshot data : task.getResult().getChildren()) {
                        switch (data.getKey()) {
                            case "happycard":
                                happyCounter=Integer.parseInt(data.getValue().toString());
                                break;
                            case "fearcard":
                                fearCounter=Integer.parseInt(data.getValue().toString());
                                break;

                            case "relaxedcard":
                                relaxedCounter=Integer.parseInt(data.getValue().toString());
                                break;

                            case "neutralcard":
                                nCounter = data.getValue().toString();
                                neutralCounter=Integer.parseInt(data.getValue().toString());
                                break;

                            case "sadcard":
                                sCounter = data.getValue().toString();
                                sadCounter=Integer.parseInt(data.getValue().toString());
                                break;

                            case "angrycard":
                                aCounter = data.getValue().toString();
                                angryCounter=Integer.parseInt(data.getValue().toString());
                                break;

                            case "proudcard":
                                pCounter = data.getValue().toString();
                                proudCounter=Integer.parseInt(data.getValue().toString());
                                break;

                            case "sickcard":
                                sicCounter = data.getValue().toString();
                                sickCounter=Integer.parseInt(data.getValue().toString());
                                break;

                            case "sillycard":
                                silCounter = data.getValue().toString();
                                sillyCounter=Integer.parseInt(data.getValue().toString());
                                break;

                        }
                    }
                }
            }
        });
    }
}