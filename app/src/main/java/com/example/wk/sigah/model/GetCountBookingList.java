package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 04/04/2018.
 */

public class GetCountBookingList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<GetCountBooking> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<GetCountBooking> getResult() {
        return result;
    }

    public void setResult(List<GetCountBooking> result) {
        this.result = result;
    }
}
