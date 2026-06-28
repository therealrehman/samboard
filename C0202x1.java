package androidx.appcompat.widget;

import android.util.IntProperty;

/* JADX INFO: renamed from: androidx.appcompat.widget.x1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0202x1 extends IntProperty {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0205y1 f6890a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0202x1(C0205y1 c0205y1) {
        super("visual_progress");
        this.f6890a = c0205y1;
    }

    @Override // android.util.Property
    public final Integer get(Object obj) {
        return Integer.valueOf(((C0205y1) obj).f6901e);
    }

    @Override // android.util.IntProperty
    public final void setValue(Object obj, int i5) {
        ((C0205y1) obj).f6901e = i5;
        this.f6890a.invalidateSelf();
    }
}
