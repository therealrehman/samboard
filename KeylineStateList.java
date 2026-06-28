package com.google.android.material.carousel;

import com.bumptech.glide.c;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class KeylineStateList {
    private static final int NO_INDEX = -1;
    private final KeylineState defaultState;
    private final float endShiftRange;
    private final List<KeylineState> endStateSteps;
    private final float[] endStateStepsInterpolationPoints;
    private final float startShiftRange;
    private final List<KeylineState> startStateSteps;
    private final float[] startStateStepsInterpolationPoints;

    private KeylineStateList(KeylineState keylineState, List<KeylineState> list, List<KeylineState> list2) {
        this.defaultState = keylineState;
        this.startStateSteps = Collections.unmodifiableList(list);
        this.endStateSteps = Collections.unmodifiableList(list2);
        float f2 = list.get(list.size() - 1).getFirstKeyline().loc - keylineState.getFirstKeyline().loc;
        this.startShiftRange = f2;
        float f7 = keylineState.getLastKeyline().loc - list2.get(list2.size() - 1).getLastKeyline().loc;
        this.endShiftRange = f7;
        this.startStateStepsInterpolationPoints = getStateStepInterpolationPoints(f2, list, true);
        this.endStateStepsInterpolationPoints = getStateStepInterpolationPoints(f7, list2, false);
    }

    private KeylineState closestStateStepFromInterpolation(List<KeylineState> list, float f2, float[] fArr) {
        float[] stateStepsRange = getStateStepsRange(list, f2, fArr);
        return stateStepsRange[0] > 0.5f ? list.get((int) stateStepsRange[2]) : list.get((int) stateStepsRange[1]);
    }

    private static int findFirstIndexAfterLastFocalKeylineWithMask(KeylineState keylineState, float f2) {
        for (int lastFocalKeylineIndex = keylineState.getLastFocalKeylineIndex(); lastFocalKeylineIndex < keylineState.getKeylines().size(); lastFocalKeylineIndex++) {
            if (f2 == keylineState.getKeylines().get(lastFocalKeylineIndex).mask) {
                return lastFocalKeylineIndex;
            }
        }
        return keylineState.getKeylines().size() - 1;
    }

    private static int findFirstNonAnchorKeylineIndex(KeylineState keylineState) {
        for (int i5 = 0; i5 < keylineState.getKeylines().size(); i5++) {
            if (!keylineState.getKeylines().get(i5).isAnchor) {
                return i5;
            }
        }
        return -1;
    }

    private static int findLastIndexBeforeFirstFocalKeylineWithMask(KeylineState keylineState, float f2) {
        for (int firstFocalKeylineIndex = keylineState.getFirstFocalKeylineIndex() - 1; firstFocalKeylineIndex >= 0; firstFocalKeylineIndex--) {
            if (f2 == keylineState.getKeylines().get(firstFocalKeylineIndex).mask) {
                return firstFocalKeylineIndex;
            }
        }
        return 0;
    }

    private static int findLastNonAnchorKeylineIndex(KeylineState keylineState) {
        for (int size = keylineState.getKeylines().size() - 1; size >= 0; size--) {
            if (!keylineState.getKeylines().get(size).isAnchor) {
                return size;
            }
        }
        return -1;
    }

    public static KeylineStateList from(Carousel carousel, KeylineState keylineState) {
        return new KeylineStateList(keylineState, getStateStepsStart(carousel, keylineState), getStateStepsEnd(carousel, keylineState));
    }

    private static float[] getStateStepInterpolationPoints(float f2, List<KeylineState> list, boolean z9) {
        int size = list.size();
        float[] fArr = new float[size];
        int i5 = 1;
        while (i5 < size) {
            int i7 = i5 - 1;
            KeylineState keylineState = list.get(i7);
            KeylineState keylineState2 = list.get(i5);
            fArr[i5] = i5 == size + (-1) ? 1.0f : fArr[i7] + ((z9 ? keylineState2.getFirstKeyline().loc - keylineState.getFirstKeyline().loc : keylineState.getLastKeyline().loc - keylineState2.getLastKeyline().loc) / f2);
            i5++;
        }
        return fArr;
    }

    private static List<KeylineState> getStateStepsEnd(Carousel carousel, KeylineState keylineState) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState);
        int iFindLastNonAnchorKeylineIndex = findLastNonAnchorKeylineIndex(keylineState);
        if (!isLastFocalItemVisibleAtRightOfContainer(carousel, keylineState) && iFindLastNonAnchorKeylineIndex != -1) {
            int lastFocalKeylineIndex = iFindLastNonAnchorKeylineIndex - keylineState.getLastFocalKeylineIndex();
            float containerWidth = carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight();
            float f2 = keylineState.getFirstKeyline().locOffset - (keylineState.getFirstKeyline().maskedItemSize / 2.0f);
            float f7 = 0.0f;
            if (lastFocalKeylineIndex <= 0 && keylineState.getLastFocalKeyline().cutoff > 0.0f) {
                arrayList.add(shiftKeylinesAndCreateKeylineState(keylineState, f2 - keylineState.getLastFocalKeyline().cutoff, containerWidth));
                return arrayList;
            }
            int i5 = 0;
            while (i5 < lastFocalKeylineIndex) {
                KeylineState keylineState2 = (KeylineState) arrayList.get(arrayList.size() - 1);
                int i7 = iFindLastNonAnchorKeylineIndex - i5;
                float f9 = f7 + keylineState.getKeylines().get(i7).cutoff;
                int i9 = i7 + 1;
                arrayList.add(moveKeylineAndCreateKeylineState(keylineState2, iFindLastNonAnchorKeylineIndex, i9 < keylineState.getKeylines().size() ? findLastIndexBeforeFirstFocalKeylineWithMask(keylineState2, keylineState.getKeylines().get(i9).mask) + 1 : 0, f2 - f9, keylineState.getFirstFocalKeylineIndex() + i5 + 1, keylineState.getLastFocalKeylineIndex() + i5 + 1, containerWidth));
                i5++;
                f7 = f9;
            }
        }
        return arrayList;
    }

    private static float[] getStateStepsRange(List<KeylineState> list, float f2, float[] fArr) {
        int size = list.size();
        float f7 = fArr[0];
        int i5 = 1;
        while (i5 < size) {
            float f9 = fArr[i5];
            if (f2 <= f9) {
                return new float[]{AnimationUtils.lerp(0.0f, 1.0f, f7, f9, f2), i5 - 1, i5};
            }
            i5++;
            f7 = f9;
        }
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    private static List<KeylineState> getStateStepsStart(Carousel carousel, KeylineState keylineState) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState);
        int iFindFirstNonAnchorKeylineIndex = findFirstNonAnchorKeylineIndex(keylineState);
        if (!isFirstFocalItemAtLeftOfContainer(keylineState) && iFindFirstNonAnchorKeylineIndex != -1) {
            int firstFocalKeylineIndex = keylineState.getFirstFocalKeylineIndex() - iFindFirstNonAnchorKeylineIndex;
            float containerWidth = carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight();
            float f2 = keylineState.getFirstKeyline().locOffset - (keylineState.getFirstKeyline().maskedItemSize / 2.0f);
            float f7 = 0.0f;
            if (firstFocalKeylineIndex <= 0 && keylineState.getFirstFocalKeyline().cutoff > 0.0f) {
                arrayList.add(shiftKeylinesAndCreateKeylineState(keylineState, f2 + keylineState.getFirstFocalKeyline().cutoff, containerWidth));
                return arrayList;
            }
            int i5 = 0;
            while (i5 < firstFocalKeylineIndex) {
                KeylineState keylineState2 = (KeylineState) arrayList.get(arrayList.size() - 1);
                int i7 = iFindFirstNonAnchorKeylineIndex + i5;
                int size = keylineState.getKeylines().size() - 1;
                float f9 = f7 + keylineState.getKeylines().get(i7).cutoff;
                arrayList.add(moveKeylineAndCreateKeylineState(keylineState2, iFindFirstNonAnchorKeylineIndex, i7 - 1 >= 0 ? findFirstIndexAfterLastFocalKeylineWithMask(keylineState2, keylineState.getKeylines().get(r3).mask) - 1 : size, f2 + f9, (keylineState.getFirstFocalKeylineIndex() - i5) - 1, (keylineState.getLastFocalKeylineIndex() - i5) - 1, containerWidth));
                i5++;
                f7 = f9;
            }
        }
        return arrayList;
    }

    private static boolean isFirstFocalItemAtLeftOfContainer(KeylineState keylineState) {
        return keylineState.getFirstFocalKeyline().locOffset - (keylineState.getFirstFocalKeyline().maskedItemSize / 2.0f) >= 0.0f && keylineState.getFirstFocalKeyline() == keylineState.getFirstNonAnchorKeyline();
    }

    private static boolean isLastFocalItemVisibleAtRightOfContainer(Carousel carousel, KeylineState keylineState) {
        int containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        return (keylineState.getLastFocalKeyline().maskedItemSize / 2.0f) + keylineState.getLastFocalKeyline().locOffset <= ((float) containerHeight) && keylineState.getLastFocalKeyline() == keylineState.getLastNonAnchorKeyline();
    }

    private static KeylineState lerp(List<KeylineState> list, float f2, float[] fArr) {
        float[] stateStepsRange = getStateStepsRange(list, f2, fArr);
        return KeylineState.lerp(list.get((int) stateStepsRange[1]), list.get((int) stateStepsRange[2]), stateStepsRange[0]);
    }

    private static KeylineState moveKeylineAndCreateKeylineState(KeylineState keylineState, int i5, int i7, float f2, int i9, int i10, float f7) {
        ArrayList arrayList = new ArrayList(keylineState.getKeylines());
        arrayList.add(i7, (KeylineState.Keyline) arrayList.remove(i5));
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.getItemSize(), f7);
        int i11 = 0;
        while (i11 < arrayList.size()) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) arrayList.get(i11);
            float f9 = keyline.maskedItemSize;
            builder.addKeyline((f9 / 2.0f) + f2, keyline.mask, f9, i11 >= i9 && i11 <= i10, keyline.isAnchor, keyline.cutoff);
            f2 += keyline.maskedItemSize;
            i11++;
        }
        return builder.build();
    }

    private static KeylineState shiftKeylinesAndCreateKeylineState(KeylineState keylineState, float f2, float f7) {
        return moveKeylineAndCreateKeylineState(keylineState, 0, 0, f2, keylineState.getFirstFocalKeylineIndex(), keylineState.getLastFocalKeylineIndex(), f7);
    }

    public KeylineState getDefaultState() {
        return this.defaultState;
    }

    public KeylineState getEndState() {
        return this.endStateSteps.get(r1.size() - 1);
    }

    public Map<Integer, KeylineState> getKeylineStateForPositionMap(int i5, int i7, int i9, boolean z9) {
        float itemSize = this.defaultState.getItemSize();
        HashMap map = new HashMap();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (i10 >= i5) {
                break;
            }
            int i12 = z9 ? (i5 - i10) - 1 : i10;
            if (i12 * itemSize * (z9 ? -1 : 1) > i9 - this.endShiftRange || i10 >= i5 - this.endStateSteps.size()) {
                Integer numValueOf = Integer.valueOf(i12);
                List<KeylineState> list = this.endStateSteps;
                map.put(numValueOf, list.get(c.d(i11, 0, list.size() - 1)));
                i11++;
            }
            i10++;
        }
        int i13 = 0;
        for (int i14 = i5 - 1; i14 >= 0; i14--) {
            int i15 = z9 ? (i5 - i14) - 1 : i14;
            if (i15 * itemSize * (z9 ? -1 : 1) < i7 + this.startShiftRange || i14 < this.startStateSteps.size()) {
                Integer numValueOf2 = Integer.valueOf(i15);
                List<KeylineState> list2 = this.startStateSteps;
                map.put(numValueOf2, list2.get(c.d(i13, 0, list2.size() - 1)));
                i13++;
            }
        }
        return map;
    }

    public KeylineState getShiftedState(float f2, float f7, float f9) {
        return getShiftedState(f2, f7, f9, false);
    }

    public KeylineState getStartState() {
        return this.startStateSteps.get(r1.size() - 1);
    }

    public KeylineState getShiftedState(float f2, float f7, float f9, boolean z9) {
        float fLerp;
        List<KeylineState> list;
        float[] fArr;
        float f10 = this.startShiftRange + f7;
        float f11 = f9 - this.endShiftRange;
        if (f2 < f10) {
            fLerp = AnimationUtils.lerp(1.0f, 0.0f, f7, f10, f2);
            list = this.startStateSteps;
            fArr = this.startStateStepsInterpolationPoints;
        } else {
            if (f2 <= f11) {
                return this.defaultState;
            }
            fLerp = AnimationUtils.lerp(0.0f, 1.0f, f11, f9, f2);
            list = this.endStateSteps;
            fArr = this.endStateStepsInterpolationPoints;
        }
        return z9 ? closestStateStepFromInterpolation(list, fLerp, fArr) : lerp(list, fLerp, fArr);
    }
}
