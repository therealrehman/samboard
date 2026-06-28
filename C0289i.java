package androidx.picker.widget;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.SeekBar;

/* JADX INFO: renamed from: androidx.picker.widget.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0289i implements SeekBar.OnSeekBarChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SeslColorPicker f8389a;

    public C0289i(SeslColorPicker seslColorPicker) {
        this.f8389a = seslColorPicker;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i5, boolean z9) {
        GradientDrawable gradientDrawable;
        SeslColorPicker seslColorPicker = this.f8389a;
        if (z9) {
            seslColorPicker.h = true;
        }
        M3.e eVar = seslColorPicker.f8094g;
        eVar.getClass();
        eVar.f2048f = Integer.valueOf(Color.HSVToColor(i5, (float[]) eVar.f2049g));
        Integer num = (Integer) seslColorPicker.f8094g.f2048f;
        if (num == null || (gradientDrawable = seslColorPicker.f8101p) == null) {
            return;
        }
        gradientDrawable.setColor(num.intValue());
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
    }
}
