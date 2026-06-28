package com.google.android.material.appbar;

import C.l;
import C.s;
import D.f;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.core.view.N;
import androidx.core.view.W;
import androidx.core.view.w0;
import com.google.android.material.R;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/google/android/material/appbar/SeslAppBarHelper;", "", "()V", "Companion", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SeslAppBarHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "SeslAppBarHelper";

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/google/android/material/appbar/SeslAppBarHelper$Companion;", "", "()V", "TAG", "", "getAppBarProPortion", "", "context", "Landroid/content/Context;", "getFullWindowHeightDp", "getScreenHeight", "", "view", "Landroid/view/View;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final float getAppBarProPortion(Context context) {
            j.f(context, "context");
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();
            if (Build.VERSION.SDK_INT < 35) {
                int i5 = R.dimen.sesl_appbar_height_proportion;
                ThreadLocal threadLocal = s.f322a;
                return l.a(resources, i5);
            }
            float fullWindowHeightDp = getFullWindowHeightDp(context);
            Log.d(SeslAppBarHelper.TAG, "orientation=" + configuration.orientation + ", fullWindowHeightDp=" + fullWindowHeightDp);
            if (configuration.orientation != 2) {
                if (fullWindowHeightDp < 639.0f) {
                    return 0.0f;
                }
                if (fullWindowHeightDp < 696.0f) {
                    return 0.48f;
                }
                if (fullWindowHeightDp < 780.0f) {
                    return 0.43f;
                }
                return fullWindowHeightDp < 960.0f ? 0.38f : 0.305f;
            }
            if (fullWindowHeightDp < 580.0f) {
                return 0.0f;
            }
            if (fullWindowHeightDp < 640.0f) {
                return 0.51f;
            }
            if (fullWindowHeightDp < 670.0f) {
                return 0.475f;
            }
            if (fullWindowHeightDp < 710.0f) {
                return 0.45f;
            }
            if (fullWindowHeightDp < 750.0f) {
                return 0.425f;
            }
            if (fullWindowHeightDp < 800.0f) {
                return 0.4f;
            }
            return fullWindowHeightDp < 1080.0f ? 0.37f : 0.27f;
        }

        public final float getFullWindowHeightDp(Context context) {
            j.f(context, "context");
            Object systemService = context.getSystemService("window");
            j.d(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            WindowMetrics currentWindowMetrics = ((WindowManager) systemService).getCurrentWindowMetrics();
            j.e(currentWindowMetrics, "wm.currentWindowMetrics");
            int iHeight = currentWindowMetrics.getBounds().height();
            float fDeriveDimension = TypedValue.deriveDimension(1, iHeight, displayMetrics);
            Log.d(SeslAppBarHelper.TAG, "fullWindowHeight(dp)=" + fDeriveDimension + ", fullWindowHeight(px)=" + iHeight + ", screenHeightDp=" + context.getResources().getConfiguration().screenHeightDp);
            return fDeriveDimension;
        }

        public final int getScreenHeight(View view) {
            f fVarF;
            j.f(view, "view");
            Context context = view.getContext();
            if (Build.VERSION.SDK_INT < 35) {
                return context.getResources().getDisplayMetrics().heightPixels;
            }
            Object systemService = context.getSystemService("window");
            j.d(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            WindowMetrics currentWindowMetrics = ((WindowManager) systemService).getCurrentWindowMetrics();
            j.e(currentWindowMetrics, "wm.currentWindowMetrics");
            WeakHashMap weakHashMap = W.f7199a;
            w0 w0VarA = N.a(view);
            if (w0VarA == null || (fVarF = w0VarA.f7266a.f(7)) == null) {
                fVarF = f.f406e;
            }
            int iHeight = currentWindowMetrics.getBounds().height();
            int i5 = fVarF.f408b;
            int i7 = fVarF.f410d;
            int i9 = (iHeight - i5) - i7;
            StringBuilder sbX = A8.l.x("screenHeight(px)=", i9, ", status=", i5, ", navi=");
            sbX.append(i7);
            Log.d(SeslAppBarHelper.TAG, sbX.toString());
            return i9;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }
}
