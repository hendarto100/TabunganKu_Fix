package com.example.tabunganku_fix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Transaksi {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("kode_transaksi")
    @Expose
    private String kode_transaksi;

    @SerializedName("nis")
    @Expose
    private String nis;

    @SerializedName("jenis_tabungan")
    @Expose
    private String jenis_tabungan;

    @SerializedName("jenis_transaksi")
    @Expose
    private String jenis_transaksi;

    @SerializedName("nominal")
    @Expose
    private int nominal;

    @SerializedName("created_at")
    @Expose
    private String created_at;
    public Transaksi(int id, String kode_transaksi,
                     String nis, String jenis_tabungan, String jenis_transaksi,
                     int nominal, String created_at){
        this.id = id;
        this.kode_transaksi=kode_transaksi;
        this.nis = nis;
        this.jenis_tabungan=jenis_tabungan;
        this.jenis_transaksi=jenis_transaksi;
        this.nominal = nominal;
        this.created_at = created_at;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getKode_transaksi() {
        return kode_transaksi;
    }
    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }
    public String getNis() {
        return nis;
    }
    public void setNis(String nis) {
        this.nis = nis;
    }
    public String getJenis_tabungan() {
        return jenis_tabungan;
    }
    public void setJenis_tabungan(String jenis_tabungan) {
        this.jenis_tabungan = jenis_tabungan;
    }
    public String getJenis_transaksi() {
        return jenis_transaksi;
    }
    public void setJenis_transaksi(String jenis_transaksi) {
        this.jenis_transaksi = jenis_transaksi;
    }
    public int getNominal() {
        return nominal;
    }
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
