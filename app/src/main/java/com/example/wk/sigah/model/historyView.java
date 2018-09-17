package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 08/04/2018.
 */

public class historyView {
    private String id_reservation;
    private String reservastion_type;
    private String date_reservation;
    private String location;
    private String status;

    public historyView(){

    }

    public String getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(String id_reservation) {
        this.id_reservation = id_reservation;
    }

    public String getReservastion_type() {
        return reservastion_type;
    }

    public void setReservastion_type(String reservastion_type) {
        this.reservastion_type = reservastion_type;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public historyView(String id_reservation, String reservastion_type, String date_reservation, String location,String status) {

        this.id_reservation = id_reservation;
        this.reservastion_type = reservastion_type;
        this.date_reservation = date_reservation;
        this.location = location;
        this.status=status;
    }
}
