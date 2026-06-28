package com.bumptech.glide.manager;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import com.bumptech.glide.b;
import com.bumptech.glide.l;
import java.util.HashSet;
import java.util.Iterator;
import k2.C0637a;
import k2.C0643g;
import k2.C0645i;
import k2.InterfaceC0641e;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class RequestManagerFragment extends Fragment {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0637a f10004e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0643g f10005f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashSet f10006g;
    public l h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public RequestManagerFragment f10007i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Fragment f10008j;

    public RequestManagerFragment() {
        C0637a c0637a = new C0637a();
        this.f10005f = new C0643g(0, this);
        this.f10006g = new HashSet();
        this.f10004e = c0637a;
    }

    public final void a(Activity activity) {
        RequestManagerFragment requestManagerFragment = this.f10007i;
        if (requestManagerFragment != null) {
            requestManagerFragment.f10006g.remove(this);
            this.f10007i = null;
        }
        C0645i c0645i = b.b(activity).f9924k;
        c0645i.getClass();
        RequestManagerFragment requestManagerFragmentD = c0645i.d(activity.getFragmentManager());
        this.f10007i = requestManagerFragmentD;
        if (equals(requestManagerFragmentD)) {
            return;
        }
        this.f10007i.f10006g.add(this);
    }

    @Override // android.app.Fragment
    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            a(activity);
        } catch (IllegalStateException e3) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e3);
            }
        }
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        C0637a c0637a = this.f10004e;
        c0637a.f11772g = true;
        Iterator it = r2.l.d(c0637a.f11770e).iterator();
        while (it.hasNext()) {
            ((InterfaceC0641e) it.next()).onDestroy();
        }
        RequestManagerFragment requestManagerFragment = this.f10007i;
        if (requestManagerFragment != null) {
            requestManagerFragment.f10006g.remove(this);
            this.f10007i = null;
        }
    }

    @Override // android.app.Fragment
    public final void onDetach() {
        super.onDetach();
        RequestManagerFragment requestManagerFragment = this.f10007i;
        if (requestManagerFragment != null) {
            requestManagerFragment.f10006g.remove(this);
            this.f10007i = null;
        }
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        C0637a c0637a = this.f10004e;
        c0637a.f11771f = true;
        Iterator it = r2.l.d(c0637a.f11770e).iterator();
        while (it.hasNext()) {
            ((InterfaceC0641e) it.next()).onStart();
        }
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        C0637a c0637a = this.f10004e;
        c0637a.f11771f = false;
        Iterator it = r2.l.d(c0637a.f11770e).iterator();
        while (it.hasNext()) {
            ((InterfaceC0641e) it.next()).onStop();
        }
    }

    @Override // android.app.Fragment
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{parent=");
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            parentFragment = this.f10008j;
        }
        sb.append(parentFragment);
        sb.append("}");
        return sb.toString();
    }
}
