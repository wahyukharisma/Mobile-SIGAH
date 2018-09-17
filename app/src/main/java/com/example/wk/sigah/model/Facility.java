package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 09/04/2018.
 */

public class Facility {
    @SerializedName("id_fasilitas")
    @Expose
    private String idFasilitas;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("nama_fasilitas")
    @Expose
    private String namaFasilitas;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("status_tersedia")
    @Expose
    private String statusTersedia;

    public String getIdFasilitas() {
        return idFasilitas;
    }

    public void setIdFasilitas(String idFasilitas) {
        this.idFasilitas = idFasilitas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaFasilitas() {
        return namaFasilitas;
    }

    public void setNamaFasilitas(String namaFasilitas) {
        this.namaFasilitas = namaFasilitas;
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

    public String getStatusTersedia() {
        return statusTersedia;
    }

    public void setStatusTersedia(String statusTersedia) {
        this.statusTersedia = statusTersedia;
    }
}
