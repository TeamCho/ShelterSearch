package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.model.Shelter;
import com.teamcho.sheltersearch.model.User;

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

    private Database localDb = Database.getInstance();
    private Shelter current;
    int user_Shelter;
    int bedsTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);
        int pos = getIntent().getIntExtra("pos", 0);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        capacity = findViewById(R.id.capacity);
        phoneNum = findViewById(R.id.phoneNum);
        restrictions = findViewById(R.id.restrictions);
        notes = findViewById(R.id.notes);
        longi = findViewById(R.id.longi);
        lat = findViewById(R.id.lat);
        vacancies = findViewById(R.id.vacancies);
        bookNumber = findViewById(R.id.bookNumber);
        alert = findViewById(R.id.alert);
        book = findViewById(R.id.bookBed);
        cancel = findViewById(R.id.cancelRes);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        current = localDb.getShelterList().get(pos);

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
        int currentVacancies = current.getVacancies();

        //The current user
        FirebaseUser currentUser = mAuth.getCurrentUser();

        User user = localDb.getUserList().get(currentUser.getUid());
        user_Shelter = user.getBooking();
        bedsTaken = user.getBedsTaken();

        if(currentVacancies == 0 || user_Shelter != Integer.MAX_VALUE) {
            book.setVisibility(View.GONE);
        }

        if(user_Shelter != current.getKey()) {
            cancel.setVisibility(View.GONE);
        }

    }

    /**
     * Goes back to the main activity screen.
     * @param view
     */
    public void onBack (View view) {
        localDb.clearData();
        localDb.loadData();
        Intent b = new Intent(view.getContext(), MainActivity.class);
        startActivity(b);
    }

    @Override
    public void onBackPressed() {
        localDb.clearData();
        localDb.loadData();
        Intent intent = new Intent(ShelterActivity.this, UserHomeScreenActivity.class);
        startActivity(intent);
    }

    /**
     * Books a given number of beds for the current shelter.
     * @param view
     */
    public void onBook(View view) {

        /* The number of beds the user wants to reserve. */
        int bedsTaken = Integer.parseInt(bookNumber.getText().toString());

        //TODO: Fix the if statement in accordance with the user variable and number to book.
        if(current.bookBed(bedsTaken)) {
            localDb.clearData();
            localDb.loadData();
            Intent b = new Intent(view.getContext(), UserHomeScreenActivity.class);
            startActivity(b);
        } else {
            alert.setText("You cannot make that booking.");
        }

    }

    /**
     * Cancels the booking/reservation for the current shelter.
     * @param view
     */
    public void onCancel(View view) {
        //TODO: What happens when the reservation/booking is cancelled.
        try {
            current.cancelReservation(bedsTaken);
            localDb.clearData();
            localDb.loadData();
            Intent b = new Intent(view.getContext(), UserHomeScreenActivity.class);
            startActivity(b);
        } catch (Exception e) {
            alert.setText("Sorry, can't cancel reservation.");
        }
    }
}
