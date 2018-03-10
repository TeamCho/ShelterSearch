package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.model.Shelter;

public class ShelterActivity extends AppCompatActivity {

    Button back;
    TextView name;
    TextView address;
    TextView capacity;
    TextView phoneNum;
    TextView restrictions;
    TextView notes;
    TextView longi;
    TextView lat;
    TextView vacancies;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);
        int pos = getIntent().getIntExtra("pos", 0);

        back = (Button) findViewById(R.id.back);
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        capacity = (TextView) findViewById(R.id.capacity);
        phoneNum = (TextView) findViewById(R.id.phoneNum);
        restrictions = (TextView) findViewById(R.id.restrictions);
        notes = (TextView) findViewById(R.id.notes);
        longi = (TextView) findViewById(R.id.longi);
        lat = (TextView) findViewById(R.id.lat);
        vacancies = (TextView) findViewById(R.id.vacancies);

        mAuth = FirebaseAuth.getInstance();

        Shelter current = Database.getShelterList().get(pos);

        name.setText(current.getName());
        address.setText(current.getAddress());
        capacity.setText("Capacity: " + current.getCapacity());
        phoneNum.setText(current.getPhoneNum());
        restrictions.setText(current.getRestrictions());
        notes.setText(current.getNotes());

        vacancies.setText("Vacancies: " + current.getVacancies());
        longi.setText(Double.toString(current.getLongitude()));
        lat.setText(Double.toString(current.getLatitude()));

    }

    public void onBack (View view) {
        Intent b = new Intent(view.getContext(), MainActivity.class);
        startActivity(b);
    }

    public void onBook(View view) {

    }
}
