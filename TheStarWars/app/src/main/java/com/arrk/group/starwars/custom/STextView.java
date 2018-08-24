package com.arrk.group.starwars.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author SANDY
 */
public class STextView extends AppCompatTextView {

    private static final String sScheme =
            "http://schemas.android.com/apk/res-auto";
    private static final String sAttribute = "text_font";

    public STextView(Context context, AttributeSet attrs) {
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
