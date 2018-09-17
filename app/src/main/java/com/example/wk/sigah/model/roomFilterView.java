package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 10/04/2018.
 */

public class roomFilterView {
    String nomor_ruangan;
    String lantai;
    String kapasitas;
    String harga;
    String deskripsi;
    String status_merokok;
    String alamat;
    String tipe_kamar;
    String jenis_kasur;
    Integer gambar;

    public String getNomor_ruangan() {
        return nomor_ruangan;
    }

    public void setNomor_ruangan(String nomor_ruangan) {
        this.nomor_ruangan = nomor_ruangan;
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

    public String getStatus_merokok() {
        return status_merokok;
    }

    public void setStatus_merokok(String status_merokok) {
        this.status_merokok = status_merokok;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTipe_kamar() {
        return tipe_kamar;
    }

    public void setTipe_kamar(String tipe_kamar) {
        this.tipe_kamar = tipe_kamar;
    }

    public String getJenis_kasur() {
        return jenis_kasur;
    }

    public void setJenis_kasur(String jenis_kasur) {
        this.jenis_kasur = jenis_kasur;
    }

    public Integer getGambar() {
        return gambar;
    }

    public void setGambar(Integer gambar) {
        this.gambar = gambar;
    }

    public roomFilterView(String nomor_ruangan, String lantai, String kapasitas, String harga, String deskripsi, String status_merokok, String alamat, String tipe_kamar, String jenis_kasur, Integer gambar) {

        this.nomor_ruangan = nomor_ruangan;
        this.lantai = lantai;
        this.kapasitas = kapasitas;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.status_merokok = status_merokok;
        this.alamat = alamat;
        this.tipe_kamar = tipe_kamar;
        this.jenis_kasur = jenis_kasur;
        this.gambar = gambar;
    }
}
