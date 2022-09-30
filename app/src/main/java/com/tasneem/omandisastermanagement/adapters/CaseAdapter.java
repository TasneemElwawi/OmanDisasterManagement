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
import com.tasneem.omandisastermanagement.models.Case;
import com.tasneem.omandisastermanagement.viewHolders.CaseViewHolder;
import com.tasneem.omandisastermanagement.viewHolders.NotViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class CaseAdapter extends RecyclerView.Adapter<CaseViewHolder> {

        Context context;
        List<Case> items ;
        int layoutItem;

        public CaseAdapter(Context context , List<Case> items, int layoutItem){
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
        public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutItem, parent, false);
            CaseViewHolder vh = new CaseViewHolder(view);
            return vh;
        }
int id = 0;
        @Override
        public void onBindViewHolder(@NonNull CaseViewHolder holder, final int position) {
            final Case notItem = items.get(position);
            holder.idTv.setText(++id +" ");
            holder.volunteerTV.setText(notItem.getVolunteerName());
            holder.address.setText(notItem.getAddress());
            holder.roadDescriptionTV.setText(notItem.getRoad_Description());
            holder.criticalTV.setText(notItem.getCritical_Case_N()+" ");
            holder.missedNumber.setText(notItem.getMissing_Number()+" ");
            holder.provideTV.setText(notItem.getProvidedAid());
            holder.unPaidTV.setText(notItem.getUn_paid_aid());
            holder.familyName.setText(notItem.getFamilyName());

        }
}
