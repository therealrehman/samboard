package b2;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class z implements q, V1.b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final z f9758f = new z(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9759e;

    public /* synthetic */ z(int i5) {
        this.f9759e = i5;
    }

    public static ByteArrayInputStream c(String str) {
        if (!str.startsWith("data:image")) {
            throw new IllegalArgumentException("Not a valid image data URL.");
        }
        int iIndexOf = str.indexOf(44);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Missing comma in data URL.");
        }
        if (str.substring(0, iIndexOf).endsWith(";base64")) {
            return new ByteArrayInputStream(Base64.decode(str.substring(iIndexOf + 1), 0));
        }
        throw new IllegalArgumentException("Not a base64 image data URL.");
    }

    @Override // b2.q
    public p B(v vVar) {
        switch (this.f9759e) {
            case 0:
                return C0407A.f9699b;
            case 2:
                return new C0413c(0, new z(1));
            case 4:
                return new C0413c(0, new z(3));
            case 6:
                return new C0407A(1);
            case 11:
                return new y(vVar.b(Uri.class, AssetFileDescriptor.class), 0);
            case 12:
                return new y(vVar.b(Uri.class, ParcelFileDescriptor.class), 0);
            case 13:
                return new y(vVar.b(Uri.class, InputStream.class), 0);
            default:
                return new C0410D(vVar.b(C0416f.class, InputStream.class));
        }
    }

    public void a(Object obj) throws IOException {
        switch (this.f9759e) {
            case 8:
                ((ParcelFileDescriptor) obj).close();
                break;
            default:
                ((InputStream) obj).close();
                break;
        }
    }

    public Object b(byte[] bArr) {
        switch (this.f9759e) {
            case 1:
                return ByteBuffer.wrap(bArr);
            default:
                return new ByteArrayInputStream(bArr);
        }
    }

    public Class d() {
        switch (this.f9759e) {
            case 1:
                return ByteBuffer.class;
            case 3:
                return InputStream.class;
            case 8:
                return ParcelFileDescriptor.class;
            default:
                return InputStream.class;
        }
    }

    public Object e(File file) {
        switch (this.f9759e) {
            case 8:
                return ParcelFileDescriptor.open(file, 268435456);
            default:
                return new FileInputStream(file);
        }
    }

    @Override // V1.b
    public boolean r(Object obj, File file, V1.h hVar) throws Throwable {
        try {
            r2.b.b((ByteBuffer) obj, file);
            return true;
        } catch (IOException e3) {
            if (Log.isLoggable("ByteBufferEncoder", 3)) {
                Log.d("ByteBufferEncoder", "Failed to write data", e3);
            }
            return false;
        }
    }
}
