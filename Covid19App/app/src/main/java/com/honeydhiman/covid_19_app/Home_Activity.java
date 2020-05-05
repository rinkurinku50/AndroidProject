package com.honeydhiman.covid_19_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.honeydhiman.covid_19_app.Models.GlobalDataModel;
import com.honeydhiman.covid_19_app.RecycleAdapter.GlobalAdapter;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity {

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;


    RecyclerView globalRecycler;
    SimpleArcLoader simpleArcLoader;
    CardView cardView;
    LinearLayout lowerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pieChart=findViewById(R.id.piechart);
        globalRecycler=findViewById(R.id.globalRecycler);
        simpleArcLoader=findViewById(R.id.loader);
        cardView=findViewById(R.id.cardLayout);
        lowerLayout=findViewById(R.id.lowerLayout);

        simpleArcLoader.setVisibility(View.VISIBLE);

        getSupportActionBar().setTitle("Global Cases");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                fetchData();

            }
        },1000);



    }


   private void  fetchData()
   {

    String url="https://corona.lmao.ninja/v2/all";

       StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject jsonObject=new JSONObject(response);
                   String totalCases=jsonObject.getString("cases");
                   String deaths=jsonObject.getString("deaths");
                   String recovered=jsonObject.getString("recovered");
                   String active=jsonObject.getString("active");
                   String todayCases=jsonObject.getString("todayCases");
                   String critical=jsonObject.getString("critical");


//                   Log.i("rinku",totalCases);
//                   Log.i("rinku",deaths);
//                   Log.i("rinku",recovered);
//                   Log.i("rinku",active);

                   /////chart code

                   pieEntries = new ArrayList<>();
                   pieEntries.add(new PieEntry(Float.valueOf(recovered)));
                   pieEntries.add(new PieEntry(Float.valueOf(totalCases)));
                   pieEntries.add(new PieEntry(Float.valueOf(deaths)));
                   pieEntries.add(new PieEntry(Float.valueOf(active)));


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

                   /////chart code end
                   ArrayList<GlobalDataModel> casesDa=new ArrayList<>();
                   casesDa.add(new GlobalDataModel("cases",totalCases));
                   casesDa.add(new GlobalDataModel("recovered",recovered));
                   casesDa.add(new GlobalDataModel("deaths",deaths));
                   casesDa.add(new GlobalDataModel("active",active));
                   casesDa.add(new GlobalDataModel("today cases",todayCases));
                   casesDa.add(new GlobalDataModel("critical",critical));


                   cardView.setVisibility(View.VISIBLE);
                   lowerLayout.setVisibility(View.VISIBLE);
                   GlobalAdapter globalAdapter=new GlobalAdapter(casesDa,Home_Activity.this);
                   globalRecycler.setLayoutManager(new LinearLayoutManager(Home_Activity.this));
                   globalRecycler.setAdapter(globalAdapter);

                   simpleArcLoader.stop();
                   simpleArcLoader.setVisibility(View.GONE);

               } catch (JSONException e) {
                   e.printStackTrace();
                   simpleArcLoader.stop();
                   simpleArcLoader.setVisibility(View.GONE);

               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               simpleArcLoader.stop();
               simpleArcLoader.setVisibility(View.GONE);

               Toast.makeText(Home_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });

       RequestQueue requestQueue= Volley.newRequestQueue(this);
       requestQueue.add(stringRequest);
   }


    public void searchActivity(View view) {
    startActivity(new Intent(Home_Activity.this,SearchCountriesActivity.class));

    }
}
