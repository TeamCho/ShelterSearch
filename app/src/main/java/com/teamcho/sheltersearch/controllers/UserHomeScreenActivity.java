package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.model.Shelter;
import java.util.ArrayList;

public class UserHomeScreenActivity extends AppCompatActivity {

    private final Database localDb = Database.getInstance();
    private EditText searchData;
    private final ArrayList<Shelter> shelterList = new ArrayList<>();
    private final ArrayList<String> shelterNameList = new ArrayList<>();
    private final ArrayList<Shelter> allShelters = localDb.getShelterList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);
        Button search = findViewById(R.id.search_bttn);
        Button shelters = findViewById(R.id.shelters);
        searchData = findViewById(R.id.search_data);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        localDb.clearData();
        localDb.loadData();
    }

    /**
     * Enters the shelter activity for the corresponding shelter that
     * is clicked on.
     * @param view the current view
     */
    public void onClickShelters(final View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * Searches for shelters that match certain keywords and displays
     * the resulting shelters in a listview.
     * @param view the current view
     */
    public void onClickSearch(final View view) {

        String searchText = searchData.getText().toString();
        boolean validSearch = checkSearchParam(searchText);

        if (validSearch) {
            System.out.println("This search is valid");
        } else {
            System.out.println("The search is invalid");
        }

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

    /**
     * Searches the for shelters that match certain keywords and displays the
     * results on Google Maps.
     * @param view the current view
     */
    //Search for Map
    public void onClickMapSearch(final View view) {
        String searchText = searchData.getText().toString();
        //boolean validSearch = checkSearchParam(searchText);

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

    /**
     * Logs the user out of the application.
     * @param view the current view
     */
    public void onClickLogout(final View view) {
        FirebaseAuth.getInstance().signOut();
        Intent a = new Intent(view.getContext(), WelcomeActivity.class);
        startActivity(a);
    }

    /**
     * Checks if the search contains any of the keywords.
     * @param search the current search query
     * @return Returns a boolean of whether or not
     * the search parameters contain any of the keywords.
     */
    public boolean checkSearchParam(String search) {
        ArrayList<String> validSearch = new ArrayList<>();
        validSearch.add("Male");
        validSearch.add("Female");
        validSearch.add("Women");
        validSearch.add("Families w/ newborns");
        validSearch.add("Families");
        validSearch.add("Children");
        validSearch.add("Young adults");
        validSearch.add("Anyone");

        return validSearch.contains(search);

    }
}
