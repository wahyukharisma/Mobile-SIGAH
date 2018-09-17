package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 06/04/2018.
 */

public class Season {
    @SerializedName("id_season")
    @Expose
    private String idSeason;
    @SerializedName("tanggal_mulai")
    @Expose
    private String tanggalMulai;
    @SerializedName("tanggal_selesai")
    @Expose
    private String tanggalSelesai;
    @SerializedName("nama_season")
    @Expose
    private String namaSeason;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("tipe_kamar")
    @Expose
    private String tipeKamar;

    public String getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(String idSeason) {
        this.idSeason = idSeason;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getNamaSeason() {
        return namaSeason;
    }

    public void setNamaSeason(String namaSeason) {
        this.namaSeason = namaSeason;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }
}
