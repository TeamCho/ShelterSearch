package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.teamcho.sheltersearch.R;

public class LoginActivity extends AppCompatActivity {

    //Database backend;

    EditText username;
    EditText password;
    Button login;
    Button cancel;
    TextView alert;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        mAuth = FirebaseAuth.getInstance();

        cancel = findViewById(R.id.cancel);
        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        alert = findViewById(R.id.alert);

    }

    /**
     * Cancels the login.
     * @param view
     */
    public void onClickCancel(View view) {
        Intent back = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(back);
    }

    /**
     * Logs the user into the application.
     * @param view
     */
    public void onClickLogin(final View view) {
        String email = username.getText().toString();
        String pass = password.getText().toString();
        if (!checkEmailAndPass(email, pass)) {
            alert.setText("Login Failed!");
        }
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent b = new Intent(view.getContext(), UserHomeScreenActivity.class);
                            startActivity(b);
                        } else {
                            // If sign in fails, display a message to the user.
                            alert.setText("Login Failed!");
                        }
                    }
                });
    }

    public boolean checkEmailAndPass(String email, String pass) {
        if (email.isEmpty() || pass.isEmpty()) {
            return false;
        }
        return true;
    }


}
