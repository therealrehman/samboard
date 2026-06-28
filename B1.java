package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import androidx.appcompat.widget.C0138c;
import com.samsung.android.keyscafe.R;
import f.AbstractC0510a;

/* JADX INFO: loaded from: classes.dex */
public final class b1 {

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public static final LinearInterpolator f9061f0 = new LinearInterpolator();

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public static final Y0 f9062g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final Y0 f9063h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static final Y0 f9064i0;
    public static final Y0 j0;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f9066B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Object[] f9067C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f9068D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f9069E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f9070F;
    public AbstractC0341j0 G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public SectionIndexer f9071H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f9072I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f9073J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final int f9074K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f9075L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final boolean f9076M;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final int f9078O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f9079P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public int f9080Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final int f9081R;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final float f9086W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public final int f9087X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public final int f9088Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public VelocityTracker f9089Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final a1 f9091a0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RecyclerView f9096d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextView f9097e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextView f9099f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ImageView f9100g;
    public final ImageView h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final View f9101i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Context f9102j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int[] f9103k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f9104l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f9105m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9106n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9107p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f9108q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f9110s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f9111t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f9112u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public AnimatorSet f9113v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public AnimatorSet f9114w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f9115x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f9116y;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f9090a = new Rect();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f9092b = new Rect();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f9094c = new Rect();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f9109r = 0.0f;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f9117z = -1;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f9065A = -1;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public long f9077N = -1;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public float f9082S = 0.0f;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public float f9083T = -1.0f;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public float f9084U = 0.0f;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public float f9085V = 0.0f;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final C6.a f9093b0 = new C6.a(8, this);

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final C0138c f9095c0 = new C0138c(2, this);
    public int d0 = -1;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f9098e0 = -1;

