package androidx.appcompat.widget;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.appcompat.widget.u1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0193u1 extends View.AccessibilityDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0196v1 f6860a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0190t1 f6861b;

    public C0193u1(C0196v1 c0196v1, C0190t1 c0190t1) {
        this.f6860a = c0196v1;
        this.f6861b = c0190t1;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
        kotlin.jvm.internal.j.f(host, "host");
        kotlin.jvm.internal.j.f(info, "info");
        super.onInitializeAccessibilityNodeInfo(host, info);
        C0196v1 c0196v1 = this.f6860a;
        info.setContentDescription(c0196v1.getResources().getString(R.string.sesl_appbar_suggest_pagination, Integer.valueOf(c0196v1.f6863e.indexOf(this.f6861b) + 1), Integer.valueOf(c0196v1.getSize())));
    }
}
