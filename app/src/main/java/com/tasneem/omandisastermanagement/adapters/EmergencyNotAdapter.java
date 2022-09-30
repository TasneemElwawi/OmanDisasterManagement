package com.tasneem.omandisastermanagement.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tasneem.omandisastermanagement.Activities.AddNotification;
import com.tasneem.omandisastermanagement.Activities.ShowUserLocation;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.models.Note;
import com.tasneem.omandisastermanagement.models.WarningNot;
import com.tasneem.omandisastermanagement.viewHolders.NotViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class EmergencyNotAdapter extends RecyclerView.Adapter<NotViewHolder> {

        Context context;
        List<WarningNot> items ;
        int layoutItem;

public EmergencyNotAdapter(Context context , List<WarningNot> items, int layoutItem){
        this.context=context;
        this.items=items;
        this.layoutItem=layoutItem;
        }

@Override
public int getItemCount() {
        return items.size();
        }

@NonNull
@Override
public NotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutItem, parent, false);
        NotViewHolder vh = new NotViewHolder(view);
        return vh;
        }

@Override
public void onBindViewHolder(@NonNull NotViewHolder holder, final int position) {
final WarningNot notItem = items.get(position);
        holder.titleTv.setText(notItem.getUserName());
        holder.detailsTv.setText(notItem.getEmail());
        holder.timeTv.setText(notItem.getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
        // go to map screen and send lat aND LONG
                        Intent i = new Intent(context, ShowUserLocation.class);
                        i.putExtra("lat",notItem.getLat_desc().trim());
                        i.putExtra("long",notItem.getLong_desc().trim());
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                }
        }
        );

        }

        }