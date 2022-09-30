package com.tasneem.omandisastermanagement.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
import com.tasneem.omandisastermanagement.models.Case;
import com.tasneem.omandisastermanagement.models.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdminProfile extends AppCompatActivity {

    TextView nameTV;
    DatabaseReference reference;
    Case cases;
    ArrayList<Case> data = new ArrayList<>();
    int totalDeath = 0;
    int missedNumber = 0;
    int criticalNumber = 0;
    int injuriesNumber = 0;
    int caseNumber;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        getDataToChart ();

            String email = getIntent().getStringExtra("Email");

        constraintLayout = findViewById(R.id.ALL_layout);
        progressBar = findViewById(R.id.pBar);
        TextView emailTV = findViewById(R.id.Email2);
            emailTV.setText(email);
            nameTV = findViewById(R.id.Name2);

            getAdminName(email);

// show help notification ..
            ImageButton notifications = findViewById(R.id.notificationButton);
            notifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    show panic notification..
                    Intent i = new Intent(getApplicationContext(), EmergencyNotifications.class);
                    startActivity(i);
                }
            });


        // send Notifications  ..
        Button sendNotification = findViewById(R.id.sendNotification);
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddNotification.class);
                i.putExtra("Email", email);
                startActivity(i);


            }
        });


// send to show All volunteer request  ..
        Button volunteerRequest = findViewById(R.id.request);
        volunteerRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplayVolunteerRequest.class);
                startActivity(i);
            }
        });


// go to Chart activity ..
            Button chart = findViewById(R.id.chart);
            chart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(), Chart.class);
                    i.putExtra("Case Number", caseNumber);
                    i.putExtra("Missed Number", missedNumber);
                    i.putExtra("Critical Number", criticalNumber);
                    i.putExtra("Injuries Number", injuriesNumber);
                    i.putExtra("Death Number", totalDeath);
                    startActivity(i);
                }
            });


// go to warning activity ..
        Button warning = findViewById(R.id.warning);
        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ShowNotifications.class);
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
// read admin based email ..
        public void getAdminName (String email){
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Admins");

            Query emailQuery = usersRef.orderByChild("email").equalTo(email);
            emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        Admin admins = child.getValue(Admin.class);

                        assert admins != null;
                        nameTV.setText(admins.getName());
//                    Toast.makeText(getApplicationContext(), "id ="+ pId, Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("TAG", error.getMessage()); //Never ignore potential errors!
                }
            });
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            Intent i = new Intent(getApplicationContext(), AllUsers.class);
            startActivity(i);
        }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void getDataToChart () {
        // read data to chart ...
        reference = FirebaseDatabase.getInstance().getReference().child("Cases");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final Case c = snapshot.getValue(Case.class);
                    assert c != null;
                    cases = new Case(c.getCritical_Case_N(), c.getMissing_Number() ,c.getInjuries_Number() , c.getDeath_Number() );
                    data.add(cases);
// get the total >>
                    totalDeath += c.getDeath_Number();
                    missedNumber += c.getMissing_Number();
                    criticalNumber += c.getCritical_Case_N();
                    injuriesNumber += c.getInjuries_Number();

                }
//                Toast.makeText(getApplicationContext(), "total Death " + totalDeath, Toast.LENGTH_SHORT).show();
                caseNumber = data.size();
//                Toast.makeText(getApplicationContext(), "cases " + caseNumber, Toast.LENGTH_SHORT).show();
                constraintLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
//                Toast.makeText(getApplicationContext(), "missed" + missedNumber, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "critical" + criticalNumber, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "injuries" + injuriesNumber, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}