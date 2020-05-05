package com.honeydhiman.covid_19_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.honeydhiman.covid_19_app.Models.CountriesDataModel;
import com.honeydhiman.covid_19_app.RecycleAdapter.CountriesAdapter;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchCountriesActivity extends AppCompatActivity {

    SimpleArcLoader countryloader;
    ArrayList<CountriesDataModel> countryData;
    CountriesAdapter countriesAdapter;
    RecyclerView countryRecycler;
    EditText searchCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_countries);
        countryloader=findViewById(R.id.countryloader);
        countryRecycler=findViewById(R.id.countryRecycler);
        searchCountry=findViewById(R.id.searchCountry);
        ///toolbar back icon
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        ///

        searchCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              countriesAdapter.getFilter().filter(charSequence);
              countriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





        fetchCountryData();
    }

    ///toolbar back icon method

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()==android.R.id.home)
       {
           finish();
       }
        return super.onOptionsItemSelected(item);

    }


    ///////////////////////

    private void fetchCountryData()
    {
      String url="https://corona.lmao.ninja/v2/countries";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    countryData=new ArrayList<>();
                    JSONArray jsonArray=new JSONArray(response);

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String countryName=jsonObject.getString("country");
                        String cases=jsonObject.getString("cases");
                        String todayCases=jsonObject.getString("todayCases");
                        String deaths=jsonObject.getString("deaths");
                        String todayDeaths=jsonObject.getString("todayDeaths");
                        String recovered=jsonObject.getString("recovered");
                        String active=jsonObject.getString("active");
                        String critical=jsonObject.getString("critical");

                        JSONObject object=jsonObject.getJSONObject("countryInfo");
                        String flag=object.getString("flag");


                        countryData.add(new CountriesDataModel(countryName,flag,cases,todayCases,deaths,todayDeaths,recovered,active,critical));

                    }

                    countriesAdapter=new CountriesAdapter(countryData,SearchCountriesActivity.this);
                    countryRecycler.setLayoutManager(new GridLayoutManager(SearchCountriesActivity.this,3));
                    countryRecycler.setAdapter(countriesAdapter);

                    countryloader.stop();
                    countryloader.setVisibility(View.GONE);



                } catch (JSONException e) {
                    countryloader.stop();
                    countryloader.setVisibility(View.GONE);
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                countryloader.stop();
                countryloader.setVisibility(View.GONE);
                Toast.makeText(SearchCountriesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
