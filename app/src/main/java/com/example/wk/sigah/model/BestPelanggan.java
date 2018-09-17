package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 14/04/2018.
 */

public class BestPelanggan {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("totalPay")
    @Expose
    private String totalPay;
    @SerializedName("poin")
    @Expose
    private String poin;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }
}
