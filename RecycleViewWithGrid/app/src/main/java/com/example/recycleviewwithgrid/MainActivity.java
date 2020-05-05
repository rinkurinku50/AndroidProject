package com.example.recycleviewwithgrid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<BookModel> lstBook ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstBook = new ArrayList<>();
        lstBook.add(new BookModel("The Vegitarian","Categorie Book","Description book",R.drawable.thevigitarian));
        lstBook.add(new BookModel("The Wild Robot","Categorie Book","Description book",R.drawable.thewildrobot));
        lstBook.add(new BookModel("Maria Semples","Categorie Book","Description book",R.drawable.mariasemples));
        lstBook.add(new BookModel("The Martian","Categorie Book","Description book",R.drawable.themartian));
        lstBook.add(new BookModel("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));
        lstBook.add(new BookModel("The Vegitarian","Categorie Book","Description book",R.drawable.thevigitarian));
        lstBook.add(new BookModel("The Wild Robot","Categorie Book","Description book",R.drawable.thewildrobot));
        lstBook.add(new BookModel("Maria Semples","Categorie Book","Description book",R.drawable.mariasemples));
        lstBook.add(new BookModel("The Martian","Categorie Book","Description book",R.drawable.themartian));
        lstBook.add(new BookModel("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));
        lstBook.add(new BookModel("The Vegitarian","Categorie Book","Description book",R.drawable.thevigitarian));
        lstBook.add(new BookModel("The Wild Robot","Categorie Book","Description book",R.drawable.thewildrobot));
        lstBook.add(new BookModel("Maria Semples","Categorie Book","Description book",R.drawable.mariasemples));
        lstBook.add(new BookModel("The Martian","Categorie Book","Description book",R.drawable.themartian));
        lstBook.add(new BookModel("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(this,lstBook);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
//       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);


    }
}
