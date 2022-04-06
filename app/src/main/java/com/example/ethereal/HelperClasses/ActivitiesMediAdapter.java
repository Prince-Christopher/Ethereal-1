package com.example.ethereal.HelperClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.Activities.MeditationPlayerActivity1;
import com.example.ethereal.Activities.MeditationPlayerActivity2;
import com.example.ethereal.Activities.MeditationPlayerActivity3;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import Model.Meditation;

public class ActivitiesMediAdapter extends RecyclerView.Adapter<ActivitiesMediAdapter.ActivitiesMediHolder> {

    ArrayList<Meditation> meditation;
    private Context mContext;

    public ActivitiesMediAdapter() {

    }

//    public void preparemediaplayer1(MediaPlayer mediaPlayer, TextView totaltime) {
//        try {
//            mediaPlayer.setDataSource("https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Mindful%2BMeditation+compression.mp3");
//            mediaPlayer.prepare();
//            totaltime.setText(millisecondstotimer(mediaPlayer.getDuration()));
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void preparemediaplayer2(MediaPlayer mediaPlayer, TextView totaltime) {
//        try {
//            mediaPlayer.setDataSource("https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Guided+Meditation.mp3");
//            mediaPlayer.prepare();
//            totaltime.setText(millisecondstotimer(mediaPlayer.getDuration()));
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private String millisecondstotimer(long milliseconds) {
//        String timerString = "";
//        String secondString;
//
//        int hours = (int) (milliseconds / (1000 * 60 * 60));
//        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
//        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
//
//        if (hours > 0) {
//            timerString = hours + ":";
//        }
//        if (seconds < 10) {
//            secondString = "0" + seconds;
//        } else {
//            secondString = "" + seconds;
//        }
//        timerString = timerString + minutes + ":" + secondString;
//        return timerString;
//
//    }


    public ActivitiesMediAdapter(ArrayList<Meditation> meditation, Context context) {
        this.meditation = meditation;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ActivitiesMediHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_medi_card, parent, false);
        ActivitiesMediHolder activitiesMediHolder = new ActivitiesMediHolder(view);
        return activitiesMediHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesMediHolder holder, @SuppressLint("RecyclerView") int position) {

        Meditation meditation1 = meditation.get(position);
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        TextView totaltime = null;
        holder.img_meditation_thumbnail.setImageResource(meditation1.getThumbnail());
        holder.tv_meditation_title.setText(meditation1.getTitle());
        holder.tv_meditation_desc.setText(meditation1.getDescription());
        holder.medicard.setOnClickListener(v -> {
            if (position == 0) {
                Intent i = new Intent(v.getContext(), MeditationPlayerActivity1.class);
                i.putExtra("Title", meditation.get(position).getTitle());
                i.putExtra("Description", meditation.get(position).getDescription());
//                i.putExtra("URL1", "https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Mindful%2BMeditation+compression.mp3");
//                preparemediaplayer1(mediaPlayer, totaltime);
                v.getContext().startActivity(i);

            }
            if (position == 1) {
                Intent i = new Intent(v.getContext(), MeditationPlayerActivity2.class);
                i.putExtra("Title", meditation.get(position).getTitle());
                i.putExtra("Description", meditation.get(position).getDescription());
//                i.putExtra("URL2", "https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Guided+Meditation.mp3");
//                preparemediaplayer2(mediaPlayer, totaltime);
                v.getContext().startActivity(i);
            }
            if (position == 2) {
                Intent i = new Intent(v.getContext(), MeditationPlayerActivity3.class);
                i.putExtra("Title", meditation.get(position).getTitle());
                i.putExtra("Description", meditation.get(position).getDescription());
//                i.putExtra("URL2", "https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Guided+Meditation.mp3");
//                preparemediaplayer2(mediaPlayer, totaltime);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meditation.size();
    }

    public static class ActivitiesMediHolder extends RecyclerView.ViewHolder {

        TextView tv_meditation_title, tv_meditation_desc;
        ImageView img_meditation_thumbnail;
        MaterialCardView medicard;
        RelativeLayout medirl;

        public ActivitiesMediHolder(@NonNull View itemView) {
            super(itemView);

            tv_meditation_title = itemView.findViewById(R.id.medititle);
            tv_meditation_desc = itemView.findViewById(R.id.medidesc);
            img_meditation_thumbnail = itemView.findViewById(R.id.mediimg);
            medicard = itemView.findViewById(R.id.medicard);
            medirl = itemView.findViewById(R.id.medirl);
        }
    }
}
