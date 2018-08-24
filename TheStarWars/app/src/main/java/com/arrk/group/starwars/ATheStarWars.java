package com.arrk.group.starwars;

import android.app.Application;
import android.content.Context;

/**
 * @author SANDY
 */
public class ATheStarWars extends Application {

    private static Context context;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ATheStarWars.context = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
