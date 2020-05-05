package com.example.sqlitedbexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ResultRecyclerViewAdapter extends RecyclerView.Adapter<ResultRecyclerViewAdapter.MyViewHolder> {

   Context context;
   ArrayList<Contact>  contactArrayList;
   public ResultRecyclerViewAdapter(Context context, ArrayList<Contact> contactArrayList) {
        this.context = context;
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.result_card_view,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
      holder.id.setText(String.valueOf(contactArrayList.get(position).getId()));
      holder.name.setText(contactArrayList.get(position).getName());
      holder.contactnumber.setText(contactArrayList.get(position).getContact());

      holder.cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Toast.makeText(context, String.valueOf(contactArrayList.get(position).getId()) , Toast.LENGTH_SHORT).show();
               DatabaseHandler databaseHandler=new DatabaseHandler(context);
               String res = databaseHandler.deleteById(String.valueOf(contactArrayList.get(position).getId()));
              Toast.makeText(context, res, Toast.LENGTH_SHORT).show();


          }
      });
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView id,name,contactnumber;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.contact_id);
            name= itemView.findViewById(R.id.contact_name);
            contactnumber= itemView.findViewById(R.id.contact_number);
            cardView= itemView.findViewById(R.id.card_view);
        }
    }
}
