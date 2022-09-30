package com.tasneem.omandisastermanagement.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;
import com.tasneem.omandisastermanagement.models.Video;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class AllUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        checkLocationPermissions(getApplicationContext());
        checkPermissions(getApplicationContext());
      ImageButton volunteerBtn = findViewById(R.id.volunteerBtn);
      ImageButton userBtn = findViewById(R.id.userBtn);
      ImageButton adminBtn = findViewById(R.id.adminBtn);

      // go to volunteer create account ..
        volunteerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() ,VolunteerLogIn.class);
                i.putExtra("TYPE", "Volunteer");

                startActivity(i);
            }
        });

        // go to USER create account ..
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , UserLogIn.class);
                i.putExtra("TYPE", "User");

                startActivity(i);
            }
        });


        // go to Admin create account ..
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , AdminLogIn.class);
                i.putExtra("TYPE", "Admin");

                startActivity(i);
            }
        });

    }

    // get location permissions..
    public void checkLocationPermissions(Context context){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Permission is not granted
            Toast.makeText(context, "No location Permissions", Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions( this,
                    new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    100);
        }

    }

    // get location permissions..
    public void checkPermissions(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Toast.makeText(context, "No CALL Permissions", Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    100);
        }

        // add Videos to db ..
        AuthFirebase authFirebase = AuthFirebase.getInstance();
        Video v = new Video("enter video title"
                ,"Enter video url "
                ,"enter video image uel " );

        Video v1 = new Video("enter video title"
                ,"Enter video url "
                ,"enter video image uel " );
//
//        authFirebase.addVideos(v);
//        authFirebase.addVideos(v1);
    }
}