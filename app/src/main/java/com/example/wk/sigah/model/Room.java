package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 09/04/2018.
 */

public class Room {

    @SerializedName("id_kamar")
    @Expose
    private String idKamar;
    @SerializedName("id_kasur")
    @Expose
    private String idKasur;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("nomor_ruangan")
    @Expose
    private String nomorRuangan;
    @SerializedName("lantai")
    @Expose
    private String lantai;
    @SerializedName("tipe_kamar")
    @Expose
    private String tipeKamar;
    @SerializedName("kapasitas")
    @Expose
    private String kapasitas;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("status_merokok")
    @Expose
    private String statusMerokok;
    @SerializedName("status_tersedia")
    @Expose
    private String statusTersedia;

    public String getIdKamar() {
        return idKamar;
    }

    public void setIdKamar(String idKamar) {
        this.idKamar = idKamar;
    }

    public String getIdKasur() {
        return idKasur;
    }

    public void setIdKasur(String idKasur) {
        this.idKasur = idKasur;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorRuangan() {
        return nomorRuangan;
    }

    public void setNomorRuangan(String nomorRuangan) {
        this.nomorRuangan = nomorRuangan;
    }

    public String getLantai() {
        return lantai;
    }

    public void setLantai(String lantai) {
        this.lantai = lantai;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatusMerokok() {
        return statusMerokok;
    }

    public void setStatusMerokok(String statusMerokok) {
        this.statusMerokok = statusMerokok;
    }

    public String getStatusTersedia() {
        return statusTersedia;
    }

    public void setStatusTersedia(String statusTersedia) {
        this.statusTersedia = statusTersedia;
    }
}
