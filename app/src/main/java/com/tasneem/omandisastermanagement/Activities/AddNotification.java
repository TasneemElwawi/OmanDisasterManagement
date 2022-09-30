package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;
import com.tasneem.omandisastermanagement.models.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AddNotification extends AppCompatActivity {
    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);

        String email = getIntent().getStringExtra("Email");


        TextInputEditText title = findViewById(R.id.titleTV);
        TextInputEditText content = findViewById(R.id.contentTV);
        Button send = findViewById(R.id.sendNotification);

        // get current date ..
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());

// send notifications
           send.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(content.getText().toString().trim().isEmpty()){
                       Toast.makeText(getApplicationContext(), "Enter a warning pleas!", Toast.LENGTH_LONG).show();
                       return;
                   }
                   // send notification..
                   NOTIFICATION_TITLE = "Danger notice!"+ Objects.requireNonNull(title.getText()).toString();
                   NOTIFICATION_MESSAGE = Objects.requireNonNull(content.getText()).toString();

                   // creating note object ..
                   Note note = new Note(NOTIFICATION_TITLE, NOTIFICATION_MESSAGE , false, currentDateAndTime , email);
                   AuthFirebase authFirebase = AuthFirebase.getInstance();
                   authFirebase.addWarning(note);

                   Notification(getApplicationContext(),NOTIFICATION_TITLE, NOTIFICATION_MESSAGE);

                   Toast.makeText(getApplicationContext(), "Notification Send Successfully", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(getApplicationContext(), NotifySendSuccessfully.class);
                   startActivity(i);
               }
           });
    }

    // create Notification ..
    public static void Notification (Context context, String title , String content) {

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

        Intent appI = new Intent(context, ShowNotifications.class);
        appI.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appI, 0);
        builder.setContentIntent(pendingIntent);

        // notificationID allows you to update the notification later on.

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}