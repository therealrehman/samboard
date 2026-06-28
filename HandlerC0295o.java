package androidx.picker.widget;

import a.C0113b;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.widget.d2;
import androidx.media.MediaBrowserServiceCompat;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.samsung.android.keyscafe.R;
import f6.AbstractC0527a;

/* JADX INFO: renamed from: androidx.picker.widget.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class HandlerC0295o extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8434a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f8435b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HandlerC0295o(Object obj, Looper looper, int i5) {
        super(looper);
        this.f8434a = i5;
        this.f8435b = obj;
    }

    public void a(Runnable runnable) {
        if (Thread.currentThread() == getLooper().getThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (this.f8434a) {
            case 0:
                super.handleMessage(message);
                int i5 = message.what;
                SeslDatePicker seslDatePicker = (SeslDatePicker) this.f8435b;
                if (i5 == 1000) {
                    if (seslDatePicker.f8173r.get(1) > seslDatePicker.getMaxYear() || seslDatePicker.f8173r.get(1) < seslDatePicker.getMinYear()) {
                        return;
                    }
                    String strA = SeslDatePicker.a(seslDatePicker, seslDatePicker.f8173r);
                    seslDatePicker.f8144U.setText(strA);
                    if (!SeslDatePicker.a(seslDatePicker, seslDatePicker.f8173r).equals(SeslDatePicker.a(seslDatePicker, seslDatePicker.o))) {
                        seslDatePicker.f8142S.announceForAccessibility(seslDatePicker.f8144U.getText());
                    }
                    String string = seslDatePicker.f8152f.getString(seslDatePicker.f8180v == 0 ? R.string.sesl_date_picker_switch_to_month_day_year_view_description : R.string.sesl_date_picker_switch_to_calendar_description);
                    seslDatePicker.getClass();
                    seslDatePicker.f8144U.setContentDescription(strA + ", " + string);
                    return;
                }
                if (i5 != 1001) {
                    return;
                }
                if (seslDatePicker.f8180v == 1) {
                    SeslDatePicker.c(seslDatePicker, 0.0f, false);
                    SeslDatePicker.d(seslDatePicker, 0.0f, false);
                    seslDatePicker.f8155g0.setImportantForAccessibility(2);
                    seslDatePicker.f8156h0.setImportantForAccessibility(2);
                    seslDatePicker.f8144U.sendAccessibilityEvent(8);
                    return;
                }
                int iR = AbstractC0527a.r();
                if (iR != -1) {
                    com.bumptech.glide.d.C(iR, seslDatePicker.f8155g0);
                    com.bumptech.glide.d.C(iR, seslDatePicker.f8156h0);
                }
                d2.a(seslDatePicker.f8155g0, seslDatePicker.getResources().getString(R.string.sesl_date_picker_decrement_month));
                d2.a(seslDatePicker.f8156h0, seslDatePicker.getResources().getString(R.string.sesl_date_picker_increment_month));
                seslDatePicker.f8155g0.setImportantForAccessibility(1);
                seslDatePicker.f8156h0.setImportantForAccessibility(1);
                int i7 = seslDatePicker.f8136M;
                if (i7 > 0 && i7 < seslDatePicker.f8137N - 1) {
                    SeslDatePicker.c(seslDatePicker, 1.0f, true);
                    SeslDatePicker.d(seslDatePicker, 1.0f, true);
                    return;
                }
                int i9 = seslDatePicker.f8137N;
                if (i9 == 1) {
                    SeslDatePicker.c(seslDatePicker, 0.4f, false);
                    SeslDatePicker.d(seslDatePicker, 0.4f, false);
                    seslDatePicker.l();
                    return;
                } else if (i7 == 0) {
                    SeslDatePicker.c(seslDatePicker, 0.4f, false);
                    SeslDatePicker.d(seslDatePicker, 1.0f, true);
                    seslDatePicker.l();
                    return;
                } else {
                    if (i7 == i9 - 1) {
                        SeslDatePicker.c(seslDatePicker, 1.0f, true);
                        SeslDatePicker.d(seslDatePicker, 0.4f, false);
                        seslDatePicker.l();
                        return;
                    }
                    return;
                }
            case 1:
                if (message.what != 1) {
                    return;
                }
                PreferenceFragment preferenceFragment = (PreferenceFragment) this.f8435b;
                PreferenceScreen preferenceScreen = preferenceFragment.f8741f.f8633g;
                if (preferenceScreen != null) {
                    preferenceFragment.f8742g.setAdapter(new androidx.preference.A(preferenceScreen));
                    preferenceScreen.m();
                    return;
                }
                return;
            case 2:
                if (message.what != 1) {
                    return;
                }
                ((PreferenceFragmentCompat) this.f8435b).bindPreferences();
                return;
            default:
                Bundle data = message.getData();
                int i10 = message.what;
                B8.e eVar = (B8.e) this.f8435b;
                switch (i10) {
                    case 1:
                        Bundle bundle = data.getBundle("data_root_hints");
                        android.support.v4.media.session.f.s(bundle);
                        String string2 = data.getString("data_package_name");
                        int i11 = data.getInt("data_calling_pid");
                        int i12 = data.getInt("data_calling_uid");
                        M3.g gVar = new M3.g(19, message.replyTo);
                        MediaBrowserServiceCompat mediaBrowserServiceCompat = (MediaBrowserServiceCompat) eVar.f286f;
                        if (string2 != null) {
                            for (String str : mediaBrowserServiceCompat.getPackageManager().getPackagesForUid(i12)) {
                                if (str.equals(string2)) {
                                    mediaBrowserServiceCompat.f7849g.a(new c0.d(eVar, gVar, string2, i11, i12, bundle, 0));
                                    return;
                                }
                            }
                        } else {
                            mediaBrowserServiceCompat.getClass();
                        }
                        throw new IllegalArgumentException("Package/uid mismatch: uid=" + i12 + " package=" + string2);
                    case 2:
                        ((MediaBrowserServiceCompat) eVar.f286f).f7849g.a(new c0.e(eVar, new M3.g(19, message.replyTo), 0));
                        return;
                    case 3:
                        Bundle bundle2 = data.getBundle("data_options");
                        android.support.v4.media.session.f.s(bundle2);
                        ((MediaBrowserServiceCompat) eVar.f286f).f7849g.a(new M1.a(eVar, new M3.g(19, message.replyTo), data.getString("data_media_item_id"), data.getBinder("data_callback_token"), bundle2, 1));
                        return;
                    case 4:
                        ((MediaBrowserServiceCompat) eVar.f286f).f7849g.a(new c0.f(eVar, new M3.g(19, message.replyTo), data.getString("data_media_item_id"), data.getBinder("data_callback_token"), 2));
                        return;
                    case 5:
                        String string3 = data.getString("data_media_item_id");
                        C0113b c0113b = (C0113b) data.getParcelable("data_result_receiver");
                        M3.g gVar2 = new M3.g(19, message.replyTo);
                        eVar.getClass();
                        if (TextUtils.isEmpty(string3) || c0113b == null) {
                            return;
                        }
                        ((MediaBrowserServiceCompat) eVar.f286f).f7849g.a(new c0.f(eVar, gVar2, string3, c0113b, 0));
                        return;
                    case 6:
                        Bundle bundle3 = data.getBundle("data_root_hints");
                        android.support.v4.media.session.f.s(bundle3);
                        ((MediaBrowserServiceCompat) eVar.f286f).f7849g.a(new c0.d(eVar, new M3.g(19, message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle3, 1));
                        return;
                    case 7:
                        ((MediaBrowserServiceCompat) eVar.f286f).f7849g.a(new c0.e(eVar, new M3.g(19, message.replyTo), 1));
                        return;
                    case 8:
                        Bundle bundle4 = data.getBundle("data_search_extras");
                        android.support.v4.media.session.f.s(bundle4);
                        String string4 = data.getString("data_search_query");
                        C0113b c0113b2 = (C0113b) data.getParcelable("data_result_receiver");
                        M3.g gVar3 = new M3.g(19, message.replyTo);
                        eVar.getClass();
                        if (TextUtils.isEmpty(string4) || c0113b2 == null) {
                            return;
                        }
                        ((MediaBrowserServiceCompat) eVar.f286f).f7849g.a(new c0.f(eVar, gVar3, string4, bundle4, c0113b2));
                        return;
                    case 9:
                        Bundle bundle5 = data.getBundle("data_custom_action_extras");
                        android.support.v4.media.session.f.s(bundle5);
                        String string5 = data.getString("data_custom_action");
                        C0113b c0113b3 = (C0113b) data.getParcelable("data_result_receiver");
                        M3.g gVar4 = new M3.g(19, message.replyTo);
                        eVar.getClass();
                        if (TextUtils.isEmpty(string5) || c0113b3 == null) {
                            return;
                        }
                        ((MediaBrowserServiceCompat) eVar.f286f).f7849g.a(new M1.a(eVar, gVar4, string5, bundle5, c0113b3, 2));
                        return;
                    default:
                        Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                        return;
                }
        }
    }

    @Override // android.os.Handler
    public boolean sendMessageAtTime(Message message, long j5) {
        switch (this.f8434a) {
            case 3:
                Bundle data = message.getData();
                data.setClassLoader(android.support.v4.media.c.class.getClassLoader());
                data.putInt("data_calling_uid", Binder.getCallingUid());
                data.putInt("data_calling_pid", Binder.getCallingPid());
                break;
        }
        return super.sendMessageAtTime(message, j5);
    }

    public HandlerC0295o(PreferenceFragment preferenceFragment) {
        this.f8434a = 1;
        this.f8435b = preferenceFragment;
    }

    public HandlerC0295o(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        this.f8434a = 3;
        this.f8435b = new B8.e(23, mediaBrowserServiceCompat);
    }
}
