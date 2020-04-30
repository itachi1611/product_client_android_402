package com.foxy.product_client.ui.authentication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.foxy.product_client.R;
import com.foxy.product_client.bases.BaseActivity;
import com.foxy.product_client.helpers.SharedPreferencesHelper;
import com.foxy.product_client.models.User;
import com.foxy.product_client.ui.home.HomeActivity;
import com.foxy.product_client.ultis.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

import static com.foxy.product_client.ultis.Constants.REGISTER_TIME_OUT;

public class LoginActivity extends BaseActivity implements AuthenticationContract.View, View.OnClickListener { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    @BindView(R.id.edtEmail)
    AppCompatEditText edtEmail;

    @BindView(R.id.edtPassword)
    AppCompatEditText edtPassword;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.tvRegister)
    AppCompatTextView tvRegister;


    private Unbinder unbinder;

    private AuthenticationContract.Presenter mPresenter = new AuthenticationPresenter(this);    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_login);  //TODO: create the layout and add it here
        initView();
    }

    private void initView() {
        unbinder = ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String e = edtEmail.getText().toString().trim();
                String p = edtPassword.getText().toString().trim();
                onShowLoading();
                mPresenter.onLogin(e, p);
                break;
            case R.id.tvRegister:
                navigateActivity(RegisterActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onLoginSuccess(User user) {
        if(user != null) {
            SharedPreferencesHelper pref = SharedPreferencesHelper.getInstance(this);
            pref.saveDataToPref("u_email", user.getEmail());
            pref.saveDataToPref("u_name", user.getName());
            pref.saveDataToPref("u_phone", user.getPhone());
            pref.saveDataToPref("u_address", user.getAddress());
            pref.saveDataToPref("u_role", user.getAdmin());
            pref.saveDataToPref("u_id", user.getId());
            pref.saveDataToPref("isLogin", true);
        }
        onHideLoading();
        CommonUtils.showSuccessToast(this, "Login succeeded !");
        new Handler().postDelayed(() -> {
            navigateActivity(HomeActivity.class);
        },REGISTER_TIME_OUT);
    }

    @Override
    public void onLoginError() {
        onHideLoading();
        CommonUtils.showErrorToast(this, "Login failed !");
    }

    @Override
    public void onRegisterSuccess(ResponseBody res) {}

    @Override
    public void onRegisterError() {}

}
