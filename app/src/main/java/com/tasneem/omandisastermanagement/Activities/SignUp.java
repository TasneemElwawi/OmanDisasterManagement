package com.tasneem.omandisastermanagement.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;
import com.tasneem.omandisastermanagement.models.User;

import java.util.HashMap;
import java.util.Objects;

public class SignUp extends AppCompatActivity {

    private TextInputEditText email;
    private TextInputEditText name , age;
    Spinner spinner;
    private TextInputEditText password, phoneNumber;
    double lat;
    double lon;
    Button btn_signup;
    String clickedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spinner = findViewById(R.id.carSpinner);
        email = findViewById(R.id.titleTV);
        name = findViewById(R.id.name_TextInputEditText);
        age = findViewById(R.id.fname_TextInputEditText);
        password = findViewById(R.id.contentTV);
        phoneNumber = findViewById(R.id.phoneNumber_TextInputEditText);
        btn_signup = findViewById(R.id.signup);

// get user type ..
            String userType = getIntent().getStringExtra("TYPE");
//        Toast.makeText(getApplicationContext(), "user type is: " + userType, Toast.LENGTH_SHORT).show();

// go to log in screen ..
            Button btn_login = findViewById(R.id.sendNotification);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(), UserLogIn.class);
                    i.putExtra("TYPE", userType);
                    startActivity(i);
                }
            });


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_list, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);//to set default values

                clickedItem = (String) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), clickedItem + " selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

// update user location..

            FusedLocationProviderClient c = LocationServices.getFusedLocationProviderClient(getApplicationContext());

            com.google.android.gms.location.LocationRequest request = com.google.android.gms.location.LocationRequest.create();
            request.setInterval(5000);
            request.setFastestInterval(90000);
            request.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                          return;
            }
            c.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    if (locationResult != null) {
                        Location locate = locationResult.getLastLocation();
                        if (locate != null) {
                            lat = locate.getLatitude();
                            lon = locate.getLongitude();

                            CameraPosition.Builder builder = new CameraPosition.Builder();
                            builder.target(new LatLng(lat, lon));
                            builder.tilt(70);
//                            Toast.makeText(getApplicationContext(), "USER lLOCATION: " + lat + "," + lon, Toast.LENGTH_SHORT).show();
//                            UpdateLocation(lon , lat);

                        }

                    }
                }
            }, null);



// create user Account ..
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthFirebase authFirebase = AuthFirebase.getInstance();
                authFirebase.UserSignup(getApplicationContext(), Objects.requireNonNull(email.getText()).toString(),
                        Objects.requireNonNull(name.getText()).toString(),
                        Objects.requireNonNull(password.getText()).toString() ,
                        Integer.parseInt(Objects.requireNonNull(age.getText()).toString()),
                        clickedItem.toString(),
                        Objects.requireNonNull(phoneNumber.getText()).toString(), lat, lon, userType);

                Toast.makeText(getApplicationContext(), "create User account successfully", Toast.LENGTH_SHORT).show();

            }
        });
        }


    // update user location in database
    String key;

    public void UpdateLocation (double longitude , double latitude ) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Query emailQuery = databaseReference.orderByChild("email").equalTo(email.getText().toString());
        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    User users = snapShot.getValue(User.class);
                    assert users != null;
                    key = snapShot.getKey(); //Key

//                                Toast.makeText(getApplicationContext(), "key ;"+ key, Toast.LENGTH_SHORT).show();

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("long_desc", longitude+"");
                    map.put("lat_desc", latitude+"");

                    databaseReference.child(key).updateChildren(map);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}