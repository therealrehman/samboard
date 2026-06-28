package androidx.fragment.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_INTERNAL_DIALOG_SHOWING = "android:dialogShowing";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    private static final String TAG = "SeslDialogFragment";
    private int mBackStackId;
    private boolean mCancelable;
    private boolean mCreatingDialog;
    private Dialog mDialog;
    private boolean mDialogCreated;
    private Runnable mDismissRunnable;
    private boolean mDismissed;
    private Handler mHandler;
    private androidx.lifecycle.A mObserver;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private boolean mShownByMe;
    private boolean mShowsDialog;
    private int mStyle;
    private int mTheme;
    private boolean mViewDestroyed;

    public DialogFragment() {
        this.mDismissRunnable = new RunnableC0247n(0, this);
        this.mOnCancelListener = new DialogInterfaceOnCancelListenerC0248o(this);
        this.mOnDismissListener = new DialogInterfaceOnDismissListenerC0249p(this);
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
        this.mObserver = new C0250q(this);
        this.mDialogCreated = false;
    }

    @Override // androidx.fragment.app.Fragment
    public J createFragmentContainer() {
        return new r(this, super.createFragmentContainer());
    }

    public void dismiss() {
        f(false, false, false);
    }

    public void dismissAllowingStateLoss() {
        f(true, false, false);
    }

    public void dismissNow() {
        f(false, false, true);
    }

    public final void f(boolean z9, boolean z10, boolean z11) {
        if (this.mDismissed) {
            return;
        }
        this.mDismissed = true;
        this.mShownByMe = false;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!z10) {
                if (Looper.myLooper() == this.mHandler.getLooper()) {
                    onDismiss(this.mDialog);
                } else {
                    this.mHandler.post(this.mDismissRunnable);
                }
            }
        }
        this.mViewDestroyed = true;
        if (this.mBackStackId >= 0) {
            if (z11) {
                Y parentFragmentManager = getParentFragmentManager();
                int i5 = this.mBackStackId;
                if (i5 < 0) {
                    parentFragmentManager.getClass();
                    throw new IllegalArgumentException(A8.l.o(i5, "Bad id: "));
                }
                parentFragmentManager.N(i5, 1);
            } else {
                getParentFragmentManager().L(this.mBackStackId, z9);
            }
            this.mBackStackId = -1;
            return;
        }
        Y parentFragmentManager2 = getParentFragmentManager();
        parentFragmentManager2.getClass();
        C0234a c0234a = new C0234a(parentFragmentManager2);
        c0234a.f7681p = true;
        c0234a.e(this);
        if (z11) {
            if (c0234a.f7674g) {
                throw new IllegalStateException("This transaction is already being added to the back stack");
            }
            c0234a.h = false;
            c0234a.f7589q.y(c0234a, false);
            return;
        }
        if (z9) {
            c0234a.i(true);
        } else {
            c0234a.i(false);
        }
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        getViewLifecycleOwnerLiveData().f(this.mObserver);
        if (this.mShownByMe) {
            return;
        }
        this.mDismissed = false;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new Handler();
        this.mShowsDialog = this.mContainerId == 0;
        if (bundle != null) {
            this.mStyle = bundle.getInt(SAVED_STYLE, 0);
            this.mTheme = bundle.getInt(SAVED_THEME, 0);
            this.mCancelable = bundle.getBoolean(SAVED_CANCELABLE, true);
            this.mShowsDialog = bundle.getBoolean(SAVED_SHOWS_DIALOG, this.mShowsDialog);
            this.mBackStackId = bundle.getInt(SAVED_BACK_STACK_ID, -1);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (Log.isLoggable("FragmentManager", 3)) {
            Log.d(TAG, "onCreateDialog called for DialogFragment " + this);
        }
        return new androidx.activity.m(requireContext(), getTheme());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = true;
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!this.mDismissed) {
                onDismiss(this.mDialog);
            }
            this.mDialog = null;
            this.mDialogCreated = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
        getViewLifecycleOwnerLiveData().g(this.mObserver);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.mViewDestroyed) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            Log.d(TAG, "onDismiss called for DialogFragment " + this);
        }
        f(true, true, false);
    }

    public View onFindViewById(int i5) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            return dialog.findViewById(i5);
        }
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = super.onGetLayoutInflater(bundle);
        boolean z9 = this.mShowsDialog;
        if (!z9 || this.mCreatingDialog) {
            if (Log.isLoggable("FragmentManager", 2)) {
                String str = "getting layout inflater for DialogFragment " + this;
                if (this.mShowsDialog) {
                    Log.d(TAG, "mCreatingDialog = true: " + str);
                } else {
                    Log.d(TAG, "mShowsDialog = false: " + str);
                }
            }
            return layoutInflaterOnGetLayoutInflater;
        }
        if (z9 && !this.mDialogCreated) {
            try {
                this.mCreatingDialog = true;
                Dialog dialogOnCreateDialog = onCreateDialog(bundle);
                this.mDialog = dialogOnCreateDialog;
                if (this.mShowsDialog) {
                    setupDialog(dialogOnCreateDialog, this.mStyle);
                    Context context = getContext();
                    if (context instanceof Activity) {
                        this.mDialog.setOwnerActivity((Activity) context);
                    }
                    this.mDialog.setCancelable(this.mCancelable);
                    this.mDialog.setOnCancelListener(this.mOnCancelListener);
                    this.mDialog.setOnDismissListener(this.mOnDismissListener);
                    this.mDialogCreated = true;
                } else {
                    this.mDialog = null;
                }
                this.mCreatingDialog = false;
            } catch (Throwable th) {
                this.mCreatingDialog = false;
                throw th;
            }
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.d(TAG, "get layout inflater for DialogFragment " + this + " from dialog context");
        }
        Dialog dialog = this.mDialog;
        return dialog != null ? layoutInflaterOnGetLayoutInflater.cloneInContext(dialog.getContext()) : layoutInflaterOnGetLayoutInflater;
    }

    public boolean onHasView() {
        return this.mDialogCreated;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            Bundle bundleOnSaveInstanceState = dialog.onSaveInstanceState();
            bundleOnSaveInstanceState.putBoolean(SAVED_INTERNAL_DIALOG_SHOWING, false);
            bundle.putBundle(SAVED_DIALOG_STATE_TAG, bundleOnSaveInstanceState);
        }
        int i5 = this.mStyle;
        if (i5 != 0) {
            bundle.putInt(SAVED_STYLE, i5);
        }
        int i7 = this.mTheme;
        if (i7 != 0) {
            bundle.putInt(SAVED_THEME, i7);
        }
        boolean z9 = this.mCancelable;
        if (!z9) {
            bundle.putBoolean(SAVED_CANCELABLE, z9);
        }
        boolean z10 = this.mShowsDialog;
        if (!z10) {
            bundle.putBoolean(SAVED_SHOWS_DIALOG, z10);
        }
        int i9 = this.mBackStackId;
        if (i9 != -1) {
            bundle.putInt(SAVED_BACK_STACK_ID, i9);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = false;
            dialog.show();
            View decorView = this.mDialog.getWindow().getDecorView();
            androidx.lifecycle.K.h(decorView, this);
            decorView.setTag(R.id.view_tree_view_model_store_owner, this);
            android.support.v4.media.session.f.V(decorView, this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        Bundle bundle2;
        super.onViewStateRestored(bundle);
        if (this.mDialog == null || bundle == null || (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) == null) {
            return;
        }
        this.mDialog.onRestoreInstanceState(bundle2);
    }

    @Override // androidx.fragment.app.Fragment
    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.performCreateView(layoutInflater, viewGroup, bundle);
        if (this.mView != null || this.mDialog == null || bundle == null || (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) == null) {
            return;
        }
        this.mDialog.onRestoreInstanceState(bundle2);
    }

    public final androidx.activity.m requireComponentDialog() {
        Dialog dialogRequireDialog = requireDialog();
        if (dialogRequireDialog instanceof androidx.activity.m) {
            return (androidx.activity.m) dialogRequireDialog;
        }
        throw new IllegalStateException("DialogFragment " + this + " did not return a ComponentDialog instance from requireDialog(). The actual Dialog is " + dialogRequireDialog);
    }

    public final Dialog requireDialog() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            return dialog;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void setCancelable(boolean z9) {
        this.mCancelable = z9;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setCancelable(z9);
        }
    }

    public void setShowsDialog(boolean z9) {
        this.mShowsDialog = z9;
    }

    public void setStyle(int i5, int i7) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.d(TAG, "Setting style and theme for DialogFragment " + this + " to " + i5 + ", " + i7);
        }
        this.mStyle = i5;
        if (i5 == 2 || i5 == 3) {
            this.mTheme = android.R.style.Theme.Panel;
        }
        if (i7 != 0) {
            this.mTheme = i7;
        }
    }

    public void setupDialog(Dialog dialog, int i5) {
        if (i5 != 1 && i5 != 2) {
            if (i5 != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void show(Y y4, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        y4.getClass();
        C0234a c0234a = new C0234a(y4);
        c0234a.f7681p = true;
        c0234a.d(0, this, str, 1);
        c0234a.i(false);
    }

    public void showNow(Y y4, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        y4.getClass();
        C0234a c0234a = new C0234a(y4);
        c0234a.f7681p = true;
        c0234a.d(0, this, str, 1);
        if (c0234a.f7674g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        c0234a.h = false;
        c0234a.f7589q.y(c0234a, false);
    }

    public int show(j0 j0Var, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        j0Var.d(0, this, str, 1);
        this.mViewDestroyed = false;
        int i5 = ((C0234a) j0Var).i(false);
        this.mBackStackId = i5;
        return i5;
    }

    public DialogFragment(int i5) {
        super(i5);
        this.mDismissRunnable = new RunnableC0247n(0, this);
        this.mOnCancelListener = new DialogInterfaceOnCancelListenerC0248o(this);
        this.mOnDismissListener = new DialogInterfaceOnDismissListenerC0249p(this);
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
        this.mObserver = new C0250q(this);
        this.mDialogCreated = false;
    }
}
