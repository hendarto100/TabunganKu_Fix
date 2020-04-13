package com.example.tabunganku_fix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JenisTabungan {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    @SerializedName("aktif")
    @Expose
    private int aktif;

    public JenisTabungan(int id, String nama, String deskripsi, int aktif){
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.aktif = aktif;
    }
    public int getId(){
        return id;}

    public void setId(int id){
        this.id=id;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama=nama;
    }
    public String getDeskripsi(){
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi){
        this.deskripsi=deskripsi;
    }
    public int getAktif(){
        return aktif;
    }
    public void setAktif(int aktif){
        this.aktif=aktif;
    }
}
