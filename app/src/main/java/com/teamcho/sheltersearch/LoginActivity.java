package com.teamcho.sheltersearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Core backend = new Core();

    EditText username;
    EditText password;
    Button login;
    Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cancel = (Button) findViewById(R.id.cancel);
        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(), WelcomeActivity.class);
                startActivity(a);
            }
        });
        final String user = username.getText().toString();
        final String pass = password.getText().toString();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if the username exists in the map of users and pass
                boolean exists = backend.contains(user);
                if (exists && pass.equals(backend.getPass(user))) {
                    Intent b = new Intent(view.getContext(), MainActivity.class);
                    startActivity(b);
                }
            }
        });
    }


}
