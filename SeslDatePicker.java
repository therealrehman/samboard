package androidx.picker.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;
import androidx.viewpager.widget.ViewPager;
import e0.AbstractC0479a;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SeslDatePicker extends LinearLayout implements T, View.OnClickListener, View.OnLongClickListener, U {

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public static final PathInterpolator f8124u0 = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f8125A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public int f8126B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f8127C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final int f8128D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f8129E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f8130F;
    public int G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f8131H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final int f8132I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f8133J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f8134K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final int f8135L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f8136M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f8137N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f8138O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f8139P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final String f8140Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final r f8141R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final ViewPager f8142S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final RelativeLayout f8143T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final TextView f8144U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final LinearLayout f8145V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final C0299t f8146W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final ViewAnimator f8147a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final SeslDatePickerSpinnerLayout f8148b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final LinearLayout f8149c0;
    public final RelativeLayout d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b0 f8150e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final LinearLayout f8151e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f8152f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public SimpleDateFormat f8153f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Locale f8154g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final ImageButton f8155g0;
    public boolean h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final ImageButton f8156h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8157i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final View f8158i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f8159j;
    public final View j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f8160k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final View f8161k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f8162l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final ObjectAnimator f8163l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f8164m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final ObjectAnimator f8165m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f8166n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public boolean f8167n0;
    public final Calendar o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public FrameLayout f8168o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Calendar f8169p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public Window f8170p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Calendar f8171q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public int f8172q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Calendar f8173r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f8174r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Calendar f8175s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final HandlerC0295o f8176s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Calendar f8177t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final ViewOnClickListenerC0291k f8178t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Calendar f8179u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f8180v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f8181w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f8182x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f8183y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f8184z;

    public SeslDatePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.datePickerStyle, 0);
        this.f8157i = false;
        this.f8160k = true;
        this.f8164m = true;
        this.f8180v = -1;
        this.f8127C = -1;
        this.f8128D = 0;
        this.f8135L = -1;
        this.f8138O = 0;
        this.f8139P = 0;
        this.f8140Q = null;
        this.f8167n0 = false;
        ViewOnFocusChangeListenerC0294n viewOnFocusChangeListenerC0294n = new ViewOnFocusChangeListenerC0294n(0, this);
        this.f8176s0 = new HandlerC0295o(this, Looper.getMainLooper(), 0);
        ViewOnTouchListenerC0296p viewOnTouchListenerC0296p = new ViewOnTouchListenerC0296p(0, this);
        A a10 = new A(1, this);
        ViewOnClickListenerC0291k viewOnClickListenerC0291k = new ViewOnClickListenerC0291k(this, 1);
        this.f8178t0 = viewOnClickListenerC0291k;
        this.f8152f = context;
        this.f8154g = Locale.getDefault();
        this.f8162l = g();
        this.f8159j = "fa".equals(this.f8154g.getLanguage());
        if (h()) {
            this.f8153f0 = new SimpleDateFormat("EEEEE", this.f8154g);
        } else {
            this.f8153f0 = new SimpleDateFormat("EEE", this.f8154g);
        }
        Calendar calendarF = f(this.f8175s, this.f8154g);
        this.f8175s = calendarF;
        Calendar calendarF2 = f(this.f8177t, this.f8154g);
        this.f8177t = calendarF2;
        this.f8179u = f(calendarF2, this.f8154g);
        Calendar calendarF3 = f(this.o, this.f8154g);
        this.o = calendarF3;
        this.f8173r = f(calendarF3, this.f8154g);
        Resources resources = getResources();
        int[] iArr = AbstractC0479a.f10580a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.datePickerStyle, 0);
        calendarF.set(typedArrayObtainStyledAttributes.getInt(0, 1902), 0, 1);
        calendarF2.set(typedArrayObtainStyledAttributes.getInt(1, 2100), 11, 31);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(com.samsung.android.keyscafe.R.layout.sesl_date_picker, (ViewGroup) this, true);
        int i5 = typedArrayObtainStyledAttributes.getInt(2, 0);
        if (i5 != 0) {
            setFirstDayOfWeek(i5);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.f8140Q = getMonthViewColorStringForSpecific();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, R.attr.datePickerStyle, 0);
        try {
            C0299t c0299t = new C0299t(this, context, typedArrayObtainStyledAttributes2);
            this.f8146W = c0299t;
            int color = typedArrayObtainStyledAttributes2.getColor(7, resources.getColor(com.samsung.android.keyscafe.R.color.sesl_date_picker_header_text_color_light));
            int color2 = typedArrayObtainStyledAttributes2.getColor(3, resources.getColor(com.samsung.android.keyscafe.R.color.sesl_date_picker_button_tint_color_light));
            typedArrayObtainStyledAttributes2.recycle();
            r rVar = new r(this);
            this.f8141R = rVar;
            ViewPager viewPager = (ViewPager) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar);
            this.f8142S = viewPager;
            viewPager.setAdapter(rVar);
            viewPager.setOnPageChangeListener(new C0297q(this));
            viewPager.seslSetSupportedMouseWheelEvent(true);
            viewPager.canSupportLayoutDirectionForDatePicker(true);
            this.f8128D = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_view_padding);
            this.f8143T = (RelativeLayout) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header);
            LinearLayout linearLayout = (LinearLayout) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_text_spinner_layout);
            this.f8151e0 = linearLayout;
            View viewFindViewById = findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_spinner);
            this.f8161k0 = viewFindViewById;
            TextView textView = (TextView) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_text);
            this.f8144U = textView;
            textView.setTextColor(color);
            this.f8169p = f(calendarF3, this.f8154g);
            this.f8171q = f(calendarF3, this.f8154g);
            this.f8147a0 = (ViewAnimator) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_view_animator);
            SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = (SeslDatePickerSpinnerLayout) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_spinner_view);
            this.f8148b0 = seslDatePickerSpinnerLayout;
            C0287g c0287g = new C0287g(this);
            if (seslDatePickerSpinnerLayout.f8195m == null) {
                seslDatePickerSpinnerLayout.f8195m = this;
            }
            seslDatePickerSpinnerLayout.f8207z = c0287g;
            this.f8180v = 0;
            linearLayout.setOnClickListener(viewOnClickListenerC0291k);
            linearLayout.setOnFocusChangeListener(new ViewOnFocusChangeListenerC0294n(1, this));
            this.f8133J = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_day_height);
            this.G = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_view_width);
            this.f8132I = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_view_margin);
            this.f8134K = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_view_width);
            LinearLayout linearLayout2 = (LinearLayout) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_day_of_the_week);
            this.f8145V = linearLayout2;
            linearLayout2.addView(c0299t);
            this.f8149c0 = (LinearLayout) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_layout);
            this.d0 = (RelativeLayout) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_layout);
            if (this.f8162l) {
                ImageButton imageButton = (ImageButton) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_next_button);
                this.f8155g0 = imageButton;
                ImageButton imageButton2 = (ImageButton) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_prev_button);
                this.f8156h0 = imageButton2;
                imageButton.setContentDescription(context.getString(com.samsung.android.keyscafe.R.string.sesl_date_picker_decrement_month));
                imageButton2.setContentDescription(context.getString(com.samsung.android.keyscafe.R.string.sesl_date_picker_increment_month));
            } else {
                this.f8155g0 = (ImageButton) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_prev_button);
                this.f8156h0 = (ImageButton) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_next_button);
            }
            this.f8155g0.setOnClickListener(this);
            this.f8156h0.setOnClickListener(this);
            this.f8155g0.setOnLongClickListener(this);
            this.f8156h0.setOnLongClickListener(this);
            this.f8155g0.setOnTouchListener(viewOnTouchListenerC0296p);
            this.f8156h0.setOnTouchListener(viewOnTouchListenerC0296p);
            this.f8155g0.setOnKeyListener(a10);
            this.f8156h0.setOnKeyListener(a10);
            this.f8155g0.setOnFocusChangeListener(viewOnFocusChangeListenerC0294n);
            this.f8156h0.setOnFocusChangeListener(viewOnFocusChangeListenerC0294n);
            this.f8155g0.setColorFilter(color2);
            this.f8156h0.setColorFilter(color2);
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackgroundBorderless, typedValue, true);
            this.f8135L = typedValue.resourceId;
            this.f8129E = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_header_height);
            this.f8130F = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_view_height);
            this.f8131H = this.G;
            linearLayout.setFocusable(true);
            this.f8155g0.setNextFocusRightId(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_text);
            this.f8156h0.setNextFocusLeftId(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_text);
            linearLayout.setNextFocusRightId(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_next_button);
            linearLayout.setNextFocusLeftId(com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_prev_button);
            this.f8158i0 = findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_between_header_and_weekend);
            this.f8181w = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_gap_between_header_and_weekend);
            this.j0 = findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_between_weekend_and_calender);
            int dimensionPixelOffset = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_gap_between_weekend_and_calender);
            this.f8182x = dimensionPixelOffset;
            this.f8183y = this.f8129E + this.f8181w + this.f8133J + dimensionPixelOffset + this.f8130F;
            n(true);
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewFindViewById, "rotation", -180.0f, 0.0f);
            this.f8163l0 = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(350L);
            PathInterpolator pathInterpolator = f8124u0;
            objectAnimatorOfFloat.setInterpolator(pathInterpolator);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(viewFindViewById, "rotation", 0.0f, -180.0f);
            this.f8165m0 = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(350L);
            objectAnimatorOfFloat2.setInterpolator(pathInterpolator);
            TypedValue typedValue2 = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.windowIsFloating, typedValue2, true);
            int i7 = typedValue2.data == 0 ? 0 : 1;
            Activity activityM = m(context);
            if (activityM != null && i7 == 0) {
                this.f8168o0 = (FrameLayout) activityM.getWindow().getDecorView().findViewById(R.id.content);
            } else if (activityM == null) {
                Log.e("SeslDatePicker", "Cannot get window of this context. context:" + context);
            }
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes2.recycle();
            throw th;
        }
    }

    public static String a(SeslDatePicker seslDatePicker, Calendar calendar) {
        if (seslDatePicker.f8159j) {
            return new SimpleDateFormat("LLLL y", seslDatePicker.f8154g).format(calendar.getTime());
        }
        int i5 = seslDatePicker.f8152f.getResources().getConfiguration().screenWidthDp;
        StringBuilder sb = new StringBuilder(50);
        Formatter formatter = new Formatter(sb, seslDatePicker.f8154g);
        sb.setLength(0);
        long timeInMillis = calendar.getTimeInMillis();
        return i5 <= 250 ? DateUtils.formatDateRange(seslDatePicker.getContext(), formatter, timeInMillis, timeInMillis, 65572, Time.getCurrentTimezone()).toString().toUpperCase() : DateUtils.formatDateRange(seslDatePicker.getContext(), formatter, timeInMillis, timeInMillis, 36, Time.getCurrentTimezone()).toString();
    }

    public static void c(SeslDatePicker seslDatePicker, float f2, boolean z9) {
        ImageButton imageButton = seslDatePicker.f8155g0;
        imageButton.setImageAlpha((int) (f2 * 255.0f));
        if (z9) {
            imageButton.setBackgroundResource(seslDatePicker.f8135L);
            imageButton.setEnabled(true);
            imageButton.setFocusable(true);
        } else {
            imageButton.setBackground(null);
            imageButton.setEnabled(false);
            imageButton.setFocusable(false);
        }
    }

    public static void d(SeslDatePicker seslDatePicker, float f2, boolean z9) {
        ImageButton imageButton = seslDatePicker.f8156h0;
        imageButton.setImageAlpha((int) (f2 * 255.0f));
        if (z9) {
            imageButton.setBackgroundResource(seslDatePicker.f8135L);
            imageButton.setEnabled(true);
            imageButton.setFocusable(true);
        } else {
            imageButton.setBackground(null);
            imageButton.setEnabled(false);
            imageButton.setFocusable(false);
        }
    }

    public static void e(Calendar calendar, int i5, int i7, int i9) {
        calendar.clear();
        calendar.set(1, i5);
        calendar.set(2, i7);
        calendar.set(5, i9);
    }

    public static Calendar f(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance(locale);
        calendar2.setTimeInMillis(timeInMillis);
        return calendar2;
    }

    private static String getCalendarPackageName() {
        Method methodU = com.bumptech.glide.c.u("com.samsung.sesl.feature.SemFloatingFeature", "hidden_getString", String.class, String.class);
        Object objC = methodU != null ? com.bumptech.glide.c.C(null, methodU, "SEC_FLOATING_FEATURE_CALENDAR_CONFIG_PACKAGE_NAME", "com.android.calendar") : null;
        String str = objC instanceof String ? (String) objC : "com.android.calendar";
        if ("com.android.calendar".equals(str)) {
            return str;
        }
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDayOffset() {
        V v4 = (V) ((SparseArray) this.f8141R.f8440b).get(this.f8136M);
        this.f8126B = v4 == null ? 1 : v4.f8223F - (v4.f8225I - 1);
        int i5 = (((this.o.get(5) % 7) + this.f8126B) - 1) % 7;
        if (i5 == 0) {
            return 7;
        }
        return i5;
    }

    private String getFormattedCurrentDate() {
        return DateUtils.formatDateTime(this.f8152f, this.o.getTimeInMillis(), 20);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getMonthViewColorStringForSpecific() {
        /*
            r7 = this;
            r0 = 0
            java.lang.String r1 = "ro.carrier"
            java.lang.String r1 = android.support.v4.media.session.f.I(r1)     // Catch: java.lang.NoClassDefFoundError -> L24
            java.lang.String r2 = "wifi-only"
            boolean r1 = r2.equalsIgnoreCase(r1)     // Catch: java.lang.NoClassDefFoundError -> L24
            java.lang.String r2 = "XXXXXBR"
            if (r1 == 0) goto L2f
            java.lang.String r7 = "persist.sys.selected_country_iso"
            java.lang.String r7 = android.support.v4.media.session.f.I(r7)     // Catch: java.lang.NoClassDefFoundError -> L24
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.NoClassDefFoundError -> L24
            if (r1 == 0) goto L26
            java.lang.String r7 = "ro.csc.countryiso_code"
            java.lang.String r7 = android.support.v4.media.session.f.I(r7)     // Catch: java.lang.NoClassDefFoundError -> L24
            goto L26
        L24:
            r7 = move-exception
            goto L78
        L26:
            java.lang.String r1 = "AE"
            boolean r7 = r1.equals(r7)     // Catch: java.lang.NoClassDefFoundError -> L24
            if (r7 == 0) goto L77
            return r2
        L2f:
            java.lang.String r1 = "XSG"
            java.lang.String r3 = "getSalesCode"
            r4 = 0
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: java.lang.NoClassDefFoundError -> L24
            java.lang.String r6 = "android.os.SemSystemProperties"
            java.lang.reflect.Method r3 = com.bumptech.glide.c.u(r6, r3, r5)     // Catch: java.lang.NoClassDefFoundError -> L24
            if (r3 == 0) goto L4b
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: java.lang.NoClassDefFoundError -> L24
            java.lang.Object r3 = com.bumptech.glide.c.C(r0, r3, r5)     // Catch: java.lang.NoClassDefFoundError -> L24
            boolean r5 = r3 instanceof java.lang.String     // Catch: java.lang.NoClassDefFoundError -> L24
            if (r5 == 0) goto L4b
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.NoClassDefFoundError -> L24
            goto L4c
        L4b:
            r3 = r0
        L4c:
            boolean r1 = r1.equals(r3)     // Catch: java.lang.NoClassDefFoundError -> L24
            if (r1 != 0) goto L53
            return r0
        L53:
            android.content.Context r7 = r7.f8152f     // Catch: java.lang.NoClassDefFoundError -> L24
            java.lang.String r1 = "phone"
            java.lang.Object r7 = r7.getSystemService(r1)     // Catch: java.lang.NoClassDefFoundError -> L24
            android.telephony.TelephonyManager r7 = (android.telephony.TelephonyManager) r7     // Catch: java.lang.NoClassDefFoundError -> L24
            java.lang.String r7 = r7.getSimOperator()     // Catch: java.lang.NoClassDefFoundError -> L24
            if (r7 == 0) goto L77
            int r1 = r7.length()     // Catch: java.lang.NoClassDefFoundError -> L24
            r3 = 3
            if (r1 <= r3) goto L77
            java.lang.String r7 = r7.substring(r4, r3)     // Catch: java.lang.NoClassDefFoundError -> L24
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.NoClassDefFoundError -> L24
            r1 = 424(0x1a8, float:5.94E-43)
            if (r7 != r1) goto L77
            return r2
        L77:
            return r0
        L78:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "msg : "
            r1.<init>(r2)
            java.lang.String r7 = r7.getMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            java.lang.String r1 = "SeslDatePicker"
            android.util.Log.e(r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslDatePicker.getMonthViewColorStringForSpecific():java.lang.String");
    }

    public static Activity m(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return m(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private void setCalendarHeaderPadding(boolean z9) {
        LinearLayout linearLayout = this.f8151e0;
        if (!z9) {
            linearLayout.setPadding(0, getPaddingTop(), 0, getPaddingBottom());
        } else {
            Context context = this.f8152f;
            linearLayout.setPadding(context.getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_header_layout_padding_left), getPaddingTop(), context.getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_header_layout_padding_right), getPaddingBottom());
        }
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(getFormattedCurrentDate());
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public final boolean g() {
        if ("ur".equals(this.f8154g.getLanguage())) {
            return false;
        }
        Locale locale = this.f8154g;
        byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        return directionality == 1 || directionality == 2;
    }

    public int getCurrentViewType() {
        return this.f8180v;
    }

    public int getDateMode() {
        return this.f8138O;
    }

    public int getDayOfMonth() {
        return this.o.get(5);
    }

    public Calendar getEndDate() {
        return this.f8171q;
    }

    public int getFirstDayOfWeek() {
        int i5 = this.f8139P;
        return i5 != 0 ? i5 : this.o.getFirstDayOfWeek();
    }

    public int[] getLunarEndDate() {
        return new int[]{0, 0, 0, 0};
    }

    public int[] getLunarStartDate() {
        return new int[]{0, 0, 0, 0};
    }

    public long getMaxDate() {
        return this.f8177t.getTimeInMillis();
    }

    public int getMaxDay() {
        return this.f8177t.get(5);
    }

    public int getMaxMonth() {
        return this.f8177t.get(2);
    }

    public int getMaxYear() {
        return this.f8177t.get(1);
    }

    public long getMinDate() {
        return this.f8175s.getTimeInMillis();
    }

    public int getMinDay() {
        return this.f8175s.get(5);
    }

    public int getMinMonth() {
        return this.f8175s.get(2);
    }

    public int getMinYear() {
        return this.f8175s.get(1);
    }

    public int getMonth() {
        return this.o.get(2);
    }

    public Calendar getStartDate() {
        return this.f8169p;
    }

    public int getYear() {
        return this.o.get(1);
    }

    public final boolean h() {
        String language = this.f8154g.getLanguage();
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        return language.equals(locale.getLanguage()) && this.f8154g.getCountry().equals(locale.getCountry());
    }

    public final boolean i() {
        return Settings.Global.getFloat(this.f8152f.getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f;
    }

    @Override // android.view.View
    public final boolean isEnabled() {
        return this.f8164m;
    }

    public final void j(boolean z9) {
        View view = this.f8161k0;
        LinearLayout linearLayout = this.f8151e0;
        if (z9) {
            linearLayout.setOnClickListener(null);
            linearLayout.setClickable(false);
            setCalendarHeaderPadding(false);
            view.setVisibility(8);
            return;
        }
        if (linearLayout.hasOnClickListeners()) {
            return;
        }
        linearLayout.setOnClickListener(this.f8178t0);
        linearLayout.setClickable(true);
        setCalendarHeaderPadding(true);
        view.setVisibility(0);
    }

    public final void k(V v4, int i5, int i7, int i9) {
        if (!this.h) {
            this.f8126B = v4.f8223F - (v4.f8225I - 1);
        }
        Calendar calendar = this.o;
        int i10 = calendar.get(1);
        int i11 = calendar.get(2);
        calendar.set(1, i5);
        calendar.set(2, i7);
        calendar.set(5, i9);
        HandlerC0295o handlerC0295o = this.f8176s0;
        Message messageObtainMessage = handlerC0295o.obtainMessage();
        messageObtainMessage.what = 1000;
        handlerC0295o.sendMessage(messageObtainMessage);
        int i12 = this.f8138O;
        Calendar calendar2 = this.f8169p;
        Calendar calendar3 = this.f8171q;
        if (i12 == 1) {
            if (calendar2.compareTo(calendar3) == 0 || calendar.compareTo(calendar3) >= 0) {
                e(calendar3, i5, i7, i9);
            }
            e(calendar2, i5, i7, i9);
        } else if (i12 == 2) {
            if (calendar.compareTo(calendar2) < 0) {
                e(calendar2, i5, i7, i9);
            }
            e(calendar3, i5, i7, i9);
        } else if (i12 != 3) {
            e(calendar2, i5, i7, i9);
            e(calendar3, i5, i7, i9);
        } else {
            this.f8166n = true;
            int i13 = (((i9 % 7) + this.f8126B) - 1) % 7;
            if (i13 == 0) {
                i13 = 7;
            }
            e(calendar2, i5, i7, (i9 - i13) + 1);
            e(this.f8171q, i5, i7, (7 - i13) + i9);
        }
        if (this.f8138O != 0) {
            calendar2.after(calendar3);
        }
        boolean z9 = this.f8136M != ((i5 - getMinYear()) * 12) + (i7 - getMinMonth());
        if (i5 != i10 || i7 != i11 || i9 != this.f8127C || z9) {
            this.f8127C = i9;
            this.f8141R.notifyDataSetChanged();
        }
        v4.l(i9, i7, i5, getFirstDayOfWeek(), (getMinMonth() == i7 && getMinYear() == i5) ? getMinDay() : 1, (getMaxMonth() == i7 && getMaxYear() == i5) ? getMaxDay() : 31, this.f8175s, this.f8177t, calendar2.get(1), calendar2.get(2), calendar2.get(5), 0, calendar3.get(1), calendar3.get(2), calendar3.get(5), 0, this.f8138O);
        v4.invalidate();
        this.h = false;
    }

    public final void l() {
        b0 b0Var = this.f8150e;
        if (b0Var != null) {
            removeCallbacks(b0Var);
            new Handler().postDelayed(new X(2, this), 200L);
        }
    }

    public final void n(boolean z9) {
        Calendar calendar = this.o;
        int i5 = calendar.get(2);
        int minMonth = (i5 - getMinMonth()) + ((calendar.get(1) - getMinYear()) * 12);
        this.f8136M = minMonth;
        boolean zI = i();
        ViewPager viewPager = this.f8142S;
        if (zI) {
            viewPager.setCurrentItem(minMonth, false);
        } else {
            viewPager.setCurrentItem(minMonth, z9);
        }
        HandlerC0295o handlerC0295o = this.f8176s0;
        Message messageObtainMessage = handlerC0295o.obtainMessage();
        messageObtainMessage.what = 1000;
        messageObtainMessage.obj = Boolean.TRUE;
        handlerC0295o.sendMessage(messageObtainMessage);
        Message messageObtainMessage2 = handlerC0295o.obtainMessage();
        messageObtainMessage2.what = 1001;
        handlerC0295o.sendMessage(messageObtainMessage2);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        ViewPager viewPager = this.f8142S;
        if (id == com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_prev_button) {
            if (this.f8162l) {
                if (this.f8136M == this.f8137N - 1) {
                    return;
                }
                if (i()) {
                    viewPager.setCurrentItem(this.f8136M + 1, false);
                    return;
                } else {
                    viewPager.setCurrentItem(this.f8136M + 1);
                    return;
                }
            }
            if (this.f8136M == 0) {
                return;
            }
            if (i()) {
                viewPager.setCurrentItem(this.f8136M - 1, false);
                return;
            } else {
                viewPager.setCurrentItem(this.f8136M - 1);
                return;
            }
        }
        if (id == com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_next_button) {
            if (this.f8162l) {
                if (this.f8136M == 0) {
                    return;
                }
                if (i()) {
                    viewPager.setCurrentItem(this.f8136M - 1, false);
                    return;
                } else {
                    viewPager.setCurrentItem(this.f8136M - 1);
                    return;
                }
            }
            if (this.f8136M == this.f8137N - 1) {
                return;
            }
            if (i()) {
                viewPager.setCurrentItem(this.f8136M + 1, false);
            } else {
                viewPager.setCurrentItem(this.f8136M + 1);
            }
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f8162l = g();
        this.f8159j = "fa".equals(this.f8154g.getLanguage());
        Locale locale = configuration.getLocales().get(0);
        if (!this.f8154g.equals(locale)) {
            this.f8154g = locale;
            if (h()) {
                this.f8153f0 = new SimpleDateFormat("EEEEE", locale);
            } else {
                this.f8153f0 = new SimpleDateFormat("EEE", locale);
            }
        }
        Resources resources = this.f8152f.getResources();
        this.f8149c0.setGravity(1);
        this.f8160k = true;
        this.f8129E = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_header_height);
        this.f8130F = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_view_height);
        this.f8133J = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_day_height);
        this.f8181w = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_gap_between_header_and_weekend);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_gap_between_weekend_and_calender);
        this.f8182x = dimensionPixelOffset;
        this.f8183y = this.f8129E + this.f8181w + this.f8133J + dimensionPixelOffset + this.f8130F;
        if (this.f8162l) {
            this.f8157i = true;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        l();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        Window window;
        super.onLayout(z9, i5, i7, i9, i10);
        if (getLayoutParams().height == -2 || getMeasuredHeight() <= this.f8183y) {
            if (this.f8168o0 == null && (window = this.f8170p0) != null) {
                this.f8168o0 = (FrameLayout) window.findViewById(com.samsung.android.keyscafe.R.id.customPanel);
            }
            int measuredHeight = this.f8174r0;
            FrameLayout frameLayout = this.f8168o0;
            if (frameLayout != null) {
                measuredHeight = frameLayout.getMeasuredHeight();
                if (this.f8170p0 != null) {
                    measuredHeight -= this.f8172q0;
                }
            }
            if (this.f8180v == 0 || !this.f8148b0.f8188e) {
                Activity activityM = m(this.f8152f);
                if (activityM == null || !activityM.isInMultiWindowMode()) {
                    j(false);
                } else if (measuredHeight >= this.f8183y) {
                    j(false);
                } else {
                    setCurrentViewType(1);
                    j(true);
                }
            }
        }
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int id = view.getId();
        if (id == com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_prev_button && this.f8136M != 0) {
            long longPressTimeout = ViewConfiguration.getLongPressTimeout();
            Runnable runnable = this.f8150e;
            if (runnable == null) {
                this.f8150e = new b0(1, this);
            } else {
                removeCallbacks(runnable);
            }
            b0 b0Var = this.f8150e;
            b0Var.f8283f = false;
            postDelayed(b0Var, longPressTimeout);
        } else if (id == com.samsung.android.keyscafe.R.id.sesl_date_picker_calendar_header_next_button && this.f8136M != this.f8137N - 1) {
            long longPressTimeout2 = ViewConfiguration.getLongPressTimeout();
            Runnable runnable2 = this.f8150e;
            if (runnable2 == null) {
                this.f8150e = new b0(1, this);
            } else {
                removeCallbacks(runnable2);
            }
            b0 b0Var2 = this.f8150e;
            b0Var2.f8283f = true;
            postDelayed(b0Var2, longPressTimeout2);
        }
        return false;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i5, int i7) {
        int size;
        this.f8174r0 = View.MeasureSpec.getSize(i7);
        int i9 = this.G;
        if (i9 != -1) {
            int mode = View.MeasureSpec.getMode(i5);
            if (mode == Integer.MIN_VALUE) {
                int i10 = getResources().getConfiguration().smallestScreenWidthDp;
                size = i10 >= 600 ? getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_dialog_min_width) : (int) (TypedValue.applyDimension(1, i10, getResources().getDisplayMetrics()) + 0.5f);
            } else {
                size = View.MeasureSpec.getSize(i5);
            }
            int i11 = this.f8132I;
            if (mode == Integer.MIN_VALUE) {
                int i12 = size - (i11 * 2);
                this.G = i12;
                this.f8134K = i12;
                i5 = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
            } else if (mode == 0) {
                i5 = View.MeasureSpec.makeMeasureSpec(i9, 1073741824);
            } else {
                if (mode != 1073741824) {
                    throw new IllegalArgumentException(A8.l.o(mode, "Unknown measure mode: "));
                }
                int i13 = size - (i11 * 2);
                this.G = i13;
                this.f8134K = i13;
            }
        }
        if (!this.f8160k && this.f8131H == this.G) {
            super.onMeasure(i5, i7);
            return;
        }
        this.f8160k = false;
        this.f8131H = this.G;
        this.d0.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f8145V.setLayoutParams(new LinearLayout.LayoutParams(this.f8134K, this.f8133J));
        this.f8146W.setLayoutParams(new LinearLayout.LayoutParams(this.f8134K, this.f8133J));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.G, this.f8130F);
        ViewPager viewPager = this.f8142S;
        viewPager.setLayoutParams(layoutParams);
        if (this.f8162l && this.f8157i) {
            viewPager.seslSetConfigurationChanged(true);
        }
        this.f8158i0.setLayoutParams(new LinearLayout.LayoutParams(-1, this.f8181w));
        this.j0.setLayoutParams(new LinearLayout.LayoutParams(-1, this.f8182x));
        super.onMeasure(i5, i7);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(((View.BaseSavedState) parcelable).getSuperState());
        C0302w c0302w = (C0302w) parcelable;
        this.o.set(c0302w.f8449e, c0302w.f8450f, c0302w.f8451g);
        this.f8175s.setTimeInMillis(c0302w.h);
        this.f8177t.setTimeInMillis(c0302w.f8452i);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        Calendar calendar = this.o;
        return new C0302w(parcelableOnSaveInstanceState, calendar.get(1), calendar.get(2), calendar.get(5), this.f8175s.getTimeInMillis(), this.f8177t.getTimeInMillis());
    }

    @Override // android.view.View
    public final void onSizeChanged(int i5, int i7, int i9, int i10) {
        super.onSizeChanged(i5, i7, i9, i10);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        super.requestLayout();
        SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = this.f8148b0;
        if (seslDatePickerSpinnerLayout == null || seslDatePickerSpinnerLayout.getVisibility() != 0) {
            return;
        }
        seslDatePickerSpinnerLayout.requestLayout();
    }

    public void setCurrentViewType(int i5) {
        int i7;
        int i9;
        int i10;
        ViewAnimator viewAnimator = this.f8147a0;
        HandlerC0295o handlerC0295o = this.f8176s0;
        SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = this.f8148b0;
        if (i5 != 0) {
            if (i5 != 1) {
                return;
            }
            if (this.f8180v != i5) {
                this.f8161k0.setRotation(-180.0f);
                int i11 = this.f8138O;
                if (i11 == 1) {
                    Calendar calendar = this.f8169p;
                    i7 = calendar.get(1);
                    i9 = calendar.get(2);
                    i10 = calendar.get(5);
                } else if (i11 != 2) {
                    Calendar calendar2 = this.o;
                    i7 = calendar2.get(1);
                    i9 = calendar2.get(2);
                    i10 = calendar2.get(5);
                } else {
                    Calendar calendar3 = this.f8171q;
                    i7 = calendar3.get(1);
                    i9 = calendar3.get(2);
                    i10 = calendar3.get(5);
                }
                seslDatePickerSpinnerLayout.h(i7, i9, i10);
                viewAnimator.setDisplayedChild(1);
                seslDatePickerSpinnerLayout.setEnabled(true);
                this.f8180v = i5;
                Message messageObtainMessage = handlerC0295o.obtainMessage();
                messageObtainMessage.what = 1000;
                handlerC0295o.sendMessage(messageObtainMessage);
            }
        } else if (this.f8180v != i5) {
            seslDatePickerSpinnerLayout.i();
            seslDatePickerSpinnerLayout.d(false);
            viewAnimator.setDisplayedChild(0);
            seslDatePickerSpinnerLayout.setVisibility(4);
            seslDatePickerSpinnerLayout.setEnabled(false);
            this.f8180v = i5;
            Message messageObtainMessage2 = handlerC0295o.obtainMessage();
            messageObtainMessage2.what = 1000;
            handlerC0295o.sendMessage(messageObtainMessage2);
            this.f8141R.notifyDataSetChanged();
        }
        Message messageObtainMessage3 = handlerC0295o.obtainMessage();
        messageObtainMessage3.what = 1001;
        handlerC0295o.sendMessage(messageObtainMessage3);
    }

    public void setDateMode(int i5) {
        this.f8138O = i5;
        this.f8166n = false;
        SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = this.f8148b0;
        Calendar calendar = this.f8169p;
        Calendar calendar2 = this.f8171q;
        if (i5 == 1) {
            seslDatePickerSpinnerLayout.h(calendar.get(1), calendar.get(2), calendar.get(5));
        } else if (i5 == 2) {
            seslDatePickerSpinnerLayout.h(calendar2.get(1), calendar2.get(2), calendar2.get(5));
        }
        if (this.f8180v == 1) {
            seslDatePickerSpinnerLayout.setVisibility(0);
            seslDatePickerSpinnerLayout.setEnabled(true);
        }
        r rVar = this.f8141R;
        V v4 = (V) ((SparseArray) rVar.f8440b).get(this.f8136M);
        if (v4 != null) {
            Calendar calendar3 = this.o;
            int i7 = calendar3.get(1);
            int i9 = calendar3.get(2);
            int i10 = calendar3.get(5);
            int minDay = (getMinMonth() == i9 && getMinYear() == i7) ? getMinDay() : 1;
            int maxDay = (getMaxMonth() == i9 && getMaxYear() == i7) ? getMaxDay() : 31;
            v4.l(i10, i9, i7, getFirstDayOfWeek(), minDay, maxDay, this.f8175s, this.f8177t, calendar.get(1), calendar.get(2), calendar.get(5), 0, calendar2.get(1), calendar2.get(2), calendar2.get(5), 0, this.f8138O);
            v4.invalidate();
        }
        rVar.notifyDataSetChanged();
    }

    public void setDateValidator(InterfaceC0298s interfaceC0298s) {
    }

    public void setDialogPaddingVertical(int i5) {
        this.f8172q0 = i5;
    }

    public void setDialogWindow(Window window) {
        if (window != null) {
            this.f8170p0 = window;
        }
    }

    public void setEditTextMode(boolean z9) {
        if (this.f8180v == 0) {
            return;
        }
        this.f8148b0.d(z9);
    }

    @Override // android.view.View
    public void setEnabled(boolean z9) {
        if (this.f8164m == z9) {
            return;
        }
        super.setEnabled(z9);
        this.f8164m = z9;
    }

    public void setFirstDayOfWeek(int i5) {
        if (i5 < 1 || i5 > 7) {
            throw new IllegalArgumentException("firstDayOfWeek must be between 1 and 7");
        }
        this.f8139P = i5;
    }

    public void setMaxDate(long j5) {
        Calendar calendar = this.f8179u;
        calendar.setTimeInMillis(j5);
        int i5 = calendar.get(1);
        Calendar calendar2 = this.f8177t;
        if (i5 != calendar2.get(1) || calendar.get(6) == calendar2.get(6)) {
            Calendar calendar3 = this.o;
            if (calendar3.after(calendar)) {
                calendar3.setTimeInMillis(j5);
            }
            calendar2.setTimeInMillis(j5);
            long timeInMillis = calendar2.getTimeInMillis();
            SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = this.f8148b0;
            seslDatePickerSpinnerLayout.f8191i.setTimeInMillis(timeInMillis);
            if (seslDatePickerSpinnerLayout.f8192j.after(seslDatePickerSpinnerLayout.f8191i)) {
                seslDatePickerSpinnerLayout.f8192j.setTimeInMillis(seslDatePickerSpinnerLayout.f8191i.getTimeInMillis());
            }
            seslDatePickerSpinnerLayout.j(true, true, true, true);
            this.f8141R.notifyDataSetChanged();
            n(false);
        }
    }

    public void setMinDate(long j5) {
        Calendar calendar = this.f8179u;
        calendar.setTimeInMillis(j5);
        int i5 = calendar.get(1);
        Calendar calendar2 = this.f8175s;
        if (i5 != calendar2.get(1) || calendar.get(6) == calendar2.get(6)) {
            Calendar calendar3 = this.o;
            if (calendar3.before(calendar)) {
                calendar3.setTimeInMillis(j5);
            }
            calendar2.setTimeInMillis(j5);
            long timeInMillis = calendar2.getTimeInMillis();
            SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = this.f8148b0;
            seslDatePickerSpinnerLayout.h.setTimeInMillis(timeInMillis);
            if (seslDatePickerSpinnerLayout.f8192j.before(seslDatePickerSpinnerLayout.h)) {
                seslDatePickerSpinnerLayout.f8192j.setTimeInMillis(seslDatePickerSpinnerLayout.h.getTimeInMillis());
            }
            seslDatePickerSpinnerLayout.j(true, true, true, true);
            this.f8141R.notifyDataSetChanged();
            n(false);
        }
    }

    public void setOnEditTextModeChangedListener(InterfaceC0300u interfaceC0300u) {
        SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = this.f8148b0;
        if (seslDatePickerSpinnerLayout.f8195m == null) {
            seslDatePickerSpinnerLayout.f8195m = this;
        }
    }

    public void setOnViewTypeChangedListener(InterfaceC0301v interfaceC0301v) {
    }

    public void setSeparateLunarButton(boolean z9) {
        if (this.f8167n0 == z9) {
            return;
        }
        if (z9) {
            Resources resources = this.f8152f.getResources();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f8143T.getLayoutParams();
            layoutParams.removeRule(16);
            layoutParams.leftMargin = 0;
            ((RelativeLayout.LayoutParams) this.f8155g0.getLayoutParams()).leftMargin = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_view_margin);
            ((RelativeLayout.LayoutParams) this.f8156h0.getLayoutParams()).rightMargin = resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_date_picker_calendar_view_margin);
        } else {
            this.f8149c0.removeView(null);
            this.f8183y -= this.f8129E;
        }
        this.f8167n0 = z9;
    }

    public void setValidationCallback(InterfaceC0303x interfaceC0303x) {
    }
}
