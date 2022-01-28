package com.example.ethereal.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.R;

import java.util.List;

import Model.Meditation;

public class ActivitiesMediAdapter extends RecyclerView.Adapter<ActivitiesMediAdapter.MyViewHolder> {

    private Context mContext;
    private List<Meditation> mData;

    public ActivitiesMediAdapter(Context mContext, List<Meditation> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.activities_medi_card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.tv_meditation_title.setText(mData.get(position).getTitle());
    holder.tv_meditation_desc.setText(mData.get(position).getDescription());
    holder.img_meditation_thumbnail.setImageResource(mData.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_meditation_title, tv_meditation_desc;
        ImageView img_meditation_thumbnail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_meditation_title = itemView.findViewById(R.id.medititle);
            tv_meditation_desc = itemView.findViewById(R.id.medidesc);
            img_meditation_thumbnail = itemView.findViewById(R.id.mediimg);
        }
    }
}
