package com.example.ethereal.HelperClasses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.Activities.MeditationPlayerActivity1;
import com.example.ethereal.Activities.MeditationPlayerActivity2;
import com.example.ethereal.Activities.MeditationPlayerActivity3;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

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
    public void onBindViewHolder(@NonNull MeditationViewHolder holder, @SuppressLint("RecyclerView") int position) {

        MeditationHelperClass medi1 = meditationChants.get(position);
        holder.image.setImageResource(medi1.getImage());
        holder.title.setText(medi1.getTitle());
        holder.desc.setText(medi1.getDescription());
        holder.medicardhome.setOnClickListener(v -> {
            if (position == 2) {
                Intent i = new Intent(v.getContext(), MeditationPlayerActivity1.class);
                i.putExtra("Title", meditationChants.get(position).getTitle());
                i.putExtra("Description", meditationChants.get(position).getDescription());
                v.getContext().startActivity(i);
            }
            if (position == 1) {
                Intent i = new Intent(v.getContext(), MeditationPlayerActivity2.class);
                i.putExtra("Title", meditationChants.get(position).getTitle());
                i.putExtra("Description", meditationChants.get(position).getDescription());
                v.getContext().startActivity(i);
            }
            if (position == 0) {
                Intent i = new Intent(v.getContext(), MeditationPlayerActivity3.class);
                i.putExtra("Title", meditationChants.get(position).getTitle());
                i.putExtra("Description", meditationChants.get(position).getDescription());
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meditationChants.size();
    }

    public static class MeditationViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;
        MaterialCardView medicardhome;

        public MeditationViewHolder(@NonNull View itemView) {
            super( itemView);

            image = itemView.findViewById(R.id.mediimg1);
            title = itemView.findViewById(R.id.medititle1);
            desc = itemView.findViewById(R.id.mediddesc1);
            medicardhome = itemView.findViewById(R.id.medicardhome);

        }
    }
}
