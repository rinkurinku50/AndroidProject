package com.example.fragmentwithrecycleview;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

   private ArrayList<Fragment> lstfragment= new ArrayList<>();
   private ArrayList<String> lsttitle= new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
//        System.out.println("getItem "+lstfragment.get(position));
        return  lstfragment.get(position);
    }


    @Override
    public int getCount() {
//        System.out.println("getItem "+lsttitle.size());
        return lsttitle.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
//        System.out.println("getItem "+lsttitle.get(position));
        return lsttitle.get(position);
    }

    public void AddFragment(Fragment fragment,String title)
    {
      lstfragment.add(fragment);
      lsttitle.add(title);
    }

}
