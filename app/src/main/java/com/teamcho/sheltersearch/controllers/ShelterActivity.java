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
    EditText bookNumber;
    TextView alert;
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
        bookNumber = (EditText) findViewById(R.id.bookNumber);
        alert = (TextView) findViewById(R.id.alert);

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
        //Gets the current Shelter
        int pos = getIntent().getIntExtra("pos", 0);
        Shelter current = Database.getShelterList().get(pos);

        /* Obtains the current number of vacancies */
        int currentVacancies = current.getVacancies();

        //TODO: A variable to keep track of what shelter the user is at.
        //TODO: Need to ensure that beds are only booked for one shelter.
        //TODO: Need to store how many beds are taken with the user.
        //Attributes: email, name, uid, userType
        //New Attributes: currentShelter, bedsOccupied
        //User's variable that shows current Shelter is modified
        //Should be stored in the User Database as an attribute of the user.
        //Access the attribute and see if it is not null.
        //How do I read data from the firebase database?

        /* The number of beds the user wants to reserve. */
        int bedsTaken = Integer.parseInt(bookNumber.getText().toString());

        //TODO: Fix the if statement in accordance with the user variable and number to book.
        if(currentVacancies - bedsTaken > 0) {
            //Updates the current amount of vacancies
            current.setVacancies(currentVacancies - bedsTaken);
        } else {
            alert.setText("This Shelter currently is full!");
        }

        Intent b = new Intent(view.getContext(), ShelterActivity.class);
        startActivity(b);
    }

    public void onCancel(View view) {
        //TODO: What happens when the reservation/booking is cancelled.
    }
}
