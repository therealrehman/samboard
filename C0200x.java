package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;

/* JADX INFO: renamed from: androidx.appcompat.widget.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0200x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CheckedTextView f6874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f6875b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f6876c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f6877d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6878e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6879f;

    public C0200x(CheckedTextView checkedTextView) {
        this.f6874a = checkedTextView;
    }

    public final void a() {
        CheckedTextView checkedTextView = this.f6874a;
        Drawable checkMarkDrawable = checkedTextView.getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            if (this.f6877d || this.f6878e) {
                Drawable drawableMutate = checkMarkDrawable.mutate();
                if (this.f6877d) {
                    E.a.h(drawableMutate, this.f6875b);
                }
                if (this.f6878e) {
                    E.a.i(drawableMutate, this.f6876c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(checkedTextView.getDrawableState());
                }
                checkedTextView.setCheckMarkDrawable(drawableMutate);
            }
        }
    }
}
