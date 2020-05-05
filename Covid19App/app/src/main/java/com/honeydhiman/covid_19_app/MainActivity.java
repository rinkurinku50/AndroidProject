package com.honeydhiman.covid_19_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
             startActivity(new Intent(MainActivity.this,Home_Activity.class));
             finish();
           }
       },3000);

    }
}
