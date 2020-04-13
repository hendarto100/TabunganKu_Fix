package com.example.tabunganku_fix.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabunganku_fix.MainActivity_TabunganA;
import com.example.tabunganku_fix.MainActivity_TabunganB;
import com.example.tabunganku_fix.MainActivity_TabunganC;
import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.models.JenisTabungan;

import java.util.ArrayList;

public class PilihTabungan_Adapter extends RecyclerView.Adapter<PilihTabungan_Adapter.ViewHolder> {
    Context mContext;
    ArrayList<JenisTabungan> pilihTabunganList;

    public PilihTabungan_Adapter(ArrayList<JenisTabungan> pilihTabunganList){
        this.pilihTabunganList=pilihTabunganList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pilih_tabungan, parent, false);
        PilihTabungan_Adapter.ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final JenisTabungan jenisTabungan = pilihTabunganList.get(position);
        if(jenisTabungan.getId()==1){
            holder.nama_jenis_tabungan.setText(jenisTabungan.getNama());
            holder.cardView.setCardBackgroundColor(Color.parseColor("#00CA59"));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(jenisTabungan.getAktif()==1){
                        Intent intent = new Intent(v.getContext(), MainActivity_TabunganA.class);
                        v.getContext().startActivity(intent);
                    }
                    else{
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                        alertDialog.setTitle("Pemberitahuan");
                        alertDialog.setMessage("Tabungan belum masa periode");
                        alertDialog.setPositiveButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = alertDialog.create();
                        alert.show();
                    }
                }
            });
        }
        if(jenisTabungan.getId()==2){
            holder.nama_jenis_tabungan.setText(jenisTabungan.getNama());
            holder.cardView.setCardBackgroundColor(Color.parseColor("#00BA52"));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(jenisTabungan.getAktif()==1){
                        Intent intent = new Intent(v.getContext(), MainActivity_TabunganB.class);
                        v.getContext().startActivity(intent);
                    }
                    else{
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                        alertDialog.setTitle("Pemberitahuan");
                        alertDialog.setMessage("Tabungan belum masa periode");
                        alertDialog.setPositiveButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = alertDialog.create();
                        alert.show();
                    }
                }
            });
        }
        if(jenisTabungan.getId()==3){
            holder.nama_jenis_tabungan.setText(jenisTabungan.getNama());
            holder.cardView.setCardBackgroundColor(Color.parseColor("#009A44"));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(jenisTabungan.getAktif()==1){
                        Intent intent = new Intent(v.getContext(), MainActivity_TabunganC.class);
                        v.getContext().startActivity(intent);
                    }
                    else{
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                        alertDialog.setTitle("Pemberitahuan");
                        alertDialog.setMessage("Tabungan belum masa periode");
                        alertDialog.setPositiveButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = alertDialog.create();
                        alert.show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pilihTabunganList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_jenis_tabungan;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_jenis_tabungan = itemView.findViewById(R.id.nama_tabungan);
            cardView = itemView.findViewById(R.id.pilihButton);
        }
    }

    /*public void tabunganConfirm(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Pemberitahuan");
        alertDialog.setMessage("Tabungan ini belum diambil, silahkan menghubungi pihak sekolah");
        alertDialog.setPositiveButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }*/

}
