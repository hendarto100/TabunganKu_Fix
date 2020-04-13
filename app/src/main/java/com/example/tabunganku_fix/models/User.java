package com.example.tabunganku_fix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nis")
    @Expose
    private String nis;

    @SerializedName("nama_lengkap")
    @Expose
    private String nama;

    @SerializedName("kelas")
    @Expose
    private String kelas;

    @SerializedName("angkatan")
    @Expose
    private int angkatan;

    @SerializedName("telp_ortu")
    @Expose
    private String telp_ortu;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nama_pengguna")
    @Expose
    private String nama_pengguna;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("aktif")
    @Expose
    private int aktif;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    public User(int id, String nis, String nama,
                String kelas, int angkatan,
                String telp_ortu, String email,
                String nama_pengguna, String token, int aktif, String avatar) {
        this.id = id;
        this.nis = nis;
        this.nama = nama;
        this.kelas = kelas;
        this.angkatan = angkatan;
        this.telp_ortu = telp_ortu;
        this.email = email;
        this.nama_pengguna = nama_pengguna;
        this.token = token;
        this.aktif = aktif;
        this.avatar=avatar;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }

    public String getTelp_ortu() {
        return telp_ortu;
    }

    public void setTelp_ortu(String telp_ortu) {
        this.telp_ortu = telp_ortu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama_pengguna() {
        return nama_pengguna;
    }

    public void setNama_pengguna(String nama_pengguna) {
        this.nama_pengguna = nama_pengguna;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public int getAktif() {
        return aktif;
    }

    public void setAktif(int aktif) {
        this.aktif = aktif;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
