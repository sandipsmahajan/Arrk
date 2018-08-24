package com.arrk.group.starwars.custom;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Locale;

/**
 * @author SANDY
 */
public enum CustomFont {
    BOLD("fonts/Proxima_Nova_Alt_Bold.ttf"),
    SEMI_BOLD("fonts/Proxima_Nova_Alt_Semibold.ttf"),
    REGULAR("fonts/Proxima_Nova_Alt_Regular.ttf"),
    LIGHT("fonts/Proxima_Nova_Alt_Thin.ttf");

    private final String fileName;

    CustomFont(String fileName) {
        this.fileName = fileName;
    }

    public static CustomFont fromString(String fontName) {
        return CustomFont.valueOf(fontName.toUpperCase(Locale.US));
    }

    public Typeface asTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), fileName);
    }
}
