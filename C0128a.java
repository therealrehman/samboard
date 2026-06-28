package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: renamed from: androidx.appcompat.view.menu.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0128a implements F.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f6229e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f6230f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Intent f6231g;
    public char h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public char f6233j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f6235l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f6236m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f6237n;
    public CharSequence o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6232i = 4096;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6234k = 4096;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f6238p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public PorterDuff.Mode f6239q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f6240r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f6241s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f6242t = 16;

    public C0128a(Context context, CharSequence charSequence) {
        this.f6236m = context;
        this.f6229e = charSequence;
    }

    @Override // F.a
    public final m b() {
        return null;
    }

    @Override // F.a
    public final F.a c(m mVar) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        return false;
    }

    public final void d() {
        Drawable drawable = this.f6235l;
        if (drawable != null) {
            if (this.f6240r || this.f6241s) {
                this.f6235l = drawable;
                Drawable drawableMutate = drawable.mutate();
                this.f6235l = drawableMutate;
                if (this.f6240r) {
                    E.a.h(drawableMutate, this.f6238p);
                }
                if (this.f6241s) {
                    E.a.i(this.f6235l, this.f6239q);
                }
            }
        }
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        return null;
    }

    @Override // F.a, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f6234k;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f6233j;
    }

    @Override // F.a, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f6237n;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return this.f6235l;
    }

    @Override // F.a, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f6238p;
    }

    @Override // F.a, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f6239q;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f6231g;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return R.id.home;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // F.a, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f6232i;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.h;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f6229e;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f6230f;
        return charSequence != null ? charSequence : this.f6229e;
    }

    @Override // F.a, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.o;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f6242t & 1) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f6242t & 2) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f6242t & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return (this.f6242t & 8) == 0;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c5) {
        this.f6233j = Character.toLowerCase(c5);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z9) {
        this.f6242t = (z9 ? 1 : 0) | (this.f6242t & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z9) {
        this.f6242t = (z9 ? 2 : 0) | (this.f6242t & (-3));
        return this;
    }

    @Override // F.a, android.view.MenuItem
    public final F.a setContentDescription(CharSequence charSequence) {
        this.f6237n = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z9) {
        this.f6242t = (z9 ? 16 : 0) | (this.f6242t & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f6235l = drawable;
        d();
        return this;
    }

    @Override // F.a, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f6238p = colorStateList;
        this.f6240r = true;
        d();
        return this;
    }

    @Override // F.a, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f6239q = mode;
        this.f6241s = true;
        d();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f6231g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c5) {
        this.h = c5;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c5, char c9) {
        this.h = c5;
        this.f6233j = Character.toLowerCase(c9);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i5) {
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i5) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f6229e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f6230f = charSequence;
        return this;
    }

    @Override // F.a, android.view.MenuItem
    public final F.a setTooltipText(CharSequence charSequence) {
        this.o = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z9) {
        this.f6242t = (this.f6242t & 8) | (z9 ? 0 : 8);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i5) {
        throw new UnsupportedOperationException();
    }

    @Override // F.a, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c5, int i5) {
        this.f6233j = Character.toLowerCase(c5);
        this.f6234k = KeyEvent.normalizeMetaState(i5);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setContentDescription(CharSequence charSequence) {
        this.f6237n = charSequence;
        return this;
    }

    @Override // F.a, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c5, int i5) {
        this.h = c5;
        this.f6232i = KeyEvent.normalizeMetaState(i5);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i5) {
        this.f6229e = this.f6236m.getResources().getString(i5);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTooltipText(CharSequence charSequence) {
        this.o = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i5) {
        this.f6235l = B.a.b(this.f6236m, i5);
        d();
        return this;
    }

    @Override // F.a, android.view.MenuItem
    public final MenuItem setShortcut(char c5, char c9, int i5, int i7) {
        this.h = c5;
        this.f6232i = KeyEvent.normalizeMetaState(i5);
        this.f6233j = Character.toLowerCase(c9);
        this.f6234k = KeyEvent.normalizeMetaState(i7);
        return this;
    }
}
