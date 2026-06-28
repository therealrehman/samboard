package androidx.appcompat.widget;

import android.widget.AbsListView;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: androidx.appcompat.widget.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0195v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Field f6862a;

    static {
        Field declaredField = null;
        try {
            declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
        f6862a = declaredField;
    }
}
