package com.google.android.material.textfield;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f10132e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f10133f;

    public /* synthetic */ d(int i5, Object obj) {
        this.f10132e = i5;
        this.f10133f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i5 = this.f10132e;
        Object obj = this.f10133f;
        switch (i5) {
            case 0:
                ((ClearTextEndIconDelegate) obj).lambda$tearDown$2();
                break;
            case 1:
                ((DropdownMenuEndIconDelegate) obj).lambda$afterEditTextChanged$3();
                break;
            default:
                ((TextInputLayout) obj).lambda$onGlobalLayout$1();
                break;
        }
    }
}
