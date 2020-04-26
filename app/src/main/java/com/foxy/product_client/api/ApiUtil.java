package com.foxy.product_client.api;

import com.foxy.product_client.ultis.Constants;

public class ApiUtil extends Constants {

    public static MasterDataService getApi(boolean isAuthorization, String token) {
        return RetrofitClient.getClient(BASE_URL, isAuthorization, token).create(MasterDataService.class);
    }

}
