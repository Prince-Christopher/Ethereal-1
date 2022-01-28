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

import Model.Health;

public class ActivitiesHealthAdapter extends RecyclerView.Adapter<ActivitiesHealthAdapter.MyViewHolder> {
    private Context mContext;
    private List<Health> mData;

    public ActivitiesHealthAdapter(Context mContext, List<Health> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ActivitiesHealthAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.activities_health_card, parent,false);
        return new ActivitiesHealthAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesHealthAdapter.MyViewHolder holder, int position) {
        holder.tv_health_title.setText(mData.get(position).getTitle());
        holder.tv_health_desc.setText(mData.get(position).getDescription());
        holder.img_health_thumbnail.setImageResource(mData.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_health_title, tv_health_desc;
        ImageView img_health_thumbnail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_health_title = itemView.findViewById(R.id.healthtitle);
            tv_health_desc = itemView.findViewById(R.id.healthdesc);
            img_health_thumbnail = itemView.findViewById(R.id.healthimg);
        }
    }
}
