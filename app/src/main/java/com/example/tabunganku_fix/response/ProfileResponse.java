package com.example.tabunganku_fix.response;

import com.example.tabunganku_fix.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data_siswa")
    @Expose
    private User user;

    public ProfileResponse (String status, User user){
        this.status = status;
        this.user = user;
    }

    public String getStatus(){
        return status;
    }

    public User getUser(){
        return user;
    }
}
