package com.example.tabunganku_fix.response;

import com.example.tabunganku_fix.models.DeskripsiTabungan;
import com.example.tabunganku_fix.models.Transaksi;
import com.example.tabunganku_fix.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailTabunganResponse {
    @SerializedName("detail_tabungan")
    @Expose
    private DeskripsiTabungan deskripsiTabungan;

    @SerializedName("data_siswa")
    @Expose
    private User user;

    @SerializedName("data_transaksi")
    @Expose
    private List<Transaksi> transaksi;


    public DetailTabunganResponse(DeskripsiTabungan deskripsiTabungan, User user, List<Transaksi> transaksi){
        this.deskripsiTabungan = deskripsiTabungan;
        this.user = user;
        this.transaksi = transaksi;
    }
    public DeskripsiTabungan getDeskripsiTabungan() {
        return deskripsiTabungan;
    }
    public void setDeskripsiTabungan(DeskripsiTabungan deskripsiTabungan) {
        this.deskripsiTabungan = deskripsiTabungan;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Transaksi> getTransaksi() {
        return transaksi;
    }
    public void setTransaksi(List<Transaksi> transaksi) {
        this.transaksi = transaksi;
    }
}
