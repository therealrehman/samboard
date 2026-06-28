package com.google.android.material.textfield;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f10126e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ EndIconDelegate f10127f;

    public /* synthetic */ a(EndIconDelegate endIconDelegate, int i5) {
        this.f10126e = i5;
        this.f10127f = endIconDelegate;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i5 = this.f10126e;
        EndIconDelegate endIconDelegate = this.f10127f;
        switch (i5) {
            case 0:
                ((ClearTextEndIconDelegate) endIconDelegate).lambda$new$0(view);
                break;
            case 1:
                ((DropdownMenuEndIconDelegate) endIconDelegate).lambda$new$0(view);
                break;
            default:
                ((PasswordToggleEndIconDelegate) endIconDelegate).lambda$new$0(view);
                break;
        }
    }
}
