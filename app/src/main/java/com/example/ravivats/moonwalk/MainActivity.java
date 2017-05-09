package com.example.ravivats.moonwalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void fnGallery(View v)
    {Intent my_intent = new Intent(this,GalleryActivity.class);
        String s="Welcome to NASA Image Gallery";
        my_intent.putExtra("name",s);
        startActivity(my_intent);
    }
    public void fnLocation(View v)
    {Intent my_intent1 = new Intent(this,LocationActivity.class);
        String s="Welcome to Location Tracking page";
        my_intent1.putExtra("name",s);
        startActivity(my_intent1);
    }
    public void fnMoon(View v)
    {Intent my_intent = new Intent(this,DataActivity.class);
        startActivity(my_intent);
    }
    public void fnQuiz(View v)
    {Intent my_intent = new Intent(this,QuizActivity.class);
        String s="Welcome to this Short Quiz.\n" +
                "Test your knowledge by answering these questions. ";
        my_intent.putExtra("name",s);
        startActivity(my_intent);
    }
    public void fnSettings(View v)
    {Intent my_intent = new Intent(this,SettingsActivity.class);
        startActivity(my_intent);
    }
    public void fnAbout(View v)
    {Intent my_intent = new Intent(this,AboutActivity.class);
        startActivity(my_intent);
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
