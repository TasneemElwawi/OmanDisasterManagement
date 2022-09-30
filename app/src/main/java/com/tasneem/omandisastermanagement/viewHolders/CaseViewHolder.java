package com.tasneem.omandisastermanagement.viewHolders;

import android.view.View;
import android.widget.TextView;

import com.tasneem.omandisastermanagement.R;

import androidx.recyclerview.widget.RecyclerView;

public class CaseViewHolder extends RecyclerView.ViewHolder{

    public TextView idTv , volunteerTV , roadDescriptionTV , familyName , address , criticalTV , missedNumber , provideTV, unPaidTV ;

    public CaseViewHolder(View itemView) {
        super(itemView);
        idTv = itemView.findViewById(R.id.IDtv);
        familyName = itemView.findViewById(R.id.familyName);
        address = itemView.findViewById(R.id.addressTV);
        volunteerTV = itemView.findViewById(R.id.volunteerNamee);
        criticalTV = itemView.findViewById(R.id.criticalTV);
        missedNumber = itemView.findViewById(R.id.missedTV);
        provideTV = itemView.findViewById(R.id.providedTV);
        unPaidTV = itemView.findViewById(R.id.unPaidTV);
        roadDescriptionTV = itemView.findViewById(R.id.roadDescriptTV);

    }

}
