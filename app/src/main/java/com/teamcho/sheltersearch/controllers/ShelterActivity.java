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

import java.util.Objects;

public class ShelterActivity extends AppCompatActivity {

    private EditText bookNumber;
    private TextView alert;

    private final Database localDb = Database.getInstance();
    private Shelter current;
    private int bedsTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);
        int pos = getIntent().getIntExtra("pos", 0);
        Button back = findViewById(R.id.back);
        TextView name = findViewById(R.id.name);
        TextView address = findViewById(R.id.address);
        TextView capacity = findViewById(R.id.capacity);
        TextView phoneNum = findViewById(R.id.phoneNum);
        TextView restrictions = findViewById(R.id.restrictions);
        TextView notes = findViewById(R.id.notes);
        TextView longi = findViewById(R.id.longi);
        TextView lat = findViewById(R.id.lat);
        TextView vacancies = findViewById(R.id.vacancies);
        bookNumber = findViewById(R.id.bookNumber);
        alert = findViewById(R.id.alert);
        Button book = findViewById(R.id.bookBed);
        Button cancel = findViewById(R.id.cancelRes);

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

        User user = localDb.getUserList().get(Objects.requireNonNull(currentUser).getUid());
        int user_Shelter = user.getBooking();
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
     * @param view the current view
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
     * @param view the current view
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
     * @param view the current view
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
