package androidx.picker.eyeDropper;

import E0.a;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.KeyguardManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.C0138c;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class SeslEyeDropperActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static a f7859k;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Bitmap f7860f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ImageView f7861g;
    public SeslMagnifyingView h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f7862i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f7863j;

    public final void e(int i5, int i7, int i9) {
        SeslMagnifyingView seslMagnifyingView = this.h;
        float f2 = i5;
        float f7 = i7;
        seslMagnifyingView.f7865f = f2;
        seslMagnifyingView.f7866g = f7;
        seslMagnifyingView.h = i9;
        seslMagnifyingView.invalidate();
        if (i7 <= ((double) this.f7860f.getHeight()) * 0.2d) {
            this.h.setY((this.f7862i.getHeight() / 2.0f) + f7 + getResources().getDimensionPixelSize(R.dimen.sesl_eyedropper_y_offset));
        } else {
            this.h.setY(f7 - (((this.f7862i.getHeight() / 2.0f) + this.h.getHeight()) + getResources().getDimensionPixelSize(R.dimen.sesl_eyedropper_y_offset)));
        }
        this.h.setX(f2 - (r8.getWidth() / 2.0f));
        this.f7862i.setX(f2 - (r8.getWidth() / 2.0f));
        this.f7862i.setY(f7 - (r6.getHeight() / 2.0f));
    }

    @Override // android.app.Activity
    public final void finishAfterTransition() {
        super.finishAfterTransition();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        finishAfterTransition();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        int i5 = 1;
        super.onCreate(bundle);
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
        if (keyguardManager != null && keyguardManager.isKeyguardLocked()) {
            keyguardManager.requestDismissKeyguard(this, null);
        }
        getWindow().setFlags(512, 512);
        setContentView(R.layout.activity_eye_dropper);
        ImageView imageView = (ImageView) findViewById(R.id.screenshotView);
        this.f7861g = imageView;
        imageView.setImportantForAccessibility(2);
        this.h = (SeslMagnifyingView) findViewById(R.id.magnifierView);
        this.f7862i = findViewById(R.id.pointerView);
        this.f7861g.post(new C6.a(21, this));
        this.f7861g.setClickable(false);
        this.f7861g.setEnabled(false);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_eyedropper_y_animation_offset);
        PathInterpolator pathInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.f7862i, "scaleX", 0.0f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.f7862i, "scaleY", 0.0f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.h, "scaleX", 0.0f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(this.h, "scaleY", 0.0f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat5 = ObjectAnimator.ofFloat(this.f7862i, "translationY", 0.0f, dimensionPixelSize);
        objectAnimatorOfFloat3.setDuration(400L).setInterpolator(pathInterpolator);
        objectAnimatorOfFloat4.setDuration(400L).setInterpolator(pathInterpolator);
        objectAnimatorOfFloat.setDuration(400L).setInterpolator(pathInterpolator);
        objectAnimatorOfFloat2.setDuration(400L).setInterpolator(pathInterpolator);
        objectAnimatorOfFloat5.setDuration(400L).setInterpolator(pathInterpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat3, objectAnimatorOfFloat4, objectAnimatorOfFloat, objectAnimatorOfFloat2, objectAnimatorOfFloat5);
        this.f7862i.setVisibility(0);
        this.h.setVisibility(0);
        animatorSet.addListener(new C0138c(6, this));
        animatorSet.start();
        this.f7861g.setOnTouchListener(new androidx.picker.features.composable.widget.a(i5, this));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        f7859k = null;
        super.onDestroy();
    }
}
