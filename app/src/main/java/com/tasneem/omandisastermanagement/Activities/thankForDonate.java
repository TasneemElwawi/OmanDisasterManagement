package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.tasneem.omandisastermanagement.R;

public class thankForDonate extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_for_donate);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Thank for Donate");
        setSupportActionBar(toolbar);

    }
}