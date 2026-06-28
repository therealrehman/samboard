package androidx.picker3.app;

import E0.a;
import E0.b;
import E0.c;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.picker3.widget.SeslColorPicker;
import androidx.picker3.widget.o;
import com.samsung.android.keyscafe.R;
import d6.AbstractC0476d;
import g.C0543k;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SeslColorPickerDialogFragment extends AppCompatDialogFragment implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f8456e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Integer f8457f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Integer f8458g = null;
    public int[] h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8459i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f8460j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f8461k = true;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f8462l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SeslColorPicker f8463m;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i5) {
        if (i5 == -2) {
            dialogInterface.dismiss();
            return;
        }
        if (i5 != -1) {
            return;
        }
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setSoftInputMode(3);
        }
        SeslColorPicker seslColorPicker = this.f8463m;
        Integer num = (Integer) seslColorPicker.f8488g.f299b;
        if (num != null) {
            seslColorPicker.f8467C.f8591a = num;
        }
        Integer num2 = seslColorPicker.getRecentColorInfo().f8591a;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.h = bundle.getIntArray("recently_used_colors");
            this.f8457f = (Integer) bundle.getSerializable("current_color");
            this.f8460j = bundle.getBoolean("opacity_bar_enabled");
            this.f8461k = bundle.getBoolean("disable_eye_dropper");
        }
    }

    @Override // androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Context context = getContext();
        FragmentActivity activity = getActivity();
        c cVar = new c(activity, s6.c.O(activity) ? 2132018055 : 2132018052);
        this.f8456e = cVar;
        cVar.e(-1, context.getString(R.string.sesl_picker_done), this);
        this.f8456e.e(-2, context.getString(R.string.sesl_picker_cancel), this);
        return this.f8456e;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Window window;
        this.f8463m = (SeslColorPicker) layoutInflater.inflate(R.layout.sesl_color_picker_oneui_3_dialog, (ViewGroup) null);
        if (getDialog() != null && (window = getDialog().getWindow()) != null) {
            window.setSoftInputMode(16);
            window.getDecorView().setOnApplyWindowInsetsListener(new b(window));
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            AbstractC0476d.t(arguments.getSerializable("color_set_listener"));
            this.f8457f = (Integer) arguments.getSerializable("current_color");
            this.h = arguments.getIntArray("recently_used_colors");
            this.f8459i = arguments.getBoolean("show_opacity_bar");
            this.f8462l = arguments.getBoolean("show_only_spectrum");
        }
        if (this.f8457f != null) {
            this.f8463m.getRecentColorInfo().f8592b = this.f8457f;
        }
        if (this.f8458g != null) {
            o recentColorInfo = this.f8463m.getRecentColorInfo();
            Integer num = this.f8458g;
            recentColorInfo.f8593c = num;
            this.f8457f = num;
        }
        if (this.h != null) {
            o recentColorInfo2 = this.f8463m.getRecentColorInfo();
            int[] iArr = this.h;
            recentColorInfo2.getClass();
            if (iArr != null) {
                int length = iArr.length;
                int i5 = SeslColorPicker.f8464W;
                ArrayList arrayList = recentColorInfo2.f8594d;
                if (length <= i5) {
                    for (int i7 : iArr) {
                        arrayList.add(Integer.valueOf(i7));
                    }
                } else {
                    for (int i9 = 0; i9 < SeslColorPicker.f8464W; i9++) {
                        arrayList.add(Integer.valueOf(iArr[i9]));
                    }
                }
            }
        }
        if (this.f8462l) {
            SeslColorPicker seslColorPicker = this.f8463m;
            seslColorPicker.f8497r.setVisibility(8);
            seslColorPicker.a();
            if (!seslColorPicker.f8490j) {
                seslColorPicker.f8490j = true;
            }
            seslColorPicker.o.setVisibility(8);
            seslColorPicker.f8495p.setVisibility(0);
            seslColorPicker.f8472I.setInputType(0);
            seslColorPicker.f8473J.setInputType(0);
            seslColorPicker.f8475L.setInputType(0);
            seslColorPicker.f8474K.setInputType(0);
        }
        this.f8463m.setOpacityBarEnabled(this.f8460j);
        this.f8463m.setEyeDropperDisable(this.f8461k);
        this.f8463m.h();
        this.f8463m.setOnColorChangedListener(null);
        this.f8463m.b(this.f8459i);
        c cVar = this.f8456e;
        SeslColorPicker seslColorPicker2 = this.f8463m;
        C0543k c0543k = cVar.f11153e;
        c0543k.h = seslColorPicker2;
        c0543k.f11135i = 0;
        c0543k.f11140n = false;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.f8463m.setOnEyeDropperListener(new a(this, arguments, activity));
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f8463m.getRecentColorInfo().f8592b = this.f8463m.getRecentColorInfo().f8591a;
        bundle.putIntArray("recently_used_colors", this.h);
        bundle.putSerializable("current_color", this.f8457f);
        bundle.putBoolean("opacity_bar_enabled", this.f8460j);
        bundle.putBoolean("disable_eye_dropper", this.f8461k);
    }
}
