package com.example.training2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs=numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 : {
                Tab1 tab1 = new Tab1();
                return tab1;
            }
            case 1 :{
                Tab2 tab2 = new Tab2();
                return tab2;
            }
            case 2 :{
                Tab3 tab3 = new Tab3();
                return tab3;
            }
            default:{
                return null;
            }

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            String title = "SQL LITE ";
            return title.subSequence(title.lastIndexOf(".") + 1, title.length());
        }else {
            String title = "ini tab " + (position + 1);
            return title.subSequence(title.lastIndexOf(".") + 1, title.length());
        }
    }
}
