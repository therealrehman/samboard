package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public final class Hct {
    private int argb;
    private double chroma;
    private double hue;
    private double tone;

    private Hct(int i5) {
        setInternalState(i5);
    }

    public static Hct from(double d8, double d10, double d11) {
        return new Hct(HctSolver.solveToInt(d8, d10, d11));
    }

    public static Hct fromInt(int i5) {
        return new Hct(i5);
    }

    private void setInternalState(int i5) {
        this.argb = i5;
        Cam16 cam16FromInt = Cam16.fromInt(i5);
        this.hue = cam16FromInt.getHue();
        this.chroma = cam16FromInt.getChroma();
        this.tone = ColorUtils.lstarFromArgb(i5);
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public double getTone() {
        return this.tone;
    }

    public Hct inViewingConditions(ViewingConditions viewingConditions) {
        double[] dArrXyzInViewingConditions = Cam16.fromInt(toInt()).xyzInViewingConditions(viewingConditions, null);
        Cam16 cam16FromXyzInViewingConditions = Cam16.fromXyzInViewingConditions(dArrXyzInViewingConditions[0], dArrXyzInViewingConditions[1], dArrXyzInViewingConditions[2], ViewingConditions.DEFAULT);
        return from(cam16FromXyzInViewingConditions.getHue(), cam16FromXyzInViewingConditions.getChroma(), ColorUtils.lstarFromY(dArrXyzInViewingConditions[1]));
    }

    public void setChroma(double d8) {
        setInternalState(HctSolver.solveToInt(this.hue, d8, this.tone));
    }

    public void setHue(double d8) {
        setInternalState(HctSolver.solveToInt(d8, this.chroma, this.tone));
    }

    public void setTone(double d8) {
        setInternalState(HctSolver.solveToInt(this.hue, this.chroma, d8));
    }

    public int toInt() {
        return this.argb;
    }
}
