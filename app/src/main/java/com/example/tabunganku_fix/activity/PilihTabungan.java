package com.example.tabunganku_fix.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.adapter.PilihTabungan_Adapter;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.models.JenisTabungan;
import com.example.tabunganku_fix.response.JenisTabunganResponse;
import com.example.tabunganku_fix.response.LogoutResponse;
import com.example.tabunganku_fix.server.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihTabungan extends AppCompatActivity implements View.OnClickListener {
    Context mContext;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    CardView cardView;
    ProgressBar bar;
    LinearLayout hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tabungan);
        Button btnLogout = findViewById(R.id.keluar);
        cardView = findViewById(R.id.pilihButton);
        bar = findViewById(R.id.bar_pilih_tabungan);
        hide = findViewById(R.id.pilih_tabungan_hide);

        //LOGOUT
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutConfirm();
            }
             /*button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PilihTabungan.this, MainActivity_TabunganA.class);
                    startActivity(intent);
                    finish();
                }
            });
        button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PilihTabungan.this, MainActivity_TabunganB.class);
                    startActivity(intent);
                    finish();
                }
            });
        button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PilihTabungan.this, MainActivity_TabunganC.class);
                    startActivity(intent);
                    finish();
                }
            });*/
        });
        requestPilihan();
    }

    public void requestPilihan(){
        Call<JenisTabunganResponse> call;
        call = RetrofitClient
                .getInstance()
                .getApi()
                .jenis_tabungan();
        call.enqueue(new Callback<JenisTabunganResponse>() {
            @Override
            public void onResponse(Call<JenisTabunganResponse> call, Response<JenisTabunganResponse> response) {
                ArrayList<JenisTabungan> itemList = new ArrayList<>();
                JenisTabunganResponse jenisTabunganResponse = response.body();
                if(response.isSuccessful()){
                    if(jenisTabunganResponse.getStatus().equals("success")){
                        for (JenisTabungan var: jenisTabunganResponse.getJenisTabungan()) {
                            itemList.add(new JenisTabungan(var.getId(), var.getNama(),
                                    var.getDeskripsi(), var.getAktif()));
                        }
                        recyclerView =  findViewById(R.id.recyclerview_pilih_tabungan);
                        recyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(PilihTabungan.this);
                        adapter = new PilihTabungan_Adapter(itemList);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        bar.setVisibility(View.GONE);
                        hide.setVisibility(View.VISIBLE);
                    }

                    else{
                        Toast.makeText(mContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JenisTabunganResponse> call, Throwable t) {

            }
        });
    }

    public void logoutConfirm(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Anda yakin ingin keluar?");
        alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //logout();
                SharedPrefManager.getInstance(PilihTabungan.this).clear();
                Intent intent = new Intent(PilihTabungan.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }


    @Override
    public void onClick(View v) {

    }

    public void logout(){
        String email = SharedPrefManager.getInstance(this).getUser().getEmail();
        Call<LogoutResponse> call;
        call = RetrofitClient
                .getInstance()
                .getApi()
                .logoutResponse(email);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(mContext, "Logout berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(mContext, "Terjadi kesalahan koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
