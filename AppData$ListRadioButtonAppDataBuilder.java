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
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\rJ\u0017\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0015\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0017\u0010\rJ\u0015\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0018¢\u0006\u0004\b\u001d\u0010\u001bJ\u000f\u0010\u001e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b!\u0010\"R\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010#R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010#R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010$R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010$R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010#R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010%R\u0016\u0010\u001c\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010%¨\u0006&"}, d2 = {"androidx/picker/model/AppData$ListRadioButtonAppDataBuilder", "", "Lz0/c;", "Lz0/b;", "appInfo", "<init>", "(Lz0/b;)V", "appInfoData", "(Lz0/c;)V", "Landroid/graphics/drawable/Drawable;", "icon", "Landroidx/picker/model/AppData$ListRadioButtonAppDataBuilder;", "setIcon", "(Landroid/graphics/drawable/Drawable;)Landroidx/picker/model/AppData$ListRadioButtonAppDataBuilder;", "subIcon", "setSubIcon", "", "label", "setLabel", "(Ljava/lang/String;)Landroidx/picker/model/AppData$ListRadioButtonAppDataBuilder;", "subLabel", "setSubLabel", "actionIcon", "setActionIcon", "", "selected", "setSelected", "(Z)Landroidx/picker/model/AppData$ListRadioButtonAppDataBuilder;", "dimmed", "setDimmed", "build", "()Lz0/c;", "Lz0/b;", "getAppInfo", "()Lz0/b;", "Landroid/graphics/drawable/Drawable;", "Ljava/lang/String;", "Z", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class AppData$ListRadioButtonAppDataBuilder {
    private Drawable actionIcon;
    private final C1281b appInfo;
    private boolean dimmed;
    private Drawable icon;
    private String label;
    private boolean selected;
    private Drawable subIcon;
    private String subLabel;

    public AppData$ListRadioButtonAppDataBuilder(C1281b appInfo) {
        j.f(appInfo, "appInfo");
        this.appInfo = appInfo;
    }

    public final C1281b getAppInfo() {
        return this.appInfo;
    }

    public final AppData$ListRadioButtonAppDataBuilder setActionIcon(Drawable actionIcon) {
        this.actionIcon = actionIcon;
        return this;
    }

    public final AppData$ListRadioButtonAppDataBuilder setDimmed(boolean dimmed) {
        this.dimmed = dimmed;
        return this;
    }

    public final AppData$ListRadioButtonAppDataBuilder setIcon(Drawable icon) {
        this.icon = icon;
        return this;
    }

    public final AppData$ListRadioButtonAppDataBuilder setLabel(String label) {
        this.label = label;
        return this;
    }

    public final AppData$ListRadioButtonAppDataBuilder setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public final AppData$ListRadioButtonAppDataBuilder setSubIcon(Drawable subIcon) {
        this.subIcon = subIcon;
        return this;
    }

    public final AppData$ListRadioButtonAppDataBuilder setSubLabel(String subLabel) {
        this.subLabel = subLabel;
        return this;
    }

    public c build() {
        return new d(this.appInfo, 4, this.icon, this.subIcon, this.label, this.subLabel, null, this.actionIcon, this.selected, this.dimmed, false, 1088);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AppData$ListRadioButtonAppDataBuilder(c appInfoData) {
        this(appInfoData.o());
        j.f(appInfoData, "appInfoData");
        setIcon(appInfoData.getIcon());
        setSubIcon(appInfoData.e());
        setLabel(appInfoData.h());
        setSubLabel(appInfoData.k());
        setActionIcon(appInfoData.l());
        setSelected(appInfoData.p());
        setDimmed(appInfoData.d());
    }
}
