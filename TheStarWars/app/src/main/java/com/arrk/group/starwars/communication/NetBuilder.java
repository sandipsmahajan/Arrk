package com.arrk.group.starwars.communication;

import com.arrk.group.starwars.communication.constants.APIConstant;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author SANDY
 */
public class NetBuilder {

    private static final NetBuilder instance = new NetBuilder();

    private ICommunicator communicator;

    private NetBuilder() {
        OkHttpClient.Builder okHttpClient =
                new OkHttpClient.Builder()
                        .readTimeout(APIConstant.TIMEOUT, TimeUnit.MINUTES)
                        .connectTimeout(APIConstant.TIMEOUT, TimeUnit.MINUTES)
                        .writeTimeout(APIConstant.TIMEOUT, TimeUnit.MINUTES);
        okHttpClient.interceptors().add(new AddCookiesInterceptor());
        okHttpClient.interceptors().add(new ReceivedCookiesInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create()))
                .client(okHttpClient.build())
                .build();
        communicator = retrofit.create(ICommunicator.class);
    }

    public synchronized static NetBuilder getInstance() {
        return instance;
    }

    public ICommunicator getService() {
        return communicator;
    }
}