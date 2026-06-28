package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;
import e.AbstractC0478a;

/* JADX INFO: loaded from: classes.dex */
public class AlertController$RecycleListView extends ListView {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6157e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6158f;

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0478a.f10575v);
        this.f6158f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, -1);
        this.f6157e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, -1);
    }
}
