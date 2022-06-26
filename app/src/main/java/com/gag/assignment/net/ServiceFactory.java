package com.gag.assignment.net;


import com.gag.assignment.BuildConfig;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ashwanisingh on 24/06/22.
 */

public class ServiceFactory {


    private static ServiceAPIs sServiceAPIs;

    public static ServiceAPIs getServiceAPIs() {
        if(sServiceAPIs == null) {
            sServiceAPIs = createServiceAPIs();
        }
        return sServiceAPIs;
    }

    private ServiceFactory() {
    }

    /**
     * The CityService communicates with the json api of the city provider.
     */
    private static ServiceAPIs createServiceAPIs() {
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(ServiceAPIs.class);
    }

    /**
     * This creates OKHttpClient
     */
    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.readTimeout(40, TimeUnit.SECONDS);
        httpClient.connectTimeout(40, TimeUnit.SECONDS);
        return httpClient.build();
    }

    /**
     * Creates a pre configured Retrofit instance
     */
    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }
}
