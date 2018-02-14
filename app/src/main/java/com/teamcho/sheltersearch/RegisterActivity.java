package com.teamcho.sheltersearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    Core backend = new Core();

    EditText username;
    EditText password;
    Button register;
    Button cancel;
    TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cancel = (Button) findViewById(R.id.cancel);
        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.newUser);
        password = (EditText) findViewById(R.id.newPass);
        alert = (TextView) findViewById(R.id.alert);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(), WelcomeActivity.class);
                startActivity(a);
            }
        });

        final String user = username.getText().toString();
        final String pass = password.getText().toString();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean exists = backend.contains(user);
                if (exists) {
                    alert.setText("Username is in use.");
                } else {
                    backend.newEntry(user, pass);
                    Intent b = new Intent(view.getContext(), MainActivity.class);
                    startActivity(b);
                }
            }
        });

    }
}
