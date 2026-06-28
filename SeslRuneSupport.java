package com.google.android.material.rune;

import com.google.android.material.rune.SeslRune;
import com.samsung.android.keyscafe.honeytea.model.HoneyTeaDB;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0081\u0002\u0018\u00002\u00020\u0001B\n\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/google/android/material/rune/SeslRuneSupport;", "", HoneyTeaDB.DB_COLUMN_NAME, "", "()Z", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public @interface SeslRuneSupport {
    @SeslRune.RuneType
    boolean value() default false;
}
