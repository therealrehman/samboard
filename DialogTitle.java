package androidx.appcompat.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class DialogTitle extends C0154h0 {
    public DialogTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.appcompat.widget.C0154h0, android.widget.TextView, android.view.View
    public final void onMeasure(int i5, int i7) {
        int lineCount;
        super.onMeasure(i5, i7);
        Layout layout = getLayout();
        if (layout == null || (lineCount = layout.getLineCount()) <= 0 || layout.getEllipsisCount(lineCount - 1) <= 0) {
            return;
        }
        setSingleLine(false);
        setMaxLines(2);
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.sesl_dialog_title_text_size);
        if (dimensionPixelSize != 0) {
            float f2 = getContext().getResources().getConfiguration().fontScale;
            float f7 = dimensionPixelSize;
            if (f2 > 1.3f) {
                f7 = (f7 / f2) * 1.3f;
            }
            setTextSize(0, f7);
        }
        super.onMeasure(i5, i7);
    }
}
