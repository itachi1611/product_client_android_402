package com.foxy.product_client.ui.home.profile;

import com.foxy.product_client.api.ApiUtil;
import com.foxy.product_client.ultis.AppLogger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onEditUser(String _id, String name, String phone, String address) {
        ApiUtil.getApi(false, null).onEditUser(_id, name, phone, address)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mView.onEditUserSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogger.e(e);
                        mView.onEditUserError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
