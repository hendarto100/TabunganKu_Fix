package com.example.tabunganku_fix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransaksiTerbaru {
    @SerializedName("nis")
    @Expose
    private String nis;

    @SerializedName("jenis_tabungan")
    @Expose
    private String jenis_tabungan;

    @SerializedName("nominal_terakhir")
    @Expose
    private String nominal_terakhir;

    @SerializedName("jenis_transaksi")
    @Expose
    private String jenis_transaksi;

    @SerializedName("total_saldo")
    @Expose
    private String total_Saldo;

    public TransaksiTerbaru(String nis, String jenis_tabungan,
                            String nominal_terakhir, String jenis_transaksi,
                            String total_Saldo){
        this.nis=nis;
        this.jenis_tabungan=jenis_tabungan;
        this.nominal_terakhir=nominal_terakhir;
        this.jenis_transaksi=jenis_transaksi;
        this.total_Saldo=total_Saldo;
    }
    public String getNis(){
        return nis;
    }
    public void setNis(String nis){
        this.nis=nis;
    }
    public String getJenis_tabungan(){
        return jenis_tabungan;
    }
    public void setJenis_tabungan(String jenis_tabungan){
        this.jenis_tabungan = jenis_tabungan;
    }
    public String getNominal_terakhir(){
        return nominal_terakhir;
    }
    public void setNominal_terakhir(String nominal_terakhir){
        this.nominal_terakhir=nominal_terakhir;
    }
    public String getJenis_transaksi(){
        return jenis_transaksi;
    }
    public void setJenis_transaksi(String jenis_transaksi){
        this.jenis_transaksi=jenis_transaksi;
    }
    public String getTotal_Saldo(){
        return total_Saldo;
    }
    public void setTotal_Saldo(String total_Saldo){
        this.total_Saldo=total_Saldo;
    }

}
