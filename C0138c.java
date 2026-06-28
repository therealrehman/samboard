package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import androidx.picker.eyeDropper.SeslEyeDropperActivity;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0138c extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6677e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f6678f;

    public /* synthetic */ C0138c(int i5, Object obj) {
        this.f6677e = i5;
        this.f6678f = obj;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.f6677e) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f6678f;
                actionBarOverlayLayout.f6354A = null;
                actionBarOverlayLayout.o = false;
                break;
            case 1:
                ((androidx.core.view.f0) this.f6678f).onAnimationCancel();
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        Object obj = this.f6678f;
        switch (this.f6677e) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) obj;
                actionBarOverlayLayout.f6354A = null;
                actionBarOverlayLayout.o = false;
                break;
            case 1:
                ((androidx.core.view.f0) obj).onAnimationEnd();
                break;
            case 2:
                ((androidx.recyclerview.widget.b1) obj).f9115x = !r3.f9115x;
                break;
            case 3:
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                ((View) obj).setClipBounds(null);
                break;
            case 4:
                ((androidx.transition.U) obj).end();
                animator.removeListener(this);
                break;
            case 5:
                androidx.vectordrawable.graphics.drawable.f fVar = (androidx.vectordrawable.graphics.drawable.f) obj;
                ArrayList arrayList = new ArrayList(fVar.f9529i);
                int size = arrayList.size();
                for (int i5 = 0; i5 < size; i5++) {
                    ((androidx.vectordrawable.graphics.drawable.c) arrayList.get(i5)).onAnimationEnd(fVar);
                }
                break;
            default:
                SeslEyeDropperActivity seslEyeDropperActivity = (SeslEyeDropperActivity) obj;
                seslEyeDropperActivity.f7861g.setClickable(true);
                seslEyeDropperActivity.f7861g.setEnabled(true);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.f6677e) {
            case 1:
                ((androidx.core.view.f0) this.f6678f).b();
                break;
            case 5:
                androidx.vectordrawable.graphics.drawable.f fVar = (androidx.vectordrawable.graphics.drawable.f) this.f6678f;
                ArrayList arrayList = new ArrayList(fVar.f9529i);
                int size = arrayList.size();
                for (int i5 = 0; i5 < size; i5++) {
                    ((androidx.vectordrawable.graphics.drawable.c) arrayList.get(i5)).onAnimationStart(fVar);
                }
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }

    public C0138c(androidx.core.view.f0 f0Var, View view) {
        this.f6677e = 1;
        this.f6678f = f0Var;
    }
}
