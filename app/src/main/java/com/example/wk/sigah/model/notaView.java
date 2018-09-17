package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 03/05/2018.
 */

public class notaView {
    String id_booking;
    String tanggal;
    String nama;
    String alamatPel;
    String checkIn;
    String checkOut;
    String dewasa;
    String anak;
    String tanggalBayar;
    String tipeReservasi;
    String statusReservasi;
    String alamatHotel;

    public notaView(String id_booking, String tanggal, String nama, String alamatPel, String checkIn, String checkOut, String dewasa, String anak, String tanggalBayar, String tipeReservasi, String statusReservasi, String alamatHotel) {
        this.id_booking = id_booking;
        this.tanggal = tanggal;
        this.nama = nama;
        this.alamatPel = alamatPel;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.dewasa = dewasa;
        this.anak = anak;
        this.tanggalBayar = tanggalBayar;
        this.tipeReservasi = tipeReservasi;
        this.statusReservasi = statusReservasi;
        this.alamatHotel = alamatHotel;
    }

    public String getId_booking() {
        return id_booking;
    }

    public void setId_booking(String id_booking) {
        this.id_booking = id_booking;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
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

    public String getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(String tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public String getTipeReservasi() {
        return tipeReservasi;
    }

    public void setTipeReservasi(String tipeReservasi) {
        this.tipeReservasi = tipeReservasi;
    }

    public String getStatusReservasi() {
        return statusReservasi;
    }

    public void setStatusReservasi(String statusReservasi) {
        this.statusReservasi = statusReservasi;
    }

    public String getAlamatHotel() {
        return alamatHotel;
    }

    public void setAlamatHotel(String alamatHotel) {
        this.alamatHotel = alamatHotel;
    }
}
