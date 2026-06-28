package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.S1;
import androidx.appcompat.widget.SeslDropDownItemTextView;
import androidx.core.view.W;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements x, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public l f6209e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ImageView f6210f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public RadioButton f6211g;
    public TextView h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CheckBox f6212i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public TextView f6213j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ImageView f6214k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ImageView f6215l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public LinearLayout f6216m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Drawable f6217n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Context f6218p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6219q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Drawable f6220r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f6221s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public LayoutInflater f6222t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f6223u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final NumberFormat f6224v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public TextView f6225w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f6226x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public SeslDropDownItemTextView f6227y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public LinearLayout f6228z;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6226x = false;
        S1 s1E = S1.e(getContext(), attributeSet, AbstractC0478a.f10572s, R.attr.listMenuViewStyle, 0);
        this.f6217n = s1E.b(5);
        TypedArray typedArray = s1E.f6522b;
        this.o = typedArray.getResourceId(1, -1);
        this.f6219q = typedArray.getBoolean(7, false);
        this.f6218p = context;
        this.f6220r = s1E.b(8);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{android.R.attr.divider}, R.attr.dropDownListViewStyle, 0);
        this.f6221s = typedArrayObtainStyledAttributes.hasValue(0);
        s1E.f();
        typedArrayObtainStyledAttributes.recycle();
        this.f6224v = NumberFormat.getInstance(Locale.getDefault());
    }

    private LayoutInflater getInflater() {
        if (this.f6222t == null) {
            this.f6222t = LayoutInflater.from(getContext());
        }
        return this.f6222t;
    }

    private void setBadgeText(String str) {
        if (this.f6225w == null) {
            this.f6225w = (TextView) findViewById(R.id.menu_badge);
        }
        if (this.f6225w == null) {
            Log.i("ListMenuItemView", "SUB_MENU_ITEM_LAYOUT case, mBadgeView is null");
            return;
        }
        if (this.f6228z == null) {
            Log.i("ListMenuItemView", "mTitleParent is null");
            return;
        }
        Resources resources = getResources();
        float dimension = resources.getDimension(R.dimen.sesl_badge_additional_width);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f6225w.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f6228z.getLayoutParams();
        if (str == null) {
            layoutParams.topMargin = (int) resources.getDimension(R.dimen.sesl_list_menu_item_dot_badge_top_margin);
            layoutParams2.width = -2;
            this.f6228z.setLayoutParams(layoutParams2);
            this.f6225w.setLayoutParams(layoutParams);
        } else {
            try {
                Integer.parseInt(str);
                String str2 = this.f6224v.format(Math.min(Integer.parseInt(str), 99));
                int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sesl_menu_item_badge_text_size);
                TextView textView = this.f6225w;
                float f2 = getResources().getConfiguration().fontScale;
                if (f2 > 1.2f) {
                    textView.setTextSize(0, (dimensionPixelSize / f2) * 1.2f);
                }
                this.f6225w.setText(str2);
                int length = (int) ((str2.length() * dimension) + resources.getDimension(R.dimen.sesl_badge_default_width));
                int dimension2 = (int) (resources.getDimension(R.dimen.sesl_badge_default_width) + dimension);
                layoutParams.width = length;
                layoutParams.height = dimension2;
                layoutParams.addRule(15, -1);
                this.f6225w.setLayoutParams(layoutParams);
            } catch (NumberFormatException unused) {
                layoutParams.topMargin = (int) resources.getDimension(R.dimen.sesl_list_menu_item_dot_badge_top_margin);
                layoutParams2.width = -2;
                this.f6228z.setLayoutParams(layoutParams2);
                this.f6225w.setLayoutParams(layoutParams);
            }
        }
        int i5 = layoutParams.width;
        if (str != null) {
            this.f6228z.setPaddingRelative(0, 0, getResources().getDimensionPixelSize(R.dimen.sesl_list_menu_item_dot_badge_end_margin) + i5, 0);
        }
        this.f6225w.setVisibility(str == null ? 8 : 0);
    }

    private void setSubMenuArrowVisible(boolean z9) {
        ImageView imageView = this.f6214k;
        if (imageView == null || this.f6226x) {
            return;
        }
        imageView.setVisibility(z9 ? 0 : 8);
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f6215l;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f6215l.getLayoutParams();
        rect.top = this.f6215l.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
    }

    @Override // androidx.appcompat.view.menu.x
    public l getItemData() {
        return this.f6209e;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012f  */
    @Override // androidx.appcompat.view.menu.x
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void initialize(androidx.appcompat.view.menu.l r10, int r11) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuItemView.initialize(androidx.appcompat.view.menu.l, int):void");
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        WeakHashMap weakHashMap = W.f7199a;
        setBackground(this.f6217n);
        SeslDropDownItemTextView seslDropDownItemTextView = (SeslDropDownItemTextView) findViewById(R.id.sub_menu_title);
        this.f6227y = seslDropDownItemTextView;
        boolean z9 = seslDropDownItemTextView != null;
        this.f6226x = z9;
        if (z9) {
            return;
        }
        TextView textView = (TextView) findViewById(R.id.title);
        this.h = textView;
        int i5 = this.o;
        if (i5 != -1) {
            textView.setTextAppearance(this.f6218p, i5);
        }
        TextView textView2 = this.h;
        if (textView2 != null) {
            textView2.setSingleLine(false);
            this.h.setMaxLines(2);
        }
        this.f6213j = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.f6214k = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f6220r);
        }
        this.f6215l = (ImageView) findViewById(R.id.group_divider);
        this.f6216m = (LinearLayout) findViewById(R.id.content);
        this.f6228z = (LinearLayout) findViewById(R.id.title_parent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        TextView textView = this.f6225w;
        if (textView == null || textView.getVisibility() != 0 || this.f6225w.getWidth() <= 0) {
            return;
        }
        CharSequence charSequence = this.f6209e.f6273i;
        if (!TextUtils.isEmpty(getContentDescription())) {
            accessibilityNodeInfo.setContentDescription(getContentDescription());
            return;
        }
        accessibilityNodeInfo.setContentDescription(((Object) charSequence) + " , " + getResources().getString(R.string.sesl_action_menu_overflow_badge_description));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i5, int i7) {
        if (this.f6210f != null && this.f6219q && !this.f6226x) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f6210f.getLayoutParams();
            int i9 = layoutParams.height;
            if (i9 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i9;
            }
        }
        super.onMeasure(i5, i7);
    }

    public void setCheckable(boolean z9) {
        CompoundButton compoundButton;
        View view;
        if (!z9 && this.f6211g == null && this.f6212i == null) {
            return;
        }
        if (this.f6226x) {
            if (z9) {
                this.f6227y.setChecked(this.f6209e.isChecked());
                return;
            }
            return;
        }
        if (this.f6209e.h()) {
            if (this.f6211g == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.sesl_list_menu_item_radio, (ViewGroup) this, false);
                this.f6211g = radioButton;
                LinearLayout linearLayout = this.f6216m;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f6211g;
            view = this.f6212i;
        } else {
            if (this.f6212i == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.sesl_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f6212i = checkBox;
                LinearLayout linearLayout2 = this.f6216m;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f6212i;
            view = this.f6211g;
        }
        if (z9) {
            compoundButton.setChecked(this.f6209e.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox2 = this.f6212i;
        if (checkBox2 != null) {
            checkBox2.setVisibility(8);
        }
        RadioButton radioButton2 = this.f6211g;
        if (radioButton2 != null) {
            radioButton2.setVisibility(8);
        }
    }

    public void setChecked(boolean z9) {
        CompoundButton compoundButton;
        if (this.f6226x) {
            this.f6227y.setChecked(z9);
            return;
        }
        if (this.f6209e.h()) {
            if (this.f6211g == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.sesl_list_menu_item_radio, (ViewGroup) this, false);
                this.f6211g = radioButton;
                LinearLayout linearLayout = this.f6216m;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f6211g;
        } else {
            if (this.f6212i == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.sesl_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f6212i = checkBox;
                LinearLayout linearLayout2 = this.f6216m;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f6212i;
        }
        compoundButton.setChecked(z9);
    }

    public void setForceShowIcon(boolean z9) {
        this.f6223u = z9;
        this.f6219q = z9;
    }

    public void setGroupDividerEnabled(boolean z9) {
        ImageView imageView = this.f6215l;
        if (imageView != null) {
            imageView.setVisibility((this.f6221s || !z9) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.f6226x) {
            return;
        }
        boolean z9 = this.f6209e.f6281r.getOptionalIconsVisible() || this.f6223u;
        if (z9 || this.f6219q) {
            ImageView imageView = this.f6210f;
            if (imageView == null && drawable == null && !this.f6219q) {
                return;
            }
            if (imageView == null && !this.f6226x) {
                ImageView imageView2 = (ImageView) getInflater().inflate(R.layout.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.f6210f = imageView2;
                LinearLayout linearLayout = this.f6216m;
                if (linearLayout != null) {
                    linearLayout.addView(imageView2, 0);
                } else {
                    addView(imageView2, 0);
                }
            }
            if (drawable == null && !this.f6219q) {
                this.f6210f.setVisibility(8);
                return;
            }
            ImageView imageView3 = this.f6210f;
            if (!z9) {
                drawable = null;
            }
            imageView3.setImageDrawable(drawable);
            if (this.f6210f.getVisibility() != 0) {
                this.f6210f.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (this.f6226x) {
            if (charSequence == null) {
                if (this.f6227y.getVisibility() != 8) {
                    this.f6227y.setVisibility(8);
                    return;
                }
                return;
            } else {
                this.f6227y.setText(charSequence);
                if (this.f6227y.getVisibility() != 0) {
                    this.f6227y.setVisibility(0);
                    return;
                }
                return;
            }
        }
        if (charSequence == null) {
            if (this.h.getVisibility() != 8) {
                this.h.setVisibility(8);
            }
        } else {
            this.h.setText(charSequence);
            if (this.h.getVisibility() != 0) {
                this.h.setVisibility(0);
            }
        }
    }
}
