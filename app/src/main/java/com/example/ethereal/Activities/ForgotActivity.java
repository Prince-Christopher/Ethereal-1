package com.example.ethereal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ethereal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import io.github.muddz.styleabletoast.StyleableToast;

public class ForgotActivity extends AppCompatActivity {

    private EditText forgotemail;
    private Button forgotreset;
    private ProgressBar forgotprogressbar;
    private ImageView forgotback;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.dark_grey));
            }
            forgotemail = findViewById(R.id.forgotemail);
            forgotreset = findViewById(R.id.forgotreset);
            forgotprogressbar = findViewById(R.id.forgotprogressbar);
            forgotback = findViewById(R.id.forgotback);

            forgotback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            auth = FirebaseAuth.getInstance();
            forgotreset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset();
                }
            });
        }
    }

    private void reset() {
        String email = forgotemail.getText().toString().trim();

        if (email.isEmpty()){
            forgotemail.setError("Email is required");
            forgotemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            forgotemail.setError("Please provide a valid email");
            forgotemail.requestFocus();
            return;
        }
        forgotprogressbar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StyleableToast.makeText(ForgotActivity.this, "Check your email to reset your password", Toast.LENGTH_SHORT,R.style.customtoast).show();
                    forgotprogressbar.setVisibility(View.GONE);
                }
                else {
                    StyleableToast.makeText(ForgotActivity.this, "Try again, Something went wrong", Toast.LENGTH_SHORT, R.style.customtoast).show();
                    forgotprogressbar.setVisibility(View.GONE);
                }
            }
        });
    }
}