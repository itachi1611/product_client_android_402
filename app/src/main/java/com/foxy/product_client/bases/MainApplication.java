package com.foxy.product_client.bases;

import androidx.multidex.MultiDexApplication;

import com.foxy.product_client.api.LenientTypeAdapterFactory;
import com.foxy.product_client.ultis.AppLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainApplication extends MultiDexApplication {

    private Gson mGson;

    private static MainApplication mSelf;

    public static MainApplication self() {
        return mSelf;
    }

    public Gson getGson() {
        return mGson;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
        mGson = new GsonBuilder().registerTypeAdapterFactory(new LenientTypeAdapterFactory()).create();
        AppLogger.init();
    }

}
