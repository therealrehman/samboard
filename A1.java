package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import androidx.recyclerview.sesl.drawable.SeslFastScrollerBgDrawable;
import com.samsung.android.keyscafe.R;
import g7.InterfaceC0562b;

/* JADX INFO: loaded from: classes.dex */
public final class a1 implements y8.G {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SeslFastScrollerBgDrawable f9047e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f9048f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f9049g;
    public final int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f9050i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final H0.f f9051j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final H0.f f9052k;

    /* JADX WARN: Type inference failed for: r1v3, types: [androidx.recyclerview.widget.Z0] */
    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.recyclerview.widget.Z0] */
    public a1(Context context, LayerDrawable layerDrawable) {
        this.f9047e = (SeslFastScrollerBgDrawable) layerDrawable.findDrawableByLayerId(R.id.thumb_bg);
        this.f9048f = context.getResources().getDimension(R.dimen.sesl_fast_scroller_thumb_min_width);
        this.f9049g = context.getResources().getDimension(R.dimen.sesl_fast_scroller_thumb_max_width);
        int iE = D.d.e(context.getResources().getColor(s6.c.O(context) ? R.color.sesl_scrollbar_handle_tint_color_light : R.color.sesl_scrollbar_handle_tint_color_dark), 255);
        this.f9050i = iE;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        this.h = D.d.e(context.getResources().getColor(typedValue.resourceId), 153);
        final int i5 = 0;
        H0.f fVar = new H0.f(new H0.a(350L, M.a.b(0.22f, 0.25f, 0.0f, 1.0f)), (Z0) new InterfaceC0562b(this) { // from class: androidx.recyclerview.widget.Z0

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ a1 f9040f;

            {
                this.f9040f = this;
            }

            @Override // g7.InterfaceC0562b
            public final Object invoke(Object obj) {
                switch (i5) {
                    case 0:
                        a1 a1Var = this.f9040f;
                        float f2 = a1Var.f9049g;
                        float f7 = a1Var.f9048f;
                        float fFloatValue = ((Float) obj).floatValue();
                        SeslFastScrollerBgDrawable seslFastScrollerBgDrawable = a1Var.f9047e;
                        seslFastScrollerBgDrawable.f8835a = (fFloatValue * (f2 - f7)) + f7;
                        seslFastScrollerBgDrawable.invalidateSelf();
                        break;
                    default:
                        a1 a1Var2 = this.f9040f;
                        a1Var2.getClass();
                        int iIntValue = ((Integer) obj).intValue();
                        SeslFastScrollerBgDrawable seslFastScrollerBgDrawable2 = a1Var2.f9047e;
                        seslFastScrollerBgDrawable2.f8836b.setColor(iIntValue);
                        seslFastScrollerBgDrawable2.invalidateSelf();
                        break;
                }
                return T6.p.f3328a;
            }
        });
        this.f9051j = fVar;
        final int i7 = 1;
        H0.f fVar2 = new H0.f(new H0.a(150L, M.a.b(0.0f, 0.0f, 1.0f, 1.0f)), new InterfaceC0562b(this) { // from class: androidx.recyclerview.widget.Z0

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ a1 f9040f;

            {
                this.f9040f = this;
            }

            @Override // g7.InterfaceC0562b
            public final Object invoke(Object obj) {
                switch (i7) {
                    case 0:
                        a1 a1Var = this.f9040f;
                        float f2 = a1Var.f9049g;
                        float f7 = a1Var.f9048f;
                        float fFloatValue = ((Float) obj).floatValue();
                        SeslFastScrollerBgDrawable seslFastScrollerBgDrawable = a1Var.f9047e;
                        seslFastScrollerBgDrawable.f8835a = (fFloatValue * (f2 - f7)) + f7;
                        seslFastScrollerBgDrawable.invalidateSelf();
                        break;
                    default:
                        a1 a1Var2 = this.f9040f;
                        a1Var2.getClass();
                        int iIntValue = ((Integer) obj).intValue();
                        SeslFastScrollerBgDrawable seslFastScrollerBgDrawable2 = a1Var2.f9047e;
                        seslFastScrollerBgDrawable2.f8836b.setColor(iIntValue);
                        seslFastScrollerBgDrawable2.invalidateSelf();
                        break;
                }
                return T6.p.f3328a;
            }
        }, (byte) 0);
        this.f9052k = fVar2;
        fVar.c(Float.valueOf(0.0f));
        fVar2.c(Integer.valueOf(iE));
    }

    @Override // y8.G
    public final void dispose() {
        this.f9051j.dispose();
        this.f9052k.dispose();
    }
}
