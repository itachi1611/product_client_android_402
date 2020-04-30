package com.foxy.product_client.ui.home.profile;

import okhttp3.ResponseBody;

public interface ProfileContract {

    interface View {
        void onEditUserSuccess(ResponseBody responseBody);
        void onEditUserError();
    }

    interface Presenter {
        void onEditUser(String _id, String name, String phone, String address);
    }

}
