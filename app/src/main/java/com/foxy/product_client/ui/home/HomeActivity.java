package com.foxy.product_client.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.foxy.product_client.R;
import com.foxy.product_client.bases.BaseActivity;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.jem.liquidswipe.LiquidSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends BaseActivity implements HomeContract.View { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    @BindView(R.id.view_pager)
    LiquidSwipeViewPager viewPager;

    @BindView(R.id.bottom_navigation_view_linear)
    BubbleNavigationLinearView bottomNavigationViewLinear;

    private Unbinder unbinder;

    private HomeContract.Presenter mPresenter = new HomePresenter(this);    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);  //TODO: create the layout and add it here
        initView();
    }

    private void initView() {
        unbinder = ButterKnife.bind(this);
        List<Fragment> fragmentList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
