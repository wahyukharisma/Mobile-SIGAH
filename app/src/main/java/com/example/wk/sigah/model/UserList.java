package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 02/04/2018.
 */

public class UserList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<User> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
