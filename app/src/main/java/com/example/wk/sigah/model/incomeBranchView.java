package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 18/04/2018.
 */

public class incomeBranchView {
    String no;
    String year;
    String totalYogyakarta;
    String totalBandung;
    String totalYogBan;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTotalYogyakarta() {
        return totalYogyakarta;
    }

    public void setTotalYogyakarta(String totalYogyakarta) {
        this.totalYogyakarta = totalYogyakarta;
    }

    public String getTotalBandung() {
        return totalBandung;
    }

    public void setTotalBandung(String totalBandung) {
        this.totalBandung = totalBandung;
    }

    public String getTotalYogBan() {
        return totalYogBan;
    }

    public void setTotalYogBan(String totalYogBan) {
        this.totalYogBan = totalYogBan;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public incomeBranchView(String no, String totalYogyakarta, String totalBandung, String totalYogBan, String year) {

        this.no = no;
        this.totalYogyakarta = totalYogyakarta;
        this.totalBandung = totalBandung;
        this.totalYogBan = totalYogBan;
        this.year=year;
    }
}
