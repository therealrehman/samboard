package androidx.picker.widget;

import android.icu.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: renamed from: androidx.picker.widget.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0287g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f8375a;

    public void a(Locale locale) {
        String language = locale.getLanguage();
        Locale locale2 = Locale.SIMPLIFIED_CHINESE;
        if (language.equals(locale2.getLanguage()) && locale.getCountry().equals(locale2.getCountry())) {
            new SimpleDateFormat("EEEEE, MMM dd", locale);
            return;
        }
        byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        if (directionality == 1 || directionality == 2) {
            new SimpleDateFormat("EEEEE, MMM dd", locale);
        } else {
            new SimpleDateFormat("EEE, MMM dd", locale);
        }
    }

    public C0287g() {
        this.f8375a = new Object[1];
        a(Locale.getDefault());
    }
}
