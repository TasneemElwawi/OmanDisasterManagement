package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tasneem.omandisastermanagement.R;

public class DonateActivity extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        // set title ..
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Donate");
        setSupportActionBar(toolbar);


        //donate action ..

       Button donateBtn = findViewById(R.id.donatebtn);
       donateBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getApplicationContext() , thankForDonate.class);
               startActivity(i);

           }
       });

    }
}