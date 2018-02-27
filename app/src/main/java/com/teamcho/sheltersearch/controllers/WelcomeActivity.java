package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.controllers.LoginActivity;
import com.teamcho.sheltersearch.controllers.RegisterActivity;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.model.Shelter;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setTitle("");

        if (Database.getShelterList() == null && Database.getShelterNameList() == null) {
            Database.loadData();
        }

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
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
