package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SeslSwitchBar extends LinearLayout implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f6622e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final SeslToggleSwitch f6623f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final SeslProgressBar f6624g;
    public final TextView h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f6625i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f6626j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f6627k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f6628l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f6629m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f6630n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final LinearLayout f6631p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final I1 f6632q;

    public SeslSwitchBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seslSwitchBarStyle, 0);
        this.f6622e = new ArrayList();
        LayoutInflater.from(context).inflate(R.layout.sesl_switchbar, this);
        Resources resources = getResources();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0478a.f10579z, R.attr.seslSwitchBarStyle, 0);
        this.f6626j = typedArrayObtainStyledAttributes.getColor(1, resources.getColor(R.color.sesl_switchbar_off_background_color_light));
        this.f6627k = typedArrayObtainStyledAttributes.getColor(0, resources.getColor(R.color.sesl_switchbar_on_background_color_light));
        this.f6628l = typedArrayObtainStyledAttributes.getColor(2, resources.getColor(R.color.sesl_switchbar_on_text_color_light));
        this.f6629m = typedArrayObtainStyledAttributes.getColor(3, resources.getColor(R.color.sesl_switchbar_on_text_color_light));
        typedArrayObtainStyledAttributes.recycle();
        this.f6624g = (SeslProgressBar) findViewById(R.id.sesl_switchbar_progress);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.sesl_switchbar_container);
        this.f6631p = linearLayout;
        linearLayout.setOnClickListener(new ViewOnClickListenerC0135b(1, this));
        this.f6630n = R.string.sesl_switchbar_on_text;
        this.o = R.string.sesl_switchbar_off_text;
        TextView textView = (TextView) findViewById(R.id.sesl_switchbar_text);
        this.h = textView;
        ((ViewGroup.MarginLayoutParams) textView.getLayoutParams()).setMarginStart((int) resources.getDimension(R.dimen.sesl_switchbar_margin_start));
        SeslToggleSwitch seslToggleSwitch = (SeslToggleSwitch) findViewById(R.id.sesl_switchbar_switch);
        this.f6623f = seslToggleSwitch;
        seslToggleSwitch.setSaveEnabled(false);
        seslToggleSwitch.setFocusable(false);
        seslToggleSwitch.setClickable(false);
        seslToggleSwitch.setOnCheckedChangeListener(this);
        int i5 = this.f6630n;
        int i7 = this.o;
        this.f6630n = i5;
        this.o = i7;
        setTextViewLabelAndBackground(seslToggleSwitch.isChecked());
        a(new F1(this));
        ((ViewGroup.MarginLayoutParams) seslToggleSwitch.getLayoutParams()).setMarginEnd((int) resources.getDimension(R.dimen.sesl_switchbar_margin_end));
        I1 i12 = new I1();
        i12.f6455a = "";
        i12.f6457c = (TextView) findViewById(R.id.sesl_switchbar_text);
        i12.f6456b = (SeslToggleSwitch) findViewById(R.id.sesl_switchbar_switch);
        this.f6632q = i12;
        androidx.core.view.W.i(linearLayout, i12);
        setSessionDescription(getActivityTitle());
    }

    private String getActivityTitle() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                CharSequence title = ((Activity) context).getTitle();
                return title != null ? title.toString() : "";
            }
        }
        return "";
    }

    public final void a(G1 g12) {
        ArrayList arrayList = this.f6622e;
        if (arrayList.contains(g12)) {
            throw new IllegalStateException("Cannot add twice the same OnSwitchChangeListener");
        }
        arrayList.add(g12);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeslSwitchBar.class.getName();
    }

    public final SeslToggleSwitch getSwitch() {
        return this.f6623f;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z9) {
        ArrayList arrayList = this.f6622e;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((G1) arrayList.get(i5)).a(this.f6623f, z9);
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        H1 h12 = (H1) parcelable;
        super.onRestoreInstanceState(h12.getSuperState());
        boolean z9 = h12.f6449e;
        SeslToggleSwitch seslToggleSwitch = this.f6623f;
        seslToggleSwitch.setCheckedInternal(z9);
        setTextViewLabelAndBackground(h12.f6449e);
        setVisibility(h12.f6450f ? 0 : 8);
        seslToggleSwitch.setOnCheckedChangeListener(h12.f6450f ? this : null);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        H1 h12 = new H1(super.onSaveInstanceState());
        h12.f6449e = this.f6623f.isChecked();
        h12.f6450f = getVisibility() == 0;
        return h12;
    }

    @Override // android.view.View
    public final boolean performClick() {
        return this.f6623f.performClick();
    }

    public void setChecked(boolean z9) {
        setTextViewLabelAndBackground(z9);
        this.f6623f.setChecked(z9);
    }

    public void setCheckedInternal(boolean z9) {
        setTextViewLabelAndBackground(z9);
        this.f6623f.setCheckedInternal(z9);
    }

    @Override // android.view.View
    public void setEnabled(boolean z9) {
        super.setEnabled(z9);
        this.h.setEnabled(z9);
        SeslToggleSwitch seslToggleSwitch = this.f6623f;
        seslToggleSwitch.setEnabled(z9);
        this.f6631p.setEnabled(z9);
        setTextViewLabelAndBackground(seslToggleSwitch.isChecked());
    }

    public void setProgressBarVisible(boolean z9) {
        try {
            this.f6624g.setVisibility(z9 ? 0 : 8);
        } catch (IndexOutOfBoundsException e3) {
            Log.i("SetProgressBarVisible", "Invalid argument" + e3);
        }
    }

    public void setSessionDescription(String str) {
        this.f6632q.f6455a = str;
    }

    public void setTextViewLabel(boolean z9) {
        String string = getResources().getString(z9 ? this.f6630n : this.o);
        this.f6625i = string;
        this.h.setText(string);
    }

    public void setTextViewLabelAndBackground(boolean z9) {
        this.f6625i = getResources().getString(z9 ? this.f6630n : this.o);
        E.a.h(this.f6631p.getBackground().mutate(), ColorStateList.valueOf(z9 ? this.f6627k : this.f6626j));
        int i5 = z9 ? this.f6628l : this.f6629m;
        TextView textView = this.h;
        textView.setTextColor(i5);
        if (isEnabled()) {
            textView.setAlpha(1.0f);
        } else if (s6.c.O(getContext()) && z9) {
            textView.setAlpha(0.55f);
        } else {
            textView.setAlpha(0.4f);
        }
        String str = this.f6625i;
        if (str == null || !str.contentEquals(textView.getText())) {
            textView.setText(this.f6625i);
        }
    }
}
