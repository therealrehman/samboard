package androidx.preference;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.fragment.app.C0234a;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.S;
import androidx.fragment.app.Y;
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
public abstract class PreferenceFragmentCompat extends Fragment implements G, E, F, InterfaceC0307b {
    public static final String ARG_PREFERENCE_ROOT = "androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT";
    private static final String DIALOG_FRAGMENT_TAG = "androidx.preference.PreferenceFragment.DIALOG";
    private static final float FONT_SCALE_LARGE = 1.3f;
    private static final float FONT_SCALE_MEDIUM = 1.1f;
    private static final int MSG_BIND_PREFERENCES = 1;
    private static final String PREFERENCES_TAG = "android:preferences";
    static final int SWITCH_PREFERENCE_LAYOUT = 2;
    static final int SWITCH_PREFERENCE_LAYOUT_LARGE = 1;
    private static final String TAG = "SeslPreferenceFragmentC";
    private boolean mHavePrefs;
    private boolean mInitDone;
    private int mIsLargeLayout;
    private boolean mIsReducedMargin;
    RecyclerView mList;
    private C0633b mListRoundedCorner;
    private ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    private H mPreferenceManager;
    private C0633b mRoundedCorner;
    private int mScreenWidthDp;
    private Runnable mSelectPreferenceRunnable;
    private int mSubheaderColor;
    private C0634c mSubheaderRoundedCorner;
    private final w mDividerDecoration = new w(this);
    private int mLayoutResId = R.layout.preference_list_fragment;
    private boolean mIsRoundedCorner = true;
    private int mLeft = -1;
    private int mTop = -1;
    private int mRight = -1;
    private int mBottom = -1;
    private final Handler mHandler = new HandlerC0295o(this, Looper.getMainLooper(), 2);
    private final Runnable mRequestFocus = new t(0, this);

    public static boolean access$100(PreferenceFragmentCompat preferenceFragmentCompat, A a10, int i5, int i7) {
        if (i5 == preferenceFragmentCompat.mIsLargeLayout) {
            return i5 == 1 && (preferenceFragmentCompat.mScreenWidthDp != i7 || a10.f8604p == 0);
        }
        return true;
    }

