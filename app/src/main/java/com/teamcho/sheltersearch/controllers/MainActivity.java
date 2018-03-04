package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.ValueEventListener;
import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.model.Database;
//import com.teamcho.sheltersearch.model.Shelter;

public class MainActivity extends AppCompatActivity {

    Button logout;
    ListView listView;
    ArrayList<String> names;
    ArrayAdapter<String> adapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = (Button) findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();

        names = Database.getShelterNameList();
        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                Intent a = new Intent(v.getContext(), ShelterActivity.class);
                a.putExtra("pos", position);
                startActivity(a);
            }
        });

    }

    public void onClickLogout(View view) {
        mAuth.getInstance().signOut();
        Intent a = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(a);
    }


}
