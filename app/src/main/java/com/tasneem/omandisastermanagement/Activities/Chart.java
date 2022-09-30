package com.tasneem.omandisastermanagement.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.adapters.CaseAdapter;
import com.tasneem.omandisastermanagement.models.Case;
import com.tasneem.omandisastermanagement.models.User;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class Chart extends AppCompatActivity {
    TextView tvCases,tvMissed,tvCritical,tvInjuries , tvDeaths;
    PieChart pieChart;

    int missedNumber;
    int criticalNumber;
    int injuriesNumber;
    int TotalDeath ;
    int caseNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

    // get CASE NUMBER ..
        caseNumber = getIntent().getIntExtra("Case Number" , 1);
        criticalNumber = getIntent().getIntExtra("Critical Number" , 1);
        injuriesNumber = getIntent().getIntExtra("Injuries Number" , 1);
        TotalDeath = getIntent().getIntExtra("Death Number" , 1);
        missedNumber = getIntent().getIntExtra("Missed Number" , 1);
        Toast.makeText(getApplicationContext(), "CASE: "+ caseNumber, Toast.LENGTH_SHORT).show();


        pieChart = findViewById(R.id.piechart);
        tvCases = findViewById(R.id.tvCases);
        tvMissed = findViewById(R.id.tvMissed);
        tvCritical = findViewById(R.id.tvCritical);
        tvInjuries = findViewById(R.id.tvInjuries);
        tvDeaths = findViewById(R.id.tvDeaths);

        tvCases.setText(caseNumber+" ");
        tvMissed.setText(missedNumber+" ");
        tvCritical.setText(criticalNumber+" ");
        tvDeaths.setText(TotalDeath+" ");
        tvInjuries.setText(injuriesNumber+" ");

        // add data to chart ..
        pieChart.addPieSlice(new PieModel("Injuries", injuriesNumber, Color.parseColor("#08600B")));
        pieChart.addPieSlice(new PieModel("Missed", missedNumber, Color.parseColor("#EC82A6")));
        pieChart.addPieSlice(new PieModel("Deaths", TotalDeath, Color.parseColor("#CF1C19")));
        pieChart.addPieSlice(new PieModel("Critical", criticalNumber, Color.parseColor("#FFC107")));
        pieChart.startAnimation();






    }
}