package com.google.android.material.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import androidx.core.view.M;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import g.C0544l;
import g.DialogInterfaceC0545m;
import java.util.WeakHashMap;
import l.C0663e;

/* JADX INFO: loaded from: classes.dex */
public class MaterialAlertDialogBuilder extends C0544l {
    private static final int DEF_STYLE_ATTR = 2130968627;
    private static final int DEF_STYLE_RES = R.style.MaterialAlertDialog_MaterialComponents;
    private static final int MATERIAL_ALERT_DIALOG_THEME_OVERLAY = R.attr.materialAlertDialogTheme;
    private Drawable background;
    private final Rect backgroundInsets;

    public MaterialAlertDialogBuilder(Context context) {
        this(context, 0);
    }

    private static Context createMaterialAlertDialogThemedContext(Context context) {
        int materialAlertDialogThemeOverlay = getMaterialAlertDialogThemeOverlay(context);
        Context contextWrap = MaterialThemeOverlay.wrap(context, null, DEF_STYLE_ATTR, DEF_STYLE_RES);
        return materialAlertDialogThemeOverlay == 0 ? contextWrap : new C0663e(contextWrap, materialAlertDialogThemeOverlay);
    }

    private static int getMaterialAlertDialogThemeOverlay(Context context) {
        TypedValue typedValueResolve = MaterialAttributes.resolve(context, MATERIAL_ALERT_DIALOG_THEME_OVERLAY);
        if (typedValueResolve == null) {
            return 0;
        }
        return typedValueResolve.data;
    }

    private static int getOverridingThemeResId(Context context, int i5) {
        return i5 == 0 ? getMaterialAlertDialogThemeOverlay(context) : i5;
    }

    @Override // g.C0544l
    public DialogInterfaceC0545m create() {
        DialogInterfaceC0545m dialogInterfaceC0545mCreate = super.create();
        Window window = dialogInterfaceC0545mCreate.getWindow();
        View decorView = window.getDecorView();
        Drawable drawable = this.background;
        if (drawable instanceof MaterialShapeDrawable) {
            WeakHashMap weakHashMap = W.f7199a;
            ((MaterialShapeDrawable) drawable).setElevation(M.i(decorView));
        }
        window.setBackgroundDrawable(MaterialDialogs.insetDrawable(this.background, this.backgroundInsets));
        decorView.setOnTouchListener(new InsetDialogOnTouchListener(dialogInterfaceC0545mCreate, this.backgroundInsets));
        return dialogInterfaceC0545mCreate;
    }

    public Drawable getBackground() {
        return this.background;
    }

    public MaterialAlertDialogBuilder setBackground(Drawable drawable) {
        this.background = drawable;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetBottom(int i5) {
        this.backgroundInsets.bottom = i5;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetEnd(int i5) {
        if (getContext().getResources().getConfiguration().getLayoutDirection() == 1) {
            this.backgroundInsets.left = i5;
        } else {
            this.backgroundInsets.right = i5;
        }
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetStart(int i5) {
        if (getContext().getResources().getConfiguration().getLayoutDirection() == 1) {
            this.backgroundInsets.right = i5;
        } else {
            this.backgroundInsets.left = i5;
        }
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetTop(int i5) {
        this.backgroundInsets.top = i5;
        return this;
    }

    public MaterialAlertDialogBuilder(Context context, int i5) {
        super(createMaterialAlertDialogThemedContext(context), getOverridingThemeResId(context, i5));
        Context context2 = getContext();
        Resources.Theme theme = context2.getTheme();
        int i7 = DEF_STYLE_ATTR;
        int i9 = DEF_STYLE_RES;
        this.backgroundInsets = MaterialDialogs.getDialogBackgroundInsets(context2, i7, i9);
        int color = MaterialColors.getColor(context2, R.attr.colorSurface, getClass().getCanonicalName());
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(null, R.styleable.MaterialAlertDialog, i7, i9);
        int color2 = typedArrayObtainStyledAttributes.getColor(R.styleable.MaterialAlertDialog_backgroundTint, color);
        typedArrayObtainStyledAttributes.recycle();
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context2, null, i7, i9);
        materialShapeDrawable.initializeElevationOverlay(context2);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(color2));
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(android.R.attr.dialogCornerRadius, typedValue, true);
        float dimension = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
        if (typedValue.type == 5 && dimension >= 0.0f) {
            materialShapeDrawable.setCornerSize(dimension);
        }
        this.background = materialShapeDrawable;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
        super.setAdapter(listAdapter, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setCancelable(boolean z9) {
        super.setCancelable(z9);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
        super.setCursor(cursor, onClickListener, str);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setCustomTitle(View view) {
        super.setCustomTitle(view);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setIconAttribute(int i5) {
        super.setIconAttribute(i5);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setNegativeButtonIcon(Drawable drawable) {
        super.setNegativeButtonIcon(drawable);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setNeutralButtonIcon(Drawable drawable) {
        super.setNeutralButtonIcon(drawable);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        super.setOnCancelListener(onCancelListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        super.setOnDismissListener(onDismissListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        super.setOnKeyListener(onKeyListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setPositiveButtonIcon(Drawable drawable) {
        super.setPositiveButtonIcon(drawable);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setIcon(int i5) {
        super.setIcon(i5);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setItems(int i5, DialogInterface.OnClickListener onClickListener) {
        super.setItems(i5, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setMessage(int i5) {
        super.setMessage(i5);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setNegativeButton(int i5, DialogInterface.OnClickListener onClickListener) {
        super.setNegativeButton(i5, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setNeutralButton(int i5, DialogInterface.OnClickListener onClickListener) {
        super.setNeutralButton(i5, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setPositiveButton(int i5, DialogInterface.OnClickListener onClickListener) {
        super.setPositiveButton(i5, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setTitle(int i5) {
        super.setTitle(i5);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setView(int i5) {
        super.setView(i5);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setIcon(Drawable drawable) {
        super.setIcon(drawable);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
        super.setItems(charSequenceArr, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setMessage(CharSequence charSequence) {
        super.setMessage(charSequence);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setMultiChoiceItems(int i5, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
        super.setMultiChoiceItems(i5, zArr, onMultiChoiceClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        super.setNegativeButton(charSequence, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        super.setNeutralButton(charSequence, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        super.setPositiveButton(charSequence, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setView(View view) {
        super.setView(view);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
        super.setMultiChoiceItems(charSequenceArr, zArr, onMultiChoiceClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setSingleChoiceItems(int i5, int i7, DialogInterface.OnClickListener onClickListener) {
        super.setSingleChoiceItems(i5, i7, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setMultiChoiceItems(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
        super.setMultiChoiceItems(cursor, str, str2, onMultiChoiceClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setSingleChoiceItems(Cursor cursor, int i5, String str, DialogInterface.OnClickListener onClickListener) {
        super.setSingleChoiceItems(cursor, i5, str, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setSingleChoiceItems(CharSequence[] charSequenceArr, int i5, DialogInterface.OnClickListener onClickListener) {
        super.setSingleChoiceItems(charSequenceArr, i5, onClickListener);
        return this;
    }

    @Override // g.C0544l
    public MaterialAlertDialogBuilder setSingleChoiceItems(ListAdapter listAdapter, int i5, DialogInterface.OnClickListener onClickListener) {
        super.setSingleChoiceItems(listAdapter, i5, onClickListener);
        return this;
    }
}
