package com.foxy.product_client.ui.home.home;

import com.foxy.product_client.api.ApiUtil;
import com.foxy.product_client.models.Product;
import com.foxy.product_client.ultis.AppLogger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onFetchProductData() {
        ApiUtil.getApi(false, null).fetchProduct()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Product> products) {
                        mView.onFetchSuccess(products);
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogger.e(e);
                        mView.onFetchError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
