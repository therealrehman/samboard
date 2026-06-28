package androidx.preference;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ComponentCallbacks2;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class PreferenceDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public DialogPreference f8726e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f8727f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CharSequence f8728g;
    public CharSequence h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f8729i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8730j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public BitmapDrawable f8731k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f8732l;

    @Deprecated
    public PreferenceDialogFragment() {
    }

    public final DialogPreference a() {
        if (this.f8726e == null) {
            this.f8726e = (DialogPreference) ((InterfaceC0307b) getTargetFragment()).findPreference(getArguments().getString("key"));
        }
        return this.f8726e;
    }

    public void b(View view) {
        int i5;
        View viewFindViewById = view.findViewById(R.id.message);
        if (viewFindViewById != null) {
            CharSequence charSequence = this.f8729i;
            if (TextUtils.isEmpty(charSequence)) {
                i5 = 8;
            } else {
                if (viewFindViewById instanceof TextView) {
                    ((TextView) viewFindViewById).setText(charSequence);
                }
                i5 = 0;
            }
            if (viewFindViewById.getVisibility() != i5) {
                viewFindViewById.setVisibility(i5);
            }
        }
    }

    public abstract void c(boolean z9);

    public void d(AlertDialog.Builder builder) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i5) {
        this.f8732l = i5;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ComponentCallbacks2 targetFragment = getTargetFragment();
        if (!(targetFragment instanceof InterfaceC0307b)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        InterfaceC0307b interfaceC0307b = (InterfaceC0307b) targetFragment;
        String string = getArguments().getString("key");
        if (bundle != null) {
            this.f8727f = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.f8728g = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.h = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.f8729i = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.f8730j = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.f8731k = new BitmapDrawable(getResources(), bitmap);
                return;
            }
            return;
        }
        DialogPreference dialogPreference = (DialogPreference) interfaceC0307b.findPreference(string);
        this.f8726e = dialogPreference;
        this.f8727f = dialogPreference.f8614X;
        this.f8728g = dialogPreference.f8617a0;
        this.h = dialogPreference.f8618b0;
        this.f8729i = dialogPreference.f8615Y;
        this.f8730j = dialogPreference.f8619c0;
        Drawable drawable = dialogPreference.f8616Z;
        if (drawable == null || (drawable instanceof BitmapDrawable)) {
            this.f8731k = (BitmapDrawable) drawable;
            return;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        this.f8731k = new BitmapDrawable(getResources(), bitmapCreateBitmap);
    }

    @Override // android.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        this.f8732l = -2;
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(activity).setTitle(this.f8727f).setIcon(this.f8731k).setPositiveButton(this.f8728g, this).setNegativeButton(this.h, this);
        int i5 = this.f8730j;
        View viewInflate = i5 != 0 ? LayoutInflater.from(activity).inflate(i5, (ViewGroup) null) : null;
        if (viewInflate != null) {
            b(viewInflate);
            negativeButton.setView(viewInflate);
        } else {
            negativeButton.setMessage(this.f8729i);
        }
        d(negativeButton);
        AlertDialog alertDialogCreate = negativeButton.create();
        if (this instanceof EditTextPreferenceDialogFragment) {
            AbstractC0321p.a(alertDialogCreate.getWindow());
        }
        return alertDialogCreate;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        c(this.f8732l == -1);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.f8727f);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.f8728g);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.h);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.f8729i);
        bundle.putInt("PreferenceDialogFragment.layout", this.f8730j);
        BitmapDrawable bitmapDrawable = this.f8731k;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }
}
