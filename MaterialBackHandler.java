package com.google.android.material.motion;

import androidx.activity.b;

/* JADX INFO: loaded from: classes.dex */
public interface MaterialBackHandler {
    void cancelBackProgress();

    void handleBackInvoked();

    void startBackProgress(b bVar);

    void updateBackProgress(b bVar);
}
