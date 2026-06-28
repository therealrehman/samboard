package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* JADX INFO: renamed from: androidx.appcompat.widget.t1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0190t1 extends FrameLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f6856e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f6857f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6858g;
    public final ImageView h;

    public C0190t1(Context context) {
        super(context, null);
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(this.f6857f);
        addView(imageView);
        this.h = imageView;
    }

    public final void a(boolean z9) {
        this.h.setImageDrawable(z9 ? this.f6857f : this.f6856e);
        setSelected(z9);
        this.f6858g = z9;
    }
}
