package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ethereal.API.RetrofitAPI;
import com.example.ethereal.HelperClasses.ChatAdapter;
import com.example.ethereal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Fragments.HomeFragment;
import Model.Chats;
import Model.Message;
import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NancyActivity extends AppCompatActivity {

    private RecyclerView chatsRV;
    private EditText usermessage;
    private FloatingActionButton sendmessage;
    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    private ArrayList<Chats>chatsArrayList;
    private ChatAdapter chatAdapter;
    public ImageButton back;


    @Override
    public void onBackPressed() {
        Intent j = new Intent(NancyActivity.this, MainActivity.class);
        startActivity(j);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nancy);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.dark_grey));
            }
        }


        chatsRV = findViewById(R.id.botchatsRV);
        usermessage = findViewById(R.id.bottypemessage);
        sendmessage = findViewById(R.id.botsendmessage);
        chatsArrayList = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatsArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatAdapter);
        back = findViewById(R.id.botback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NancyActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usermessage.getText().toString().isEmpty()){
                    StyleableToast.makeText(NancyActivity.this, "Please enter your message", Toast.LENGTH_SHORT, R.style.customtoast).show();
                    return;
                }
                getResponse(usermessage.getText().toString());
                usermessage.setText("");
            }
        });

    }

    private void getResponse(String message){
    chatsArrayList.add(new Chats(message, USER_KEY));
    chatAdapter.notifyDataSetChanged();
    String url = "http://api.brainshop.ai/get?bid=162828&key=bLmf424BLXBfB5mo&uid=[uid]&msg="+message;
    String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<Message> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()) {
                    Message modal = response.body();
                    chatsArrayList.add(new Chats(modal.getCnt(), BOT_KEY));
                    chatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
            chatsArrayList.add(new Chats("Please revert your question", BOT_KEY));
            chatAdapter.notifyDataSetChanged();
            }
        });
        }
    }
