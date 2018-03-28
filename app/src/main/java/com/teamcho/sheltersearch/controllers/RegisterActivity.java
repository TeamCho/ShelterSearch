package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.model.User;
import com.teamcho.sheltersearch.model.UserType;

public class RegisterActivity extends AppCompatActivity {

    //Database backend;

    EditText email_entry;
    EditText name_entry;
    EditText password_entry;
    Button registerButton;
    Button cancelButton;
    TextView alert;
    Spinner userTypeSpinner;

    private String email_address;
    private String user_name;
    private String password;

    private User newUser;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        registerButton = (Button) findViewById(R.id.register_button);
        email_entry = (EditText) findViewById(R.id.email_entry);
        password_entry = (EditText) findViewById(R.id.password_entry);
        alert = (TextView) findViewById(R.id.alert);
        name_entry = (EditText) findViewById(R.id.u_name);
        setTitle("New User Registration");

        //User Type Spinner Setup
        userTypeSpinner = findViewById(R.id.auth_level_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);
        userTypeSpinner.setSelection(0);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void onClickCancel(View view) {
        Intent back = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(back);
    }

    public void onClickRegister(final View view) {
        email_address = email_entry.getText().toString();
        password = password_entry.getText().toString();
        user_name = name_entry.getText().toString();
        final String userType = (String) userTypeSpinner.getSelectedItem();


        //Email is empty
        if(TextUtils.isEmpty(email_address)) {
            alert.setText("Email is missing!");
            return;
        }
        // Username is empty
        if(TextUtils.isEmpty(user_name)) {
            alert.setText("Name is empty!");
            return;
        }
        // Password is empty
        if(TextUtils.isEmpty(password)) {
            alert.setText("Password is missing!");
            return;
        }
        // Password is less than 6 characters
        if(TextUtils.getTrimmedLength(password) < 6) {
            alert.setText("Password is less than 6 characters!");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email_address, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            newUser = new User(currentUser.getUid(), email_address, user_name, userType,Integer.MAX_VALUE,0);
                            mDatabase.child("User").child(currentUser.getUid()).setValue(newUser);
                            Intent b = new Intent(view.getContext(), UserHomeScreenActivity.class);
                            startActivity(b);
                        } else {
                            // If sign in fails, display a message to the user.
                            alert.setText("Unable to create that user!");
                        }

                        // ...
                    }
                });
    }
}
