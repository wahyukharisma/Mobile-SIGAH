package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kharisma on 08/04/2018.
 */

public class Reservation {
    @SerializedName("id_reservasi")
    @Expose
    private String idReservasi;
    @SerializedName("id_peg")
    @Expose
    private String idPeg;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("reservasi_status")
    @Expose
    private String reservasiStatus;
    @SerializedName("reservasi_tipe")
    @Expose
    private String reservasiTipe;
    @SerializedName("kode_reservasi")
    @Expose
    private String kodeReservasi;
    @SerializedName("nama_institusi")
    @Expose
    private Object namaInstitusi;
    @SerializedName("periode_waktu_bayar")
    @Expose
    private String periodeWaktuBayar;
    @SerializedName("jumlah_kamar")
    @Expose
    private String jumlahKamar;
    @SerializedName("dewasa")
    @Expose
    private String dewasa;
    @SerializedName("anak")
    @Expose
    private String anak;
    @SerializedName("urutan_reservasi")
    @Expose
    private String urutanReservasi;
    @SerializedName("tgl_reservasi")
    @Expose
    private String tglReservasi;

    public String getIdReservasi() {
        return idReservasi;
    }

    public void setIdReservasi(String idReservasi) {
        this.idReservasi = idReservasi;
    }

    public String getIdPeg() {
        return idPeg;
    }

    public void setIdPeg(String idPeg) {
        this.idPeg = idPeg;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getReservasiStatus() {
        return reservasiStatus;
    }

    public void setReservasiStatus(String reservasiStatus) {
        this.reservasiStatus = reservasiStatus;
    }

    public String getReservasiTipe() {
        return reservasiTipe;
    }

    public void setReservasiTipe(String reservasiTipe) {
        this.reservasiTipe = reservasiTipe;
    }

    public String getKodeReservasi() {
        return kodeReservasi;
    }

    public void setKodeReservasi(String kodeReservasi) {
        this.kodeReservasi = kodeReservasi;
    }

    public Object getNamaInstitusi() {
        return namaInstitusi;
    }

    public void setNamaInstitusi(Object namaInstitusi) {
        this.namaInstitusi = namaInstitusi;
    }

    public String getPeriodeWaktuBayar() {
        return periodeWaktuBayar;
    }

    public void setPeriodeWaktuBayar(String periodeWaktuBayar) {
        this.periodeWaktuBayar = periodeWaktuBayar;
    }

    public String getJumlahKamar() {
        return jumlahKamar;
    }

    public void setJumlahKamar(String jumlahKamar) {
        this.jumlahKamar = jumlahKamar;
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

    public String getUrutanReservasi() {
        return urutanReservasi;
    }

    public void setUrutanReservasi(String urutanReservasi) {
        this.urutanReservasi = urutanReservasi;
    }

    public String getTglReservasi() {
        return tglReservasi;
    }

    public void setTglReservasi(String tglReservasi) {
        this.tglReservasi = tglReservasi;
    }
}
