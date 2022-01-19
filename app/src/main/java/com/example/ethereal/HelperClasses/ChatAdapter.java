package com.example.ethereal.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.R;

import java.util.ArrayList;

import Model.Chats;

public class ChatAdapter extends RecyclerView.Adapter {
    private ArrayList<Chats> chatsArrayList;
    private Context context;

    public ChatAdapter(ArrayList<Chats> chatsArrayList, Context context) {
        this.chatsArrayList = chatsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_message, parent, false);
                return new UserViewHolder(view);

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_message, parent, false);
                return new BotViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chats chats = chatsArrayList.get(position);
        switch (chats.getSender()){
            case "user":
                ((UserViewHolder)holder).user.setText(chats.getMessage());
                break;
            case "bot":
                ((BotViewHolder)holder).bot.setText(chats.getMessage());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatsArrayList.get(position).getSender()){
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatsArrayList.size();
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView user;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.usermessage);
        }
    }
    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView bot;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            bot = itemView.findViewById(R.id.botmessage);
        }
    }
}
