package com.foxy.product_client.ui.home.cart;

import com.foxy.product_client.models.Invoice;

import java.util.List;

public interface CartContract {

    interface View {
        void onFetchOrderSuccess(List<Invoice> invoices);
        void onFetchOrderError();
    }

    interface Presenter {
        void onFetchOrder(String customer_id);
    }

}
