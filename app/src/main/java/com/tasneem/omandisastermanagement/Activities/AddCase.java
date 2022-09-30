package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;
import com.tasneem.omandisastermanagement.models.Case;
import com.tasneem.omandisastermanagement.models.Note;

import static com.tasneem.omandisastermanagement.models.Case.incrementID;

public class AddCase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);

        EditText volunteerNameTV = findViewById(R.id.volunteerName);
        EditText addressTV = findViewById(R.id.address);
        EditText descriptionTV = findViewById(R.id.descriptTheRoad);
        EditText familyNameTV = findViewById(R.id.fname);
        EditText totalNumber = findViewById(R.id.totalNumb2);
        EditText childrenTV = findViewById(R.id.childrenNum);
        EditText teenagerTV = findViewById(R.id.teenagerNum);
        EditText oldTV = findViewById(R.id.oldNumb);
        EditText criticalCaseTV = findViewById(R.id.criticalCase);
        EditText missedTV = findViewById(R.id.missingNumb2);
        EditText deathTV = findViewById(R.id.deathsNum4);
        EditText injuriesTV = findViewById(R.id.injuriesNum2);
        EditText neededTV = findViewById(R.id.needed);
        EditText providerTV = findViewById(R.id.provided);
        EditText un_paidTV = findViewById(R.id.unpaid_aid);

// add case to database ..
        Button AddCase = findViewById(R.id.AddCase);
        AddCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(volunteerNameTV.getText().toString().trim().isEmpty() || addressTV.getText().toString().trim().isEmpty()
                   || descriptionTV.getText().toString().trim().isEmpty() || familyNameTV.getText().toString().trim().isEmpty()
                        || totalNumber.getText().toString().trim().isEmpty() || childrenTV.getText().toString().trim().isEmpty()
                        || teenagerTV.getText().toString().trim().isEmpty() || oldTV.getText().toString().trim().isEmpty()
                        || criticalCaseTV.getText().toString().trim().isEmpty() || missedTV.getText().toString().trim().isEmpty()
                        || deathTV.getText().toString().trim().isEmpty() || injuriesTV.getText().toString().trim().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Fill all input pleas!", Toast.LENGTH_LONG).show();
                    return;
                }

                Case c = new Case(incrementID, familyNameTV.getText().toString().trim(),volunteerNameTV.getText().toString().trim(),
                        addressTV.getText().toString().trim() , descriptionTV.getText().toString().trim(),
                        Integer.parseInt(totalNumber.getText().toString().trim()), Integer.parseInt(oldTV.getText().toString().trim()),
                        Integer.parseInt(teenagerTV.getText().toString().trim()),Integer.parseInt(childrenTV.getText().toString().trim()),
                        Integer.parseInt(criticalCaseTV.getText().toString().trim()),Integer.parseInt(deathTV.getText().toString().trim()),
                        Integer.parseInt(injuriesTV.getText().toString().trim()),Integer.parseInt(missedTV.getText().toString().trim()),
                        neededTV.getText().toString().trim(),providerTV.getText().toString().trim(),un_paidTV.getText().toString().trim());

                AuthFirebase authFirebase = AuthFirebase.getInstance();
                authFirebase.AddCase(c);
                ++incrementID;
                Toast.makeText(getApplicationContext(), "Add Case Successfully! ", Toast.LENGTH_SHORT).show();


            }
        });


    }
}