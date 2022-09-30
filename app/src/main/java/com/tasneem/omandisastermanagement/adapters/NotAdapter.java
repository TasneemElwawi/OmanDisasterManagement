package com.tasneem.omandisastermanagement.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.models.Note;
import com.tasneem.omandisastermanagement.viewHolders.NotViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


public class NotAdapter extends RecyclerView.Adapter<NotViewHolder> {

    Context context;
    List<Note> items ;
    int layoutItem;

    public NotAdapter(Context context , List<Note> items, int layoutItem){
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
        final Note notItem = items.get(position);
        holder.titleTv.setText(notItem.getTitle());
        holder.detailsTv.setText(notItem.getDetails());
        holder.timeTv.setText(notItem.getTime());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
            // show popup menu on long press ..
                PopupMenu p = new PopupMenu(context, v);
                p.getMenuInflater().inflate(R.menu.popup_menu, p.getMenu());
                p.show();

                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(final MenuItem item) {

                        // dialog in delete item ..
                        AlertDialog.Builder builder =new AlertDialog.Builder(v.getContext());
                        builder.setTitle("warning");
                        builder.setMessage("Do you really want to delete this notice? ");
                        builder.setIcon(R.drawable.ic_baseline_delete_24);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                       //action ( update item and put is delete true..)

                       DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Warning_From_Admin").child(notItem.getId());
                       ref.child("delete").setValue(true);

                         items.remove(position);
                         items.clear();
                         notifyDataSetChanged();

                            }
                        });
                       builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }

                        });
                        AlertDialog d = builder.create();
                        d.setCanceledOnTouchOutside(true);
                        d.show();

                        return true;
                    }
                });


                return true;
            }

        });

    }

}
