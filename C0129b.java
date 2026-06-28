package androidx.appcompat.view.menu;

import androidx.appcompat.widget.A0;
import androidx.appcompat.widget.C0150g;
import androidx.appcompat.widget.C0153h;

/* JADX INFO: renamed from: androidx.appcompat.view.menu.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0129b extends A0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ActionMenuItemView f6243n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0129b(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.f6243n = actionMenuItemView;
    }

    @Override // androidx.appcompat.widget.A0
    public final A b() {
        C0150g c0150g;
        AbstractC0130c abstractC0130c = this.f6243n.f6174j;
        if (abstractC0130c == null || (c0150g = ((C0153h) abstractC0130c).f6712a.f6771p) == null) {
            return null;
        }
        return c0150g.getPopup();
    }

    @Override // androidx.appcompat.widget.A0
    public final boolean c() {
        A aB;
        ActionMenuItemView actionMenuItemView = this.f6243n;
        i iVar = actionMenuItemView.h;
        return iVar != null && iVar.a(actionMenuItemView.f6170e) && (aB = b()) != null && aB.a();
    }
}
