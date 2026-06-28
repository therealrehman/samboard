package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.RectF;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0166l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6736a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f6737b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f6738c = -1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f6739d = -1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f6740e = -1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f6741f = new int[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6742g = false;
    public final TextView h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f6743i;

    static {
        new RectF();
        new ConcurrentHashMap();
        new ConcurrentHashMap();
    }

    public C0166l0(TextView textView) {
        this.h = textView;
        this.f6743i = textView.getContext();
        new C0160j0();
    }

    public static int[] a(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i5 : iArr) {
            if (i5 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i5)) < 0) {
                arrayList.add(Integer.valueOf(i5));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i7 = 0; i7 < size; i7++) {
            iArr2[i7] = ((Integer) arrayList.get(i7)).intValue();
        }
        return iArr2;
    }

    public final boolean b() {
        if (d() && this.f6736a == 1) {
            if (!this.f6742g || this.f6741f.length == 0) {
                int iFloor = ((int) Math.floor((this.f6740e - this.f6739d) / this.f6738c)) + 1;
                int[] iArr = new int[iFloor];
                for (int i5 = 0; i5 < iFloor; i5++) {
                    iArr[i5] = Math.round((i5 * this.f6738c) + this.f6739d);
                }
                this.f6741f = a(iArr);
            }
            this.f6737b = true;
        } else {
            this.f6737b = false;
        }
        return this.f6737b;
    }

    public final boolean c() {
        boolean z9 = this.f6741f.length > 0;
        this.f6742g = z9;
        if (z9) {
            this.f6736a = 1;
            this.f6739d = r0[0];
            this.f6740e = r0[r1 - 1];
            this.f6738c = -1.0f;
        }
        return z9;
    }

    public final boolean d() {
        return !(this.h instanceof B);
    }

    public final void e(float f2, float f7, float f9) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        }
        if (f7 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f7 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        }
        if (f9 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f9 + "px) is less or equal to (0px)");
        }
        this.f6736a = 1;
        this.f6739d = f2;
        this.f6740e = f7;
        this.f6738c = f9;
        this.f6742g = false;
    }
}
