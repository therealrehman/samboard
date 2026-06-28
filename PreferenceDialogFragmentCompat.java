package androidx.preference;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.InterfaceC0266h;
import g.C0544l;
import g.DialogInterfaceC0545m;

/* JADX INFO: loaded from: classes.dex */
public abstract class PreferenceDialogFragmentCompat extends DialogFragment implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public DialogPreference f8733e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f8734f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CharSequence f8735g;
    public CharSequence h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f8736i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8737j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public BitmapDrawable f8738k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f8739l;

    public final DialogPreference g() {
        if (this.f8733e == null) {
            this.f8733e = (DialogPreference) ((InterfaceC0307b) getTargetFragment()).findPreference(requireArguments().getString("key"));
        }
        return this.f8733e;
    }

    public void h(View view) {
        int i5;
        View viewFindViewById = view.findViewById(R.id.message);
        if (viewFindViewById != null) {
            CharSequence charSequence = this.f8736i;
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

    public abstract void i(boolean z9);

    public void j(C0544l c0544l) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i5) {
        this.f8739l = i5;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        InterfaceC0266h targetFragment = getTargetFragment();
        if (!(targetFragment instanceof InterfaceC0307b)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        InterfaceC0307b interfaceC0307b = (InterfaceC0307b) targetFragment;
        String string = requireArguments().getString("key");
        if (bundle != null) {
            this.f8734f = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.f8735g = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.h = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.f8736i = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.f8737j = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.f8738k = new BitmapDrawable(getResources(), bitmap);
                return;
            }
            return;
        }
        DialogPreference dialogPreference = (DialogPreference) interfaceC0307b.findPreference(string);
        this.f8733e = dialogPreference;
        this.f8734f = dialogPreference.f8614X;
        this.f8735g = dialogPreference.f8617a0;
        this.h = dialogPreference.f8618b0;
        this.f8736i = dialogPreference.f8615Y;
        this.f8737j = dialogPreference.f8619c0;
        Drawable drawable = dialogPreference.f8616Z;
        if (drawable == null || (drawable instanceof BitmapDrawable)) {
            this.f8738k = (BitmapDrawable) drawable;
            return;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        this.f8738k = new BitmapDrawable(getResources(), bitmapCreateBitmap);
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        this.f8739l = -2;
        C0544l c0544l = new C0544l(requireContext());
        c0544l.setTitle(this.f8734f);
        c0544l.setIcon(this.f8738k);
        c0544l.setPositiveButton(this.f8735g, this);
        c0544l.setNegativeButton(this.h, this);
        requireContext();
        int i5 = this.f8737j;
        View viewInflate = i5 != 0 ? getLayoutInflater().inflate(i5, (ViewGroup) null) : null;
        if (viewInflate != null) {
            h(viewInflate);
            c0544l.setView(viewInflate);
        } else {
            c0544l.setMessage(this.f8736i);
        }
        j(c0544l);
        DialogInterfaceC0545m dialogInterfaceC0545mCreate = c0544l.create();
        if (this instanceof EditTextPreferenceDialogFragmentCompat) {
            q.a(dialogInterfaceC0545mCreate.getWindow());
        }
        return dialogInterfaceC0545mCreate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        i(this.f8739l == -1);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.f8734f);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.f8735g);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.h);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.f8736i);
        bundle.putInt("PreferenceDialogFragment.layout", this.f8737j);
        BitmapDrawable bitmapDrawable = this.f8738k;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }
}
