package com.example.ethereal.HelperClasses;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.Activities.HealthCourseActivity1;
import com.example.ethereal.Activities.HealthCourseActivity2;
import com.example.ethereal.Activities.HealthCourseActivity3;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivitiesViewHolder> {
    ArrayList<ActivitiesHelperClass> activities;

    public ActivitiesAdapter(ArrayList<ActivitiesHelperClass>activities){
        this.activities = activities;
    }

    @NonNull
    @Override
    public ActivitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_card_design, parent, false);
        ActivitiesViewHolder activitiesViewHolder = new ActivitiesViewHolder(view);
        return activitiesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesViewHolder holder, int position) {
        ActivitiesHelperClass activitiesHelperClass = activities.get(position);
        holder.image.setImageResource(activitiesHelperClass.getImage());
        holder.title.setText(activitiesHelperClass.getTitle());
        holder.desc.setText(activitiesHelperClass.getDescription());
        holder.acticard.setOnClickListener(v -> {
            if (position == 2){
                Intent i = new Intent(v.getContext(), HealthCourseActivity1.class);
                i.putExtra("Title", activities.get(position).getTitle());
                i.putExtra("Description", activities.get(position).getDescription());
//                i.putExtra("URL1", "https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Mindful%2BMeditation+compression.mp3");
//                preparemediaplayer1(mediaPlayer, totaltime);
                v.getContext().startActivity(i);
            }
            if (position == 1){
                Intent i = new Intent(v.getContext(), HealthCourseActivity2.class);
                i.putExtra("Title", activities.get(position).getTitle());
                i.putExtra("Description", activities.get(position).getDescription());
//                i.putExtra("URL1", "https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Mindful%2BMeditation+compression.mp3");
//                preparemediaplayer1(mediaPlayer, totaltime);
                v.getContext().startActivity(i);
            }
            if (position == 0){
                Intent i = new Intent(v.getContext(), HealthCourseActivity3.class);
                i.putExtra("Title", activities.get(position).getTitle());
                i.putExtra("Description", activities.get(position).getDescription());
//                i.putExtra("URL1", "https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Mindful%2BMeditation+compression.mp3");
//                preparemediaplayer1(mediaPlayer, totaltime);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ActivitiesViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;
        MaterialCardView acticard;

        public ActivitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.actiimg1);
            title = itemView.findViewById(R.id.actititle1);
            desc = itemView.findViewById(R.id.actidesc1);
            acticard = itemView.findViewById(R.id.acticard);
        }
    }


}
