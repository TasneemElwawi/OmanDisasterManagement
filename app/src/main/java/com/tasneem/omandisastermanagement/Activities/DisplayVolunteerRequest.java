package com.tasneem.omandisastermanagement.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.adapters.RequestAdapter;
import com.tasneem.omandisastermanagement.models.VolunteerRequest;

import java.util.ArrayList;

public class DisplayVolunteerRequest extends AppCompatActivity {

    RecyclerView rec;
    ProgressBar progressBar;
    ArrayList<VolunteerRequest> requests = new ArrayList<>();
    RequestAdapter adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_volunteer_request);

        // set recycler
        rec = findViewById(R.id.sList);
        progressBar = findViewById(R.id.pBar);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(llm);

        reference = FirebaseDatabase.getInstance().getReference("Requests");

        registerForContextMenu(rec);


        setRecycler();

    }

    VolunteerRequest s ;
    // read volunteer request from database and display it in recycler view
    private void setRecycler() {
        Query emailQuery = reference.orderByChild("status").equalTo("pending");

        emailQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    VolunteerRequest volunteerRequest = child.getValue(VolunteerRequest.class);

                    assert volunteerRequest != null;
                    s = new VolunteerRequest(volunteerRequest.getEmail(), volunteerRequest.getWork(), volunteerRequest.getCarType(), volunteerRequest.getSkills(), volunteerRequest.getStatus(), volunteerRequest.getTime());
                    requests.add(s);
                }

                adapter = new RequestAdapter(getApplicationContext(), requests, R.layout.request_layout);
                rec.setAdapter(adapter);
                rec.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}