package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;
import com.tasneem.omandisastermanagement.models.VolunteerRequest;
import com.tasneem.omandisastermanagement.models.volunteer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.tasneem.omandisastermanagement.Activities.AddNotification.Notification;

public class ToBeVolunteer extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner , jobSpinner , skillsSpinner;
    String clickedItem , jobSelections , skillsSelection ,email ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_be_volunteer);
// set title ..
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Request to be volunteer");
        setSupportActionBar(toolbar);

        email = getIntent().getStringExtra("Email");

        spinner = findViewById(R.id.carSpinner);
        jobSpinner = findViewById(R.id.carSpinner2);
        skillsSpinner = findViewById(R.id.skills);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cars_list, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.jobs_list, android.R.layout.simple_spinner_item);

ArrayAdapter<CharSequence> adapterSkills = ArrayAdapter.createFromResource(this,
                R.array.skills_list, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSkills.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        jobSpinner.setAdapter(adapter1);
        skillsSpinner.setAdapter(adapterSkills);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);//to set default values

                clickedItem = (String) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), clickedItem + "car selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        jobSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jobSpinner.setSelection(position);//to set default values

                jobSelections = (String) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), jobSelections + "job selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        skillsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skillsSpinner.setSelection(position);//to set default values

                skillsSelection = (String) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), skillsSelection + "skill selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // get current date ..
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());   // 24/8/2020 10:46:03

        AuthFirebase authFirebase = AuthFirebase.getInstance();

        Button sentRequest = findViewById(R.id.addVOLUNTEERRequest);
       sentRequest.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               VolunteerRequest vRequest;
               // creating Request object ..
               if(jobSelections.equalsIgnoreCase("Doctor") & clickedItem.equalsIgnoreCase("4WD") ){
                    vRequest = new VolunteerRequest(email , jobSelections, clickedItem, skillsSelection , "ACCEPTED" ,currentDateAndTime );

                   volunteer volunteer = new volunteer(email, jobSelections, clickedItem, skillsSelection );
                   authFirebase.addVolunteer(volunteer);

                   Toast.makeText(getApplicationContext(), "YOU ACCEPT THE REQUEST", Toast.LENGTH_SHORT).show();
                   Notification(getApplicationContext(),"Volunteer Request ", "congrats your request has been accepted!");

                   Intent i = new Intent(getApplicationContext() , requestAccepted.class);
                   startActivity(i);

               }else {
                    vRequest = new VolunteerRequest(email, jobSelections, clickedItem, skillsSelection, "pending", currentDateAndTime);
               }
               authFirebase.addRequest(vRequest);



           }
       });

    }
}