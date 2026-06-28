package com.bumptech.glide.load;

/* JADX INFO: loaded from: classes.dex */
public enum ImageHeaderParser$ImageType {
    GIF(true),
    JPEG(false),
    RAW(false),
    PNG_A(true),
    PNG(false),
    WEBP_A(true),
    WEBP(false),
    UNKNOWN(false);


    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f9981e;

    ImageHeaderParser$ImageType(boolean z9) {
        this.f9981e = z9;
    }

    public boolean hasAlpha() {
        return this.f9981e;
    }
}
