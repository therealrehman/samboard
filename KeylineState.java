package com.google.android.material.carousel;

import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class KeylineState {
    private final int firstFocalKeylineIndex;
    private final float itemSize;
    private final List<Keyline> keylines;
    private final int lastFocalKeylineIndex;

    public static final class Builder {
        private static final int NO_INDEX = -1;
        private static final float UNKNOWN_LOC = Float.MIN_VALUE;
        private final float availableSpace;
        private final float itemSize;
        private Keyline tmpFirstFocalKeyline;
        private Keyline tmpLastFocalKeyline;
        private final List<Keyline> tmpKeylines = new ArrayList();
        private int firstFocalKeylineIndex = -1;
        private int lastFocalKeylineIndex = -1;
        private float lastKeylineMaskedSize = 0.0f;
        private int latestAnchorKeylineIndex = -1;

        public Builder(float f2, float f7) {
            this.itemSize = f2;
            this.availableSpace = f7;
        }

        private static float calculateKeylineLocationForItemPosition(float f2, float f7, int i5, int i7) {
            return (i7 * f7) + (f2 - (i5 * f7));
        }

        public Builder addAnchorKeyline(float f2, float f7, float f9) {
            return addKeyline(f2, f7, f9, false, true);
        }

        public Builder addKeyline(float f2, float f7, float f9, boolean z9) {
            return addKeyline(f2, f7, f9, z9, false);
        }

        public Builder addKeylineRange(float f2, float f7, float f9, int i5) {
            return addKeylineRange(f2, f7, f9, i5, false);
        }

        public KeylineState build() {
            if (this.tmpFirstFocalKeyline == null) {
                throw new IllegalStateException("There must be a keyline marked as focal.");
            }
            ArrayList arrayList = new ArrayList();
            for (int i5 = 0; i5 < this.tmpKeylines.size(); i5++) {
                Keyline keyline = this.tmpKeylines.get(i5);
                arrayList.add(new Keyline(calculateKeylineLocationForItemPosition(this.tmpFirstFocalKeyline.locOffset, this.itemSize, this.firstFocalKeylineIndex, i5), keyline.locOffset, keyline.mask, keyline.maskedItemSize, keyline.isAnchor, keyline.cutoff));
            }
            return new KeylineState(this.itemSize, arrayList, this.firstFocalKeylineIndex, this.lastFocalKeylineIndex);
        }

        public Builder addKeyline(float f2, float f7, float f9) {
            return addKeyline(f2, f7, f9, false);
        }

        public Builder addKeylineRange(float f2, float f7, float f9, int i5, boolean z9) {
            if (i5 > 0 && f9 > 0.0f) {
                for (int i7 = 0; i7 < i5; i7++) {
                    addKeyline((i7 * f9) + f2, f7, f9, z9);
                }
            }
            return this;
        }

        public Builder addKeyline(float f2, float f7, float f9, boolean z9, boolean z10, float f10) {
            if (f9 <= 0.0f) {
                return this;
            }
            if (z10) {
                if (!z9) {
                    int i5 = this.latestAnchorKeylineIndex;
                    if (i5 != -1 && i5 != 0) {
                        throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
                    }
                    this.latestAnchorKeylineIndex = this.tmpKeylines.size();
                } else {
                    throw new IllegalArgumentException("Anchor keylines cannot be focal.");
                }
            }
            Keyline keyline = new Keyline(UNKNOWN_LOC, f2, f7, f9, z10, f10);
            if (z9) {
                if (this.tmpFirstFocalKeyline == null) {
                    this.tmpFirstFocalKeyline = keyline;
                    this.firstFocalKeylineIndex = this.tmpKeylines.size();
                }
                if (this.lastFocalKeylineIndex != -1 && this.tmpKeylines.size() - this.lastFocalKeylineIndex > 1) {
                    throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
                }
                if (f9 == this.tmpFirstFocalKeyline.maskedItemSize) {
                    this.tmpLastFocalKeyline = keyline;
                    this.lastFocalKeylineIndex = this.tmpKeylines.size();
                } else {
                    throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
                }
            } else {
                if (this.tmpFirstFocalKeyline == null && keyline.maskedItemSize < this.lastKeylineMaskedSize) {
                    throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
                }
                if (this.tmpLastFocalKeyline != null && keyline.maskedItemSize > this.lastKeylineMaskedSize) {
                    throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
                }
            }
            this.lastKeylineMaskedSize = keyline.maskedItemSize;
            this.tmpKeylines.add(keyline);
            return this;
        }

        public Builder addKeyline(float f2, float f7, float f9, boolean z9, boolean z10) {
            float fAbs;
            float f10 = f9 / 2.0f;
            float f11 = f2 - f10;
            float f12 = f10 + f2;
            float f13 = this.availableSpace;
            if (f12 > f13) {
                fAbs = Math.abs(f12 - Math.max(f12 - f9, f13));
            } else {
                fAbs = 0.0f;
                if (f11 < 0.0f) {
                    fAbs = Math.abs(f11 - Math.min(f11 + f9, 0.0f));
                }
            }
            return addKeyline(f2, f7, f9, z9, z10, fAbs);
        }
    }

    public static final class Keyline {
        final float cutoff;
        final boolean isAnchor;
        final float loc;
        final float locOffset;
        final float mask;
        final float maskedItemSize;

        public Keyline(float f2, float f7, float f9, float f10) {
            this(f2, f7, f9, f10, false, 0.0f);
        }

        public static Keyline lerp(Keyline keyline, Keyline keyline2, float f2) {
            return new Keyline(AnimationUtils.lerp(keyline.loc, keyline2.loc, f2), AnimationUtils.lerp(keyline.locOffset, keyline2.locOffset, f2), AnimationUtils.lerp(keyline.mask, keyline2.mask, f2), AnimationUtils.lerp(keyline.maskedItemSize, keyline2.maskedItemSize, f2));
        }

        public Keyline(float f2, float f7, float f9, float f10, boolean z9, float f11) {
            this.loc = f2;
            this.locOffset = f7;
            this.mask = f9;
            this.maskedItemSize = f10;
            this.isAnchor = z9;
            this.cutoff = f11;
        }
    }

    public static KeylineState lerp(KeylineState keylineState, KeylineState keylineState2, float f2) {
        if (keylineState.getItemSize() != keylineState2.getItemSize()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
        }
        List<Keyline> keylines = keylineState.getKeylines();
        List<Keyline> keylines2 = keylineState2.getKeylines();
        if (keylines.size() != keylines2.size()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 < keylineState.getKeylines().size(); i5++) {
            arrayList.add(Keyline.lerp(keylines.get(i5), keylines2.get(i5), f2));
        }
        return new KeylineState(keylineState.getItemSize(), arrayList, AnimationUtils.lerp(keylineState.getFirstFocalKeylineIndex(), keylineState2.getFirstFocalKeylineIndex(), f2), AnimationUtils.lerp(keylineState.getLastFocalKeylineIndex(), keylineState2.getLastFocalKeylineIndex(), f2));
    }

    public static KeylineState reverse(KeylineState keylineState, float f2) {
        Builder builder = new Builder(keylineState.getItemSize(), f2);
        float f7 = (f2 - keylineState.getLastKeyline().locOffset) - (keylineState.getLastKeyline().maskedItemSize / 2.0f);
        int size = keylineState.getKeylines().size() - 1;
        while (size >= 0) {
            Keyline keyline = keylineState.getKeylines().get(size);
            builder.addKeyline((keyline.maskedItemSize / 2.0f) + f7, keyline.mask, keyline.maskedItemSize, size >= keylineState.getFirstFocalKeylineIndex() && size <= keylineState.getLastFocalKeylineIndex(), keyline.isAnchor);
            f7 += keyline.maskedItemSize;
            size--;
        }
        return builder.build();
    }

    public Keyline getFirstFocalKeyline() {
        return this.keylines.get(this.firstFocalKeylineIndex);
    }

    public int getFirstFocalKeylineIndex() {
        return this.firstFocalKeylineIndex;
    }

    public Keyline getFirstKeyline() {
        return this.keylines.get(0);
    }

    public Keyline getFirstNonAnchorKeyline() {
        for (int i5 = 0; i5 < this.keylines.size(); i5++) {
            Keyline keyline = this.keylines.get(i5);
            if (!keyline.isAnchor) {
                return keyline;
            }
        }
        return null;
    }

    public List<Keyline> getFocalKeylines() {
        return this.keylines.subList(this.firstFocalKeylineIndex, this.lastFocalKeylineIndex + 1);
    }

    public float getItemSize() {
        return this.itemSize;
    }

    public List<Keyline> getKeylines() {
        return this.keylines;
    }

    public Keyline getLastFocalKeyline() {
        return this.keylines.get(this.lastFocalKeylineIndex);
    }

    public int getLastFocalKeylineIndex() {
        return this.lastFocalKeylineIndex;
    }

    public Keyline getLastKeyline() {
        return this.keylines.get(r1.size() - 1);
    }

    public Keyline getLastNonAnchorKeyline() {
        for (int size = this.keylines.size() - 1; size >= 0; size--) {
            Keyline keyline = this.keylines.get(size);
            if (!keyline.isAnchor) {
                return keyline;
            }
        }
        return null;
    }

    private KeylineState(float f2, List<Keyline> list, int i5, int i7) {
        this.itemSize = f2;
        this.keylines = Collections.unmodifiableList(list);
        this.firstFocalKeylineIndex = i5;
        this.lastFocalKeylineIndex = i7;
    }
}
