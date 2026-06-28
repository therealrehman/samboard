package com.google.android.material.bottomsheet;

import android.view.View;
import android.view.WindowInsetsAnimation;
import androidx.core.view.h0;
import androidx.core.view.i0;
import androidx.core.view.k0;
import androidx.core.view.w0;
import com.google.android.material.animation.AnimationUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class InsetsAnimationCallback extends i0 {
    private int startTranslationY;
    private int startY;
    private final int[] tmpLocation = new int[2];
    private final View view;

    public InsetsAnimationCallback(View view) {
        this.view = view;
    }

    @Override // androidx.core.view.i0
    public void onEnd(k0 k0Var) {
        if ((((WindowInsetsAnimation) k0Var.f7238a.f3147f).getTypeMask() & 8) != 0) {
            this.view.setTranslationY(0.0f);
        }
    }

    @Override // androidx.core.view.i0
    public void onPrepare(k0 k0Var) {
        if ((((WindowInsetsAnimation) k0Var.f7238a.f3147f).getTypeMask() & 8) != 0) {
            this.view.getLocationOnScreen(this.tmpLocation);
            this.startY = this.tmpLocation[1];
        }
    }

    @Override // androidx.core.view.i0
    public w0 onProgress(w0 w0Var, List<k0> list) {
        Iterator<k0> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if ((((WindowInsetsAnimation) it.next().f7238a.f3147f).getTypeMask() & 8) != 0) {
                this.view.setTranslationY(AnimationUtils.lerp(this.startTranslationY, 0, ((WindowInsetsAnimation) r0.f7238a.f3147f).getInterpolatedFraction()));
                break;
            }
        }
        return w0Var;
    }

    @Override // androidx.core.view.i0
    public h0 onStart(k0 k0Var, h0 h0Var) {
        if ((((WindowInsetsAnimation) k0Var.f7238a.f3147f).getTypeMask() & 8) != 0) {
            this.view.getLocationOnScreen(this.tmpLocation);
            int i5 = this.startY - this.tmpLocation[1];
            this.startTranslationY = i5;
            this.view.setTranslationY(i5);
        }
        return h0Var;
    }
}
