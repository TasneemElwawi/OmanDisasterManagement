package com.tasneem.omandisastermanagement.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;
import com.tasneem.omandisastermanagement.models.Admin;
import com.tasneem.omandisastermanagement.models.User;

public class VolunteerProfile extends AppCompatActivity {

    TextView nameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_profile);

        String email = getIntent().getStringExtra("Email");

        TextView emailTV = findViewById(R.id.Email2);
        emailTV.setText(email);
//        nameTV = findViewById(R.id.Name2);


// show help notification ..
        ImageButton notifications = findViewById(R.id.notificationButton);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(), EmergencyNotifications.class);
                    startActivity(i);
            }
        });



// send to add cases ..
        Button addCASE = findViewById(R.id.request);
        addCASE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddCase.class);
                startActivity(i);
            }
        });


// go to Show cases activity ..
        Button showCase = findViewById(R.id.sendNotification);
        showCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ShowCases.class);
                startActivity(i);
            }
        });


        // log out from firebase  ..
        Button log_out = findViewById(R.id.logOut);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthFirebase authFirebase = AuthFirebase.getInstance();
                authFirebase.signOut(getApplicationContext());

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), AllUsers.class);
        startActivity(i);
    }
}