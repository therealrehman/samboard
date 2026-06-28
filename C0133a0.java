package androidx.appcompat.widget;

import android.graphics.Typeface;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0133a0 extends C.q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f6667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ WeakReference f6668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ C0148f0 f6669d;

    public C0133a0(C0148f0 c0148f0, int i5, int i7, WeakReference weakReference) {
        this.f6669d = c0148f0;
        this.f6666a = i5;
        this.f6667b = i7;
        this.f6668c = weakReference;
    }

    @Override // C.q
    public final void onFontRetrievalFailed(int i5) {
    }

    @Override // C.q
    public final void onFontRetrieved(Typeface typeface) {
        int i5 = this.f6666a;
        if (i5 != -1) {
            typeface = AbstractC0145e0.a(typeface, i5, (this.f6667b & 2) != 0);
        }
        C0148f0 c0148f0 = this.f6669d;
        if (c0148f0.f6706m) {
            c0148f0.f6705l = typeface;
            TextView textView = (TextView) this.f6668c.get();
            if (textView != null) {
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                if (textView.isAttachedToWindow()) {
                    textView.post(new androidx.activity.g(textView, typeface, c0148f0.f6703j));
                } else {
                    textView.setTypeface(typeface, c0148f0.f6703j);
                }
            }
        }
    }
}
