package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.model.Shelter;
import com.teamcho.sheltersearch.model.User;

import java.util.ArrayList;

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
    EditText bookNumber;
    TextView alert;
    Button book;
    Button cancel;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser currentUser;
    private int currentVacancies;
    private Shelter current;
    private int user_Shelter;
    private int bedsTaken;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


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
        bookNumber = (EditText) findViewById(R.id.bookNumber);
        alert = (TextView) findViewById(R.id.alert);

        book = (Button) findViewById(R.id.bookBed);
        cancel = (Button) findViewById(R.id.cancel);


        mAuth = FirebaseAuth.getInstance();

        current = Database.getShelterList().get(pos);

        name.setText(current.getName());
        address.setText(current.getAddress());
        capacity.setText("Capacity: " + current.getCapacity());
        phoneNum.setText(current.getPhoneNum());
        restrictions.setText(current.getRestrictions());
        notes.setText(current.getNotes());

        vacancies.setText("Vacancies: " + current.getVacancies());
        longi.setText(Double.toString(current.getLongitude()));
        lat.setText(Double.toString(current.getLatitude()));


        //Gets the current Shelter

        /* Obtains the current number of vacancies */
        currentVacancies = current.getVacancies();

        //The current user
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //mDatabase is the User Database

        mDatabase = database.getReference("/User");

        //Gets information about the current user.
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> userList = dataSnapshot.getChildren();
                for (DataSnapshot user : userList) {
                    if(user.child("uid").toString().equals(currentUser.getUid())) {
                        user_Shelter = Integer.parseInt(user.child("booking").toString());
                        bedsTaken = Integer.parseInt(user.child("bedsTaken").toString());
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(currentVacancies == 0 || user_Shelter != current.getKey()) {
            book.setVisibility(View.INVISIBLE);
        }

        if(user_Shelter == current.getKey()) {
            cancel.setVisibility(View.INVISIBLE);
        }

    }

    public void onBack (View view) {
        Intent b = new Intent(view.getContext(), MainActivity.class);
        startActivity(b);
    }

    public void onBook(View view) 

        /* The number of beds the user wants to reserve. */
        int bedsTaken = Integer.parseInt(bookNumber.getText().toString());

        //TODO: Fix the if statement in accordance with the user variable and number to book.
        if(currentVacancies - bedsTaken > 0 && bookingHolder.get(0) == null) {
            //Updates the current amount of vacancies
            current.setVacancies(currentVacancies - bedsTaken);

            //Updates the user's bedsTaken
            mDatabase.child(currentUser.getUid()).child("bedsTaken").setValue(bedsTaken);
            //Updates the user's booking
            mDatabase.child(currentUser.getUid()).child("booking").setValue(current);

            Intent b = new Intent(view.getContext(), ShelterActivity.class);
            startActivity(b);
        } else {
            alert.setText("This Shelter currently is full!");
        }
    }



    public void onCancel(View view) {
        //TODO: What happens when the reservation/booking is cancelled.
    }
}
