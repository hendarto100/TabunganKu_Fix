package com.example.tabunganku_fix.fragment;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tabunganku_fix.R;

public class InfoFragment extends Fragment {
    TextView info_sekolah, info_tabungan;
    private InfoViewModel infoViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        infoViewModel =
                ViewModelProviders.of(this).get(InfoViewModel.class);
        View v = inflater.inflate(R.layout.fragment_info_tabungan, container, false);
        info_sekolah = v.findViewById(R.id.text_info_sekolah);
        info_tabungan = v.findViewById(R.id.text_info_tabunganku);
        info_sekolah.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        info_tabungan.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        return v;
    }
}