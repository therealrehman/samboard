package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.C0234a;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.S;
import androidx.fragment.app.Y;
import androidx.lifecycle.InterfaceC0276s;
import com.samsung.android.keyscafe.R;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/preference/PreferenceHeaderFragmentCompat;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "M5/k", "preference_release"}, k = 1, mv = {1, 8, 0})
public abstract class PreferenceHeaderFragmentCompat extends Fragment {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public M5.k f8765e;

    public final W0.g f() {
        View viewRequireView = requireView();
        kotlin.jvm.internal.j.d(viewRequireView, "null cannot be cast to non-null type androidx.slidingpanelayout.widget.SlidingPaneLayout");
        return (W0.g) viewRequireView;
    }

    public abstract PreferenceFragmentCompat g();

    public final boolean h(PreferenceFragmentCompat caller, Preference pref) {
        kotlin.jvm.internal.j.f(caller, "caller");
        kotlin.jvm.internal.j.f(pref, "pref");
        int id = caller.getId();
        String str = pref.f8717r;
        if (id != R.id.preferences_header) {
            if (caller.getId() != R.id.preferences_detail) {
                return false;
            }
            S sD = getChildFragmentManager().D();
            requireContext().getClassLoader();
            kotlin.jvm.internal.j.c(str);
            Fragment fragmentA = sD.a(str);
            kotlin.jvm.internal.j.e(fragmentA, "childFragmentManager.fra….fragment!!\n            )");
            fragmentA.setArguments(pref.e());
            Y childFragmentManager = getChildFragmentManager();
            kotlin.jvm.internal.j.e(childFragmentManager, "childFragmentManager");
            C0234a c0234a = new C0234a(childFragmentManager);
            c0234a.f7681p = true;
            c0234a.f(R.id.preferences_detail, fragmentA, null);
            c0234a.f7673f = 4099;
            if (!c0234a.h) {
                throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
            }
            c0234a.f7674g = true;
            c0234a.f7675i = null;
            c0234a.i(false);
            return true;
        }
        if (str == null) {
            Intent intent = pref.f8716q;
            if (intent != null) {
                startActivity(intent);
            }
        } else {
            S sD2 = getChildFragmentManager().D();
            requireContext().getClassLoader();
            Fragment fragmentA2 = sD2.a(str);
            if (fragmentA2 != null) {
                fragmentA2.setArguments(pref.e());
            }
            ArrayList arrayList = getChildFragmentManager().f7568d;
            if ((arrayList != null ? arrayList.size() : 0) > 0) {
                C0234a c0234a2 = (C0234a) getChildFragmentManager().f7568d.get(0);
                kotlin.jvm.internal.j.e(c0234a2, "childFragmentManager.getBackStackEntryAt(0)");
                getChildFragmentManager().L(c0234a2.f7591s, false);
            }
            Y childFragmentManager2 = getChildFragmentManager();
            kotlin.jvm.internal.j.e(childFragmentManager2, "childFragmentManager");
            C0234a c0234a3 = new C0234a(childFragmentManager2);
            c0234a3.f7681p = true;
            kotlin.jvm.internal.j.c(fragmentA2);
            c0234a3.f(R.id.preferences_detail, fragmentA2, null);
            if (f().e()) {
                c0234a3.f7673f = 4099;
            }
            W0.g gVarF = f();
            gVarF.f5232B = true;
            gVarF.f5231A = false;
            gVarF.g(!(Settings.System.getInt(gVarF.getContext().getContentResolver(), "remove_animations", 0) == 1));
            c0234a3.i(false);
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onAttach(Context context) {
        kotlin.jvm.internal.j.f(context, "context");
        super.onAttach(context);
        Y parentFragmentManager = getParentFragmentManager();
        kotlin.jvm.internal.j.e(parentFragmentManager, "parentFragmentManager");
        C0234a c0234a = new C0234a(parentFragmentManager);
        c0234a.k(this);
        c0234a.i(false);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        kotlin.jvm.internal.j.f(inflater, "inflater");
        W0.g gVar = new W0.g(inflater.getContext());
        gVar.setId(R.id.preferences_sliding_pane_layout);
        Context context = inflater.getContext();
        kotlin.jvm.internal.j.e(context, "inflater.context");
        FragmentContainerView fragmentContainerView = new FragmentContainerView(context);
        fragmentContainerView.setId(R.id.preferences_header);
        W0.d dVar = new W0.d(getResources().getDimensionPixelSize(R.dimen.preferences_header_width));
        dVar.f5226a = getResources().getInteger(R.integer.preferences_header_pane_weight);
        gVar.addView(fragmentContainerView, dVar);
        Context context2 = inflater.getContext();
        kotlin.jvm.internal.j.e(context2, "inflater.context");
        FragmentContainerView fragmentContainerView2 = new FragmentContainerView(context2);
        fragmentContainerView2.setId(R.id.preferences_detail);
        W0.d dVar2 = new W0.d(getResources().getDimensionPixelSize(R.dimen.preferences_detail_width));
        dVar2.f5226a = getResources().getInteger(R.integer.preferences_detail_pane_weight);
        gVar.addView(fragmentContainerView2, dVar2);
        if (getChildFragmentManager().A(R.id.preferences_header) == null) {
            PreferenceFragmentCompat preferenceFragmentCompatG = g();
            Y childFragmentManager = getChildFragmentManager();
            kotlin.jvm.internal.j.e(childFragmentManager, "childFragmentManager");
            C0234a c0234a = new C0234a(childFragmentManager);
            c0234a.f7681p = true;
            c0234a.d(R.id.preferences_header, preferenceFragmentCompatG, null, 1);
            c0234a.i(false);
        }
        gVar.setLockMode(3);
        return gVar;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        androidx.activity.x onBackPressedDispatcher;
        kotlin.jvm.internal.j.f(view, "view");
        super.onViewCreated(view, bundle);
        this.f8765e = new M5.k(this);
        W0.g gVarF = f();
        if (!gVarF.isLaidOut() || gVarF.isLayoutRequested()) {
            gVarF.addOnLayoutChangeListener(new C(this));
        } else {
            M5.k kVar = this.f8765e;
            kotlin.jvm.internal.j.c(kVar);
            kVar.setEnabled(f().f5264j && f().e());
        }
        Y childFragmentManager = getChildFragmentManager();
        B b3 = new B(this);
        if (childFragmentManager.f7575l == null) {
            childFragmentManager.f7575l = new ArrayList();
        }
        childFragmentManager.f7575l.add(b3);
        androidx.activity.y yVar = (androidx.activity.y) w8.l.t(w8.l.x(w8.l.v(androidx.activity.z.f6154f, view), androidx.activity.z.f6155g));
        if (yVar == null || (onBackPressedDispatcher = yVar.getOnBackPressedDispatcher()) == null) {
            return;
        }
        InterfaceC0276s viewLifecycleOwner = getViewLifecycleOwner();
        kotlin.jvm.internal.j.e(viewLifecycleOwner, "viewLifecycleOwner");
        M5.k kVar2 = this.f8765e;
        kotlin.jvm.internal.j.c(kVar2);
        onBackPressedDispatcher.a(viewLifecycleOwner, kVar2);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewStateRestored(Bundle bundle) {
        Fragment fragmentA;
        super.onViewStateRestored(bundle);
        if (bundle == null) {
            Fragment fragmentA2 = getChildFragmentManager().A(R.id.preferences_header);
            kotlin.jvm.internal.j.d(fragmentA2, "null cannot be cast to non-null type androidx.preference.PreferenceFragmentCompat");
            PreferenceFragmentCompat preferenceFragmentCompat = (PreferenceFragmentCompat) fragmentA2;
            if (preferenceFragmentCompat.getPreferenceScreen().f8760Z.size() <= 0) {
                fragmentA = null;
            } else {
                int size = preferenceFragmentCompat.getPreferenceScreen().f8760Z.size();
                for (int i5 = 0; i5 < size; i5++) {
                    Preference preferenceI = preferenceFragmentCompat.getPreferenceScreen().I(i5);
                    kotlin.jvm.internal.j.e(preferenceI, "headerFragment.preferenc…reen.getPreference(index)");
                    String str = preferenceI.f8717r;
                    if (str != null) {
                        S sD = getChildFragmentManager().D();
                        requireContext().getClassLoader();
                        fragmentA = sD.a(str);
                        if (fragmentA != null) {
                            fragmentA.setArguments(preferenceI.e());
                        }
                    }
                }
                fragmentA = null;
            }
            if (fragmentA != null) {
                Y childFragmentManager = getChildFragmentManager();
                kotlin.jvm.internal.j.e(childFragmentManager, "childFragmentManager");
                C0234a c0234a = new C0234a(childFragmentManager);
                c0234a.f7681p = true;
                c0234a.f(R.id.preferences_detail, fragmentA, null);
                c0234a.i(false);
            }
        }
    }
}