    static {
        ViewConfiguration.getTapTimeout();
        f9062g0 = new Y0("left", 0);
        f9063h0 = new Y0("top", 1);
        f9064i0 = new Y0("right", 2);
        j0 = new Y0("bottom", 3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [android.view.View, android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.view.View, android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r3v10, types: [android.view.ViewGroupOverlay] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [boolean, int] */
    public b1(RecyclerView recyclerView) {
        ?? r22;
        ?? r12;
        ?? r32;
        int[] iArr = new int[2];
        this.f9103k = iArr;
        this.f9081R = -1;
        this.f9096d = recyclerView;
        this.f9079P = e(recyclerView);
        this.f9080Q = recyclerView.getChildCount();
        Context context = recyclerView.getContext();
        this.f9102j = context;
        this.f9078O = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f9075L = recyclerView.getScrollBarStyle();
        this.f9116y = true;
        this.f9069E = 1;
        this.f9076M = context.getApplicationInfo().targetSdkVersion >= 11;
        ImageView imageView = new ImageView(context);
        this.h = imageView;
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
        imageView.setScaleType(scaleType);
        ImageView imageView2 = new ImageView(context);
        this.f9100g = imageView2;
        imageView2.setScaleType(scaleType);
        View view = new View(context);
        this.f9101i = view;
        view.setAlpha(0.0f);
        TextView textViewD = d(context);
        this.f9097e = textViewD;
        TextView textViewD2 = d(context);
        this.f9099f = textViewD2;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, G0.a.f1022a, 0, 2132018606);
        try {
            this.f9074K = typedArrayObtainStyledAttributes.getInt(8, 0);
            iArr[0] = typedArrayObtainStyledAttributes.getResourceId(6, 0);
            iArr[1] = typedArrayObtainStyledAttributes.getResourceId(7, 0);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(9);
            Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(14);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(2);
            float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
            int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0);
            int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
            int dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, 0);
            int dimensionPixelSize5 = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, 0);
            int dimensionPixelSize6 = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 0);
            this.f9108q = typedArrayObtainStyledAttributes.getInt(13, 0);
            typedArrayObtainStyledAttributes.recycle();
            if (drawable instanceof LayerDrawable) {
                this.f9091a0 = new a1(context, (LayerDrawable) drawable);
            } else {
                this.f9091a0 = null;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            this.f9081R = D.d.e(context.getResources().getColor(typedValue.resourceId), 229);
            imageView.setImageDrawable(drawable2);
            int iMax = drawable2 != null ? Math.max(0, drawable2.getIntrinsicWidth()) : 0;
            imageView2.setImageDrawable(drawable);
            imageView2.setMinimumWidth(dimensionPixelSize4);
            imageView2.setMinimumHeight(dimensionPixelSize5);
            this.f9112u = Math.max(drawable != null ? Math.max(iMax, drawable.getIntrinsicWidth()) : iMax, dimensionPixelSize4);
            view.setMinimumWidth(dimensionPixelSize2);
            view.setMinimumHeight(dimensionPixelSize3);
            if (resourceId != 0) {
                TextView textView = textViewD;
                textView.setTextAppearance(context, resourceId);
                TextView textView2 = textViewD2;
                textView2.setTextAppearance(context, resourceId);
                r12 = textView;
                r22 = textView2;
            } else {
                r22 = textViewD2;
                r12 = textViewD;
            }
            if (colorStateList != null) {
                r12.setTextColor(colorStateList);
                r22.setTextColor(colorStateList);
            }
            if (dimensionPixelSize > 0.0f) {
                r32 = 0;
                r12.setTextSize(0, dimensionPixelSize);
                r22.setTextSize(0, dimensionPixelSize);
            } else {
                r32 = 0;
            }
            int iMax2 = Math.max((int) r32, dimensionPixelSize3);
            r12.setMinimumWidth(dimensionPixelSize2);
            r12.setMinimumHeight(iMax2);
            r12.setIncludeFontPadding(r32);
            r22.setMinimumWidth(dimensionPixelSize2);
            r22.setMinimumHeight(iMax2);
            r22.setIncludeFontPadding(r32);
            boolean z9 = this.f9069E == 2;
            imageView2.setPressed(z9);
            imageView.setPressed(z9);
            ?? overlay = recyclerView.getOverlay();
            overlay.add(imageView);
            overlay.add(imageView2);
            overlay.add(view);
            overlay.add(r12);
            overlay.add(r22);
            Resources resources = context.getResources();
            this.f9104l = resources.getDimensionPixelOffset(R.dimen.sesl_fast_scroll_preview_margin_end);
            this.f9086W = resources.getDimension(R.dimen.sesl_fast_scroll_additional_touch_area);
            this.f9105m = resources.getDimensionPixelOffset(R.dimen.sesl_fast_scroller_track_vertical_padding);
            this.f9106n = 0;
            this.o = 0;
            this.f9107p = 0;
            r12.setPadding(dimensionPixelSize6, 0, dimensionPixelSize6, 0);
            r22.setPadding(dimensionPixelSize6, 0, dimensionPixelSize6, 0);
            h();
            x(this.f9080Q);
            r(recyclerView.getVerticalScrollbarPosition());
            p();
            this.f9087X = s6.c.X(26);
            this.f9088Y = s6.c.X(24);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public static int e(RecyclerView recyclerView) {
        if (recyclerView.getAdapter() == null) {
            return 0;
        }
        return recyclerView.getAdapter().getItemCount();
    }

    public static AnimatorSet i(Property property, float f2, View... viewArr) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builderPlay = null;
        for (int length = viewArr.length - 1; length >= 0; length--) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewArr[length], (Property<View, Float>) property, f2);
            if (builderPlay == null) {
                builderPlay = animatorSet.play(objectAnimatorOfFloat);
            } else {
                builderPlay.with(objectAnimatorOfFloat);
            }
        }
        return animatorSet;
    }

    public final void a(Rect rect, View view) {
        view.layout(rect.left, rect.top, rect.right, rect.bottom);
        view.setPivotX(this.f9072I ? rect.right - rect.left : 0.0f);
    }

    public final void b() {
        this.f9077N = -1L;
        if (this.G == null) {
            h();
        }
        RecyclerView recyclerView = this.f9096d;
        recyclerView.requestDisallowInterceptTouchEvent(true);
        MotionEvent motionEventObtain = MotionEvent.obtain(0L, 0L, 3, 0.0f, 0.0f, 0);
        recyclerView.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
        s(2);
    }

    public final boolean c(int i5) {
        RecyclerView recyclerView = this.f9096d;
        int childCount = recyclerView.getChildCount();
        if (childCount == 0) {
            return false;
        }
        int iFindFirstVisibleItemPosition = recyclerView.findFirstVisibleItemPosition();
        Rect rect = recyclerView.mListPadding;
        if (i5 > 0) {
            return iFindFirstVisibleItemPosition + childCount < e(recyclerView) || recyclerView.getChildAt(childCount + (-1)).getBottom() > recyclerView.getHeight() - rect.bottom;
        }
        return iFindFirstVisibleItemPosition > 0 || recyclerView.getChildAt(0).getTop() < rect.top;
    }

    public final TextView d(Context context) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        textView.setGravity(17);
        textView.setAlpha(0.0f);
        textView.setLayoutDirection(this.f9096d.getLayoutDirection());
        return textView;
    }

    public final float f(int i5, int i7, int i9) {
        int spanSize;
        Object[] objArr;
        int positionForSection;
        if (this.f9071H == null || this.G == null) {
            h();
        }
        if (i7 == 0 || i9 == 0) {
            return 0.0f;
        }
        SectionIndexer sectionIndexer = this.f9071H;
        RecyclerView recyclerView = this.f9096d;
        int paddingTop = recyclerView.getPaddingTop();
        AbstractC0370y0 layoutManager = recyclerView.getLayoutManager();
        if (paddingTop > 0 && (layoutManager instanceof LinearLayoutManager)) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            while (i5 > 0) {
                int i10 = i5 - 1;
                if (linearLayoutManager.findViewByPosition(i10) == null) {
                    break;
                }
                i5 = i10;
            }
        }
        int childAdapterPosition = i5 - recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0));
        if (childAdapterPosition < 0) {
            childAdapterPosition = 0;
        }
        View childAt = recyclerView.getChildAt(childAdapterPosition);
        float top = (childAt == null || childAt.getHeight() == 0) ? 0.0f : i5 == 0 ? (paddingTop - childAt.getTop()) / (childAt.getHeight() + paddingTop) : (-childAt.getTop()) / childAt.getHeight();
        if (sectionIndexer == null || (objArr = this.f9067C) == null || objArr.length <= 0 || !this.f9076M) {
            if (i7 != i9 || (i5 != 0 && !(layoutManager instanceof StaggeredGridLayoutManager))) {
                if (layoutManager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    spanSize = gridLayoutManager.f8899f / gridLayoutManager.f8903k.getSpanSize(i5);
                } else {
                    spanSize = layoutManager instanceof StaggeredGridLayoutManager ? ((StaggeredGridLayoutManager) layoutManager).f9002e : 1;
                }
                f = ((top * spanSize) + i5) / i9;
            } else if ((layoutManager instanceof StaggeredGridLayoutManager) && i5 != 0 && childAt != null) {
                ((g1) childAt.getLayoutParams()).getClass();
            }
        } else {
            if (i5 < 0) {
                return 0.0f;
            }
            int sectionForPosition = sectionIndexer.getSectionForPosition(i5);
            int positionForSection2 = sectionIndexer.getPositionForSection(sectionForPosition);
            int length = this.f9067C.length;
            if (sectionForPosition < length - 1) {
                int i11 = sectionForPosition + 1;
                positionForSection = (i11 < length ? sectionIndexer.getPositionForSection(i11) : i9 - 1) - positionForSection2;
            } else {
                positionForSection = i9 - positionForSection2;
            }
            f = (sectionForPosition + (positionForSection != 0 ? ((i5 + top) - positionForSection2) / positionForSection : 0.0f)) / length;
        }
        if (i5 + i7 != i9) {
            return f;
        }
        View childAt2 = recyclerView.getChildAt(i7 - 1);
        View childAt3 = recyclerView.getChildAt(0);
        int paddingBottom = recyclerView.getPaddingBottom() + (childAt2.getBottom() - recyclerView.getHeight());
        int top2 = paddingBottom - (childAt3.getTop() - recyclerView.getPaddingTop());
        if (top2 > childAt2.getHeight() || i5 > 0) {
            top2 = childAt2.getHeight();
        }
        int i12 = top2 - paddingBottom;
        return (i12 <= 0 || top2 <= 0) ? f : f + ((i12 / top2) * (1.0f - f));
    }

    public final float g(float f2) {
        float f7 = this.f9111t;
        if (f7 <= 0.0f) {
            return 0.0f;
        }
        return com.bumptech.glide.c.c(((f2 - this.f9110s) + this.f9109r) / f7, 0.0f, 1.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void h() {
        this.f9071H = null;
        AbstractC0341j0 adapter = this.f9096d.getAdapter();
        if (!(adapter instanceof SectionIndexer)) {
            this.G = adapter;
            this.f9067C = null;
        } else {
            this.G = adapter;
            SectionIndexer sectionIndexer = (SectionIndexer) adapter;
            this.f9071H = sectionIndexer;
            this.f9067C = sectionIndexer.getSections();
        }
    }

    public final boolean j() {
        if (this.f9073J && !this.f9066B) {
            this.f9066B = c(1) || c(-1);
        }
        return this.f9073J && this.f9066B;
    }

    public final boolean k(float f2, float f7) {
        boolean z9 = this.f9072I;
        ImageView imageView = this.f9100g;
        float f9 = this.f9086W;
        if (!z9 ? f2 <= imageView.getRight() + f9 : f2 >= imageView.getLeft() - f9) {
            float translationY = imageView.getTranslationY();
            float top = imageView.getTop() + translationY;
            float bottom = imageView.getBottom() + translationY;
            if (f7 >= top && f7 <= bottom && this.f9069E != 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(android.widget.TextView r9, android.graphics.Rect r10) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.b1.l(android.widget.TextView, android.graphics.Rect):void");
    }

    public final void m(int i5, int i7, int i9) {
        if (!j()) {
            s(0);
            return;
        }
        if ((c(1) || c(-1)) && this.f9069E != 2) {
            float f2 = this.f9083T;
            if (f2 != -1.0f) {
                t(f2);
                this.f9083T = -1.0f;
            } else {
                t(f(i5, i7, i9));
            }
            if (this.f9069E != 2) {
                s(1);
                p();
            }
        }
        this.f9116y = true;
    }

    public final void n() {
        if (!j()) {
            s(0);
        } else if (this.f9069E == 1) {
            p();
        } else {
            s(1);
            p();
        }
    }

    public final boolean o(MotionEvent motionEvent) {
        Rect rect = this.f9094c;
        int i5 = rect.top;
        int i7 = rect.bottom;
        ImageView imageView = this.h;
        float top = imageView.getTop();
        float bottom = imageView.getBottom();
        this.f9082S = motionEvent.getY();
        if (!j()) {
            return false;
        }
        if (this.f9089Z == null) {
            this.f9089Z = VelocityTracker.obtain();
        }
        this.f9089Z.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                if (this.f9077N >= 0) {
                    b();
                    this.f9089Z.computeCurrentVelocity(1000);
                    float yVelocity = this.f9089Z.getYVelocity();
                    float fG = g(motionEvent.getY());
                    this.f9083T = fG;
                    t(fG);
                    q(fG, Math.abs(yVelocity));
                }
                this.f9089Z.clear();
                if (this.f9069E == 2) {
                    this.f9096d.requestDisallowInterceptTouchEvent(false);
                    s(1);
                    p();
                    this.f9082S = 0.0f;
                    this.f9109r = 0.0f;
                    return true;
                }
            } else if (actionMasked == 2) {
                if (this.f9077N >= 0 && Math.abs(motionEvent.getY()) > this.f9078O) {
                    b();
                    float f2 = this.f9082S;
                    float f7 = i5;
                    if (f2 > f7 && f2 < i7) {
                        float f9 = f7 + top;
                        if (f2 < f9) {
                            this.f9082S = f9;
                        } else if (f2 > bottom) {
                            this.f9082S = bottom;
                        }
                    }
                }
                if (this.f9069E == 2) {
                    float fG2 = g(motionEvent.getY());
                    this.f9083T = fG2;
                    t(fG2);
                    if (this.f9084U != 0.0f && Math.abs(this.f9085V - this.f9082S) <= this.f9084U) {
                        return true;
                    }
                    this.f9085V = this.f9082S;
                    if (this.f9116y) {
                        this.f9089Z.computeCurrentVelocity(1000);
                        q(fG2, Math.abs(this.f9089Z.getYVelocity()));
                    }
                    float f10 = this.f9082S;
                    float f11 = i5;
                    if (f10 > f11 && f10 < i7) {
                        float f12 = f11 + top;
                        if (f10 < f12) {
                            this.f9082S = f12;
                        } else if (f10 > bottom) {
                            this.f9082S = bottom;
                        }
                    }
                    return true;
                }
            } else if (actionMasked == 3) {
                this.f9077N = -1L;
                this.f9089Z.clear();
                if (this.f9069E == 2) {
                    s(0);
                }
                this.f9082S = 0.0f;
            }
        } else if (k(motionEvent.getX(), motionEvent.getY())) {
            b();
            this.f9109r = ((r0.getHeight() / 2.0f) + (r0.getTop() + this.f9100g.getTranslationY())) - motionEvent.getY();
            return true;
        }
        return false;
    }

    public final void p() {
        RecyclerView recyclerView = this.f9096d;
        C6.a aVar = this.f9093b0;
        recyclerView.removeCallbacks(aVar);
        recyclerView.postDelayed(aVar, 1500L);
    }

    public final void q(float f2, float f7) {
        int iD;
        int i5;
        int i7 = -1;
        this.f9116y = false;
        RecyclerView recyclerView = this.f9096d;
        int iE = e(recyclerView);
        Object[] objArr = this.f9067C;
        int length = objArr == null ? 0 : objArr.length;
        if (objArr == null || length <= 0) {
            iD = com.bumptech.glide.c.d((int) (iE * f2), 0, iE - 1);
        } else {
            float f9 = length;
            int i9 = length - 1;
            int iD2 = com.bumptech.glide.c.d((int) (f2 * f9), 0, i9);
            int positionForSection = this.f9071H.getPositionForSection(iD2);
            int i10 = iD2 + 1;
            int positionForSection2 = iD2 < i9 ? this.f9071H.getPositionForSection(i10) : iE;
            if (positionForSection2 == positionForSection) {
                i5 = iD2;
                int positionForSection3 = positionForSection;
                while (true) {
                    if (i5 <= 0) {
                        i7 = iD2;
                        i5 = i7;
                        break;
                    }
                    i5--;
                    positionForSection3 = this.f9071H.getPositionForSection(i5);
                    if (positionForSection3 != positionForSection) {
                        i7 = i5;
                        break;
                    } else if (i5 == 0) {
                        i7 = 0;
                        i5 = iD2;
                        break;
                    }
                }
                positionForSection = positionForSection3;
            } else {
                i7 = iD2;
                i5 = i7;
            }
            int i11 = iD2 + 2;
            while (i11 < length && this.f9071H.getPositionForSection(i11) == positionForSection2) {
                i11++;
                i10++;
            }
            float f10 = i5 / f9;
            float f11 = i10 / f9;
            float f12 = iE == 0 ? Float.MAX_VALUE : 0.125f / iE;
            if (i5 != iD2 || f2 - f10 >= f12) {
                positionForSection += (int) (((f2 - f10) * (positionForSection2 - positionForSection)) / (f11 - f10));
            }
            iD = com.bumptech.glide.c.d(positionForSection, 0, iE - 1);
        }
        AbstractC0370y0 layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(iD, 0);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).B(iD, true);
        }
        m(recyclerView.findFirstVisibleItemPosition(), recyclerView.getChildCount(), e(recyclerView));
        this.f9117z = i7;
        boolean zU = u(f7, i7);
        Log.d("SeslFastScroller", "scrollTo() called transitionPreviewLayout() sectionIndex =" + i7 + ", position = " + f2);
        boolean z9 = this.f9070F;
        if (z9 || !zU) {
            if (!z9 || zU) {
                return;
            }
            v();
            return;
        }
        AnimatorSet animatorSet = this.f9113v;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        Animator duration = i(View.ALPHA, 1.0f, this.f9100g, this.h, this.f9101i).setDuration(167L);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.f9113v = animatorSet2;
        animatorSet2.playTogether(duration);
        this.f9113v.setInterpolator(AbstractC0510a.f10750a);
        this.f9113v.start();
        this.f9070F = true;
    }

    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v3 */
    public final void r(int i5) {
        AbstractC0370y0 layoutManager = this.f9096d.getLayoutManager();
        if (i5 == 0 && layoutManager != null) {
            i5 = layoutManager.getLayoutDirection() == 1 ? 1 : 2;
        }
        if (this.f9065A != i5) {
            this.f9065A = i5;
            ?? r12 = i5 == 1 ? 0 : 1;
            this.f9072I = r12;
            int i7 = this.f9103k[r12];
            View view = this.f9101i;
            view.setBackgroundResource(i7);
            view.getBackground().setTintMode(PorterDuff.Mode.MULTIPLY);
            view.getBackground().setTint(this.f9081R);
            this.d0 = -1;
            this.f9098e0 = -1;
            w();
        }
    }

    public final void s(int i5) {
        int i7;
        this.f9096d.removeCallbacks(this.f9093b0);
        if (i5 == this.f9069E) {
            return;
        }
        ImageView imageView = this.f9100g;
        ImageView imageView2 = this.h;
        if (i5 == 0) {
            this.f9070F = false;
            this.f9117z = -1;
            AnimatorSet animatorSet = this.f9113v;
            if (animatorSet != null) {
                animatorSet.cancel();
                i7 = 150;
            } else {
                i7 = 0;
            }
            Animator duration = i(View.ALPHA, 0.0f, imageView, imageView2, this.f9101i, this.f9097e, this.f9099f).setDuration(i7);
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.f9113v = animatorSet2;
            animatorSet2.playTogether(duration);
            this.f9113v.setInterpolator(f9061f0);
            this.f9113v.start();
        } else if (i5 == 1) {
            v();
        } else if (i5 == 2) {
            u(0.0f, this.f9117z);
        }
        a1 a1Var = this.f9091a0;
        if (a1Var != null) {
            boolean z9 = i5 == 2;
            a1Var.f9051j.c(Float.valueOf(z9 ? 1.0f : 0.0f));
            a1Var.f9052k.c(Integer.valueOf(z9 ? a1Var.h : a1Var.f9050i));
        }
        this.f9069E = i5;
        boolean z10 = i5 == 2;
        imageView.setPressed(z10);
        imageView2.setPressed(z10);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x000c A[PHI: r2
      0x000c: PHI (r2v6 float) = (r2v0 float), (r2v1 float) binds: [B:3:0x000a, B:6:0x0011] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void t(float r6) {
        /*
            r5 = this;
            android.graphics.Rect r0 = r5.f9094c
            int r1 = r0.top
            int r0 = r0.bottom
            r2 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r3 <= 0) goto Le
        Lc:
            r6 = r2
            goto L14
        Le:
            r2 = 0
            int r3 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r3 >= 0) goto L14
            goto Lc
        L14:
            float r2 = r5.f9111t
            float r6 = r6 * r2
            float r2 = r5.f9110s
            float r6 = r6 + r2
            android.widget.ImageView r2 = r5.f9100g
            int r3 = r2.getHeight()
            float r3 = (float) r3
            r4 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 / r4
            float r3 = r6 - r3
            r2.setTranslationY(r3)
            android.view.View r2 = r5.f9101i
            int r3 = r2.getHeight()
            float r3 = (float) r3
            float r3 = r3 / r4
            float r1 = (float) r1
            float r1 = r1 + r3
            float r0 = (float) r0
            float r0 = r0 - r3
            float r6 = com.bumptech.glide.c.c(r6, r1, r0)
            float r6 = r6 - r3
            r2.setTranslationY(r6)
            android.widget.TextView r0 = r5.f9097e
            r0.setTranslationY(r6)
            android.widget.TextView r5 = r5.f9099f
            r5.setTranslationY(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.b1.t(float):void");
    }

    public final boolean u(float f2, int i5) {
        Object obj;
        Object[] objArr = this.f9067C;
        String string = (objArr == null || i5 < 0 || i5 >= objArr.length || (obj = objArr[i5]) == null) ? null : obj.toString();
        boolean z9 = this.f9115x;
        TextView textView = this.f9099f;
        TextView textView2 = this.f9097e;
        if (!z9) {
            textView2 = textView;
            textView = textView2;
        }
        textView.setText(string);
        Rect rect = this.f9090a;
        l(textView, rect);
        a(rect, textView);
        int i7 = this.f9069E;
        if (i7 == 1) {
            textView2.setText("");
        } else if (i7 == 2 && textView2.getText().equals(string) && textView2.getAlpha() != 0.0f) {
            return !TextUtils.isEmpty(string);
        }
        AnimatorSet animatorSet = this.f9114w;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        if (!textView2.getText().equals("")) {
            RecyclerView recyclerView = this.f9096d;
            if (f2 > 1000.0f) {
                recyclerView.performHapticFeedback(this.f9088Y);
            } else {
                recyclerView.performHapticFeedback(this.f9087X);
            }
        }
        Property property = View.ALPHA;
        Animator duration = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) property, 1.0f).setDuration(0L);
        Animator duration2 = ObjectAnimator.ofFloat(textView2, (Property<TextView, Float>) property, 0.0f).setDuration(0L);
        duration2.addListener(this.f9095c0);
        int i9 = rect.left;
        View view = this.f9101i;
        rect.left = i9 - view.getPaddingLeft();
        rect.top -= view.getPaddingTop();
        rect.right = view.getPaddingRight() + rect.right;
        rect.bottom = view.getPaddingBottom() + rect.bottom;
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofInt(f9062g0, rect.left), PropertyValuesHolder.ofInt(f9063h0, rect.top), PropertyValuesHolder.ofInt(f9064i0, rect.right), PropertyValuesHolder.ofInt(j0, rect.bottom));
        objectAnimatorOfPropertyValuesHolder.setDuration(100L);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.f9114w = animatorSet2;
        AnimatorSet.Builder builderWith = animatorSet2.play(duration2).with(duration);
        builderWith.with(objectAnimatorOfPropertyValuesHolder);
        int width = (view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight();
        int width2 = textView.getWidth();
        if (width2 > width) {
            textView.setScaleX(width / width2);
            builderWith.with(ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.SCALE_X, 1.0f).setDuration(100L));
        } else {
            textView.setScaleX(1.0f);
        }
        int width3 = textView2.getWidth();
        if (width3 > width2) {
            builderWith.with(ObjectAnimator.ofFloat(textView2, (Property<TextView, Float>) View.SCALE_X, width2 / width3).setDuration(100L));
        }
        this.f9114w.setInterpolator(AbstractC0510a.f10750a);
        this.f9114w.start();
        return !TextUtils.isEmpty(string);
    }

    public final void v() {
        AnimatorSet animatorSet = this.f9113v;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        Property property = View.ALPHA;
        Animator duration = i(property, 1.0f, this.f9100g, this.h).setDuration(167L);
        Animator duration2 = i(property, 0.0f, this.f9101i, this.f9097e, this.f9099f).setDuration(150L);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.f9113v = animatorSet2;
        animatorSet2.playTogether(duration, duration2);
        this.f9113v.setInterpolator(AbstractC0510a.f10750a);
        this.f9070F = false;
        this.f9113v.start();
    }

    public final void w() {
        int i5;
        int i7;
        int i9;
        if (this.f9069E == 2) {
            return;
        }
        RecyclerView recyclerView = this.f9096d;
        int iComputeVerticalScrollRange = recyclerView.computeVerticalScrollRange();
        int iComputeVerticalScrollExtent = recyclerView.computeVerticalScrollExtent();
        int i10 = this.d0;
        Rect rect = this.f9094c;
        if ((i10 <= 0 || iComputeVerticalScrollRange != i10 || (i9 = this.f9098e0) <= 0 || iComputeVerticalScrollExtent != i9 || rect.width() <= 0) && !this.f9068D) {
            this.f9068D = true;
            this.d0 = iComputeVerticalScrollRange;
            this.f9098e0 = iComputeVerticalScrollExtent;
            rect.left = 0;
            rect.top = 0;
            rect.right = recyclerView.getWidth();
            rect.bottom = recyclerView.getHeight();
            int i11 = this.f9075L;
            if (i11 == 16777216 || i11 == 0) {
                rect.left = recyclerView.getPaddingLeft() + rect.left;
                rect.top = recyclerView.getPaddingTop() + rect.top;
                rect.right -= recyclerView.getPaddingRight();
                rect.bottom -= recyclerView.getPaddingBottom();
                if (i11 == 16777216) {
                    int i12 = this.f9112u;
                    if (this.f9065A == 2) {
                        rect.right += i12;
                    } else {
                        rect.left -= i12;
                    }
                }
            }
            boolean z9 = this.f9072I;
            Rect rect2 = this.f9090a;
            Context context = this.f9102j;
            if (z9) {
                int iWidth = rect.width();
                rect2.right = iWidth;
                rect2.left = iWidth - context.getResources().getDimensionPixelOffset(R.dimen.sesl_fast_scroll_thumb_width);
            } else {
                rect2.right = context.getResources().getDimensionPixelOffset(R.dimen.sesl_fast_scroll_thumb_width);
                rect2.left = 0;
            }
            rect2.top = 0;
            int height = recyclerView.getHeight();
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.sesl_fast_scroll_thumb_min_height);
            int iRound = Math.round((height * this.f9098e0) / this.d0);
            if (iRound >= dimensionPixelOffset) {
                dimensionPixelOffset = iRound;
            }
            rect2.bottom = dimensionPixelOffset;
            ImageView imageView = this.f9100g;
            a(rect2, imageView);
            int iMax = Math.max(0, rect.width());
            int iMax2 = Math.max(0, rect.height());
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax, Integer.MIN_VALUE);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(iMax2), 0);
            ImageView imageView2 = this.h;
            imageView2.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int i13 = this.f9108q;
            int i14 = this.f9105m;
            if (i13 == 1) {
                i7 = rect.top + i14 + this.o;
                i5 = (rect.bottom - i14) - this.f9106n;
            } else {
                int height2 = imageView.getHeight() / 2;
                int i15 = rect.top + height2 + i14 + this.o;
                i5 = ((rect.bottom - height2) - i14) - this.f9106n;
                i7 = i15;
            }
            if (i5 < i7) {
                Log.e("SeslFastScroller", "Error occured during layoutTrack() because bottom[" + i5 + "] is less than top[" + i5 + "].");
                i5 = i7;
            }
            int measuredWidth = imageView2.getMeasuredWidth();
            int width = ((imageView.getWidth() - measuredWidth) / 2) + imageView.getLeft();
            imageView2.layout(width, i7, measuredWidth + width, i5);
            y();
            this.f9068D = false;
            TextView textView = this.f9097e;
            l(textView, rect2);
            a(rect2, textView);
            TextView textView2 = this.f9099f;
            l(textView2, rect2);
            a(rect2, textView2);
            int i16 = rect2.left;
            View view = this.f9101i;
            rect2.left = i16 - view.getPaddingLeft();
            rect2.top -= view.getPaddingTop();
            rect2.right = view.getPaddingRight() + rect2.right;
            rect2.bottom = view.getPaddingBottom() + rect2.bottom;
            a(rect2, view);
            float f2 = this.f9083T;
            if (f2 != -1.0f) {
                t(f2);
                this.f9083T = -1.0f;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void x(int r2) {
        /*
            r1 = this;
            if (r2 <= 0) goto L11
            r2 = 1
            boolean r0 = r1.c(r2)
            if (r0 != 0) goto L12
            r0 = -1
            boolean r0 = r1.c(r0)
            if (r0 == 0) goto L11
            goto L12
        L11:
            r2 = 0
        L12:
            boolean r0 = r1.f9066B
            if (r0 == r2) goto L1b
            r1.f9066B = r2
            r1.n()
        L1b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.b1.x(int):void");
    }

    public final void y() {
        float top;
        float bottom;
        int i5 = this.f9108q;
        ImageView imageView = this.h;
        if (i5 == 1) {
            float height = this.f9100g.getHeight() / 2.0f;
            top = imageView.getTop() + height;
            bottom = imageView.getBottom() - height;
        } else {
            top = imageView.getTop();
            bottom = imageView.getBottom();
        }
        this.f9110s = top;
        float f2 = (bottom - top) - this.f9107p;
        this.f9111t = f2;
        if (f2 < 0.0f) {
            this.f9111t = 0.0f;
        }
    }
}
