package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import e.AbstractC0478a;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0203y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CompoundButton f6891a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f6892b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f6893c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f6894d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6895e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6896f;

    public C0203y(CompoundButton compoundButton) {
        this.f6891a = compoundButton;
    }

    public final void a() {
        CompoundButton compoundButton = this.f6891a;
        Drawable drawableA = P.c.a(compoundButton);
        if (drawableA != null) {
            if (this.f6894d || this.f6895e) {
                Drawable drawableMutate = drawableA.mutate();
                if (this.f6894d) {
                    E.a.h(drawableMutate, this.f6892b);
                }
                if (this.f6895e) {
                    E.a.i(drawableMutate, this.f6893c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(drawableMutate);
            }
        }
    }

    public final void b(AttributeSet attributeSet, int i5) {
        int resourceId;
        int resourceId2;
        CompoundButton compoundButton = this.f6891a;
        Context context = compoundButton.getContext();
        int[] iArr = AbstractC0478a.f10567m;
        S1 s1E = S1.e(context, attributeSet, iArr, i5, 0);
        TypedArray typedArray = s1E.f6522b;
        CompoundButton compoundButton2 = this.f6891a;
        Context context2 = compoundButton2.getContext();
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.T.d(compoundButton2, context2, iArr, attributeSet, s1E.f6522b, i5, 0);
        try {
            if (typedArray.hasValue(1) && (resourceId2 = typedArray.getResourceId(1, 0)) != 0) {
                try {
                    compoundButton.setButtonDrawable(android.support.v4.media.session.f.y(compoundButton.getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(0)) {
                        compoundButton.setButtonDrawable(android.support.v4.media.session.f.y(compoundButton.getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(0) && (resourceId = typedArray.getResourceId(0, 0)) != 0) {
                compoundButton.setButtonDrawable(android.support.v4.media.session.f.y(compoundButton.getContext(), resourceId));
            }
            if (typedArray.hasValue(2)) {
                P.b.c(compoundButton, s1E.a(2));
            }
            if (typedArray.hasValue(3)) {
                P.b.d(compoundButton, AbstractC0183r0.b(typedArray.getInt(3, -1), null));
            }
        } finally {
            s1E.f();
        }
    }
}
