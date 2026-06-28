package com.google.android.material.color.utilities;

import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10076a;

    public /* synthetic */ e(int i5) {
        this.f10076a = i5;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (this.f10076a) {
            case 0:
                return dynamicScheme.neutralPalette;
            case 1:
                return MaterialDynamicColors.lambda$surfaceContainerLow$24(dynamicScheme);
            case 2:
                return dynamicScheme.secondaryPalette;
            case 3:
                return MaterialDynamicColors.lambda$secondaryContainer$74(dynamicScheme);
            case 4:
                return dynamicScheme.neutralPalette;
            case 5:
                return dynamicScheme.tertiaryPalette;
            case 6:
                return MaterialDynamicColors.lambda$onTertiaryFixed$138(dynamicScheme);
            case 7:
                return dynamicScheme.tertiaryPalette;
            case 8:
                return MaterialDynamicColors.lambda$tertiaryPaletteKeyColor$5(dynamicScheme);
            case 9:
                return dynamicScheme.neutralPalette;
            case 10:
                return MaterialDynamicColors.lambda$controlHighlight$150(dynamicScheme);
            case 11:
                return MaterialDynamicColors.lambda$controlHighlight$151(dynamicScheme);
            case 12:
                return MaterialDynamicColors.lambda$textSecondaryAndTertiaryInverseDisabled$159(dynamicScheme);
            case 13:
                return dynamicScheme.primaryPalette;
            case 14:
                return MaterialDynamicColors.lambda$primaryContainer$59(dynamicScheme);
            case 15:
                return dynamicScheme.neutralPalette;
            case 16:
                return MaterialDynamicColors.lambda$background$11(dynamicScheme);
            case 17:
                return dynamicScheme.neutralPalette;
            case 18:
                return MaterialDynamicColors.lambda$surfaceContainerHigh$28(dynamicScheme);
            case 19:
                return dynamicScheme.neutralPalette;
            case 20:
                return MaterialDynamicColors.lambda$onBackground$13(dynamicScheme);
            case 21:
                return dynamicScheme.tertiaryPalette;
            case 22:
                return dynamicScheme.neutralVariantPalette;
            case 23:
                return MaterialDynamicColors.lambda$outlineVariant$45(dynamicScheme);
            case 24:
                return dynamicScheme.neutralPalette;
            case 25:
                return MaterialDynamicColors.lambda$surfaceBright$20(dynamicScheme);
            case 26:
                return dynamicScheme.primaryPalette;
            case 27:
                return MaterialDynamicColors.lambda$onPrimary$56(dynamicScheme);
            case 28:
                return dynamicScheme.primaryPalette;
            default:
                return MaterialDynamicColors.lambda$inversePrimary$65(dynamicScheme);
        }
    }
}
