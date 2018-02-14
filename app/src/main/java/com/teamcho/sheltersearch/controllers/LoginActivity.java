package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.R;

public class LoginActivity extends AppCompatActivity {

    //Database backend;

    EditText username;
    EditText password;
    Button login;
    Button cancel;
    TextView alert;

    private String user;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cancel = (Button) findViewById(R.id.cancel);
        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        alert = (TextView) findViewById(R.id.alert);

        //backend = new Database();

        user = username.getText().toString();
        pass = password.getText().toString();

    }

    public void onClickCancel(View view) {
        Intent back = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(back);
    }

    public void onClickLogin(View view) {
        user = username.getText().toString();
        pass = password.getText().toString();

        boolean exists = Database.contains(user);

        if (exists && pass.equals(Database.getPass(user))) {
            Intent b = new Intent(view.getContext(), MainActivity.class);
            startActivity(b);
        } else {
            alert.setText("Login Failed!");
        }
    }


}
