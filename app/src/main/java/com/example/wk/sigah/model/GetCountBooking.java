package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 04/04/2018.
 */

public class GetCountBooking {
    @SerializedName("total_reservation")
    @Expose
    private String totalReservation;

    public String getTotalReservation() {
        return totalReservation;
    }

    public void setTotalReservation(String totalReservation) {
        this.totalReservation = totalReservation;
    }
}
