package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 01/04/2018.
 */

public class Pelanggan {
    @SerializedName("id_pel")
    @Expose
    private String idPel;
    @SerializedName("id_role")
    @Expose
    private String idRole;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("no_identitas")
    @Expose
    private String noIdentitas;
    @SerializedName("telp")
    @Expose
    private String telp;
    @SerializedName("alamat")
    @Expose
    private String alamat;

    public String getIdPel() {
        return idPel;
    }

    public void setIdPel(String idPel) {
        this.idPel = idPel;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
