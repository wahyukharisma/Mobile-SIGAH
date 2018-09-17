package com.example.wk.sigah.model;

import java.util.List;

/**
 * Created by Kharisma on 01/04/2018.
 */

public class Value {
    Integer value;
    String message;
    List<Pelanggan> result;
    List<User> userList;

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<Pelanggan> getResult() {
        return result;
    }

    public List<User> getUserList() {
        return userList;
    }
}
