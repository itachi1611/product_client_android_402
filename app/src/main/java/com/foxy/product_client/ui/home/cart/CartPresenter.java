package com.foxy.product_client.ui.home.cart;

public class CartPresenter implements CartContract.Presenter {

    private CartContract.View mView;

    public CartPresenter(CartContract.View mView) {
        this.mView = mView;
    }

}
