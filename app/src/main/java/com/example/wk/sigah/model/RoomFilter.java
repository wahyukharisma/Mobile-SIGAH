package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 10/04/2018.
 */

public class RoomFilter {
    @SerializedName("nomor_ruangan")
    @Expose
    private String nomorRuangan;
    @SerializedName("lantai")
    @Expose
    private String lantai;
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
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("tipe_kamar")
    @Expose
    private String tipeKamar;
    @SerializedName("jenis_kasur")
    @Expose
    private String jenisKasur;

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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public String getJenisKasur() {
        return jenisKasur;
    }

    public void setJenisKasur(String jenisKasur) {
        this.jenisKasur = jenisKasur;
    }
}
