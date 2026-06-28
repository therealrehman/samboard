package androidx.picker.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import com.samsung.android.keyscafe.R;
import java.util.Calendar;

/* JADX INFO: renamed from: androidx.picker.widget.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0299t extends View {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Calendar f8442e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f8443f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f8444g;
    public final int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f8445i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f8446j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final String f8447k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SeslDatePicker f8448l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0299t(SeslDatePicker seslDatePicker, Context context, TypedArray typedArray) {
        super(context);
        this.f8448l = seslDatePicker;
        this.f8446j = new int[7];
        this.f8442e = Calendar.getInstance();
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sesl_date_picker_month_day_label_text_size);
        int color = typedArray.getColor(6, resources.getColor(R.color.sesl_date_picker_normal_text_color_light));
        this.f8444g = color;
        this.h = typedArray.getColor(10, resources.getColor(R.color.sesl_date_picker_sunday_text_color_light));
        ThreadLocal threadLocal = C.s.f322a;
        this.f8445i = C.k.a(resources, R.color.sesl_date_picker_saturday_week_text_color_light, null);
        String str = seslDatePicker.f8140Q;
        if (str != null) {
            this.f8447k = str;
        } else {
            this.f8447k = com.bumptech.glide.d.p();
        }
        Paint paint = new Paint();
        this.f8443f = paint;
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setTextSize(dimensionPixelSize);
        if (Build.VERSION.SDK_INT >= 33) {
            paint.setTypeface(Typeface.create(Typeface.create("sec", 0), 400, false));
        } else {
            paint.setTypeface(Typeface.create("sec-roboto-light", 0));
        }
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(false);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int[] iArr;
        int i5;
        int i7;
        super.onDraw(canvas);
        SeslDatePicker seslDatePicker = this.f8448l;
        int i9 = seslDatePicker.f8184z;
        if (i9 == 0) {
            return;
        }
        int i10 = (seslDatePicker.f8133J * 2) / 3;
        int i11 = seslDatePicker.f8134K / (i9 * 2);
        int i12 = 0;
        int i13 = 0;
        while (true) {
            int i14 = seslDatePicker.f8184z;
            iArr = this.f8446j;
            if (i13 >= i14) {
                break;
            }
            char cCharAt = this.f8447k.charAt(i13);
            int i15 = (i13 + 2) % seslDatePicker.f8184z;
            if (cCharAt == 'B') {
                iArr[i15] = this.f8445i;
            } else if (cCharAt != 'R') {
                iArr[i15] = this.f8444g;
            } else {
                iArr[i15] = this.h;
            }
            i13++;
        }
        while (true) {
            int i16 = seslDatePicker.f8184z;
            if (i12 >= i16) {
                return;
            }
            int i17 = (seslDatePicker.f8125A + i12) % i16;
            Calendar calendar = this.f8442e;
            calendar.set(7, i17);
            String upperCase = seslDatePicker.f8153f0.format(calendar.getTime()).toUpperCase();
            if (seslDatePicker.f8162l) {
                i5 = ((((seslDatePicker.f8184z - 1) - i12) * 2) + 1) * i11;
                i7 = seslDatePicker.f8128D;
            } else {
                i5 = ((i12 * 2) + 1) * i11;
                i7 = seslDatePicker.f8128D;
            }
            int i18 = i5 + i7;
            Paint paint = this.f8443f;
            paint.setColor(iArr[i17]);
            canvas.drawText(upperCase, i18, i10, paint);
            i12++;
        }
    }
}
