package com.example.tabunganku_fix.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.models.User;
import com.example.tabunganku_fix.response.EditResponse;
import com.example.tabunganku_fix.response.LoginResponse;
import com.example.tabunganku_fix.server.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaturanFragment extends Fragment {
    EditText edit_no;
    Button save_no;
    Intent intent;
    User user = SharedPrefManager.getInstance(getContext()).getUser();
    LoginResponse loginResponse;
    Context mContext;
    ProgressBar bar;
    ScrollView hide;
    ProgressDialog progress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pengaturan_tabungan, container, false);
        edit_no = v.findViewById(R.id.telp_edit_setting);
        save_no = v.findViewById(R.id.save_settings);
       /*  bar = v.findViewById(R.id.bar_pengaturan);
         hide = v.findViewById(R.id.scroll_pengaturan);*/
        edit_no.setText(user.getTelp_ortu().trim());
        save_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = new ProgressDialog(getContext());
                progress.setCancelable(false);
                progress.setMessage("Loading...");
                progress.show();
                edit();
            }
        });
        return v;
    }
    public void edit(){
        final String token = user.getToken();
                /* bar.setVisibility(View.GONE);
                 hide.setVisibility(View.VISIBLE);*/
        Call<EditResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .UpdateTelepon(
                        token,
                        edit_no.getText().toString());
        call.enqueue(new Callback<EditResponse>() {
            @Override
            public void onResponse(Call<EditResponse> call, Response<EditResponse> response) {
                String message = response.body().getMessage();
                progress.dismiss();
                if (response.isSuccessful()){
                    if(message.equals("Edit nomor telepon sukses")){
                        Toast.makeText(getContext(), "No telepon berhasil diubah", Toast.LENGTH_SHORT).show();
                        reload();
                    }
                }
                else{
                    Toast.makeText(getContext(), "No telepon gagal diubah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EditResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Terjadi Kesalahan", Toast.LENGTH_SHORT);
            }

        });
    }
    public void reload(){
        //getActivity().getSupportFragmentManager().beginTransaction().replace(PengaturanFragment.this.getId(), new PengaturanFragment()).commit();
        PengaturanFragment pengaturan = new PengaturanFragment();
        FragmentTransaction fragment = getFragmentManager().beginTransaction();
        fragment.detach(pengaturan);
        fragment.replace(R.id.nav_tools, pengaturan);
        fragment.addToBackStack(null);
        fragment.commit();

    }
}
