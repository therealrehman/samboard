package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class SeslMenuDivider extends ImageView {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6571e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6572f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f6573g;

    public SeslMenuDivider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f6572f = (int) TypedValue.applyDimension(1, 1.5f, displayMetrics);
        this.f6571e = (int) TypedValue.applyDimension(1, 3.0f, displayMetrics);
        Paint paint = new Paint();
        this.f6573g = paint;
        paint.setFlags(1);
        Resources resources = context.getResources();
        int i5 = s6.c.O(context) ? R.color.sesl_popup_menu_divider_color_light : R.color.sesl_popup_menu_divider_color_dark;
        ThreadLocal threadLocal = C.s.f322a;
        paint.setColor(C.k.a(resources, i5, null));
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        int i5;
        int i7;
        super.onDraw(canvas);
        int width = (getWidth() - getPaddingStart()) - getPaddingEnd();
        int height = getHeight();
        int i9 = this.f6572f;
        int i10 = this.f6571e;
        int i11 = (width - i9) / (i10 + i9);
        int i12 = i11 + 1;
        int paddingStart = getPaddingStart() + ((int) ((i9 / 2.0f) + 0.5f));
        int i13 = (width - i9) - ((i10 + i9) * i11);
        if (i9 % 2 != 0) {
            i13--;
        }
        if (i11 > 0) {
            i7 = i13 / i11;
            i5 = i13 % i11;
        } else {
            i5 = 0;
            i7 = 0;
        }
        int i14 = 0;
        for (int i15 = 0; i15 < i12; i15++) {
            canvas.drawCircle(paddingStart + i14, height / 2.0f, i9 / 2.0f, this.f6573g);
            int i16 = i9 + i10 + i7 + i14;
            if (i15 < i5) {
                i16++;
            }
            i14 = i16;
        }
    }
}
