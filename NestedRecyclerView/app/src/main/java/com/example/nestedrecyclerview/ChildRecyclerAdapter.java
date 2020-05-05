package com.example.nestedrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.MyViewHolder> {

    public ChildRecyclerAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    ArrayList<String> arrayList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rowlayout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.Days.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Days;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Days=itemView.findViewById(R.id.daytxt);
        }
    }

}
