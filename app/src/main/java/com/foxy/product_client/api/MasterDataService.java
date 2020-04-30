package com.foxy.product_client.api;

import com.foxy.product_client.models.Invoice;
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
     * Edit user
     */
    @POST("api/user/edit")
    @FormUrlEncoded
    Observable<ResponseBody> onEditUser(@Field("_id") String _id, @Field("name") String name, @Field("phone") String phone, @Field("address") String address);

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
            @Field("product_name") String product_name,
            @Field("product_quantity") int product_quantity,
            @Field("product_price") int product_price);

    /**
     * Method: POST
     * Find invoices by customer id
     */
    @POST("api/order/user")
    @FormUrlEncoded
    Observable<List<Invoice>> onFetchOrderByCustomer(@Field("customer_id") String customer_id);

}
