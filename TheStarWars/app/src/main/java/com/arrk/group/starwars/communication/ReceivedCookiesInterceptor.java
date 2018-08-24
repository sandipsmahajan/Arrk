package com.arrk.group.starwars.communication;

import com.arrk.group.starwars.ATheStarWars;
import com.arrk.group.starwars.util.Util;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author SANDY
 * This Interceptor add all received Cookies to the app DefaultPreferences.
 * Your implementation on how to save the Cookies on the Preferences may vary.
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>(originalResponse.headers("Set-Cookie"));
            Util.setCookies(ATheStarWars.getAppContext(), cookies);
        }
        return originalResponse;
    }
}