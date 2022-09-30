package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;

import java.util.Objects;

public class AdminLogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_in);

// get data from text view ..
                TextInputEditText email = findViewById(R.id.titleTV);
                TextInputEditText password = findViewById(R.id.contentTV);

//log in ..
                Button btn_login = findViewById(R.id.sendNotification);
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AuthFirebase authFirebase = AuthFirebase.getInstance();
                            authFirebase.AdminLogin(getApplicationContext(), Objects.requireNonNull(email.getText()).toString(), Objects.requireNonNull(password.getText()).toString());
                        }
                });


}
}