package com.arrk.group.starwars.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.arrk.group.starwars.R;
import com.arrk.group.starwars.communication.constants.UIConstant;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

/**
 * @author SANDY
 */
public class Util {

    private static KProgressHUD progress;


    public static boolean isValid(String text) {
        return text != null && !"".equals(text.trim());
    }

    public static void showProgress(Context context) {
        if (null != progress)
            return;
        progress = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setLabel(context.getString(R.string.label_loading))
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(context.getResources().getColor(R.color.colorAccent))
                .show();
    }

    public static void hideProgress() {
        if (null != progress && progress.isShowing()) {
            progress.dismiss();
            progress = null;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static HashSet<String> getCookies(Context context) {
        SharedPreferences app_cookies = context.getSharedPreferences("app_cookies", 0);
        return (HashSet<String>) app_cookies.getStringSet("cookies", new HashSet<String>());
    }

    public static boolean setCookies(Context context, HashSet<String> cookies) {
        SharedPreferences app_cookies = context.getSharedPreferences("app_cookies", 0);
        SharedPreferences.Editor editor = app_cookies.edit();
        return editor.putStringSet("cookies", cookies).commit();
    }

    public static double convertToMeter(String meter) {
        try {
            final double METER = 0.01;
            int centimeters = Integer.parseInt(meter);
            return centimeters * METER;
        } catch (NumberFormatException ignored) {
        }
        return 0;
    }

    public static String formatDate(String created) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIConstant.SERVER_DATE_FORMAT, Locale.US);
        try {
            Date date = dateFormat.parse(created);
            return new SimpleDateFormat(UIConstant.UI_DATE_FORMAT, Locale.US).format(date);
        } catch (ParseException ignored) {
        }
        return "";
    }
}
