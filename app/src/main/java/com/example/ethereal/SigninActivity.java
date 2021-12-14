package com.example.ethereal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_signin);

        emailsignin = findViewById(R.id.emailsignin);
        passwordsignin = findViewById(R.id.passwordsignin);

        forgotpassword = findViewById(R.id.forgotpassword);
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
                                StyleableToast.makeText(SigninActivity.this, "Welcome to your comfy space ;)", R.style.customtoast).show();
                                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
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