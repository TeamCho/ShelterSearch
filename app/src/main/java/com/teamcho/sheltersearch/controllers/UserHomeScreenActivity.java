package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.model.Shelter;
import java.util.ArrayList;

public class UserHomeScreenActivity extends AppCompatActivity {

    private Database localDb = Database.getInstance();
    Button search;
    Button shelters;
    EditText searchData;
    private FirebaseAuth mAuth;
    ArrayList<Shelter> shelterList =  new ArrayList<Shelter>();
    ArrayList<String> shelterNameList = new ArrayList<String>();
    ArrayList<Shelter> allShelters = localDb.getShelterList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);
        search = (Button) findViewById(R.id.search_bttn);
        shelters = (Button) findViewById(R.id.shelters);
        searchData = (EditText) findViewById(R.id.search_data);
        mAuth = FirebaseAuth.getInstance();
        localDb.clearData();
        localDb.loadData();
    }

    public void onClickShelters(final View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickSearch(final View view) {
        String searchText = searchData.getText().toString();
        if (searchText.equals("Male")) {
            for (Shelter s: allShelters) {
                if (!s.getRestrictions().contains("Women")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Female")) {
            for (Shelter s: allShelters) {
                if (!s.getRestrictions().contains("Men")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Families w/ newborns")) {
            for (Shelter s : allShelters) {
                if (s.getRestrictions().equals("Families w/ newborns")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.contains("Families")) {
            for (Shelter s : allShelters) {
                if (s.getRestrictions().contains("Families")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Children")) {
            for (Shelter s: allShelters) {
                if (s.getRestrictions().contains("Children")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Young adults")) {
            for (Shelter s: allShelters) {
                if (s.getRestrictions().contains("Young adults")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Anyone")) {
            for (Shelter s: allShelters) {
                if (s.getRestrictions().contains("Anyone")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else {
            for (Shelter s: allShelters) {
                if (s.getName().equals(searchText)) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        }
        if (!shelterList.isEmpty()) {
            localDb.setShelterList(shelterList);
            localDb.setShelterNameList(shelterNameList);
        }
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }

    //Search for Map
    public void onClickMapSearch(final View view) {
        String searchText = searchData.getText().toString();
        if (searchText.equals("Male")) {
            for (Shelter s: allShelters) {
                if (!s.getRestrictions().contains("Women")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Female")) {
            for (Shelter s: allShelters) {
                if (!s.getRestrictions().contains("Men")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Families w/ newborns")) {
            for (Shelter s : allShelters) {
                if (s.getRestrictions().equals("Families w/ newborns")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.contains("Families")) {
            for (Shelter s : allShelters) {
                if (s.getRestrictions().contains("Families")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Children")) {
            for (Shelter s: allShelters) {
                if (s.getRestrictions().contains("Children")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Young adults")) {
            for (Shelter s: allShelters) {
                if (s.getRestrictions().contains("Young adults")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else if (searchText.equals("Anyone")) {
            for (Shelter s: allShelters) {
                if (s.getRestrictions().contains("Anyone")) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        } else {
            for (Shelter s: allShelters) {
                if (s.getName().equals(searchText)) {
                    shelterList.add(s);
                    shelterNameList.add(s.getName());
                }
            }
        }
        if (!shelterList.isEmpty()) {
            localDb.setShelterList(shelterList);
            localDb.setShelterNameList(shelterNameList);
        }

        Intent intent = new Intent(view.getContext(), MapsActivity.class);
        startActivity(intent);
    }

    public void onClickLogout(final View view) {
        mAuth.getInstance().signOut();
        Intent a = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(a);
    }
}
