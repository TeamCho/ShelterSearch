package com.teamcho.sheltersearch;

import com.google.firebase.database.DatabaseReference;
import com.teamcho.sheltersearch.controllers.LoginActivity;
import com.teamcho.sheltersearch.controllers.ShelterActivity;
import com.teamcho.sheltersearch.controllers.UserHomeScreenActivity;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.controllers.RegisterActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.junit.Assert.assertEquals;

import com.google.firebase.database.FirebaseDatabase;
import com.teamcho.sheltersearch.model.Shelter;

/**
 * Contains a set of JUnit tests to test the functionality of the database model class.
 * Created by Luis G on 4/4/2018.
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({FirebaseDatabase.class})
public class ModelTests {
    /*
    @Before
    public void setUp() {
        DatabaseReference mockedDBRef = Mockito.mock(DatabaseReference.class);
        FirebaseDatabase mockedDB = Mockito.mock(FirebaseDatabase.class);
        when(mockedDB.getReference()).thenReturn(mockedDBRef);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedDB);
        Database db = Database.getInstance();
        db.loadData();
    }*/

    //Luis's JUnit
    @Test
    public void testRegister() {
        RegisterActivity reg = new RegisterActivity();
        String testNoUser = reg.checkRegisterParams("", "pass", "email@gmail.com");
        String testNoPass = reg.checkRegisterParams("name", "", "email@gmail.com");
        String testShortPass = reg.checkRegisterParams("name", "pass", "email@gmail.com");
        String testNoEmail = reg.checkRegisterParams("name", "pass", "");
        String testCorrect = reg.checkRegisterParams("name", "password", "email@hotmail.com");

        // Test if no user returns right messsage
        assertEquals(testNoUser, "Name is empty!");
        // Test if no password return right message
        assertEquals(testNoPass, "Password is missing!");
        // Test if short pass return right message
        assertEquals(testShortPass, "Password is less than 6 characters!");
        // Test if no email return right message
        assertEquals(testNoEmail, "Email is missing!");
        // Test if everything correct returns right message
        assertEquals(testCorrect, "register");

    }

    //Hemanth's JUnit
    @Test
    public void testLogin() {
        LoginActivity log = new LoginActivity();
        boolean noUser = log.checkEmailAndPass("", "1234");
        boolean noPass = log.checkEmailAndPass("cho@gmail.com", "");
        boolean noBoth = log.checkEmailAndPass("","");
        boolean bothRight = log.checkEmailAndPass("cho@gmail.com", "1234");

        assertEquals(noUser, false);
        assertEquals(noPass, false);
        assertEquals(noBoth, false);
        assertEquals(bothRight, true);
    }

    @Test
    public void testSearchParams() {
        UserHomeScreenActivity a = new UserHomeScreenActivity();
        String men = "Male";
        String families = "Families";
        String wrong = "nothing";

        assertEquals(a.checkSearchParam(men), true);
        assertEquals(a.checkSearchParam(families), true);
        assertEquals(a.checkSearchParam(wrong), false);
    }

    @Test
    public void testOnBook() {
        Shelter shelter = new Shelter();
        int numVacancies = 2;
        int numToBook = 3;

        shelter.setVacancies(numVacancies);
        assertEquals(shelter.bookBed(numToBook), false);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


}
