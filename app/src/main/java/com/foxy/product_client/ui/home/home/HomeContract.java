package com.foxy.product_client.ui.home.home;

import com.foxy.product_client.models.Product;

import java.util.List;

public interface HomeContract {

    interface View {
        void onFetchSuccess(List<Product> products);
        void onFetchError();
    }

    interface Presenter {
        void onFetchProductData();
    }

}
