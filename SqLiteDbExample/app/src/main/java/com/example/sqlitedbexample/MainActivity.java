package com.example.sqlitedbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextView name, contact;
    Button button_send;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name_text);
        contact = findViewById(R.id.contact_text);
        button_send = findViewById(R.id.button_setdata);
        recyclerView=findViewById(R.id.recycler_view);

        readData();
        final DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String res = databaseHandler.insertData(new Contact(name.getText().toString(), contact.getText().toString()));

                Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();
                name.setText("");
                contact.setText("");
                readData();
            }
        });


    }


    public void readData()
    {

        DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);

        ArrayList<Contact> contacts = databaseHandler.readData();

        Log.d("Name: ", contacts.toString());

//        for (Contact cn : contacts) {
//            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " +
//                    cn.getContact();
//            // Writing Contacts to log
//            Log.d("Name: ", log);
//
//        }

       if (!contacts.isEmpty()) {
           ResultRecyclerViewAdapter resultRecyclerViewAdapter = new ResultRecyclerViewAdapter(MainActivity.this, contacts);
           recyclerView.setLayoutManager(new LinearLayoutManager(this));
           recyclerView.setAdapter(resultRecyclerViewAdapter);
           resultRecyclerViewAdapter.notifyDataSetChanged();
       }
       else {

           Toast.makeText(this, "No data Found", Toast.LENGTH_SHORT).show();
       }

    }






}