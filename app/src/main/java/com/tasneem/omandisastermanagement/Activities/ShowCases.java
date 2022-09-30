package com.tasneem.omandisastermanagement.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.adapters.CaseAdapter;
import com.tasneem.omandisastermanagement.adapters.NotAdapter;
import com.tasneem.omandisastermanagement.models.Case;
import com.tasneem.omandisastermanagement.models.Note;

import java.util.ArrayList;
import java.util.Date;

public class ShowCases extends AppCompatActivity {

    DatabaseReference reference;
    CaseAdapter adapter;
    ArrayList<Case> data = new ArrayList<>();
    RecyclerView rec;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cases);

        progressBar = findViewById(R.id.pBar);
// set list display ..
        rec = findViewById(R.id.cList);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(llm);

        registerForContextMenu(rec);

        reference = FirebaseDatabase.getInstance().getReference().child("Cases");

        rec.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        setRecycler();


    }
    Case cases;
    // read notes from database and display it in recycler view
    private void setRecycler() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final Case c = snapshot.getValue(Case.class);
                    assert c != null;


                    cases = new Case(c.getFamilyName(), c.getAddress(), c.getRoad_Description() , c.getCritical_Case_N(), c.getMissing_Number() , c.getProvidedAid() , c.getVolunteerName(),c.getUn_paid_aid() );
                    data.add(cases);
                }
                adapter = new CaseAdapter(getApplicationContext(), data, R.layout.case_item);
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