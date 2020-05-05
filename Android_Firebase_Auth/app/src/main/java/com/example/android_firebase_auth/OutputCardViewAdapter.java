package com.example.android_firebase_auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_firebase_auth.CURD.ModelData;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OutputCardViewAdapter extends RecyclerView.Adapter<OutputCardViewAdapter.MyviewHolder> {

    Context context;
    ArrayList<ModelData> responseData;

    public OutputCardViewAdapter(Context context, ArrayList<ModelData> responseData) {
        this.context = context;
        this.responseData = responseData;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyviewHolder viewHolder = null;
       View v= LayoutInflater.from(context).inflate(R.layout.result_card_view,parent,false);

        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {


           holder.title_text.setText(responseData.get(position).getFirstname());
           holder.post_text.setText(responseData.get(position).getLastname());
           holder.description_text.setText(responseData.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return responseData.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder
    {
        TextView title_text,post_text,description_text;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            title_text=itemView.findViewById(R.id.title_text);
            post_text=itemView.findViewById(R.id.post_text);
            description_text=itemView.findViewById(R.id.description_text);
        }
    }
}
