package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.C0278u;
import androidx.lifecycle.EnumC0270l;
import androidx.lifecycle.EnumC0271m;
import b.InterfaceC0402b;
import b0.AbstractC0403a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import z.AbstractC1277a;

/* JADX INFO: loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity implements z.e {
    static final String LIFECYCLE_TAG = "android:support:lifecycle";
    boolean mCreated;
    boolean mResumed;
    final K mFragments = new K(new G(this));
    final C0278u mFragmentLifecycleRegistry = new C0278u(this);
    boolean mStopped = true;

    public FragmentActivity() {
        getSavedStateRegistry().d("android:support:lifecycle", new D(0, this));
        final int i5 = 0;
        addOnConfigurationChangedListener(new K.a(this) { // from class: androidx.fragment.app.E

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ FragmentActivity f7510b;

            {
                this.f7510b = this;
            }

            @Override // K.a
            public final void accept(Object obj) {
                switch (i5) {
                    case 0:
                        this.f7510b.mFragments.a();
                        break;
                    default:
                        this.f7510b.mFragments.a();
                        break;
                }
            }
        });
        final int i7 = 1;
        addOnNewIntentListener(new K.a(this) { // from class: androidx.fragment.app.E

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ FragmentActivity f7510b;

            {
                this.f7510b = this;
            }

            @Override // K.a
            public final void accept(Object obj) {
                switch (i7) {
                    case 0:
                        this.f7510b.mFragments.a();
                        break;
                    default:
                        this.f7510b.mFragments.a();
                        break;
                }
            }
        });
        addOnContextAvailableListener(new InterfaceC0402b() { // from class: androidx.fragment.app.F
            @Override // b.InterfaceC0402b
            public final void a(Context context) {
                L l2 = this.f7511a.mFragments.f7523a;
                l2.h.b(l2, l2, null);
            }
        });
    }

    public static boolean c(Y y4) {
        boolean zC = false;
        for (Fragment fragment : y4.f7567c.f()) {
            if (fragment != null) {
                if (fragment.getHost() != null) {
                    zC |= c(fragment.getChildFragmentManager());
                }
                s0 s0Var = fragment.mViewLifecycleOwner;
                EnumC0271m enumC0271m = EnumC0271m.h;
                if (s0Var != null) {
                    s0Var.b();
                    if (s0Var.h.f7828c.compareTo(enumC0271m) >= 0) {
                        fragment.mViewLifecycleOwner.h.g();
                        zC = true;
                    }
                }
                if (fragment.mLifecycleRegistry.f7828c.compareTo(enumC0271m) >= 0) {
                    fragment.mLifecycleRegistry.g();
                    zC = true;
                }
            }
        }
        return zC;
    }

    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.f7523a.h.f7570f.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (shouldDumpInternalState(strArr)) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            String str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print(" mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            if (getApplication() != null) {
                AbstractC0403a.a(this).b(str2, printWriter);
            }
            this.mFragments.f7523a.h.u(str, fileDescriptor, printWriter, strArr);
        }
    }

    public Y getSupportFragmentManager() {
        return this.mFragments.f7523a.h;
    }

    @Deprecated
    public AbstractC0403a getSupportLoaderManager() {
        return AbstractC0403a.a(this);
    }

    public void markFragmentsCreated() {
        while (c(getSupportFragmentManager())) {
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i5, int i7, Intent intent) {
        this.mFragments.a();
        super.onActivityResult(i5, i7, intent);
    }

    @Deprecated
    public void onAttachFragment(Fragment fragment) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        y8.G g9;
        Fragment fragment = (Fragment) U6.n.r0(0, getSupportFragmentManager().f7567c.f());
        if (fragment != null && (g9 = fragment.mDisposableHandle) != null) {
            g9.dispose();
        }
        super.onBackPressed();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.e(EnumC0270l.ON_CREATE);
        Z z9 = this.mFragments.f7523a.h;
        z9.f7557F = false;
        z9.G = false;
        z9.f7563M.f7616m = false;
        z9.t(1);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.f7523a.h.k();
        this.mFragmentLifecycleRegistry.e(EnumC0270l.ON_DESTROY);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i5, MenuItem menuItem) {
        if (super.onMenuItemSelected(i5, menuItem)) {
            return true;
        }
        if (i5 == 6) {
            return this.mFragments.f7523a.h.i(menuItem);
        }
        return false;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.f7523a.h.t(5);
        this.mFragmentLifecycleRegistry.e(EnumC0270l.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i5, String[] strArr, int[] iArr) {
        this.mFragments.a();
        super.onRequestPermissionsResult(i5, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.mResumed = true;
        this.mFragments.a();
        this.mFragments.f7523a.h.x(true);
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.e(EnumC0270l.ON_RESUME);
        Z z9 = this.mFragments.f7523a.h;
        z9.f7557F = false;
        z9.G = false;
        z9.f7563M.f7616m = false;
        z9.t(7);
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            Z z9 = this.mFragments.f7523a.h;
            z9.f7557F = false;
            z9.G = false;
            z9.f7563M.f7616m = false;
            z9.t(4);
        }
        this.mFragments.a();
        this.mFragments.f7523a.h.x(true);
        this.mFragmentLifecycleRegistry.e(EnumC0270l.ON_START);
        Z z10 = this.mFragments.f7523a.h;
        z10.f7557F = false;
        z10.G = false;
        z10.f7563M.f7616m = false;
        z10.t(5);
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.a();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        Z z9 = this.mFragments.f7523a.h;
        z9.G = true;
        z9.f7563M.f7616m = true;
        z9.t(4);
        this.mFragmentLifecycleRegistry.e(EnumC0270l.ON_STOP);
    }

    public void setEnterSharedElementCallback(z.q qVar) {
        AbstractC1277a.c(this, null);
    }

    public void setExitSharedElementCallback(z.q qVar) {
        AbstractC1277a.d(this, null);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i5, Bundle bundle) {
        if (i5 == -1) {
            startActivityForResult(intent, -1, bundle);
        } else {
            fragment.startActivityForResult(intent, i5, bundle);
        }
    }

    @Deprecated
    public void startIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i5, Intent intent, int i7, int i9, int i10, Bundle bundle) throws IntentSender.SendIntentException {
        if (i5 == -1) {
            startIntentSenderForResult(intentSender, i5, intent, i7, i9, i10, bundle);
        } else {
            fragment.startIntentSenderForResult(intentSender, i5, intent, i7, i9, i10, bundle);
        }
    }

    public void supportFinishAfterTransition() {
        AbstractC1277a.a(this);
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateMenu();
    }

    public void supportPostponeEnterTransition() {
        AbstractC1277a.b(this);
    }

    public void supportStartPostponedEnterTransition() {
        AbstractC1277a.e(this);
    }

    @Override // z.e
    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i5) {
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i5) {
        startActivityFromFragment(fragment, intent, i5, (Bundle) null);
    }
}
