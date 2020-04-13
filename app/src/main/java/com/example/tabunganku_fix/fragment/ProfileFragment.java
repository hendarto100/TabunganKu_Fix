package com.example.tabunganku_fix.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.models.User;
import com.example.tabunganku_fix.response.ProfileResponse;
import com.example.tabunganku_fix.server.SharedPrefManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    ProfileViewModel mViewModel;
    TextView nama_lengkap, nis, kelas, angkatan, email;
    ScrollView hide;
    User user = SharedPrefManager.getInstance(getContext()).getUser();
    ProgressDialog loading;
    ProgressBar bar;
    CircleImageView profile;
    String url = "http://192.168.100.10/TugasAkhir_TabunganKu/public/img/";

/*    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        profile=v.findViewById(R.id.profil_pic);
        nama_lengkap = v.findViewById(R.id.nama_profil);
        nis = v.findViewById(R.id.nis_profi2);
        kelas = v.findViewById(R.id.kelas_profi2);
        angkatan = v.findViewById(R.id.tahun_profi2);
        email = v.findViewById(R.id.email_profi2);
        hide = v.findViewById(R.id.profile_hide);
        bar = v.findViewById(R.id.bar);
        String token = user.getToken();
        Call<ProfileResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .ProfileSiswa(token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse = response.body();
                if (response.isSuccessful()) {
                    if (profileResponse.getStatus().equals("success")) {
                        Log.i("info", "check img path"+user.getAvatar());
                        Picasso.with(getContext()).load(url+user.getAvatar()).into(profile);
                        nama_lengkap.setText(user.getNama());
                        nis.setText(String.valueOf(user.getNis()));
                        kelas.setText(user.getKelas());
                        angkatan.setText(String.valueOf(user.getAngkatan()));
                        email.setText(user.getEmail());

                        bar.setVisibility(View.GONE);
                        hide.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
            }
        });

        return v;
    }
}

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }*/

