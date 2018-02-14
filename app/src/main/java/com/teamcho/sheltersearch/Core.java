package com.teamcho.sheltersearch;

import java.util.HashMap;
import java.util.Map;

/**
 * Here to hold the map for usernames and passwords.
 * Also handles setting new usernames and passwords.
 *
 * Created by lgpso on 2/13/2018.
 */

public class Core {

    private static Map<String, String> userMap = new HashMap<>();

    public Core() {
        userMap.put("admin", "pass123");

    }

    public boolean contains(String key){
        return userMap.containsKey(key);
    }

    public boolean newEntry(String key, String pass) {
        userMap.put(key, pass);
        return true;
    }

    public String getPass(String key) {
        return userMap.get(key);
    }

}
