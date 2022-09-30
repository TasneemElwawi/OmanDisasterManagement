package com.tasneem.omandisastermanagement.database;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tasneem.omandisastermanagement.Activities.AdminProfile;
import com.tasneem.omandisastermanagement.Activities.AllUsers;
import com.tasneem.omandisastermanagement.Activities.VolunteerProfile;
import com.tasneem.omandisastermanagement.Activities.PanicButton;
import com.tasneem.omandisastermanagement.models.Admin;
import com.tasneem.omandisastermanagement.models.Case;
import com.tasneem.omandisastermanagement.models.Note;
import com.tasneem.omandisastermanagement.models.User;
import com.tasneem.omandisastermanagement.models.Video;
import com.tasneem.omandisastermanagement.models.VolunteerRequest;
import com.tasneem.omandisastermanagement.models.WarningNot;
import com.tasneem.omandisastermanagement.models.volunteer;

import java.util.HashMap;
import java.util.Objects;

import androidx.annotation.NonNull;

import static com.tasneem.omandisastermanagement.models.Case.incrementID;

public class AuthFirebase extends Application {
    private FirebaseAuth auth ;
    static private AuthFirebase singlton ;
    private static DatabaseReference reference;


    private static FirebaseDatabase mDatabase;

    public static String id_txt = "";
    private Intent i;
    String userName;

    private AuthFirebase() {}
// get instance from firebase
    public static AuthFirebase getInstance(){
        if (singlton == null) {
            singlton = new AuthFirebase();
            singlton.auth =  FirebaseAuth.getInstance();
            return singlton;
        }
        else return singlton;
    }


    // User sign up ..
    public void UserSignup(final Context context, final String email,  String name, String password, int age , String gender ,String phoneNumber ,double latitude ,double longitude,  String type){
        if (password != null && !password.isEmpty() && email != null && !email.isEmpty()&& name != null && !name.isEmpty()) {
            boolean isTrueEmail = checkEmail(context, email);
            boolean isTruePass = checkPass(context, password);
            if(isTrueEmail && isTruePass ){
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            id_txt = firebaseUser.getUid();

                            Toast.makeText(context, "sign up..", Toast.LENGTH_SHORT).show();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(id_txt);
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id", id_txt);
                            hashMap.put("fullName", name);
                            hashMap.put("email", email);
                            hashMap.put("phone", phoneNumber);
                            hashMap.put("age", String.valueOf(age));
                            hashMap.put("gender", gender);
                            hashMap.put("lat_desc", latitude+"");
                            hashMap.put("long_desc", longitude+"");
                            hashMap.put("type", type);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task1) {
                                    if(task1.isSuccessful()){
                                        Intent i = new Intent(context, PanicButton.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        i.putExtra("Email", email);
                                        i.putExtra("fullName", name);

                                        context.startActivity(i);
                                    }
                                }
                            });



                        }else{
                            Toast.makeText(context, "wrong email or pass", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        } else {
            Toast.makeText(context, "Fill all info", Toast.LENGTH_SHORT).show();
        }
    }


    // Admin log in ..
    public void AdminLogin(final Context context, String email, String password){
        if (password != null && !password.isEmpty() && email != null && !email.isEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            id_txt = firebaseUser.getUid();

                            i = new Intent(context, AdminProfile.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("Email", email);
                            context.startActivity(i);


                        }else{
                            Toast.makeText(context, "wrong email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("error", Objects.requireNonNull(e.getMessage()));
                    }
                });

        }else{
            Toast.makeText(context, "Fill all info", Toast.LENGTH_SHORT).show();
        }
    }

// volunteer log in ..
    public void VolunteerLogin(final Context context, String email, String password){
        if (password != null && !password.isEmpty() && email != null && !email.isEmpty()) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        assert firebaseUser != null;
                        id_txt = firebaseUser.getUid();

                        i = new Intent(context, VolunteerProfile.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("Email", email);
                        context.startActivity(i);


                    }else{
                        Toast.makeText(context, "wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("error", Objects.requireNonNull(e.getMessage()));
                }
            });

        }else{
            Toast.makeText(context, "Fill all info", Toast.LENGTH_SHORT).show();
        }
    }
