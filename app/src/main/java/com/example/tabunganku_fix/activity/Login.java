package com.example.tabunganku_fix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tabunganku_fix.R;
import com.example.tabunganku_fix.api_helper.BaseApiService;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.response.LoginResponse;
import com.example.tabunganku_fix.server.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button button_login, button_lupa_sandi;
    ProgressDialog loading;
    CheckBox checkBox_password;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        initComponents();
        lupaPassword();

        checkBox_password = findViewById(R.id.chechbox);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        checkBox_password.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(!isChecked){
                            //Show Password
                            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                        }
                        else{
                            //Hide Password
                            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                    }
                }
        );
    }
    public void onStart(){
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedin()){
            Intent intent = new Intent(mContext, PilihTabungan.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void requestLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            loading.dismiss();
            etEmail.setError("Email tidak boleh kosong");
            etEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loading.dismiss();
            etEmail.setError("Email yang dimasukan salah");
            etEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            loading.dismiss();
            etPassword.setError("Password tidak boleh kosong");
            etPassword.requestFocus();
            return;
        }

        Call<LoginResponse> call;
        call = RetrofitClient
                .getInstance()
                .getApi()
                .loginRequest(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful()) {
                    loading.dismiss();
                    if(loginResponse.getStatus().equals("success")) {
                        Log.i("debug", "Succesfull");
                        Log.i("debug", "tokens" + loginResponse.getUser().getToken());
                        SharedPrefManager.getInstance(Login.this).saveUser(loginResponse.getUser());
                        Intent intent = new Intent(mContext, PilihTabungan.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(mContext, "Email atau kata sandi salah", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }}

                else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Email atau kata sandi salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
            }
        });
    }

    private void initComponents() {
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true,
                        false);
                requestLogin();
            }
        });
    }

    private void lupaPassword(){
        button_lupa_sandi = findViewById(R.id.tombol_lupa_sandi);
        button_lupa_sandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Pemberitahuan");
                alertDialog.setMessage("Jika mengalami kesulitan untuk masuk,\n" +
                        "silahkan hubungi pihak sekolah");
                alertDialog.setPositiveButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.show();
            }
        });
    }
}