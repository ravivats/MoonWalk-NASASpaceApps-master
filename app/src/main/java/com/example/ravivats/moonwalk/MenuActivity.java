package com.example.ravivats.moonwalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "Menu_CardView";

    int[] pictures ={R.drawable.menu_rocket,R.drawable.menu_mercury,R.drawable.menu_venus,R.drawable.menu_earth,R.drawable.menu_moon,R.drawable.menu_mars,R.drawable.menu_jupiter,R.drawable.menu_saturn,R.drawable.menu_uranus,R.drawable.menu_neptune,R.drawable.menu_pluto};
    String[] planets={"NASA: Fact of the day","About Mercury","About Venus","About Earth","About Moon","About Mars","About Jupiter","About Saturn","About Uranus","About Neptune","About Pluto"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                if(position==0){
                    startActivity(new Intent(MenuActivity.this, FactActivity.class));
                } else if(position>0){
                    startActivity(new Intent(MenuActivity.this, MoonActivity.class));
                }

            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index <= 10; index++) {
            DataObject obj = new DataObject(planets[index],
                    "",pictures[index]);
            results.add(index, obj);
        }
        return results;
    }
}

