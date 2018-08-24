package com.arrk.group.starwars.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * @author SANDY
 */
public class SButton extends AppCompatButton {

    private static final String sScheme =
            "http://schemas.android.com/apk/res-auto";
    private static final String sAttribute = "button_font";

    public SButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }
        String fontName = attrs.getAttributeValue(sScheme, sAttribute);

        if (fontName == null) {
            fontName = "regular";
        }
        Typeface customTypeface = CustomFont.fromString(fontName).asTypeface(context);
        setTypeface(customTypeface);
    }
}
