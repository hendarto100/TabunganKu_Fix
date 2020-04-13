package com.example.tabunganku_fix.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditResponse {
    @SerializedName("message")
    @Expose
    private String message;


    public  EditResponse(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}