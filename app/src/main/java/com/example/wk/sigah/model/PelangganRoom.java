package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 16/04/2018.
 */

public class PelangganRoom {
    @SerializedName("Reservationtype")
    @Expose
    private String reservationtype;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Month")
    @Expose
    private String month;
    @SerializedName("RoomType")
    @Expose
    private String roomType;
    @SerializedName("Total")
    @Expose
    private String total;

    public String getReservationtype() {
        return reservationtype;
    }

    public void setReservationtype(String reservationtype) {
        this.reservationtype = reservationtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
