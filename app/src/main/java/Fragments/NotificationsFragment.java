package Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.ethereal.Activities.AddRemainderActivity;
import com.example.ethereal.AlarmDatabase.DatabaseClass;
import com.example.ethereal.AlarmDatabase.EntityClass;
import com.example.ethereal.HelperClasses.AlarmEventAdapter;
import com.example.ethereal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotificationsFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    AlarmEventAdapter alarmEventAdapter;
    RecyclerView notificationrv;
    DatabaseClass databaseClass;

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
        notificationrv = view.findViewById(R.id.notificationrv);
        databaseClass = DatabaseClass.getDatabase(getContext().getApplicationContext());
        return view;
    }
    private void setAlarmEventAdapter(){
        List<EntityClass> classList = databaseClass.EventDao().getAllData();
        alarmEventAdapter = new AlarmEventAdapter(getContext(), classList);
        notificationrv.setAdapter(alarmEventAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setAlarmEventAdapter();
    }
}