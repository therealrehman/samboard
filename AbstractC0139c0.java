package androidx.appcompat.widget;

import android.os.LocaleList;
import android.widget.TextView;

/* JADX INFO: renamed from: androidx.appcompat.widget.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0139c0 {
    public static LocaleList a(String str) {
        return LocaleList.forLanguageTags(str);
    }

    public static void b(TextView textView, LocaleList localeList) {
        textView.setTextLocales(localeList);
    }
}
