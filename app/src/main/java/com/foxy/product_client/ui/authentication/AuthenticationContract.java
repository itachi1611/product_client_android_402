package com.foxy.product_client.ui.authentication;

import com.foxy.product_client.models.User;

import okhttp3.ResponseBody;

public interface AuthenticationContract {

    interface View {
        void onLoginSuccess(User user);
        void onLoginError();
        void onRegisterSuccess(ResponseBody res);
        void onRegisterError();
    }

    interface Presenter {
        void onLogin(String e, String p);
        void onRegister(String e, String p);
    }

}
