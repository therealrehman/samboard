package androidx.picker.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import com.samsung.android.keyscafe.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: androidx.picker.widget.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0293m extends androidx.customview.widget.b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ int f8426f = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String[][] f8427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f8428b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8429c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8430d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ SeslColorSwatchView f8431e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0293m(SeslColorSwatchView seslColorSwatchView, View view) {
        super(view);
        this.f8431e = seslColorSwatchView;
        this.f8427a = new String[][]{new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_white), seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_gray), seslColorSwatchView.h.getString(R.string.sesl_color_picker_gray), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_gray), seslColorSwatchView.h.getString(R.string.sesl_color_picker_black)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_red), seslColorSwatchView.h.getString(R.string.sesl_color_picker_red), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_red)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_orange), seslColorSwatchView.h.getString(R.string.sesl_color_picker_orange), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_orange)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_yellow), seslColorSwatchView.h.getString(R.string.sesl_color_picker_yellow), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_yellow)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_green), seslColorSwatchView.h.getString(R.string.sesl_color_picker_green), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_green)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_spring_green), seslColorSwatchView.h.getString(R.string.sesl_color_picker_spring_green), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_spring_green)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_cyan), seslColorSwatchView.h.getString(R.string.sesl_color_picker_cyan), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_cyan)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_azure), seslColorSwatchView.h.getString(R.string.sesl_color_picker_azure), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_azure)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_blue), seslColorSwatchView.h.getString(R.string.sesl_color_picker_blue), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_blue)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_violet), seslColorSwatchView.h.getString(R.string.sesl_color_picker_violet), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_violet)}, new String[]{seslColorSwatchView.h.getString(R.string.sesl_color_picker_light_magenta), seslColorSwatchView.h.getString(R.string.sesl_color_picker_magenta), seslColorSwatchView.h.getString(R.string.sesl_color_picker_dark_magenta)}};
        this.f8428b = new Rect();
    }

    public final StringBuilder d(int i5) {
        int i7 = i5 % 11;
        this.f8429c = i7;
        int i9 = i5 / 11;
        this.f8430d = i9;
        SeslColorSwatchView seslColorSwatchView = this.f8431e;
        if (seslColorSwatchView.f8123r[i7][i9] == null) {
            StringBuilder sb = new StringBuilder();
            int i10 = this.f8429c;
            String[][] strArr = this.f8427a;
            if (i10 == 0) {
                int i11 = this.f8430d;
                if (i11 == 0) {
                    sb.append(strArr[i10][0]);
                } else if (i11 < 3) {
                    sb.append(strArr[i10][1]);
                } else if (i11 < 6) {
                    sb.append(strArr[i10][2]);
                } else if (i11 < 9) {
                    sb.append(strArr[i10][3]);
                } else {
                    sb.append(strArr[i10][4]);
                }
            } else {
                int i12 = this.f8430d;
                if (i12 < 3) {
                    sb.append(strArr[i10][0]);
                } else if (i12 < 6) {
                    sb.append(strArr[i10][1]);
                } else {
                    sb.append(strArr[i10][2]);
                }
            }
            sb.append(", ");
            sb.append(seslColorSwatchView.f8122q[this.f8429c][this.f8430d]);
            seslColorSwatchView.f8123r[this.f8429c][this.f8430d] = sb;
        }
        return seslColorSwatchView.f8123r[this.f8429c][this.f8430d];
    }

    @Override // androidx.customview.widget.b
    public final int getVirtualViewAt(float f2, float f7) {
        SeslColorSwatchView seslColorSwatchView = this.f8431e;
        float f9 = seslColorSwatchView.f8116j;
        float f10 = 11.0f * f9;
        float f11 = seslColorSwatchView.f8115i;
        float f12 = 10.0f * f11;
        if (f2 >= f10) {
            f2 = f10 - 1.0f;
        } else if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f7 >= f12) {
            f7 = f12 - 1.0f;
        } else if (f7 < 0.0f) {
            f7 = 0.0f;
        }
        int i5 = (int) (f2 / f9);
        this.f8429c = i5;
        int i7 = (int) (f7 / f11);
        this.f8430d = i7;
        return (i7 * 11) + i5;
    }

    @Override // androidx.customview.widget.b
    public final void getVisibleVirtualViews(List list) {
        for (int i5 = 0; i5 < 110; i5++) {
            ((ArrayList) list).add(Integer.valueOf(i5));
        }
    }

    @Override // androidx.customview.widget.b
    public final boolean onPerformActionForVirtualView(int i5, int i7, Bundle bundle) {
        if (i7 != 16) {
            return false;
        }
        int i9 = i5 % 11;
        this.f8429c = i9;
        int i10 = i5 / 11;
        this.f8430d = i10;
        SeslColorSwatchView seslColorSwatchView = this.f8431e;
        int i11 = seslColorSwatchView.f8121p[i9][i10];
        C0287g c0287g = seslColorSwatchView.f8112e;
        if (c0287g != null) {
            SeslColorPicker seslColorPicker = (SeslColorPicker) c0287g.f8375a;
            seslColorPicker.h = true;
            seslColorPicker.f8094g.O(i11);
            seslColorPicker.d();
        }
        seslColorSwatchView.o.sendEventForVirtualView(seslColorSwatchView.f8118l, 1);
        return false;
    }

    @Override // androidx.customview.widget.b
    public final void onPopulateEventForVirtualView(int i5, AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setContentDescription(d(i5));
    }

    @Override // androidx.customview.widget.b
    public final void onPopulateNodeForVirtualView(int i5, L.l lVar) {
        int i7 = i5 % 11;
        this.f8429c = i7;
        int i9 = i5 / 11;
        this.f8430d = i9;
        SeslColorSwatchView seslColorSwatchView = this.f8431e;
        float f2 = seslColorSwatchView.f8116j;
        float f7 = seslColorSwatchView.f8115i;
        Rect rect = this.f8428b;
        rect.set((int) ((i7 * f2) + 0.5f), (int) ((i9 * f7) + 0.5f), (int) (((i7 + 1) * f2) + 0.5f), (int) (((i9 + 1) * f7) + 0.5f));
        lVar.m(d(i5));
        lVar.h(rect);
        lVar.a(16);
        lVar.j(Button.class.getName());
        int i10 = seslColorSwatchView.f8118l;
        if (i10 == -1 || i5 != i10) {
            return;
        }
        lVar.a(4);
        lVar.k(true);
        lVar.i(true);
        lVar.f1793a.setChecked(true);
    }
}
