package com.example.ravivats.moonwalk;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FactActivity extends AppCompatActivity {
    TextView explanationTxt,titleTxt,dateTxt;
    ImageView imageView;
    String getURL ="https://api.nasa.gov/planetary/apod?api_key=ZBCHQARaDf75KEY1sEXstfFsR6BRkMf8XHdiFaxd";
    String imageURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact);
        RequestQueue rQueue = Volley.newRequestQueue(FactActivity.this);
        explanationTxt= (TextView) findViewById(R.id.apollo_exp_TextView);
        titleTxt= (TextView) findViewById(R.id.apolloTitleTextView);
        dateTxt=(TextView) findViewById(R.id.apollo_date_TextView);
        imageView=(ImageView)findViewById(R.id.apollo_ImageView);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, getURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            explanationTxt.setText(response.getString("explanation"));
                            titleTxt.setText(response.getString("title"));
                            dateTxt.setText(response.getString("date"));
                            imageURL= response.getString("url");
                            imageLoad();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            explanationTxt.setText("Sample explanation");
                            titleTxt.setText("Sample title");
                            dateTxt.setText("2017-05-08");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        rQueue.add(jsObjRequest);
    }
    void imageLoad()
    {   RequestQueue r1Queue = Volley.newRequestQueue(FactActivity.this);
        ImageRequest imageRequest = new ImageRequest(imageURL,
            new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }, 0, 0, null,
            new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    imageView.setImageResource(R.drawable.solarsys);
                }
            });
        r1Queue.add(imageRequest);


    }
}
