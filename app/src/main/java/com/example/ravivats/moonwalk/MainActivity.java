package com.example.ravivats.moonwalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton galleryBtn,locationBtn, menuChoiceBtn,quizBtn, liveStreamBtn,aboutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        galleryBtn =(ImageButton) findViewById(R.id.imageGallery);
        locationBtn =(ImageButton) findViewById(R.id.imageLocation);
        menuChoiceBtn =(ImageButton) findViewById(R.id.imageMoon);
        quizBtn =(ImageButton) findViewById(R.id.imageQuiz);
        liveStreamBtn =(ImageButton) findViewById(R.id.imageSettings);
        aboutBtn =(ImageButton) findViewById(R.id.imageAbout);

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my_intent = new Intent(MainActivity.this,GalleryActivity.class);
                String s="Welcome to NASA Image Gallery";
                my_intent.putExtra("name",s);
                startActivity(my_intent);
            }
        });
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my_intent = new Intent(MainActivity.this,LocationActivity.class);
                String s="Welcome to Location Tracking page";
                my_intent.putExtra("name",s);
                startActivity(my_intent);
            }
        });
        menuChoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my_intent = new Intent(MainActivity.this,QuizActivity.class);
                String s="Welcome to the Quiz section";
                my_intent.putExtra("name",s);
                startActivity(my_intent);
            }
        });
        liveStreamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LiveStreamActivity.class));
            }
        });
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
