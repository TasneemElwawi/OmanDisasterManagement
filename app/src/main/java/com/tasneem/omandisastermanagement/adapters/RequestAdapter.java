package com.tasneem.omandisastermanagement.adapters;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tasneem.omandisastermanagement.Activities.AddNotification;
import com.tasneem.omandisastermanagement.Activities.ForgetPassword;
import com.tasneem.omandisastermanagement.Activities.ShowNotifications;
import com.tasneem.omandisastermanagement.Activities.requestAccepted;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;
import com.tasneem.omandisastermanagement.models.User;
import com.tasneem.omandisastermanagement.models.VolunteerRequest;
import com.tasneem.omandisastermanagement.models.volunteer;
import com.tasneem.omandisastermanagement.viewHolders.RequestHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import static com.tasneem.omandisastermanagement.Activities.AddNotification.Notification;


public class RequestAdapter extends RecyclerView.Adapter<RequestHolder> {

    Context context;
    List<VolunteerRequest> requests;
    int layoutItem;

    public RequestAdapter(Context context, List<VolunteerRequest> requests, int layoutItem) {
        this.context = context;
        this.requests = requests;
        this.layoutItem = layoutItem;
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutItem, parent, false);
        RequestHolder vh = new RequestHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        final VolunteerRequest sItem = requests.get(position);
        holder.emailTV.setText(sItem.getEmail());
        holder.skillsTV.setText(sItem.getSkills());
        holder.carTV.setText(sItem.getCarType());
        holder.jobTV.setText(sItem.getWork());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                AuthFirebase authFirebase = AuthFirebase.getInstance();
                authFirebase.UpdateStatus(context ,sItem.getEmail() ,"ACCEPTED" );
//                sItem.setStatus("ACCEPTED");
                requests.remove(position);
//                notifyDataSetChanged();

                // creating Request object ..
                volunteer volunteer = new volunteer(sItem.getFullName() ,sItem.getEmail(),sItem.getPhone(), sItem.getWork(), sItem.getCarType(), sItem.getSkills() );
                authFirebase.addVolunteer(volunteer);
//              Toast.makeText(context, "YOU ACCEPT THE REQUEST", Toast.LENGTH_SHORT).show();

                Toast.makeText(context, "YOU ACCEPT THE REQUEST", Toast.LENGTH_SHORT).show();
                Notification(context,"Volunteer Request ", "congrats your request has been accepted!");
//
//                Intent i = new Intent(context , requestAccepted.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                AuthFirebase authFirebase = AuthFirebase.getInstance();
                authFirebase.UpdateStatus(context, sItem.getEmail() ,"Canceled" );
//                sItem.setStatus("Canceled");
                requests.remove(position);
//                notifyDataSetChanged();
                Toast.makeText(context, "YOU CANCEL THE REQUEST", Toast.LENGTH_SHORT).show();
                Notification(context,"Volunteer Request ", "Unfortunately your request has been canceled!");


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

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

}