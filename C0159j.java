package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.appcompat.view.menu.AbstractC0131d;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;

/* JADX INFO: renamed from: androidx.appcompat.widget.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0159j extends AppCompatImageView {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Configuration f6724e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C0171n f6725f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0159j(C0171n c0171n, Context context) {
        super(context, null, R.attr.actionOverflowButtonStyle);
        this.f6725f = c0171n;
        setClickable(true);
        setFocusable(true);
        setLongClickable(true);
        String string = getResources().getString(R.string.sesl_action_menu_overflow_description);
        c0171n.getClass();
        d2.a(this, string);
        this.f6724e = ((AbstractC0131d) c0171n).mContext.getResources().getConfiguration();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Configuration configuration2 = this.f6724e;
        int iDiff = configuration2 != null ? configuration2.diff(configuration) : 4096;
        this.f6724e = configuration;
        Context context = getContext();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, AbstractC0478a.f10554E, R.attr.actionOverflowButtonStyle, 0);
        setMinimumHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0));
        typedArrayObtainStyledAttributes.recycle();
        context.getResources().getString(R.string.sesl_action_menu_overflow_description);
        this.f6725f.getClass();
        if ((iDiff & 4096) != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(null, AbstractC0478a.f10561f, R.attr.actionOverflowButtonStyle, 0);
            Drawable drawableB = B.a.b(context, typedArrayObtainStyledAttributes2.getResourceId(0, -1));
            if (drawableB != null) {
                setImageDrawable(drawableB);
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i5, int i7) {
        super.onMeasure(i5, i7);
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        if (this.f6725f.k()) {
            isHovered();
        }
        return true;
    }

    @Override // android.view.View
    public final boolean performLongClick() {
        return super.performLongClick();
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i5, int i7, int i9, int i10) {
        boolean frame = super.setFrame(i5, i7, i9, i10);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (drawable != null && background != null) {
            int width = getWidth();
            int height = getHeight();
            int paddingLeft = (getPaddingLeft() - getPaddingRight()) / 2;
            E.a.f(background, paddingLeft, 0, width + paddingLeft, height);
        }
        return frame;
    }
}
