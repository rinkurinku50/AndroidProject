package com.example.recycleviewwithgrid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHandler>
{

    private Context context;
    ArrayList<BookModel>   mData;

    public RecyclerViewAdapter(Context context, ArrayList<BookModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.cardview_item_book,parent,false);

        return new MyViewHandler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHandler holder, final int position) {
     holder.book_title.setText(mData.get(position).getTitle());
     holder.book_image.setImageResource(mData.get(position).getThumbnail());

     //set on Click

     holder.cardview_id.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
//             Toast.makeText(context, "CardId:"+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
             Intent intent=new Intent(context,Book_Activity.class);

             //pass data to book activity

             intent.putExtra("Title",mData.get(position).getTitle());
             intent.putExtra("Description",mData.get(position).getDescription());
             intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
             intent.putExtra("Category",mData.get(position).getCategory());

             context.startActivity(intent);
         }
     });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHandler extends RecyclerView.ViewHolder
    {
       TextView book_title;
       ImageView book_image;
       CardView cardview_id;

        public MyViewHandler(@NonNull View itemView) {
            super(itemView);
            book_title=itemView.findViewById(R.id.book_title_id);
            book_image=itemView.findViewById(R.id.book_image_id);
            cardview_id=itemView.findViewById(R.id.cardview_id);
       }
    }
}
