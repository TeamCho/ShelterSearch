package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.controllers.LoginActivity;
import com.teamcho.sheltersearch.controllers.RegisterActivity;
import com.teamcho.sheltersearch.model.Database;

public class WelcomeActivity extends AppCompatActivity {

    Database localDb = Database.getInstance();
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setTitle("");

        if (localDb.getShelterList() == null && localDb.getShelterNameList() == null) {
            localDb.loadData();
        }

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
    }

    public void onClickLogin(View view) {
        Intent a = new Intent(view.getContext(), LoginActivity.class);
        startActivity(a);
    }

    public void onClickRegister(View view) {
        Intent b = new Intent(view.getContext(), RegisterActivity.class);
        startActivity(b);
    }
}
