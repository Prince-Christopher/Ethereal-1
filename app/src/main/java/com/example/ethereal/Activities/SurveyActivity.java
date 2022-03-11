package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ethereal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

import Model.Survey;

public class SurveyActivity extends AppCompatActivity {

    //mark these as private. if there is no access modifier, it defaults to protected which can be confusing and
    //is a privacy concern
    //since you only use these inside this class, keep them private.
    private Button op1, op2, op3, op4;
    private ArrayList<Survey> surveyArrayList;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser fUser;
    DatabaseReference databaseReference;
    private SharedPreferences sp;
    private TextView question, questionNumber; //use camelCase as much as possible
    private Random random;
    private int currentScore = 0, questionsAttempted = 0, currentPosition = 1; //try to use whole words, it will potentially avoid confusion
    private Survey survey; //make the current survey a variable, so you dont need to get it from the list everytime.

    //also in android studio, use CTRL + Alt + L to format your code.
    //it is a fast way to make it look nicer.
//    String prevStarted = "yes";
//    @Override
//    protected void onResume() {
//        super.onResume();
//        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
//        if (!sharedpreferences.getBoolean(prevStarted, false)) {
//            SharedPreferences.Editor editor = sharedpreferences.edit();
//            editor.putBoolean(prevStarted, Boolean.TRUE);
//            editor.apply();
//        } else {
//            updateViews();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            View decor = getWindow().getDecorView();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.lavender));
                decor.setSystemUiVisibility(0);
            }
        }

        surveyArrayList = new ArrayList<>();
        random = new Random();
        initViews();
        addSurveyQuestions();
        updatePosition(); //make sure this is called at the start to get a survey
        updateViews();

    }

    //create a separate function for initializing your views.
    //its a personal preference, but it does look a lot cleaner.
    private void initViews() {
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);
        firebaseDatabase = FirebaseDatabase.getInstance();
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference("Users").child(fUser.getUid());
        questionNumber = findViewById(R.id.questionnumber);
        question = findViewById(R.id.question);

        //instead of new View.OnClickListener() { just use a lambda. it is a lot cleaner and faster
        // v -> { } with v as the view from the public void onClick(View v) {
        op1.setOnClickListener
                (v -> {
            //use equalsIgnoreCase instead of making the entire string lowercase
            if (survey.getAnswer() != (op1.getText().toString())) {
                currentScore = currentScore + 0; //cant tell what you're trying to do with your score here...
                //using currentScore++; will add 1 to the score
                //if different answers give a higher score, you should add that in your survey class
                //such as currentScore += survey.getScore()
            }
            updatePosition();
            updateViews();
        });
        op2.setOnClickListener(v -> {
            //since you set the strings for Survey yourself, there is no trailing space.
            //trim() is not needed.
            if (survey.getAnswer() != (op1.getText().toString())) {
                currentScore = currentScore + 1;
            }
            updatePosition();
            updateViews();
        });
        op3.setOnClickListener(v -> {
            if (survey.getAnswer() != (op3.getText().toString())) {
                currentScore = currentScore + 2;
            }
            updatePosition();
            updateViews();
        });
        op4.setOnClickListener(v -> {
            if (survey.getAnswer() != (op4.getText().toString())) {
                currentScore = currentScore + 3;
            }
            updatePosition();
            updateViews();
        });
    }


    //since you use only currentPosition globally (outside any function) the variable is not needed.
    private void updateViews() {
        //make sure it doesn't go past 9
        if (questionsAttempted >= 9) {
            String stringcurrentscore = String.valueOf(currentScore);
            Intent i = new Intent(SurveyActivity.this, HomeActivity.class);
            databaseReference.child("Survey").child("surveyscore").setValue(stringcurrentscore);
            startActivity(i);
        }
        if (survey == null) {
            return;
        }
        //might as well add questionsAttempted++; here
        questionsAttempted++;
        //the first + sign is not needed.
        //using String.format is just nicer imo
        //questionattempted is replacing the %s
        questionNumber.setText(String.format("%s out of 9", questionsAttempted));
        question.setText(survey.getQuestion());
        op1.setText(survey.getOption1());
        op2.setText(survey.getOption2());
        op3.setText(survey.getOption3());
        op4.setText(survey.getOption4());
    }

    //since you only have one arraylist, the variable is not needed.
    //also you using getSurveyQuestion() is confusing.
    private void addSurveyQuestions() {
        surveyArrayList.add(new Survey("Little interest or pleasure in doing things?",
                "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add(new Survey("Feeling down, depressed, or hopeless?", "Not at all",
                "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add(new Survey("Trouble falling or staying asleep, or sleeping too much?",
                "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add(new Survey("Feeling tired or having little energy?",
                "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add(new Survey("Poor appetite or overeating?",
                "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add(new Survey("Feeling bad about yourself - or that you are a failure or have let yourself or your family down?",
                "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add(new Survey("Trouble concentrating on things, such as reading the newspaper or watching television?",
                "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add(new Survey("Moving or speaking so slowly that other people could have noticed?\n" +
                "Or the opposite - being so fidgety or restless that you have been moving around a lot more than usual?",
                "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add(new Survey("Thoughts that you would be better off dead, or of hurting yourself in some way?",
                "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
    }

    //generates the index and refreshes the survey
    private void updatePosition() {
        currentPosition = random.nextInt(1);
        refreshSurvey();
    }

    //this will make getting the survey cleaner and less error prone.
    private void refreshSurvey() {
        //check if the list is empty or the if the position is higher than the list size.
        if (surveyArrayList.isEmpty() || currentPosition > surveyArrayList.size() - 1) {
            if (survey != null) {
                survey = null;
            }
            return;
        }
        survey = surveyArrayList.get(currentPosition);
        surveyArrayList.remove(currentPosition); //remove the survey from the list so it doesn't get asked again.
    }

    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}