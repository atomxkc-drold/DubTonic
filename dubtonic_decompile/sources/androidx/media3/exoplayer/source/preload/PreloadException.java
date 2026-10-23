package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.MediaItem;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class PreloadException extends Exception {
    public final MediaItem mediaItem;

    public PreloadException(MediaItem mediaItem, String str, Throwable th) {
        super(str, th);
        this.mediaItem = mediaItem;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
    
        if (r3 == null) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean errorInfoEquals(PreloadException preloadException) {
        if (this == preloadException) {
            return true;
        }
        if (preloadException != null && getClass() == preloadException.getClass()) {
            Throwable cause = getCause();
            Throwable cause2 = preloadException.getCause();
            if (cause == null || cause2 == null) {
                if (cause == null) {
                }
            } else if (!Objects.equals(cause.getMessage(), cause2.getMessage()) || !Objects.equals(cause.getClass(), cause2.getClass())) {
                return false;
            }
            return Objects.equals(this.mediaItem, preloadException.mediaItem) && Objects.equals(getMessage(), preloadException.getMessage());
        }
        return false;
    }
}
