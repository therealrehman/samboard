package androidx.appcompat.widget;

import F8.C0040j;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView {
    private boolean isNeedToSkipRefreshDrawable;
    private final C0188t mBackgroundTintHelper;
    private boolean mHasLevel;
    private final F mImageHelper;

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.a();
        }
        F f2 = this.mImageHelper;
        if (f2 != null) {
            f2.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            return c0188t.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            return c0188t.c();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        C0040j c0040j;
        F f2 = this.mImageHelper;
        if (f2 == null || (c0040j = f2.f6433b) == null) {
            return null;
        }
        return (ColorStateList) c0040j.f907c;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        C0040j c0040j;
        F f2 = this.mImageHelper;
        if (f2 == null || (c0040j = f2.f6433b) == null) {
            return null;
        }
        return (PorterDuff.Mode) c0040j.f908d;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return ((this.mImageHelper.f6432a.getBackground() instanceof RippleDrawable) ^ true) && super.hasOverlappingRendering();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getBackground() instanceof SeslRecoilDrawable) {
            this.isNeedToSkipRefreshDrawable = true;
        }
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (!this.isNeedToSkipRefreshDrawable || getStateListAnimator() == null) {
            return;
        }
        getStateListAnimator().jumpToCurrentState();
        this.isNeedToSkipRefreshDrawable = false;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i5) {
        super.setBackgroundResource(i5);
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.f(i5);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        F f2 = this.mImageHelper;
        if (f2 != null) {
            f2.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        F f2 = this.mImageHelper;
        if (f2 != null && drawable != null && !this.mHasLevel) {
            f2.f6434c = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        F f7 = this.mImageHelper;
        if (f7 != null) {
            f7.a();
            if (this.mHasLevel) {
                return;
            }
            F f9 = this.mImageHelper;
            ImageView imageView = f9.f6432a;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(f9.f6434c);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i5) {
        super.setImageLevel(i5);
        this.mHasLevel = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i5) {
        F f2 = this.mImageHelper;
        if (f2 != null) {
            f2.c(i5);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        F f2 = this.mImageHelper;
        if (f2 != null) {
            f2.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.i(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        F f2 = this.mImageHelper;
        if (f2 != null) {
            if (f2.f6433b == null) {
                f2.f6433b = new C0040j();
            }
            C0040j c0040j = f2.f6433b;
            c0040j.f907c = colorStateList;
            c0040j.f906b = true;
            f2.a();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        F f2 = this.mImageHelper;
        if (f2 != null) {
            if (f2.f6433b == null) {
                f2.f6433b = new C0040j();
            }
            C0040j c0040j = f2.f6433b;
            c0040j.f908d = mode;
            c0040j.f905a = true;
            f2.a();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatImageView(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        Q1.a(context);
        this.mHasLevel = false;
        P1.a(this, getContext());
        C0188t c0188t = new C0188t(this);
        this.mBackgroundTintHelper = c0188t;
        c0188t.d(attributeSet, i5);
        F f2 = new F(this);
        this.mImageHelper = f2;
        f2.b(attributeSet, i5);
    }
}
