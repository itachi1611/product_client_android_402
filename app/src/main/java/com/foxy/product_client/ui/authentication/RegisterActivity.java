package com.foxy.product_client.ui.authentication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.foxy.product_client.R;
import com.foxy.product_client.bases.BaseActivity;
import com.foxy.product_client.models.User;
import com.foxy.product_client.ultis.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

import static com.foxy.product_client.ultis.Constants.REGISTER_TIME_OUT;

public class RegisterActivity extends BaseActivity implements AuthenticationContract.View, View.OnClickListener { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    @BindView(R.id.edtEmail)
    AppCompatEditText edtEmail;

    @BindView(R.id.edtPassword)
    AppCompatEditText edtPassword;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.tvLogin)
    AppCompatTextView tvLogin;


    private Unbinder unbinder;

    private AuthenticationContract.Presenter mPresenter = new AuthenticationPresenter(this);    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_register);  //TODO: create the layout and add it here
        initView();
    }

    private void initView() {
        unbinder = ButterKnife.bind(this);
        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                String e = edtEmail.getText().toString().trim();
                String p = edtPassword.getText().toString().trim();
                onShowLoading();
                mPresenter.onRegister(e, p);
                break;
            case R.id.tvLogin:
                navigateActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    public void onLoginSuccess(User user) {}

    @Override
    public void onLoginError() {}

    @Override
    public void onRegisterSuccess(ResponseBody res) {
        onHideLoading();
        CommonUtils.showSuccessToast(this, "Register succeeded !");
        new Handler().postDelayed(() -> {
            navigateActivity(LoginActivity.class);
        },REGISTER_TIME_OUT);
    }

    @Override
    public void onRegisterError() {
        onHideLoading();
        CommonUtils.showErrorToast(this, "Register failed !");
    }

}
