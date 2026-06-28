package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.W;
import com.samsung.android.keyscafe.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class Preference implements Comparable<Preference> {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final boolean f8684A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final boolean f8685B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final boolean f8686C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final boolean f8687D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final boolean f8688E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final boolean f8689F;
    public final boolean G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final boolean f8690H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f8691I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f8692J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final boolean f8693K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public A f8694L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public ArrayList f8695M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public PreferenceGroup f8696N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f8697O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public ViewOnCreateContextMenuListenerC0320o f8698P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public W1.a f8699Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final ViewOnClickListenerC0315j f8700R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f8701S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public boolean f8702T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public int f8703U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f8704V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final ColorStateList f8705W;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f8706e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public H f8707f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f8708g;
    public boolean h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public InterfaceC0318m f8709i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public InterfaceC0319n f8710j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8711k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public CharSequence f8712l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f8713m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8714n;
    public Drawable o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f8715p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Intent f8716q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f8717r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Bundle f8718s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f8719t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f8720u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f8721v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final String f8722w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Object f8723x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f8724y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f8725z;

    public Preference(Context context, AttributeSet attributeSet, int i5, int i7) {
        this.f8711k = Integer.MAX_VALUE;
        this.f8719t = true;
        this.f8720u = true;
        this.f8721v = true;
        this.f8724y = true;
        this.f8725z = true;
        this.f8684A = true;
        this.f8685B = true;
        this.f8686C = true;
        this.f8688E = true;
        this.f8690H = true;
        this.f8691I = R.layout.sesl_preference;
        this.f8700R = new ViewOnClickListenerC0315j(this, 0);
        this.f8701S = false;
        this.f8702T = false;
        this.f8703U = 0;
        this.f8704V = false;
        this.f8706e = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8653f, i5, i7);
        this.f8714n = typedArrayObtainStyledAttributes.getResourceId(23, typedArrayObtainStyledAttributes.getResourceId(0, 0));
        String string = typedArrayObtainStyledAttributes.getString(27);
        this.f8715p = string == null ? typedArrayObtainStyledAttributes.getString(6) : string;
        CharSequence text = typedArrayObtainStyledAttributes.getText(35);
        this.f8712l = text == null ? typedArrayObtainStyledAttributes.getText(4) : text;
        CharSequence text2 = typedArrayObtainStyledAttributes.getText(34);
        this.f8713m = text2 == null ? typedArrayObtainStyledAttributes.getText(7) : text2;
        this.f8711k = typedArrayObtainStyledAttributes.getInt(29, typedArrayObtainStyledAttributes.getInt(8, Integer.MAX_VALUE));
        String string2 = typedArrayObtainStyledAttributes.getString(22);
        this.f8717r = string2 == null ? typedArrayObtainStyledAttributes.getString(13) : string2;
        this.f8691I = typedArrayObtainStyledAttributes.getResourceId(28, typedArrayObtainStyledAttributes.getResourceId(3, R.layout.preference));
        this.f8692J = typedArrayObtainStyledAttributes.getResourceId(36, typedArrayObtainStyledAttributes.getResourceId(9, 0));
        this.f8693K = typedArrayObtainStyledAttributes.getBoolean(25, typedArrayObtainStyledAttributes.getBoolean(25, false));
        this.f8719t = typedArrayObtainStyledAttributes.getBoolean(21, typedArrayObtainStyledAttributes.getBoolean(2, true));
        boolean z9 = typedArrayObtainStyledAttributes.getBoolean(31, typedArrayObtainStyledAttributes.getBoolean(5, true));
        this.f8720u = z9;
        this.f8721v = typedArrayObtainStyledAttributes.getBoolean(30, typedArrayObtainStyledAttributes.getBoolean(1, true));
        String string3 = typedArrayObtainStyledAttributes.getString(19);
        this.f8722w = string3 == null ? typedArrayObtainStyledAttributes.getString(10) : string3;
        this.f8685B = typedArrayObtainStyledAttributes.getBoolean(16, typedArrayObtainStyledAttributes.getBoolean(16, z9));
        this.f8686C = typedArrayObtainStyledAttributes.getBoolean(17, typedArrayObtainStyledAttributes.getBoolean(17, z9));
        if (typedArrayObtainStyledAttributes.hasValue(18)) {
            this.f8723x = r(typedArrayObtainStyledAttributes, 18);
        } else if (typedArrayObtainStyledAttributes.hasValue(11)) {
            this.f8723x = r(typedArrayObtainStyledAttributes, 11);
        }
        this.f8690H = typedArrayObtainStyledAttributes.getBoolean(32, typedArrayObtainStyledAttributes.getBoolean(12, true));
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(33);
        this.f8687D = zHasValue;
        if (zHasValue) {
            this.f8688E = typedArrayObtainStyledAttributes.getBoolean(33, typedArrayObtainStyledAttributes.getBoolean(14, true));
        }
        this.f8689F = typedArrayObtainStyledAttributes.getBoolean(24, typedArrayObtainStyledAttributes.getBoolean(15, false));
        this.f8684A = typedArrayObtainStyledAttributes.getBoolean(26, typedArrayObtainStyledAttributes.getBoolean(26, true));
        this.G = typedArrayObtainStyledAttributes.getBoolean(20, typedArrayObtainStyledAttributes.getBoolean(20, false));
        typedArrayObtainStyledAttributes.recycle();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textColorSecondary, typedValue, true);
        if (typedValue.resourceId > 0) {
            this.f8705W = context.getResources().getColorStateList(typedValue.resourceId);
        }
    }

    public static void y(View view, boolean z9) {
        view.setEnabled(z9);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                y(viewGroup.getChildAt(childCount), z9);
            }
        }
    }

    private void z(TextView textView) {
        if (Build.VERSION.SDK_INT >= 33) {
            textView.setLineBreakWordStyle(1);
        }
    }

    public final void A(CharSequence charSequence) {
        if (this.f8699Q != null) {
            throw new IllegalStateException("Preference already has a SummaryProvider set.");
        }
        if (TextUtils.equals(this.f8713m, charSequence)) {
            return;
        }
        this.f8713m = charSequence;
        k();
    }

    public final void B(String str) {
        if (TextUtils.equals(str, this.f8712l)) {
            return;
        }
        this.f8712l = str;
        k();
    }

    public boolean C() {
        return !i();
    }

    public final boolean D() {
        return this.f8707f != null && this.f8721v && (TextUtils.isEmpty(this.f8715p) ^ true);
    }

    public final void E(SharedPreferences.Editor editor) {
        if (!this.f8707f.f8631e) {
            editor.apply();
        }
    }

    public final void F() {
        ArrayList arrayList;
        PreferenceScreen preferenceScreen;
        String str = this.f8722w;
        if (str != null) {
            H h = this.f8707f;
            Preference preferenceH = null;
            if (h != null && (preferenceScreen = h.f8633g) != null) {
                preferenceH = preferenceScreen.H(str);
            }
            if (preferenceH == null || (arrayList = preferenceH.f8695M) == null) {
                return;
            }
            arrayList.remove(this);
        }
    }

    public final boolean a(Serializable serializable) {
        InterfaceC0318m interfaceC0318m = this.f8709i;
        if (interfaceC0318m == null) {
            return true;
        }
        interfaceC0318m.a(this, serializable);
        return true;
    }

    public void b() {
        InterfaceC0319n interfaceC0319n = this.f8710j;
        if (interfaceC0319n != null) {
            interfaceC0319n.w(this);
        }
    }

    public void c(Bundle bundle) {
        Parcelable parcelable;
        if (!(!TextUtils.isEmpty(this.f8715p)) || (parcelable = bundle.getParcelable(this.f8715p)) == null) {
            return;
        }
        this.f8697O = false;
        s(parcelable);
        if (!this.f8697O) {
            throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Preference preference) {
        Preference preference2 = preference;
        int i5 = this.f8711k;
        int i7 = preference2.f8711k;
        if (i5 != i7) {
            return i5 - i7;
        }
        CharSequence charSequence = this.f8712l;
        CharSequence charSequence2 = preference2.f8712l;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference2.f8712l.toString());
    }

    public void d(Bundle bundle) {
        if (!TextUtils.isEmpty(this.f8715p)) {
            this.f8697O = false;
            Parcelable parcelableT = t();
            if (!this.f8697O) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            }
            if (parcelableT != null) {
                bundle.putParcelable(this.f8715p, parcelableT);
            }
        }
    }

    public final Bundle e() {
        if (this.f8718s == null) {
            this.f8718s = new Bundle();
        }
        return this.f8718s;
    }

    public long f() {
        return this.f8708g;
    }

    public final String g(String str) {
        return !D() ? str : this.f8707f.c().getString(this.f8715p, str);
    }

    public CharSequence h() {
        W1.a aVar = this.f8699Q;
        return aVar != null ? aVar.n(this) : this.f8713m;
    }

    public boolean i() {
        return this.f8719t && this.f8724y && this.f8725z;
    }

    public final boolean j() {
        String string;
        Context context = this.f8706e;
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        if (accessibilityManager == null || !accessibilityManager.isEnabled() || (string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services")) == null) {
            return false;
        }
        return string.matches("(?i).*com.samsung.accessibility/com.samsung.android.app.talkback.TalkBackService.*") || string.matches("(?i).*com.samsung.android.accessibility.talkback/com.samsung.android.marvin.talkback.TalkBackService.*") || string.matches("(?i).*com.google.android.marvin.talkback.TalkBackService.*") || string.matches("(?i).*com.samsung.accessibility/com.samsung.accessibility.universalswitch.UniversalSwitchService.*");
    }

    public void k() {
        int iIndexOf;
        A a10 = this.f8694L;
        if (a10 == null || (iIndexOf = a10.f8597g.indexOf(this)) == -1) {
            return;
        }
        a10.notifyItemChanged(iIndexOf, this);
    }

    public void l(boolean z9) {
        ArrayList arrayList = this.f8695M;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            Preference preference = (Preference) arrayList.get(i5);
            if (preference.f8724y == z9) {
                preference.f8724y = !z9;
                preference.l(preference.C());
                preference.k();
            }
        }
    }

    public void m() {
        PreferenceScreen preferenceScreen;
        String str = this.f8722w;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        H h = this.f8707f;
        Preference preferenceH = null;
        if (h != null && (preferenceScreen = h.f8633g) != null) {
            preferenceH = preferenceScreen.H(str);
        }
        if (preferenceH == null) {
            StringBuilder sbY = A8.l.y("Dependency \"", str, "\" not found for preference \"");
            sbY.append(this.f8715p);
            sbY.append("\" (title: \"");
            sbY.append((Object) this.f8712l);
            sbY.append("\"");
            throw new IllegalStateException(sbY.toString());
        }
        if (preferenceH.f8695M == null) {
            preferenceH.f8695M = new ArrayList();
        }
        preferenceH.f8695M.add(this);
        boolean zC = preferenceH.C();
        if (this.f8724y == zC) {
            this.f8724y = !zC;
            l(C());
            k();
        }
    }

    public void n(H h) {
        long j5;
        this.f8707f = h;
        if (!this.h) {
            synchronized (h) {
                j5 = h.f8628b;
                h.f8628b = 1 + j5;
            }
            this.f8708g = j5;
        }
        if (D()) {
            H h2 = this.f8707f;
            if ((h2 != null ? h2.c() : null).contains(this.f8715p)) {
                v(null, true);
                return;
            }
        }
        Object obj = this.f8723x;
        if (obj != null) {
            v(obj, false);
        }
    }

    public void o(K k5) {
        Integer numValueOf;
        boolean z9 = this.f8688E;
        boolean z10 = this.f8687D;
        View view = k5.itemView;
        view.setOnClickListener(this.f8700R);
        view.setId(0);
        TextView textView = (TextView) k5.a(android.R.id.summary);
        if (textView != null) {
            CharSequence charSequenceH = h();
            if (TextUtils.isEmpty(charSequenceH)) {
                textView.setVisibility(8);
                numValueOf = null;
            } else {
                textView.setText(charSequenceH);
                z(textView);
                ColorStateList colorStateList = this.f8705W;
                if (colorStateList != null) {
                    textView.setTextColor(colorStateList);
                }
                textView.setVisibility(0);
                numValueOf = Integer.valueOf(textView.getCurrentTextColor());
            }
        } else {
            numValueOf = null;
        }
        k5.f8644j = 0;
        boolean z11 = this.f8701S;
        int i5 = this.f8703U;
        boolean z12 = this.f8702T;
        k5.f8646l = z11;
        k5.f8645k = i5;
        k5.f8647m = z12;
        TextView textView2 = (TextView) k5.a(android.R.id.title);
        boolean z13 = this.f8720u;
        if (textView2 != null) {
            CharSequence charSequence = this.f8712l;
            if (!TextUtils.isEmpty(charSequence)) {
                textView2.setText(charSequence);
                textView2.setVisibility(0);
                if (z10) {
                    textView2.setSingleLine(z9);
                }
                if (!z13 && i() && numValueOf != null) {
                    textView2.setTextColor(numValueOf.intValue());
                }
            } else if (TextUtils.isEmpty(charSequence) && (this instanceof PreferenceCategory)) {
                textView2.setVisibility(0);
                if (z10) {
                    textView2.setSingleLine(z9);
                }
            } else {
                textView2.setVisibility(8);
            }
        }
        ImageView imageView = (ImageView) k5.a(android.R.id.icon);
        boolean z14 = this.f8689F;
        if (imageView != null) {
            int i7 = this.f8714n;
            if (i7 != 0 || this.o != null) {
                if (this.o == null) {
                    this.o = android.support.v4.media.session.f.y(this.f8706e, i7);
                }
                Drawable drawable = this.o;
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                }
            }
            if (this.o != null) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(z14 ? 4 : 8);
            }
        }
        View viewA = k5.a(R.id.icon_frame);
        if (viewA == null) {
            viewA = k5.a(android.R.id.icon_frame);
        }
        if (viewA != null) {
            if (this.o != null) {
                viewA.setVisibility(0);
            } else {
                viewA.setVisibility(z14 ? 4 : 8);
            }
        }
        if (this.f8690H) {
            y(view, i());
        } else {
            y(view, true);
        }
        view.setFocusable(z13);
        view.setClickable(z13);
        k5.h = this.f8685B;
        k5.f8643i = this.f8686C;
        boolean z15 = this.G;
        if (z15 && this.f8698P == null) {
            this.f8698P = new ViewOnCreateContextMenuListenerC0320o(this);
        }
        view.setOnCreateContextMenuListener(z15 ? this.f8698P : null);
        view.setLongClickable(z15);
        if (!z15 || z13) {
            return;
        }
        WeakHashMap weakHashMap = W.f7199a;
        view.setBackground(null);
    }

    public void p() {
    }

    public void q() {
        F();
    }

    public Object r(TypedArray typedArray, int i5) {
        return null;
    }

    public void s(Parcelable parcelable) {
        this.f8697O = true;
        if (parcelable != AbsSavedState.EMPTY_STATE && parcelable != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    public Parcelable t() {
        this.f8697O = true;
        return AbsSavedState.EMPTY_STATE;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        CharSequence charSequence = this.f8712l;
        if (!TextUtils.isEmpty(charSequence)) {
            sb.append(charSequence);
            sb.append(' ');
        }
        CharSequence charSequenceH = h();
        if (!TextUtils.isEmpty(charSequenceH)) {
            sb.append(charSequenceH);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public void u(Object obj) {
    }

    public void v(Object obj, boolean z9) {
        u(obj);
    }

    public void w(View view) {
        Intent intent;
        G g9;
        if (i() && this.f8720u) {
            p();
            InterfaceC0319n interfaceC0319n = this.f8710j;
            if (interfaceC0319n == null || !interfaceC0319n.w(this)) {
                H h = this.f8707f;
                if ((h == null || (g9 = h.h) == null || !g9.onPreferenceTreeClick(this)) && (intent = this.f8716q) != null) {
                    this.f8706e.startActivity(intent);
                }
            }
        }
    }

    public final void x(String str) {
        if (D() && !TextUtils.equals(str, g(null))) {
            SharedPreferences.Editor editorB = this.f8707f.b();
            editorB.putString(this.f8715p, str);
            E(editorB);
        }
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C.b.a(context, R.attr.preferenceStyle, android.R.attr.preferenceStyle), 0);
    }
}
