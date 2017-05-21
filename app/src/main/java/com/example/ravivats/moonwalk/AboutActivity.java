package com.example.ravivats.moonwalk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {
    ListView membersListView;
    String[] mailIds={"vats.ravi96@gmail.com","turrentrock@gmail.com","vishaldhull007@gmail.com"};
    String[] addresses=new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        membersListView = (ListView)findViewById(R.id.membersListView);
        membersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addresses[0]=mailIds[position];
                composeEmail(addresses,"Query regarding MoonWalk App");
            }
        });
        ArrayList<String> ai = new ArrayList<>();
        ai.add("Ravi Vats     USN:1MS14CS100");
        ai.add("Satya Teja    USN:1MS14CS110");
        ai.add("Vishal Dhull  USN:1MS14CS124");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ai);
        membersListView.setAdapter(adapter);
    }
    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only option1 apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
