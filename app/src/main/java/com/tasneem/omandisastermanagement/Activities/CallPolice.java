package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.tasneem.omandisastermanagement.R;

public class CallPolice extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_police);

        // set title
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Call");
        setSupportActionBar(toolbar);


       ImageButton ambulanceImg =  findViewById(R.id.amb);
       ImageButton policeImg =  findViewById(R.id.police);
       ImageButton hospitalImg =  findViewById(R.id.hospital);


// call ambulance ..
        ambulanceImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent("24521777");
            }
        });
// call police ..
        policeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent("9999");
            }
        });
// call hospital ..
        hospitalImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent("24 599000");
            }
        });


    }

// call method ..

    private void callIntent(String phone_no){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phone_no));//change the number
        startActivity(callIntent);

    }


}