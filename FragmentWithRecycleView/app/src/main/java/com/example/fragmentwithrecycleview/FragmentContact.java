package com.example.fragmentwithrecycleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentContact extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private ArrayList<ContactModel> lstContact;
    public FragmentContact() {
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.contacts_fragment,container,false);
        recyclerView= v.findViewById(R.id.contact_recycleview);
        RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getContext(),lstContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstContact=new ArrayList<>();
        lstContact.add(new ContactModel("Aaron jones","(111) 25253698",R.drawable.aalego));
        lstContact.add(new ContactModel("Aaron jones","(111) 25253698",R.drawable.bblego));
        lstContact.add(new ContactModel("Aaron jones","(111) 25253698",R.drawable.cclego));
        lstContact.add(new ContactModel("Aaron jones","(111) 25253698",R.drawable.ddlego));
        lstContact.add(new ContactModel("Aaron jones","(111) 25253698",R.drawable.eelego));
        lstContact.add(new ContactModel("Aaron jones","(111) 25253698",R.drawable.fflego));
        lstContact.add(new ContactModel("Aaron jones","(111) 25253698",R.drawable.gglego));
    }
}
