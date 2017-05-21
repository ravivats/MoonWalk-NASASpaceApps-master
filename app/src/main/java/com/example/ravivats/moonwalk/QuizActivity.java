package com.example.ravivats.moonwalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        String code = getIntent().getStringExtra("name");
        Toast.makeText(this, code, Toast.LENGTH_LONG).show();
    }
    public void submit(View v)
    {   int a=0;
        EditText s1=(EditText) findViewById(R.id.ans1);
        EditText s2=(EditText) findViewById(R.id.ans2);
        EditText s3=(EditText) findViewById(R.id.ans3);
        EditText s4=(EditText) findViewById(R.id.ans4);
        EditText s5=(EditText) findViewById(R.id.ans5);
        String ss1= String.valueOf(s1.getText());
        String ss2= String.valueOf(s2.getText());
        String ss3= String.valueOf(s3.getText());
        String ss4= String.valueOf(s4.getText());
        String ss5= String.valueOf(s5.getText());
        if(ss1.equals("B") || ss1.equals("b")){a=a+1;}
        if(ss2.equals("A") || ss2.equals("a")){a=a+1;}
        if(ss3.equals("C") || ss3.equals("c")){a=a+1;}
        if(ss4.equals("D") || ss4.equals("d")){a=a+1;}
        if(ss5.equals("A") || ss5.equals("a")){a=a+1;}
        String score="Your score is "+ a +"/5";
        Toast.makeText(this,score,Toast.LENGTH_LONG).show();
    }
}
