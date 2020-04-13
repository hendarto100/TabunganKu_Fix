package com.example.tabunganku_fix.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.activity.Detail_tabunganA;
import com.example.tabunganku_fix.activity.Detail_tabunganB;
import com.example.tabunganku_fix.activity.Detail_tabunganC;
import com.example.tabunganku_fix.models.Saldo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SaldoTabungan_Adapter extends RecyclerView.Adapter<SaldoTabungan_Adapter.ViewHolder> {
    ArrayList<Saldo> saldoList;

    public SaldoTabungan_Adapter(ArrayList<Saldo> saldoList){
        this.saldoList = saldoList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kartu_saldo, parent, false);
        SaldoTabungan_Adapter.ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Saldo saldo = saldoList.get(position);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah =
                NumberFormat.getCurrencyInstance(localeID);
        holder.nominalSaldo.setText(formatRupiah.format(saldo.getSaldo()));
        holder.periode.setText(saldo.getPeriode());
        holder.status_tabungan.setText(saldo.getStatus_tabungan());
        if(saldo.getJenis_tabungan().equals("Tabungan Wajib")){
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Detail_tabunganA.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
        if(saldo.getJenis_tabungan().equals("Tabungan Prestasi")){
            holder.circle1.setImageResource(R.drawable.circle_orange);
            holder.circle2.setImageResource(R.drawable.circle_red);
            holder.circle3.setImageResource(R.drawable.circle_green);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Detail_tabunganB.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
        if(saldo.getJenis_tabungan().equals("Tabungan Wisata")){
            holder.circle1.setImageResource(R.drawable.circle_green);
            holder.circle2.setImageResource(R.drawable.circle_orange);
            holder.circle3.setImageResource(R.drawable.circle_red);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Detail_tabunganC.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return saldoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nominalSaldo, periode, status_tabungan;
        CardView cardView;
        ImageView circle1, circle2, circle3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nominalSaldo = itemView.findViewById(R.id.nominal_saldo);
            periode = itemView.findViewById(R.id.tahun);
            status_tabungan = itemView.findViewById(R.id.aktif_home);
            cardView = itemView.findViewById(R.id.card_home_a);
            circle1 = itemView.findViewById(R.id.circle_1);
            circle2 = itemView.findViewById(R.id.circle_2);
            circle3 = itemView.findViewById(R.id.circle_3);
        }
    }
}

