package androidx.picker.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.icu.text.AlphabeticIndex;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.picker.controller.strategy.AppItemStrategy;
import androidx.picker.controller.strategy.Strategy;
import androidx.picker.loader.select.SelectableItem;
import androidx.recyclerview.widget.H0;
import androidx.recyclerview.widget.RecyclerView;
import e0.AbstractC0479a;
import f0.AbstractC0513c;
import f0.C0517g;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import y0.C1210g;
import z0.InterfaceC1280a;

/* JADX INFO: renamed from: androidx.picker.widget.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0288h extends RecyclerView implements H0, i0.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0517g f8378e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f8379f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f8380g;
    public final F8.C h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C1210g f8381i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8382j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f8383k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f8384l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0286f f8385m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0286f f8386n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0288h(Context context, AttributeSet attributeSet) throws Throwable {
        String str;
        String string;
        o0.c cVar;
        Strategy appItemStrategy;
        TypedArray typedArrayObtainStyledAttributes;
        super(context, attributeSet, 0);
        int i5 = 0;
        this.f8380g = 15;
        this.f8382j = 0;
        SeslAppPickerGridView seslAppPickerGridView = (SeslAppPickerGridView) this;
        this.f8385m = new C0286f(seslAppPickerGridView, 0);
        this.f8386n = new C0286f(seslAppPickerGridView, 1);
        this.f8379f = context;
        TypedArray typedArray = null;
        String name = null;
        typedArray = null;
        try {
            try {
                typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0479a.f10582c, 0, 0);
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    string = typedArrayObtainStyledAttributes.getString(3);
                } catch (RuntimeException e3) {
                    e = e3;
                    string = null;
                    typedArray = typedArrayObtainStyledAttributes;
                    str = null;
                }
            } catch (Throwable th2) {
                th = th2;
                typedArray = typedArrayObtainStyledAttributes;
                if (typedArray != null) {
                    typedArray.recycle();
                }
                throw th;
            }
        } catch (RuntimeException e10) {
            e = e10;
            str = null;
            string = null;
        }
        try {
            name = typedArrayObtainStyledAttributes.getString(0);
            int i7 = typedArrayObtainStyledAttributes.getInt(2, 15);
            this.f8380g = i7;
            i0.b.a(this, "init strategy=" + string + ", roundedCorner=" + i7);
            i5 = typedArrayObtainStyledAttributes.getInt(1, 0);
            typedArrayObtainStyledAttributes.recycle();
        } catch (RuntimeException e11) {
            e = e11;
            String str2 = name;
            typedArray = typedArrayObtainStyledAttributes;
            str = str2;
            e.printStackTrace();
            if (typedArray != null) {
                typedArray.recycle();
            }
            name = str;
        }
        this.f8383k = i5 != 1 ? 1 : 2;
        if (name == null) {
            try {
                name = o0.c.class.getName();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException unused) {
                cVar = new o0.c(context);
            }
        }
        cVar = (o0.c) Class.forName(name).getConstructor(Context.class).newInstance(context);
        i0.b.a(this, "used appPickerContext: " + cVar);
        setRecyclerListener(this);
        this.f8381i = (C1210g) cVar.f12569d.getValue();
        try {
            Objects.requireNonNull(string);
            appItemStrategy = (Strategy) Class.forName(string).getConstructor(o0.c.class).newInstance(cVar);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException unused2) {
            appItemStrategy = new AppItemStrategy(cVar);
        }
        F8.C c5 = new F8.C(appItemStrategy);
        this.h = c5;
        RunnableC0284d runnableC0284d = new RunnableC0284d(seslAppPickerGridView, 1);
        this.f8381i.f14966a = new C0287g(seslAppPickerGridView);
        ((ArrayList) c5.h).add(new C0285e(seslAppPickerGridView, runnableC0284d));
    }

    public List<InterfaceC1280a> getAppDataList() {
        return (ArrayList) this.h.f799j;
    }

    public int getAppListOrder() {
        return this.f8384l;
    }

    /* JADX INFO: renamed from: getLogTag */
    public String getF7850m() {
        return "SeslAppPickerView";
    }

    public int getType() {
        return this.f8382j;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOnScrollListener(this.f8386n);
        addOnScrollListener(this.f8385m);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        setAdapter(null);
        super.onDetachedFromWindow();
        removeOnScrollListener(this.f8386n);
        removeOnScrollListener(this.f8385m);
    }

    public void setAppListOrder(int i5) {
        this.f8384l = i5;
        Comparator<B0.h> bVar = i5 != 1 ? i5 != 2 ? i5 != 3 ? i5 != 4 ? null : new k0.b(new k0.c(0)) : new k0.b(new k0.c(2)) : new k0.c(0) : new k0.c(2);
        F8.C c5 = this.h;
        c5.getClass();
        ArrayList arrayList = (ArrayList) c5.f799j;
        Strategy strategy = (Strategy) c5.f798i;
        strategy.clear$picker_app_release();
        List<B0.h> elements = strategy.convert$picker_app_release(arrayList, bVar);
        kotlin.jvm.internal.j.f(elements, "elements");
        ArrayList itemList = (ArrayList) c5.f796f;
        itemList.clear();
        itemList.addAll(elements);
        for (C0285e c0285e : (ArrayList) c5.h) {
            C0517g c0517g = c0285e.f8371a.f8378e;
            if (c0517g != null) {
                kotlin.jvm.internal.j.f(itemList, "itemList");
                AbstractC0513c abstractC0513c = c0517g.f10778e;
                abstractC0513c.getClass();
                i0.b.b(abstractC0513c, "submitList list=" + itemList.size());
                ArrayList arrayList2 = abstractC0513c.f10767e;
                arrayList2.clear();
                arrayList2.addAll(itemList);
                HashMap map = abstractC0513c.f10769g;
                map.clear();
                ArrayList arrayList3 = new ArrayList();
                LocaleList locales = abstractC0513c.f10771j.getResources().getConfiguration().getLocales();
                if (locales.size() == 0) {
                    locales = new LocaleList(Locale.ENGLISH);
                }
                AlphabeticIndex alphabeticIndex = new AlphabeticIndex(locales.get(0));
                int size = locales.size();
                for (int i7 = 1; i7 < size; i7++) {
                    alphabeticIndex.addLabels(locales.get(i7));
                }
                alphabeticIndex.addLabels(Locale.ENGLISH);
                AlphabeticIndex.ImmutableIndex immutableIndexBuildImmutableIndex = alphabeticIndex.buildImmutableIndex();
                ArrayList arrayList4 = abstractC0513c.f10768f;
                abstractC0513c.f10770i = new int[arrayList4.size()];
                for (int i9 = 0; i9 < arrayList4.size(); i9++) {
                    B0.h hVar = (B0.h) arrayList4.get(i9);
                    if (hVar instanceof B0.c) {
                        String strH = ((B0.c) hVar).f160a.h();
                        if (TextUtils.isEmpty(strH)) {
                            strH = "";
                        }
                        String label = immutableIndexBuildImmutableIndex.getBucket(immutableIndexBuildImmutableIndex.getBucketIndex(strH)).getLabel();
                        if (!map.containsKey(label)) {
                            arrayList3.add(label);
                            map.put(label, Integer.valueOf(i9));
                        }
                        abstractC0513c.f10770i[i9] = arrayList3.size() - 1;
                    }
                }
                String[] strArr = new String[arrayList3.size()];
                abstractC0513c.h = strArr;
                arrayList3.toArray(strArr);
                abstractC0513c.getFilter().filter(abstractC0513c.f10772k);
            }
            c0285e.f8372b.run();
        }
    }

    public void setOnItemClickEventListener(InterfaceC0281a interfaceC0281a) {
        if (this.f8378e != null) {
            post(new RunnableC0284d(this, 0));
        }
    }

    public void setOnStateChangeListener(InterfaceC0282b interfaceC0282b) {
    }

    public void setSearchFilter(String str) {
        C0517g c0517g = this.f8378e;
        if (c0517g != null) {
            c0517g.f10778e.getFilter().filter(str);
        }
    }

    public void setStateAll(boolean z9) {
        Object next;
        SelectableItem selectableItem;
        List viewDataList = (List) this.h.f797g;
        C1210g c1210g = this.f8381i;
        c1210g.getClass();
        kotlin.jvm.internal.j.f(viewDataList, "viewDataList");
        Iterator it = viewDataList.iterator();
        while (true) {
            if (it.hasNext()) {
                next = it.next();
                if (((B0.h) next) instanceof B0.a) {
                    break;
                }
            } else {
                next = null;
                break;
            }
        }
        B0.a aVar = (B0.a) next;
        if (aVar != null) {
            ArrayList<B0.c> arrayList = new ArrayList();
            for (Object obj : viewDataList) {
                if (obj instanceof B0.c) {
                    arrayList.add(obj);
                }
            }
            for (B0.c cVar : arrayList) {
                if (!cVar.f160a.d() && (selectableItem = cVar.f162c) != null) {
                    selectableItem.setValueSilence$picker_app_release(Boolean.valueOf(z9));
                }
            }
            aVar.f158a.setValue(Boolean.valueOf(z9));
            return;
        }
        ArrayList<z0.e> arrayList2 = new ArrayList();
        for (Object obj2 : viewDataList) {
            if (obj2 instanceof z0.e) {
                arrayList2.add(obj2);
            }
        }
        for (z0.e eVar : arrayList2) {
            if (!(eVar instanceof B0.c) || !((B0.c) eVar).f160a.d()) {
                SelectableItem selectableItemM = eVar.m();
                if (selectableItemM != null) {
                    selectableItemM.setValueSilence$picker_app_release(Boolean.valueOf(z9));
                }
            }
        }
        C0287g c0287g = c1210g.f14966a;
        if (c0287g != null) {
            ((AbstractC0288h) c0287g.f8375a).getClass();
        }
    }
}
