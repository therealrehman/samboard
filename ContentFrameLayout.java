package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f6420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TypedValue f6421f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TypedValue f6422g;
    public TypedValue h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public TypedValue f6423i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public TypedValue f6424j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Rect f6425k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public InterfaceC0172n0 f6426l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f6427m;

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f6425k = new Rect();
        a();
    }

    public final void a() {
        this.f6427m = TypedValue.applyDimension(1, r0.getConfiguration().screenWidthDp, getResources().getDisplayMetrics());
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f6423i == null) {
            this.f6423i = new TypedValue();
        }
        return this.f6423i;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f6424j == null) {
            this.f6424j = new TypedValue();
        }
        return this.f6424j;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f6422g == null) {
            this.f6422g = new TypedValue();
        }
        return this.f6422g;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.h == null) {
            this.h = new TypedValue();
        }
        return this.h;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f6420e == null) {
            this.f6420e = new TypedValue();
        }
        return this.f6420e;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f6421f == null) {
            this.f6421f = new TypedValue();
        }
        return this.f6421f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        InterfaceC0172n0 interfaceC0172n0 = this.f6426l;
        if (interfaceC0172n0 != null) {
            interfaceC0172n0.getClass();
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f6421f == null) {
            this.f6421f = new TypedValue();
        }
        getContext().getTheme().resolveAttribute(R.attr.windowMinWidthMinor, this.f6421f, true);
        if (this.f6420e == null) {
            this.f6420e = new TypedValue();
        }
        getContext().getTheme().resolveAttribute(R.attr.windowMinWidthMajor, this.f6420e, true);
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        InterfaceC0172n0 interfaceC0172n0 = this.f6426l;
        if (interfaceC0172n0 != null) {
            g.C c5 = (g.C) ((B8.e) interfaceC0172n0).f286f;
            InterfaceC0175o0 interfaceC0175o0 = c5.f11005v;
            if (interfaceC0175o0 != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) interfaceC0175o0;
                actionBarOverlayLayout.e();
                ((c2) actionBarOverlayLayout.f6362i).f6679a.dismissPopupMenus();
            }
            if (c5.f10963A != null) {
                c5.f10999p.getDecorView().removeCallbacks(c5.f10964B);
                if (c5.f10963A.isShowing()) {
                    try {
                        c5.f10963A.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                c5.f10963A = null;
            }
            androidx.core.view.e0 e0Var = c5.f10965C;
            if (e0Var != null) {
                e0Var.b();
            }
            androidx.appcompat.view.menu.j jVar = c5.z(0).h;
            if (jVar != null) {
                jVar.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dc  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(InterfaceC0172n0 interfaceC0172n0) {
        this.f6426l = interfaceC0172n0;
    }
}
