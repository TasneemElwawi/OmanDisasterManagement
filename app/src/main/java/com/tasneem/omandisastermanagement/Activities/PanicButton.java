package com.tasneem.omandisastermanagement.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import at.markushi.ui.CircleButton;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;
import com.tasneem.omandisastermanagement.models.Video;
import com.tasneem.omandisastermanagement.models.WarningNot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class PanicButton extends AppCompatActivity {

    Toolbar toolbar;
    String email, name;
    private CircleButton panicButton;

    double lon, lat;
    private static final String TAG = "MAIN";
    private boolean isActivated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_panic);
        getLocation();

        // get email ..
        email = getIntent().getStringExtra("Email");
        name = getIntent().getStringExtra("fullName");
        // get userName ..
        AuthFirebase authFirebase = AuthFirebase.getInstance();
        String userName = authFirebase.getUserData(getApplicationContext(), email);

        // set title ..
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Emergence Notifications");
        setSupportActionBar(toolbar);



        // get current date ..
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());


        // initialize panic button, set listener to control bluetooth
        panicButton = findViewById(R.id.panicButton);
        panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                if (isActivated) {
                    Toast.makeText(getApplicationContext(), "deactivate", Toast.LENGTH_SHORT).show();
                    panicButton.setColor(ResourcesCompat.getColor(getResources(), R.color.colorDeactivated, null));
                    //send to admin user data and location

                } else {
                    Toast.makeText(getApplicationContext(), "activate", Toast.LENGTH_SHORT).show();
                    panicButton.setColor(ResourcesCompat.getColor(getResources(), R.color.colorActivated, null));
                    Toast.makeText(getApplicationContext(), "n" + name, Toast.LENGTH_SHORT).show();
                    WarningNot w = new WarningNot(name, email, String.valueOf(lat), String.valueOf(lon) , currentDateAndTime );
                    authFirebase.AddPanicWarningNot(w);
                    Toast.makeText(getApplicationContext(), "We send your emergency notifications to the volunteer to help you! ", Toast.LENGTH_SHORT).show();


                    String NOTIFICATION_TITLE = "Emergency Notice";
                    String NOTIFICATION_MESSAGE = "Help me! I'm in danger..";

                    SendNotification(getApplicationContext() , NOTIFICATION_TITLE,NOTIFICATION_MESSAGE );
//                        Toast.makeText(getApplicationContext(), "user"+ userName, Toast.LENGTH_SHORT).show();
                }
                isActivated = !isActivated;


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.request_tobe_volunteer) {
//            Toast.makeText(getApplicationContext(), "REQUEST TO BE VOLUNTEER", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ToBeVolunteer.class);
            i.putExtra("Email", email);
            startActivity(i);
            return true;
        } else if (id == R.id.notifications) {
//            Toast.makeText(getApplicationContext(), "Notifications", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ShowNotifications.class);
            startActivity(i);
            return true;
        } else if (id == R.id.call_us) {
//            Toast.makeText(getApplicationContext(), "Call", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), CallPolice.class);
            startActivity(i);
            return true;
        } else if (id == R.id.videos) {
//                Toast.makeText(getApplicationContext(), "video", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), VideoActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.send_warning) {
            Intent i = new Intent(getApplicationContext(), AddNotification.class);
            startActivity(i);
            return true;
        } else if (id == R.id.Share) {
            Toast.makeText(getApplicationContext(), "Share App to your friends", Toast.LENGTH_LONG).show();
            shareApp();
            return true;
        } else if (id == R.id.donate) {
//                Toast.makeText(getApplicationContext(), "Donate", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), DonateActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.logout) {
            Toast.makeText(getApplicationContext(), "Sign out", Toast.LENGTH_LONG).show();
            AuthFirebase authFirebase = AuthFirebase.getInstance();
            authFirebase.signOut(getApplicationContext());
            Intent i = new Intent(getApplicationContext(), AllUsers.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
// SHARE APP..
    private void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        Intent.createChooser(sendIntent, "Share via");
        startActivity(sendIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), AllUsers.class);
        startActivity(i);
    }

    // update user location..
    public void getLocation() {
        FusedLocationProviderClient c = LocationServices.getFusedLocationProviderClient(getApplicationContext());

        com.google.android.gms.location.LocationRequest request = com.google.android.gms.location.LocationRequest.create();
        request.setInterval(5000);
        request.setFastestInterval(5 * 1000);
        request.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        c.requestLocationUpdates(request, new

                LocationCallback() {
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

//                                Toast.makeText(getApplicationContext(), "USER lLOCATION: " + lat + "," + lon, Toast.LENGTH_SHORT).show();
//                            UpdateLocation(lon , lat);

                            }

                        }
                    }
                }, null);

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermissions(getApplicationContext());
    }

    // create Notification ..
    public static void SendNotification (Context context, String title , String content) {

        int NOTIFICATION_ID = 234;
        String CHANNEL_ID = "my_channel_01";

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.notify_icon)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo))
                .setVibrate(new long[]{500, 1500});

        Intent appI = new Intent(context, EmergencyNotifications.class);
        appI.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appI, 0);
        builder.setContentIntent(pendingIntent);

        // notificationID allows you to update the notification later on.

        notificationManager.notify(NOTIFICATION_ID, builder.build());
        ++ NOTIFICATION_ID;

    }


}

