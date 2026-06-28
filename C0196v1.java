package androidx.appcompat.widget;

import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: androidx.appcompat.widget.v1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0196v1 extends LinearLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList f6863e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public InterfaceC0187s1 f6864f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f6865g;
    public Drawable h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6866i;

    public final void a() {
        ArrayList arrayList = this.f6863e;
        int size = arrayList.size();
        int i5 = 0;
        while (i5 < size) {
            ((C0190t1) arrayList.get(i5)).a(i5 == this.f6866i);
            i5++;
        }
    }

    public final void b(int i5) {
        if (i5 >= 0) {
            ArrayList arrayList = this.f6863e;
            if (i5 >= arrayList.size()) {
                return;
            }
            removeView((C0190t1) arrayList.remove(i5));
            if (this.f6866i >= arrayList.size()) {
                setSelectedPosition(this.f6866i - 1);
            } else {
                a();
            }
        }
    }

    public final Drawable getDefaultCircle() {
        return this.f6865g;
    }

    public final Drawable getSelectCircle() {
        return this.h;
    }

    public final int getSelectedPosition() {
        return this.f6866i;
    }

    public final int getSize() {
        return this.f6863e.size();
    }

    public final void setDefaultCircle(Drawable drawable) {
        for (C0190t1 c0190t1 : this.f6863e) {
            c0190t1.f6856e = drawable;
            c0190t1.a(c0190t1.f6858g);
        }
        this.f6865g = drawable;
    }

    public final void setOnItemClickListener(InterfaceC0187s1 itemClickListener) {
        kotlin.jvm.internal.j.f(itemClickListener, "itemClickListener");
        this.f6864f = itemClickListener;
        Iterator it = this.f6863e.iterator();
        while (it.hasNext()) {
            ((C0190t1) it.next()).setOnClickListener(new C5.b(10, itemClickListener, this));
        }
    }

    public final void setSelectCircle(Drawable drawable) {
        for (C0190t1 c0190t1 : this.f6863e) {
            c0190t1.f6857f = drawable;
            c0190t1.a(c0190t1.f6858g);
        }
        this.h = drawable;
    }

    public final void setSelectedPosition(int i5) {
        if (i5 < 0) {
            i5 = 0;
        } else {
            ArrayList arrayList = this.f6863e;
            if (i5 >= arrayList.size()) {
                i5 = arrayList.size() - 1;
            }
        }
        this.f6866i = i5;
        a();
    }
}
