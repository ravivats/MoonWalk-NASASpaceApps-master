package com.example.ravivats.moonwalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizResult extends AppCompatActivity {
    TextView quizResult;
    ListView answersListView;
    int result;
    ArrayList<String> answers,displayTexts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        quizResult = (TextView) findViewById(R.id.quizResultTxt);
        answersListView = (ListView) findViewById(R.id.answersListView);
        answers = getIntent().getStringArrayListExtra("answerInfo");
        displayTexts = new ArrayList<>();
        result =getIntent().getIntExtra("score",0);
        quizResult.setText("Your Quiz Score is: "+ result +" out of 5");
        for(int i=1;i<=answers.size();i++) {
            displayTexts.add("Question"+ i+": "+answers.get(i-1));
        }
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayTexts);
        answersListView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(QuizResult.this,MainActivity.class));
    }
}
