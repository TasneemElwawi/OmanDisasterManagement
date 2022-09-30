package com.tasneem.omandisastermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.database.AuthFirebase;

import java.util.Objects;

public class UserLogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);


// get data from text view ..
        TextInputEditText email = findViewById(R.id.titleTV);
        TextInputEditText password = findViewById(R.id.contentTV);
// get user type ..
        String userType = getIntent().getStringExtra("TYPE");
//        Toast.makeText(getApplicationContext(), "user type is: "+ userType, Toast.LENGTH_SHORT).show();

//log in ..
        Button btn_login = findViewById(R.id.sendNotification);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthFirebase authFirebase = AuthFirebase.getInstance();
                authFirebase.UserLogin(getApplicationContext(), Objects.requireNonNull(email.getText()).toString(), Objects.requireNonNull(password.getText()).toString());
            }
        });

        // if user forget password ..
        Button btn_forget = findViewById(R.id.forgetPass);
        btn_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , ForgetPassword.class);
                startActivity(i);
            }
        });
// if user don't have account , go to sign op ..
        Button btn_signup = findViewById(R.id.signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , SignUp.class);
                startActivity(i);
            }
        });
    }
}