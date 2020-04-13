package com.example.tabunganku_fix.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tabunganku_fix.MainActivity_TabunganC;
import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.adapter.JenisTabungan_Adapter;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.models.JenisTabungan;
import com.example.tabunganku_fix.response.JenisTabunganResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_tabunganC extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tabungan_c);
        Button button = findViewById(R.id.kembali_home_c);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_tabunganC.this, MainActivity_TabunganC.class);
                startActivity(intent);
                finish();
            }
        });
        detail_tabungan();
    }
    public void detail_tabungan(){
        Call<JenisTabunganResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .jenis_tabungan();
        call.enqueue(new Callback<JenisTabunganResponse>() {
            @Override
            public void onResponse(Call<JenisTabunganResponse> call, Response<JenisTabunganResponse> response) {
                ArrayList<JenisTabungan> itemList = new ArrayList<>();
                JenisTabunganResponse jenisTabunganResponse = response.body();
                if(response.isSuccessful()){
                    if(jenisTabunganResponse.getStatus().equals("success")) {
                        for (JenisTabungan var: jenisTabunganResponse.getJenisTabungan()) {
                            if(var.getId()==3)
                                itemList.add(new JenisTabungan(var.getId(), var.getNama(),
                                        var.getDeskripsi(), var.getAktif()));
                        }
                        recyclerView =  findViewById(R.id.recyclerview_detail_c);
                        recyclerView.setHasFixedSize(true);

                        layoutManager = new LinearLayoutManager(Detail_tabunganC.this);
                        adapter = new JenisTabungan_Adapter(itemList);

                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<JenisTabunganResponse> call, Throwable t) {

            }
        });



    }
}
