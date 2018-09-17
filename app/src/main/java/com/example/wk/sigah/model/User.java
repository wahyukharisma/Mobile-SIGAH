package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 02/04/2018.
 */

public class User {
    String message;
    Integer value;

    public String getMessage() {
        return message;
    }

    public Integer getValue() {
        return value;
    }

    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_role")
    @Expose
    private String idRole;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id_peg")
    @Expose
    private Object idPeg;
    @SerializedName("id_pel")
    @Expose
    private String idPel;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getIdPeg() {
        return idPeg;
    }

    public void setIdPeg(Object idPeg) {
        this.idPeg = idPeg;
    }

    public String getIdPel() {
        return idPel;
    }

    public void setIdPel(String idPel) {
        this.idPel = idPel;
    }

}
