package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 17/04/2018.
 */

public class IncomeMounth {
    @SerializedName("ReservationType")
    @Expose
    private String reservationType;
    @SerializedName("Month")
    @Expose
    private String month;
    @SerializedName("Income")
    @Expose
    private String income;

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}
