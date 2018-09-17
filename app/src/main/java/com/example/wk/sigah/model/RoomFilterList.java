package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 10/04/2018.
 */

public class RoomFilterList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<RoomFilter> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<RoomFilter> getResult() {
        return result;
    }

    public void setResult(List<RoomFilter> result) {
        this.result = result;
    }
}
