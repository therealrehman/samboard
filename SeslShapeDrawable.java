package androidx.appcompat.util;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import com.bumptech.glide.c;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class SeslShapeDrawable extends GradientDrawable {
    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        Method methodT = c.t(GradientDrawable.class, "setSmoothCorner", Boolean.TYPE);
        if (methodT == null) {
            Log.w("SeslShapeDrawable", "This API is not supported by the platform.");
        } else {
            c.C(this, methodT, Boolean.TRUE);
        }
    }
}
