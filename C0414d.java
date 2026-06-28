package b2;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: b2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0414d implements com.bumptech.glide.load.data.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9710e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f9711f;

    public /* synthetic */ C0414d(int i5, Object obj) {
        this.f9710e = i5;
        this.f9711f = obj;
    }

    private final void c() {
    }

    private final void d() {
    }

    private final void g() {
    }

    private final void h() {
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        switch (this.f9710e) {
            case 0:
                return ByteBuffer.class;
            default:
                return this.f9711f.getClass();
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        int i5 = this.f9710e;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        int i5 = this.f9710e;
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        switch (this.f9710e) {
        }
        return 1;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(com.bumptech.glide.f fVar, com.bumptech.glide.load.data.d dVar) {
        switch (this.f9710e) {
            case 0:
                try {
                    dVar.d(r2.b.a((File) this.f9711f));
                } catch (IOException e3) {
                    if (Log.isLoggable("ByteBufferFileLoader", 3)) {
                        Log.d("ByteBufferFileLoader", "Failed to obtain ByteBuffer for file", e3);
                    }
                    dVar.c(e3);
                    return;
                }
                break;
            default:
                dVar.d(this.f9711f);
                break;
        }
    }
}
