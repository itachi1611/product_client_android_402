package com.foxy.product_client.ui.authentication;

import com.foxy.product_client.api.ApiUtil;
import com.foxy.product_client.models.User;
import com.foxy.product_client.ultis.AppLogger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AuthenticationPresenter implements AuthenticationContract.Presenter {

    private AuthenticationContract.View mView;

    public AuthenticationPresenter(AuthenticationContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onLogin(String e, String p) {
        ApiUtil.getApi(false, null).onLogin(e, p)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        mView.onLoginSuccess(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogger.e(e);
                        mView.onLoginError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onRegister(String e, String p) {
        ApiUtil.getApi(false, null).onRegister(e, p)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mView.onRegisterSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogger.e(e);
                        mView.onRegisterError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
