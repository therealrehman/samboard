package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.s;
import androidx.constraintlayout.widget.u;
import java.util.ArrayList;
import java.util.HashMap;
import u.d;
import u.g;
import v.C1070b;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends u {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public g f6925n;

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6937e = new int[32];
        this.f6942k = new HashMap();
        this.f6939g = context;
        g(attributeSet);
    }

    @Override // androidx.constraintlayout.widget.u, androidx.constraintlayout.widget.c
    public final void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        g gVar = new g();
        gVar.f14301s0 = 0;
        gVar.f14302t0 = 0;
        gVar.f14303u0 = 0;
        gVar.f14304v0 = 0;
        gVar.f14305w0 = 0;
        gVar.f14306x0 = 0;
        gVar.f14307y0 = false;
        gVar.f14308z0 = 0;
        gVar.f14286A0 = 0;
        gVar.f14287B0 = new C1070b();
        gVar.f14288C0 = null;
        gVar.D0 = -1;
        gVar.f14289E0 = -1;
        gVar.f14290F0 = -1;
        gVar.f14291G0 = -1;
        gVar.f14292H0 = -1;
        gVar.I0 = -1;
        gVar.J0 = 0.5f;
        gVar.K0 = 0.5f;
        gVar.L0 = 0.5f;
        gVar.M0 = 0.5f;
        gVar.N0 = 0.5f;
        gVar.O0 = 0.5f;
        gVar.P0 = 0;
        gVar.Q0 = 0;
        gVar.R0 = 2;
        gVar.S0 = 2;
        gVar.f14293T0 = 0;
        gVar.f14294U0 = -1;
        gVar.f14295V0 = 0;
        gVar.f14296W0 = new ArrayList();
        gVar.f14297X0 = null;
        gVar.f14298Y0 = null;
        gVar.f14299Z0 = null;
        gVar.f14300b1 = 0;
        this.f6925n = gVar;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, s.f7142b);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i5 = 0; i5 < indexCount; i5++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i5);
                if (index == 0) {
                    this.f6925n.f14295V0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == 1) {
                    g gVar2 = this.f6925n;
                    int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                    gVar2.f14301s0 = dimensionPixelSize;
                    gVar2.f14302t0 = dimensionPixelSize;
                    gVar2.f14303u0 = dimensionPixelSize;
                    gVar2.f14304v0 = dimensionPixelSize;
                } else if (index == 18) {
                    g gVar3 = this.f6925n;
                    int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                    gVar3.f14303u0 = dimensionPixelSize2;
                    gVar3.f14305w0 = dimensionPixelSize2;
                    gVar3.f14306x0 = dimensionPixelSize2;
                } else if (index == 19) {
                    this.f6925n.f14304v0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == 2) {
                    this.f6925n.f14305w0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == 3) {
                    this.f6925n.f14301s0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == 4) {
                    this.f6925n.f14306x0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == 5) {
                    this.f6925n.f14302t0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == 54) {
                    this.f6925n.f14293T0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == 44) {
                    this.f6925n.D0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == 53) {
                    this.f6925n.f14289E0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == 38) {
                    this.f6925n.f14290F0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == 46) {
                    this.f6925n.f14292H0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == 40) {
                    this.f6925n.f14291G0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == 48) {
                    this.f6925n.I0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == 42) {
                    this.f6925n.J0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == 37) {
                    this.f6925n.L0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == 45) {
                    this.f6925n.N0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == 39) {
                    this.f6925n.M0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == 47) {
                    this.f6925n.O0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == 51) {
                    this.f6925n.K0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == 41) {
                    this.f6925n.R0 = typedArrayObtainStyledAttributes.getInt(index, 2);
                } else if (index == 50) {
                    this.f6925n.S0 = typedArrayObtainStyledAttributes.getInt(index, 2);
                } else if (index == 43) {
                    this.f6925n.P0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == 52) {
                    this.f6925n.Q0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == 49) {
                    this.f6925n.f14294U0 = typedArrayObtainStyledAttributes.getInt(index, -1);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.h = this.f6925n;
        i();
    }

    @Override // androidx.constraintlayout.widget.c
    public final void h(d dVar, boolean z9) {
        g gVar = this.f6925n;
        int i5 = gVar.f14303u0;
        if (i5 > 0 || gVar.f14304v0 > 0) {
            if (z9) {
                gVar.f14305w0 = gVar.f14304v0;
                gVar.f14306x0 = i5;
            } else {
                gVar.f14305w0 = i5;
                gVar.f14306x0 = gVar.f14304v0;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.u
    public final void j(g gVar, int i5, int i7) {
        int mode = View.MeasureSpec.getMode(i5);
        int size = View.MeasureSpec.getSize(i5);
        int mode2 = View.MeasureSpec.getMode(i7);
        int size2 = View.MeasureSpec.getSize(i7);
        if (gVar == null) {
            setMeasuredDimension(0, 0);
        } else {
            gVar.V(mode, size, mode2, size2);
            setMeasuredDimension(gVar.f14308z0, gVar.f14286A0);
        }
    }

    @Override // androidx.constraintlayout.widget.c, android.view.View
    public final void onMeasure(int i5, int i7) {
        j(this.f6925n, i5, i7);
    }

    public void setFirstHorizontalBias(float f2) {
        this.f6925n.L0 = f2;
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i5) {
        this.f6925n.f14290F0 = i5;
        requestLayout();
    }

    public void setFirstVerticalBias(float f2) {
        this.f6925n.M0 = f2;
        requestLayout();
    }

    public void setFirstVerticalStyle(int i5) {
        this.f6925n.f14291G0 = i5;
        requestLayout();
    }

    public void setHorizontalAlign(int i5) {
        this.f6925n.R0 = i5;
        requestLayout();
    }

    public void setHorizontalBias(float f2) {
        this.f6925n.J0 = f2;
        requestLayout();
    }

    public void setHorizontalGap(int i5) {
        this.f6925n.P0 = i5;
        requestLayout();
    }

    public void setHorizontalStyle(int i5) {
        this.f6925n.D0 = i5;
        requestLayout();
    }

    public void setLastHorizontalBias(float f2) {
        this.f6925n.N0 = f2;
        requestLayout();
    }

    public void setLastHorizontalStyle(int i5) {
        this.f6925n.f14292H0 = i5;
        requestLayout();
    }

    public void setLastVerticalBias(float f2) {
        this.f6925n.O0 = f2;
        requestLayout();
    }

    public void setLastVerticalStyle(int i5) {
        this.f6925n.I0 = i5;
        requestLayout();
    }

    public void setMaxElementsWrap(int i5) {
        this.f6925n.f14294U0 = i5;
        requestLayout();
    }

    public void setOrientation(int i5) {
        this.f6925n.f14295V0 = i5;
        requestLayout();
    }

    public void setPadding(int i5) {
        g gVar = this.f6925n;
        gVar.f14301s0 = i5;
        gVar.f14302t0 = i5;
        gVar.f14303u0 = i5;
        gVar.f14304v0 = i5;
        requestLayout();
    }

    public void setPaddingBottom(int i5) {
        this.f6925n.f14302t0 = i5;
        requestLayout();
    }

    public void setPaddingLeft(int i5) {
        this.f6925n.f14305w0 = i5;
        requestLayout();
    }

    public void setPaddingRight(int i5) {
        this.f6925n.f14306x0 = i5;
        requestLayout();
    }

    public void setPaddingTop(int i5) {
        this.f6925n.f14301s0 = i5;
        requestLayout();
    }

    public void setVerticalAlign(int i5) {
        this.f6925n.S0 = i5;
        requestLayout();
    }

    public void setVerticalBias(float f2) {
        this.f6925n.K0 = f2;
        requestLayout();
    }

    public void setVerticalGap(int i5) {
        this.f6925n.Q0 = i5;
        requestLayout();
    }

    public void setVerticalStyle(int i5) {
        this.f6925n.f14289E0 = i5;
        requestLayout();
    }

    public void setWrapMode(int i5) {
        this.f6925n.f14293T0 = i5;
        requestLayout();
    }
}
