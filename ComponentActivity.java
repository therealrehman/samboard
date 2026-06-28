package androidx.core.app;

import A8.l;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.core.view.InterfaceC0214f;
import androidx.core.view.W;
import androidx.lifecycle.AbstractC0272n;
import androidx.lifecycle.C0278u;
import androidx.lifecycle.InterfaceC0276s;
import androidx.lifecycle.K;
import androidx.lifecycle.ReportFragment;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import q.k;
import z.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\u0006B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\t\u0010\nJ)\u0010\u000e\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u000b*\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0017¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0017¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0016\u0010\u0014J\u001f\u0010\u001a\u001a\u00020\u00122\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0004¢\u0006\u0004\b\u001a\u0010\u001bR.\u0010\u001d\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\f\u0012\u0004\u0012\u00020\u00060\u001c8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u0012\u0004\b\u001f\u0010\u0005R\u001a\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b!\u0010\"\u0012\u0004\b#\u0010\u0005¨\u0006$"}, d2 = {"Landroidx/core/app/ComponentActivity;", "Landroid/app/Activity;", "Landroidx/lifecycle/s;", "Landroidx/core/view/f;", "<init>", "()V", "Lz/h;", "extraData", "LT6/p;", "putExtraData", "(Lz/h;)V", "T", "Ljava/lang/Class;", "extraDataClass", "getExtraData", "(Ljava/lang/Class;)Lz/h;", "Landroid/view/KeyEvent;", "event", "", "superDispatchKeyEvent", "(Landroid/view/KeyEvent;)Z", "dispatchKeyShortcutEvent", "dispatchKeyEvent", "", "", "args", "shouldDumpInternalState", "([Ljava/lang/String;)Z", "Lq/k;", "extraDataMap", "Lq/k;", "getExtraDataMap$annotations", "Landroidx/lifecycle/u;", "lifecycleRegistry", "Landroidx/lifecycle/u;", "getLifecycleRegistry$annotations", "core_release"}, k = 1, mv = {1, 8, 0})
public class ComponentActivity extends Activity implements InterfaceC0276s, InterfaceC0214f {
    private final k extraDataMap = new k();
    private final C0278u lifecycleRegistry = new C0278u(this);

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        j.f(event, "event");
        j.e(getWindow().getDecorView(), "window.decorView");
        WeakHashMap weakHashMap = W.f7199a;
        return superDispatchKeyEvent(event);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        j.f(event, "event");
        j.e(getWindow().getDecorView(), "window.decorView");
        WeakHashMap weakHashMap = W.f7199a;
        return super.dispatchKeyShortcutEvent(event);
    }

    public <T extends h> T getExtraData(Class<T> extraDataClass) {
        j.f(extraDataClass, "extraDataClass");
        l.z(this.extraDataMap.getOrDefault(extraDataClass, null));
        return null;
    }

    public AbstractC0272n getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i5 = ReportFragment.f7799e;
        K.g(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        j.f(outState, "outState");
        this.lifecycleRegistry.g();
        super.onSaveInstanceState(outState);
    }

    public void putExtraData(h extraData) {
        j.f(extraData, "extraData");
        throw null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean shouldDumpInternalState(java.lang.String[] r3) {
        /*
            r2 = this;
            r2 = 1
            r0 = 0
            if (r3 == 0) goto L47
            int r1 = r3.length
            if (r1 != 0) goto L8
            goto L47
        L8:
            r3 = r3[r0]
            int r1 = r3.hashCode()
            switch(r1) {
                case -645125871: goto L3f;
                case 100470631: goto L2f;
                case 472614934: goto L26;
                case 1159329357: goto L1d;
                case 1455016274: goto L12;
                default: goto L11;
            }
        L11:
            goto L47
        L12:
            java.lang.String r1 = "--autofill"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L1b
            goto L47
        L1b:
            r0 = r2
            goto L47
        L1d:
            java.lang.String r1 = "--contentcapture"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L1b
            goto L47
        L26:
            java.lang.String r1 = "--list-dumpables"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L38
            goto L47
        L2f:
            java.lang.String r1 = "--dump-dumpable"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L38
            goto L47
        L38:
            int r3 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            if (r3 < r1) goto L47
            goto L1b
        L3f:
            java.lang.String r1 = "--translation"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L1b
        L47:
            r2 = r2 ^ r0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.ComponentActivity.shouldDumpInternalState(java.lang.String[]):boolean");
    }

    @Override // androidx.core.view.InterfaceC0214f
    public boolean superDispatchKeyEvent(KeyEvent event) {
        j.f(event, "event");
        return super.dispatchKeyEvent(event);
    }
}
