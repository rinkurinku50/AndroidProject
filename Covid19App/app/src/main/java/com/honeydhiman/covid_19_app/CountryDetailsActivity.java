package com.honeydhiman.covid_19_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class CountryDetailsActivity extends AppCompatActivity {
    TextView counCases,counDeaths,counRecovered,counActive,counCritical,counTodayCases,counTodayDeaths;
    ImageView countyD_image;

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        countyD_image=findViewById(R.id.countyD_image);
        counCases=findViewById(R.id.county_cases);
        counDeaths=findViewById(R.id.county_deaths);
        counRecovered=findViewById(R.id.county_recovered);
        counActive=findViewById(R.id.county_active);
        counCritical=findViewById(R.id.county_critical);
        counTodayCases=findViewById(R.id.county_todaycases);
        counTodayDeaths=findViewById(R.id.county_todaydeaths);
        pieChart=findViewById(R.id.countrypiechart);


        Intent getIntent=getIntent();
        String counrtyName=getIntent.getExtras().getString("countryName");

        String countryFlag=getIntent.getExtras().getString("countryFlag");

        Glide.with(this).load(countryFlag).into(countyD_image);

        String countryCases=getIntent.getExtras().getString("cases");

        String countryDeaths=getIntent.getExtras().getString("deaths");

        String countryRecovered=getIntent.getExtras().getString("recovered");

        String countryActive=getIntent.getExtras().getString("active");

        String countryCritical=getIntent.getExtras().getString("critical");

        String countryTodayCases=getIntent.getExtras().getString("todaycases");

        String countryTodayDeaths=getIntent.getExtras().getString("todaydeaths");

         counCases.setText(countryCases);
         counDeaths.setText(countryDeaths);
         counRecovered.setText(countryRecovered);
         counActive.setText(countryActive);
         counCritical.setText(countryCritical);
         counTodayCases.setText(countryTodayCases);
         counTodayDeaths.setText(countryTodayDeaths);


         ///// piechart code

        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(Float.valueOf(countryRecovered)));
        pieEntries.add(new PieEntry(Float.valueOf(countryCases)));
        pieEntries.add(new PieEntry(Float.valueOf(countryDeaths)));
        pieEntries.add(new PieEntry(Float.valueOf(countryActive)));


        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setDrawValues(false);
        pieDataSet.setSliceSpace(5f);
        pieChart.animateY(2000, Easing.Linear);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);







        ////////////





        ////toolbar back button
        getSupportActionBar().setTitle(counrtyName);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////
    }

    ///toolbar back button method
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
    //////
}
