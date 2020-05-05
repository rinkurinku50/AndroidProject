package com.example.fragmentwithrecycleview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
  Context context;
  ArrayList<ContactModel> mData;
  Dialog dialog;

    public RecyclerViewAdapter(Context context, ArrayList<ContactModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        final MyViewHolder myViewHolder = new MyViewHolder(v);

//       Dialog in

        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_contact);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        myViewHolder.item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView dialog_name=dialog.findViewById(R.id.dialog_name_id);
                TextView dialog_phone=dialog.findViewById(R.id.dialog_phone_id);
                ImageView dialog_contact_img=dialog.findViewById(R.id.dialog_img);

                dialog_name.setText(mData.get(myViewHolder.getAdapterPosition()).getName());
                dialog_phone.setText(mData.get(myViewHolder.getAdapterPosition()).getPhone());
                dialog_contact_img.setImageResource(mData.get(myViewHolder.getAdapterPosition()).getPhoto());




                Toast.makeText(context, "Text Click"+String.valueOf(myViewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                dialog.show();
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_phone.setText(mData.get(position).getPhone());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        private LinearLayout item_contact;
        TextView tv_name;
        TextView tv_phone;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_contact=itemView.findViewById(R.id.contact_item_id);
            tv_name=itemView.findViewById(R.id.name_contact);
            tv_phone=itemView.findViewById(R.id.phone_contact);
            img=itemView.findViewById(R.id.img_contact);
        }
    }

}
