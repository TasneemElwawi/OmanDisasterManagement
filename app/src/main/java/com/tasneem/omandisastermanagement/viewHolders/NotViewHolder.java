package com.tasneem.omandisastermanagement.viewHolders;

import android.view.View;
import android.widget.TextView;

import com.tasneem.omandisastermanagement.R;

import androidx.recyclerview.widget.RecyclerView;

public class NotViewHolder extends RecyclerView.ViewHolder{

    public TextView titleTv , detailsTv , timeTv;

    public NotViewHolder(View itemView) {
        super(itemView);
        titleTv = itemView.findViewById(R.id.name);
        detailsTv = itemView.findViewById(R.id.detailsTV);
        timeTv = itemView.findViewById(R.id.timeTV);

    }

}
