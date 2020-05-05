package com.honeydhiman.covid_19_app.RecycleAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.honeydhiman.covid_19_app.CountryDetailsActivity;
import com.honeydhiman.covid_19_app.Models.CountriesDataModel;
import com.honeydhiman.covid_19_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder> implements Filterable {
   ArrayList<CountriesDataModel> coutriesData;
   ArrayList<CountriesDataModel> coutriesDataFiltered;
   Context context;

    public CountriesAdapter(ArrayList<CountriesDataModel> coutriesData, Context context) {
        this.coutriesData = coutriesData;
        this.coutriesDataFiltered = new ArrayList<>(coutriesData);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.coutries_cases_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
         holder.countryName.setText(coutriesData.get(position).getCountryname());
         Glide.with(context).load(coutriesData.get(position).getFlag()).into(holder.countryImage);

        holder.countrycardview_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CountryDetailsActivity.class);
                intent.putExtra("countryName",coutriesData.get(position).getCountryname());
                intent.putExtra("countryFlag",coutriesData.get(position).getFlag());
                intent.putExtra("cases",coutriesData.get(position).getCases());
                intent.putExtra("deaths",coutriesData.get(position).getDeaths());
                intent.putExtra("recovered",coutriesData.get(position).getRecovered());
                intent.putExtra("active",coutriesData.get(position).getActive());
                intent.putExtra("critical",coutriesData.get(position).getCritical());
                intent.putExtra("todaycases",coutriesData.get(position).getTodaycase());
                intent.putExtra("todaydeaths",coutriesData.get(position).getTodaydeath());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coutriesData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView countryImage;
        TextView countryName;
        CardView countrycardview_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName=itemView.findViewById(R.id.country_name);
            countryImage=itemView.findViewById(R.id.country_image);
            countrycardview_id=itemView.findViewById(R.id.countrycardview_id);
        }
    }

    /////filter data using search

    @Override
    public Filter getFilter() {
        return examplefilter;
    }

    private Filter examplefilter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
           ArrayList<CountriesDataModel> filerlist=new ArrayList<>();

           if(charSequence == null || charSequence.length() == 0)
           {
               filerlist.addAll(coutriesDataFiltered);
           }
           else
           {
               String filterPattern =charSequence.toString().toLowerCase().trim();

               for(CountriesDataModel item:coutriesDataFiltered)
               {
                  if(item.getCountryname().toLowerCase().startsWith(filterPattern))
                  {
                      filerlist.add(item);
                  }
               }

           }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filerlist;


            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
             coutriesData.clear();
             coutriesData.addAll((List)filterResults.values);
             notifyDataSetChanged();
        }
    };
}
