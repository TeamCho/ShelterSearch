package com.teamcho.sheltersearch;

import com.google.firebase.database.DatabaseReference;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.controllers.RegisterActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.junit.Assert.assertEquals;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Contains a set of JUnit tests to test the functionality of the database model class.
 * Created by Luis G on 4/4/2018.
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({FirebaseDatabase.class})
public class ModelTests {

    @Before
    public void setUp() {
        DatabaseReference mockedDBRef = Mockito.mock(DatabaseReference.class);
        FirebaseDatabase mockedDB = Mockito.mock(FirebaseDatabase.class);
        when(mockedDB.getReference()).thenReturn(mockedDBRef);
        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedDB);
        Database db = Database.getInstance();
        db.loadData();
    }

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

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


}
