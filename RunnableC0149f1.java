package androidx.appcompat.widget;

import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: renamed from: androidx.appcompat.widget.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0149f1 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ SearchView.SearchAutoComplete f6707e;

    public RunnableC0149f1(SearchView.SearchAutoComplete searchAutoComplete) {
        this.f6707e = searchAutoComplete;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SearchView.SearchAutoComplete searchAutoComplete = this.f6707e;
        if (searchAutoComplete.f6567i || !searchAutoComplete.f6566g) {
            return;
        }
        ((InputMethodManager) searchAutoComplete.getContext().getSystemService("input_method")).showSoftInput(searchAutoComplete, 0);
        searchAutoComplete.f6566g = false;
    }
}
