package com.example.feedbacksystem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiset {
    @GET("json_fetch_teachers.php/")
    Call<List<ResponseHandler>>getdata();
}
