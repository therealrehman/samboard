package androidx.picker3.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
class SeslOpacitySeekBar extends SeekBar {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public GradientDrawable f8553e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f8554f;

    public SeslOpacitySeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8554f = new int[]{-1, -16777216};
    }

    public final void a(int i5, int i7) {
        if (this.f8553e != null) {
            int iE = D.d.e(i5, 255);
            int[] iArr = this.f8554f;
            iArr[1] = iE;
            this.f8553e.setColors(iArr);
            setProgressDrawable(this.f8553e);
            float[] fArr = new float[3];
            Color.colorToHSV(iE, fArr);
            iArr[0] = Color.HSVToColor(0, fArr);
            iArr[1] = Color.HSVToColor(255, fArr);
            setProgress(i7);
        }
    }

    public final void b(int i5) {
        float[] fArr = new float[3];
        Color.colorToHSV(i5, fArr);
        int iAlpha = Color.alpha(i5);
        int iHSVToColor = Color.HSVToColor(0, fArr);
        int[] iArr = this.f8554f;
        iArr[0] = iHSVToColor;
        iArr[1] = Color.HSVToColor(255, fArr);
        setProgress(iAlpha);
    }
}
