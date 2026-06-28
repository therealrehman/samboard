package androidx.appcompat.widget;

import android.util.Log;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.appcompat.widget.g1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0152g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6711a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    public static /* synthetic */ int a(int i5, int i7) {
        if (i5 == 0 || i7 == 0) {
            throw null;
        }
        return i5 - i7;
    }

    public static /* synthetic */ int b(int i5) {
        if (i5 == 1) {
            return R.color.sesl_search_view_background_hint_text_color_light;
        }
        if (i5 == 2) {
            return R.color.sesl_search_view_hint_text_color;
        }
        if (i5 == 3) {
            return R.color.sesl_search_view_background_hint_text_color_dark;
        }
        if (i5 == 4) {
            return R.color.sesl_search_view_hint_text_color_dark;
        }
        throw null;
    }

    public static /* synthetic */ int c(int i5) {
        if (i5 == 1) {
            return R.color.sesl_search_view_background_icon_color_light;
        }
        if (i5 == 2) {
            return R.color.sesl_search_view_icon_color;
        }
        if (i5 == 3) {
            return R.color.sesl_search_view_background_icon_color_dark;
        }
        if (i5 == 4) {
            return R.color.sesl_search_view_icon_color_dark;
        }
        throw null;
    }

    public static /* synthetic */ int d(int i5) {
        if (i5 == 1) {
            return R.color.sesl_search_view_background_text_color_light;
        }
        if (i5 == 2) {
            return R.color.sesl_search_view_text_color;
        }
        if (i5 == 3) {
            return R.color.sesl_search_view_background_text_color_dark;
        }
        if (i5 == 4) {
            return R.color.sesl_search_view_text_color_dark;
        }
        throw null;
    }

    public static StringBuilder e(int i5, String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(i5);
        sb.append(str2);
        return sb;
    }

    public static void f(int i5, String str, String str2) {
        Log.d(str2, str + i5);
    }

    public static /* synthetic */ int g(int i5) {
        if (i5 != 0) {
            return i5 - 1;
        }
        throw null;
    }

    public static /* synthetic */ String h(int i5) {
        return i5 != 1 ? i5 != 2 ? i5 != 3 ? i5 != 4 ? "null" : "DARK_WITHOUT_BACKGROUND" : "DARK_WITH_BACKGROUND" : "LIGHT_WITHOUT_BACKGROUND" : "LIGHT_WITH_BACKGROUND";
    }

    public static /* synthetic */ int[] i(int i5) {
        int[] iArr = new int[i5];
        System.arraycopy(f6711a, 0, iArr, 0, i5);
        return iArr;
    }
}
