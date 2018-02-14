package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamcho.sheltersearch.R;

public class MainActivity extends AppCompatActivity {

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = (Button) findViewById(R.id.logout);

    }

    public void onClickLogout(View view) {
        Intent a = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(a);
    }
}
