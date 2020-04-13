package com.example.tabunganku_fix.response;

import com.example.tabunganku_fix.models.JenisTabungan;
import com.example.tabunganku_fix.models.Saldo;
import com.example.tabunganku_fix.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaldoResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data_saldo")
    @Expose
    private List<Saldo> data_saldo;

    @SerializedName("data_siswa")
    @Expose
    private User user;

    @SerializedName("data_jenis")
    @Expose
    private List<JenisTabungan> jenisTabungans;

    public SaldoResponse(String status, List<Saldo> data_saldo, User user, List<JenisTabungan> jenisTabungans){
        this.status=status;
        this.data_saldo=data_saldo;
        this.user=user;
        this.jenisTabungans = jenisTabungans;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Saldo> getData_saldo() {
        return data_saldo;
    }
    public void setData_saldo(List<Saldo> data_saldo) {
        this.data_saldo = data_saldo;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<JenisTabungan> getJenisTabungans() {
        return jenisTabungans;
    }
    public void setJenisTabungans(List<JenisTabungan> jenisTabungans) {
        this.jenisTabungans = jenisTabungans;
    }
}

