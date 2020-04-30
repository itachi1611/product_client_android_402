package com.foxy.product_client.ui.home;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.foxy.product_client.R;
import com.foxy.product_client.adapters.SlidePagerAdapter;
import com.foxy.product_client.bases.BaseActivity;
import com.foxy.product_client.ui.home.about.AboutFragment;
import com.foxy.product_client.ui.home.cart.CartFragment;
import com.foxy.product_client.ui.home.home.HomeFragment;
import com.foxy.product_client.ui.home.profile.ProfileFragment;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.jem.liquidswipe.LiquidSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends BaseActivity { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    @BindView(R.id.view_pager)
    LiquidSwipeViewPager viewPager;

    @BindView(R.id.bottom_navigation_view_linear)
    BubbleNavigationLinearView bottomNavigationViewLinear;

    private Unbinder unbinder;

    //private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_home);  //TODO: create the layout and add it here

        initView();

        initSlidePager();

    }

    private void initView() {
        unbinder = ButterKnife.bind(this);
        //fragmentList = new ArrayList<>();
        //fragmentList.add(HomeFragment.newInstance( R.color.red_inactive));
        //fragmentList.add(CartFragment.newInstance( R.color.blue_inactive));
        //fragmentList.add(ProfileFragment.newInstance( R.color.white));
        //fragmentList.add(AboutFragment.newInstance( R.color.green_inactive));
    }

    private void initSlidePager() {
        SlidePagerAdapter adapter = new SlidePagerAdapter(getSupportFragmentManager());
        bottomNavigationViewLinear.setTypeface(Typeface.createFromAsset(getAssets(), "rubik.ttf"));
        //bottomNavigationViewLinear.setBadgeValue(0, "40");
        //bottomNavigationViewLinear.setBadgeValue(1, null); //invisible badge
        //bottomNavigationViewLinear.setBadgeValue(2, "7");//empty badge
        //bottomNavigationViewLinear.setBadgeValue(3, "");//empty badge

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationViewLinear.setCurrentActiveItem(position);
                switch (position) {
                    case 0:
                        bottomNavigationViewLinear.setBadgeValue(0, "40");
                        break;
                    case 1:
                        bottomNavigationViewLinear.setBadgeValue(1, null); //invisible badge
                        break;
                    case 2:
                        bottomNavigationViewLinear.setBadgeValue(2, "7");//empty badge
                        break;
                    case 3:
                        bottomNavigationViewLinear.setBadgeValue(3, "");//empty badge
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationViewLinear.setNavigationChangeListener((view, position) -> {
            viewPager.setCurrentItem(position, true);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
