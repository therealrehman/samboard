package com.google.android.material.transition;

import A8.l;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.TypedValue;
import android.view.View;
import androidx.transition.H;
import androidx.transition.I;
import androidx.transition.U;
import androidx.transition.a0;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import d6.AbstractC0476d;
import f6.AbstractC0527a;

/* JADX INFO: loaded from: classes.dex */
class TransitionUtils {
    static final int NO_ATTR_RES_ID = 0;
    static final int NO_DURATION = -1;
    private static final int PATH_TYPE_ARC = 1;
    private static final int PATH_TYPE_LINEAR = 0;
    private static final RectF transformAlphaRectF = new RectF();

    public interface CornerSizeBinaryOperator {
        CornerSize apply(CornerSize cornerSize, CornerSize cornerSize2);
    }

    private TransitionUtils() {
    }

    public static float calculateArea(RectF rectF) {
        return rectF.height() * rectF.width();
    }

    public static ShapeAppearanceModel convertToRelativeCornerSizes(ShapeAppearanceModel shapeAppearanceModel, final RectF rectF) {
        return shapeAppearanceModel.withTransformedCornerSizes(new ShapeAppearanceModel.CornerSizeUnaryOperator() { // from class: com.google.android.material.transition.a
            @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
            public final CornerSize apply(CornerSize cornerSize) {
                return RelativeCornerSize.createFromCornerSize(rectF, cornerSize);
            }
        });
    }

