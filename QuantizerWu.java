package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class QuantizerWu implements Quantizer {
    private static final int INDEX_BITS = 5;
    private static final int INDEX_COUNT = 33;
    private static final int TOTAL_SIZE = 35937;
    Box[] cubes;
    double[] moments;
    int[] momentsB;
    int[] momentsG;
    int[] momentsR;
    int[] weights;

    /* JADX INFO: renamed from: com.google.android.material.color.utilities.QuantizerWu$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction;

        static {
            int[] iArr = new int[Direction.values().length];
            $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction = iArr;
            try {
                iArr[Direction.RED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[Direction.GREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[Direction.BLUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static final class CreateBoxesResult {
        int resultCount;

        public CreateBoxesResult(int i5, int i7) {
            this.resultCount = i7;
        }
    }

    public enum Direction {
        RED,
        GREEN,
        BLUE
    }

    public static final class MaximizeResult {
        int cutLocation;
        double maximum;

        public MaximizeResult(int i5, double d8) {
            this.cutLocation = i5;
            this.maximum = d8;
        }
    }

    public static int bottom(Box box, Direction direction, int[] iArr) {
        int i5;
        int i7;
        int i9 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i9 == 1) {
            i5 = (-iArr[getIndex(box.f10065r0, box.f10064g1, box.f10062b1)]) + iArr[getIndex(box.f10065r0, box.f10064g1, box.f10061b0)] + iArr[getIndex(box.f10065r0, box.f10063g0, box.f10062b1)];
            i7 = iArr[getIndex(box.f10065r0, box.f10063g0, box.f10061b0)];
        } else if (i9 == 2) {
            i5 = (-iArr[getIndex(box.f10066r1, box.f10063g0, box.f10062b1)]) + iArr[getIndex(box.f10066r1, box.f10063g0, box.f10061b0)] + iArr[getIndex(box.f10065r0, box.f10063g0, box.f10062b1)];
            i7 = iArr[getIndex(box.f10065r0, box.f10063g0, box.f10061b0)];
        } else {
            if (i9 != 3) {
                throw new IllegalArgumentException("unexpected direction " + direction);
            }
            i5 = (-iArr[getIndex(box.f10066r1, box.f10064g1, box.f10061b0)]) + iArr[getIndex(box.f10066r1, box.f10063g0, box.f10061b0)] + iArr[getIndex(box.f10065r0, box.f10064g1, box.f10061b0)];
            i7 = iArr[getIndex(box.f10065r0, box.f10063g0, box.f10061b0)];
        }
        return i5 - i7;
    }

    public static int getIndex(int i5, int i7, int i9) {
        return (i5 << 10) + (i5 << 6) + i5 + (i7 << 5) + i7 + i9;
    }

    public static int top(Box box, Direction direction, int i5, int[] iArr) {
        int i7;
        int i9;
        int i10 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i10 == 1) {
            i7 = (iArr[getIndex(i5, box.f10064g1, box.f10062b1)] - iArr[getIndex(i5, box.f10064g1, box.f10061b0)]) - iArr[getIndex(i5, box.f10063g0, box.f10062b1)];
            i9 = iArr[getIndex(i5, box.f10063g0, box.f10061b0)];
        } else if (i10 == 2) {
            i7 = (iArr[getIndex(box.f10066r1, i5, box.f10062b1)] - iArr[getIndex(box.f10066r1, i5, box.f10061b0)]) - iArr[getIndex(box.f10065r0, i5, box.f10062b1)];
            i9 = iArr[getIndex(box.f10065r0, i5, box.f10061b0)];
        } else {
            if (i10 != 3) {
                throw new IllegalArgumentException("unexpected direction " + direction);
            }
            i7 = (iArr[getIndex(box.f10066r1, box.f10064g1, i5)] - iArr[getIndex(box.f10066r1, box.f10063g0, i5)]) - iArr[getIndex(box.f10065r0, box.f10064g1, i5)];
            i9 = iArr[getIndex(box.f10065r0, box.f10063g0, i5)];
        }
        return i7 + i9;
    }

    public static int volume(Box box, int[] iArr) {
        return ((((((iArr[getIndex(box.f10066r1, box.f10064g1, box.f10062b1)] - iArr[getIndex(box.f10066r1, box.f10064g1, box.f10061b0)]) - iArr[getIndex(box.f10066r1, box.f10063g0, box.f10062b1)]) + iArr[getIndex(box.f10066r1, box.f10063g0, box.f10061b0)]) - iArr[getIndex(box.f10065r0, box.f10064g1, box.f10062b1)]) + iArr[getIndex(box.f10065r0, box.f10064g1, box.f10061b0)]) + iArr[getIndex(box.f10065r0, box.f10063g0, box.f10062b1)]) - iArr[getIndex(box.f10065r0, box.f10063g0, box.f10061b0)];
    }

    public void constructHistogram(Map<Integer, Integer> map) {
        this.weights = new int[TOTAL_SIZE];
        this.momentsR = new int[TOTAL_SIZE];
        this.momentsG = new int[TOTAL_SIZE];
        this.momentsB = new int[TOTAL_SIZE];
        this.moments = new double[TOTAL_SIZE];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            int iIntValue2 = entry.getValue().intValue();
            int iRedFromArgb = ColorUtils.redFromArgb(iIntValue);
            int iGreenFromArgb = ColorUtils.greenFromArgb(iIntValue);
            int iBlueFromArgb = ColorUtils.blueFromArgb(iIntValue);
            int index = getIndex((iRedFromArgb >> 3) + 1, (iGreenFromArgb >> 3) + 1, (iBlueFromArgb >> 3) + 1);
            int[] iArr = this.weights;
            iArr[index] = iArr[index] + iIntValue2;
            int[] iArr2 = this.momentsR;
            iArr2[index] = (iRedFromArgb * iIntValue2) + iArr2[index];
            int[] iArr3 = this.momentsG;
            iArr3[index] = (iGreenFromArgb * iIntValue2) + iArr3[index];
            int[] iArr4 = this.momentsB;
            iArr4[index] = (iBlueFromArgb * iIntValue2) + iArr4[index];
            double[] dArr = this.moments;
            int i5 = iBlueFromArgb * iBlueFromArgb;
            dArr[index] = dArr[index] + ((double) ((i5 + (iGreenFromArgb * iGreenFromArgb) + (iRedFromArgb * iRedFromArgb)) * iIntValue2));
        }
    }

    public CreateBoxesResult createBoxes(int i5) {
        int i7;
        this.cubes = new Box[i5];
        for (int i9 = 0; i9 < i5; i9++) {
            this.cubes[i9] = new Box(null);
        }
        double[] dArr = new double[i5];
        Box box = this.cubes[0];
        box.f10066r1 = 32;
        box.f10064g1 = 32;
        box.f10062b1 = 32;
        int i10 = 0;
        int i11 = 1;
        while (true) {
            if (i11 >= i5) {
                i7 = i5;
                break;
            }
            Box[] boxArr = this.cubes;
            if (cut(boxArr[i10], boxArr[i11]).booleanValue()) {
                Box box2 = this.cubes[i10];
                dArr[i10] = box2.vol > 1 ? variance(box2) : 0.0d;
                Box box3 = this.cubes[i11];
                dArr[i11] = box3.vol > 1 ? variance(box3) : 0.0d;
            } else {
                dArr[i10] = 0.0d;
                i11--;
            }
            double d8 = dArr[0];
            int i12 = 0;
            for (int i13 = 1; i13 <= i11; i13++) {
                double d10 = dArr[i13];
                if (d10 > d8) {
                    i12 = i13;
                    d8 = d10;
                }
            }
            if (d8 <= 0.0d) {
                i7 = i11 + 1;
                break;
            }
            i11++;
            i10 = i12;
        }
        return new CreateBoxesResult(i5, i7);
    }

    public void createMoments() {
        int i5 = 1;
        while (true) {
            int i7 = 33;
            if (i5 >= 33) {
                return;
            }
            int[] iArr = new int[33];
            int[] iArr2 = new int[33];
            int[] iArr3 = new int[33];
            int[] iArr4 = new int[33];
            double[] dArr = new double[33];
            int i9 = 1;
            while (i9 < i7) {
                int i10 = 0;
                int i11 = 0;
                double d8 = 0.0d;
                int i12 = 1;
                int i13 = 0;
                int i14 = 0;
                while (i12 < i7) {
                    int index = getIndex(i5, i9, i12);
                    int i15 = i10 + this.weights[index];
                    i13 += this.momentsR[index];
                    i14 += this.momentsG[index];
                    i11 += this.momentsB[index];
                    d8 += this.moments[index];
                    iArr[i12] = iArr[i12] + i15;
                    iArr2[i12] = iArr2[i12] + i13;
                    iArr3[i12] = iArr3[i12] + i14;
                    iArr4[i12] = iArr4[i12] + i11;
                    dArr[i12] = dArr[i12] + d8;
                    int index2 = getIndex(i5 - 1, i9, i12);
                    int[] iArr5 = this.weights;
                    iArr5[index] = iArr5[index2] + iArr[i12];
                    int[] iArr6 = this.momentsR;
                    iArr6[index] = iArr6[index2] + iArr2[i12];
                    int[] iArr7 = this.momentsG;
                    iArr7[index] = iArr7[index2] + iArr3[i12];
                    int[] iArr8 = this.momentsB;
                    iArr8[index] = iArr8[index2] + iArr4[i12];
                    double[] dArr2 = this.moments;
                    dArr2[index] = dArr2[index2] + dArr[i12];
                    i12++;
                    i10 = i15;
                    i7 = 33;
                }
                i9++;
                i7 = 33;
            }
            i5++;
        }
    }

    public List<Integer> createResult(int i5) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < i5; i7++) {
            Box box = this.cubes[i7];
            int iVolume = volume(box, this.weights);
            if (iVolume > 0) {
                int iVolume2 = volume(box, this.momentsR) / iVolume;
                int iVolume3 = volume(box, this.momentsG) / iVolume;
                arrayList.add(Integer.valueOf(((volume(box, this.momentsB) / iVolume) & 255) | ((iVolume2 & 255) << 16) | (-16777216) | ((iVolume3 & 255) << 8)));
            }
        }
        return arrayList;
    }

    public Boolean cut(Box box, Box box2) {
        int iVolume = volume(box, this.momentsR);
        int iVolume2 = volume(box, this.momentsG);
        int iVolume3 = volume(box, this.momentsB);
        int iVolume4 = volume(box, this.weights);
        Direction direction = Direction.RED;
        MaximizeResult maximizeResultMaximize = maximize(box, direction, box.f10065r0 + 1, box.f10066r1, iVolume, iVolume2, iVolume3, iVolume4);
        Direction direction2 = Direction.GREEN;
        MaximizeResult maximizeResultMaximize2 = maximize(box, direction2, box.f10063g0 + 1, box.f10064g1, iVolume, iVolume2, iVolume3, iVolume4);
        Direction direction3 = Direction.BLUE;
        MaximizeResult maximizeResultMaximize3 = maximize(box, direction3, box.f10061b0 + 1, box.f10062b1, iVolume, iVolume2, iVolume3, iVolume4);
        double d8 = maximizeResultMaximize.maximum;
        double d10 = maximizeResultMaximize2.maximum;
        double d11 = maximizeResultMaximize3.maximum;
        if (d8 < d10 || d8 < d11) {
            direction = (d10 < d8 || d10 < d11) ? direction3 : direction2;
        } else if (maximizeResultMaximize.cutLocation < 0) {
            return Boolean.FALSE;
        }
        box2.f10066r1 = box.f10066r1;
        box2.f10064g1 = box.f10064g1;
        box2.f10062b1 = box.f10062b1;
        int i5 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i5 == 1) {
            int i7 = maximizeResultMaximize.cutLocation;
            box.f10066r1 = i7;
            box2.f10065r0 = i7;
            box2.f10063g0 = box.f10063g0;
            box2.f10061b0 = box.f10061b0;
        } else if (i5 == 2) {
            int i9 = maximizeResultMaximize2.cutLocation;
            box.f10064g1 = i9;
            box2.f10065r0 = box.f10065r0;
            box2.f10063g0 = i9;
            box2.f10061b0 = box.f10061b0;
        } else if (i5 == 3) {
            int i10 = maximizeResultMaximize3.cutLocation;
            box.f10062b1 = i10;
            box2.f10065r0 = box.f10065r0;
            box2.f10063g0 = box.f10063g0;
            box2.f10061b0 = i10;
        }
        box.vol = (box.f10062b1 - box.f10061b0) * (box.f10064g1 - box.f10063g0) * (box.f10066r1 - box.f10065r0);
        box2.vol = (box2.f10062b1 - box2.f10061b0) * (box2.f10064g1 - box2.f10063g0) * (box2.f10066r1 - box2.f10065r0);
        return Boolean.TRUE;
    }

    public MaximizeResult maximize(Box box, Direction direction, int i5, int i7, int i9, int i10, int i11, int i12) {
        int i13;
        QuantizerWu quantizerWu = this;
        Box box2 = box;
        Direction direction2 = direction;
        int iBottom = bottom(box2, direction2, quantizerWu.momentsR);
        int iBottom2 = bottom(box2, direction2, quantizerWu.momentsG);
        int iBottom3 = bottom(box2, direction2, quantizerWu.momentsB);
        int iBottom4 = bottom(box2, direction2, quantizerWu.weights);
        int i14 = -1;
        double d8 = 0.0d;
        int i15 = i5;
        while (i15 < i7) {
            int pVar = top(box2, direction2, i15, quantizerWu.momentsR) + iBottom;
            int pVar2 = top(box2, direction2, i15, quantizerWu.momentsG) + iBottom2;
            int pVar3 = top(box2, direction2, i15, quantizerWu.momentsB) + iBottom3;
            int pVar4 = top(box2, direction2, i15, quantizerWu.weights) + iBottom4;
            if (pVar4 == 0) {
                i13 = iBottom;
            } else {
                i13 = iBottom;
                double d10 = ((double) ((pVar3 * pVar3) + ((pVar2 * pVar2) + (pVar * pVar)))) / ((double) pVar4);
                int i16 = i9 - pVar;
                int i17 = i10 - pVar2;
                int i18 = i11 - pVar3;
                int i19 = i12 - pVar4;
                if (i19 != 0) {
                    double d11 = (((double) ((i18 * i18) + ((i17 * i17) + (i16 * i16)))) / ((double) i19)) + d10;
                    if (d11 > d8) {
                        d8 = d11;
                        i14 = i15;
                    }
                }
            }
            i15++;
            quantizerWu = this;
            box2 = box;
            direction2 = direction;
            iBottom = i13;
        }
        return new MaximizeResult(i14, d8);
    }

    @Override // com.google.android.material.color.utilities.Quantizer
    public QuantizerResult quantize(int[] iArr, int i5) {
        constructHistogram(new QuantizerMap().quantize(iArr, i5).colorToCount);
        createMoments();
        List<Integer> listCreateResult = createResult(createBoxes(i5).resultCount);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Integer num : listCreateResult) {
            num.getClass();
            linkedHashMap.put(num, 0);
        }
        return new QuantizerResult(linkedHashMap);
    }

    public double variance(Box box) {
        int iVolume = volume(box, this.momentsR);
        int iVolume2 = volume(box, this.momentsG);
        int iVolume3 = volume(box, this.momentsB);
        return (((((((this.moments[getIndex(box.f10066r1, box.f10064g1, box.f10062b1)] - this.moments[getIndex(box.f10066r1, box.f10064g1, box.f10061b0)]) - this.moments[getIndex(box.f10066r1, box.f10063g0, box.f10062b1)]) + this.moments[getIndex(box.f10066r1, box.f10063g0, box.f10061b0)]) - this.moments[getIndex(box.f10065r0, box.f10064g1, box.f10062b1)]) + this.moments[getIndex(box.f10065r0, box.f10064g1, box.f10061b0)]) + this.moments[getIndex(box.f10065r0, box.f10063g0, box.f10062b1)]) - this.moments[getIndex(box.f10065r0, box.f10063g0, box.f10061b0)]) - (((double) ((iVolume3 * iVolume3) + ((iVolume2 * iVolume2) + (iVolume * iVolume)))) / ((double) volume(box, this.weights)));
    }

    public static final class Box {

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        int f10061b0;

        /* JADX INFO: renamed from: b1, reason: collision with root package name */
        int f10062b1;

        /* JADX INFO: renamed from: g0, reason: collision with root package name */
        int f10063g0;

        /* JADX INFO: renamed from: g1, reason: collision with root package name */
        int f10064g1;

        /* JADX INFO: renamed from: r0, reason: collision with root package name */
        int f10065r0;

        /* JADX INFO: renamed from: r1, reason: collision with root package name */
        int f10066r1;
        int vol;

        private Box() {
            this.f10065r0 = 0;
            this.f10066r1 = 0;
            this.f10063g0 = 0;
            this.f10064g1 = 0;
            this.f10061b0 = 0;
            this.f10062b1 = 0;
            this.vol = 0;
        }

        public /* synthetic */ Box(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
