package com.example.ethereal.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.Activities.MeditationPlayerActivity;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import Model.Therapist;
import Model.User;

public class TherapistRetrieverAdapter extends RecyclerView.Adapter<TherapistRetrieverAdapter.MyViewHolder> {

    Context context;
    ArrayList<Therapist> list;

    public TherapistRetrieverAdapter(Context context, ArrayList<Therapist> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.therapists_card , parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Therapist therapist = list.get(position);
        holder.name.setText(therapist.getName());
        holder.email.setText(therapist.getEmail());
//        holder.therapistscard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.getContext().startActivity(new Intent(v.getContext(), MeditationPlayerActivity.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView name,email;
//        MaterialCardView therapistscard;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.therapistname);
            email=itemView.findViewById(R.id.therapistemail);
//            therapistscard=itemView.findViewById(R.id.therapistscard);
        }
    }
}
