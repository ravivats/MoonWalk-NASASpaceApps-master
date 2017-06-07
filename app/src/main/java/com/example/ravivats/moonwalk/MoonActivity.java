package com.example.ravivats.moonwalk;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MoonActivity extends AppCompatActivity {
    ImageView celestialImageView;
    TextView celestialTextView;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moon);
        Resources res = getResources();
        String[] planets = res.getStringArray(R.array.planets_array);
        String[] imageUrls = res.getStringArray(R.array.url_array);
        celestialImageView= (ImageView) findViewById(R.id.celestialImageView);
        celestialTextView =(TextView) findViewById(R.id.celestialTextView);
        position=getIntent().getIntExtra("position",1);
        try {
            Glide.with(this).load(imageUrls[position - 1]).into(celestialImageView);
        }catch(Exception e){
            celestialImageView.setImageResource(R.drawable.moon_bck);
        }
        celestialTextView.setText(planets[position-1]);
    }
}
