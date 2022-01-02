package com.example.ethereal.HelperClasses;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.R;
import com.google.android.gms.common.Feature;

import java.util.ArrayList;

public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder> {

    ArrayList<MeditationHelperClass> meditationChants;

    public MeditationAdapter(ArrayList<MeditationHelperClass> meditationChants) {
        this.meditationChants = meditationChants;
    }

    @NonNull
    @Override
    public MeditationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meditation_card_design,parent,false);
        MeditationViewHolder meditationViewHolder = new MeditationViewHolder(view);
        return meditationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationViewHolder holder, int position) {

        MeditationHelperClass meditationHelperClass = meditationChants.get(position);
        holder.image.setImageResource(meditationHelperClass.getImage());
        holder.title.setText(meditationHelperClass.getTitle());
        holder.desc.setText(meditationHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return meditationChants.size();
    }

    public static class MeditationViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public MeditationViewHolder(@NonNull View itemView) {
            super( itemView);

            image = itemView.findViewById(R.id.mediimg1);
            title = itemView.findViewById(R.id.medititle1);
            desc = itemView.findViewById(R.id.mediddesc1);
        }
    }
}
