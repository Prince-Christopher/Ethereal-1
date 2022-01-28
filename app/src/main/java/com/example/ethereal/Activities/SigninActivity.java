package com.example.ethereal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ethereal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import OnBoarding.OnBoardingActivity;
import io.github.muddz.styleabletoast.StyleableToast;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{

    EditText emailsignin, passwordsignin;
    TextView dontsignin, forgotpassword;
    FirebaseAuth mAuth;
    Button submitsignin;
    ProgressBar progressBar;

    @Override
    public void onBackPressed() {
        Intent j = new Intent(SigninActivity.this, StartActivity.class);
        startActivity(j);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_signin);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        emailsignin = findViewById(R.id.emailsignin);
        passwordsignin = findViewById(R.id.passwordsignin);

        forgotpassword = findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(this);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        submitsignin = findViewById(R.id.submitsignin);
        submitsignin.setOnClickListener(this);

        dontsignin = findViewById(R.id.dontsignin);
        dontsignin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitsignin:
                submitsignin();
                break;
            case R.id.dontsignin:
                startActivity(new Intent(this, JoinActivity.class));
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, ForgotActivity.class));
                break;
        }

    }

    private void submitsignin() {
        String email = emailsignin.getText().toString().trim();
        String password = passwordsignin.getText().toString().trim();

        if(email.isEmpty()){
            emailsignin.setError("This field is required");
            emailsignin.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailsignin.setError("Please provide a valid email");
            emailsignin.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordsignin.setError("This field is required");
            passwordsignin.requestFocus();
            return;
        }
        if(password.length() < 6){
            passwordsignin.setError("Min password length should be 6 characters");
            passwordsignin.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            progressBar.setVisibility(View.GONE);

                            if (user.isEmailVerified()) {
                                StyleableToast.makeText(SigninActivity.this, "Let's get started!", R.style.customtoast).show();
                                Intent intent = new Intent(SigninActivity.this, OnBoardingActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                finish();
                            }
                        }else {
                            StyleableToast.makeText(SigninActivity.this, "Failed to sign in! Please check your credentials", R.style.customtoast).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}