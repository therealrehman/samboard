package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: androidx.recyclerview.widget.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0364v0 implements k1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0370y0 f9282b;

    public /* synthetic */ C0364v0(AbstractC0370y0 abstractC0370y0, int i5) {
        this.f9281a = i5;
        this.f9282b = abstractC0370y0;
    }

    @Override // androidx.recyclerview.widget.k1
    public final int a() {
        switch (this.f9281a) {
            case 0:
                AbstractC0370y0 abstractC0370y0 = this.f9282b;
                return abstractC0370y0.getWidth() - abstractC0370y0.getPaddingRight();
            default:
                AbstractC0370y0 abstractC0370y02 = this.f9282b;
                return abstractC0370y02.getHeight() - abstractC0370y02.getPaddingBottom();
        }
    }

    @Override // androidx.recyclerview.widget.k1
    public final int b(View view) {
        switch (this.f9281a) {
            case 0:
                return this.f9282b.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((C0372z0) view.getLayoutParams())).leftMargin;
            default:
                return this.f9282b.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((C0372z0) view.getLayoutParams())).topMargin;
        }
    }

    @Override // androidx.recyclerview.widget.k1
    public final View c(int i5) {
        switch (this.f9281a) {
        }
        return this.f9282b.getChildAt(i5);
    }

    @Override // androidx.recyclerview.widget.k1
    public final int d() {
        switch (this.f9281a) {
            case 0:
                return this.f9282b.getPaddingLeft();
            default:
                return this.f9282b.getPaddingTop();
        }
    }

    @Override // androidx.recyclerview.widget.k1
    public final int e(View view) {
        switch (this.f9281a) {
            case 0:
                return this.f9282b.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((C0372z0) view.getLayoutParams())).rightMargin;
            default:
                return this.f9282b.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((C0372z0) view.getLayoutParams())).bottomMargin;
        }
    }
}
