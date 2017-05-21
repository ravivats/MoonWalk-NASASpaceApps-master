package com.example.ravivats.moonwalk;

import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.ArrayListMultimap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import static android.R.attr.name;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity" ;
    private static int correct=0;
    private DatabaseReference mDatabase;
    Iterable<DataSnapshot> questions;
    Iterator<DataSnapshot> question;
    ArrayList<Boolean> answers;
    TextView quizQuestion;
    RadioButton option1Btn,option2Btn,option3Btn,option4Btn;
    Button nextQuestionBtn,submitQuestionBtn,startQuiz;
    RadioGroup quizGroup;
    String correctAnswer,markedAnswer;
    int checkId=9999;
    int loopno=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        String code = getIntent().getStringExtra("name");
        Toast.makeText(this, code, Toast.LENGTH_LONG).show();
        quizQuestion = (TextView) findViewById(R.id.quizQuestion);
        option1Btn = (RadioButton) findViewById(R.id.quizOption1);
        option2Btn = (RadioButton) findViewById(R.id.quizOption2);
        option3Btn = (RadioButton) findViewById(R.id.quizOption3);
        option4Btn = (RadioButton) findViewById(R.id.quizOption4);
        nextQuestionBtn = (Button) findViewById(R.id.nextQuestionBtn);
        startQuiz = (Button) findViewById(R.id.startQuiz);
        submitQuestionBtn = (Button) findViewById(R.id.submitQuestionBtn);
        quizGroup = (RadioGroup) findViewById(R.id.quizOptions);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("questions").child("trigger").setValue(Math.random());
        mDatabase.child("questions").child("trigger").removeValue();
        mDatabase.child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questions = dataSnapshot.getChildren();
                question = questions.iterator();
                for (DataSnapshot question1 : questions) {
                    Question ques = question1.getValue(Question.class);
                    quizQuestion.setText(ques.question);
                    option1Btn.setText(ques.option1);
                    option2Btn.setText(ques.option2);
                    option3Btn.setText(ques.option3);
                    option4Btn.setText(ques.option4);
                    correctAnswer = ques.answer;
                    Log.d("DATA", "data found");
                    break;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(QuizActivity.this, "Retrieval failed. Check Internet Connection.", Toast.LENGTH_SHORT).show();
            }
        });

        quizGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                checkId = checkedId;
            }
        });

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizQuestion.setVisibility(View.VISIBLE);
                nextQuestionBtn.setVisibility(View.VISIBLE);
                submitQuestionBtn.setVisibility(View.VISIBLE);
                quizGroup.setVisibility(View.VISIBLE);
                startQuiz.setVisibility(View.INVISIBLE);
                startQuiz.setClickable(false);
            }
        });
        submitQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (checkId) {
                    case R.id.quizOption1:
                        markedAnswer = "A";
                        break;
                    case R.id.quizOption2:
                        markedAnswer = "B";
                        break;
                    case R.id.quizOption3:
                        markedAnswer = "C";
                        break;
                    case R.id.quizOption4:
                        markedAnswer = "D";
                        break;
                    default:
                        break;
                }
                if (correctAnswer.equals(markedAnswer)) {
                    increaseCorrect();
                    Toast.makeText(QuizActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    Toast.makeText(QuizActivity.this, "Correct Answers:"+correct, Toast.LENGTH_SHORT).show();
                }
            }
        });
        nextQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(question.hasNext()) {
                    Question ques = question.next().getValue(Question.class);
                    quizQuestion.setText(ques.question);
                    option1Btn.setText(ques.option1);
                    option2Btn.setText(ques.option2);
                    option3Btn.setText(ques.option3);
                    option4Btn.setText(ques.option4);
                    correctAnswer = ques.answer;
                }
            }
        });
    }
    private static void increaseCorrect(){
         correct=correct+1;
    }
}
