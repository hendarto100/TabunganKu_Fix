package com.example.tabunganku_fix.fragment;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.adapter.SaldoTabungan_Adapter;
import com.example.tabunganku_fix.adapter.TabunganA_Adapter_Home;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.models.DeskripsiTabungan;
import com.example.tabunganku_fix.models.Saldo;
import com.example.tabunganku_fix.models.Transaksi;
import com.example.tabunganku_fix.models.User;
import com.example.tabunganku_fix.response.SaldoResponse;
import com.example.tabunganku_fix.response.TransaksiResponse;
import com.example.tabunganku_fix.server.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment_B extends Fragment implements View.OnClickListener {

    private HomeViewModel_B homeViewModelB;
    User user = SharedPrefManager.getInstance(getContext()).getUser();
    Transaksi transaksi = SharedPrefManager.getInstance(getContext()).getTransaksi();
    DeskripsiTabungan desc = SharedPrefManager.getInstance(getContext()).getDetailTabungan();
    Context mContext;
    CardView card_home;

    ScrollView hide;
    ProgressBar bar;
    RecyclerView recyclerView, recyclerView1;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    String mText;
    TextView jenis_tabungan, periode, status, nama_pengguna, nominal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_home_tabungan_b, container, false);
        recyclerView1 = v.findViewById(R.id.recyclerview_card_home_b);
        nama_pengguna = v.findViewById(R.id.text_home);
        bar = v.findViewById(R.id.bar_b_home);
        hide = v.findViewById(R.id.scroll_b);
        jenis_tabungan = v.findViewById(R.id.nama_tabungan_home_b);
        String token = user.getToken();
        Call<TransaksiResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .TransaksiSiswa(token);
        call.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                ArrayList<Transaksi> itemList = new ArrayList<>();
                TransaksiResponse transaksiResponse = response.body();
                if(response.isSuccessful()){
                    nama_pengguna.setText("Selamat Datang, " +user.getNama());
                    for (Transaksi var : transaksiResponse.getTransaksi())
                        if(var.getJenis_tabungan().equals("Tabungan Prestasi")){
                            itemList.add(new Transaksi(var.getId(), var.getKode_transaksi(),
                                    var.getNis(), var.getJenis_tabungan(), var.getJenis_transaksi(),
                                    var.getNominal(), var.getCreated_at()));
                            recyclerView = v.findViewById(R.id.recyclerview_home_b);
                            recyclerView.setHasFixedSize(true);

                            layoutManager = new LinearLayoutManager(getActivity());
                            adapter = new TabunganA_Adapter_Home(itemList);

                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            bar.setVisibility(View.GONE);
                            hide.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Benar "+itemList.size(), Toast.LENGTH_SHORT).show();
                        }
                }
            }
            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
        show_saldo();
        return v;
    }

    public void onClick(View view) {
        /*if (view.getId()== R.id.card_home_b){
            Intent intent = new Intent(getActivity(), Detail_tabunganB.class);
            startActivity(intent);
        }*/
    }
    public void show_saldo(){
        String token = user.getToken();
        Call<SaldoResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .SaldoSiswa(token);
        call.enqueue(new Callback<SaldoResponse>() {
            @Override
            public void onResponse(Call<SaldoResponse> call, Response<SaldoResponse> response) {
                SaldoResponse saldoRersponse = response.body();
                ArrayList<Saldo> items = new ArrayList<>();
                if(response.isSuccessful()){
                    for (Saldo var:saldoRersponse.getData_saldo()) {
                        if(var.getJenis_tabungan().equals("Tabungan Prestasi"))
                            items.add(new Saldo(var.getId(), var.getNis(),
                                    var.getJenis_tabungan(), var.getSaldo(),
                                    var.getPeriode(), var.getStatus_tabungan()));
                        jenis_tabungan.setText(var.getJenis_tabungan());
                        recyclerView1.findViewById(R.id.recyclerview_card_home_b);
                        recyclerView1.setHasFixedSize(true);

                        layoutManager = new LinearLayoutManager(getActivity());
                        adapter = new SaldoTabungan_Adapter(items);

                        recyclerView1.setLayoutManager(layoutManager);
                        recyclerView1.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Terjadi kesalahan koneksi", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SaldoResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
