package com.foxy.product_client.ui.home.cart;

import com.foxy.product_client.api.ApiUtil;
import com.foxy.product_client.models.Invoice;
import com.foxy.product_client.ultis.AppLogger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartPresenter implements CartContract.Presenter {

    private CartContract.View mView;

    public CartPresenter(CartContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onFetchOrder(String customer_id) {
        ApiUtil.getApi(false, null).onFetchOrderByCustomer(customer_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Invoice>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Invoice> invoices) {
                        mView.onFetchOrderSuccess(invoices);
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogger.e(e);
                        mView.onFetchOrderError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
