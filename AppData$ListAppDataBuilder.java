package androidx.picker.model;

import android.graphics.drawable.Drawable;
import d.InterfaceC0463a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import z0.C1281b;
import z0.c;
import z0.d;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\rJ\u0017\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u001a\u0010\u0013J\u000f\u0010\u001b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010 R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010 R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010!R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010!R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010!R\u0016\u0010\"\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006$"}, d2 = {"androidx/picker/model/AppData$ListAppDataBuilder", "", "Lz0/c;", "Lz0/b;", "appInfo", "<init>", "(Lz0/b;)V", "appInfoData", "(Lz0/c;)V", "Landroid/graphics/drawable/Drawable;", "icon", "Landroidx/picker/model/AppData$ListAppDataBuilder;", "setIcon", "(Landroid/graphics/drawable/Drawable;)Landroidx/picker/model/AppData$ListAppDataBuilder;", "subIcon", "setSubIcon", "", "label", "setLabel", "(Ljava/lang/String;)Landroidx/picker/model/AppData$ListAppDataBuilder;", "subLabel", "", "isValue", "setSubLabel", "(Ljava/lang/String;Z)Landroidx/picker/model/AppData$ListAppDataBuilder;", "extraLabel", "setExtraLabel", "build", "()Lz0/c;", "Lz0/b;", "getAppInfo", "()Lz0/b;", "Landroid/graphics/drawable/Drawable;", "Ljava/lang/String;", "isValueInSubLabel", "Z", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class AppData$ListAppDataBuilder {
    private final C1281b appInfo;
    private String extraLabel;
    private Drawable icon;
    private boolean isValueInSubLabel;
    private String label;
    private Drawable subIcon;
    private String subLabel;

    public AppData$ListAppDataBuilder(C1281b appInfo) {
        j.f(appInfo, "appInfo");
        this.appInfo = appInfo;
    }

    public static /* synthetic */ AppData$ListAppDataBuilder setSubLabel$default(AppData$ListAppDataBuilder appData$ListAppDataBuilder, String str, boolean z9, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            z9 = false;
        }
        return appData$ListAppDataBuilder.setSubLabel(str, z9);
    }

    public final C1281b getAppInfo() {
        return this.appInfo;
    }

    public final AppData$ListAppDataBuilder setExtraLabel(String extraLabel) {
        this.extraLabel = extraLabel;
        return this;
    }

    public final AppData$ListAppDataBuilder setIcon(Drawable icon) {
        this.icon = icon;
        return this;
    }

    public final AppData$ListAppDataBuilder setLabel(String label) {
        this.label = label;
        return this;
    }

    public final AppData$ListAppDataBuilder setSubIcon(Drawable subIcon) {
        this.subIcon = subIcon;
        return this;
    }

    public final AppData$ListAppDataBuilder setSubLabel(String str) {
        return setSubLabel$default(this, str, false, 2, null);
    }

    public c build() {
        return new d(this.appInfo, 0, this.icon, this.subIcon, this.label, this.subLabel, this.extraLabel, null, false, false, this.isValueInSubLabel, 896);
    }

    public final AppData$ListAppDataBuilder setSubLabel(String subLabel, boolean isValue) {
        this.subLabel = subLabel;
        this.isValueInSubLabel = isValue;
        return this;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AppData$ListAppDataBuilder(c appInfoData) {
        this(appInfoData.o());
        j.f(appInfoData, "appInfoData");
        setIcon(appInfoData.getIcon());
        setSubIcon(appInfoData.e());
        setLabel(appInfoData.h());
        setSubLabel(appInfoData.k(), appInfoData.a());
        setExtraLabel(appInfoData.c());
    }
}
