package com.google.android.material.appbar.model;

import com.google.android.material.appbar.model.AppBarModel;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B+\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/google/android/material/appbar/model/ButtonModel;", "", "text", "", "clickListener", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "contentDescription", "(Ljava/lang/String;Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;Ljava/lang/String;)V", "getClickListener", "()Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "getContentDescription", "()Ljava/lang/String;", "getText", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class ButtonModel {
    private final AppBarModel.OnClickListener clickListener;
    private final String contentDescription;
    private final String text;

    public ButtonModel() {
        this(null, null, null, 7, null);
    }

    public final AppBarModel.OnClickListener getClickListener() {
        return this.clickListener;
    }

    public final String getContentDescription() {
        return this.contentDescription;
    }

    public final String getText() {
        return this.text;
    }

    public ButtonModel(String str) {
        this(str, null, null, 6, null);
    }

    public ButtonModel(String str, AppBarModel.OnClickListener onClickListener) {
        this(str, onClickListener, null, 4, null);
    }

    public ButtonModel(String str, AppBarModel.OnClickListener onClickListener, String str2) {
        this.text = str;
        this.clickListener = onClickListener;
        this.contentDescription = str2;
    }

    public /* synthetic */ ButtonModel(String str, AppBarModel.OnClickListener onClickListener, String str2, int i5, e eVar) {
        this((i5 & 1) != 0 ? null : str, (i5 & 2) != 0 ? null : onClickListener, (i5 & 4) != 0 ? null : str2);
    }
}
