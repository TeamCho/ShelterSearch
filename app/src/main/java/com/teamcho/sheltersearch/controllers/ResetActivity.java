package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamcho.sheltersearch.R;

/**
 * Created by rahulzhade on 4/23/18.
 */

public class ResetActivity extends AppCompatActivity {
    private EditText username;
    private TextView alert;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        setTitle("Reset");

        mAuth = FirebaseAuth.getInstance();

        Button cancel = findViewById(R.id.cancel);
        Button reset = findViewById(R.id.reset);
        username = findViewById(R.id.username);
        alert = findViewById(R.id.alert);

    }

    /**
     * Logs the user into the application.
     * @param view the current view
     */
    public void onClickReset(final View view) {
        String email = username.getText().toString();
        if (!checkEmailAndPass(email)) {
            alert.setText("Please enter a valid email!");
        }
        mAuth.sendPasswordResetEmail(email);
        Intent b = new Intent(view.getContext(), LoginActivity.class);
        startActivity(b);
    }

    /**
     * Cancels the login.
     * @param view the current view
     */
    public void onClickCancel(View view) {
        Intent back = new Intent(view.getContext(), LoginActivity.class);
        startActivity(back);
    }

    public boolean checkEmailAndPass(String email) {
        if (!email.isEmpty()) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }
}
