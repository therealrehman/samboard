package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.samsung.android.keyscafe.R;
import com.samsung.android.keyscafe.honeytea.setting.fragment.HoneyTeaSettingsFragment;
import com.samsung.android.keyscafe.honeytea.setting.view.HoneyTeaThemeListAdapter;
import g.C0543k;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.core.view.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0230w implements K.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f7264b;

    public /* synthetic */ C0230w(int i5, Object obj) {
        this.f7263a = i5;
        this.f7264b = obj;
    }

    @Override // K.a
    public final void accept(Object obj) {
        LinearLayout linearLayout;
        switch (this.f7263a) {
            case 0:
                ((View) this.f7264b).setTouchDelegate((B) obj);
                break;
            case 1:
                androidx.window.layout.C info = (androidx.window.layout.C) obj;
                kotlin.jvm.internal.j.e(info, "info");
                ((A8.n) this.f7264b).d(info);
                break;
            case 2:
                HoneyTeaSettingsFragment.switchListener$lambda$1$lambda$0((HoneyTeaSettingsFragment) this.f7264b, ((Boolean) obj).booleanValue());
                break;
            case 3:
                HoneyTeaThemeListAdapter.updateData$lambda$0((HoneyTeaThemeListAdapter) this.f7264b, (ArrayList) obj);
                break;
            default:
                ViewGroup viewGroup = (ViewGroup) obj;
                ((C0543k) this.f7264b).getClass();
                if (viewGroup != null && (linearLayout = (LinearLayout) viewGroup.findViewById(R.id.buttonBarLayout)) != null) {
                    linearLayout.post(new C6.a(19, linearLayout));
                }
                break;
        }
    }
}
