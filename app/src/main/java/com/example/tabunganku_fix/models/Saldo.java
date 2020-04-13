package com.example.tabunganku_fix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Saldo {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nis")
    @Expose
    private String nis;

    @SerializedName("jenis_tabungan")
    @Expose
    private String jenis_tabungan;

    @SerializedName("saldo")
    @Expose
    private int saldo;

    @SerializedName("periode")
    @Expose
    private String periode;

    @SerializedName("status_tabungan")
    @Expose
    private String status_tabungan;


    public Saldo(int id, String nis, String jenis_tabungan,
                 int saldo, String periode, String status_tabungan){
        this.id=id;
        this.nis=nis;
        this.jenis_tabungan=jenis_tabungan;
        this.saldo=saldo;
        this.periode = periode;
        this.status_tabungan=status_tabungan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getStatus_tabungan() {
        return status_tabungan;
    }

    public void setStatus_tabungan(String status_tabungan) {
        this.status_tabungan = status_tabungan;
    }
}
