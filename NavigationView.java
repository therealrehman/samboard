package com.google.android.material.navigation;

import A8.l;
import T.a;
import T.c;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.activity.b;
import androidx.appcompat.view.menu.h;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.S1;
import androidx.core.view.W;
import androidx.core.view.w0;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeableDelegate;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Objects;
import java.util.WeakHashMap;
import l.C0668j;

/* JADX INFO: loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout implements MaterialBackHandler {
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    private final a backDrawerListener;
    private final MaterialBackOrchestrator backOrchestrator;
    private boolean bottomInsetScrimEnabled;
    private int drawerLayoutCornerSize;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private final NavigationMenuPresenter presenter;
    private final ShapeableDelegate shapeableDelegate;
    private final MaterialSideContainerBackHelper sideContainerBackHelper;
    private final int[] tmpLocation;
    private boolean topInsetScrimEnabled;
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int DEF_STYLE_RES = com.google.android.material.R.style.Widget_Design_NavigationView;

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem menuItem);
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    private ColorStateList createDefaultColorStateList(int i5) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i5, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListF = p0.a.f(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(com.samsung.android.keyscafe.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i7 = typedValue.data;
        int defaultColor = colorStateListF.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListF.getColorForState(iArr, defaultColor), i7, defaultColor});
    }

    private Drawable createDefaultItemBackground(S1 s12) {
        return createDefaultItemDrawable(s12, MaterialResources.getColorStateList(getContext(), s12, com.google.android.material.R.styleable.NavigationView_itemShapeFillColor));
    }

    private Drawable createDefaultItemDrawable(S1 s12, ColorStateList colorStateList) {
        int resourceId = s12.f6522b.getResourceId(com.google.android.material.R.styleable.NavigationView_itemShapeAppearance, 0);
        int i5 = com.google.android.material.R.styleable.NavigationView_itemShapeAppearanceOverlay;
        TypedArray typedArray = s12.f6522b;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(getContext(), resourceId, typedArray.getResourceId(i5, 0)).build());
        materialShapeDrawable.setFillColor(colorStateList);
        return new InsetDrawable((Drawable) materialShapeDrawable, typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemShapeInsetStart, 0), typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemShapeInsetTop, 0), typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemShapeInsetEnd, 0), typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new C0668j(getContext());
        }
        return this.menuInflater;
    }

    private boolean hasShapeAppearance(S1 s12) {
        if (!s12.f6522b.hasValue(com.google.android.material.R.styleable.NavigationView_itemShapeAppearance)) {
            if (!s12.f6522b.hasValue(com.google.android.material.R.styleable.NavigationView_itemShapeAppearanceOverlay)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchDraw$0(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    private void maybeUpdateCornerSizeForDrawerLayout(int i5, int i7) {
        getParent();
    }

    private Pair<c, Object> requireDrawerLayoutParent() {
        getParent();
        getLayoutParams();
        throw new IllegalStateException("NavigationView back progress requires the direct parent view to be a DrawerLayout.");
    }

    private void setupInsetScrimsListener() {
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.google.android.material.navigation.NavigationView.3
            /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onGlobalLayout() {
                /*
                    Method dump skipped, instruction units count: 203
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.AnonymousClass3.onGlobalLayout():void");
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    public void addHeaderView(View view) {
        this.presenter.addHeaderView(view);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        requireDrawerLayoutParent();
        this.sideContainerBackHelper.cancelBackProgress();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        this.shapeableDelegate.maybeClip(canvas, new A6.a(5, this));
    }

    public MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    public int getDividerInsetEnd() {
        return this.presenter.getDividerInsetEnd();
    }

    public int getDividerInsetStart() {
        return this.presenter.getDividerInsetStart();
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int i5) {
        return this.presenter.getHeaderView(i5);
    }

    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public int getItemMaxLines() {
        return this.presenter.getItemMaxLines();
    }

    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    public int getItemVerticalPadding() {
        return this.presenter.getItemVerticalPadding();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public int getSubheaderInsetEnd() {
        return this.presenter.getSubheaderInsetEnd();
    }

    public int getSubheaderInsetStart() {
        return this.presenter.getSubheaderInsetStart();
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        Pair<c, Object> pairRequireDrawerLayoutParent = requireDrawerLayoutParent();
        l.z(pairRequireDrawerLayoutParent.first);
        if (this.sideContainerBackHelper.onHandleBackInvoked() == null || Build.VERSION.SDK_INT < 34) {
            throw null;
        }
        l.z(pairRequireDrawerLayoutParent.second);
        throw null;
    }

    public View inflateHeaderView(int i5) {
        return this.presenter.inflateHeaderView(i5);
    }

    public void inflateMenu(int i5) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i5, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    public boolean isBottomInsetScrimEnabled() {
        return this.bottomInsetScrimEnabled;
    }

    public boolean isTopInsetScrimEnabled() {
        return this.topInsetScrimEnabled;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        getParent();
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        getParent();
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public void onInsetsChanged(w0 w0Var) {
        this.presenter.dispatchApplyWindowInsets(w0Var);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i5, int i7) {
        int mode = View.MeasureSpec.getMode(i5);
        if (mode == Integer.MIN_VALUE) {
            i5 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i5), this.maxWidth), 1073741824);
        } else if (mode == 0) {
            i5 = View.MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
        }
        super.onMeasure(i5, i7);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuState);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.menuState = bundle;
        this.menu.savePresenterStates(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i5, int i7, int i9, int i10) {
        super.onSizeChanged(i5, i7, i9, i10);
        maybeUpdateCornerSizeForDrawerLayout(i5, i7);
    }

    public void removeHeaderView(View view) {
        this.presenter.removeHeaderView(view);
    }

    public void setBottomInsetScrimEnabled(boolean z9) {
        this.bottomInsetScrimEnabled = z9;
    }

    public void setCheckedItem(int i5) {
        MenuItem menuItemFindItem = this.menu.findItem(i5);
        if (menuItemFindItem != null) {
            this.presenter.setCheckedItem((androidx.appcompat.view.menu.l) menuItemFindItem);
        }
    }

    public void setDividerInsetEnd(int i5) {
        this.presenter.setDividerInsetEnd(i5);
    }

    public void setDividerInsetStart(int i5) {
        this.presenter.setDividerInsetStart(i5);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.setElevation(this, f2);
    }

    public void setForceCompatClippingEnabled(boolean z9) {
        this.shapeableDelegate.setForceCompatClippingEnabled(this, z9);
    }

    public void setItemBackground(Drawable drawable) {
        this.presenter.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i5) {
        setItemBackground(B.a.b(getContext(), i5));
    }

    public void setItemHorizontalPadding(int i5) {
        this.presenter.setItemHorizontalPadding(i5);
    }

    public void setItemHorizontalPaddingResource(int i5) {
        this.presenter.setItemHorizontalPadding(getResources().getDimensionPixelSize(i5));
    }

    public void setItemIconPadding(int i5) {
        this.presenter.setItemIconPadding(i5);
    }

    public void setItemIconPaddingResource(int i5) {
        this.presenter.setItemIconPadding(getResources().getDimensionPixelSize(i5));
    }

    public void setItemIconSize(int i5) {
        this.presenter.setItemIconSize(i5);
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.presenter.setItemIconTintList(colorStateList);
    }

    public void setItemMaxLines(int i5) {
        this.presenter.setItemMaxLines(i5);
    }

    public void setItemTextAppearance(int i5) {
        this.presenter.setItemTextAppearance(i5);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z9) {
        this.presenter.setItemTextAppearanceActiveBoldEnabled(z9);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.presenter.setItemTextColor(colorStateList);
    }

    public void setItemVerticalPadding(int i5) {
        this.presenter.setItemVerticalPadding(i5);
    }

    public void setItemVerticalPaddingResource(int i5) {
        this.presenter.setItemVerticalPadding(getResources().getDimensionPixelSize(i5));
    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.listener = onNavigationItemSelectedListener;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i5) {
        super.setOverScrollMode(i5);
        NavigationMenuPresenter navigationMenuPresenter = this.presenter;
        if (navigationMenuPresenter != null) {
            navigationMenuPresenter.setOverScrollMode(i5);
        }
    }

    public void setSubheaderInsetEnd(int i5) {
        this.presenter.setSubheaderInsetEnd(i5);
    }

    public void setSubheaderInsetStart(int i5) {
        this.presenter.setSubheaderInsetStart(i5);
    }

    public void setTopInsetScrimEnabled(boolean z9) {
        this.topInsetScrimEnabled = z9;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(b bVar) {
        requireDrawerLayoutParent();
        this.sideContainerBackHelper.startBackProgress(bVar);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(b bVar) {
        l.z(requireDrawerLayoutParent().second);
        throw null;
    }

    public static class SavedState extends R.c {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.navigation.NavigationView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i5) {
                return new SavedState[i5];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public Bundle menuState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        @Override // R.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i5) {
            super.writeToParcel(parcel, i5);
            parcel.writeBundle(this.menuState);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.material.R.attr.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NavigationView(Context context, AttributeSet attributeSet, int i5) {
        ColorStateList colorStateListCreateDefaultColorStateList;
        int i7;
        int i9;
        Drawable drawable;
        int i10 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(context, attributeSet, i5, i10), attributeSet, i5);
        NavigationMenuPresenter navigationMenuPresenter = new NavigationMenuPresenter();
        this.presenter = navigationMenuPresenter;
        this.tmpLocation = new int[2];
        this.topInsetScrimEnabled = true;
        this.bottomInsetScrimEnabled = true;
        this.drawerLayoutCornerSize = 0;
        this.shapeableDelegate = ShapeableDelegate.create(this);
        this.sideContainerBackHelper = new MaterialSideContainerBackHelper(this);
        this.backOrchestrator = new MaterialBackOrchestrator(this);
        this.backDrawerListener = new T.b() { // from class: com.google.android.material.navigation.NavigationView.1
            public void onDrawerClosed(View view) {
                NavigationView navigationView = NavigationView.this;
                if (view == navigationView) {
                    navigationView.backOrchestrator.stopListeningForBackCallbacks();
                }
            }

            public void onDrawerOpened(View view) {
                NavigationView navigationView = NavigationView.this;
                if (view == navigationView) {
                    MaterialBackOrchestrator materialBackOrchestrator = navigationView.backOrchestrator;
                    Objects.requireNonNull(materialBackOrchestrator);
                    view.post(new C6.a(10, materialBackOrchestrator));
                }
            }
        };
        Context context2 = getContext();
        NavigationMenu navigationMenu = new NavigationMenu(context2);
        this.menu = navigationMenu;
        S1 s1ObtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, com.google.android.material.R.styleable.NavigationView, i5, i10, new int[0]);
        int i11 = com.google.android.material.R.styleable.NavigationView_android_background;
        if (s1ObtainTintedStyledAttributes.f6522b.hasValue(i11)) {
            Drawable drawableB = s1ObtainTintedStyledAttributes.b(i11);
            WeakHashMap weakHashMap = W.f7199a;
            setBackground(drawableB);
        }
        int i12 = com.google.android.material.R.styleable.NavigationView_drawerLayoutCornerSize;
        TypedArray typedArray = s1ObtainTintedStyledAttributes.f6522b;
        this.drawerLayoutCornerSize = typedArray.getDimensionPixelSize(i12, 0);
        Drawable background = getBackground();
        ColorStateList colorStateListOrNull = DrawableUtils.getColorStateListOrNull(background);
        if (background == null || colorStateListOrNull != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(context2, attributeSet, i5, i10).build());
            if (colorStateListOrNull != null) {
                materialShapeDrawable.setFillColor(colorStateListOrNull);
            }
            materialShapeDrawable.initializeElevationOverlay(context2);
            WeakHashMap weakHashMap2 = W.f7199a;
            setBackground(materialShapeDrawable);
        }
        if (typedArray.hasValue(com.google.android.material.R.styleable.NavigationView_elevation)) {
            setElevation(typedArray.getDimensionPixelSize(r2, 0));
        }
        setFitsSystemWindows(typedArray.getBoolean(com.google.android.material.R.styleable.NavigationView_android_fitsSystemWindows, false));
        this.maxWidth = typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_android_maxWidth, 0);
        int i13 = com.google.android.material.R.styleable.NavigationView_subheaderColor;
        ColorStateList colorStateListA = typedArray.hasValue(i13) ? s1ObtainTintedStyledAttributes.a(i13) : null;
        int i14 = com.google.android.material.R.styleable.NavigationView_subheaderTextAppearance;
        int resourceId = typedArray.hasValue(i14) ? typedArray.getResourceId(i14, 0) : 0;
        if (resourceId == 0 && colorStateListA == null) {
            colorStateListA = createDefaultColorStateList(R.attr.textColorSecondary);
        }
        int i15 = com.google.android.material.R.styleable.NavigationView_itemIconTint;
        if (typedArray.hasValue(i15)) {
            colorStateListCreateDefaultColorStateList = s1ObtainTintedStyledAttributes.a(i15);
        } else {
            colorStateListCreateDefaultColorStateList = createDefaultColorStateList(R.attr.textColorSecondary);
        }
        int i16 = com.google.android.material.R.styleable.NavigationView_itemTextAppearance;
        int resourceId2 = typedArray.hasValue(i16) ? typedArray.getResourceId(i16, 0) : 0;
        boolean z9 = typedArray.getBoolean(com.google.android.material.R.styleable.NavigationView_itemTextAppearanceActiveBoldEnabled, true);
        int i17 = com.google.android.material.R.styleable.NavigationView_itemIconSize;
        if (typedArray.hasValue(i17)) {
            setItemIconSize(typedArray.getDimensionPixelSize(i17, 0));
        }
        int i18 = com.google.android.material.R.styleable.NavigationView_itemTextColor;
        ColorStateList colorStateListA2 = typedArray.hasValue(i18) ? s1ObtainTintedStyledAttributes.a(i18) : null;
        if (resourceId2 == 0 && colorStateListA2 == null) {
            colorStateListA2 = createDefaultColorStateList(R.attr.textColorPrimary);
        }
        Drawable drawableB2 = s1ObtainTintedStyledAttributes.b(com.google.android.material.R.styleable.NavigationView_itemBackground);
        if (drawableB2 == null && hasShapeAppearance(s1ObtainTintedStyledAttributes)) {
            Drawable drawableCreateDefaultItemBackground = createDefaultItemBackground(s1ObtainTintedStyledAttributes);
            ColorStateList colorStateList = MaterialResources.getColorStateList(context2, s1ObtainTintedStyledAttributes, com.google.android.material.R.styleable.NavigationView_itemRippleColor);
            if (colorStateList != null) {
                drawable = drawableCreateDefaultItemBackground;
                navigationMenuPresenter.setItemForeground(new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(colorStateList), null, createDefaultItemDrawable(s1ObtainTintedStyledAttributes, null)));
            } else {
                drawable = drawableCreateDefaultItemBackground;
            }
            drawableB2 = drawable;
        }
        int i19 = com.google.android.material.R.styleable.NavigationView_itemHorizontalPadding;
        if (typedArray.hasValue(i19)) {
            i7 = 0;
            setItemHorizontalPadding(typedArray.getDimensionPixelSize(i19, 0));
        } else {
            i7 = 0;
        }
        int i20 = com.google.android.material.R.styleable.NavigationView_itemVerticalPadding;
        if (typedArray.hasValue(i20)) {
            setItemVerticalPadding(typedArray.getDimensionPixelSize(i20, i7));
        }
        setDividerInsetStart(typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_dividerInsetStart, i7));
        setDividerInsetEnd(typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_dividerInsetEnd, i7));
        setSubheaderInsetStart(typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_subheaderInsetStart, i7));
        setSubheaderInsetEnd(typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_subheaderInsetEnd, i7));
        setTopInsetScrimEnabled(typedArray.getBoolean(com.google.android.material.R.styleable.NavigationView_topInsetScrimEnabled, this.topInsetScrimEnabled));
        setBottomInsetScrimEnabled(typedArray.getBoolean(com.google.android.material.R.styleable.NavigationView_bottomInsetScrimEnabled, this.bottomInsetScrimEnabled));
        int dimensionPixelSize = typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemIconPadding, 0);
        setItemMaxLines(typedArray.getInt(com.google.android.material.R.styleable.NavigationView_itemMaxLines, 1));
        navigationMenu.setCallback(new h() { // from class: com.google.android.material.navigation.NavigationView.2
            @Override // androidx.appcompat.view.menu.h
            public boolean onMenuItemSelected(j jVar, MenuItem menuItem) {
                OnNavigationItemSelectedListener onNavigationItemSelectedListener = NavigationView.this.listener;
                return onNavigationItemSelectedListener != null && onNavigationItemSelectedListener.onNavigationItemSelected(menuItem);
            }

            @Override // androidx.appcompat.view.menu.h
            public void onMenuModeChange(j jVar) {
            }
        });
        navigationMenuPresenter.setId(1);
        navigationMenuPresenter.initForMenu(context2, navigationMenu);
        if (resourceId != 0) {
            navigationMenuPresenter.setSubheaderTextAppearance(resourceId);
        }
        navigationMenuPresenter.setSubheaderColor(colorStateListA);
        navigationMenuPresenter.setItemIconTintList(colorStateListCreateDefaultColorStateList);
        navigationMenuPresenter.setOverScrollMode(getOverScrollMode());
        if (resourceId2 != 0) {
            navigationMenuPresenter.setItemTextAppearance(resourceId2);
        }
        navigationMenuPresenter.setItemTextAppearanceActiveBoldEnabled(z9);
        navigationMenuPresenter.setItemTextColor(colorStateListA2);
        navigationMenuPresenter.setItemBackground(drawableB2);
        navigationMenuPresenter.setItemIconPadding(dimensionPixelSize);
        navigationMenu.addMenuPresenter(navigationMenuPresenter);
        addView((View) navigationMenuPresenter.getMenuView(this));
        int i21 = com.google.android.material.R.styleable.NavigationView_menu;
        if (typedArray.hasValue(i21)) {
            i9 = 0;
            inflateMenu(typedArray.getResourceId(i21, 0));
        } else {
            i9 = 0;
        }
        int i22 = com.google.android.material.R.styleable.NavigationView_headerLayout;
        if (typedArray.hasValue(i22)) {
            inflateHeaderView(typedArray.getResourceId(i22, i9));
        }
        s1ObtainTintedStyledAttributes.f();
        setupInsetScrimsListener();
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.menu.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.presenter.setCheckedItem((androidx.appcompat.view.menu.l) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
