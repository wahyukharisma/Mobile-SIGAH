package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 14/05/2018.
 */

public class ReportThree {
    String number;
    String roomType;
    String group;
    String personal;
    String total;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ReportThree(String number, String roomType, String group, String personal, String total) {

        this.number = number;
        this.roomType = roomType;
        this.group = group;
        this.personal = personal;
        this.total = total;
    }
}
