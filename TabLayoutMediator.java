package com.google.android.material.tabs;

import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.AbstractC0345l0;
import androidx.viewpager2.widget.ViewPager2;
import c1.j;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TabLayoutMediator {
    private AbstractC0341j0 adapter;
    private boolean attached;
    private final boolean autoRefresh;
    private TabLayoutOnPageChangeCallback onPageChangeCallback;
    private TabLayout.OnTabSelectedListener onTabSelectedListener;
    private AbstractC0345l0 pagerAdapterObserver;
    private final boolean smoothScroll;
    private final TabConfigurationStrategy tabConfigurationStrategy;
    private final TabLayout tabLayout;
    private final ViewPager2 viewPager;

    public class PagerAdapterObserver extends AbstractC0345l0 {
        public PagerAdapterObserver() {
        }

        @Override // androidx.recyclerview.widget.AbstractC0345l0
        public void onChanged() {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeChanged(int i5, int i7) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.AbstractC0345l0
        public void onItemRangeInserted(int i5, int i7) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.AbstractC0345l0
        public void onItemRangeMoved(int i5, int i7, int i9) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.AbstractC0345l0
        public void onItemRangeRemoved(int i5, int i7) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.AbstractC0345l0
        public void onItemRangeChanged(int i5, int i7, Object obj) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }
    }

    public interface TabConfigurationStrategy {
        void onConfigureTab(TabLayout.Tab tab, int i5);
    }

    public static class TabLayoutOnPageChangeCallback extends j {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        public TabLayoutOnPageChangeCallback(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference<>(tabLayout);
            reset();
        }

        @Override // c1.j
        public void onPageScrollStateChanged(int i5) {
            this.previousScrollState = this.scrollState;
            this.scrollState = i5;
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null) {
                tabLayout.updateViewPagerScrollState(this.scrollState);
            }
        }

        @Override // c1.j
        public void onPageScrolled(int i5, float f2, int i7) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null) {
                int i9 = this.scrollState;
                tabLayout.setScrollPosition(i5, f2, i9 != 2 || this.previousScrollState == 1, (i9 == 2 && this.previousScrollState == 0) ? false : true, false);
            }
        }

        @Override // c1.j
        public void onPageSelected(int i5) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i5 || i5 >= tabLayout.getTabCount()) {
                return;
            }
            int i7 = this.scrollState;
            tabLayout.selectTab(tabLayout.getTabAt(i5), i7 == 0 || (i7 == 2 && this.previousScrollState == 0));
        }

        public void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    public static class ViewPagerOnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        private final boolean smoothScroll;
        private final ViewPager2 viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager2 viewPager2, boolean z9) {
            this.viewPager = viewPager2;
            this.smoothScroll = z9;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            this.viewPager.c(tab.getPosition(), this.smoothScroll);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }
    }

    public TabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager2, TabConfigurationStrategy tabConfigurationStrategy) {
        this(tabLayout, viewPager2, true, tabConfigurationStrategy);
    }

    public void attach() {
        if (this.attached) {
            throw new IllegalStateException("TabLayoutMediator is already attached");
        }
        AbstractC0341j0 adapter = this.viewPager.getAdapter();
        this.adapter = adapter;
        if (adapter == null) {
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        this.attached = true;
        TabLayoutOnPageChangeCallback tabLayoutOnPageChangeCallback = new TabLayoutOnPageChangeCallback(this.tabLayout);
        this.onPageChangeCallback = tabLayoutOnPageChangeCallback;
        ((ArrayList) this.viewPager.f9613g.f9844b).add(tabLayoutOnPageChangeCallback);
        ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new ViewPagerOnTabSelectedListener(this.viewPager, this.smoothScroll);
        this.onTabSelectedListener = viewPagerOnTabSelectedListener;
        this.tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) viewPagerOnTabSelectedListener);
        if (this.autoRefresh) {
            PagerAdapterObserver pagerAdapterObserver = new PagerAdapterObserver();
            this.pagerAdapterObserver = pagerAdapterObserver;
            this.adapter.registerAdapterDataObserver(pagerAdapterObserver);
        }
        populateTabsFromPagerAdapter();
        this.tabLayout.setScrollPosition(this.viewPager.getCurrentItem(), 0.0f, true);
    }

    public void detach() {
        AbstractC0341j0 abstractC0341j0;
        if (this.autoRefresh && (abstractC0341j0 = this.adapter) != null) {
            abstractC0341j0.unregisterAdapterDataObserver(this.pagerAdapterObserver);
            this.pagerAdapterObserver = null;
        }
        this.tabLayout.removeOnTabSelectedListener(this.onTabSelectedListener);
        ((ArrayList) this.viewPager.f9613g.f9844b).remove(this.onPageChangeCallback);
        this.onTabSelectedListener = null;
        this.onPageChangeCallback = null;
        this.adapter = null;
        this.attached = false;
    }

    public boolean isAttached() {
        return this.attached;
    }

    public void populateTabsFromPagerAdapter() {
        this.tabLayout.removeAllTabs();
        AbstractC0341j0 abstractC0341j0 = this.adapter;
        if (abstractC0341j0 != null) {
            int itemCount = abstractC0341j0.getItemCount();
            for (int i5 = 0; i5 < itemCount; i5++) {
                TabLayout.Tab tabNewTab = this.tabLayout.newTab();
                this.tabConfigurationStrategy.onConfigureTab(tabNewTab, i5);
                this.tabLayout.addTab(tabNewTab, false);
            }
            if (itemCount > 0) {
                int iMin = Math.min(this.viewPager.getCurrentItem(), this.tabLayout.getTabCount() - 1);
                if (iMin != this.tabLayout.getSelectedTabPosition()) {
                    TabLayout tabLayout = this.tabLayout;
                    tabLayout.selectTab(tabLayout.getTabAt(iMin));
                }
            }
        }
    }

    public TabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager2, boolean z9, TabConfigurationStrategy tabConfigurationStrategy) {
        this(tabLayout, viewPager2, z9, true, tabConfigurationStrategy);
    }

    public TabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager2, boolean z9, boolean z10, TabConfigurationStrategy tabConfigurationStrategy) {
        this.tabLayout = tabLayout;
        this.viewPager = viewPager2;
        this.autoRefresh = z9;
        this.smoothScroll = z10;
        this.tabConfigurationStrategy = tabConfigurationStrategy;
    }
}
