package com.example.recycleviewwithgrid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Book_Activity extends AppCompatActivity {

    TextView til;
    TextView des;
    TextView cat;
    ImageView imageThum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        til=findViewById(R.id.book_title);
        des=findViewById(R.id.book_description);
        cat=findViewById(R.id.book_category);
        imageThum=findViewById(R.id.bookthumbnail);
        Intent intent=getIntent();
        String Title=intent.getExtras().getString("Title");
        String Description=intent.getExtras().getString("Description");
        String Category=intent.getExtras().getString("Category");
        int image=intent.getExtras().getInt("Thumbnail");

        til.setText(Title);
        des.setText(Description);
        cat.setText(Category);
        imageThum.setImageResource(image);



    }
}
