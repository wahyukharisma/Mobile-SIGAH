package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 15/04/2018.
 */

public class NewPelanggan {
    @SerializedName("Month")
    @Expose
    private String month;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("NewPel")
    @Expose
    private String newPel;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNewPel() {
        return newPel;
    }

    public void setNewPel(String newPel) {
        this.newPel = newPel;
    }
}
