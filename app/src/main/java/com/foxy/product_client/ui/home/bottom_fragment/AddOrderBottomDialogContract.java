package com.foxy.product_client.ui.home.bottom_fragment;

import okhttp3.ResponseBody;

public interface AddOrderBottomDialogContract {

    interface View {
        void onCreateSuccess(ResponseBody responseBody);
        void onCreateError();
    }

    interface Presenter {
        void onCreateOrder(String customer_name, String customer_id, String shipping_address, String product_name, int product_quantity, int product_price);
    }

}