// User log in ..
    public void UserLogin(final Context context, String email, String password){
        if (password != null && !password.isEmpty() && email != null && !email.isEmpty()) {
            getUserData( context , email);
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        assert firebaseUser != null;
                        id_txt = firebaseUser.getUid();

                        Toast.makeText(context, "log in successfully", Toast.LENGTH_SHORT).show();

                        i = new Intent(context, PanicButton.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("Email", email);
                        i.putExtra("fullName", userName);
                        context.startActivity(i);


                    }else{
                        Toast.makeText(context, "wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("erorr", Objects.requireNonNull(e.getMessage()));
                }
            });

        }else{
            Toast.makeText(context, "Fill all info", Toast.LENGTH_SHORT).show();
        }
    }

// reset password ..
    public void resetPass(final Context context, String email) {
        if (email != null && !email.isEmpty()) {
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

// log out ..
    public void signOut(Context context) {
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        Intent i = new Intent(context, AllUsers.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

// add notifications TO VOLUNTEER REQUEST ..
    public void addWarning(Note note) {
        boolean status = false;
        mDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Warning_From_Admin");

        Task task = reference.push().setValue(note);

        if (task.isSuccessful()) {
            status = true;
        }
    }

// check if password is valid ..
    private boolean checkPass(Context context, String password){
            String passwordPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+[A-Z0-9_!#$%&'*+/=?`{|}~^.-]+";
        if(password.length() < 9 &&  !password.trim().matches(passwordPattern) ){

            Toast.makeText(context, "Password should be more 9 characters and contains one of this \"!#$%&'*+/\" ", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

// check if email is valid ..
    private boolean checkEmail(Context context, String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(!email.trim().matches(emailPattern)){
            Toast.makeText(context, "Wrong Email!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    // add request from user ..
    public void addRequest(VolunteerRequest request) {
        boolean status = false;
        reference = FirebaseDatabase.getInstance().getReference().child("Requests");

        Task task = reference.push().setValue(request);

        if (task.isSuccessful()) {
            status = true;
            Toast.makeText(getApplicationContext(), "Add Request Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    // add volunteer from db ..
    public void addVolunteer(volunteer volunteer) {
        boolean status = false;
        reference = FirebaseDatabase.getInstance().getReference().child("Volunteers");

        Task task = reference.push().setValue(volunteer);

        if (task.isSuccessful()) {
            status = true;
            Toast.makeText(getApplicationContext(), "Add Volunteer Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    // update volunteer request status in database
    String key;
    public void UpdateStatus (Context context , String email , String status ) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Requests");
        Query emailQuery = databaseReference.orderByChild("email").equalTo(email);
        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    VolunteerRequest request = snapShot.getValue(VolunteerRequest.class);
                    assert request != null;
                    key = snapShot.getKey(); //Key

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("status", status+"");
                    Toast.makeText(context, "Update Status Successfully", Toast.LENGTH_SHORT).show();
                    databaseReference.child(key).updateChildren(map);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // add videos to db ..
    public void addVideos(Video video) {
        boolean status = false;
        reference = FirebaseDatabase.getInstance().getReference().child("Videos");

        Task task = reference.push().setValue(video);

        if (task.isSuccessful()) {
            status = true;
            Toast.makeText(getApplicationContext(), "Add Videos Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    // read user from email ..
    public String getUserData (Context context , String email){
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");

        Query emailQuery = usersRef.orderByChild("email").equalTo(email);
        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    assert user != null;
                    userName = user.getFullName();
                    Toast.makeText(context, "n" + userName, Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return userName;
    }

    // add emergency notifications from user ..
    public void AddPanicWarningNot(WarningNot warningNot) {
        boolean status = false;
        reference = FirebaseDatabase.getInstance().getReference().child("Emergency_Notifications");

        Task task = reference.push().setValue(warningNot);

        if (task.isSuccessful()) {
            status = true;
            Toast.makeText(getApplicationContext(), "We send your emergency notifications to the volunteer to help you! ", Toast.LENGTH_SHORT).show();

        }
    }

    // add case  from volunteer ..
    public void AddCase(Case c) {
        boolean status = false;
        reference = FirebaseDatabase.getInstance().getReference().child("Cases");

        Task task = reference.push().setValue(c);

        if (task.isSuccessful()) {
            ++incrementID;
            Toast.makeText(getApplicationContext(), "Add Case Successfully! ", Toast.LENGTH_SHORT).show();
            status = true;

        }
    }
}
