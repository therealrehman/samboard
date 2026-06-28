package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class DialogPreference extends Preference {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public final CharSequence f8614X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public final String f8615Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public final Drawable f8616Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final String f8617a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final String f8618b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final int f8619c0;

    public DialogPreference(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8649b, i5, 0);
        String string = typedArrayObtainStyledAttributes.getString(9);
        string = string == null ? typedArrayObtainStyledAttributes.getString(0) : string;
        this.f8614X = string;
        if (string == null) {
            this.f8614X = this.f8712l;
        }
        String string2 = typedArrayObtainStyledAttributes.getString(8);
        this.f8615Y = string2 == null ? typedArrayObtainStyledAttributes.getString(1) : string2;
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(6);
        this.f8616Z = drawable == null ? typedArrayObtainStyledAttributes.getDrawable(2) : drawable;
        String string3 = typedArrayObtainStyledAttributes.getString(11);
        this.f8617a0 = string3 == null ? typedArrayObtainStyledAttributes.getString(3) : string3;
        String string4 = typedArrayObtainStyledAttributes.getString(10);
        this.f8618b0 = string4 == null ? typedArrayObtainStyledAttributes.getString(4) : string4;
        this.f8619c0 = typedArrayObtainStyledAttributes.getResourceId(7, typedArrayObtainStyledAttributes.getResourceId(5, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public void p() {
        E e3 = this.f8707f.f8634i;
        if (e3 != null) {
            e3.onDisplayPreferenceDialog(this);
        }
    }

    public DialogPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C.b.a(context, R.attr.dialogPreferenceStyle, android.R.attr.dialogPreferenceStyle));
    }
}
