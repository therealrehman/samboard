package com.bumptech.glide.manager;

import M3.g;
import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.Y;
import com.bumptech.glide.b;
import com.bumptech.glide.l;
import java.util.HashSet;
import java.util.Iterator;
import k2.C0637a;
import k2.InterfaceC0641e;

/* JADX INFO: loaded from: classes.dex */
public class SupportRequestManagerFragment extends Fragment {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0637a f10009e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final g f10010f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashSet f10011g;
    public SupportRequestManagerFragment h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public l f10012i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Fragment f10013j;

    public SupportRequestManagerFragment() {
        C0637a c0637a = new C0637a();
        this.f10010f = new g(27, this);
        this.f10011g = new HashSet();
        this.f10009e = c0637a;
    }

    public final void f(Context context, Y y4) {
        SupportRequestManagerFragment supportRequestManagerFragment = this.h;
        if (supportRequestManagerFragment != null) {
            supportRequestManagerFragment.f10011g.remove(this);
            this.h = null;
        }
        SupportRequestManagerFragment supportRequestManagerFragmentE = b.b(context).f9924k.e(y4);
        this.h = supportRequestManagerFragmentE;
        if (equals(supportRequestManagerFragmentE)) {
            return;
        }
        this.h.f10011g.add(this);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onAttach(Context context) {
        super.onAttach(context);
        Fragment parentFragment = this;
        while (parentFragment.getParentFragment() != null) {
            parentFragment = parentFragment.getParentFragment();
        }
        Y fragmentManager = parentFragment.getFragmentManager();
        if (fragmentManager == null) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
            }
        } else {
            try {
                f(getContext(), fragmentManager);
            } catch (IllegalStateException e3) {
                if (Log.isLoggable("SupportRMFragment", 5)) {
                    Log.w("SupportRMFragment", "Unable to register fragment with root", e3);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        C0637a c0637a = this.f10009e;
        c0637a.f11772g = true;
        Iterator it = r2.l.d(c0637a.f11770e).iterator();
        while (it.hasNext()) {
            ((InterfaceC0641e) it.next()).onDestroy();
        }
        SupportRequestManagerFragment supportRequestManagerFragment = this.h;
        if (supportRequestManagerFragment != null) {
            supportRequestManagerFragment.f10011g.remove(this);
            this.h = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDetach() {
        super.onDetach();
        this.f10013j = null;
        SupportRequestManagerFragment supportRequestManagerFragment = this.h;
        if (supportRequestManagerFragment != null) {
            supportRequestManagerFragment.f10011g.remove(this);
            this.h = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        C0637a c0637a = this.f10009e;
        c0637a.f11771f = true;
        Iterator it = r2.l.d(c0637a.f11770e).iterator();
        while (it.hasNext()) {
            ((InterfaceC0641e) it.next()).onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        C0637a c0637a = this.f10009e;
        c0637a.f11771f = false;
        Iterator it = r2.l.d(c0637a.f11770e).iterator();
        while (it.hasNext()) {
            ((InterfaceC0641e) it.next()).onStop();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{parent=");
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            parentFragment = this.f10013j;
        }
        sb.append(parentFragment);
        sb.append("}");
        return sb.toString();
    }
}
