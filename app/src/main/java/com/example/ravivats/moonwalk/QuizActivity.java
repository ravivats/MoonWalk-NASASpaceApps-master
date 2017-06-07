package com.example.ravivats.moonwalk;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Iterator;


public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity" ;
    private static int correct=0;
    private DatabaseReference mDatabase;
    Iterable<DataSnapshot> questions;
    Iterator<DataSnapshot> question;
    TextView quizQuestion;
    RadioButton option1Btn,option2Btn,option3Btn,option4Btn;
    Button submitQuestionBtn,startQuiz;
    RadioGroup quizGroup;
    String correctAnswer,markedAnswer;
    int checkId=9999;
    ArrayList<String> answerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        String code = getIntent().getStringExtra("name");
        Toast.makeText(this, code, Toast.LENGTH_LONG).show();
        quizQuestion = (TextView) findViewById(R.id.quizQuestion);
        option1Btn = (RadioButton) findViewById(R.id.quizOption1);
        answerInfo = new ArrayList<>();
        option2Btn = (RadioButton) findViewById(R.id.quizOption2);
        option3Btn = (RadioButton) findViewById(R.id.quizOption3);
        option4Btn = (RadioButton) findViewById(R.id.quizOption4);
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
                    case R.id.quizOption1: markedAnswer = "A";
                        break;
                    case R.id.quizOption2: markedAnswer = "B";
                        break;
                    case R.id.quizOption3: markedAnswer = "C";
                        break;
                    case R.id.quizOption4: markedAnswer = "D";
                        break;
                    default:
                        break;
                }
                if (correctAnswer.equals(markedAnswer)) {
                    increaseCorrect();
                }
                answerInfo.add("Your Ans: " +markedAnswer+"  Correct Ans: " +correctAnswer);
                quizGroup.clearCheck();
                nextQuestion();
            }
        });
    }

    private void nextQuestion(){
        if(question.hasNext()) {
            Question ques = question.next().getValue(Question.class);
            quizQuestion.setText(ques.question);
            option1Btn.setText(ques.option1);
            option2Btn.setText(ques.option2);
            option3Btn.setText(ques.option3);
            option4Btn.setText(ques.option4);
            correctAnswer = ques.answer;
        }
        else{
            Toast.makeText(this, "Quiz Finished!", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(QuizActivity.this,QuizResult.class);
            i.putExtra("answerInfo",answerInfo);
            i.putExtra("score",correct);
            correct=0;
            startActivity(i);
        }
    }
    private static void increaseCorrect(){
         correct=correct+1;
    }
}
