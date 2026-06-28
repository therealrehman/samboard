package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;

/* JADX INFO: renamed from: androidx.appcompat.widget.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0165l extends C0154h0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ C0171n f6735e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0165l(C0171n c0171n, Context context) {
        super(context, null, R.attr.actionOverflowButtonStyle);
        this.f6735e = c0171n;
        setClickable(true);
        setFocusable(true);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, AbstractC0478a.f10564j, 0, 0);
        setTextAppearance(typedArrayObtainStyledAttributes.getResourceId(26, 0));
        typedArrayObtainStyledAttributes.recycle();
        setText(getResources().getString(R.string.sesl_more_item_label));
        boolean zO = s6.c.O(context);
        c0171n.getClass();
        if (zO) {
            setBackgroundResource(R.drawable.sesl_action_bar_item_text_background_light);
        } else {
            setBackgroundResource(R.drawable.sesl_action_bar_item_text_background_dark);
        }
        seslSetButtonShapeEnabled(true);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.appcompat.widget.C0154h0, android.widget.TextView, android.view.View
    public final void onMeasure(int i5, int i7) {
        super.onMeasure(i5, i7);
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        this.f6735e.k();
        return true;
    }
}
