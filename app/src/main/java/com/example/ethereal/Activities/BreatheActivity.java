package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import android.view.View;

public class BreatheActivity extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    LottieAnimationView breathe;
    MaterialCardView skip;
    Animation anim;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.pale_pink));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        breathe = findViewById(R.id.breathe);
        breathe.playAnimation();
        anim = AnimationUtils.loadAnimation(this, R.anim.transition_anim);
        breathe.startAnimation(anim);
        handler = new Handler();
        runnable = () ->
        {   Intent i = new Intent(BreatheActivity.this, HomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
            overridePendingTransition(R.anim.fadeinsplash, R.anim.fadeoutsplash);
            finish();

        };
        handler.postDelayed(runnable, 5000);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener((View.OnClickListener) v ->
        {
            runnable.run();
        });

    }}