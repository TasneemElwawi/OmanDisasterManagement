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
import com.tasneem.omandisastermanagement.adapters.EmergencyNotAdapter;
import com.tasneem.omandisastermanagement.models.WarningNot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EmergencyNotifications extends AppCompatActivity {

    DatabaseReference reference;
    EmergencyNotAdapter adapter;
    ArrayList<WarningNot> notes = new ArrayList<>();
    RecyclerView rec;
    ProgressBar progressBar;
    Date noteDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_notifications);
        progressBar = findViewById(R.id.pBar);
// set list display ..
        rec = findViewById(R.id.cList);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(llm);

        registerForContextMenu(rec);

        reference = FirebaseDatabase.getInstance().getReference().child("Emergency_Notifications");

        rec.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        setRecycler();


    }

    WarningNot n;
    // read notes from database and display it in recycler view
    private void setRecycler() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final WarningNot note = snapshot.getValue(WarningNot.class);
                    assert note != null;
                    String difTime = printDifference(note.getTime());
//                    Toast.makeText(getContext(), "diff "+ difTime, Toast.LENGTH_SHORT).show();

                    n = new WarningNot(note.getUserName(),note.getEmail(), note.getLat_desc(), note.getLong_desc(), difTime);
                        notes.add(n);
                }
                adapter = new EmergencyNotAdapter(getApplicationContext(), notes, R.layout.emergency_not_item);
                rec.setAdapter(adapter);
                rec.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // get notification time ..
    public String printDifference( String noteTime ) {

        DateFormat df=new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
        try {
            noteDate=df.parse(noteTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //system time
        Date currentTime = Calendar.getInstance().getTime();

        long diff=(currentTime.getTime() - noteDate.getTime())/1000;

        //for months
        Calendar calObj = Calendar.getInstance();
        calObj.setTime(noteDate);
        int m=calObj.get(Calendar.MONTH);

        Calendar calObjNow = Calendar.getInstance();
        calObj.setTime(currentTime);
        int mNow=calObj.get(Calendar.MONTH);

        String disTime="..";
        if(diff<15) {
            disTime="\nJust Now";
        } else if(diff<60)
        {
            disTime="\n"+diff+" seconds ago";
        } else if(diff<3600) // until 1 hr
        {
            long temp=diff/60;
            if(temp==1)
                disTime="\n"+temp+" min ago";
            else
                disTime="\n"+temp+" mins ago";
        } else if(diff<(24*3600)) // until 24 hrs
        {
            long temp=diff/3600;
            if(temp==1)
                disTime="\n"+temp+" hr ago";
            else
                disTime="\n"+temp+" hrs ago";
        } else if(diff<(24*3600*7)) //until 7 days
        {
            long temp=diff/(3600*24);
            if (temp==1)
                disTime="\nyesterday";
            else
                disTime="\n"+temp+" days ago";
        } else if(diff<((24*3600*365))) // no. of  months.. until 1 yr
        {
            //long temp=diff/(3600*24;
            if(diff<=1) {
                if(diff<1) {
                    long weeks=diff/7;
                    if(weeks<1)
                        disTime="last week";
                    else
                        disTime=weeks+" weeks ago";
                } else
                    disTime="1 month ago";
            } else {
                int diffMonth=mNow-m;
                disTime="\n"+diffMonth+" months ago";
            }

        }
        else
        {
            disTime="\n"+noteTime;
        }

        return disTime;
    }
}