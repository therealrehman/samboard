package com.google.android.material.drawable;

import E.a;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.IOException;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class DrawableUtils {
    public static final int INTRINSIC_SIZE = -1;
    private static final int UNSPECIFIED_HEIGHT = -1;
    private static final int UNSPECIFIED_WIDTH = -1;

    private DrawableUtils() {
    }

    public static Drawable compositeTwoLayeredDrawable(Drawable drawable, Drawable drawable2) {
        return compositeTwoLayeredDrawable(drawable, drawable2, -1, -1);
    }

    public static Drawable createTintableDrawableIfNeeded(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        return createTintableMutatedDrawableIfNeeded(drawable, colorStateList, mode, false);
    }

    public static Drawable createTintableMutatedDrawableIfNeeded(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        return createTintableMutatedDrawableIfNeeded(drawable, colorStateList, mode, false);
    }

    public static int[] getCheckedState(int[] iArr) {
        for (int i5 = 0; i5 < iArr.length; i5++) {
            int i7 = iArr[i5];
            if (i7 == 16842912) {
                return iArr;
            }
            if (i7 == 0) {
                int[] iArr2 = (int[]) iArr.clone();
                iArr2[i5] = 16842912;
                return iArr2;
            }
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length + 1);
        iArrCopyOf[iArr.length] = 16842912;
        return iArrCopyOf;
    }

    public static ColorStateList getColorStateListOrNull(Drawable drawable) {
        if (drawable instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable) drawable).getColor());
        }
        if (drawable instanceof ColorStateListDrawable) {
            return ((ColorStateListDrawable) drawable).getColorStateList();
        }
        return null;
    }

    private static int getTopLayerIntrinsicHeight(Drawable drawable, Drawable drawable2) {
        int intrinsicHeight = drawable2.getIntrinsicHeight();
        return intrinsicHeight != -1 ? intrinsicHeight : drawable.getIntrinsicHeight();
    }

    private static int getTopLayerIntrinsicWidth(Drawable drawable, Drawable drawable2) {
        int intrinsicWidth = drawable2.getIntrinsicWidth();
        return intrinsicWidth != -1 ? intrinsicWidth : drawable.getIntrinsicWidth();
    }

    public static int[] getUncheckedState(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        int i5 = 0;
        for (int i7 : iArr) {
            if (i7 != 16842912) {
                iArr2[i5] = i7;
                i5++;
            }
        }
        return iArr2;
    }

    public static AttributeSet parseDrawableXml(Context context, int i5, CharSequence charSequence) {
        int next;
        try {
            XmlResourceParser xml = context.getResources().getXml(i5);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            if (TextUtils.equals(xml.getName(), charSequence)) {
                return Xml.asAttributeSet(xml);
            }
            throw new XmlPullParserException("Must have a <" + ((Object) charSequence) + "> start tag");
        } catch (IOException | XmlPullParserException e3) {
            Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(i5));
            notFoundException.initCause(e3);
            throw notFoundException;
        }
    }

    public static void setOutlineToPath(Outline outline, Path path) {
        outline.setPath(path);
    }

    @TargetApi(21)
    public static void setRippleDrawableRadius(RippleDrawable rippleDrawable, int i5) {
        rippleDrawable.setRadius(i5);
    }

    public static void setTint(Drawable drawable, int i5) {
        if (i5 != 0) {
            a.g(drawable, i5);
        } else {
            a.h(drawable, null);
        }
    }

    public static PorterDuffColorFilter updateTintFilter(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(drawable.getState(), 0), mode);
    }

    public static Drawable compositeTwoLayeredDrawable(Drawable drawable, Drawable drawable2, int i5, int i7) {
        if (drawable == null) {
            return drawable2;
        }
        if (drawable2 == null) {
            return drawable;
        }
        if (i5 == -1) {
            i5 = getTopLayerIntrinsicWidth(drawable, drawable2);
        }
        if (i7 == -1) {
            i7 = getTopLayerIntrinsicHeight(drawable, drawable2);
        }
        if (i5 > drawable.getIntrinsicWidth() || i7 > drawable.getIntrinsicHeight()) {
            float f2 = i5 / i7;
            if (f2 >= drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight()) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                i7 = (int) (intrinsicWidth / f2);
                i5 = intrinsicWidth;
            } else {
                i7 = drawable.getIntrinsicHeight();
                i5 = (int) (f2 * i7);
            }
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
        layerDrawable.setLayerSize(1, i5, i7);
        layerDrawable.setLayerGravity(1, 17);
        return layerDrawable;
    }

    private static Drawable createTintableMutatedDrawableIfNeeded(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode, boolean z9) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            drawable = drawable.mutate();
            if (mode != null) {
                a.i(drawable, mode);
            }
        } else if (z9) {
            drawable.mutate();
        }
        return drawable;
    }
}
