package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.PointerIcon;

/* JADX INFO: renamed from: androidx.core.view.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0228u {
    public static PointerIcon a(Bitmap bitmap, float f2, float f7) {
        return PointerIcon.create(bitmap, f2, f7);
    }

    public static PointerIcon b(Context context, int i5) {
        return PointerIcon.getSystemIcon(context, i5);
    }

    public static PointerIcon c(Resources resources, int i5) {
        return PointerIcon.load(resources, i5);
    }
}
