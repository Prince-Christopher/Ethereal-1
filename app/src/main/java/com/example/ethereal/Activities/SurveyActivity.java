package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.ethereal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

import Model.Survey;

public class SurveyActivity extends AppCompatActivity {

    Button op1, op2, op3, op4;
    ArrayList<Survey> surveyArrayList;
    TextView question, questionnumber, myscore;
    Random random;
    int currentscore = 0, questionattempted = 1, currentpos;
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

        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);
        myscore = findViewById(R.id.myscore);
        questionnumber = findViewById(R.id.questionnumber);
        question = findViewById(R.id.question);
        surveyArrayList = new ArrayList<>();
        random = new Random();
        getSurveyQuestion(surveyArrayList);
        currentpos = random.nextInt(surveyArrayList.size());
        setDataToViews(currentpos);

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (surveyArrayList.get(currentpos).getAnswer().trim().toLowerCase() != op1.getText().toString().trim().toLowerCase()){
                currentscore = currentscore + 1;
            }
            questionattempted++;
            currentpos = random.nextInt(surveyArrayList.size());
            setDataToViews(currentpos);
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surveyArrayList.get(currentpos).getAnswer().trim().toLowerCase() != op2.getText().toString().trim().toLowerCase()){
                    currentscore = currentscore + 2;
                }
                questionattempted++;
                currentpos = random.nextInt(surveyArrayList.size());
                setDataToViews(currentpos);
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surveyArrayList.get(currentpos).getAnswer().trim().toLowerCase() != op3.getText().toString().trim().toLowerCase()){
                    currentscore = currentscore + 3;
                }
                questionattempted++;
                currentpos = random.nextInt(surveyArrayList.size());
                setDataToViews(currentpos);
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surveyArrayList.get(currentpos).getAnswer().trim().toLowerCase() != op4.getText().toString().trim().toLowerCase()){
                    currentscore = currentscore + 4;
                }
                questionattempted++;
                currentpos = random.nextInt(surveyArrayList.size());
                setDataToViews(currentpos);
            }
        });
    }


    private void setDataToViews(int currentpos) {
        questionnumber.setText(+questionattempted + " of 9");
        myscore.setText("Score is " +currentscore);
        if (questionattempted == 9) {
            Intent i = new Intent(SurveyActivity.this, HomeActivity.class);
            startActivity(i);
        } else {
            question.setText(surveyArrayList.get(currentpos).getQuestion());
//            surveyArrayList.remove(0);
//            surveyArrayList.remove(1);
//            surveyArrayList.remove(2);
//            surveyArrayList.remove(3);
//            surveyArrayList.remove(4);
//            surveyArrayList.remove(5);
//            surveyArrayList.remove(6);
//            surveyArrayList.remove(7);
//            surveyArrayList.remove(8);
            op1.setText(surveyArrayList.get(currentpos).getOption1());
            op2.setText(surveyArrayList.get(currentpos).getOption2());
            op3.setText(surveyArrayList.get(currentpos).getOption3());
            op4.setText(surveyArrayList.get(currentpos).getOption4());
        }
    }

    private void getSurveyQuestion(ArrayList<Survey> surveyArrayList) {
        surveyArrayList.add
                (new Survey("Little interest or pleasure in doing things?", "Not at all", "Several days", "More than half the days", "Nearly every day", "0"));
        surveyArrayList.add
                (new Survey("Feeling down, depressed, or hopeless?", "Not at all", "Several days", "More than half the days", "Nearly every day", "0"));

        surveyArrayList.add
                (new Survey("Trouble falling or staying asleep, or sleeping too much?", "Not at all", "Several days", "More than half the days", "Nearly every day", "0"));

        surveyArrayList.add
                (new Survey("Feeling tired or having little energy?", "Not at all", "Several days", "More than half the days", "Nearly every day", "0"));
        surveyArrayList.add
                (new Survey("Poor appetite or overeating?", "Not at all", "Several days", "More than half the days", "Nearly every day", "0"));
        surveyArrayList.add
                (new Survey("Feeling bad about yourself - or that you are a failure or have let yourself or your family down?", "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add
                (new Survey("Trouble concentrating on things, such as reading the newspaper or watching television?", "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add
                (new Survey("Moving or speaking so slowly that other people could have noticed?\n" +
                        "Or the opposite - being so fidgety or restless that you have been moving around a lot more than usual?", "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
        surveyArrayList.add
                (new Survey("Thoughts that you would be better off dead, or of hurting yourself in some way?", "Not at all", "Several days", "More than half the days", "Nearly every day", ""));
    }

}