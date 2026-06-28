package androidx.appcompat.widget;

import android.content.Context;
import android.view.View;
import androidx.appcompat.view.menu.AbstractC0131d;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.appcompat.widget.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0150g extends androidx.appcompat.view.menu.u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6709a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0171n f6710b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0150g(C0171n c0171n, Context context, androidx.appcompat.view.menu.j jVar, C0162k c0162k) {
        super(R.attr.actionOverflowMenuStyle, 0, context, c0162k, jVar, true);
        this.f6710b = c0171n;
        setGravity(8388613);
        setPresenterCallback(c0171n.f6774s);
    }

    @Override // androidx.appcompat.view.menu.u
    public final void onDismiss() {
        switch (this.f6709a) {
            case 0:
                C0171n c0171n = this.f6710b;
                c0171n.f6771p = null;
                c0171n.f6775t = 0;
                super.onDismiss();
                break;
            default:
                C0171n c0171n2 = this.f6710b;
                if (((AbstractC0131d) c0171n2).mMenu != null) {
                    ((AbstractC0131d) c0171n2).mMenu.close();
                }
                c0171n2.o = null;
                super.onDismiss();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0150g(C0171n c0171n, Context context, androidx.appcompat.view.menu.D d8, View view) {
        super(R.attr.actionOverflowMenuStyle, 0, context, view, d8, false);
        this.f6710b = c0171n;
        if (!((androidx.appcompat.view.menu.l) d8.getItem()).g()) {
            View view2 = c0171n.f6762e;
            setAnchorView(view2 == null ? (View) ((AbstractC0131d) c0171n).mMenuView : view2);
        }
        setPresenterCallback(c0171n.f6774s);
    }
}
