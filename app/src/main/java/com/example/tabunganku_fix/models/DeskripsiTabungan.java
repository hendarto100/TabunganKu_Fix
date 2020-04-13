package com.example.tabunganku_fix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeskripsiTabungan {
    @SerializedName("nis")
    @Expose
    private String nis;

    @SerializedName("jenis_tabungan")
    @Expose
    private String jenis_tabungan;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    @SerializedName("total_saldo")
    @Expose
    private String total_saldo;

    @SerializedName("wali_kelas")
    @Expose
    private String wali_kelas;

    @SerializedName("nominal_terakhir")
    @Expose
    private int nominal_terakhir;

    @SerializedName("jenis_transaksi")
    @Expose
    private String jenis_transaksi;

    public DeskripsiTabungan(String nis, String jenis_tabungan, String status,
                             String deskripsi, String total_saldo, String wali_kelas, int nominal_terakhir,String jenis_transaksi){
        this.nis=nis;
        this.jenis_tabungan=jenis_tabungan;
        this.status=status;
        this.deskripsi=deskripsi;
        this.total_saldo = total_saldo;
        this.wali_kelas = wali_kelas;
        this.nominal_terakhir = nominal_terakhir;
        this.jenis_transaksi = jenis_transaksi;
    }
    public String getNis(){
        return nis;
    }
    public void setNis(String nis){
        this.nis=nis;
    }
    public String getJenis_tabungan() {
        return jenis_tabungan;
    }
    public void setJenis_tabungan(String jenis_tabungan) {
        this.jenis_tabungan = jenis_tabungan;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getDeskripsi(){
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi){
        this.deskripsi=deskripsi;
    }
    public String getTotal_saldo() {
        return total_saldo;
    }
    public void setTotal_saldo(String total_saldo) {
        this.total_saldo = total_saldo;
    }
    public void setNominal_terakhir(int nominal_terakhir) {
        this.nominal_terakhir = nominal_terakhir;
    }
    public int getNominal_terakhir() {
        return nominal_terakhir;
    }
    public String getWali_kelas(){
        return wali_kelas;
    }
    public void setWali_kelas(String wali_kelas){
        this.wali_kelas=wali_kelas;
    }
    public String getJenis_transaksi() {
        return jenis_transaksi;
    }
    public void setJenis_transaksi(String jenis_transaksi) {
        this.jenis_transaksi = jenis_transaksi;
    }
}
