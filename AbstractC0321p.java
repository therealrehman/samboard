package androidx.preference;

import android.view.Window;
import android.view.WindowInsets;

/* JADX INFO: renamed from: androidx.preference.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0321p {
    public static void a(Window window) {
        window.getDecorView().getWindowInsetsController().show(WindowInsets.Type.ime());
    }
}
