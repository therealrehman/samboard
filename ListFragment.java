package androidx.fragment.app;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ListFragment extends Fragment {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f7527e = new Handler();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RunnableC0247n f7528f = new RunnableC0247n(3, this);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final t0 f7529g = new t0(this);
    public ListAdapter h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ListView f7530i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f7531j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public View f7532k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f7533l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f7534m;

    public final void f() {
        if (this.f7530i != null) {
            return;
        }
        View view = getView();
        if (view == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (view instanceof ListView) {
            this.f7530i = (ListView) view;
        } else {
            TextView textView = (TextView) view.findViewById(16711681);
            if (textView == null) {
                this.f7531j = view.findViewById(R.id.empty);
            } else {
                textView.setVisibility(8);
            }
            this.f7532k = view.findViewById(16711682);
            this.f7533l = view.findViewById(16711683);
            View viewFindViewById = view.findViewById(R.id.list);
            if (!(viewFindViewById instanceof ListView)) {
                if (viewFindViewById != null) {
                    throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                }
                throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
            }
            ListView listView = (ListView) viewFindViewById;
            this.f7530i = listView;
            View view2 = this.f7531j;
            if (view2 != null) {
                listView.setEmptyView(view2);
            }
        }
        this.f7534m = true;
        this.f7530i.setOnItemClickListener(this.f7529g);
        ListAdapter listAdapter = this.h;
        if (listAdapter != null) {
            this.h = listAdapter;
            ListView listView2 = this.f7530i;
            if (listView2 != null) {
                listView2.setAdapter(listAdapter);
                if (!this.f7534m) {
                    g(true, requireView().getWindowToken() != null);
                }
            }
        } else if (this.f7532k != null) {
            g(false, false);
        }
        this.f7527e.post(this.f7528f);
    }

    public final void g(boolean z9, boolean z10) {
        f();
        View view = this.f7532k;
        if (view == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (this.f7534m == z9) {
            return;
        }
        this.f7534m = z9;
        if (z9) {
            if (z10) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
                this.f7533l.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
            } else {
                view.clearAnimation();
                this.f7533l.clearAnimation();
            }
            this.f7532k.setVisibility(8);
            this.f7533l.setVisibility(0);
            return;
        }
        if (z10) {
            view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
            this.f7533l.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        } else {
            view.clearAnimation();
            this.f7533l.clearAnimation();
        }
        this.f7532k.setVisibility(0);
        this.f7533l.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context contextRequireContext = requireContext();
        FrameLayout frameLayout = new FrameLayout(contextRequireContext);
        LinearLayout linearLayout = new LinearLayout(contextRequireContext);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(contextRequireContext, null, R.attr.progressBarStyleLarge), new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(contextRequireContext);
        frameLayout2.setId(16711683);
        TextView textView = new TextView(contextRequireContext);
        textView.setId(16711681);
        textView.setGravity(17);
        frameLayout2.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        ListView listView = new ListView(contextRequireContext);
        listView.setId(R.id.list);
        listView.setDrawSelectorOnTop(false);
        frameLayout2.addView(listView, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroyView() {
        this.f7527e.removeCallbacks(this.f7528f);
        this.f7530i = null;
        this.f7534m = false;
        this.f7533l = null;
        this.f7532k = null;
        this.f7531j = null;
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        f();
    }
}
