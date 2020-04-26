package com.foxy.product_client.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import java.util.List;

public class SlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mList;
    private FragmentManager fragmentManager;

    public SlidePagerAdapter(@NonNull FragmentManager fm, List<Fragment> mList) {
        super(fm);
        this.mList = mList;
        this.fragmentManager = fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position >= 0 && position < mList.size()) {
            return mList.get(position);
        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

}