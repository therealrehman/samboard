package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;

/* JADX INFO: loaded from: classes.dex */
public final class c2 implements InterfaceC0178p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Toolbar f6679a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6680b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f6681c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f6682d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f6683e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Drawable f6684f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6685g;
    public CharSequence h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CharSequence f6686i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final CharSequence f6687j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Window.Callback f6688k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6689l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0171n f6690m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f6691n;
    public final Drawable o;

    public c2(Toolbar toolbar, boolean z9) {
        Drawable drawable;
        this.f6691n = 0;
        this.f6679a = toolbar;
        this.h = toolbar.getTitle();
        this.f6686i = toolbar.getSubtitle();
        this.f6685g = this.h != null;
        this.f6684f = toolbar.getNavigationIcon();
        S1 s1E = S1.e(toolbar.getContext(), null, AbstractC0478a.f10556a, R.attr.actionBarStyle, 0);
        int i5 = 15;
        this.o = s1E.b(15);
        if (z9) {
            TypedArray typedArray = s1E.f6522b;
            CharSequence text = typedArray.getText(27);
            if (!TextUtils.isEmpty(text)) {
                this.f6685g = true;
                this.h = text;
                if ((this.f6680b & 8) != 0) {
                    Toolbar toolbar2 = this.f6679a;
                    toolbar2.setTitle(text);
                    if (this.f6685g) {
                        androidx.core.view.W.j(toolbar2.getRootView(), text);
                    }
                }
            }
            CharSequence text2 = typedArray.getText(25);
            if (!TextUtils.isEmpty(text2)) {
                this.f6686i = text2;
                if ((this.f6680b & 8) != 0) {
                    toolbar.setSubtitle(text2);
                }
            }
            Drawable drawableB = s1E.b(20);
            if (drawableB != null) {
                this.f6683e = drawableB;
                c();
            }
            Drawable drawableB2 = s1E.b(17);
            if (drawableB2 != null) {
                this.f6682d = drawableB2;
                c();
            }
            if (this.f6684f == null && (drawable = this.o) != null) {
                this.f6684f = drawable;
                int i7 = this.f6680b & 4;
                Toolbar toolbar3 = this.f6679a;
                if (i7 != 0) {
                    toolbar3.setNavigationIcon(drawable);
                } else {
                    toolbar3.setNavigationIcon((Drawable) null);
                }
            }
            a(typedArray.getInt(10, 0));
            int resourceId = typedArray.getResourceId(9, 0);
            if (resourceId != 0) {
                View viewInflate = LayoutInflater.from(toolbar.getContext()).inflate(resourceId, (ViewGroup) toolbar, false);
                View view = this.f6681c;
                if (view != null && (this.f6680b & 16) != 0) {
                    toolbar.removeView(view);
                }
                this.f6681c = viewInflate;
                if (viewInflate != null && (this.f6680b & 16) != 0) {
                    toolbar.addView(viewInflate);
                }
                a(this.f6680b | 16);
            }
            int layoutDimension = typedArray.getLayoutDimension(13, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
                layoutParams.height = layoutDimension;
                toolbar.setLayoutParams(layoutParams);
            }
            int dimensionPixelOffset = typedArray.getDimensionPixelOffset(7, -1);
            int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(3, -1);
            if (dimensionPixelOffset >= 0 || dimensionPixelOffset2 >= 0) {
                toolbar.setContentInsetsRelative(Math.max(dimensionPixelOffset, 0), Math.max(dimensionPixelOffset2, 0));
            }
            int resourceId2 = typedArray.getResourceId(28, 0);
            if (resourceId2 != 0) {
                toolbar.setTitleTextAppearance(toolbar.getContext(), resourceId2);
            }
            int resourceId3 = typedArray.getResourceId(26, 0);
            if (resourceId3 != 0) {
                toolbar.setSubtitleTextAppearance(toolbar.getContext(), resourceId3);
            }
            int resourceId4 = typedArray.getResourceId(22, 0);
            if (resourceId4 != 0) {
                toolbar.setPopupTheme(resourceId4);
            }
        } else {
            if (toolbar.getNavigationIcon() != null) {
                this.o = toolbar.getNavigationIcon();
            } else {
                i5 = 11;
            }
            this.f6680b = i5;
        }
        s1E.f();
        if (R.string.sesl_action_bar_up_description != this.f6691n) {
            this.f6691n = R.string.sesl_action_bar_up_description;
            if (TextUtils.isEmpty(toolbar.getNavigationContentDescription())) {
                int i9 = this.f6691n;
                this.f6687j = i9 != 0 ? toolbar.getContext().getString(i9) : null;
                b();
            }
        }
        this.f6687j = toolbar.getNavigationContentDescription();
        toolbar.setNavigationOnClickListener(new a2(this));
    }

    public final void a(int i5) {
        View view;
        int i7 = this.f6680b ^ i5;
        this.f6680b = i5;
        if (i7 != 0) {
            if ((i7 & 4) != 0) {
                if ((i5 & 4) != 0) {
                    b();
                }
                int i9 = this.f6680b & 4;
                Toolbar toolbar = this.f6679a;
                if (i9 != 0) {
                    Drawable drawable = this.f6684f;
                    if (drawable == null) {
                        drawable = this.o;
                    }
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            if ((i7 & 3) != 0) {
                c();
            }
            int i10 = i7 & 8;
            Toolbar toolbar2 = this.f6679a;
            if (i10 != 0) {
                if ((i5 & 8) != 0) {
                    toolbar2.setTitle(this.h);
                    toolbar2.setSubtitle(this.f6686i);
                } else {
                    toolbar2.setTitle((CharSequence) null);
                    toolbar2.setSubtitle((CharSequence) null);
                }
            }
            if ((i7 & 16) == 0 || (view = this.f6681c) == null) {
                return;
            }
            if ((i5 & 16) != 0) {
                toolbar2.addView(view);
            } else {
                toolbar2.removeView(view);
            }
        }
    }

    public final void b() {
        if ((this.f6680b & 4) != 0) {
            boolean zIsEmpty = TextUtils.isEmpty(this.f6687j);
            Toolbar toolbar = this.f6679a;
            if (zIsEmpty) {
                toolbar.setNavigationContentDescription(this.f6691n);
            } else {
                toolbar.setNavigationContentDescription(this.f6687j);
            }
        }
    }

    public final void c() {
        Drawable drawable;
        int i5 = this.f6680b;
        if ((i5 & 2) == 0) {
            drawable = null;
        } else if ((i5 & 1) == 0 || (drawable = this.f6683e) == null) {
            drawable = this.f6682d;
        }
        this.f6679a.setLogo(drawable);
    }
}
