package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 14/04/2018.
 */

public class bestPelangganView {
    public String nama;
    public String poin;
    public String totalPay;
    public String position;

    public bestPelangganView(String nama, String poin, String totalPay,String position) {
        this.nama = nama;
        this.poin = poin;
        this.totalPay = totalPay;
        this.position =position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNama() {

        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }
}
