package com.example.feedbacksystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<ResponseHandler> data;

    public MyAdapter(List<ResponseHandler> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.singlerowdesign,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.t1.setText(data.get(position).getTeacher_name());
        holder.t2.setText(data.get(position).getTeacher_designation());
        Glide.with(holder.img.getContext()).load("https://192.168.1.163/FeedbackSystem/teacher_images/"+data.get(position).getTeacher_image())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView t1,t2;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.img);
            t1 = itemView.findViewById(R.id.name);
            t2 = itemView.findViewById(R.id.designation);
        }
    }
}
