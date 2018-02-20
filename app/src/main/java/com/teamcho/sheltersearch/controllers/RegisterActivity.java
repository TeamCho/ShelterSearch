package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

        //backend = new Database();

        user = username.getText().toString();
        pass = password.getText().toString();
    }

    public void onClickCancel(View view) {
        Intent back = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(back);
    }

    public void onClickRegister(View view) {
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
    }
}
