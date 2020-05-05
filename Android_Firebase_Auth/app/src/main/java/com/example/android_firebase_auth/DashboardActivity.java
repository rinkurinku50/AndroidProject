package com.example.android_firebase_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_firebase_auth.CURD.ModelData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {

    String EmailName;

    private Button logoutbutton,addDataButton,readDataButton;

    TextView activeUser;

    EditText firstname,lastname,username,search;

    DatabaseReference mDatabaseReference;

    Query mDatabaseReference1;

    RecyclerView result_recylerview;

    long maxid=0;

    String fname,lname,uname,searchdata;

    ArrayList<ModelData> getDataInArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        logoutbutton=findViewById(R.id.logout);
        addDataButton=findViewById(R.id.add_data);
        readDataButton=findViewById(R.id.read_data);
        activeUser=findViewById(R.id.activeuser);
        firstname=findViewById(R.id.firstnameget);
        lastname=findViewById(R.id.lastnameget);
        username=findViewById(R.id.usernameget);
        search=findViewById(R.id.searchget);
        result_recylerview=findViewById(R.id.result_recylerview);

        ////////////////////////// logout and active user section


        EmailName=getIntent().getStringExtra("EmailName");
        activeUser.setText(EmailName);
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ////////// logout and active user section end






//        String email = "user@example.com";
        String domain = EmailName .substring(0,EmailName.indexOf("@"));

        mDatabaseReference=FirebaseDatabase.getInstance().getReference().child("data").child(domain);



        //////////// to get the number of item at firebase

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            Toast.makeText(DashboardActivity.this, String.valueOf(dataSnapshot.getChildrenCount()), Toast.LENGTH_LONG).show();

            if(dataSnapshot.exists())
            {
                maxid=dataSnapshot.getChildrenCount();
            }
     }
       @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




          ////////////////////  add button listener

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname=firstname.getText().toString();
                lname=lastname.getText().toString();
                uname=username.getText().toString();

                 if(fname.trim().equalsIgnoreCase("") || lname.trim().equalsIgnoreCase("") || uname.trim().equalsIgnoreCase(""))
                {
                    Toast.makeText(DashboardActivity.this, "Please enter all field", Toast.LENGTH_LONG).show();
                }
                else {

                     insertDataIntoFirebase(fname,lname,uname);
            }


            }
        });




     readDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             searchdata=search.getText().toString();

             ///check child exist or not

                DatabaseReference rootRef=  FirebaseDatabase.getInstance().getReference().child("data");


                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.hasChild(searchdata)) {
                            Toast.makeText(DashboardActivity.this, "Exist Child ", Toast.LENGTH_SHORT).show();

                 /// if child exist true then find search data
                  DatabaseReference databaseReferencesearch=  FirebaseDatabase.getInstance().getReference().child("data").child(searchdata);

                 databaseReferencesearch.addValueEventListener(new ValueEventListener() {
                        @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                        getDataInArrayList.removeAll(getDataInArrayList);
                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

                            ModelData getData = singleSnapshot.getValue(ModelData.class);


                            getDataInArrayList.add(getData);

                        }

                        Log.i("Rinku",String.valueOf(getDataInArrayList.size()));

                        OutputCardViewAdapter outputCardViewAdapter=new OutputCardViewAdapter(DashboardActivity.this,getDataInArrayList);
                        result_recylerview.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                        result_recylerview.setAdapter(outputCardViewAdapter);
                        outputCardViewAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                       getDataInArrayList.removeAll(getDataInArrayList);
                        Toast.makeText(DashboardActivity.this, "There is no data found into firebase", Toast.LENGTH_SHORT).show();
                    }
                });




                        }
                        else
                        {
                            Toast.makeText(DashboardActivity.this, "not Exist Child ", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

//                DatabaseReference databaseReferencesearch=  FirebaseDatabase.getInstance().getReference().child("data").child(searchdata);
//
//             databaseReferencesearch.addValueEventListener(new ValueEventListener() {
//                    @Override
//
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//
//                        getDataInArrayList.removeAll(getDataInArrayList);
//                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
//
//                            ModelData getData = singleSnapshot.getValue(ModelData.class);
//
//
//                            getDataInArrayList.add(getData);
//
//                        }
//
//                        Log.i("Rinku",String.valueOf(getDataInArrayList.size()));
//
//                        OutputCardViewAdapter outputCardViewAdapter=new OutputCardViewAdapter(DashboardActivity.this,getDataInArrayList);
//                        result_recylerview.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
//                        result_recylerview.setAdapter(outputCardViewAdapter);
//                        outputCardViewAdapter.notifyDataSetChanged();
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(DashboardActivity.this, "There is no data found into firebase", Toast.LENGTH_SHORT).show();
//                    }
//                });









//                mDatabaseReference1 = FirebaseDatabase.getInstance().getReference("data")
//                        .child("1").child("username");
//                mDatabaseReference1.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Log.i("Home",dataSnapshot.getValue().toString());
////                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
////                            Log.i("Home",singleSnapshot.getValue().toString());
////                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });


            }
        });



         //////// readdata end



    }


    /////write data method

    public void insertDataIntoFirebase(final String firstname, final String lastname, final String username)
    {


                     ///// first way to write data into firebase

//        mDatabaseReference.child(String.valueOf(maxid+1)).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//              dataSnapshot.getRef().child("firstname").setValue(firstname);
//              dataSnapshot.getRef().child("lastname").setValue(lastname);
//              dataSnapshot.getRef().child("usernameS").setValue(username);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



            ///////second way to write data into firebase

     ModelData modelData=new ModelData(firstname,lastname,username);

     mDatabaseReference.child(String.valueOf(maxid+1)).setValue(modelData).addOnSuccessListener(new OnSuccessListener<Void>() {
         @Override
         public void onSuccess(Void aVoid) {
             Toast.makeText(DashboardActivity.this, "Successfully insert Record", Toast.LENGTH_LONG).show();
         }
     }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             Toast.makeText(DashboardActivity.this, "Failed to insert Record :", Toast.LENGTH_LONG).show();

         }
     });



    }

                   /////read data method

    public void readDataFromFirebase()
    {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getDataInArrayList.removeAll(getDataInArrayList);
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

                    ModelData getData = singleSnapshot.getValue(ModelData.class);


                    getDataInArrayList.add(getData);

                }

                Log.i("Rinku",String.valueOf(getDataInArrayList.size()));

                OutputCardViewAdapter outputCardViewAdapter=new OutputCardViewAdapter(DashboardActivity.this,getDataInArrayList);
                result_recylerview.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                result_recylerview.setAdapter(outputCardViewAdapter);
                outputCardViewAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DashboardActivity.this, "There is no data found into firebase", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