    public static Shader createColorShader(int i5) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, 0.0f, i5, i5, Shader.TileMode.CLAMP);
    }

    public static <T> T defaultIfNull(T t8, T t9) {
        return t8 != null ? t8 : t9;
    }

    public static View findAncestorById(View view, int i5) {
        String resourceName = view.getResources().getResourceName(i5);
        while (view != null) {
            if (view.getId() != i5) {
                Object parent = view.getParent();
                if (!(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            } else {
                return view;
            }
        }
        throw new IllegalArgumentException(AbstractC0476d.k(resourceName, " is not a valid ancestor"));
    }

    public static View findDescendantOrAncestorById(View view, int i5) {
        View viewFindViewById = view.findViewById(i5);
        return viewFindViewById != null ? viewFindViewById : findAncestorById(view, i5);
    }

    public static RectF getLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new RectF(iArr[0], iArr[1], view.getWidth() + r1, view.getHeight() + r0);
    }

    public static RectF getRelativeBounds(View view) {
        return new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    public static Rect getRelativeBoundsRect(View view) {
        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    private static boolean isShapeAppearanceSignificant(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        return (shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getTopRightCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(rectF) == 0.0f) ? false : true;
    }

    public static float lerp(float f2, float f7, float f9) {
        return AbstractC0476d.u(f7, f2, f9, f2);
    }

    public static void maybeAddTransition(a0 a0Var, U u5) {
        if (u5 != null) {
            a0Var.f(u5);
        }
    }

    public static boolean maybeApplyThemeDuration(U u5, Context context, int i5) {
        int iResolveThemeDuration;
        if (i5 == 0 || u5.getDuration() != -1 || (iResolveThemeDuration = MotionUtils.resolveThemeDuration(context, i5, -1)) == -1) {
            return false;
        }
        u5.setDuration(iResolveThemeDuration);
        return true;
    }

    public static boolean maybeApplyThemeInterpolator(U u5, Context context, int i5, TimeInterpolator timeInterpolator) {
        if (i5 == 0 || u5.getInterpolator() != null) {
            return false;
        }
        u5.setInterpolator(MotionUtils.resolveThemeInterpolator(context, i5, timeInterpolator));
        return true;
    }

    public static boolean maybeApplyThemePath(U u5, Context context, int i5) {
        H hResolveThemePath;
        if (i5 == 0 || (hResolveThemePath = resolveThemePath(context, i5)) == null) {
            return false;
        }
        u5.setPathMotion(hResolveThemePath);
        return true;
    }

    public static void maybeRemoveTransition(a0 a0Var, U u5) {
        if (u5 != null) {
            a0Var.h(u5);
        }
    }

    public static H resolveThemePath(Context context, int i5) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i5, typedValue, true)) {
            return null;
        }
        int i7 = typedValue.type;
        if (i7 != 16) {
            if (i7 == 3) {
                return new I(AbstractC0527a.l(String.valueOf(typedValue.string)));
            }
            throw new IllegalArgumentException("Motion path theme attribute must either be an enum value or path data string");
        }
        int i9 = typedValue.data;
        if (i9 == 0) {
            return null;
        }
        if (i9 == 1) {
            return new MaterialArcMotion();
        }
        throw new IllegalArgumentException(l.o(i9, "Invalid motion path type: "));
    }

    private static int saveLayerAlphaCompat(Canvas canvas, Rect rect, int i5) {
        RectF rectF = transformAlphaRectF;
        rectF.set(rect);
        return canvas.saveLayerAlpha(rectF, i5);
    }

    public static void transform(Canvas canvas, Rect rect, float f2, float f7, float f9, int i5, CanvasCompat.CanvasOperation canvasOperation) {
        if (i5 <= 0) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(f2, f7);
        canvas.scale(f9, f9);
        if (i5 < 255) {
            saveLayerAlphaCompat(canvas, rect, i5);
        }
        canvasOperation.run(canvas);
        canvas.restoreToCount(iSave);
    }

    public static ShapeAppearanceModel transformCornerSizes(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, CornerSizeBinaryOperator cornerSizeBinaryOperator) {
        return (isShapeAppearanceSignificant(shapeAppearanceModel, rectF) ? shapeAppearanceModel : shapeAppearanceModel2).toBuilder().setTopLeftCornerSize(cornerSizeBinaryOperator.apply(shapeAppearanceModel.getTopLeftCornerSize(), shapeAppearanceModel2.getTopLeftCornerSize())).setTopRightCornerSize(cornerSizeBinaryOperator.apply(shapeAppearanceModel.getTopRightCornerSize(), shapeAppearanceModel2.getTopRightCornerSize())).setBottomLeftCornerSize(cornerSizeBinaryOperator.apply(shapeAppearanceModel.getBottomLeftCornerSize(), shapeAppearanceModel2.getBottomLeftCornerSize())).setBottomRightCornerSize(cornerSizeBinaryOperator.apply(shapeAppearanceModel.getBottomRightCornerSize(), shapeAppearanceModel2.getBottomRightCornerSize())).build();
    }

    public static float lerp(float f2, float f7, float f9, float f10, float f11) {
        return lerp(f2, f7, f9, f10, f11, false);
    }

    public static float lerp(float f2, float f7, float f9, float f10, float f11, boolean z9) {
        if (!z9 || (f11 >= 0.0f && f11 <= 1.0f)) {
            return f11 < f9 ? f2 : f11 > f10 ? f7 : lerp(f2, f7, (f11 - f9) / (f10 - f9));
        }
        return lerp(f2, f7, f11);
    }

    public static int lerp(int i5, int i7, float f2, float f7, float f9) {
        return f9 < f2 ? i5 : f9 > f7 ? i7 : (int) lerp(i5, i7, (f9 - f2) / (f7 - f2));
    }

    public static ShapeAppearanceModel lerp(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, final RectF rectF, final RectF rectF2, final float f2, final float f7, final float f9) {
        return f9 < f2 ? shapeAppearanceModel : f9 > f7 ? shapeAppearanceModel2 : transformCornerSizes(shapeAppearanceModel, shapeAppearanceModel2, rectF, new CornerSizeBinaryOperator() { // from class: com.google.android.material.transition.TransitionUtils.1
            @Override // com.google.android.material.transition.TransitionUtils.CornerSizeBinaryOperator
            public CornerSize apply(CornerSize cornerSize, CornerSize cornerSize2) {
                return new AbsoluteCornerSize(TransitionUtils.lerp(cornerSize.getCornerSize(rectF), cornerSize2.getCornerSize(rectF2), f2, f7, f9));
            }
        });
    }
}
