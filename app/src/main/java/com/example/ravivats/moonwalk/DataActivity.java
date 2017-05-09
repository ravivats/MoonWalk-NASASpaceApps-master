package com.example.ravivats.moonwalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DataActivity extends AppCompatActivity {
    ImageButton moonButton, apolloButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        moonButton =(ImageButton) findViewById(R.id.moonBtn);
        apolloButton= (ImageButton) findViewById(R.id.apolloBtn);
        moonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataActivity.this, MoonActivity.class));
            }
        });
        apolloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataActivity.this, ApolloActivity.class));
            }
        });

    }
}
