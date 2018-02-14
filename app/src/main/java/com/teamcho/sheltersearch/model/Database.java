package com.teamcho.sheltersearch.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Here to hold the map for usernames and passwords.
 * Also handles setting new usernames and passwords.
 *
 * Created by lgpso on 2/13/2018.
 */

public class Database {

    private static Map<String, String> userMap = new HashMap<String, String>(){{ put("admin", "pass123"); }};

    public static boolean contains(String name){
        String stored = userMap.get(name);

        if(stored != null){
            return true;
        } else {
            return false;
        }
    }

    public static boolean newEntry(String username, String pass) {
        userMap.put(username, pass);
        return true;
    }

    public static String getPass(String username) {
        return userMap.get(username);
    }

}
