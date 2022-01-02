package com.example.ethereal;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.User;
import io.github.muddz.styleabletoast.StyleableToast;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener{

    TextView alreadyjoin;
    EditText namenickname,emailjoin,passwordjoin;
    Button submitjoin;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    public void onBackPressed() {
        Intent j = new Intent(JoinActivity.this, StartActivity.class);
        startActivity(j);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_join);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.dark_grey));
            }
        }

        mAuth = FirebaseAuth.getInstance();

        namenickname = (EditText) findViewById(R.id.namenickname);
        emailjoin = (EditText) findViewById(R.id.emailjoin);
        passwordjoin = (EditText) findViewById(R.id.passwordjoin);

        submitjoin = (Button) findViewById(R.id.submitjoin);
        submitjoin.setOnClickListener(this);

        alreadyjoin = (TextView) findViewById(R.id.alreadyjoin);
        alreadyjoin.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitjoin:
                submitjoin();
                break;
            case R.id.alreadyjoin:
                startActivity(new Intent(this, SigninActivity.class));
                break;
        }

    }
    private void submitjoin() {
        String name = namenickname.getText().toString().trim();
        String email = emailjoin.getText().toString().trim();
        String password = passwordjoin.getText().toString().trim();

        if(name.isEmpty()){
            namenickname.setError("This field is required");
            namenickname.requestFocus();
            return;
        }

        if(email.isEmpty()){
            emailjoin.setError("This field is required");
            emailjoin.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailjoin.setError("Please provide a valid email");
            emailjoin.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordjoin.setError("This field is required");
            passwordjoin.requestFocus();
            return;
        }
        if(password.length() < 6){
            passwordjoin.setError("Min password length should be 6 characters");
            passwordjoin.requestFocus();
            return;
        }
        {
            mAuth.fetchSignInMethodsForEmail(emailjoin.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            boolean check = !task.getResult().getSignInMethods().isEmpty();
                            if(check)
                            {
                                StyleableToast.makeText(getApplicationContext(), "Email already exists", R.style.customtoast).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(name,email,password);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {


                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                progressBar.setVisibility(View.GONE);

                                if (user.isEmailVerified()) {
                                    startActivity(new Intent(JoinActivity.this, MainActivity.class));
                                }
                                else {
                                    user.sendEmailVerification();
                                    StyleableToast.makeText(JoinActivity.this, "Check your email to verify your account", R.style.customtoast).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                                if(!task.isSuccessful())
                                {
                                    StyleableToast.makeText(JoinActivity.this, "Failed to sign up", R.style.customtoast).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        }

                    });
                }
            }
        });
    }}
