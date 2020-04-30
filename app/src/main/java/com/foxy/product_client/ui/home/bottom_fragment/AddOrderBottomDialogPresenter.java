package com.foxy.product_client.ui.home.bottom_fragment;

import com.foxy.product_client.api.ApiUtil;
import com.foxy.product_client.ultis.AppLogger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AddOrderBottomDialogPresenter implements AddOrderBottomDialogContract.Presenter {

    private AddOrderBottomDialogContract.View mView;

    public AddOrderBottomDialogPresenter(AddOrderBottomDialogContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onCreateOrder(String customer_name, String customer_id, String shipping_address, String product_name, int product_quantity, int product_price) {
        ApiUtil.getApi(false, null).onCreateOrder(customer_name, customer_id, shipping_address, product_name, product_quantity, product_price)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mView.onCreateSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogger.e(e);
                        mView.onCreateError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
