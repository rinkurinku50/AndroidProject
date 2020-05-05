package com.example.nestedrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.MyViewHolder> {

    ArrayList<String> parentArrayList;
    Context context;


    public ParentRecyclerAdapter(ArrayList<String> parentArrayList, Context context) {
        this.parentArrayList = parentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_rowlayout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     holder.ItemName.setText(parentArrayList.get(position));
     RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
     holder.ChildRV.setLayoutManager(layoutManager);
     holder.ChildRV.setHasFixedSize(true);
        ArrayList<String> dayArrayList=new ArrayList<>();
    if(parentArrayList.get(position).equals("Item One"))
    {
        String [] days={"Mango","Orange","Apple","Gavava"};
        for(int i = 0; i < days.length;i++ )
        {
            dayArrayList.add(days[i]);
        }
    }
    else {
        String [] days={"Sat","Sun","Mon","FRI","WED","THU"};
        for(int i = 0; i < days.length;i++ )
        {
            dayArrayList.add(days[i]);
        }
    }

     ChildRecyclerAdapter childRecyclerAdapter=new ChildRecyclerAdapter(dayArrayList);
     holder.ChildRV.setAdapter(childRecyclerAdapter);
     childRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ItemName;
        RecyclerView ChildRV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName=itemView.findViewById(R.id.itemname);
            ChildRV=itemView.findViewById(R.id.childRV);
        }
    }
}
