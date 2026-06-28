package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public final class CorePalette {
    public TonalPalette a1;

    /* JADX INFO: renamed from: a2, reason: collision with root package name */
    public TonalPalette f10057a2;

    /* JADX INFO: renamed from: a3, reason: collision with root package name */
    public TonalPalette f10058a3;
    public TonalPalette error;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public TonalPalette f10059n1;

    /* JADX INFO: renamed from: n2, reason: collision with root package name */
    public TonalPalette f10060n2;

    private CorePalette(int i5, boolean z9) {
        Hct hctFromInt = Hct.fromInt(i5);
        double hue = hctFromInt.getHue();
        double chroma = hctFromInt.getChroma();
        if (z9) {
            this.a1 = TonalPalette.fromHueAndChroma(hue, chroma);
            this.f10057a2 = TonalPalette.fromHueAndChroma(hue, chroma / 3.0d);
            this.f10058a3 = TonalPalette.fromHueAndChroma(60.0d + hue, chroma / 2.0d);
            this.f10059n1 = TonalPalette.fromHueAndChroma(hue, Math.min(chroma / 12.0d, 4.0d));
            this.f10060n2 = TonalPalette.fromHueAndChroma(hue, Math.min(chroma / 6.0d, 8.0d));
        } else {
            this.a1 = TonalPalette.fromHueAndChroma(hue, Math.max(48.0d, chroma));
            this.f10057a2 = TonalPalette.fromHueAndChroma(hue, 16.0d);
            this.f10058a3 = TonalPalette.fromHueAndChroma(60.0d + hue, 24.0d);
            this.f10059n1 = TonalPalette.fromHueAndChroma(hue, 4.0d);
            this.f10060n2 = TonalPalette.fromHueAndChroma(hue, 8.0d);
        }
        this.error = TonalPalette.fromHueAndChroma(25.0d, 84.0d);
    }

    public static CorePalette contentOf(int i5) {
        return new CorePalette(i5, true);
    }

    public static CorePalette of(int i5) {
        return new CorePalette(i5, false);
    }
}
