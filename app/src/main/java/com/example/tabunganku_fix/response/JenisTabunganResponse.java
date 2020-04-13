package com.example.tabunganku_fix.response;

import com.example.tabunganku_fix.models.JenisTabungan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JenisTabunganResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data_jenis")
    @Expose
    private ArrayList<JenisTabungan> jenisTabungan;

    public JenisTabunganResponse(String status,  ArrayList<JenisTabungan>  jenisTabungan){
        this.status = status;
        this.jenisTabungan = jenisTabungan;
    }
    public String getStatus(){
        return status;
    }
    public void  setStatus(String status){
        this.status=status;
    }
    public  ArrayList<JenisTabungan>  getJenisTabungan(){
        return jenisTabungan;
    }
    public void setJenisTabungan( ArrayList<JenisTabungan>  jenisTabungan){
        this.jenisTabungan=jenisTabungan;
    }
}