    public void addPreferencesFromResource(int i5) {
        H h = this.mPreferenceManager;
        if (h == null) {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
        setPreferenceScreen(h.d(requireContext(), i5, getPreferenceScreen()));
    }

    public void bindPreferences() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            getListView().setAdapter(onCreateAdapter(preferenceScreen));
            preferenceScreen.m();
        }
        onBindPreferences();
    }

    @Override // androidx.preference.InterfaceC0307b
    public <T extends Preference> T findPreference(CharSequence charSequence) {
        PreferenceScreen preferenceScreen;
        H h = this.mPreferenceManager;
        if (h == null || (preferenceScreen = h.f8633g) == null) {
            return null;
        }
        return (T) preferenceScreen.H(charSequence);
    }

    public Fragment getCallbackFragment() {
        return null;
    }

    public final RecyclerView getListView() {
        return this.mList;
    }

    public H getPreferenceManager() {
        return this.mPreferenceManager;
    }

    public PreferenceScreen getPreferenceScreen() {
        return this.mPreferenceManager.f8633g;
    }

    public void onBindPreferences() {
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (getListView() != null) {
            if (this.mOnPreDrawListener == null) {
                ViewTreeObserver viewTreeObserver = getListView().getViewTreeObserver();
                if (this.mList != null) {
                    this.mOnPreDrawListener = new u(0, this);
                }
                viewTreeObserver.addOnPreDrawListener(this.mOnPreDrawListener);
            }
            AbstractC0341j0 adapter = getListView().getAdapter();
            AbstractC0370y0 layoutManager = getListView().getLayoutManager();
            boolean z9 = configuration.screenWidthDp <= 250;
            if (z9 != this.mIsReducedMargin && (adapter instanceof A) && layoutManager != null) {
                this.mIsReducedMargin = z9;
                if (getContext() != null) {
                    TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, L.h, R.attr.preferenceFragmentCompatStyle, 0);
                    try {
                        setDivider(typedArrayObtainStyledAttributes.getDrawable(1));
                        Parcelable parcelableOnSaveInstanceState = layoutManager.onSaveInstanceState();
                        getListView().setAdapter(getListView().getAdapter());
                        layoutManager.onRestoreInstanceState(parcelableOnSaveInstanceState);
                    } finally {
                        typedArrayObtainStyledAttributes.recycle();
                    }
                }
            }
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        requireContext().getTheme().resolveAttribute(R.attr.preferenceTheme, typedValue, true);
        Configuration configuration = getResources().getConfiguration();
        int i5 = configuration.screenWidthDp;
        this.mIsLargeLayout = ((i5 > 320 || configuration.fontScale < FONT_SCALE_MEDIUM) && (i5 >= 411 || configuration.fontScale < FONT_SCALE_LARGE)) ? 2 : 1;
        this.mScreenWidthDp = i5;
        this.mIsReducedMargin = i5 <= 250;
        int i7 = typedValue.resourceId;
        if (i7 == 0) {
            i7 = R.style.PreferenceThemeOverlay;
        }
        requireContext().getTheme().applyStyle(i7, false);
        H h = new H(requireContext());
        this.mPreferenceManager = h;
        h.f8635j = this;
        onCreatePreferences(bundle, getArguments() != null ? getArguments().getString(ARG_PREFERENCE_ROOT) : null);
    }

    public AbstractC0341j0 onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new A(preferenceScreen);
    }

    public AbstractC0370y0 onCreateLayoutManager() {
        requireContext();
        return new LinearLayoutManager();
    }

    public abstract void onCreatePreferences(Bundle bundle, String str);

    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        if (requireContext().getPackageManager().hasSystemFeature("android.hardware.type.automotive") && (recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view)) != null) {
            return recyclerView;
        }
        RecyclerView recyclerView2 = (RecyclerView) layoutInflater.inflate(R.layout.sesl_preference_recyclerview, viewGroup, false);
        recyclerView2.setLayoutManager(onCreateLayoutManager());
        recyclerView2.setAccessibilityDelegateCompat(new J(recyclerView2));
        return recyclerView2;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TypedArray typedArrayObtainStyledAttributes = requireContext().obtainStyledAttributes(null, L.h, R.attr.preferenceFragmentCompatStyle, 0);
        this.mLayoutResId = typedArrayObtainStyledAttributes.getResourceId(0, this.mLayoutResId);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, -1);
        boolean z9 = typedArrayObtainStyledAttributes.getBoolean(3, true);
        typedArrayObtainStyledAttributes.recycle();
        Context context = getContext();
        if (context != null) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(null, AbstractC0478a.f10554E, android.R.attr.listSeparatorTextViewStyle, 0);
            Drawable drawable2 = typedArrayObtainStyledAttributes2.getDrawable(1);
            if (drawable2 instanceof ColorDrawable) {
                this.mSubheaderColor = ((ColorDrawable) drawable2).getColor();
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(context);
        View viewInflate = layoutInflaterCloneInContext.inflate(this.mLayoutResId, viewGroup, false);
        View viewFindViewById = viewInflate.findViewById(android.R.id.list_container);
        if (!(viewFindViewById instanceof ViewGroup)) {
            throw new IllegalStateException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById;
        RecyclerView recyclerViewOnCreateRecyclerView = onCreateRecyclerView(layoutInflaterCloneInContext, viewGroup2, bundle);
        if (recyclerViewOnCreateRecyclerView == null) {
            throw new RuntimeException("Could not create RecyclerView");
        }
        this.mList = recyclerViewOnCreateRecyclerView;
        if (this.mOnPreDrawListener == null) {
            ViewTreeObserver viewTreeObserver = recyclerViewOnCreateRecyclerView.getViewTreeObserver();
            if (this.mList != null) {
                this.mOnPreDrawListener = new u(0, this);
            }
            viewTreeObserver.addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mList.addOnAttachStateChangeListener(new r(1, this));
        recyclerViewOnCreateRecyclerView.addItemDecoration(this.mDividerDecoration);
        setDivider(drawable);
        if (dimensionPixelSize != -1) {
            setDividerHeight(dimensionPixelSize);
        }
        this.mDividerDecoration.f8824c = z9;
        this.mList.setItemAnimator(null);
        this.mRoundedCorner = new C0633b(context);
        this.mSubheaderRoundedCorner = new C0634c(context);
        if (this.mIsRoundedCorner) {
            recyclerViewOnCreateRecyclerView.seslSetFillBottomEnabled(true);
            recyclerViewOnCreateRecyclerView.seslSetFillBottomColor(this.mSubheaderColor);
            C0633b c0633b = new C0633b(context);
            this.mListRoundedCorner = c0633b;
            c0633b.d(3);
        }
        if (this.mList.getParent() == null) {
            viewGroup2.addView(this.mList);
        }
        this.mHandler.post(this.mRequestFocus);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sesl_preference_padding_horizontal);
        int i5 = this.mLeft;
        if (i5 < 0) {
            i5 = dimensionPixelSize2;
        }
        int i7 = this.mTop;
        if (i7 < 0) {
            i7 = 0;
        }
        int i9 = this.mRight;
        if (i9 >= 0) {
            dimensionPixelSize2 = i9;
        }
        int i10 = this.mBottom;
        setPadding(i5, i7, dimensionPixelSize2, i10 >= 0 ? i10 : 0);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        RecyclerView recyclerView;
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mHandler.removeMessages(1);
        if (this.mHavePrefs) {
            getListView().setAdapter(null);
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            if (preferenceScreen != null) {
                preferenceScreen.q();
            }
            onUnbindPreferences();
        }
        if (this.mOnPreDrawListener != null && (recyclerView = this.mList) != null) {
            recyclerView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mList = null;
        super.onDestroyView();
    }

    @Override // androidx.preference.E
    public void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment multiSelectListPreferenceDialogFragmentCompat;
        getCallbackFragment();
        for (Fragment parentFragment = this; parentFragment != null; parentFragment = parentFragment.getParentFragment()) {
        }
        getContext();
        getActivity();
        if (getParentFragmentManager().B(DIALOG_FRAGMENT_TAG) != null) {
            return;
        }
        if (preference instanceof EditTextPreference) {
            String str = preference.f8715p;
            multiSelectListPreferenceDialogFragmentCompat = new EditTextPreferenceDialogFragmentCompat();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", str);
            multiSelectListPreferenceDialogFragmentCompat.setArguments(bundle);
        } else if (preference instanceof ListPreference) {
            String str2 = preference.f8715p;
            multiSelectListPreferenceDialogFragmentCompat = new ListPreferenceDialogFragmentCompat();
            Bundle bundle2 = new Bundle(1);
            bundle2.putString("key", str2);
            multiSelectListPreferenceDialogFragmentCompat.setArguments(bundle2);
        } else {
            if (!(preference instanceof MultiSelectListPreference)) {
                throw new IllegalArgumentException("Cannot display dialog for an unknown Preference type: " + preference.getClass().getSimpleName() + ". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
            }
            String str3 = preference.f8715p;
            multiSelectListPreferenceDialogFragmentCompat = new MultiSelectListPreferenceDialogFragmentCompat();
            Bundle bundle3 = new Bundle(1);
            bundle3.putString("key", str3);
            multiSelectListPreferenceDialogFragmentCompat.setArguments(bundle3);
        }
        multiSelectListPreferenceDialogFragmentCompat.setTargetFragment(this, 0);
        multiSelectListPreferenceDialogFragmentCompat.show(getParentFragmentManager(), DIALOG_FRAGMENT_TAG);
    }

    @Override // androidx.preference.F
    public void onNavigateToScreen(PreferenceScreen preferenceScreen) {
        getCallbackFragment();
        for (Fragment parentFragment = this; parentFragment != null; parentFragment = parentFragment.getParentFragment()) {
        }
        getContext();
        getActivity();
    }

    @Override // androidx.preference.G
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.f8717r == null) {
            return false;
        }
        boolean zH = getCallbackFragment() instanceof PreferenceHeaderFragmentCompat ? ((PreferenceHeaderFragmentCompat) getCallbackFragment()).h(this, preference) : false;
        for (Fragment parentFragment = this; !zH && parentFragment != null; parentFragment = parentFragment.getParentFragment()) {
            if (parentFragment instanceof PreferenceHeaderFragmentCompat) {
                zH = ((PreferenceHeaderFragmentCompat) parentFragment).h(this, preference);
            }
        }
        if (!zH && (getContext() instanceof PreferenceHeaderFragmentCompat)) {
            zH = ((PreferenceHeaderFragmentCompat) getContext()).h(this, preference);
        }
        if (!zH && (getActivity() instanceof PreferenceHeaderFragmentCompat)) {
            zH = ((PreferenceHeaderFragmentCompat) getActivity()).h(this, preference);
        }
        if (!zH) {
            Log.w(TAG, "onPreferenceStartFragment is not implemented in the parent activity - attempting to use a fallback implementation. You should implement this method so that you can configure the new fragment that will be displayed, and set a transition between the fragments.");
            Y parentFragmentManager = getParentFragmentManager();
            Bundle bundleE = preference.e();
            S sD = parentFragmentManager.D();
            requireActivity().getClassLoader();
            Fragment fragmentA = sD.a(preference.f8717r);
            fragmentA.setArguments(bundleE);
            fragmentA.setTargetFragment(this, 0);
            C0234a c0234a = new C0234a(parentFragmentManager);
            c0234a.f(((View) requireView().getParent()).getId(), fragmentA, null);
            if (!c0234a.h) {
                throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
            }
            c0234a.f7674g = true;
            c0234a.f7675i = null;
            c0234a.i(false);
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreen.d(bundle2);
            bundle.putBundle(PREFERENCES_TAG, bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        H h = this.mPreferenceManager;
        h.h = this;
        h.f8634i = this;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        H h = this.mPreferenceManager;
        h.h = null;
        h.f8634i = null;
    }

    public void onUnbindPreferences() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen preferenceScreen;
        super.onViewCreated(view, bundle);
        if (bundle != null && (bundle2 = bundle.getBundle(PREFERENCES_TAG)) != null && (preferenceScreen = getPreferenceScreen()) != null) {
            preferenceScreen.c(bundle2);
        }
        if (this.mHavePrefs) {
            bindPreferences();
            Runnable runnable = this.mSelectPreferenceRunnable;
            if (runnable != null) {
                runnable.run();
                this.mSelectPreferenceRunnable = null;
            }
        }
        this.mInitDone = true;
    }

    public void scrollToPreference(Preference preference) {
        v vVar = new v(this, preference, null);
        if (this.mList == null) {
            this.mSelectPreferenceRunnable = vVar;
        } else {
            vVar.run();
        }
    }

    public void seslSetRoundedCorner(boolean z9) {
        this.mIsRoundedCorner = z9;
    }

    public void setDivider(Drawable drawable) {
        w wVar = this.mDividerDecoration;
        if (drawable != null) {
            wVar.getClass();
            wVar.f8823b = drawable.getIntrinsicHeight();
        } else {
            wVar.f8823b = 0;
        }
        wVar.f8822a = drawable;
        wVar.f8825d.mList.invalidateItemDecorations();
    }

    public void setDividerHeight(int i5) {
        w wVar = this.mDividerDecoration;
        wVar.f8823b = i5;
        wVar.f8825d.mList.invalidateItemDecorations();
    }

    public void setPadding(int i5, int i7, int i9, int i10) {
        this.mLeft = i5;
        this.mTop = i7;
        this.mRight = i9;
        this.mBottom = i10;
        RecyclerView recyclerView = this.mList;
        if (recyclerView != null) {
            recyclerView.setPadding(i5, i7, i9, i10);
            this.mList.seslSetFillHorizontalPaddingEnabled((this.mLeft == 0 && this.mRight == 0 && this.mTop == 0 && this.mBottom == 0) ? false : true);
            this.mList.setScrollBarStyle((this.mLeft > 0 || this.mRight > 0) ? 33554432 : 0);
        }
    }

    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        H h = this.mPreferenceManager;
        PreferenceScreen preferenceScreen2 = h.f8633g;
        if (preferenceScreen != preferenceScreen2) {
            if (preferenceScreen2 != null) {
                preferenceScreen2.q();
            }
            h.f8633g = preferenceScreen;
            if (preferenceScreen != null) {
                onUnbindPreferences();
                this.mHavePrefs = true;
                if (!this.mInitDone || this.mHandler.hasMessages(1)) {
                    return;
                }
                this.mHandler.obtainMessage(1).sendToTarget();
            }
        }
    }

    public void setPreferencesFromResource(int i5, String str) {
        H h = this.mPreferenceManager;
        if (h == null) {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
        PreferenceScreen preferenceScreenD = h.d(requireContext(), i5, null);
        PreferenceScreen preferenceScreen = preferenceScreenD;
        if (str != null) {
            Preference preferenceH = preferenceScreenD.H(str);
            boolean z9 = preferenceH instanceof PreferenceScreen;
            preferenceScreen = preferenceH;
            if (!z9) {
                throw new IllegalArgumentException(A8.l.t("Preference object with key ", str, " is not a PreferenceScreen"));
            }
        }
        setPreferenceScreen(preferenceScreen);
    }

    public void scrollToPreference(String str) {
        v vVar = new v(this, null, str);
        if (this.mList == null) {
            this.mSelectPreferenceRunnable = vVar;
        } else {
            vVar.run();
        }
    }
}
