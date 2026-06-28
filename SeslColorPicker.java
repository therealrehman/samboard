package androidx.picker3.widget;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import androidx.appcompat.util.SeslShapeDrawable;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.timepicker.TimeModel;
import com.samsung.android.keyscafe.R;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SeslColorPicker extends LinearLayout {

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public static int f8464W = 6;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public SeslColorSpectrumView f8465A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final LinearLayout f8466B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final o f8467C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final ArrayList f8468D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final ArrayList f8469E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final String[] f8470F;
    public final EditText G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final EditText f8471H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final EditText f8472I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final EditText f8473J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final EditText f8474K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final EditText f8475L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public EditText f8476M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f8477N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f8478O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f8479P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f8480Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f8481R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f8482S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public boolean f8483T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final h f8484U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final e f8485V;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f8486e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Resources f8487f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C.d f8488g;
    public boolean h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f8489i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f8490j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public l f8491k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f8492l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ImageView f8493m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ImageView f8494n;
    public final FrameLayout o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public FrameLayout f8495p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final LinearLayout f8496q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final TabLayout f8497r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final GradientDrawable f8498s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final GradientDrawable f8499t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final HorizontalScrollView f8500u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final AppCompatImageView f8501v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public SeslOpacitySeekBar f8502w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public FrameLayout f8503x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final SeslGradientColorSeekBar f8504y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final SeslColorSwatchView f8505z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeslColorPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i5 = 15;
        int[] iArr = {320, 360, 411};
        this.h = false;
        this.f8490j = false;
        this.f8469E = new ArrayList();
        this.f8470F = null;
        this.f8478O = false;
        this.f8479P = false;
        this.f8480Q = false;
        this.f8481R = false;
        this.f8482S = false;
        this.f8483T = false;
        this.f8484U = new h(this);
        this.f8485V = new e(this);
        this.f8486e = context;
        Resources resources = getResources();
        this.f8487f = resources;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.isLightTheme, typedValue, true);
        this.f8489i = typedValue.data != 0;
        LayoutInflater.from(context).inflate(R.layout.sesl_color_picker_oneui_3_layout, this);
        this.f8500u = (HorizontalScrollView) findViewById(R.id.horizontal_scroll_view);
        this.f8501v = (AppCompatImageView) findViewById(R.id.sesl_eye_dropper);
        o oVar = new o();
        oVar.f8591a = null;
        oVar.f8592b = null;
        oVar.f8593c = null;
        ArrayList arrayList = new ArrayList();
        oVar.f8594d = arrayList;
        this.f8467C = oVar;
        this.f8468D = arrayList;
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sesl_color_picker_tab_layout);
        this.f8497r = tabLayout;
        tabLayout.seslSetSubTabStyle();
        TabLayout.Tab tabAt = tabLayout.getTabAt(0);
        if (tabAt != null) {
            tabAt.select();
        }
        C.d dVar = new C.d();
        dVar.f299b = null;
        dVar.f298a = 255;
        dVar.f300c = new float[3];
        this.f8488g = dVar;
        if (resources.getConfiguration().orientation == 1) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            float f2 = displayMetrics.density;
            if (f2 % 1.0f != 0.0f) {
                float f7 = displayMetrics.widthPixels;
                int i7 = (int) (f7 / f2);
                int i9 = 0;
                while (true) {
                    if (i9 >= 3) {
                        break;
                    }
                    if (iArr[i9] == i7) {
                        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sesl_color_picker_seekbar_width);
                        if (f7 < (resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_dialog_padding_left) * 2) + dimensionPixelSize) {
                            int i10 = (int) ((f7 - dimensionPixelSize) / 2.0f);
                            ((LinearLayout) findViewById(R.id.sesl_color_picker_main_content_container)).setPadding(i10, resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_dialog_padding_top), i10, resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_dialog_padding_bottom));
                        }
                    } else {
                        i9++;
                    }
                }
            }
        }
        this.f8493m = (ImageView) findViewById(R.id.sesl_color_picker_current_color_view);
        this.f8494n = (ImageView) findViewById(R.id.sesl_color_picker_picked_color_view);
        this.f8471H = (EditText) findViewById(R.id.sesl_color_seek_bar_opacity_value_edit_view);
        this.G = (EditText) findViewById(R.id.sesl_color_seek_bar_saturation_value_edit_view);
        this.f8471H.setPrivateImeOptions("disableDirectWriting=true;");
        this.G.setPrivateImeOptions("disableDirectWriting=true;");
        this.f8471H.setTag(1);
        this.f8477N = true;
        GradientDrawable gradientDrawable = (GradientDrawable) this.f8494n.getBackground();
        this.f8498s = gradientDrawable;
        Integer num = (Integer) this.f8488g.f299b;
        if (num != null) {
            gradientDrawable.setColor(num.intValue());
        }
        this.f8499t = (GradientDrawable) this.f8493m.getBackground();
        this.f8497r.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this.f8484U);
        this.f8471H.addTextChangedListener(new f(this, 0));
        this.f8471H.setOnFocusChangeListener(new g(this, 0));
        this.f8471H.setOnEditorActionListener(new c(this, 1));
        this.f8505z = (SeslColorSwatchView) findViewById(R.id.sesl_color_picker_color_swatch_view);
        this.o = (FrameLayout) findViewById(R.id.sesl_color_picker_color_swatch_view_container);
        this.f8505z.f8532f = new i(this);
        this.f8496q = (LinearLayout) findViewById(R.id.sesl_color_picker_saturation_layout);
        this.f8504y = (SeslGradientColorSeekBar) findViewById(R.id.sesl_color_picker_saturation_seekbar);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.sesl_color_picker_saturation_seekbar_container);
        SeslGradientColorSeekBar seslGradientColorSeekBar = this.f8504y;
        Integer num2 = (Integer) this.f8488g.f299b;
        seslGradientColorSeekBar.setMax(100);
        if (num2 != null) {
            seslGradientColorSeekBar.a(num2.intValue());
        }
        seslGradientColorSeekBar.setProgressDrawable(seslGradientColorSeekBar.f8551e);
        seslGradientColorSeekBar.setThumb(seslGradientColorSeekBar.getContext().getDrawable(R.drawable.sesl_color_picker_seekbar_cursor));
        seslGradientColorSeekBar.setThumbOffset(0);
        this.f8504y.setOnSeekBarChangeListener(new j(this, 0));
        this.f8504y.setOnTouchListener(new a(this, 0));
        StringBuilder sb = new StringBuilder();
        Resources resources2 = this.f8487f;
        sb.append(resources2.getString(R.string.sesl_color_picker_hue_and_saturation));
        sb.append(", ");
        sb.append(resources2.getString(R.string.sesl_color_picker_slider));
        sb.append(", ");
        sb.append(resources2.getString(R.string.sesl_color_picker_double_tap_to_select));
        frameLayout.setContentDescription(sb.toString());
        a();
        b(false);
        this.f8466B = (LinearLayout) findViewById(R.id.sesl_color_picker_used_color_item_list_layout);
        Resources resources3 = this.f8487f;
        this.f8470F = new String[]{resources3.getString(R.string.sesl_color_picker_color_one), resources3.getString(R.string.sesl_color_picker_color_two), resources3.getString(R.string.sesl_color_picker_color_three), resources3.getString(R.string.sesl_color_picker_color_four), resources3.getString(R.string.sesl_color_picker_color_five), resources3.getString(R.string.sesl_color_picker_color_six), resources3.getString(R.string.sesl_color_picker_color_seven)};
        int i11 = this.f8489i ? R.color.sesl_color_picker_used_color_item_empty_slot_color_light : R.color.sesl_color_picker_used_color_item_empty_slot_color_dark;
        Context context2 = this.f8486e;
        int iA = B.b.a(context2, i11);
        if (resources3.getConfiguration().orientation != 2 || (context2.getResources().getConfiguration().screenLayout & 15) >= 3) {
            f8464W = 6;
        } else {
            f8464W = 7;
        }
        for (int i12 = 0; i12 < f8464W; i12++) {
            View childAt = this.f8466B.getChildAt(i12);
            e(childAt, Integer.valueOf(iA));
            childAt.setFocusable(false);
            childAt.setClickable(false);
        }
        AppCompatImageView appCompatImageView = this.f8501v;
        appCompatImageView.setFocusable(true);
        appCompatImageView.setClickable(true);
        appCompatImageView.setTooltipText(getResources().getString(R.string.sesl_color_picker_eye_dropper));
        appCompatImageView.setContentDescription(getResources().getString(R.string.sesl_color_picker_eye_dropper));
        SeslShapeDrawable seslShapeDrawable = new SeslShapeDrawable();
        Context context3 = this.f8486e;
        seslShapeDrawable.setColor(B.b.a(context3, R.color.sesl_color_picker_transparent));
        seslShapeDrawable.setShape(1);
        appCompatImageView.setBackground(new SeslRecoilDrawable(B.b.a(context3, this.f8489i ? R.color.sesl_ripple_color_light : R.color.sesl_ripple_color_dark), new Drawable[]{seslShapeDrawable}, null));
        appCompatImageView.setOnClickListener(new B5.c(i5, this));
        f();
        Integer num3 = (Integer) this.f8488g.f299b;
        if (num3 != null) {
            c(num3.intValue());
        }
        this.f8472I = (EditText) findViewById(R.id.sesl_color_hex_edit_text);
        this.f8473J = (EditText) findViewById(R.id.sesl_color_red_edit_text);
        this.f8475L = (EditText) findViewById(R.id.sesl_color_blue_edit_text);
        this.f8474K = (EditText) findViewById(R.id.sesl_color_green_edit_text);
        this.f8473J.setPrivateImeOptions("disableDirectWriting=true;");
        this.f8475L.setPrivateImeOptions("disableDirectWriting=true;");
        this.f8474K.setPrivateImeOptions("disableDirectWriting=true;");
        EditText editText = this.f8473J;
        ArrayList<EditText> arrayList2 = this.f8469E;
        arrayList2.add(editText);
        arrayList2.add(this.f8474K);
        arrayList2.add(this.f8475L);
        arrayList2.add(this.f8472I);
        this.f8472I.addTextChangedListener(new f(this, 2));
        this.f8492l = "";
        for (int i13 = 0; i13 < arrayList2.size() - 1; i13++) {
            EditText editText2 = (EditText) arrayList2.get(i13);
            editText2.addTextChangedListener(new d(this, editText2));
        }
        for (EditText editText3 : arrayList2) {
            editText3.setOnFocusChangeListener(new b(this, editText3));
        }
        this.f8475L.setOnEditorActionListener(new c(this, 0));
    }

    public final void a() {
        this.f8465A = (SeslColorSpectrumView) findViewById(R.id.sesl_color_picker_color_spectrum_view);
        this.f8495p = (FrameLayout) findViewById(R.id.sesl_color_picker_color_spectrum_view_container);
        this.G.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(this.f8504y.getProgress())));
        this.f8465A.f8519r = new i(this);
        this.G.addTextChangedListener(new f(this, 1));
        this.G.setOnFocusChangeListener(new g(this, 1));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void b(boolean z9) {
        this.f8502w = (SeslOpacitySeekBar) findViewById(R.id.sesl_color_picker_opacity_seekbar);
        this.f8503x = (FrameLayout) findViewById(R.id.sesl_color_picker_opacity_seekbar_container);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.sesl_color_picker_opacity_layout);
        if (z9) {
            linearLayout.setVisibility(0);
            this.f8502w.setVisibility(0);
            this.f8503x.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
            this.f8502w.setVisibility(8);
            this.f8503x.setVisibility(8);
        }
        SeslOpacitySeekBar seslOpacitySeekBar = this.f8502w;
        Integer num = (Integer) this.f8488g.f299b;
        seslOpacitySeekBar.setMax(255);
        if (num != null) {
            seslOpacitySeekBar.b(num.intValue());
        }
        GradientDrawable gradientDrawable = (GradientDrawable) seslOpacitySeekBar.getContext().getDrawable(R.drawable.sesl_color_picker_opacity_seekbar_shape);
        seslOpacitySeekBar.f8553e = gradientDrawable;
        seslOpacitySeekBar.setProgressDrawable(gradientDrawable);
        seslOpacitySeekBar.setThumb(seslOpacitySeekBar.getContext().getResources().getDrawable(R.drawable.sesl_color_picker_seekbar_cursor));
        seslOpacitySeekBar.setThumbOffset(0);
        this.f8502w.setOnSeekBarChangeListener(new j(this, 1));
        this.f8502w.setOnTouchListener(new a(this, 1));
        FrameLayout frameLayout = this.f8503x;
        StringBuilder sb = new StringBuilder();
        Resources resources = this.f8487f;
        sb.append(resources.getString(R.string.sesl_color_picker_opacity));
        sb.append(", ");
        sb.append(resources.getString(R.string.sesl_color_picker_slider));
        sb.append(", ");
        sb.append(resources.getString(R.string.sesl_color_picker_double_tap_to_select));
        frameLayout.setContentDescription(sb.toString());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void c(int i5) {
        GradientDrawable gradientDrawable;
        C.d dVar = this.f8488g;
        dVar.f299b = Integer.valueOf(i5);
        dVar.f298a = Color.alpha(i5);
        Color.colorToHSV(((Integer) dVar.f299b).intValue(), (float[]) dVar.f300c);
        SeslColorSwatchView seslColorSwatchView = this.f8505z;
        if (seslColorSwatchView != null) {
            Point pointB = seslColorSwatchView.b(i5);
            if (seslColorSwatchView.f8541q) {
                seslColorSwatchView.f8538m.set(pointB.x, pointB.y);
            }
            if (seslColorSwatchView.f8541q) {
                seslColorSwatchView.f8549y = D.d.e(i5, 255);
                seslColorSwatchView.d(seslColorSwatchView.f8534i);
                seslColorSwatchView.c(seslColorSwatchView.h);
                seslColorSwatchView.invalidate();
                Point point = seslColorSwatchView.f8538m;
                seslColorSwatchView.f8539n = (point.y * 11) + point.x;
            } else {
                seslColorSwatchView.f8539n = -1;
            }
        }
        SeslColorSpectrumView seslColorSpectrumView = this.f8465A;
        if (seslColorSpectrumView != null) {
            seslColorSpectrumView.a(i5);
        }
        SeslGradientColorSeekBar seslGradientColorSeekBar = this.f8504y;
        if (seslGradientColorSeekBar != null && (gradientDrawable = seslGradientColorSeekBar.f8551e) != null) {
            seslGradientColorSeekBar.a(i5);
            gradientDrawable.setColors(seslGradientColorSeekBar.f8552f);
            seslGradientColorSeekBar.setProgressDrawable(gradientDrawable);
        }
        SeslOpacitySeekBar seslOpacitySeekBar = this.f8502w;
        if (seslOpacitySeekBar != null) {
            seslOpacitySeekBar.b(i5);
            seslOpacitySeekBar.f8553e.setColors(seslOpacitySeekBar.f8554f);
            seslOpacitySeekBar.setProgressDrawable(seslOpacitySeekBar.f8553e);
        }
        GradientDrawable gradientDrawable2 = this.f8498s;
        if (gradientDrawable2 != null) {
            gradientDrawable2.setColor(i5);
            d(i5, 1);
        }
        if (this.f8465A != null) {
            float[] fArr = (float[]) dVar.f300c;
            float f2 = fArr[2];
            int i7 = dVar.f298a;
            fArr[2] = 1.0f;
            dVar.f299b = Integer.valueOf(Color.HSVToColor(i7, fArr));
            dVar.f298a = 255;
            Integer numValueOf = Integer.valueOf(Color.HSVToColor(255, (float[]) dVar.f300c));
            dVar.f299b = numValueOf;
            this.f8465A.b(numValueOf.intValue());
            float[] fArr2 = (float[]) dVar.f300c;
            fArr2[2] = f2;
            dVar.f299b = Integer.valueOf(Color.HSVToColor(dVar.f298a, fArr2));
            dVar.f298a = i7;
            dVar.f299b = Integer.valueOf(Color.HSVToColor(i7, (float[]) dVar.f300c));
        }
        if (this.f8502w != null) {
            int iCeil = (int) Math.ceil((r7.getProgress() * 100) / 255.0f);
            this.f8471H.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(iCeil)));
            this.f8471H.setSelection(String.valueOf(iCeil).length());
        }
    }

    public final void d(int i5, int i7) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        SeslColorSwatchView seslColorSwatchView = this.f8505z;
        if (seslColorSwatchView != null) {
            sb2 = seslColorSwatchView.a(i5);
        }
        if (sb2 != null) {
            sb.append(", ");
            sb.append((CharSequence) sb2);
        }
        Resources resources = this.f8487f;
        if (i7 == 0) {
            sb.insert(0, resources.getString(R.string.sesl_color_picker_current));
        } else {
            if (i7 != 1) {
                return;
            }
            sb.insert(0, resources.getString(R.string.sesl_color_picker_new));
        }
    }

    public final void e(View view, Integer num) {
        GradientDrawable gradientDrawable = (GradientDrawable) this.f8486e.getDrawable(this.f8489i ? R.drawable.sesl_color_picker_used_color_item_slot_light : R.drawable.sesl_color_picker_used_color_item_slot_dark);
        gradientDrawable.setColor(num.intValue());
        SeslRecoilDrawable seslRecoilDrawable = new SeslRecoilDrawable(Color.argb(61, 0, 0, 0), new Drawable[]{gradientDrawable}, null);
        view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getContext(), R.animator.sesl_recoil_button_selector));
        view.setBackground(seslRecoilDrawable);
        view.setOnClickListener(this.f8485V);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void f() {
        C.d dVar = this.f8488g;
        Integer num = (Integer) dVar.f299b;
        if (num != null) {
            SeslOpacitySeekBar seslOpacitySeekBar = this.f8502w;
            if (seslOpacitySeekBar != null) {
                seslOpacitySeekBar.a(num.intValue(), dVar.f298a);
                int progress = this.f8502w.getProgress();
                this.f8471H.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(progress)));
                this.f8471H.setSelection(String.valueOf(progress).length());
            }
            GradientDrawable gradientDrawable = this.f8498s;
            if (gradientDrawable != null) {
                gradientDrawable.setColor(num.intValue());
                d(num.intValue(), 1);
            }
            SeslColorSpectrumView seslColorSpectrumView = this.f8465A;
            if (seslColorSpectrumView != null) {
                seslColorSpectrumView.b(num.intValue());
                this.f8465A.a(num.intValue());
            }
            SeslGradientColorSeekBar seslGradientColorSeekBar = this.f8504y;
            if (seslGradientColorSeekBar != null) {
                int progress2 = seslGradientColorSeekBar.getProgress();
                SeslGradientColorSeekBar seslGradientColorSeekBar2 = this.f8504y;
                int iIntValue = num.intValue();
                GradientDrawable gradientDrawable2 = seslGradientColorSeekBar2.f8551e;
                if (gradientDrawable2 != null) {
                    int iE = D.d.e(iIntValue, 255);
                    boolean zEquals = String.format("%08x", Integer.valueOf(iE)).substring(2).equals(seslGradientColorSeekBar2.getResources().getString(R.string.sesl_color_black_000000));
                    int[] iArr = seslGradientColorSeekBar2.f8552f;
                    if (zEquals) {
                        iArr[1] = Color.parseColor("#" + seslGradientColorSeekBar2.getResources().getString(R.string.sesl_color_white_ffffff));
                    } else {
                        iArr[1] = iE;
                    }
                    gradientDrawable2.setColors(iArr);
                    seslGradientColorSeekBar2.setProgressDrawable(gradientDrawable2);
                    float[] fArr = {0.0f, 0.0f, 1.0f};
                    Color.colorToHSV(iE, fArr);
                    float f2 = fArr[2];
                    iArr[1] = Color.HSVToColor(fArr);
                    seslGradientColorSeekBar2.setProgress(Math.round(f2 * seslGradientColorSeekBar2.getMax()));
                }
                this.f8480Q = true;
                this.G.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(progress2)));
                this.G.setSelection(String.valueOf(progress2).length());
                this.f8480Q = false;
            }
        }
    }

    public final void g(int i5) {
        if (i5 != 0) {
            String str = String.format("%08x", Integer.valueOf(i5));
            String strSubstring = str.substring(2, str.length());
            this.f8472I.setText("" + strSubstring.toUpperCase());
            EditText editText = this.f8472I;
            editText.setSelection(editText.getText().length());
            int color = Color.parseColor("#".concat(strSubstring));
            this.f8473J.setText("" + Color.red(color));
            this.f8475L.setText("" + Color.blue(color));
            this.f8474K.setText("" + Color.green(color));
        }
    }

    public o getRecentColorInfo() {
        return this.f8467C;
    }

    public final void h() {
        ArrayList arrayList = this.f8468D;
        int size = arrayList != null ? arrayList.size() : 0;
        StringBuilder sb = new StringBuilder(", ");
        Resources resources = this.f8487f;
        sb.append(resources.getString(R.string.sesl_color_picker_option));
        String string = sb.toString();
        if (resources.getConfiguration().orientation == 2) {
            f8464W = 7;
        } else {
            f8464W = 6;
        }
        for (int i5 = 0; i5 < f8464W; i5++) {
            View childAt = this.f8466B.getChildAt(i5);
            if (i5 < size) {
                Integer num = (Integer) arrayList.get(i5);
                int iIntValue = num.intValue();
                e(childAt, num);
                StringBuilder sb2 = new StringBuilder();
                sb2.append((CharSequence) this.f8505z.a(iIntValue));
                sb2.insert(0, this.f8470F[i5] + string + ", ");
                childAt.setContentDescription(sb2);
                childAt.setFocusable(true);
                childAt.setClickable(true);
            }
        }
        o oVar = this.f8467C;
        Integer num2 = oVar.f8592b;
        if (num2 != null) {
            int iIntValue2 = num2.intValue();
            this.f8499t.setColor(iIntValue2);
            d(iIntValue2, 0);
            this.f8498s.setColor(iIntValue2);
            c(iIntValue2);
            g(this.f8499t.getColor().getDefaultColor());
        } else if (size != 0) {
            int iIntValue3 = ((Integer) arrayList.get(0)).intValue();
            this.f8499t.setColor(iIntValue3);
            d(iIntValue3, 0);
            this.f8498s.setColor(iIntValue3);
            c(iIntValue3);
            g(this.f8499t.getColor().getDefaultColor());
        }
        Integer num3 = oVar.f8593c;
        if (num3 != null) {
            int iIntValue4 = num3.intValue();
            this.f8498s.setColor(iIntValue4);
            c(iIntValue4);
            g(this.f8498s.getColor().getDefaultColor());
        }
    }

    public void setEyeDropperDisable(boolean z9) {
        View viewFindViewById = findViewById(R.id.sesl_last_used_color_slot);
        AppCompatImageView appCompatImageView = this.f8501v;
        if (z9) {
            appCompatImageView.setVisibility(8);
            viewFindViewById.setVisibility(0);
        } else {
            appCompatImageView.setVisibility(0);
            viewFindViewById.setVisibility(8);
        }
    }

    public void setOnColorChangedListener(k kVar) {
    }

    public void setOnEyeDropperListener(l lVar) {
        this.f8491k = lVar;
    }

    public void setOpacityBarEnabled(boolean z9) {
        if (z9) {
            this.f8502w.setVisibility(0);
            this.f8503x.setVisibility(0);
        }
    }
}
