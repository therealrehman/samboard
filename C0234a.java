package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.EnumC0271m;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.fragment.app.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0234a extends j0 implements W {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Y f7589q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f7590r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f7591s;

    public C0234a(Y y4) {
        y4.D();
        L l2 = y4.f7583u;
        if (l2 != null) {
            l2.f7525f.getClassLoader();
        }
        this.f7668a = new ArrayList();
        this.h = true;
        this.f7681p = false;
        this.f7591s = -1;
        this.f7589q = y4;
    }

    @Override // androidx.fragment.app.W
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f7674g) {
            return true;
        }
        Y y4 = this.f7589q;
        if (y4.f7568d == null) {
            y4.f7568d = new ArrayList();
        }
        y4.f7568d.add(this);
        return true;
    }

    @Override // androidx.fragment.app.j0
    public final void d(int i5, Fragment fragment, String str, int i7) {
        String str2 = fragment.mPreviousWho;
        if (str2 != null) {
            Y.d.c(fragment, str2);
        }
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str3 = fragment.mTag;
            if (str3 != null && !str.equals(str3)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
            fragment.mTag = str;
        }
        if (i5 != 0) {
            if (i5 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i9 = fragment.mFragmentId;
            if (i9 != 0 && i9 != i5) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i5);
            }
            fragment.mFragmentId = i5;
            fragment.mContainerId = i5;
        }
        b(new i0(fragment, i7));
        fragment.mFragmentManager = this.f7589q;
    }

    @Override // androidx.fragment.app.j0
    public final C0234a e(Fragment fragment) {
        Y y4 = fragment.mFragmentManager;
        if (y4 == null || y4 == this.f7589q) {
            b(new i0(fragment, 3));
            return this;
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.j0
    public final C0234a g(Fragment fragment, EnumC0271m enumC0271m) {
        Y y4 = fragment.mFragmentManager;
        Y y9 = this.f7589q;
        if (y4 != y9) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + y9);
        }
        if (enumC0271m == EnumC0271m.f7819f && fragment.mState > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + enumC0271m + " after the Fragment has been created");
        }
        if (enumC0271m == EnumC0271m.f7818e) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + enumC0271m + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
        }
        i0 i0Var = new i0();
        i0Var.f7657a = 10;
        i0Var.f7658b = fragment;
        i0Var.f7659c = false;
        i0Var.h = fragment.mMaxState;
        i0Var.f7664i = enumC0271m;
        b(i0Var);
        return this;
    }

    public final void h(int i5) {
        if (this.f7674g) {
            if (Log.isLoggable("FragmentManager", 2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i5);
            }
            ArrayList arrayList = this.f7668a;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                i0 i0Var = (i0) arrayList.get(i7);
                Fragment fragment = i0Var.f7658b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i5;
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Log.v("FragmentManager", "Bump nesting of " + i0Var.f7658b + " to " + i0Var.f7658b.mBackStackNesting);
                    }
                }
            }
        }
    }

    public final int i(boolean z9) {
        if (this.f7590r) {
            throw new IllegalStateException("commit already called");
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new K.b(0));
            j("  ", printWriter, true);
            printWriter.close();
        }
        this.f7590r = true;
        boolean z10 = this.f7674g;
        Y y4 = this.f7589q;
        if (z10) {
            this.f7591s = y4.f7572i.getAndIncrement();
        } else {
            this.f7591s = -1;
        }
        y4.v(this, z9);
        return this.f7591s;
    }

    public final void j(String str, PrintWriter printWriter, boolean z9) {
        String str2;
        if (z9) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f7675i);
            printWriter.print(" mIndex=");
            printWriter.print(this.f7591s);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f7590r);
            if (this.f7673f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f7673f));
            }
            if (this.f7669b != 0 || this.f7670c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f7669b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f7670c));
            }
            if (this.f7671d != 0 || this.f7672e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f7671d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f7672e));
            }
            if (this.f7676j != 0 || this.f7677k != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f7676j));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f7677k);
            }
            if (this.f7678l != 0 || this.f7679m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f7678l));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f7679m);
            }
        }
        ArrayList arrayList = this.f7668a;
        if (arrayList.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            i0 i0Var = (i0) arrayList.get(i5);
            switch (i0Var.f7657a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + i0Var.f7657a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i5);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(i0Var.f7658b);
            if (z9) {
                if (i0Var.f7660d != 0 || i0Var.f7661e != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(i0Var.f7660d));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(i0Var.f7661e));
                }
                if (i0Var.f7662f != 0 || i0Var.f7663g != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(i0Var.f7662f));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(i0Var.f7663g));
                }
            }
        }
    }

    public final C0234a k(Fragment fragment) {
        Y y4;
        if (fragment == null || (y4 = fragment.mFragmentManager) == null || y4 == this.f7589q) {
            b(new i0(fragment, 8));
            return this;
        }
        throw new IllegalStateException("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f7591s >= 0) {
            sb.append(" #");
            sb.append(this.f7591s);
        }
        if (this.f7675i != null) {
            sb.append(" ");
            sb.append(this.f7675i);
        }
        sb.append("}");
        return sb.toString();
    }
}
