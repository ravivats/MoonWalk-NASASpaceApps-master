package com.example.ravivats.moonwalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.common.util.concurrent.ExecutionError;

public class MoonActivity extends AppCompatActivity {
    String[] imageUrls= {"http://solarviews.com/thumb/vss/VSS00093.jpg","http://solarviews.com/thumb/venus/venus2.jpg",
            "http://solarviews.com/thumb/earth/bluemarblewest.jpg", "http://nineplanets.org/images/themoon.jpg",
            "http://solarviews.com/images/marssystem.jpg","https://upload.wikimedia.org/wikipedia/commons/2/2b/Jupiter_and_its_shrunken_Great_Red_Spot.jpg",
            "http://www.cool2bkids.com/wp-content/uploads/2014/05/Saturn-Images.jpg","http://www.crystalinks.com/uranus2.jpg",
            "http://www.hotelroomsearch.net/im/hotels/it/nettuno-1.jpg","http://solarviews.com/thumb/pia/PIA19708.jpg" };
    ImageView celestialImageView;
    TextView celestialTextView;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moon);
        celestialImageView= (ImageView) findViewById(R.id.celestialImageView);
        celestialTextView =(TextView) findViewById(R.id.celestialTextView);
        position=getIntent().getIntExtra("position",1);
        try {
            Glide.with(this).load(imageUrls[position - 1]).into(celestialImageView);
        }catch(Exception e){
            celestialImageView.setImageResource(R.drawable.moon_bck);
        }
        celestialTextView.setText("");
    }
}
