package com.example.feedbacksystem;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersList extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_list);
        recyclerView = findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        processData();
    }
    //Function to fetch data from the database
    public void processData(){
        Call<List<ResponseHandler>>call=ApiController
                                        .getInstance()
                                        .getapi()
                                        .getdata();
        call.enqueue(new Callback<List<ResponseHandler>>() {
            @Override
            public void onResponse(Call<List<ResponseHandler>> call, Response<List<ResponseHandler>> response) {
                List<ResponseHandler> data = response.body();
                MyAdapter adapter = new MyAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ResponseHandler>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
