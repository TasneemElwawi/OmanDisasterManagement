package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;

public class ForgetPassword extends AppCompatActivity {

    private TextInputEditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //      reset password ..
                email = findViewById(R.id.titleTV);
                findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String emailTXT= email.getText().toString();
                        AuthFirebase authFirebase = AuthFirebase.getInstance();

                        authFirebase.resetPass(getApplicationContext(),emailTXT);
                    }
                });

        //  back to log in activity ..
                Button btn_back = findViewById(R.id.btn_back);
                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext() , UserLogIn.class);
                        startActivity(i);
                    }
                });
            }
        }