package androidx.recyclerview.widget;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f9136b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f9137c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f9138d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9139e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f9140f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ StaggeredGridLayoutManager f9141g;

    public f1(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.f9141g = staggeredGridLayoutManager;
        a();
    }

    public final void a() {
        this.f9135a = -1;
        this.f9136b = Integer.MIN_VALUE;
        this.f9137c = false;
        this.f9138d = false;
        this.f9139e = false;
        int[] iArr = this.f9140f;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
    }
}
