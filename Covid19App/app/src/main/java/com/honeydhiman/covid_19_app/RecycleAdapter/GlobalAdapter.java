package com.honeydhiman.covid_19_app.RecycleAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.honeydhiman.covid_19_app.Models.GlobalDataModel;
import com.honeydhiman.covid_19_app.R;

import java.text.NumberFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GlobalAdapter extends RecyclerView.Adapter<GlobalAdapter.MyViewHolder> {
   ArrayList<GlobalDataModel> globalData;
   Context context;

    public GlobalAdapter(ArrayList<GlobalDataModel> globalData, Context context) {
        this.globalData = globalData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.global_cases_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.casesN.setText(globalData.get(position).getCasesName());
        holder.casesV.setText(NumberFormat.getInstance().format(Integer.parseInt(globalData.get(position).getCasesValue())));
        holder.casesV.setTextColor(Color.parseColor("#fb7268"));
    }

    @Override
    public int getItemCount() {
       return globalData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView casesN,casesV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            casesN=itemView.findViewById(R.id.casesName);
            casesV=itemView.findViewById(R.id.casesId);
        }
    }
}
