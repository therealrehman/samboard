package androidx.preference;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.preference.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnCreateContextMenuListenerC0320o implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Preference f8808e;

    public ViewOnCreateContextMenuListenerC0320o(Preference preference) {
        this.f8808e = preference;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        Preference preference = this.f8808e;
        CharSequence charSequenceH = preference.h();
        if (!preference.G || TextUtils.isEmpty(charSequenceH)) {
            return;
        }
        contextMenu.setHeaderTitle(charSequenceH);
        contextMenu.add(0, 0, 0, R.string.copy).setOnMenuItemClickListener(this);
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        Preference preference = this.f8808e;
        ClipboardManager clipboardManager = (ClipboardManager) preference.f8706e.getSystemService("clipboard");
        CharSequence charSequenceH = preference.h();
        clipboardManager.setPrimaryClip(ClipData.newPlainText("Preference", charSequenceH));
        Context context = preference.f8706e;
        Toast.makeText(context, context.getString(R.string.preference_copied, charSequenceH), 0).show();
        return true;
    }
}
