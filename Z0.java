package androidx.appcompat.widget;

import android.view.KeyEvent;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class Z0 implements TextView.OnEditorActionListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ SearchView f6662e;

    public Z0(SearchView searchView) {
        this.f6662e = searchView;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i5, KeyEvent keyEvent) {
        this.f6662e.j();
        return true;
    }
}
