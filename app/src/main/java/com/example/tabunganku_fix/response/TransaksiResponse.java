package com.example.tabunganku_fix.response;

import com.example.tabunganku_fix.models.DeskripsiTabungan;
import com.example.tabunganku_fix.models.JenisTabungan;
import com.example.tabunganku_fix.models.Transaksi;
import com.example.tabunganku_fix.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TransaksiResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data_transaksi")
    @Expose
    private List<Transaksi> transaksi;

    @SerializedName("data_jenis")
    @Expose
    private ArrayList<JenisTabungan> jenisTabungan;

    @SerializedName("data_siswa")
    @Expose
    private User user;

    @SerializedName("detail_tabungan")
    @Expose
    private DeskripsiTabungan deskripsiTabungan;


    public TransaksiResponse (String status, List<Transaksi> transaksi, User user,
                              DeskripsiTabungan deskripsiTabungan, ArrayList<JenisTabungan> jenisTabungan){
        this.status = status;
        this.transaksi = transaksi;
        this.user = user;
        this.deskripsiTabungan = deskripsiTabungan;
        this.jenisTabungan = jenisTabungan;
    }
    public String getStatus(){
        return status;
    }
    public List<Transaksi> getTransaksi() {
        return transaksi;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setTransaksi(List<Transaksi> transaksi) {
        this.transaksi = transaksi;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public DeskripsiTabungan getDeskripsiTabungan() {
        return deskripsiTabungan;
    }
    public void setDeskripsiTabungan(DeskripsiTabungan deskripsiTabungan) {
        this.deskripsiTabungan = deskripsiTabungan;
    }
    public ArrayList<JenisTabungan> getJenisTabungan() {
        return jenisTabungan;
    }
    public void setJenisTabungan(ArrayList<JenisTabungan> jenisTabungan) {
        this.jenisTabungan = jenisTabungan;
    }
}

