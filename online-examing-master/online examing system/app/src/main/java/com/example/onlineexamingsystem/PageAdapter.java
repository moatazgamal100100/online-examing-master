package com.example.onlineexamingsystem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public  class PageAdapter extends FragmentPagerAdapter {
    int numoftabs;
    public PageAdapter(FragmentManager fm,int numOfTabs) {
        super(fm);
        this.numoftabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProfessorsRegisterList();
            case 1:
                return new StudentsRegisterList();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
