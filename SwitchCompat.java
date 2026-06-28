package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.PathInterpolator;
import android.widget.CompoundButton;
import e.AbstractC0478a;
import f6.AbstractC0527a;
import j.C0612a;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {
    private static final String ACCESSIBILITY_EVENT_CLASS_NAME = "android.widget.Switch";
    private static final int CHANGE_FLING_VELOCITY = 2000;
    private static final int MIN_FLING_VELOCITY = 500;
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int THUMB_ANIMATION_DURATION = 150;
    private static final int THUMB_ANIMATION_DURATION_ABOVE_M = 300;
    private static final float THUMB_TRACK_WIDTH_RATIO = 0.5714286f;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE = 0;
    private CharSequence mAccessibilityTextOff;
    private CharSequence mAccessibilityTextOn;
    private D mAppCompatEmojiTextHelper;
    private N1 mEmojiCompatInitCallback;
    private boolean mEnforceSwitchWidth;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private PathInterpolator mInterpolator;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    O1 mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private final C0148f0 mTextHelper;
    private CharSequence mTextOff;
    private CharSequence mTextOffTransformed;
    private CharSequence mTextOn;
    private CharSequence mTextOnTransformed;
    private final TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private PorterDuff.Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private int mTrackMargin;
    private Drawable mTrackOffDrawable;
    private Drawable mTrackOnDrawable;
    private ColorStateList mTrackTintList;
    private PorterDuff.Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;
    private static final boolean SUPPORT_TOUCH_FEEDBACK = true;
    private static final Property<SwitchCompat, Float> THUMB_POS = new L1(Float.class, "thumbPos");
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.samsung.android.keyscafe.R.attr.switchStyle);
    }

    private D getEmojiTextViewHelper() {
        if (this.mAppCompatEmojiTextHelper == null) {
            this.mAppCompatEmojiTextHelper = new D(this);
        }
        return this.mAppCompatEmojiTextHelper;
    }

    private boolean getTargetCheckedState() {
        return this.mThumbPosition > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((h2.a(this) ? 1.0f - this.mThumbPosition : this.mThumbPosition) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.mTrackDrawable;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.mTempRect;
        drawable.getPadding(rect);
        Drawable drawable2 = this.mThumbDrawable;
        Rect rectA = drawable2 != null ? AbstractC0183r0.a(drawable2) : AbstractC0183r0.f6838a;
        return (((((this.mSwitchWidth + this.mTrackMargin) - this.mThumbWidth) - rect.left) - rect.right) - rectA.left) - rectA.right;
    }

    private void setTextOffInternal(CharSequence charSequence) {
        this.mTextOff = charSequence;
        D emojiTextViewHelper = getEmojiTextViewHelper();
        TransformationMethod transformationMethodK0 = ((AbstractC0527a) emojiTextViewHelper.f6429b.f286f).k0(this.mSwitchTransformationMethod);
        if (transformationMethodK0 != null) {
            charSequence = transformationMethodK0.getTransformation(charSequence, this);
        }
        this.mTextOffTransformed = charSequence;
        this.mOffLayout = null;
        if (this.mShowText) {
            e();
        }
    }

    private void setTextOnInternal(CharSequence charSequence) {
        this.mTextOn = charSequence;
        D emojiTextViewHelper = getEmojiTextViewHelper();
        TransformationMethod transformationMethodK0 = ((AbstractC0527a) emojiTextViewHelper.f6429b.f286f).k0(this.mSwitchTransformationMethod);
        if (transformationMethodK0 != null) {
            charSequence = transformationMethodK0.getTransformation(charSequence, this);
        }
        this.mTextOnTransformed = charSequence;
        this.mOnLayout = null;
        if (this.mShowText) {
            e();
        }
    }

    public final void a() {
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            if (this.mHasThumbTint || this.mHasThumbTintMode) {
                Drawable drawableMutate = drawable.mutate();
                this.mThumbDrawable = drawableMutate;
                if (this.mHasThumbTint) {
                    E.a.h(drawableMutate, this.mThumbTintList);
                }
                if (this.mHasThumbTintMode) {
                    E.a.i(this.mThumbDrawable, this.mThumbTintMode);
                }
                if (this.mThumbDrawable.isStateful()) {
                    this.mThumbDrawable.setState(getDrawableState());
                }
            }
        }
    }

    public final void b() {
        Drawable drawable = this.mTrackDrawable;
        if (drawable != null) {
            if (this.mHasTrackTint || this.mHasTrackTintMode) {
                Drawable drawableMutate = drawable.mutate();
                this.mTrackDrawable = drawableMutate;
                if (this.mHasTrackTint) {
                    E.a.h(drawableMutate, this.mTrackTintList);
                }
                if (this.mHasTrackTintMode) {
                    E.a.i(this.mTrackDrawable, this.mTrackTintMode);
                }
                if (this.mTrackDrawable.isStateful()) {
                    this.mTrackDrawable.setState(getDrawableState());
                }
            }
        }
    }

    public final void c() {
        Object string = this.mAccessibilityTextOff;
        if (string == null) {
            string = getResources().getString(com.samsung.android.keyscafe.R.string.abc_capital_off);
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        new androidx.core.view.I(com.samsung.android.keyscafe.R.id.tag_state_description, CharSequence.class, 64, 30, 1).i(this, string);
    }

    public boolean canHapticFeedback(boolean z9) {
        return SUPPORT_TOUCH_FEEDBACK && z9 != isChecked() && hasWindowFocus() && com.bumptech.glide.d.t(null, this) && !isTemporarilyDetached();
    }

    public final void d() {
        Object string = this.mAccessibilityTextOn;
        if (string == null) {
            string = getResources().getString(com.samsung.android.keyscafe.R.string.abc_capital_on);
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        new androidx.core.view.I(com.samsung.android.keyscafe.R.id.tag_state_description, CharSequence.class, 64, 30, 1).i(this, string);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i5;
        int i7;
        Rect rect = this.mTempRect;
        int i9 = this.mSwitchLeft;
        int i10 = this.mSwitchTop;
        int i11 = this.mSwitchRight;
        int i12 = this.mSwitchBottom;
        int thumbOffset = getThumbOffset() + i9;
        Drawable drawable = this.mThumbDrawable;
        Rect rectA = drawable != null ? AbstractC0183r0.a(drawable) : AbstractC0183r0.f6838a;
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i13 = rect.left;
            thumbOffset += i13;
            int i14 = this.mTrackMargin;
            int i15 = (i14 / 2) + i9;
            int i16 = i11 - (i14 / 2);
            if (rectA != null) {
                int i17 = rectA.left;
                if (i17 > i13) {
                    i15 += i17 - i13;
                }
                int i18 = rectA.top;
                int i19 = rect.top;
                i5 = i18 > i19 ? (i18 - i19) + i10 : i10;
                int i20 = rectA.right;
                int i21 = rect.right;
                if (i20 > i21) {
                    i16 -= i20 - i21;
                }
                int i22 = rectA.bottom;
                int i23 = rect.bottom;
                if (i22 > i23) {
                    i7 = i12 - (i22 - i23);
                }
                this.mTrackDrawable.setBounds(i15, i5, i16, i7);
            } else {
                i5 = i10;
            }
            i7 = i12;
            this.mTrackDrawable.setBounds(i15, i5, i16, i7);
        }
        Drawable drawable3 = this.mThumbDrawable;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i24 = thumbOffset - rect.left;
            int i25 = thumbOffset + this.mThumbWidth + rect.right;
            this.mThumbDrawable.setBounds(i24, i10, i25, i12);
            Drawable background = getBackground();
            if (background != null) {
                E.a.f(background, i24, i10, i25, i12);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f2, float f7) {
        super.drawableHotspotChanged(f2, f7);
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            E.a.e(drawable, f2, f7);
        }
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            E.a.e(drawable2, f2, f7);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mThumbDrawable;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public final void e() {
        if (this.mEmojiCompatInitCallback == null && this.mAppCompatEmojiTextHelper.b() && androidx.emoji2.text.i.f7447j != null) {
            androidx.emoji2.text.i iVarA = androidx.emoji2.text.i.a();
            int iB = iVarA.b();
            if (iB == 3 || iB == 0) {
                N1 n12 = new N1(this);
                this.mEmojiCompatInitCallback = n12;
                iVarA.g(n12);
            }
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!h2.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.mSwitchWidth + this.mTrackMargin;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.mSwitchPadding : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (h2.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.mSwitchWidth + this.mTrackMargin;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.mSwitchPadding : compoundPaddingRight;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return super.getCustomSelectionActionModeCallback();
    }

    public boolean getShowText() {
        return this.mShowText;
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }

    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public final float getThumbPosition() {
        return this.mThumbPosition;
    }

    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }

    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }

    public ColorStateList getTrackTintList() {
        return this.mTrackTintList;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.mTrackTintMode;
    }

    public boolean isEmojiCompatEnabled() {
        return getEmojiTextViewHelper().b();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        if (this.mPositionAnimator != null) {
            clearAnimation();
            this.mPositionAnimator = null;
        }
        setThumbPosition(isChecked() ? 1.0f : 0.0f);
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mSwitchWidth = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_switch_width);
        this.mAccessibilityTextOn = getResources().getString(com.samsung.android.keyscafe.R.string.sesl_switch_on);
        this.mAccessibilityTextOff = getResources().getString(com.samsung.android.keyscafe.R.string.sesl_switch_off);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i5) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i5 + 1);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Rect rect = this.mTempRect;
        Drawable drawable = this.mTrackDrawable;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i5 = this.mSwitchTop;
        int i7 = this.mSwitchBottom;
        int i9 = i5 + rect.top;
        int i10 = i7 - rect.bottom;
        Drawable drawable2 = this.mThumbDrawable;
        if (drawable != null) {
            if (!this.mSplitTrack || drawable2 == null) {
                Drawable drawable3 = isChecked() ? this.mTrackOffDrawable : this.mTrackOnDrawable;
                drawable3.setBounds(drawable.getBounds());
                int i11 = (int) (this.mThumbPosition * 255.0f);
                if (i11 > 255) {
                    i11 = 255;
                } else if (i11 < 0) {
                    i11 = 0;
                }
                int i12 = 255 - i11;
                if (isChecked()) {
                    drawable.setAlpha(i11);
                    drawable3.setAlpha(i12);
                } else {
                    drawable.setAlpha(i12);
                    drawable3.setAlpha(i11);
                }
                drawable.draw(canvas);
                drawable3.draw(canvas);
            } else {
                Rect rectA = AbstractC0183r0.a(drawable2);
                drawable2.copyBounds(rect);
                rect.left += rectA.left;
                rect.right -= rectA.right;
                int iSave = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        int iSave2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.mOnLayout : this.mOffLayout;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.mTextColors;
            if (colorStateList != null) {
                this.mTextPaint.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.mTextPaint.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (layout.getWidth() / 2), ((i9 + i10) / 2) - (layout.getHeight() / 2));
            layout.draw(canvas);
        }
        canvas.restoreToCount(iSave2);
    }

    public void onEmojiCompatInitializedForSwitchText() {
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        requestLayout();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_EVENT_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_EVENT_CLASS_NAME);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int iMax;
        int width;
        int paddingLeft;
        int height;
        int paddingTop;
        super.onLayout(z9, i5, i7, i9, i10);
        int iMax2 = 0;
        if (this.mThumbDrawable != null) {
            Rect rect = this.mTempRect;
            Drawable drawable = this.mTrackDrawable;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectA = AbstractC0183r0.a(this.mThumbDrawable);
            iMax = Math.max(0, rectA.left - rect.left);
            iMax2 = Math.max(0, rectA.right - rect.right);
        } else {
            iMax = 0;
        }
        if (h2.a(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = (((this.mSwitchWidth + paddingLeft) + this.mTrackMargin) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = ((width - this.mSwitchWidth) - this.mTrackMargin) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int height2 = ((getHeight() + getPaddingTop()) - getPaddingBottom()) / 2;
            int i11 = this.mSwitchHeight;
            int i12 = height2 - (i11 / 2);
            height = i11 + i12;
            paddingTop = i12;
        } else if (gravity != 80) {
            paddingTop = getPaddingTop();
            height = this.mSwitchHeight + paddingTop;
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = height - this.mSwitchHeight;
        }
        this.mSwitchLeft = paddingLeft;
        this.mSwitchTop = paddingTop;
        this.mSwitchBottom = height;
        this.mSwitchRight = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i5, int i7) {
        int intrinsicWidth;
        int intrinsicHeight;
        int intrinsicHeight2;
        if (this.mShowText) {
            if (this.mOnLayout == null) {
                CharSequence charSequence = this.mTextOnTransformed;
                this.mOnLayout = new StaticLayout(charSequence, this.mTextPaint, charSequence != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence, r4)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            }
            if (this.mOffLayout == null) {
                CharSequence charSequence2 = this.mTextOffTransformed;
                this.mOffLayout = new StaticLayout(charSequence2, this.mTextPaint, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, r4)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            }
        }
        Rect rect = this.mTempRect;
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.mThumbDrawable.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.mThumbDrawable.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.mThumbWidth = Math.max(this.mShowText ? (this.mThumbTextPadding * 2) + Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth()) : 0, intrinsicWidth);
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.mTrackDrawable.getIntrinsicHeight();
        } else {
            rect.setEmpty();
            intrinsicHeight2 = 0;
        }
        int i9 = rect.left;
        int i10 = rect.right;
        Drawable drawable3 = this.mThumbDrawable;
        if (drawable3 != null) {
            Rect rectA = AbstractC0183r0.a(drawable3);
            Math.max(i9, rectA.left);
            Math.max(i10, rectA.right);
        }
        int iMax = Math.max(intrinsicHeight2, intrinsicHeight);
        this.mSwitchHeight = iMax;
        this.mTrackMargin = this.mThumbWidth / this.mSwitchWidth > THUMB_TRACK_WIDTH_RATIO ? (int) Math.ceil(r2 - (r3 * THUMB_TRACK_WIDTH_RATIO)) : 0;
        super.onMeasure(i5, i7);
        if (getMeasuredHeight() < iMax) {
            setMeasuredDimension(getMeasuredWidthAndState(), iMax);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.mAccessibilityTextOn : this.mAccessibilityTextOff;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00dc  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void seslSetThumbStrokeColor(int i5) {
        GradientDrawable gradientDrawable;
        Drawable.ConstantState constantState = this.mThumbDrawable.getConstantState();
        if (constantState == null) {
            return;
        }
        Drawable drawable = ((DrawableContainer.DrawableContainerState) constantState).getChildren()[2];
        if (!(drawable instanceof LayerDrawable) || (gradientDrawable = (GradientDrawable) ((LayerDrawable) drawable).findDrawableByLayerId(com.samsung.android.keyscafe.R.id.sesl_switch_thumb_on)) == null) {
            return;
        }
        gradientDrawable.setStroke(4, i5);
    }

    public void seslSetTrackStrokeColor(int i5) {
        GradientDrawable gradientDrawable;
        Drawable.ConstantState constantState = this.mTrackDrawable.getConstantState();
        if (constantState == null) {
            return;
        }
        Drawable drawable = ((DrawableContainer.DrawableContainerState) constantState).getChildren()[2];
        if (!(drawable instanceof LayerDrawable) || (gradientDrawable = (GradientDrawable) ((LayerDrawable) drawable).findDrawableByLayerId(com.samsung.android.keyscafe.R.id.sesl_switch_track_on)) == null) {
            return;
        }
        gradientDrawable.setStroke(4, i5);
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z9) {
        super.setAllCaps(z9);
        getEmojiTextViewHelper().d(z9);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z9) {
        if (canHapticFeedback(z9)) {
            performHapticFeedback(s6.c.X(27));
        }
        super.setChecked(z9);
        boolean zIsChecked = isChecked();
        if (zIsChecked) {
            d();
        } else {
            c();
        }
        if (getWindowToken() != null) {
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            if (isLaidOut()) {
                O1 o12 = this.mPositionAnimator;
                if (o12 != null && o12 != null) {
                    clearAnimation();
                    this.mPositionAnimator = null;
                }
                O1 o13 = new O1(this, this.mThumbPosition, zIsChecked ? 1.0f : 0.0f);
                this.mPositionAnimator = o13;
                o13.setDuration(150L);
                this.mPositionAnimator.setDuration(300L);
                this.mPositionAnimator.setInterpolator(this.mInterpolator);
                this.mPositionAnimator.setAnimationListener(new M1(this, zIsChecked));
                startAnimation(this.mPositionAnimator);
                return;
            }
        }
        if (this.mPositionAnimator != null) {
            clearAnimation();
            this.mPositionAnimator = null;
        }
        setThumbPosition(zIsChecked ? 1.0f : 0.0f);
    }

    public void setCheckedWithoutAnimation(boolean z9) {
        super.setChecked(z9);
        boolean zIsChecked = isChecked();
        if (zIsChecked) {
            d();
        } else {
            c();
        }
        if (this.mPositionAnimator != null) {
            clearAnimation();
            this.mPositionAnimator = null;
        }
        setThumbPosition(zIsChecked ? 1.0f : 0.0f);
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    public void setEmojiCompatEnabled(boolean z9) {
        getEmojiTextViewHelper().e(z9);
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        requestLayout();
    }

    public final void setEnforceSwitchWidth(boolean z9) {
        this.mEnforceSwitchWidth = z9;
        invalidate();
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setShowText(boolean z9) {
        if (this.mShowText != z9) {
            this.mShowText = z9;
            requestLayout();
            if (z9) {
                e();
            }
        }
    }

    public void setSplitTrack(boolean z9) {
        this.mSplitTrack = z9;
        invalidate();
    }

    public void setSwitchMinWidth(int i5) {
        this.mSwitchMinWidth = i5;
        requestLayout();
    }

    public void setSwitchPadding(int i5) {
        this.mSwitchPadding = i5;
        requestLayout();
    }

    public void setSwitchTextAppearance(Context context, int i5) {
        ColorStateList colorStateList;
        int resourceId;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i5, AbstractC0478a.f10552C);
        if (!typedArrayObtainStyledAttributes.hasValue(3) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(3, 0)) == 0 || (colorStateList = p0.a.f(context, resourceId)) == null) {
            colorStateList = typedArrayObtainStyledAttributes.getColorStateList(3);
        }
        if (colorStateList != null) {
            this.mTextColors = colorStateList;
        } else {
            this.mTextColors = getTextColors();
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        if (dimensionPixelSize != 0) {
            float f2 = dimensionPixelSize;
            if (f2 != this.mTextPaint.getTextSize()) {
                this.mTextPaint.setTextSize(f2);
                requestLayout();
            }
        }
        int i7 = typedArrayObtainStyledAttributes.getInt(1, -1);
        setSwitchTypeface(i7 != 1 ? i7 != 2 ? i7 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, typedArrayObtainStyledAttributes.getInt(2, -1));
        if (typedArrayObtainStyledAttributes.getBoolean(14, false)) {
            Context context2 = getContext();
            C0612a c0612a = new C0612a();
            c0612a.f11579a = context2.getResources().getConfiguration().locale;
            this.mSwitchTransformationMethod = c0612a;
        } else {
            this.mSwitchTransformationMethod = null;
        }
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void setSwitchTypeface(Typeface typeface, int i5) {
        if (i5 <= 0) {
            this.mTextPaint.setFakeBoldText(false);
            this.mTextPaint.setTextSkewX(0.0f);
            setSwitchTypeface(typeface);
        } else {
            Typeface typefaceDefaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i5) : Typeface.create(typeface, i5);
            setSwitchTypeface(typefaceDefaultFromStyle);
            int i7 = (~(typefaceDefaultFromStyle != null ? typefaceDefaultFromStyle.getStyle() : 0)) & i5;
            this.mTextPaint.setFakeBoldText((i7 & 1) != 0);
            this.mTextPaint.setTextSkewX((i7 & 2) != 0 ? -0.25f : 0.0f);
        }
    }

    public void setTextOff(CharSequence charSequence) {
        setTextOffInternal(charSequence);
        requestLayout();
        if (isChecked()) {
            return;
        }
        c();
    }

    public void setTextOn(CharSequence charSequence) {
        setTextOnInternal(charSequence);
        requestLayout();
        if (isChecked()) {
            d();
        }
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.mThumbDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mThumbDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f2) {
        this.mThumbPosition = f2;
        invalidate();
    }

    public void setThumbResource(int i5) {
        setThumbDrawable(android.support.v4.media.session.f.y(getContext(), i5));
    }

    public void setThumbTextPadding(int i5) {
        this.mThumbTextPadding = i5;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.mThumbTintList = colorStateList;
        this.mHasThumbTint = true;
        a();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.mThumbTintMode = mode;
        this.mHasThumbTintMode = true;
        a();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.mTrackDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mTrackDrawable = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                this.mTrackOnDrawable = constantState.newDrawable();
                this.mTrackOffDrawable = constantState.newDrawable();
            } else {
                this.mTrackOnDrawable = drawable;
                this.mTrackOffDrawable = drawable;
            }
            this.mTrackOnDrawable.setState(new int[]{R.attr.state_enabled, R.attr.state_checked});
            this.mTrackOffDrawable.setState(new int[]{R.attr.state_enabled, -16842912});
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i5) {
        setTrackDrawable(android.support.v4.media.session.f.y(getContext(), i5));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.mTrackTintList = colorStateList;
        this.mHasTrackTint = true;
        b();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.mTrackTintMode = mode;
        this.mHasTrackTintMode = true;
        b();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mThumbDrawable || drawable == this.mTrackDrawable;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mTrackTintList = null;
        this.mTrackTintMode = null;
        this.mHasTrackTint = false;
        this.mHasTrackTintMode = false;
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mEnforceSwitchWidth = true;
        this.mTempRect = new Rect();
        this.mTrackMargin = 0;
        P1.a(this, getContext());
        TextPaint textPaint = new TextPaint(1);
        this.mTextPaint = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        int i7 = Settings.System.getString(context.getContentResolver(), "current_sec_active_themepackage") != null ? com.samsung.android.keyscafe.R.attr.themeSwitchStyle : i5;
        int[] iArr = AbstractC0478a.f10551B;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i7, 0);
        S1 s12 = new S1(context, typedArrayObtainStyledAttributes);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.T.d(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i7, 0);
        Drawable drawableB = s12.b(2);
        this.mThumbDrawable = drawableB;
        if (drawableB != null) {
            drawableB.setCallback(this);
        }
        Drawable drawableB2 = s12.b(12);
        this.mTrackDrawable = drawableB2;
        if (drawableB2 != null) {
            drawableB2.setCallback(this);
            Drawable.ConstantState constantState = this.mTrackDrawable.getConstantState();
            if (constantState != null) {
                this.mTrackOnDrawable = constantState.newDrawable();
                this.mTrackOffDrawable = constantState.newDrawable();
            } else {
                Drawable drawable = this.mTrackDrawable;
                this.mTrackOnDrawable = drawable;
                this.mTrackOffDrawable = drawable;
            }
            this.mTrackOnDrawable.setState(new int[]{R.attr.state_enabled, R.attr.state_checked});
            this.mTrackOffDrawable.setState(new int[]{R.attr.state_enabled, -16842912});
        }
        setTextOnInternal(typedArrayObtainStyledAttributes.getText(0));
        setTextOffInternal(typedArrayObtainStyledAttributes.getText(1));
        this.mShowText = typedArrayObtainStyledAttributes.getBoolean(3, true);
        this.mThumbTextPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, 0);
        this.mSwitchMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
        this.mSwitchPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.mSplitTrack = typedArrayObtainStyledAttributes.getBoolean(4, false);
        ColorStateList colorStateListA = s12.a(10);
        if (colorStateListA != null) {
            this.mThumbTintList = colorStateListA;
            this.mHasThumbTint = true;
        }
        PorterDuff.Mode modeB = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(11, -1), null);
        if (this.mThumbTintMode != modeB) {
            this.mThumbTintMode = modeB;
            this.mHasThumbTintMode = true;
        }
        if (this.mHasThumbTint || this.mHasThumbTintMode) {
            a();
        }
        ColorStateList colorStateListA2 = s12.a(13);
        if (colorStateListA2 != null) {
            this.mTrackTintList = colorStateListA2;
            this.mHasTrackTint = true;
        }
        PorterDuff.Mode modeB2 = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(14, -1), null);
        if (this.mTrackTintMode != modeB2) {
            this.mTrackTintMode = modeB2;
            this.mHasTrackTintMode = true;
        }
        if (this.mHasTrackTint || this.mHasTrackTintMode) {
            b();
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(8, 0);
        if (resourceId != 0) {
            setSwitchTextAppearance(context, resourceId);
        }
        C0148f0 c0148f0 = new C0148f0(this);
        this.mTextHelper = c0148f0;
        c0148f0.f(attributeSet, i5);
        s12.f();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        getEmojiTextViewHelper().c(attributeSet, i5);
        this.mSwitchWidth = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_switch_width);
        this.mAccessibilityTextOn = getResources().getString(com.samsung.android.keyscafe.R.string.sesl_switch_on);
        this.mAccessibilityTextOff = getResources().getString(com.samsung.android.keyscafe.R.string.sesl_switch_off);
        this.mInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.mTextPaint.getTypeface() == null || this.mTextPaint.getTypeface().equals(typeface)) && (this.mTextPaint.getTypeface() != null || typeface == null)) {
            return;
        }
        this.mTextPaint.setTypeface(typeface);
        requestLayout();
        invalidate();
    }
}
