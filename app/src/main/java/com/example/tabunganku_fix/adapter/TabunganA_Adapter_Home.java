package com.example.tabunganku_fix.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.models.Transaksi;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TabunganA_Adapter_Home extends RecyclerView.Adapter<TabunganA_Adapter_Home.ViewHolder> {
    Context mContext;
    ArrayList<Transaksi> transaksiList;

    public TabunganA_Adapter_Home(ArrayList<Transaksi> transaksiList){
        this.mContext = mContext;
        this.transaksiList = transaksiList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jenis_transaksi, tgl_transaksi, nominal;
        ImageView simbol;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simbol = itemView.findViewById(R.id.simbol_transaksi);
            jenis_transaksi = itemView.findViewById(R.id.kegiatanTransaksi);
            tgl_transaksi = itemView.findViewById(R.id.tanggalTransaksi);
            nominal = itemView.findViewById(R.id.nominalTransaksi);
        }
    }

    @NonNull
    @Override
    public TabunganA_Adapter_Home.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);
        TabunganA_Adapter_Home.ViewHolder v = new TabunganA_Adapter_Home.ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah =
                NumberFormat.getCurrencyInstance(localeID);
        Transaksi transaksi = transaksiList.get(position);
        String created_at = transaksi.getCreated_at();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Date date = format.parse(created_at);

            DateFormat df = new SimpleDateFormat("dd MMMM yyyy", localeID);
            holder.tgl_transaksi.setText(df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.jenis_transaksi.setText(transaksi.getJenis_transaksi());
        if(transaksi.getJenis_transaksi().equals("tarik")){
            holder.nominal.setText("- "+formatRupiah.format(transaksi.getNominal()));
        }
        else{
            holder.nominal.setText("+ "+formatRupiah.format(transaksi.getNominal()));
            holder.nominal.setTextColor(Color.parseColor("#009A44"));
            holder.simbol.setImageResource(R.drawable.setor);
        }
    }

    @Override
    public int getItemCount() {
        if (transaksiList != null) {
            return Math.min(transaksiList.size(), 3);
        } else {
            return 0;
        }
    }
}

