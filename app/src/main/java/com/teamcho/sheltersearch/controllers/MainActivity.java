package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.teamcho.sheltersearch.R;

public class MainActivity extends AppCompatActivity {

    Button logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = (Button) findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();

    }

    public void onClickLogout(View view) {
        mAuth.getInstance().signOut();
        Intent a = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(a);
    }
}
