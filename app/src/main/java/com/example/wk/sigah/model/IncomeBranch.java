package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 18/04/2018.
 */

public class IncomeBranch {
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Yogyakarta")
    @Expose
    private String yogyakarta;
    @SerializedName("Bandung")
    @Expose
    private String bandung;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYogyakarta() {
        return yogyakarta;
    }

    public void setYogyakarta(String yogyakarta) {
        this.yogyakarta = yogyakarta;
    }

    public String getBandung() {
        return bandung;
    }

    public void setBandung(String bandung) {
        this.bandung = bandung;
    }
}
