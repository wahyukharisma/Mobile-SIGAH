package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 06/05/2018.
 */

public class nota {
    @SerializedName("kodeReservasi")
    @Expose
    private String kodeReservasi;
    @SerializedName("tgl_reservasi")
    @Expose
    private String tglReservasi;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamatPel")
    @Expose
    private String alamatPel;
    @SerializedName("tgl_check_in")
    @Expose
    private String tglCheckIn;
    @SerializedName("tgl_check_out")
    @Expose
    private String tglCheckOut;
    @SerializedName("dewasa")
    @Expose
    private String dewasa;
    @SerializedName("anak")
    @Expose
    private String anak;
    @SerializedName("reservasiTipe")
    @Expose
    private String reservasiTipe;
    @SerializedName("reservasiStatus")
    @Expose
    private String reservasiStatus;
    @SerializedName("alamat")
    @Expose
    private String alamat;

    public String getKodeReservasi() {
        return kodeReservasi;
    }

    public void setKodeReservasi(String kodeReservasi) {
        this.kodeReservasi = kodeReservasi;
    }

    public String getTglReservasi() {
        return tglReservasi;
    }

    public void setTglReservasi(String tglReservasi) {
        this.tglReservasi = tglReservasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamatPel() {
        return alamatPel;
    }

    public void setAlamatPel(String alamatPel) {
        this.alamatPel = alamatPel;
    }

    public String getTglCheckIn() {
        return tglCheckIn;
    }

    public void setTglCheckIn(String tglCheckIn) {
        this.tglCheckIn = tglCheckIn;
    }

    public String getTglCheckOut() {
        return tglCheckOut;
    }

    public void setTglCheckOut(String tglCheckOut) {
        this.tglCheckOut = tglCheckOut;
    }

    public String getDewasa() {
        return dewasa;
    }

    public void setDewasa(String dewasa) {
        this.dewasa = dewasa;
    }

    public String getAnak() {
        return anak;
    }

    public void setAnak(String anak) {
        this.anak = anak;
    }

    public String getReservasiTipe() {
        return reservasiTipe;
    }

    public void setReservasiTipe(String reservasiTipe) {
        this.reservasiTipe = reservasiTipe;
    }

    public String getReservasiStatus() {
        return reservasiStatus;
    }

    public void setReservasiStatus(String reservasiStatus) {
        this.reservasiStatus = reservasiStatus;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
