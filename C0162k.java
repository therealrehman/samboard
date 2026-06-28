package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.appcompat.widget.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0162k extends FrameLayout implements InterfaceC0174o {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ViewGroup f6728e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextView f6729f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final View f6730g;
    public CharSequence h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f6731i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ C0171n f6732j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0162k(C0171n c0171n, Context context) {
        super(context);
        this.f6732j = c0171n;
        View c0165l = c0171n.f6776u ? new C0165l(c0171n, context) : new C0159j(c0171n, context);
        this.f6730g = c0165l;
        addView(c0165l, new FrameLayout.LayoutParams(-2, -2));
        Resources resources = getResources();
        if (c0165l instanceof C0159j) {
            this.h = c0165l.getContentDescription();
            this.f6731i = ((Object) this.h) + " , " + resources.getString(R.string.sesl_action_menu_overflow_badge_description);
        }
        if (TextUtils.isEmpty(this.h)) {
            CharSequence string = resources.getString(R.string.sesl_action_menu_overflow_description);
            this.h = string;
            c0165l.setContentDescription(string);
        }
        ViewGroup viewGroup = (ViewGroup) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.sesl_action_menu_item_badge, (ViewGroup) this, false);
        this.f6728e = viewGroup;
        this.f6729f = (TextView) viewGroup.getChildAt(0);
        addView(viewGroup);
    }

    @Override // androidx.appcompat.widget.InterfaceC0174o
    public final boolean a() {
        return false;
    }

    @Override // androidx.appcompat.widget.InterfaceC0174o
    public final boolean b() {
        return false;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        float dimension;
        super.onConfigurationChanged(configuration);
        Resources resources = getResources();
        float dimension2 = (int) resources.getDimension(R.dimen.sesl_menu_item_badge_text_size);
        TextView textView = this.f6729f;
        textView.setTextSize(0, dimension2);
        ViewGroup viewGroup = this.f6728e;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
        CharSequence text = textView.getText();
        if (text == null || text.toString() == null) {
            float dimension3 = resources.getDimension(R.dimen.sesl_badge_default_width);
            if (text != null) {
                dimension = resources.getDimension(R.dimen.sesl_badge_additional_width) * text.length();
            } else {
                dimension = 0.0f;
            }
            marginLayoutParams.width = (int) (dimension3 + dimension);
            marginLayoutParams.height = (int) (resources.getDimension(R.dimen.sesl_badge_additional_width) + resources.getDimension(R.dimen.sesl_badge_default_width));
            marginLayoutParams.topMargin = (int) getResources().getDimension(R.dimen.sesl_menu_item_number_badge_top_margin);
            marginLayoutParams.setMarginEnd((int) resources.getDimension(R.dimen.sesl_menu_item_number_badge_end_margin));
        } else {
            marginLayoutParams.width = (int) resources.getDimension(R.dimen.sesl_menu_item_badge_size);
            marginLayoutParams.height = (int) resources.getDimension(R.dimen.sesl_menu_item_badge_size);
        }
        viewGroup.setLayoutParams(marginLayoutParams);
        View view = this.f6730g;
        boolean z9 = view instanceof C0159j;
        if (z9) {
            this.h = getContentDescription();
            this.f6731i = ((Object) this.h) + " , " + resources.getString(R.string.sesl_action_menu_overflow_badge_description);
        }
        if (TextUtils.isEmpty(this.h)) {
            this.h = resources.getString(R.string.sesl_action_menu_overflow_description);
            this.f6731i = ((Object) this.h) + " , " + resources.getString(R.string.sesl_action_menu_overflow_badge_description);
        }
        if (viewGroup.getVisibility() == 0) {
            if (z9) {
                view.setContentDescription(this.f6731i);
            }
        } else if (z9) {
            view.setContentDescription(this.h);
        }
    }
}
