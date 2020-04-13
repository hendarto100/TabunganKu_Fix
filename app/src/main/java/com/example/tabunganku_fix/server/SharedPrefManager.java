package com.example.tabunganku_fix.server;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tabunganku_fix.models.DeskripsiTabungan;
import com.example.tabunganku_fix.models.JenisTabungan;
import com.example.tabunganku_fix.models.Saldo;
import com.example.tabunganku_fix.models.Transaksi;
import com.example.tabunganku_fix.models.User;
import com.example.tabunganku_fix.response.LoginResponse;

public class SharedPrefManager {
    private static final String SP = "sp";

    private SharedPreferences sharedPreferences;
    private static SharedPrefManager mInstance;
    private Context mContext;

    private SharedPrefManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized SharedPrefManager getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mContext);
        }
        return mInstance;
    }

    public boolean isLoggedin(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1)!=-1;
    }

    //User
    public LoginResponse saveUser(User status) {
        sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", status.getId());
        editor.putString("nis", status.getNis());
        editor.putString("nama", status.getNama());
        editor.putString("kelas", status.getKelas());
        editor.putInt("angkatan", status.getAngkatan());
        editor.putString("telp_ortu", status.getTelp_ortu());
        editor.putString("email", status.getEmail());
        editor.putString("nama_pengguna", status.getNama_pengguna());
        editor.putString("token", status.getToken());
        editor.putInt("aktif", status.getAktif());
        editor.putString("avatar", status.getAvatar());
        editor.apply();
        return null;
    }
    public User getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("nis", null),
                sharedPreferences.getString("nama", null),
                sharedPreferences.getString("kelas", null),
                sharedPreferences.getInt("angkatan", 0),
                sharedPreferences.getString("telp_ortu",null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("nama_pengguna", null),
                sharedPreferences.getString("token", null),
                sharedPreferences.getInt("aktif", 0),
                sharedPreferences.getString("avatar", null)
        );
    }

    //TRANSAKSI SISWA
    public Transaksi getTransaksi() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        return new Transaksi(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("kode_transaksi", null),
                sharedPreferences.getString("nis", null),
                sharedPreferences.getString("jenis_tabungan", null),
                sharedPreferences.getString("jenis_transaksi", null),
                sharedPreferences.getInt("nominal", -1),
                sharedPreferences.getString("created_at", null));
    }
    public void saveTransaksi(Transaksi transaksi){
        sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", transaksi.getId());
        editor.putString("kode_transaksi", transaksi.getKode_transaksi());
        editor.putString("nis", transaksi.getNis());
        editor.putString("jenis_tabungan", transaksi.getJenis_tabungan());
        editor.putString("jenis_transaksi", transaksi.getJenis_transaksi());
        editor.putInt("nominal", transaksi.getNominal());
        editor.putInt("created_at", transaksi.getNominal());
        /* editor.putBoolean("aktif", jenisTabungan.getAktif());*/
    }

    //JENIS TABUNGAN
    public JenisTabungan getJenisTabungan(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        return new JenisTabungan(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("nama", null),
                sharedPreferences.getString("deskripsi", null),
                sharedPreferences.getInt("aktif", -1)
        );
    }
    public void saveJenisTabungan(JenisTabungan jenisTabungan){
        sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", jenisTabungan.getId());
        editor.putString("nama", jenisTabungan.getNama());
        editor.putString("deskripsi", jenisTabungan.getDeskripsi());
        editor.putInt("aktif", jenisTabungan.getAktif());
    }

    //DETAIL TABUNGAN
    public DeskripsiTabungan getDetailTabungan(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        return new DeskripsiTabungan(
                sharedPreferences.getString("nis", null),
                sharedPreferences.getString("jenis_tabungan", null),
                sharedPreferences.getString("status", null),
                sharedPreferences.getString("deskripsi", null),
                sharedPreferences.getString("total_saldo", null),
                sharedPreferences.getString("wali_kelas", null),
                sharedPreferences.getInt("nominal_terakhir", 0),
                sharedPreferences.getString("jenis_transaksi", null)
        );
    }
    public void saveDetailTabungan(DeskripsiTabungan detailTabungan){
        sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nis", detailTabungan.getNis());
        editor.putString("jenis_tabungan", detailTabungan.getJenis_tabungan());
        editor.putString("status", detailTabungan.getStatus());
        editor.putString("deskripsi", detailTabungan.getDeskripsi());
        editor.putString("total_Saldo", detailTabungan.getTotal_saldo());
        editor.putString("wali_kelas", detailTabungan.getWali_kelas());
        editor.putInt("nominal_terakhir", detailTabungan.getNominal_terakhir());
        editor.putString("jenis_transaksi", detailTabungan.getJenis_transaksi());


    } public Saldo getSaldo(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        return new Saldo(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("nis", null),
                sharedPreferences.getString("jenis_tabungan", null),
                sharedPreferences.getInt("saldo", 0),
                sharedPreferences.getString("periode", null),
                sharedPreferences.getString("status_tabungan", null)
        );
    }
    public void getSaldo(Saldo saldo){
        sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", saldo.getId());
        editor.putString("nis", saldo.getNis());
        editor.putString("jenis_tabungan", saldo.getJenis_tabungan());
        editor.putInt("saldo", saldo.getSaldo());
        editor.putString("periode", saldo.getPeriode());
        editor.putString("status_tabungan", saldo.getStatus_tabungan());
    }




    public void clear() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(SP).apply();
        sharedPreferences.edit().clear().commit();
    }

}
