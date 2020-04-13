package com.example.tabunganku_fix.api_helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //public static final String BASE_URL_API = " http://10.33.83.80/TugasAkhir_TabunganKu/public/api/";
    //zpublic static final String BASE_URL_API = " http://192.168.100.8/TugasAkhir_TabunganKu/public/api/";
    //public static final String BASE_URL_API = " http://10.33.74.40/TugasAkhir_TabunganKu/public/api/";
    //public static final String BASE_URL_API = " http://10.72.54.123/TugasAkhir_TabunganKu/public/api/";
    //public static final String BASE_URL_API = " http://192.168.43.232/TugasAkhir_TabunganKu/public/api/";
    //public static final String BASE_URL_API = " http://192.168.100.248/TugasAkhir_TabunganKu/public/api/";
    public static final String BASE_URL_API = " http://192.168.100.10/TugasAkhir_TabunganKu/public/api/";
    //public static final String BASE_URL_API = " http://192.168.100.25/TugasAkhir_TabunganKu/public/api/";
    //public static final String BASE_URL_API = "http://10.0.2.2:8000/";
    //public static final String BASE_URL_API = "http://10.33.80.134:8383/";

    private static Retrofit retrofit;
    private static RetrofitClient mInstance;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    public static synchronized RetrofitClient getInstance()
    {
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public BaseApiService getApi(){
        return retrofit.create(BaseApiService.class);
    }
}