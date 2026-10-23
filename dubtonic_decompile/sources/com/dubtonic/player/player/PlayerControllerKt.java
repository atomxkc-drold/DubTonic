package com.dubtonic.player.player;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PlayerController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0003H\u0002\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0005H\u0002\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0002¨\u0006\t"}, d2 = {"bitDepth", "", "codecLabel", "", "toPlayerRepeat", "Lcom/dubtonic/player/player/RepeatMode;", "toMediaItem", "Landroidx/media3/common/MediaItem;", "Lcom/dubtonic/player/player/QueueItem;", "app_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PlayerControllerKt {

    /* JADX INFO: compiled from: PlayerController.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            try {
                iArr[RepeatMode.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RepeatMode.ALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[RepeatMode.ONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int bitDepth(int i) {
        if (i == 2) {
            return 16;
        }
        if (i == 3) {
            return 8;
        }
        if (i != 4) {
            if (i == 21) {
                return 24;
            }
            if (i != 22) {
                return 0;
            }
        }
        return 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
    
        if (r3.equals("mpeg") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        if (r3.equals("flac") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0058, code lost:
    
        if (r3.equals("3gpp") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
    
        if (r3.equals("wav") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006a, code lost:
    
        if (r3.equals("pcm") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006d, code lost:
    
        return "WAV";
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0076, code lost:
    
        if (r3.equals("ogg") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007f, code lost:
    
        if (r3.equals("mp4") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0088, code lost:
    
        if (r3.equals("mp3") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008b, code lost:
    
        return "MP3";
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0094, code lost:
    
        if (r3.equals("aac") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0097, code lost:
    
        return "AAC";
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a0, code lost:
    
        if (r3.equals("vorbis") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a3, code lost:
    
        return "OGG";
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ac, code lost:
    
        if (r3.equals("x-flac") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00af, code lost:
    
        return "FLAC";
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0022, code lost:
    
        if (r3.equals("vnd.wave") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002c, code lost:
    
        if (r3.equals("x-wav") == false) goto L53;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String codecLabel(String str) {
        String lowerCase = StringsKt.substringAfterLast$default(str, '/', (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase.hashCode()) {
            case -814765155:
                break;
            case -810722925:
                break;
            case 96323:
                break;
            case 108272:
                break;
            case 108273:
                break;
            case 109967:
                break;
            case 110810:
                break;
            case 117484:
                break;
            case 1621908:
                break;
            case 3145576:
                break;
            case 3358085:
                break;
            case 3418175:
                if (lowerCase.equals("opus")) {
                    return "Opus";
                }
                String upperCase = StringsKt.substringAfterLast$default(str, '/', (String) null, 2, (Object) null).toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                return upperCase;
            case 112280599:
                break;
            case 1243385595:
                break;
            default:
                String upperCase2 = StringsKt.substringAfterLast$default(str, '/', (String) null, 2, (Object) null).toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                return upperCase2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int toPlayerRepeat(RepeatMode repeatMode) {
        int i = WhenMappings.$EnumSwitchMapping$0[repeatMode.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 1;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaItem toMediaItem(QueueItem queueItem) {
        String streamUrl = queueItem.getStreamUrl();
        if (streamUrl == null) {
            return null;
        }
        MediaItem.Builder mediaId = new MediaItem.Builder().setUri(Uri.parse(streamUrl)).setMediaId(queueItem.getSong().getId());
        MediaMetadata.Builder albumTitle = new MediaMetadata.Builder().setTitle(queueItem.getSong().getTitle()).setArtist(queueItem.getSong().getArtist()).setAlbumTitle(queueItem.getSong().getAlbum());
        String artworkUrl = queueItem.getArtworkUrl();
        if (artworkUrl != null) {
            albumTitle.setArtworkUri(Uri.parse(artworkUrl));
        }
        return mediaId.setMediaMetadata(albumTitle.build()).build();
    }
}
