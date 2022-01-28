package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ethereal.Activities.AddRemainderActivity;
import com.example.ethereal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotificationsFragment extends Fragment {


    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        floatingActionButton = view.findViewById(R.id.fab_add_task);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddRemainderActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}