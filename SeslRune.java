package com.google.android.material.rune;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/google/android/material/rune/SeslRune;", "", "()V", "WIDGET_BASIC_INTERACTION", "", "WIDGET_BASIC_INTERACTION_SUGGESTION_APP_BAR", "WIDGET_SHOW_BUTTON_SHAPES", "RuneType", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SeslRune {
    public static final SeslRune INSTANCE = new SeslRune();
    public static final boolean WIDGET_BASIC_INTERACTION = true;
    public static final boolean WIDGET_BASIC_INTERACTION_SUGGESTION_APP_BAR = true;
    public static final boolean WIDGET_SHOW_BUTTON_SHAPES = true;

    @BooleanDef({true, true, true})
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0081\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/google/android/material/rune/SeslRune$RuneType;", "", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RuneType {
    }

    private SeslRune() {
    }
}
