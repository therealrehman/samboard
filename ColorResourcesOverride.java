package com.google.android.material.color;

import android.content.Context;
import android.os.Build;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface ColorResourcesOverride {
    static ColorResourcesOverride getInstance() {
        int i5 = Build.VERSION.SDK_INT;
        if (i5 > 33 && i5 < 34) {
            return null;
        }
        return ResourcesLoaderColorResourcesOverride.getInstance();
    }

    boolean applyIfPossible(Context context, Map<Integer, Integer> map);

    Context wrapContextIfPossible(Context context, Map<Integer, Integer> map);
}
