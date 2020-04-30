package com.foxy.product_client.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.foxy.product_client.R;
import com.foxy.product_client.ui.home.about.AboutFragment;
import com.foxy.product_client.ui.home.cart.CartFragment;
import com.foxy.product_client.ui.home.home.HomeFragment;
import com.foxy.product_client.ui.home.profile.ProfileFragment;

public class SlidePagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 4;

    private FragmentManager fragmentManager;

    public SlidePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance( R.color.red_inactive);
            case 1:
                return CartFragment.newInstance( R.color.blue_inactive);
            case 2:
                return ProfileFragment.newInstance( R.color.white);
            case 3:
                return AboutFragment.newInstance( R.color.green_inactive);
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

}