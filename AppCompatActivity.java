package androidx.appcompat.app;

import H.k;
import U0.a;
import android.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.media.session.f;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.C0206z;
import androidx.appcompat.widget.P0;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.f2;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.K;
import g.AbstractC0533a;
import g.C;
import g.C0546n;
import g.I;
import g.InterfaceC0534b;
import g.InterfaceC0547o;
import g.P;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import l.AbstractC0660b;
import l.C0668j;
import l.InterfaceC0659a;
import p1.d;
import q.e;
import z.r;
import z.s;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements InterfaceC0547o, r {
    private static final String DELEGATE_TAG = "androidx:appcompat";
    private g.r mDelegate;
    private Resources mResources;

    public AppCompatActivity() {
        getSavedStateRegistry().d(DELEGATE_TAG, new a(this));
        addOnContextAvailableListener(new C0546n(this));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        d();
        C c5 = (C) getDelegate();
        c5.w();
        ((ViewGroup) c5.f10967E.findViewById(R.id.content)).addView(view, layoutParams);
        c5.f11000q.a(c5.f10999p.getCallback());
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00af  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void attachBaseContext(android.content.Context r9) {
        /*
            Method dump skipped, instruction units count: 493
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatActivity.attachBaseContext(android.content.Context):void");
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        AbstractC0533a supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.a()) {
                super.closeOptionsMenu();
            }
        }
    }

    public final void d() {
        K.h(getWindow().getDecorView(), this);
        View decorView = getWindow().getDecorView();
        j.f(decorView, "<this>");
        decorView.setTag(com.samsung.android.keyscafe.R.id.view_tree_view_model_store_owner, this);
        f.V(getWindow().getDecorView(), this);
        View decorView2 = getWindow().getDecorView();
        j.f(decorView2, "<this>");
        decorView2.setTag(com.samsung.android.keyscafe.R.id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        AbstractC0533a supportActionBar = getSupportActionBar();
        if (keyCode == 82 && supportActionBar != null && supportActionBar.j(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i5) {
        C c5 = (C) getDelegate();
        c5.w();
        return (T) c5.f10999p.findViewById(i5);
    }

    public g.r getDelegate() {
        if (this.mDelegate == null) {
            I i5 = g.r.f11155e;
            this.mDelegate = new C(this, null, this, this);
        }
        return this.mDelegate;
    }

    public InterfaceC0534b getDrawerToggleDelegate() {
        ((C) getDelegate()).getClass();
        return new e2.C(4);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        C c5 = (C) getDelegate();
        if (c5.f11003t == null) {
            c5.A();
            AbstractC0533a abstractC0533a = c5.f11002s;
            c5.f11003t = new C0668j(abstractC0533a != null ? abstractC0533a.e() : c5.o);
        }
        return c5.f11003t;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = this.mResources;
        if (resources == null) {
            int i5 = f2.f6708a;
        }
        return resources == null ? super.getResources() : resources;
    }

    public AbstractC0533a getSupportActionBar() {
        C c5 = (C) getDelegate();
        c5.A();
        return c5.f11002s;
    }

    @Override // z.r
    public Intent getSupportParentActivityIntent() {
        return d.g(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getDelegate().b();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C c5 = (C) getDelegate();
        if (c5.f10971J && c5.f10966D) {
            c5.A();
            AbstractC0533a abstractC0533a = c5.f11002s;
            if (abstractC0533a != null) {
                abstractC0533a.g();
            }
        }
        C0206z c0206zA = C0206z.a();
        Context context = c5.o;
        synchronized (c0206zA) {
            P0 p02 = c0206zA.f6907a;
            synchronized (p02) {
                e eVar = (e) p02.f6507a.get(context);
                if (eVar != null) {
                    eVar.a();
                }
            }
        }
        c5.f10983V = new Configuration(c5.o.getResources().getConfiguration());
        c5.n(false, false);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(super.getResources().getConfiguration(), super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        onSupportContentChanged();
    }

    public void onCreateSupportNavigateUpTaskStack(s sVar) {
        sVar.getClass();
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = d.g(this);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            Context context = sVar.f15264f;
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(context.getPackageManager());
            }
            ArrayList arrayList = sVar.f15263e;
            int size = arrayList.size();
            try {
                for (Intent intentH = d.h(context, component); intentH != null; intentH = d.h(context, intentH.getComponent())) {
                    arrayList.add(size, intentH);
                }
                arrayList.add(supportParentActivityIntent);
            } catch (PackageManager.NameNotFoundException e3) {
                Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                throw new IllegalArgumentException(e3);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().e();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i5, KeyEvent keyEvent) {
        return super.onKeyDown(i5, keyEvent);
    }

    public void onLocalesChanged(k kVar) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i5, MenuItem menuItem) {
        if (super.onMenuItemSelected(i5, menuItem)) {
            return true;
        }
        AbstractC0533a supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null || (supportActionBar.d() & 4) == 0) {
            return false;
        }
        return onSupportNavigateUp();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i5, Menu menu) {
        return super.onMenuOpened(i5, menu);
    }

    public void onNightModeChanged(int i5) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i5, Menu menu) {
        super.onPanelClosed(i5, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((C) getDelegate()).w();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        C c5 = (C) getDelegate();
        c5.A();
        AbstractC0533a abstractC0533a = c5.f11002s;
        if (abstractC0533a != null) {
            abstractC0533a.n(true);
        }
    }

    public void onPrepareSupportNavigateUpTaskStack(s sVar) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        ((C) getDelegate()).n(true, false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        getDelegate().f();
    }

    @Override // g.InterfaceC0547o
    public void onSupportActionModeFinished(AbstractC0660b abstractC0660b) {
    }

    @Override // g.InterfaceC0547o
    public void onSupportActionModeStarted(AbstractC0660b abstractC0660b) {
    }

    @Deprecated
    public void onSupportContentChanged() {
    }

    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        if (!supportShouldUpRecreateTask(supportParentActivityIntent)) {
            supportNavigateUpTo(supportParentActivityIntent);
            return true;
        }
        s sVar = new s(this);
        onCreateSupportNavigateUpTaskStack(sVar);
        onPrepareSupportNavigateUpTaskStack(sVar);
        ArrayList arrayList = sVar.f15263e;
        if (arrayList.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[0]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        sVar.f15264f.startActivities(intentArr, null);
        try {
            finishAffinity();
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i5) {
        super.onTitleChanged(charSequence, i5);
        getDelegate().l(charSequence);
    }

    @Override // g.InterfaceC0547o
    public AbstractC0660b onWindowStartingSupportActionMode(InterfaceC0659a interfaceC0659a) {
        return null;
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        AbstractC0533a supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.k()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i5) {
        d();
        getDelegate().i(i5);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        C c5 = (C) getDelegate();
        if (c5.f10998n instanceof Activity) {
            c5.A();
            AbstractC0533a abstractC0533a = c5.f11002s;
            if (abstractC0533a instanceof P) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            c5.f11003t = null;
            if (abstractC0533a != null) {
                abstractC0533a.h();
            }
            c5.f11002s = null;
            if (toolbar != null) {
                Object obj = c5.f10998n;
                g.K k5 = new g.K(toolbar, obj instanceof Activity ? ((Activity) obj).getTitle() : c5.f11004u, c5.f11000q);
                c5.f11002s = k5;
                c5.f11000q.f11168f = k5.f11020c;
                toolbar.setBackInvokedCallbackEnabled(true);
                Window window = c5.f10999p;
                if (window != null) {
                    window.setCallback(c5.f11000q);
                }
            } else {
                c5.f11000q.f11168f = null;
            }
            c5.b();
        }
    }

    @Deprecated
    public void setSupportProgress(int i5) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean z9) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean z9) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean z9) {
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i5) {
        super.setTheme(i5);
        ((C) getDelegate()).f10985X = i5;
    }

    public AbstractC0660b startSupportActionMode(InterfaceC0659a interfaceC0659a) {
        return getDelegate().m(interfaceC0659a);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void supportInvalidateOptionsMenu() {
        getDelegate().b();
    }

    public void supportNavigateUpTo(Intent intent) {
        navigateUpTo(intent);
    }

    public boolean supportRequestWindowFeature(int i5) {
        return getDelegate().h(i5);
    }

    public boolean supportShouldUpRecreateTask(Intent intent) {
        return shouldUpRecreateTask(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        d();
        getDelegate().j(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        d();
        getDelegate().k(view, layoutParams);
    }
}
