package com.google.android.material.internal;

import M3.g;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import androidx.core.view.E;
import androidx.core.view.InterfaceC0226s;
import androidx.core.view.K;
import androidx.core.view.M;
import androidx.core.view.U;
import androidx.core.view.W;
import androidx.core.view.w0;
import androidx.core.view.x0;
import com.google.android.material.drawable.DrawableUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import p5.C0828d;

/* JADX INFO: loaded from: classes.dex */
public class ViewUtils {
    public static final int EDGE_TO_EDGE_FLAGS = 768;

    public interface OnApplyWindowInsetsListener {
        w0 onApplyWindowInsets(View view, w0 w0Var, RelativePadding relativePadding);
    }

    private ViewUtils() {
    }

    public static void addOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static Rect calculateOffsetRectFromBounds(View view, View view2) {
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        int i5 = iArr[0];
        int i7 = iArr[1];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int i9 = i5 - iArr2[0];
        int i10 = i7 - iArr2[1];
        return new Rect(i9, i10, view2.getWidth() + i9, view2.getHeight() + i10);
    }

    public static Rect calculateRectFromBounds(View view) {
        return calculateRectFromBounds(view, 0);
    }

    public static void doOnApplyWindowInsets(View view, AttributeSet attributeSet, int i5, int i7) {
        doOnApplyWindowInsets(view, attributeSet, i5, i7, null);
    }

    public static float dpToPx(Context context, int i5) {
        return TypedValue.applyDimension(1, i5, context.getResources().getDisplayMetrics());
    }

    public static Integer getBackgroundColor(View view) {
        ColorStateList colorStateListOrNull = DrawableUtils.getColorStateListOrNull(view.getBackground());
        if (colorStateListOrNull != null) {
            return Integer.valueOf(colorStateListOrNull.getDefaultColor());
        }
        return null;
    }

