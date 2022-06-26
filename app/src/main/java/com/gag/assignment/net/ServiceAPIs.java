package com.gag.assignment.net;

import com.gag.assignment.BuildConfig;
import com.gag.assignment.model.ProductData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by ashwanisingh on 24/06/22.
 */

public interface ServiceAPIs {

    @Headers("Authorization: " + BuildConfig.AUTH)
    @GET("products/{productId}")
    Observable<ProductData> getProduct(@Path("productId") String productId);

}
