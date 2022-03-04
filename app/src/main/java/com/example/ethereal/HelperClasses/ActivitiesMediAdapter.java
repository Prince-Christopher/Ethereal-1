package com.example.ethereal.HelperClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.Activities.MeditationPlayerActivity;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import Model.Meditation;

public class ActivitiesMediAdapter extends RecyclerView.Adapter<ActivitiesMediAdapter.ActivitiesMediHolder> {

    ArrayList<Meditation> meditation;

    public ActivitiesMediAdapter(ArrayList<Meditation> meditation) {
        this.meditation = meditation;
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
        holder.img_meditation_thumbnail.setImageResource(meditation1.getThumbnail());
        holder.tv_meditation_title.setText(meditation1.getTitle());
        holder.tv_meditation_desc.setText(meditation1.getDescription());
        holder.medicard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("TAG3", "Card Works!!!!!!!!");
                Intent i = new Intent(v.getContext(), MeditationPlayerActivity.class);
                i.putExtra("Title", meditation.get(position).getTitle());
                i.putExtra("Description", meditation.get(position).getDescription());
                v.getContext().startActivity(i);

            }
        });
//        holder.medirl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TAG3", "onClick" + position);
//                v.getContext().startActivity(new Intent(v.getContext(), MeditationPlayerActivity.class));
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return meditation.size();
    }

    public static class ActivitiesMediHolder extends RecyclerView.ViewHolder{

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
    //    private Context mContext;
//    private List<Meditation> mData;
//
//    public ActivitiesMediAdapter(Context mContext, List<Meditation> mData) {
//        this.mContext = mContext;
//        this.mData = mData;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view = mInflater.inflate(R.layout.activities_medi_card, parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//    holder.tv_meditation_title.setText(mData.get(position).getTitle());
//    holder.tv_meditation_desc.setText(mData.get(position).getDescription());
//    holder.img_meditation_thumbnail.setImageResource(mData.get(position).getThumbnail());
//    holder.medicard.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent i = new Intent(mContext, MeditationPlayerActivity.class);
//            mContext.startActivity(i);
//        }
//    });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView tv_meditation_title, tv_meditation_desc;
//        ImageView img_meditation_thumbnail;
//        MaterialCardView medicard;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv_meditation_title = itemView.findViewById(R.id.medititle);
//            tv_meditation_desc = itemView.findViewById(R.id.medidesc);
//            img_meditation_thumbnail = itemView.findViewById(R.id.mediimg);
//            medicard = itemView.findViewById(R.id.medicard);
//        }
//    }
}
