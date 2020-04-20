package com.example.tabunganku_fix.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.models.JenisTabungan;

import java.util.ArrayList;

public class
JenisTabungan_Adapter extends RecyclerView.Adapter<JenisTabungan_Adapter.ViewHolder> {
    Context mContext;
    ArrayList<JenisTabungan> jenisTabunganList;


    public JenisTabungan_Adapter(ArrayList<JenisTabungan> jenisTabunganList){
        this.mContext=mContext;
        this.jenisTabunganList=jenisTabunganList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_jenis_tabungan, parent, false);
        JenisTabungan_Adapter.ViewHolder v = new ViewHolder(view);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JenisTabungan jenisTabungan = jenisTabunganList.get(position);
        holder.namaTabungan.setText(jenisTabungan.getNama());
        holder.detail_tabungan.setText(jenisTabungan.getDeskripsi());
        holder.detail_tabungan.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaTabungan, detail_tabungan, wali_kelas, nominal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTabungan = itemView.findViewById(R.id.desc_tabungan_a);
            detail_tabungan = itemView.findViewById(R.id.desc_home2_a);
        }
    }

    @Override
    public int getItemCount() {
        return jenisTabunganList.size();
    }
}
