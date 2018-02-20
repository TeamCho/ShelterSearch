package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.R;

public class RegisterActivity extends AppCompatActivity {

    //Database backend;

    EditText u_name;
    EditText username;
    EditText password;
    Button register;
    Button cancel;
    TextView alert;
    Spinner userType;

    private String name;
    private String user;
    private String pass;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cancel = (Button) findViewById(R.id.cancel);
        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.newUser);
        password = (EditText) findViewById(R.id.newPass);
        alert = (TextView) findViewById(R.id.alert);
        setTitle("New User Registration");

        user = username.getText().toString();
        pass = password.getText().toString();

        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickCancel(View view) {
        Intent back = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(back);
    }

    public void onClickRegister(final View view) {
        user = username.getText().toString();
        pass = password.getText().toString();

        boolean exists = Database.contains(user);
        if (exists) {
            alert.setText("Username is in use.");
        } else if (user != "" && pass != "") {
            Database.newEntry(user, pass);
            Intent b = new Intent(view.getContext(), MainActivity.class);
            startActivity(b);
        }

        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user.
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent b = new Intent(view.getContext(), MainActivity.class);
                            startActivity(b);
                        } else {
                            alert.setText("Unable to create that user!");
                        }

                        // ...
                    }
                });
    }
}
