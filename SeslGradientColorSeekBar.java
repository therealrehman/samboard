package androidx.picker3.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
class SeslGradientColorSeekBar extends SeekBar {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final GradientDrawable f8551e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f8552f;

    public SeslGradientColorSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8552f = new int[]{-16777216, -1};
        context.getResources();
        this.f8551e = (GradientDrawable) getContext().getDrawable(R.drawable.sesl_color_picker_gradient_seekbar_drawable);
    }

    public final void a(int i5) {
        float[] fArr = {0.0f, 0.0f, 1.0f};
        Color.colorToHSV(i5, fArr);
        float f2 = fArr[2];
        this.f8552f[1] = Color.HSVToColor(fArr);
        setProgress(Math.round(f2 * getMax()));
    }
}
