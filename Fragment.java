package androidx.fragment.app;

import a0.AbstractC0115b;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.appcompat.widget.AbstractC0152g1;
import androidx.lifecycle.AbstractC0272n;
import androidx.lifecycle.C0278u;
import androidx.lifecycle.EnumC0270l;
import androidx.lifecycle.EnumC0271m;
import androidx.lifecycle.InterfaceC0266h;
import androidx.lifecycle.InterfaceC0275q;
import androidx.lifecycle.InterfaceC0276s;
import b0.AbstractC0403a;
import c.AbstractC0431a;
import com.samsung.android.keyscafe.R;
import d6.AbstractC0476d;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, InterfaceC0276s, androidx.lifecycle.W, InterfaceC0266h, U0.g {
    static final int ACTIVITY_CREATED = 4;
    static final int ATTACHED = 0;
    static final int AWAITING_ENTER_EFFECTS = 6;
    static final int AWAITING_EXIT_EFFECTS = 3;
    static final int CREATED = 1;
    static final int INITIALIZING = -1;
    static final int RESUMED = 7;
    static final int STARTED = 5;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    static final int VIEW_CREATED = 2;
    boolean mAdded;
    A mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mBeingSaved;
    private boolean mCalled;
    Y mChildFragmentManager;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    androidx.lifecycle.U mDefaultFactory;
    boolean mDeferStart;
    boolean mDetached;
    y8.G mDisposableHandle;
    int mFragmentId;
    Y mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    L mHost;
    boolean mInLayout;
    boolean mIsCreated;
    private Boolean mIsPrimaryNavigationFragment;
    LayoutInflater mLayoutInflater;
    C0278u mLifecycleRegistry;
    EnumC0271m mMaxState;
    boolean mMenuVisible;
    private final AtomicInteger mNextLocalRequestCode;
    private final ArrayList<B> mOnPreAttachedListeners;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    Runnable mPostponedDurationRunnable;
    Handler mPostponedHandler;
    public String mPreviousWho;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    private final B mSavedStateAttachListener;
    U0.f mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    Bundle mSavedViewRegistryState;
    SparseArray<Parcelable> mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetRequestCode;
    String mTargetWho;
    boolean mUserVisibleHint;
    View mView;
    s0 mViewLifecycleOwner;
    androidx.lifecycle.z mViewLifecycleOwnerLiveData;
    String mWho;

    public Fragment() {
        this.mState = -1;
        this.mWho = UUID.randomUUID().toString();
        this.mTargetWho = null;
        this.mIsPrimaryNavigationFragment = null;
        this.mChildFragmentManager = new Z();
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mPostponedDurationRunnable = new RunnableC0254v(this, 0);
        this.mMaxState = EnumC0271m.f7821i;
        this.mViewLifecycleOwnerLiveData = new androidx.lifecycle.z();
        this.mNextLocalRequestCode = new AtomicInteger();
        this.mOnPreAttachedListeners = new ArrayList<>();
        this.mDisposableHandle = null;
        this.mSavedStateAttachListener = new C0255w(this);
        d();
    }

    @Deprecated
    public static Fragment instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    public final A a() {
        if (this.mAnimationInfo == null) {
            A a10 = new A();
            a10.f7496i = null;
            Object obj = USE_DEFAULT_TRANSITION;
            a10.f7497j = obj;
            a10.f7498k = null;
            a10.f7499l = obj;
            a10.f7500m = null;
            a10.f7501n = obj;
            a10.f7503q = 1.0f;
            a10.f7504r = null;
            this.mAnimationInfo = a10;
        }
        return this.mAnimationInfo;
    }

    public final int b() {
        EnumC0271m enumC0271m = this.mMaxState;
        return (enumC0271m == EnumC0271m.f7819f || this.mParentFragment == null) ? enumC0271m.ordinal() : Math.min(enumC0271m.ordinal(), this.mParentFragment.b());
    }

    public final Fragment c(boolean z9) {
        String str;
        if (z9) {
            Y.c cVar = Y.d.f5697a;
            Y.d.b(new Y.f(this, "Attempting to get target fragment from fragment " + this));
            Y.d.a(this).getClass();
            Object obj = Y.b.f5693j;
            if (obj instanceof Void) {
            }
        }
        Fragment fragment = this.mTarget;
        if (fragment != null) {
            return fragment;
        }
        Y y4 = this.mFragmentManager;
        if (y4 == null || (str = this.mTargetWho) == null) {
            return null;
        }
        return y4.f7567c.b(str);
    }

    public void callStartTransitionListener(boolean z9) {
        ViewGroup viewGroup;
        Y y4;
        A a10 = this.mAnimationInfo;
        if (a10 != null) {
            a10.f7505s = false;
        }
        if (this.mView == null || (viewGroup = this.mContainer) == null || (y4 = this.mFragmentManager) == null) {
            return;
        }
        C0246m c0246mL = C0246m.l(viewGroup, y4);
        c0246mL.m();
        if (z9) {
            this.mHost.f7526g.post(new RunnableC0247n(1, c0246mL));
        } else {
            c0246mL.h();
        }
        Handler handler = this.mPostponedHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mPostponedDurationRunnable);
            this.mPostponedHandler = null;
        }
    }

    public J createFragmentContainer() {
        return new C0256x(this);
    }

    public final void d() {
        this.mLifecycleRegistry = new C0278u(this);
        this.mSavedStateRegistryController = new U0.f(this);
        this.mDefaultFactory = null;
        if (this.mOnPreAttachedListeners.contains(this.mSavedStateAttachListener)) {
            return;
        }
        B b3 = this.mSavedStateAttachListener;
        if (this.mState >= 0) {
            b3.a();
        } else {
            this.mOnPreAttachedListeners.add(b3);
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mSavedViewRegistryState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.mSavedViewRegistryState);
        }
        Fragment fragmentC = c(false);
        if (fragmentC != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentC);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(getPopDirection());
        if (getEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(getEnterAnim());
        }
        if (getExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(getExitAnim());
        }
        if (getPopEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(getPopEnterAnim());
        }
        if (getPopExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(getPopExitAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
        }
        if (getContext() != null) {
            AbstractC0403a.a(this).b(str, printWriter);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.mChildFragmentManager + ":");
        this.mChildFragmentManager.u(AbstractC0476d.k(str, "  "), fileDescriptor, printWriter, strArr);
    }

    public final C0253u e(AbstractC0431a abstractC0431a, C0257y c0257y, androidx.activity.result.b bVar) {
        if (this.mState > 1) {
            throw new IllegalStateException(A8.l.q("Fragment ", this, " is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate())."));
        }
        AtomicReference atomicReference = new AtomicReference();
        C0258z c0258z = new C0258z(this, c0257y, atomicReference, abstractC0431a, bVar);
        if (this.mState >= 0) {
            c0258z.a();
        } else {
            this.mOnPreAttachedListeners.add(c0258z);
        }
        return new C0253u(atomicReference);
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Fragment findFragmentByWho(String str) {
        return str.equals(this.mWho) ? this : this.mChildFragmentManager.f7567c.c(str);
    }

    public String generateActivityResultKey() {
        return "fragment_" + this.mWho + "_rq#" + this.mNextLocalRequestCode.getAndIncrement();
    }

    public final FragmentActivity getActivity() {
        L l2 = this.mHost;
        if (l2 == null) {
            return null;
        }
        return (FragmentActivity) l2.f7524e;
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        A a10 = this.mAnimationInfo;
        if (a10 == null || (bool = a10.f7502p) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        A a10 = this.mAnimationInfo;
        if (a10 == null || (bool = a10.o) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public View getAnimatingAway() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        a10.getClass();
        return null;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final Y getChildFragmentManager() {
        if (this.mHost != null) {
            return this.mChildFragmentManager;
        }
        throw new IllegalStateException(A8.l.q("Fragment ", this, " has not been attached yet."));
    }

    public Context getContext() {
        L l2 = this.mHost;
        if (l2 == null) {
            return null;
        }
        return l2.f7525f;
    }

    @Override // androidx.lifecycle.InterfaceC0266h
    public AbstractC0115b getDefaultViewModelCreationExtras() {
        Application application;
        Context applicationContext = requireContext().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            }
            if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            }
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        if (application == null && Log.isLoggable("FragmentManager", 3)) {
            Log.d("FragmentManager", "Could not find Application instance from Context " + requireContext().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
        }
        a0.d dVar = new a0.d();
        LinkedHashMap linkedHashMap = dVar.f5976a;
        if (application != null) {
            linkedHashMap.put(androidx.lifecycle.S.f7800e, application);
        }
        linkedHashMap.put(androidx.lifecycle.K.f7773a, this);
        linkedHashMap.put(androidx.lifecycle.K.f7774b, this);
        if (getArguments() != null) {
            linkedHashMap.put(androidx.lifecycle.K.f7775c, getArguments());
        }
        return dVar;
    }

    public androidx.lifecycle.U getDefaultViewModelProviderFactory() {
        Application application;
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.mDefaultFactory == null) {
            Context applicationContext = requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    application = null;
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            if (application == null && Log.isLoggable("FragmentManager", 3)) {
                Log.d("FragmentManager", "Could not find Application instance from Context " + requireContext().getApplicationContext() + ", you will need CreationExtras to use AndroidViewModel with the default ViewModelProvider.Factory");
            }
            this.mDefaultFactory = new androidx.lifecycle.N(application, this, getArguments());
        }
        return this.mDefaultFactory;
    }

    public int getEnterAnim() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return 0;
        }
        return a10.f7490b;
    }

    public Object getEnterTransition() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        return a10.f7496i;
    }

    public z.q getEnterTransitionCallback() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        a10.getClass();
        return null;
    }

    public int getExitAnim() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return 0;
        }
        return a10.f7491c;
    }

    public Object getExitTransition() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        return a10.f7498k;
    }

    public z.q getExitTransitionCallback() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        a10.getClass();
        return null;
    }

    public View getFocusedView() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        return a10.f7504r;
    }

    @Deprecated
    public final Y getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        L l2 = this.mHost;
        if (l2 == null) {
            return null;
        }
        return ((G) l2).f7516i;
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    @Override // androidx.lifecycle.InterfaceC0276s
    public AbstractC0272n getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public AbstractC0403a getLoaderManager() {
        return AbstractC0403a.a(this);
    }

    public int getNextTransition() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return 0;
        }
        return a10.f7494f;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public final Y getParentFragmentManager() {
        Y y4 = this.mFragmentManager;
        if (y4 != null) {
            return y4;
        }
        throw new IllegalStateException(A8.l.q("Fragment ", this, " not associated with a fragment manager."));
    }

    public boolean getPopDirection() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return false;
        }
        return a10.f7489a;
    }

    public int getPopEnterAnim() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return 0;
        }
        return a10.f7492d;
    }

    public int getPopExitAnim() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return 0;
        }
        return a10.f7493e;
    }

    public float getPostOnViewCreatedAlpha() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return 1.0f;
        }
        return a10.f7503q;
    }

    public Object getReenterTransition() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        Object obj = a10.f7499l;
        return obj == USE_DEFAULT_TRANSITION ? getExitTransition() : obj;
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    @Deprecated
    public final boolean getRetainInstance() {
        Y.c cVar = Y.d.f5697a;
        Y.d.b(new Y.e(this, "Attempting to get retain instance for fragment " + this));
        Y.d.a(this).getClass();
        Object obj = Y.b.h;
        if (obj instanceof Void) {
        }
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        Object obj = a10.f7497j;
        return obj == USE_DEFAULT_TRANSITION ? getEnterTransition() : obj;
    }

    @Override // U0.g
    public final U0.e getSavedStateRegistry() {
        return this.mSavedStateRegistryController.f3356b;
    }

    public Object getSharedElementEnterTransition() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        return a10.f7500m;
    }

    public Object getSharedElementReturnTransition() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return null;
        }
        Object obj = a10.f7501n;
        return obj == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : obj;
    }

    public ArrayList<String> getSharedElementSourceNames() {
        ArrayList<String> arrayList;
        A a10 = this.mAnimationInfo;
        return (a10 == null || (arrayList = a10.f7495g) == null) ? new ArrayList<>() : arrayList;
    }

    public ArrayList<String> getSharedElementTargetNames() {
        ArrayList<String> arrayList;
        A a10 = this.mAnimationInfo;
        return (a10 == null || (arrayList = a10.h) == null) ? new ArrayList<>() : arrayList;
    }

    public final String getString(int i5) {
        return getResources().getString(i5);
    }

    public final String getTag() {
        return this.mTag;
    }

    @Deprecated
    public final Fragment getTargetFragment() {
        return c(true);
    }

    @Deprecated
    public final int getTargetRequestCode() {
        Y.c cVar = Y.d.f5697a;
        Y.d.b(new Y.f(this, "Attempting to get target request code from fragment " + this));
        Y.d.a(this).getClass();
        Object obj = Y.b.f5693j;
        if (obj instanceof Void) {
        }
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int i5) {
        return getResources().getText(i5);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public InterfaceC0276s getViewLifecycleOwner() {
        s0 s0Var = this.mViewLifecycleOwner;
        if (s0Var != null) {
            return s0Var;
        }
        throw new IllegalStateException(A8.l.q("Can't access the Fragment View's LifecycleOwner for ", this, " when getView() is null i.e., before onCreateView() or after onDestroyView()"));
    }

    public androidx.lifecycle.y getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // androidx.lifecycle.W
    public androidx.lifecycle.V getViewModelStore() {
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (b() == 1) {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
        HashMap map = this.mFragmentManager.f7563M.f7613j;
        androidx.lifecycle.V v4 = (androidx.lifecycle.V) map.get(this.mWho);
        if (v4 != null) {
            return v4;
        }
        androidx.lifecycle.V v5 = new androidx.lifecycle.V();
        map.put(this.mWho, v5);
        return v5;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void initState() {
        d();
        this.mPreviousWho = this.mWho;
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new Z();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        if (!this.mHidden) {
            Y y4 = this.mFragmentManager;
            if (y4 == null) {
                return false;
            }
            Fragment fragment = this.mParentFragment;
            y4.getClass();
            if (!(fragment == null ? false : fragment.isHidden())) {
                return false;
            }
        }
        return true;
    }

    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        if (this.mMenuVisible) {
            if (this.mFragmentManager == null) {
                return true;
            }
            Fragment fragment = this.mParentFragment;
            if (fragment == null ? true : fragment.isMenuVisible()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPostponed() {
        A a10 = this.mAnimationInfo;
        if (a10 == null) {
            return false;
        }
        return a10.f7505s;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        return this.mState >= 7;
    }

    public final boolean isStateSaved() {
        Y y4 = this.mFragmentManager;
        if (y4 == null) {
            return false;
        }
        return y4.f7557F || y4.G;
    }

    public final boolean isVisible() {
        View view;
        return (!isAdded() || isHidden() || (view = this.mView) == null || view.getWindowToken() == null || this.mView.getVisibility() != 0) ? false : true;
    }

    public void noteStateNotSaved() {
        this.mChildFragmentManager.K();
    }

    @Deprecated
    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    @Deprecated
    public void onActivityResult(int i5, int i7, Intent intent) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i5 + " resultCode: " + i7 + " data: " + intent);
        }
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        L l2 = this.mHost;
        Activity activity = l2 == null ? null : l2.f7524e;
        if (activity != null) {
            this.mCalled = false;
            onAttach(activity);
        }
    }

    @Deprecated
    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState();
        Y y4 = this.mChildFragmentManager;
        if (y4.f7582t >= 1) {
            return;
        }
        y4.f7557F = false;
        y4.G = false;
        y4.f7563M.f7616m = false;
        y4.t(1);
    }

    public Animation onCreateAnimation(int i5, boolean z9, int i7) {
        View view;
        Context context = getContext();
        if (context == null || !TextUtils.isEmpty(Settings.System.getString(context.getContentResolver(), "current_sec_active_themepackage")) || (view = getView()) == null) {
            return null;
        }
        FragmentActivity activity = getActivity();
        int[] iArrI = AbstractC0152g1.i(2);
        int length = iArrI.length;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                int[] iArrI2 = AbstractC0152g1.i(2);
                int length2 = iArrI2.length;
                int i10 = 0;
                while (true) {
                    if (i10 >= length2) {
                        break;
                    }
                    if (A8.l.b(iArrI2[i10]) == i7) {
                        view.setTranslationZ(1.0f);
                        break;
                    }
                    i10++;
                }
            } else {
                if (A8.l.c(iArrI[i9]) == i7) {
                    view.setTranslationZ(0.0f);
                    break;
                }
                i9++;
            }
        }
        for (int i11 : AbstractC0152g1.i(2)) {
            if (A8.l.b(i11) == i7 || A8.l.c(i11) == i7 || A8.l.h(i11) == i7 || A8.l.i(i11) == i7) {
                if (activity != null) {
                    activity.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.sesl_fragment_fgcolor));
                }
                view.setBackgroundColor(getResources().getColor(R.color.sesl_fragment_bgcolor));
                final WeakReference weakReference = new WeakReference(view);
                this.mDisposableHandle = new y8.G() { // from class: androidx.fragment.app.s
                    @Override // y8.G
                    public final void dispose() {
                        Animation animation;
                        Fragment fragment = this.f7721e;
                        fragment.getClass();
                        View view2 = (View) weakReference.get();
                        if (view2 != null && (animation = view2.getAnimation()) != null && !animation.hasEnded()) {
                            Log.d("FragmentManager", "Fragment Animation was canceled by back press");
                            view2.clearAnimation();
                        }
                        fragment.mDisposableHandle = null;
                    }
                };
                return null;
            }
        }
        final WeakReference weakReference2 = new WeakReference(view);
        this.mDisposableHandle = new y8.G() { // from class: androidx.fragment.app.s
            @Override // y8.G
            public final void dispose() {
                Animation animation;
                Fragment fragment = this.f7721e;
                fragment.getClass();
                View view2 = (View) weakReference2.get();
                if (view2 != null && (animation = view2.getAnimation()) != null && !animation.hasEnded()) {
                    Log.d("FragmentManager", "Fragment Animation was canceled by back press");
                    view2.clearAnimation();
                }
                fragment.mDisposableHandle = null;
            }
        };
        return null;
    }

    public Animator onCreateAnimator(int i5, boolean z9, int i7) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Deprecated
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i5 = this.mContentLayoutId;
        if (i5 != 0) {
            return layoutInflater.inflate(i5, viewGroup, false);
        }
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    @Deprecated
    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z9) {
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        L l2 = this.mHost;
        Activity activity = l2 == null ? null : l2.f7524e;
        if (activity != null) {
            this.mCalled = false;
            onInflate(activity, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z9) {
    }

    @Deprecated
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    @Deprecated
    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z9) {
    }

    @Deprecated
    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onPrimaryNavigationFragmentChanged(boolean z9) {
    }

    @Deprecated
    public void onRequestPermissionsResult(int i5, String[] strArr, int[] iArr) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    public void performActivityCreated(Bundle bundle) {
        this.mChildFragmentManager.K();
        this.mState = 3;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onActivityCreated()"));
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
        }
        if (this.mView != null) {
            Bundle bundle2 = this.mSavedFragmentState;
            restoreViewState(bundle2 != null ? bundle2.getBundle("savedInstanceState") : null);
        }
        this.mSavedFragmentState = null;
        Y y4 = this.mChildFragmentManager;
        y4.f7557F = false;
        y4.G = false;
        y4.f7563M.f7616m = false;
        y4.t(4);
    }

    public void performAttach() {
        Iterator<B> it = this.mOnPreAttachedListeners.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.mOnPreAttachedListeners.clear();
        this.mChildFragmentManager.b(this.mHost, createFragmentContainer(), this);
        this.mState = 0;
        this.mCalled = false;
        onAttach(this.mHost.f7525f);
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onAttach()"));
        }
        Iterator it2 = this.mFragmentManager.f7577n.iterator();
        while (it2.hasNext()) {
            ((c0) it2.next()).a(this);
        }
        Y y4 = this.mChildFragmentManager;
        y4.f7557F = false;
        y4.G = false;
        y4.f7563M.f7616m = false;
        y4.t(0);
    }

    public void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
    }

    public boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        return this.mChildFragmentManager.i(menuItem);
    }

    public void performCreate(Bundle bundle) {
        this.mChildFragmentManager.K();
        this.mState = 1;
        this.mCalled = false;
        this.mLifecycleRegistry.a(new InterfaceC0275q() { // from class: androidx.fragment.app.Fragment.6
            @Override // androidx.lifecycle.InterfaceC0275q
            public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
                View view;
                if (enumC0270l != EnumC0270l.ON_STOP || (view = Fragment.this.mView) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
            }
        });
        onCreate(bundle);
        this.mIsCreated = true;
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onCreate()"));
        }
        this.mLifecycleRegistry.e(EnumC0270l.ON_CREATE);
    }

    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z9 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z9 = true;
        }
        return z9 | this.mChildFragmentManager.j(menu, menuInflater);
    }

    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mChildFragmentManager.K();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new s0(this, getViewModelStore(), new RunnableC0252t(0, this));
        View viewOnCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = viewOnCreateView;
        if (viewOnCreateView == null) {
            if (this.mViewLifecycleOwner.h != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.mViewLifecycleOwner = null;
            return;
        }
        this.mViewLifecycleOwner.b();
        if (Log.isLoggable("FragmentManager", 3)) {
            Log.d("FragmentManager", "Setting ViewLifecycleOwner on View " + this.mView + " for Fragment " + this);
        }
        androidx.lifecycle.K.h(this.mView, this.mViewLifecycleOwner);
        View view = this.mView;
        s0 s0Var = this.mViewLifecycleOwner;
        kotlin.jvm.internal.j.f(view, "<this>");
        view.setTag(R.id.view_tree_view_model_store_owner, s0Var);
        android.support.v4.media.session.f.V(this.mView, this.mViewLifecycleOwner);
        this.mViewLifecycleOwnerLiveData.h(this.mViewLifecycleOwner);
    }

    public void performDestroy() {
        this.mChildFragmentManager.k();
        this.mLifecycleRegistry.e(EnumC0270l.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onDestroy()"));
        }
    }

    public void performDestroyView() {
        this.mChildFragmentManager.t(1);
        if (this.mView != null) {
            s0 s0Var = this.mViewLifecycleOwner;
            s0Var.b();
            if (s0Var.h.f7828c.compareTo(EnumC0271m.f7820g) >= 0) {
                this.mViewLifecycleOwner.a(EnumC0270l.ON_DESTROY);
            }
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onDestroyView()"));
        }
        q.l lVar = AbstractC0403a.a(this).f9697b.h;
        if (lVar.g() <= 0) {
            this.mPerformedCreateView = false;
        } else {
            A8.l.z(lVar.h(0));
            throw null;
        }
    }

    public void performDetach() {
        this.mState = -1;
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onDetach()"));
        }
        Y y4 = this.mChildFragmentManager;
        if (y4.f7558H) {
            return;
        }
        y4.k();
        this.mChildFragmentManager = new Z();
    }

    public LayoutInflater performGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = layoutInflaterOnGetLayoutInflater;
        return layoutInflaterOnGetLayoutInflater;
    }

    public void performLowMemory() {
        onLowMemory();
    }

    public void performMultiWindowModeChanged(boolean z9) {
        onMultiWindowModeChanged(z9);
    }

    public boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) {
            return true;
        }
        return this.mChildFragmentManager.o(menuItem);
    }

    public void performOptionsMenuClosed(Menu menu) {
        if (this.mHidden) {
            return;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onOptionsMenuClosed(menu);
        }
        this.mChildFragmentManager.p(menu);
    }

    public void performPause() {
        this.mChildFragmentManager.t(5);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(EnumC0270l.ON_PAUSE);
        }
        this.mLifecycleRegistry.e(EnumC0270l.ON_PAUSE);
        this.mState = 6;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onPause()"));
        }
    }

    public void performPictureInPictureModeChanged(boolean z9) {
        onPictureInPictureModeChanged(z9);
    }

    public boolean performPrepareOptionsMenu(Menu menu) {
        boolean z9 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z9 = true;
        }
        return z9 | this.mChildFragmentManager.s(menu);
    }

    public void performPrimaryNavigationFragmentChanged() {
        this.mFragmentManager.getClass();
        boolean zI = Y.I(this);
        Boolean bool = this.mIsPrimaryNavigationFragment;
        if (bool == null || bool.booleanValue() != zI) {
            this.mIsPrimaryNavigationFragment = Boolean.valueOf(zI);
            onPrimaryNavigationFragmentChanged(zI);
            Y y4 = this.mChildFragmentManager;
            y4.a0();
            y4.q(y4.f7586x);
        }
    }

    public void performResume() {
        this.mChildFragmentManager.K();
        this.mChildFragmentManager.x(true);
        this.mState = 7;
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onResume()"));
        }
        C0278u c0278u = this.mLifecycleRegistry;
        EnumC0270l enumC0270l = EnumC0270l.ON_RESUME;
        c0278u.e(enumC0270l);
        if (this.mView != null) {
            this.mViewLifecycleOwner.h.e(enumC0270l);
        }
        Y y4 = this.mChildFragmentManager;
        y4.f7557F = false;
        y4.G = false;
        y4.f7563M.f7616m = false;
        y4.t(7);
    }

    public void performSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
    }

    public void performStart() {
        this.mChildFragmentManager.K();
        this.mChildFragmentManager.x(true);
        this.mState = 5;
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onStart()"));
        }
        C0278u c0278u = this.mLifecycleRegistry;
        EnumC0270l enumC0270l = EnumC0270l.ON_START;
        c0278u.e(enumC0270l);
        if (this.mView != null) {
            this.mViewLifecycleOwner.h.e(enumC0270l);
        }
        Y y4 = this.mChildFragmentManager;
        y4.f7557F = false;
        y4.G = false;
        y4.f7563M.f7616m = false;
        y4.t(5);
    }

    public void performStop() {
        Y y4 = this.mChildFragmentManager;
        y4.G = true;
        y4.f7563M.f7616m = true;
        y4.t(4);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(EnumC0270l.ON_STOP);
        }
        this.mLifecycleRegistry.e(EnumC0270l.ON_STOP);
        this.mState = 4;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onStop()"));
        }
    }

    public void performViewCreated() {
        Bundle bundle = this.mSavedFragmentState;
        onViewCreated(this.mView, bundle != null ? bundle.getBundle("savedInstanceState") : null);
        this.mChildFragmentManager.t(2);
    }

    public void postponeEnterTransition() {
        a().f7505s = true;
    }

    public final <I, O> androidx.activity.result.c registerForActivityResult(AbstractC0431a abstractC0431a, androidx.activity.result.b bVar) {
        return e(abstractC0431a, new C0257y(0, this), bVar);
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    @Deprecated
    public final void requestPermissions(String[] strArr, int i5) {
        if (this.mHost == null) {
            throw new IllegalStateException(A8.l.q("Fragment ", this, " not attached to Activity"));
        }
        Y parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.f7554C == null) {
            parentFragmentManager.f7583u.getClass();
            return;
        }
        parentFragmentManager.f7555D.addLast(new V(this.mWho, i5));
        parentFragmentManager.f7554C.a(strArr);
    }

    public final FragmentActivity requireActivity() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException(A8.l.q("Fragment ", this, " not attached to an activity."));
    }

    public final Bundle requireArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException(A8.l.q("Fragment ", this, " does not have any arguments."));
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException(A8.l.q("Fragment ", this, " not attached to a context."));
    }

    @Deprecated
    public final Y requireFragmentManager() {
        return getParentFragmentManager();
    }

    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException(A8.l.q("Fragment ", this, " not attached to a host."));
    }

    public final Fragment requireParentFragment() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            return parentFragment;
        }
        if (getContext() == null) {
            throw new IllegalStateException(A8.l.q("Fragment ", this, " is not attached to any Fragment or host"));
        }
        throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + getContext());
    }

    public final View requireView() {
        View view = getView();
        if (view != null) {
            return view;
        }
        throw new IllegalStateException(A8.l.q("Fragment ", this, " did not return a View from onCreateView() or this was called before onCreateView()."));
    }

    public void restoreChildFragmentState() {
        Bundle bundle;
        Bundle bundle2 = this.mSavedFragmentState;
        if (bundle2 == null || (bundle = bundle2.getBundle("childFragmentManager")) == null) {
            return;
        }
        this.mChildFragmentManager.R(bundle);
        Y y4 = this.mChildFragmentManager;
        y4.f7557F = false;
        y4.G = false;
        y4.f7563M.f7616m = false;
        y4.t(1);
    }

    public final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (!this.mCalled) {
            throw new y0(A8.l.q("Fragment ", this, " did not call through to super.onViewStateRestored()"));
        }
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(EnumC0270l.ON_CREATE);
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z9) {
        a().f7502p = Boolean.valueOf(z9);
    }

    public void setAllowReturnTransitionOverlap(boolean z9) {
        a().o = Boolean.valueOf(z9);
    }

    public void setAnimations(int i5, int i7, int i9, int i10) {
        if (this.mAnimationInfo == null && i5 == 0 && i7 == 0 && i9 == 0 && i10 == 0) {
            return;
        }
        a().f7490b = i5;
        a().f7491c = i7;
        a().f7492d = i9;
        a().f7493e = i10;
    }

    public void setArguments(Bundle bundle) {
        if (this.mFragmentManager != null && isStateSaved()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.mArguments = bundle;
    }

    public void setEnterSharedElementCallback(z.q qVar) {
        a().getClass();
    }

    public void setEnterTransition(Object obj) {
        a().f7496i = obj;
    }

    public void setExitSharedElementCallback(z.q qVar) {
        a().getClass();
    }

    public void setExitTransition(Object obj) {
        a().f7498k = obj;
    }

    public void setFocusedView(View view) {
        a().f7504r = view;
    }

    @Deprecated
    public void setHasOptionsMenu(boolean z9) {
        if (this.mHasMenu != z9) {
            this.mHasMenu = z9;
            if (!isAdded() || isHidden()) {
                return;
            }
            ((G) this.mHost).f7516i.invalidateMenu();
        }
    }

    public void setInitialSavedState(C c5) {
        Bundle bundle;
        if (this.mFragmentManager != null) {
            throw new IllegalStateException("Fragment already added");
        }
        if (c5 == null || (bundle = c5.f7506e) == null) {
            bundle = null;
        }
        this.mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean z9) {
        if (this.mMenuVisible != z9) {
            this.mMenuVisible = z9;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                ((G) this.mHost).f7516i.invalidateMenu();
            }
        }
    }

    public void setNextTransition(int i5) {
        if (this.mAnimationInfo == null && i5 == 0) {
            return;
        }
        a();
        this.mAnimationInfo.f7494f = i5;
    }

    public void setPopDirection(boolean z9) {
        if (this.mAnimationInfo == null) {
            return;
        }
        a().f7489a = z9;
    }

    public void setPostOnViewCreatedAlpha(float f2) {
        a().f7503q = f2;
    }

    public void setReenterTransition(Object obj) {
        a().f7499l = obj;
    }

    @Deprecated
    public void setRetainInstance(boolean z9) {
        Y.c cVar = Y.d.f5697a;
        Y.d.b(new Y.e(this, "Attempting to set retain instance for fragment " + this));
        Y.d.a(this).getClass();
        Object obj = Y.b.h;
        if (obj instanceof Void) {
        }
        this.mRetainInstance = z9;
        Y y4 = this.mFragmentManager;
        if (y4 == null) {
            this.mRetainInstanceChangedWhileDetached = true;
        } else if (z9) {
            y4.f7563M.c(this);
        } else {
            y4.f7563M.g(this);
        }
    }

    public void setReturnTransition(Object obj) {
        a().f7497j = obj;
    }

    public void setSharedElementEnterTransition(Object obj) {
        a().f7500m = obj;
    }

    public void setSharedElementNames(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        a();
        A a10 = this.mAnimationInfo;
        a10.f7495g = arrayList;
        a10.h = arrayList2;
    }

    public void setSharedElementReturnTransition(Object obj) {
        a().f7501n = obj;
    }

    @Deprecated
    public void setTargetFragment(Fragment fragment, int i5) {
        if (fragment != null) {
            Y.c cVar = Y.d.f5697a;
            Y.d.b(new Y.f(this, "Attempting to set target fragment " + fragment + " with request code " + i5 + " for fragment " + this));
            Y.d.a(this).getClass();
            Object obj = Y.b.f5693j;
            if (obj instanceof Void) {
            }
        }
        Y y4 = this.mFragmentManager;
        Y y9 = fragment != null ? fragment.mFragmentManager : null;
        if (y4 != null && y9 != null && y4 != y9) {
            throw new IllegalArgumentException(A8.l.q("Fragment ", fragment, " must share the same FragmentManager to be set as a target fragment"));
        }
        for (Fragment fragmentC = fragment; fragmentC != null; fragmentC = fragmentC.c(false)) {
            if (fragmentC.equals(this)) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        if (fragment == null) {
            this.mTargetWho = null;
            this.mTarget = null;
        } else if (this.mFragmentManager == null || fragment.mFragmentManager == null) {
            this.mTargetWho = null;
            this.mTarget = fragment;
        } else {
            this.mTargetWho = fragment.mWho;
            this.mTarget = null;
        }
        this.mTargetRequestCode = i5;
    }

    @Deprecated
    public void setUserVisibleHint(boolean z9) {
        Y.c cVar = Y.d.f5697a;
        Y.d.b(new Y.a(this, "Attempting to set user visible hint to " + z9 + " for fragment " + this));
        Y.d.a(this).getClass();
        Object obj = Y.b.f5692i;
        if (obj instanceof Void) {
        }
        boolean z10 = false;
        if (!this.mUserVisibleHint && z9 && this.mState < 5 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            Y y4 = this.mFragmentManager;
            f0 f0VarF = y4.f(this);
            Fragment fragment = f0VarF.f7642c;
            if (fragment.mDeferStart) {
                if (y4.f7566b) {
                    y4.f7559I = true;
                } else {
                    fragment.mDeferStart = false;
                    f0VarF.k();
                }
            }
        }
        this.mUserVisibleHint = z9;
        if (this.mState < 5 && !z9) {
            z10 = true;
        }
        this.mDeferStart = z10;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z9);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        L l2 = this.mHost;
        if (l2 != null) {
            return p0.a.p(((G) l2).f7516i, str);
        }
        return false;
    }

    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int i5) {
        startActivityForResult(intent, i5, null);
    }

    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i5, Intent intent, int i7, int i9, int i10, Bundle bundle) throws IntentSender.SendIntentException {
        Intent intent2 = intent;
        if (this.mHost == null) {
            throw new IllegalStateException(A8.l.q("Fragment ", this, " not attached to Activity"));
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in startIntentSenderForResult() requestCode: " + i5 + " IntentSender: " + intentSender + " fillInIntent: " + intent + " options: " + bundle);
        }
        Y parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.f7553B == null) {
            L l2 = parentFragmentManager.f7583u;
            if (i5 == -1) {
                l2.f7524e.startIntentSenderForResult(intentSender, i5, intent, i7, i9, i10, bundle);
                return;
            } else {
                l2.getClass();
                throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
            }
        }
        if (bundle != null) {
            if (intent2 == null) {
                intent2 = new Intent();
                intent2.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
            }
            if (Log.isLoggable("FragmentManager", 2)) {
                Log.v("FragmentManager", "ActivityOptions " + bundle + " were added to fillInIntent " + intent2 + " for fragment " + this);
            }
            intent2.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        }
        kotlin.jvm.internal.j.f(intentSender, "intentSender");
        androidx.activity.result.i iVar = new androidx.activity.result.i(intentSender, intent2, i7, i9);
        parentFragmentManager.f7555D.addLast(new V(this.mWho, i5));
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Fragment " + this + "is launching an IntentSender for result ");
        }
        parentFragmentManager.f7553B.a(iVar);
    }

    public void startPostponedEnterTransition() {
        if (this.mAnimationInfo == null || !a().f7505s) {
            return;
        }
        if (this.mHost == null) {
            a().f7505s = false;
        } else if (Looper.myLooper() != this.mHost.f7526g.getLooper()) {
            this.mHost.f7526g.postAtFrontOfQueue(new RunnableC0254v(this, 1));
        } else {
            callStartTransitionListener(true);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} (");
        sb.append(this.mWho);
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        sb.append(")");
        return sb.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    @Deprecated
    public static Fragment instantiate(Context context, String str, Bundle bundle) {
        try {
            Fragment fragment = (Fragment) S.c(context.getClassLoader(), str).getConstructor(null).newInstance(null);
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.setArguments(bundle);
            }
            return fragment;
        } catch (IllegalAccessException e3) {
            throw new G6.a(5, A8.l.t("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e3);
        } catch (InstantiationException e10) {
            throw new G6.a(5, A8.l.t("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e10);
        } catch (NoSuchMethodException e11) {
            throw new G6.a(5, A8.l.t("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e11);
        } catch (InvocationTargetException e12) {
            throw new G6.a(5, A8.l.t("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e12);
        }
    }

    public final String getString(int i5, Object... objArr) {
        return getResources().getString(i5, objArr);
    }

    public final void postponeEnterTransition(long j5, TimeUnit timeUnit) {
        a().f7505s = true;
        Handler handler = this.mPostponedHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mPostponedDurationRunnable);
        }
        Y y4 = this.mFragmentManager;
        if (y4 != null) {
            this.mPostponedHandler = y4.f7583u.f7526g;
        } else {
            this.mPostponedHandler = new Handler(Looper.getMainLooper());
        }
        this.mPostponedHandler.removeCallbacks(this.mPostponedDurationRunnable);
        this.mPostponedHandler.postDelayed(this.mPostponedDurationRunnable, timeUnit.toMillis(j5));
    }

    public final <I, O> androidx.activity.result.c registerForActivityResult(AbstractC0431a abstractC0431a, androidx.activity.result.g gVar, androidx.activity.result.b bVar) {
        return e(abstractC0431a, new C0257y(1, gVar), bVar);
    }

    public void startActivity(Intent intent, Bundle bundle) {
        L l2 = this.mHost;
        if (l2 == null) {
            throw new IllegalStateException(A8.l.q("Fragment ", this, " not attached to Activity"));
        }
        l2.f7525f.startActivity(intent, bundle);
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int i5, Bundle bundle) {
        if (this.mHost == null) {
            throw new IllegalStateException(A8.l.q("Fragment ", this, " not attached to Activity"));
        }
        Y parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.f7552A != null) {
            parentFragmentManager.f7555D.addLast(new V(this.mWho, i5));
            if (bundle != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
            }
            parentFragmentManager.f7552A.a(intent);
            return;
        }
        L l2 = parentFragmentManager.f7583u;
        if (i5 == -1) {
            l2.f7525f.startActivity(intent, bundle);
        } else {
            l2.getClass();
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        L l2 = this.mHost;
        if (l2 != null) {
            FragmentActivity fragmentActivity = ((G) l2).f7516i;
            LayoutInflater layoutInflaterCloneInContext = fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
            layoutInflaterCloneInContext.setFactory2(this.mChildFragmentManager.f7570f);
            return layoutInflaterCloneInContext;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }

    public Fragment(int i5) {
        this();
        this.mContentLayoutId = i5;
    }
}
