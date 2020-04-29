package com.foxy.product_client.ui.splash;

import android.os.Bundle;
import android.os.Handler;

import com.foxy.product_client.R;
import com.foxy.product_client.bases.BaseActivity;
import com.foxy.product_client.helpers.SharedPreferencesHelper;
import com.foxy.product_client.ui.authentication.LoginActivity;
import com.foxy.product_client.ui.home.HomeActivity;

import static com.foxy.product_client.ultis.Constants.SPLASH_TIME_OUT;

public class SplashActivity extends BaseActivity implements SplashContract.View { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    private SplashContract.Presenter mPresenter = new SplashPresenter(this);    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash);  //TODO: create the layout and add it here
        onNavigateTime();
    }

    private void onNavigateTime() {
        new Handler().postDelayed(() -> {
            if(SharedPreferencesHelper.getInstance(this).getDataFromPref("isLogin", false)) {
                navigateActivity(LoginActivity.class);
                finish();
            } else {
                navigateActivity(HomeActivity.class);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}
