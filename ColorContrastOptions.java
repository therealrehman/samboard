package com.google.android.material.color;

/* JADX INFO: loaded from: classes.dex */
public class ColorContrastOptions {
    private final int highContrastThemeOverlayResourceId;
    private final int mediumContrastThemeOverlayResourceId;

    public static class Builder {
        private int highContrastThemeOverlayResourceId;
        private int mediumContrastThemeOverlayResourceId;

        public ColorContrastOptions build() {
            return new ColorContrastOptions(this);
        }

        public Builder setHighContrastThemeOverlay(int i5) {
            this.highContrastThemeOverlayResourceId = i5;
            return this;
        }

        public Builder setMediumContrastThemeOverlay(int i5) {
            this.mediumContrastThemeOverlayResourceId = i5;
            return this;
        }
    }

    public int getHighContrastThemeOverlay() {
        return this.highContrastThemeOverlayResourceId;
    }

    public int getMediumContrastThemeOverlay() {
        return this.mediumContrastThemeOverlayResourceId;
    }

    private ColorContrastOptions(Builder builder) {
        this.mediumContrastThemeOverlayResourceId = builder.mediumContrastThemeOverlayResourceId;
        this.highContrastThemeOverlayResourceId = builder.highContrastThemeOverlayResourceId;
    }
}
