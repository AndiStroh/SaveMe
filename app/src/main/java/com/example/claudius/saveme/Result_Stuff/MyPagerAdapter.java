package com.example.claudius.saveme.Result_Stuff;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.claudius.saveme.Result_Stuff.Giftfinder_Tab;
import com.example.claudius.saveme.Result_Stuff.Girlfriend_Tab;


public class MyPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs = 2;



    public MyPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Girlfriend_Tab tab1 = new Girlfriend_Tab();

                return tab1;
            case 1:
                Giftfinder_Tab tab2 = new Giftfinder_Tab();
                return tab2;

            default:
                return null;
        }
    }

    @Override

    public int getCount() {
        return mNumOfTabs;
    }
}
