package com.example.tabunganku_fix.api_helper;

import com.example.tabunganku_fix.response.EditResponse;
import com.example.tabunganku_fix.response.JenisTabunganResponse;
import com.example.tabunganku_fix.response.LoginResponse;
import com.example.tabunganku_fix.response.LogoutResponse;
import com.example.tabunganku_fix.response.ProfileResponse;
import com.example.tabunganku_fix.response.SaldoResponse;
import com.example.tabunganku_fix.response.TransaksiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> loginRequest(
            @Field("email") String email,
            @Field("password") String password);

    @GET("logout/{email}")
    Call<LogoutResponse> logoutResponse(
            @Path("email") String email);

    @GET("detail_profile/{token}")
    Call<ProfileResponse> ProfileSiswa(
            @Path("token") String token);

    @GET("transaksi/{token}")
    Call<TransaksiResponse> TransaksiSiswa(
            @Path("token") String token);

    @GET("getSaldo/{token}")
    Call<SaldoResponse> SaldoSiswa(
            @Path("token") String token);


    @GET("tabungan")
    Call<JenisTabunganResponse> jenis_tabungan();

    @FormUrlEncoded
    @PUT("editTelp/{token}")
    Call<EditResponse> UpdateTelepon(
            @Path("token") String token,
            @Field("telp_ortu") String telp_ortu);
}
