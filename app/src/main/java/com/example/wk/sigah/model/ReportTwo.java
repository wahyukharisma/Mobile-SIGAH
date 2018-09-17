package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 14/05/2018.
 */

public class ReportTwo {
    String number;
    String month;
    String total;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ReportTwo(String number, String month, String total) {

        this.number = number;
        this.month = month;
        this.total = total;
    }
}
