package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 09/04/2018.
 */

public class RoomFacilityList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<Room> result = null;
    @SerializedName("values")
    @Expose
    private Integer values;
    @SerializedName("results")
    @Expose
    private List<Facility> results = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<Room> getResult() {
        return result;
    }

    public void setResult(List<Room> result) {
        this.result = result;
    }

    public Integer getValues() {
        return values;
    }

    public void setValues(Integer values) {
        this.values = values;
    }

    public List<Facility> getResults() {
        return results;
    }

    public void setResults(List<Facility> results) {
        this.results = results;
    }
}
