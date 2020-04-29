package com.foxy.product_client.api;

import com.foxy.product_client.models.Product;
import com.foxy.product_client.models.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MasterDataService {

    /**
     * Method: GET
     * Get product list data
    */
    @GET("api/product")
    Observable<List<Product>> fetchProduct();

    /**
     * Method: POST
     * Login
     */
    @POST("api/login")
    @FormUrlEncoded
    Observable<User> onLogin(@Field("email") String e, @Field("password") String p);

    /**
     * Method: POST
     * Register
     */
    @POST("api/register")
    @FormUrlEncoded
    Observable<ResponseBody> onRegister(@Field("email") String e, @Field("password") String p);

    /**
     * Method: POST
     * Create order
     */
    @POST("api/order/add")
    @FormUrlEncoded
    Observable<ResponseBody> onCreateOrder(
            @Field("customer_name") String customer_name,
            @Field("customer_id") String customer_id,
            @Field("shipping_address") String shipping_address,
            @Field("payment_method") String payment_method,
            @Field("payment_status") int payment_status,
            @Field("product_name") String product_name,
            @Field("product_quantity") int product_quantity);

}
