package com.foxy.product_client.api;

import com.foxy.product_client.models.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MasterDataService {

    /**
     * Method: GET
     * Get product list data
    */
    @GET("/api/product")
    Observable<List<Product>> fetchProduct();


}
