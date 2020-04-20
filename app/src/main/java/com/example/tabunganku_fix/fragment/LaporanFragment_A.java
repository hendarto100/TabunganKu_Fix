package com.example.tabunganku_fix.fragment;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.adapter.TabunganA_Adapter;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.models.Transaksi;
import com.example.tabunganku_fix.models.User;
import com.example.tabunganku_fix.response.TransaksiResponse;
import com.example.tabunganku_fix.server.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LaporanFragment_A extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RelativeLayout hide;
    ProgressBar bar;
    Context mContext;
    Bitmap bmp, test;
    int page =0;

    User user = SharedPrefManager.getInstance(getContext()).getUser();
    Transaksi transaksi = SharedPrefManager.getInstance(getContext()).getTransaksi();

    private LaporanViewModel_A laporanViewModelA;
    private MutableLiveData<List<Transaksi>> transaksiList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        laporanViewModelA =
                ViewModelProviders.of(this).get(LaporanViewModel_A.class);
        final View v = inflater.inflate(R.layout.fragment_laporan_tabungan_a, container, false);
        String token = user.getToken();

        bar = v.findViewById(R.id.bar_a);
        hide = v.findViewById(R.id.bar_laporan_a);

        Call<TransaksiResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .TransaksiSiswa(token);
        call.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                Log.i("test", "response");
                ArrayList<Transaksi> itemList = new ArrayList<>();
                TransaksiResponse responseTransaksi = response.body();
                if (response.isSuccessful()) {
                    for (Transaksi var : responseTransaksi.getTransaksi())
                        if(var.getJenis_tabungan().equals("Tabungan Wajib")){ {
                            itemList.add(new Transaksi(var.getId(), var.getKode_transaksi(),
                                    var.getNis(), var.getJenis_tabungan(), var.getJenis_transaksi(),
                                    var.getNominal(), var.getCreated_at()));}
                            recyclerView =  v.findViewById(R.id.recyclerview_a);
                            recyclerView.setHasFixedSize(true);

                            layoutManager = new LinearLayoutManager(getActivity());
                            adapter = new TabunganA_Adapter(itemList);

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
        return v;
    }
}