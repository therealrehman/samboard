package androidx.recyclerview.widget;

import android.util.IntProperty;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class Y0 extends IntProperty {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9035a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Y0(String str, int i5) {
        super(str);
        this.f9035a = i5;
    }

    @Override // android.util.Property
    public final Integer get(Object obj) {
        switch (this.f9035a) {
            case 0:
                return Integer.valueOf(((View) obj).getLeft());
            case 1:
                return Integer.valueOf(((View) obj).getTop());
            case 2:
                return Integer.valueOf(((View) obj).getRight());
            default:
                return Integer.valueOf(((View) obj).getBottom());
        }
    }

    @Override // android.util.IntProperty
    public final void setValue(Object obj, int i5) {
        switch (this.f9035a) {
            case 0:
                ((View) obj).setLeft(i5);
                break;
            case 1:
                ((View) obj).setTop(i5);
                break;
            case 2:
                ((View) obj).setRight(i5);
                break;
            default:
                ((View) obj).setBottom(i5);
                break;
        }
    }
}