    public static List<View> getChildren(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                arrayList.add(viewGroup.getChildAt(i5));
            }
        }
        return arrayList;
    }

    public static ViewGroup getContentView(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static ViewOverlayImpl getContentViewOverlay(View view) {
        return getOverlay(getContentView(view));
    }

    private static InputMethodManager getInputMethodManager(View view) {
        return (InputMethodManager) B.b.b(view.getContext(), InputMethodManager.class);
    }

    public static ViewOverlayImpl getOverlay(View view) {
        if (view == null) {
            return null;
        }
        return new ViewOverlayApi18(view);
    }

    public static float getParentAbsoluteElevation(View view) {
        float fI = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            WeakHashMap weakHashMap = W.f7199a;
            fI += M.i((View) parent);
        }
        return fI;
    }

    public static void hideKeyboard(View view) {
        hideKeyboard(view, true);
    }

    public static boolean isLayoutRtl(View view) {
        WeakHashMap weakHashMap = W.f7199a;
        return view.getLayoutDirection() == 1;
    }

    public static PorterDuff.Mode parseTintMode(int i5, PorterDuff.Mode mode) {
        if (i5 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i5 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i5 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i5) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (view != null) {
            removeOnGlobalLayoutListener(view.getViewTreeObserver(), onGlobalLayoutListener);
        }
    }

    public static void requestApplyInsetsWhenAttached(View view) {
        WeakHashMap weakHashMap = W.f7199a;
        if (view.isAttachedToWindow()) {
            K.c(view);
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.android.material.internal.ViewUtils.3
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view2) {
                    view2.removeOnAttachStateChangeListener(this);
                    WeakHashMap weakHashMap2 = W.f7199a;
                    K.c(view2);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view2) {
                }
            });
        }
    }

    public static void requestFocusAndShowKeyboard(View view) {
        requestFocusAndShowKeyboard(view, true);
    }

    public static void setBoundsFromRect(View view, Rect rect) {
        view.setLeft(rect.left);
        view.setTop(rect.top);
        view.setRight(rect.right);
        view.setBottom(rect.bottom);
    }

    public static void showKeyboard(View view) {
        showKeyboard(view, true);
    }

    public static Rect calculateRectFromBounds(View view, int i5) {
        return new Rect(view.getLeft(), view.getTop() + i5, view.getRight(), view.getBottom() + i5);
    }

    public static void doOnApplyWindowInsets(View view, AttributeSet attributeSet, int i5, int i7, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, com.google.android.material.R.styleable.Insets, i5, i7);
        final boolean z9 = typedArrayObtainStyledAttributes.getBoolean(com.google.android.material.R.styleable.Insets_paddingBottomSystemWindowInsets, false);
        final boolean z10 = typedArrayObtainStyledAttributes.getBoolean(com.google.android.material.R.styleable.Insets_paddingLeftSystemWindowInsets, false);
        final boolean z11 = typedArrayObtainStyledAttributes.getBoolean(com.google.android.material.R.styleable.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        doOnApplyWindowInsets(view, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.internal.ViewUtils.1
            @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
            public w0 onApplyWindowInsets(View view2, w0 w0Var, RelativePadding relativePadding) {
                if (z9) {
                    relativePadding.bottom = w0Var.a() + relativePadding.bottom;
                }
                boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(view2);
                if (z10) {
                    if (zIsLayoutRtl) {
                        relativePadding.end = w0Var.b() + relativePadding.end;
                    } else {
                        relativePadding.start = w0Var.b() + relativePadding.start;
                    }
                }
                if (z11) {
                    if (zIsLayoutRtl) {
                        relativePadding.start = w0Var.c() + relativePadding.start;
                    } else {
                        relativePadding.end = w0Var.c() + relativePadding.end;
                    }
                }
                relativePadding.applyToView(view2);
                OnApplyWindowInsetsListener onApplyWindowInsetsListener2 = onApplyWindowInsetsListener;
                return onApplyWindowInsetsListener2 != null ? onApplyWindowInsetsListener2.onApplyWindowInsets(view2, w0Var, relativePadding) : w0Var;
            }
        });
    }

    public static void hideKeyboard(View view, boolean z9) {
        if (z9) {
            WeakHashMap weakHashMap = W.f7199a;
            x0 x0VarC = U.c(view);
            if (x0VarC != null) {
                C0828d c0828d = x0VarC.f7269a;
                ((E) ((g) c0828d.f12719g).f2056f).W0();
                ((WindowInsetsController) c0828d.f12718f).hide(0);
                return;
            }
        }
        InputMethodManager inputMethodManager = getInputMethodManager(view);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void removeOnGlobalLayoutListener(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public static void requestFocusAndShowKeyboard(final View view, final boolean z9) {
        view.requestFocus();
        view.post(new Runnable() { // from class: com.google.android.material.internal.b
            @Override // java.lang.Runnable
            public final void run() {
                ViewUtils.showKeyboard(view, z9);
            }
        });
    }

    public static void showKeyboard(View view, boolean z9) {
        if (z9) {
            WeakHashMap weakHashMap = W.f7199a;
            x0 x0VarC = U.c(view);
            if (x0VarC != null) {
                C0828d c0828d = x0VarC.f7269a;
                ((E) ((g) c0828d.f12719g).f2056f).Y0();
                ((WindowInsetsController) c0828d.f12718f).show(0);
                return;
            }
        }
        getInputMethodManager(view).showSoftInput(view, 1);
    }

    public static class RelativePadding {
        public int bottom;
        public int end;
        public int start;
        public int top;

        public RelativePadding(int i5, int i7, int i9, int i10) {
            this.start = i5;
            this.top = i7;
            this.end = i9;
            this.bottom = i10;
        }

        public void applyToView(View view) {
            int i5 = this.start;
            int i7 = this.top;
            int i9 = this.end;
            int i10 = this.bottom;
            WeakHashMap weakHashMap = W.f7199a;
            view.setPaddingRelative(i5, i7, i9, i10);
        }

        public RelativePadding(RelativePadding relativePadding) {
            this.start = relativePadding.start;
            this.top = relativePadding.top;
            this.end = relativePadding.end;
            this.bottom = relativePadding.bottom;
        }
    }

    public static void doOnApplyWindowInsets(View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        WeakHashMap weakHashMap = W.f7199a;
        final RelativePadding relativePadding = new RelativePadding(view.getPaddingStart(), view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        M.u(view, new InterfaceC0226s() { // from class: com.google.android.material.internal.ViewUtils.2
            @Override // androidx.core.view.InterfaceC0226s
            public w0 onApplyWindowInsets(View view2, w0 w0Var) {
                return onApplyWindowInsetsListener.onApplyWindowInsets(view2, w0Var, new RelativePadding(relativePadding));
            }
        });
        requestApplyInsetsWhenAttached(view);
    }
}
