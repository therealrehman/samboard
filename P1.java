package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import e.AbstractC0478a;

/* JADX INFO: loaded from: classes.dex */
public abstract class P1 {
    static {
        new ThreadLocal();
    }

    public static void a(View view, Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(AbstractC0478a.f10564j);
        try {
            if (!typedArrayObtainStyledAttributes.hasValue(145)) {
                Log.e("ThemeUtils", "View " + view.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
