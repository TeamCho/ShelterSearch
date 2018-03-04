package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.teamcho.sheltersearch.R;

public class UserHomeScreenActivity extends AppCompatActivity {

    Button search;
    Button shelters;
    EditText searchData;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);
        search = (Button) findViewById(R.id.search_bttn);
        shelters = (Button) findViewById(R.id.shelters);
        searchData = (EditText) findViewById(R.id.search_data);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickShelters(final View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickSearch(final View view) {

    }

    public void onClickLogout(final View view) {
        mAuth.getInstance().signOut();
        Intent a = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(a);
    }
}
