package com.google.android.material.navigation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import androidx.appcompat.view.menu.AbstractC0131d;
import androidx.appcompat.view.menu.D;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.u;
import androidx.appcompat.view.menu.v;
import androidx.appcompat.view.menu.x;
import androidx.appcompat.view.menu.y;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.internal.ParcelableSparseArray;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class NavigationBarPresenter extends AbstractC0131d {
    private static final int ANIM_UPDATE_DELAY = 180;
    private static final int ANIM_UPDATE_DURATION = 400;
    private static final int MSG_UPDATE_ANIMATION = 100;
    private int id;
    private Handler mAnimationHandler;
    private Context mContext;
    private OverflowPopup mOverflowPopup;
    private final PopupPresenterCallback mPopupPresenterCallback;
    private OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mSetAnim;
    private j menu;
    private NavigationBarMenuView menuView;
    private boolean updateSuspended;

    public class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;

        @Override // java.lang.Runnable
        public void run() {
            if (NavigationBarPresenter.this.menu != null) {
                NavigationBarPresenter.this.menu.changeMenuMode();
            }
            if (NavigationBarPresenter.this.menuView != null && NavigationBarPresenter.this.menuView.getWindowToken() != null && this.mPopup.tryShow(0, 0)) {
                NavigationBarPresenter.this.mOverflowPopup = this.mPopup;
            }
            NavigationBarPresenter.this.mPostedOpenRunnable = null;
        }

        private OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.mPopup = overflowPopup;
        }
    }

    public class OverflowPopup extends u {
        @Override // androidx.appcompat.view.menu.u
        public void onDismiss() {
            if (NavigationBarPresenter.this.menu != null) {
                NavigationBarPresenter.this.menu.close();
            }
            NavigationBarPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }

        private OverflowPopup(Context context, j jVar, View view, boolean z9) {
            super(R.attr.actionOverflowBottomMenuStyle, 0, context, view, jVar, z9);
            setGravity(8388613);
            setPresenterCallback(NavigationBarPresenter.this.mPopupPresenterCallback);
            setAnchorView(view);
            seslSetOverlapAnchor(false);
            seslForceShowUpper(true);
        }
    }

    public class PopupPresenterCallback implements v {
        public PopupPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.v
        public void onCloseMenu(j jVar, boolean z9) {
            if (jVar instanceof D) {
                jVar.getRootMenu().close(false);
            }
            v callback = NavigationBarPresenter.this.getCallback();
            if (callback != null) {
                callback.onCloseMenu(jVar, z9);
            }
        }

        @Override // androidx.appcompat.view.menu.v
        public boolean onOpenSubMenu(j jVar) {
            v callback;
            return (jVar == null || (callback = NavigationBarPresenter.this.getCallback()) == null || !callback.onOpenSubMenu(jVar)) ? false : true;
        }
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.material.navigation.NavigationBarPresenter.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i5) {
                return new SavedState[i5];
            }
        };
        ParcelableSparseArray badgeSavedStates;
        int selectedItemId;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i5) {
            parcel.writeInt(this.selectedItemId);
            parcel.writeParcelable(this.badgeSavedStates, 0);
        }

        public SavedState(Parcel parcel) {
            this.selectedItemId = parcel.readInt();
            this.badgeSavedStates = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    public NavigationBarPresenter(Context context) {
        super(context);
        this.updateSuspended = false;
        this.mSetAnim = false;
        this.mAnimationHandler = new Handler(Looper.getMainLooper()) { // from class: com.google.android.material.navigation.NavigationBarPresenter.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 100) {
                    NavigationBarPresenter.this.updateMenuViewWithAnimate();
                }
            }
        };
        this.mPopupPresenterCallback = new PopupPresenterCallback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMenuViewWithAnimate() {
        if (this.menuView == null) {
            return;
        }
        final PathInterpolator pathInterpolator = new PathInterpolator(0.33f, 0.0f, 0.1f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.menuView, "y", r1.getHeight());
        objectAnimatorOfFloat.setDuration(400L);
        objectAnimatorOfFloat.setInterpolator(pathInterpolator);
        objectAnimatorOfFloat.start();
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.navigation.NavigationBarPresenter.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                NavigationBarPresenter.this.menuView.buildMenuView();
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(NavigationBarPresenter.this.menuView, "y", 0.0f);
                objectAnimatorOfFloat2.setDuration(400L);
                objectAnimatorOfFloat2.setInterpolator(pathInterpolator);
                objectAnimatorOfFloat2.start();
                super.onAnimationEnd(animator);
            }
        });
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d
    public void bindItemView(l lVar, x xVar) {
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public boolean collapseItemActionView(j jVar, l lVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public boolean expandItemActionView(j jVar, l lVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.w
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public int getId() {
        return this.id;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d
    public y getMenuView(ViewGroup viewGroup) {
        return this.menuView;
    }

    public boolean hideOverflowMenu() {
        Object obj;
        OpenOverflowRunnable openOverflowRunnable = this.mPostedOpenRunnable;
        if (openOverflowRunnable != null && (obj = this.mMenuView) != null) {
            ((View) obj).removeCallbacks(openOverflowRunnable);
            this.mPostedOpenRunnable = null;
            return true;
        }
        OverflowPopup overflowPopup = this.mOverflowPopup;
        if (overflowPopup == null) {
            return false;
        }
        overflowPopup.dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public void initForMenu(Context context, j jVar) {
        this.menu = jVar;
        this.menuView.initialize(jVar);
        this.mContext = context;
    }

    public boolean isOverflowMenuShowing() {
        OverflowPopup overflowPopup = this.mOverflowPopup;
        return overflowPopup != null && overflowPopup.isShowing();
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public void onCloseMenu(j jVar, boolean z9) {
    }

    @Override // androidx.appcompat.view.menu.w
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.menuView.tryRestoreSelectedItemId(savedState.selectedItemId);
            this.menuView.restoreBadgeDrawables(BadgeUtils.createBadgeDrawablesFromSavedStates(this.menuView.getContext(), savedState.badgeSavedStates));
        }
    }

    @Override // androidx.appcompat.view.menu.w
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.selectedItemId = this.menuView.getSelectedItemId();
        savedState.badgeSavedStates = BadgeUtils.createParcelableBadgeStates(this.menuView.getBadgeDrawables());
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public boolean onSubMenuSelected(D d8) {
        return false;
    }

    public void setAnimationEnable(boolean z9) {
        this.mSetAnim = z9;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public void setCallback(v vVar) {
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d
    public void setId(int i5) {
        this.id = i5;
    }

    public void setMenuView(NavigationBarMenuView navigationBarMenuView) {
        this.menuView = navigationBarMenuView;
    }

    public void setUpdateSuspended(boolean z9) {
        this.updateSuspended = z9;
    }

    public boolean showOverflowMenu(j jVar) {
        if (isOverflowMenuShowing() || jVar == null || this.menuView == null || this.mPostedOpenRunnable != null || jVar.getNonActionItems().isEmpty()) {
            return false;
        }
        OverflowPopup overflowPopup = new OverflowPopup(this.mContext, jVar, this.menuView.mOverflowButton, true);
        this.mOverflowPopup = overflowPopup;
        OpenOverflowRunnable openOverflowRunnable = new OpenOverflowRunnable(overflowPopup);
        this.mPostedOpenRunnable = openOverflowRunnable;
        this.menuView.post(openOverflowRunnable);
        super.onSubMenuSelected(null);
        return true;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public void updateMenuView(boolean z9) {
        if (this.updateSuspended) {
            return;
        }
        if (!this.mSetAnim) {
            if (z9) {
                this.menuView.buildMenuView();
                return;
            } else {
                this.menuView.updateMenuView();
                return;
            }
        }
        if (!z9) {
            this.menuView.postDelayed(new Runnable() { // from class: com.google.android.material.navigation.NavigationBarPresenter.2
                @Override // java.lang.Runnable
                public void run() {
                    NavigationBarPresenter.this.menuView.updateMenuView();
                }
            }, 180L);
            return;
        }
        if (this.mAnimationHandler.hasMessages(100)) {
            this.mAnimationHandler.removeMessages(100);
        }
        this.mAnimationHandler.sendEmptyMessage(100);
    }
}
