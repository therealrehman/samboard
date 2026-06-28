package androidx.preference;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.picker.widget.HandlerC0295o;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.AbstractC0370y0;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import k.C0633b;
import k.C0634c;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class PreferenceFragment extends Fragment implements G, E, F, InterfaceC0307b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public H f8741f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public RecyclerView f8742g;
    public ContextThemeWrapper h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C0633b f8744j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public C0633b f8745k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public C0634c f8746l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8747m;
    public ViewTreeObserver.OnPreDrawListener o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f8749p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f8750q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f8751r;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s f8740e = new s(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8743i = R.layout.preference_list_fragment;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f8748n = true;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f8752s = -1;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f8753t = -1;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f8754u = -1;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f8755v = -1;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final HandlerC0295o f8756w = new HandlerC0295o(this);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final t f8757x = new t(2, this);

    public abstract void a(Bundle bundle, String str);

    @Override // androidx.preference.InterfaceC0307b
    public final Preference findPreference(CharSequence charSequence) {
        PreferenceScreen preferenceScreen;
        H h = this.f8741f;
        if (h == null || (preferenceScreen = h.f8633g) == null) {
            return null;
        }
        return preferenceScreen.H(charSequence);
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        RecyclerView recyclerView = this.f8742g;
        if (recyclerView != null) {
            if (this.o == null) {
                ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
                if (this.f8742g != null) {
                    this.o = new u(1, this);
                }
                viewTreeObserver.addOnPreDrawListener(this.o);
            }
            AbstractC0341j0 adapter = this.f8742g.getAdapter();
            boolean z9 = configuration.screenWidthDp <= 250;
            if (z9 != this.f8751r && (adapter instanceof A)) {
                this.f8751r = z9;
                TypedArray typedArrayObtainStyledAttributes = null;
                try {
                    ContextThemeWrapper contextThemeWrapper = this.h;
                    typedArrayObtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(null, L.f8654g, C.b.a(contextThemeWrapper, R.attr.preferenceFragmentStyle, android.R.attr.preferenceFragmentStyle), 0);
                    Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
                    s sVar = this.f8740e;
                    if (drawable != null) {
                        sVar.getClass();
                        sVar.f8812b = drawable.getIntrinsicHeight();
                    } else {
                        sVar.f8812b = 0;
                    }
                    sVar.f8811a = drawable;
                    sVar.f8814d.f8742g.invalidateItemDecorations();
                    RecyclerView recyclerView2 = this.f8742g;
                    recyclerView2.setAdapter(recyclerView2.getAdapter());
                    AbstractC0370y0 layoutManager = this.f8742g.getLayoutManager();
                    if (layoutManager != null) {
                        layoutManager.onRestoreInstanceState(layoutManager.onSaveInstanceState());
                    }
                    typedArrayObtainStyledAttributes.recycle();
                } catch (Throwable th) {
                    if (typedArrayObtainStyledAttributes != null) {
                        typedArrayObtainStyledAttributes.recycle();
                    }
                    throw th;
                }
            }
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.preferenceTheme, typedValue, true);
        Configuration configuration = getResources().getConfiguration();
        int i5 = configuration.screenWidthDp;
        this.f8749p = ((i5 > 320 || configuration.fontScale < 1.1f) && (i5 >= 411 || configuration.fontScale < 1.3f)) ? 2 : 1;
        this.f8750q = i5;
        this.f8751r = i5 <= 250;
        int i7 = typedValue.resourceId;
        if (i7 == 0) {
            i7 = R.style.PreferenceThemeOverlay;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), i7);
        this.h = contextThemeWrapper;
        H h = new H(contextThemeWrapper);
        this.f8741f = h;
        h.f8635j = this;
        a(bundle, getArguments() != null ? getArguments().getString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT) : null);
    }

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        ContextThemeWrapper contextThemeWrapper = this.h;
        TypedArray typedArrayObtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(null, L.f8654g, C.b.a(contextThemeWrapper, R.attr.preferenceFragmentStyle, android.R.attr.preferenceFragmentStyle), 0);
        this.f8743i = typedArrayObtainStyledAttributes.getResourceId(0, this.f8743i);
        boolean z9 = true;
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, -1);
        boolean z10 = typedArrayObtainStyledAttributes.getBoolean(3, true);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = this.h.obtainStyledAttributes(null, AbstractC0478a.f10554E, android.R.attr.listSeparatorTextViewStyle, 0);
        Drawable drawable2 = typedArrayObtainStyledAttributes2.getDrawable(1);
        if (drawable2 instanceof ColorDrawable) {
            this.f8747m = ((ColorDrawable) drawable2).getColor();
        }
        Log.d("SeslPreferenceFragment", " sub header color = " + this.f8747m);
        typedArrayObtainStyledAttributes2.recycle();
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(this.h);
        View viewInflate = layoutInflaterCloneInContext.inflate(this.f8743i, viewGroup, false);
        View viewFindViewById = viewInflate.findViewById(android.R.id.list_container);
        if (!(viewFindViewById instanceof ViewGroup)) {
            throw new RuntimeException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById;
        if (!this.h.getPackageManager().hasSystemFeature("android.hardware.type.automotive") || (recyclerView = (RecyclerView) viewGroup2.findViewById(R.id.recycler_view)) == null) {
            recyclerView = (RecyclerView) layoutInflaterCloneInContext.inflate(R.layout.sesl_preference_recyclerview, viewGroup2, false);
            getActivity();
            recyclerView.setLayoutManager(new LinearLayoutManager());
            recyclerView.setAccessibilityDelegateCompat(new J(recyclerView));
        }
        this.f8742g = recyclerView;
        if (this.o == null) {
            ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
            if (this.f8742g != null) {
                this.o = new u(1, this);
            }
            viewTreeObserver.addOnPreDrawListener(this.o);
        }
        this.f8742g.addOnAttachStateChangeListener(new r(0, this));
        s sVar = this.f8740e;
        recyclerView.addItemDecoration(sVar);
        if (drawable != null) {
            sVar.getClass();
            sVar.f8812b = drawable.getIntrinsicHeight();
        } else {
            sVar.f8812b = 0;
        }
        sVar.f8811a = drawable;
        sVar.f8814d.f8742g.invalidateItemDecorations();
        if (dimensionPixelSize != -1) {
            sVar.f8812b = dimensionPixelSize;
            sVar.f8814d.f8742g.invalidateItemDecorations();
        }
        sVar.f8813c = z10;
        this.f8742g.setItemAnimator(null);
        this.f8744j = new C0633b(this.h);
        this.f8746l = new C0634c(this.h);
        if (this.f8748n) {
            recyclerView.seslSetFillBottomEnabled(true);
            recyclerView.seslSetFillBottomColor(this.f8747m);
            C0633b c0633b = new C0633b(this.h);
            this.f8745k = c0633b;
            c0633b.d(3);
        }
        if (this.f8742g.getParent() == null) {
            viewGroup2.addView(this.f8742g);
        }
        this.f8756w.post(this.f8757x);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sesl_preference_padding_horizontal);
        int i5 = this.f8752s;
        if (i5 < 0) {
            i5 = dimensionPixelSize2;
        }
        int i7 = this.f8753t;
        if (i7 < 0) {
            i7 = 0;
        }
        int i9 = this.f8754u;
        if (i9 >= 0) {
            dimensionPixelSize2 = i9;
        }
        int i10 = this.f8755v;
        if (i10 < 0) {
            i10 = 0;
        }
        this.f8752s = i5;
        this.f8753t = i7;
        this.f8754u = dimensionPixelSize2;
        this.f8755v = i10;
        RecyclerView recyclerView2 = this.f8742g;
        if (recyclerView2 != null) {
            recyclerView2.setPadding(i5, i7, dimensionPixelSize2, i10);
            RecyclerView recyclerView3 = this.f8742g;
            if (this.f8752s == 0 && this.f8754u == 0 && this.f8753t == 0 && this.f8755v == 0) {
                z9 = false;
            }
            recyclerView3.seslSetFillHorizontalPaddingEnabled(z9);
            this.f8742g.setScrollBarStyle((this.f8752s > 0 || this.f8754u > 0) ? 33554432 : 0);
        }
        return viewInflate;
    }

    @Override // android.app.Fragment
    public final void onDestroyView() {
        RecyclerView recyclerView;
        t tVar = this.f8757x;
        HandlerC0295o handlerC0295o = this.f8756w;
        handlerC0295o.removeCallbacks(tVar);
        handlerC0295o.removeMessages(1);
        if (this.o != null && (recyclerView = this.f8742g) != null) {
            recyclerView.getViewTreeObserver().removeOnPreDrawListener(this.o);
        }
        this.f8742g = null;
        super.onDestroyView();
    }

    @Override // androidx.preference.E
    public final void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment multiSelectListPreferenceDialogFragment;
        getActivity();
        if (getFragmentManager().findFragmentByTag("androidx.preference.PreferenceFragment.DIALOG") != null) {
            return;
        }
        if (preference instanceof EditTextPreference) {
            String str = preference.f8715p;
            multiSelectListPreferenceDialogFragment = new EditTextPreferenceDialogFragment();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", str);
            multiSelectListPreferenceDialogFragment.setArguments(bundle);
        } else if (preference instanceof ListPreference) {
            String str2 = preference.f8715p;
            multiSelectListPreferenceDialogFragment = new ListPreferenceDialogFragment();
            Bundle bundle2 = new Bundle(1);
            bundle2.putString("key", str2);
            multiSelectListPreferenceDialogFragment.setArguments(bundle2);
        } else {
            if (!(preference instanceof MultiSelectListPreference)) {
                throw new IllegalArgumentException("Tried to display dialog for unknown preference type. Did you forget to override onDisplayPreferenceDialog()?");
            }
            String str3 = preference.f8715p;
            multiSelectListPreferenceDialogFragment = new MultiSelectListPreferenceDialogFragment();
            Bundle bundle3 = new Bundle(1);
            bundle3.putString("key", str3);
            multiSelectListPreferenceDialogFragment.setArguments(bundle3);
        }
        multiSelectListPreferenceDialogFragment.setTargetFragment(this, 0);
        multiSelectListPreferenceDialogFragment.show(getFragmentManager(), "androidx.preference.PreferenceFragment.DIALOG");
    }

    @Override // androidx.preference.F
    public final void onNavigateToScreen(PreferenceScreen preferenceScreen) {
        getActivity();
    }

    @Override // androidx.preference.G
    public final boolean onPreferenceTreeClick(Preference preference) {
        if (preference.f8717r != null) {
            getActivity();
        }
        return false;
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferenceScreen = this.f8741f.f8633g;
        if (preferenceScreen != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreen.d(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        H h = this.f8741f;
        h.h = this;
        h.f8634i = this;
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        H h = this.f8741f;
        h.h = null;
        h.f8634i = null;
    }

    @Override // android.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen preferenceScreen;
        super.onViewCreated(view, bundle);
        if (bundle == null || (bundle2 = bundle.getBundle("android:preferences")) == null || (preferenceScreen = this.f8741f.f8633g) == null) {
            return;
        }
        preferenceScreen.c(bundle2);
    }
}
