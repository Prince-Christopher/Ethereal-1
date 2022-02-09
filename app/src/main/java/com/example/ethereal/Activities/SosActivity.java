package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

public class SosActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SosActivity.this, HomeActivity.class));
    }

    MaterialCardView crisishelplinecard, childhelplinecard, sosback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.pale_pink));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            crisishelplinecard = findViewById(R.id.crisishelplinecard);
            crisishelplinecard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SosActivity.this, CrisisHelplinesActivity.class);
                    startActivity(i);
                }
            });
            childhelplinecard = findViewById(R.id.childhelplinecard);
            childhelplinecard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SosActivity.this, ChildHelplinesActivity.class);
                    startActivity(i);
                }
            });
            sosback = findViewById(R.id.sosback);
            sosback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SosActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            });
        }
    }
}