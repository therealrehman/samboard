package androidx.fragment.app;

import android.graphics.Rect;
import android.view.View;
import java.util.List;

/* JADX INFO: renamed from: androidx.fragment.app.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0237d implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7619e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f7620f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f7621g;
    public final /* synthetic */ Object h;

    public /* synthetic */ RunnableC0237d(int i5, Object obj, Object obj2, Object obj3) {
        this.f7619e = i5;
        this.f7620f = obj;
        this.f7621g = obj2;
        this.h = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f7619e) {
            case 0:
                List awaitingContainerChanges = (List) this.f7620f;
                kotlin.jvm.internal.j.f(awaitingContainerChanges, "$awaitingContainerChanges");
                v0 operation = (v0) this.f7621g;
                kotlin.jvm.internal.j.f(operation, "$operation");
                kotlin.jvm.internal.j.f((C0246m) this.h, "this$0");
                if (awaitingContainerChanges.contains(operation)) {
                    awaitingContainerChanges.remove(operation);
                    View view = operation.f7738c.mView;
                    int i5 = operation.f7736a;
                    kotlin.jvm.internal.j.e(view, "view");
                    A8.l.a(i5, view);
                }
                break;
            case 1:
                r0 impl = (r0) this.f7620f;
                kotlin.jvm.internal.j.f(impl, "$impl");
                Rect lastInEpicenterRect = (Rect) this.h;
                kotlin.jvm.internal.j.f(lastInEpicenterRect, "$lastInEpicenterRect");
                r0.g(lastInEpicenterRect, (View) this.f7621g);
                break;
            default:
                C0246m this$0 = (C0246m) this.h;
                kotlin.jvm.internal.j.f(this$0, "this$0");
                C0241h animationInfo = (C0241h) this.f7621g;
                kotlin.jvm.internal.j.f(animationInfo, "$animationInfo");
                this$0.f7694a.endViewTransition((View) this.f7620f);
                animationInfo.a();
                break;
        }
    }

    public /* synthetic */ RunnableC0237d(C0246m c0246m, View view, C0241h c0241h) {
        this.f7619e = 2;
        this.h = c0246m;
        this.f7620f = view;
        this.f7621g = c0241h;
    }
}
