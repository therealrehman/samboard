package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.Property;
import java.util.ArrayList;
import java.util.List;
import q.k;

/* JADX INFO: loaded from: classes.dex */
public class MotionSpec {
    private static final String TAG = "MotionSpec";
    private final k timings = new k();
    private final k propertyValues = new k();

    private static void addInfoFromAnimator(MotionSpec motionSpec, Animator animator) {
        if (!(animator instanceof ObjectAnimator)) {
            throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
        }
        ObjectAnimator objectAnimator = (ObjectAnimator) animator;
        motionSpec.setPropertyValues(objectAnimator.getPropertyName(), objectAnimator.getValues());
        motionSpec.setTiming(objectAnimator.getPropertyName(), MotionTiming.createFromAnimator(objectAnimator));
    }

    private PropertyValuesHolder[] clonePropertyValuesHolder(PropertyValuesHolder[] propertyValuesHolderArr) {
        PropertyValuesHolder[] propertyValuesHolderArr2 = new PropertyValuesHolder[propertyValuesHolderArr.length];
        for (int i5 = 0; i5 < propertyValuesHolderArr.length; i5++) {
            propertyValuesHolderArr2[i5] = propertyValuesHolderArr[i5].clone();
        }
        return propertyValuesHolderArr2;
    }

    public static MotionSpec createFromAttribute(Context context, TypedArray typedArray, int i5) {
        int resourceId;
        if (!typedArray.hasValue(i5) || (resourceId = typedArray.getResourceId(i5, 0)) == 0) {
            return null;
        }
        return createFromResource(context, resourceId);
    }

    public static MotionSpec createFromResource(Context context, int i5) {
        try {
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, i5);
            if (animatorLoadAnimator instanceof AnimatorSet) {
                return createSpecFromAnimators(((AnimatorSet) animatorLoadAnimator).getChildAnimations());
            }
            if (animatorLoadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(animatorLoadAnimator);
            return createSpecFromAnimators(arrayList);
        } catch (Exception e3) {
            Log.w(TAG, "Can't load animation resource ID #0x" + Integer.toHexString(i5), e3);
            return null;
        }
    }

    private static MotionSpec createSpecFromAnimators(List<Animator> list) {
        MotionSpec motionSpec = new MotionSpec();
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            addInfoFromAnimator(motionSpec, list.get(i5));
        }
        return motionSpec;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MotionSpec) {
            return this.timings.equals(((MotionSpec) obj).timings);
        }
        return false;
    }

    public <T> ObjectAnimator getAnimator(String str, T t8, Property<T, ?> property) {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(t8, getPropertyValues(str));
        objectAnimatorOfPropertyValuesHolder.setProperty(property);
        getTiming(str).apply(objectAnimatorOfPropertyValuesHolder);
        return objectAnimatorOfPropertyValuesHolder;
    }

    public PropertyValuesHolder[] getPropertyValues(String str) {
        if (hasPropertyValues(str)) {
            return clonePropertyValuesHolder((PropertyValuesHolder[]) this.propertyValues.getOrDefault(str, null));
        }
        throw new IllegalArgumentException();
    }

    public MotionTiming getTiming(String str) {
        if (hasTiming(str)) {
            return (MotionTiming) this.timings.getOrDefault(str, null);
        }
        throw new IllegalArgumentException();
    }

    public long getTotalDuration() {
        int i5 = this.timings.f12929g;
        long jMax = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            MotionTiming motionTiming = (MotionTiming) this.timings.l(i7);
            jMax = Math.max(jMax, motionTiming.getDuration() + motionTiming.getDelay());
        }
        return jMax;
    }

    public boolean hasPropertyValues(String str) {
        return this.propertyValues.getOrDefault(str, null) != null;
    }

    public boolean hasTiming(String str) {
        return this.timings.getOrDefault(str, null) != null;
    }

    public int hashCode() {
        return this.timings.hashCode();
    }

    public void setPropertyValues(String str, PropertyValuesHolder[] propertyValuesHolderArr) {
        this.propertyValues.put(str, propertyValuesHolderArr);
    }

    public void setTiming(String str, MotionTiming motionTiming) {
        this.timings.put(str, motionTiming);
    }

    public String toString() {
        return "\n" + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.timings + "}\n";
    }
}
