package com.tasneem.omandisastermanagement.viewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tasneem.omandisastermanagement.R;

import androidx.recyclerview.widget.RecyclerView;

public class RequestHolder extends RecyclerView.ViewHolder{

    public TextView skillsTV;
    public TextView carTV;
    public TextView jobTV;
    public TextView emailTV;
    public Button accept;
    public Button cancel;

    public RequestHolder(View itemView) {
        super(itemView);
        skillsTV = itemView.findViewById(R.id.skillsTV);
        carTV = itemView.findViewById(R.id.carTV);
        jobTV = itemView.findViewById(R.id.jobTV);
        emailTV = itemView.findViewById(R.id.emailTV);
        accept = itemView.findViewById(R.id.acceptBtn);
        cancel = itemView.findViewById(R.id.cancelBtn);

    }

}
