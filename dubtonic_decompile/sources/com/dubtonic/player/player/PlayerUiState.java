package com.dubtonic.player.player;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerState.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b?\b\u0087\b\u0018\u00002\u00020\u0001B×\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001e¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0006HÆ\u0003J\t\u0010F\u001a\u00020\bHÆ\u0003J\t\u0010G\u001a\u00020\bHÆ\u0003J\t\u0010H\u001a\u00020\u000bHÆ\u0003J\t\u0010I\u001a\u00020\u000bHÆ\u0003J\t\u0010J\u001a\u00020\u000eHÆ\u0003J\t\u0010K\u001a\u00020\bHÆ\u0003J\t\u0010L\u001a\u00020\u000eHÆ\u0003J\t\u0010M\u001a\u00020\bHÆ\u0003J\t\u0010N\u001a\u00020\u0013HÆ\u0003J\t\u0010O\u001a\u00020\u000bHÆ\u0003J\t\u0010P\u001a\u00020\bHÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\t\u0010R\u001a\u00020\u0006HÆ\u0003J\t\u0010S\u001a\u00020\u0006HÆ\u0003J\t\u0010T\u001a\u00020\u0006HÆ\u0003J\t\u0010U\u001a\u00020\u0017HÆ\u0003J\t\u0010V\u001a\u00020\u0006HÆ\u0003J\t\u0010W\u001a\u00020\u001eHÆ\u0003JÙ\u0001\u0010X\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u001d\u001a\u00020\u001eHÇ\u0001J\u0013\u0010Y\u001a\u00020\b2\b\u0010Z\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010[\u001a\u00020\u0006H×\u0001J\t\u0010\\\u001a\u00020\u0017H×\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u000f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&R\u0011\u0010\u0010\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010,R\u0011\u0010\u0011\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b/\u0010&R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0014\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b2\u0010)R\u0011\u0010\u0015\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b3\u0010&R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0018\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b6\u0010$R\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b7\u0010$R\u0011\u0010\u001a\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b8\u0010$R\u0011\u0010\u001b\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b9\u00105R\u0011\u0010\u001c\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b:\u0010$R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0013\u0010=\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\bA\u0010&R\u0011\u0010B\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\bC\u0010)¨\u0006]"}, d2 = {"Lcom/dubtonic/player/player/PlayerUiState;", "", "queue", "", "Lcom/dubtonic/player/player/QueueItem;", "index", "", "playing", "", "loading", "positionMs", "", "durationMs", "volume", "", "muted", "speed", "shuffle", "repeat", "Lcom/dubtonic/player/player/RepeatMode;", "sleepDeadlineMs", "demo", "error", "", "audioSessionId", "sampleRate", "bitDepth", "codec", "bitrateBps", "eq", "Lcom/dubtonic/player/player/EqState;", "<init>", "(Ljava/util/List;IZZJJFZFZLcom/dubtonic/player/player/RepeatMode;JZLjava/lang/String;IIILjava/lang/String;ILcom/dubtonic/player/player/EqState;)V", "getQueue", "()Ljava/util/List;", "getIndex", "()I", "getPlaying", "()Z", "getLoading", "getPositionMs", "()J", "getDurationMs", "getVolume", "()F", "getMuted", "getSpeed", "getShuffle", "getRepeat", "()Lcom/dubtonic/player/player/RepeatMode;", "getSleepDeadlineMs", "getDemo", "getError", "()Ljava/lang/String;", "getAudioSessionId", "getSampleRate", "getBitDepth", "getCodec", "getBitrateBps", "getEq", "()Lcom/dubtonic/player/player/EqState;", "current", "getCurrent", "()Lcom/dubtonic/player/player/QueueItem;", "hasQueue", "getHasQueue", "sleepLeftMs", "getSleepLeftMs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PlayerUiState {
    public static final int $stable = 8;
    private final int audioSessionId;
    private final int bitDepth;
    private final int bitrateBps;
    private final String codec;
    private final boolean demo;
    private final long durationMs;
    private final EqState eq;
    private final String error;
    private final int index;
    private final boolean loading;
    private final boolean muted;
    private final boolean playing;
    private final long positionMs;
    private final List<QueueItem> queue;
    private final RepeatMode repeat;
    private final int sampleRate;
    private final boolean shuffle;
    private final long sleepDeadlineMs;
    private final float speed;
    private final float volume;

    public PlayerUiState() {
        this(null, 0, false, false, 0L, 0L, 0.0f, false, 0.0f, false, null, 0L, false, null, 0, 0, 0, null, 0, null, 1048575, null);
    }

    public final List<QueueItem> component1() {
        return this.queue;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getShuffle() {
        return this.shuffle;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final RepeatMode getRepeat() {
        return this.repeat;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getSleepDeadlineMs() {
        return this.sleepDeadlineMs;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getDemo() {
        return this.demo;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getAudioSessionId() {
        return this.audioSessionId;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getSampleRate() {
        return this.sampleRate;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getBitDepth() {
        return this.bitDepth;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getCodec() {
        return this.codec;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final int getBitrateBps() {
        return this.bitrateBps;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final EqState getEq() {
        return this.eq;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPlaying() {
        return this.playing;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getLoading() {
        return this.loading;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getPositionMs() {
        return this.positionMs;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getVolume() {
        return this.volume;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getMuted() {
        return this.muted;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final float getSpeed() {
        return this.speed;
    }

    public final PlayerUiState copy(List<QueueItem> queue, int index, boolean playing, boolean loading, long positionMs, long durationMs, float volume, boolean muted, float speed, boolean shuffle, RepeatMode repeat, long sleepDeadlineMs, boolean demo, String error, int audioSessionId, int sampleRate, int bitDepth, String codec, int bitrateBps, EqState eq) {
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(repeat, "repeat");
        Intrinsics.checkNotNullParameter(codec, "codec");
        Intrinsics.checkNotNullParameter(eq, "eq");
        return new PlayerUiState(queue, index, playing, loading, positionMs, durationMs, volume, muted, speed, shuffle, repeat, sleepDeadlineMs, demo, error, audioSessionId, sampleRate, bitDepth, codec, bitrateBps, eq);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerUiState)) {
            return false;
        }
        PlayerUiState playerUiState = (PlayerUiState) other;
        return Intrinsics.areEqual(this.queue, playerUiState.queue) && this.index == playerUiState.index && this.playing == playerUiState.playing && this.loading == playerUiState.loading && this.positionMs == playerUiState.positionMs && this.durationMs == playerUiState.durationMs && Float.compare(this.volume, playerUiState.volume) == 0 && this.muted == playerUiState.muted && Float.compare(this.speed, playerUiState.speed) == 0 && this.shuffle == playerUiState.shuffle && this.repeat == playerUiState.repeat && this.sleepDeadlineMs == playerUiState.sleepDeadlineMs && this.demo == playerUiState.demo && Intrinsics.areEqual(this.error, playerUiState.error) && this.audioSessionId == playerUiState.audioSessionId && this.sampleRate == playerUiState.sampleRate && this.bitDepth == playerUiState.bitDepth && Intrinsics.areEqual(this.codec, playerUiState.codec) && this.bitrateBps == playerUiState.bitrateBps && Intrinsics.areEqual(this.eq, playerUiState.eq);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((((((((this.queue.hashCode() * 31) + Integer.hashCode(this.index)) * 31) + Boolean.hashCode(this.playing)) * 31) + Boolean.hashCode(this.loading)) * 31) + Long.hashCode(this.positionMs)) * 31) + Long.hashCode(this.durationMs)) * 31) + Float.hashCode(this.volume)) * 31) + Boolean.hashCode(this.muted)) * 31) + Float.hashCode(this.speed)) * 31) + Boolean.hashCode(this.shuffle)) * 31) + this.repeat.hashCode()) * 31) + Long.hashCode(this.sleepDeadlineMs)) * 31) + Boolean.hashCode(this.demo)) * 31;
        String str = this.error;
        return ((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.audioSessionId)) * 31) + Integer.hashCode(this.sampleRate)) * 31) + Integer.hashCode(this.bitDepth)) * 31) + this.codec.hashCode()) * 31) + Integer.hashCode(this.bitrateBps)) * 31) + this.eq.hashCode();
    }

    public String toString() {
        return "PlayerUiState(queue=" + this.queue + ", index=" + this.index + ", playing=" + this.playing + ", loading=" + this.loading + ", positionMs=" + this.positionMs + ", durationMs=" + this.durationMs + ", volume=" + this.volume + ", muted=" + this.muted + ", speed=" + this.speed + ", shuffle=" + this.shuffle + ", repeat=" + this.repeat + ", sleepDeadlineMs=" + this.sleepDeadlineMs + ", demo=" + this.demo + ", error=" + this.error + ", audioSessionId=" + this.audioSessionId + ", sampleRate=" + this.sampleRate + ", bitDepth=" + this.bitDepth + ", codec=" + this.codec + ", bitrateBps=" + this.bitrateBps + ", eq=" + this.eq + ")";
    }

    public PlayerUiState(List<QueueItem> queue, int i, boolean z, boolean z2, long j, long j2, float f, boolean z3, float f2, boolean z4, RepeatMode repeat, long j3, boolean z5, String str, int i2, int i3, int i4, String codec, int i5, EqState eq) {
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(repeat, "repeat");
        Intrinsics.checkNotNullParameter(codec, "codec");
        Intrinsics.checkNotNullParameter(eq, "eq");
        this.queue = queue;
        this.index = i;
        this.playing = z;
        this.loading = z2;
        this.positionMs = j;
        this.durationMs = j2;
        this.volume = f;
        this.muted = z3;
        this.speed = f2;
        this.shuffle = z4;
        this.repeat = repeat;
        this.sleepDeadlineMs = j3;
        this.demo = z5;
        this.error = str;
        this.audioSessionId = i2;
        this.sampleRate = i3;
        this.bitDepth = i4;
        this.codec = codec;
        this.bitrateBps = i5;
        this.eq = eq;
    }

    public /* synthetic */ PlayerUiState(List list, int i, boolean z, boolean z2, long j, long j2, float f, boolean z3, float f2, boolean z4, RepeatMode repeatMode, long j3, boolean z5, String str, int i2, int i3, int i4, String str2, int i5, EqState eqState, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? CollectionsKt.emptyList() : list, (i6 & 2) != 0 ? -1 : i, (i6 & 4) != 0 ? false : z, (i6 & 8) != 0 ? false : z2, (i6 & 16) != 0 ? 0L : j, (i6 & 32) != 0 ? 0L : j2, (i6 & 64) != 0 ? 1.0f : f, (i6 & 128) != 0 ? false : z3, (i6 & 256) == 0 ? f2 : 1.0f, (i6 & 512) != 0 ? false : z4, (i6 & 1024) != 0 ? RepeatMode.ALL : repeatMode, (i6 & 2048) != 0 ? 0L : j3, (i6 & 4096) != 0 ? false : z5, (i6 & 8192) != 0 ? null : str, (i6 & 16384) != 0 ? 0 : i2, (i6 & 32768) != 0 ? 0 : i3, (i6 & 65536) != 0 ? 0 : i4, (i6 & 131072) != 0 ? "" : str2, (i6 & 262144) != 0 ? 0 : i5, (i6 & 524288) != 0 ? new EqState(false, null, null, false, 15, null) : eqState);
    }

    public final List<QueueItem> getQueue() {
        return this.queue;
    }

    public final int getIndex() {
        return this.index;
    }

    public final boolean getPlaying() {
        return this.playing;
    }

    public final boolean getLoading() {
        return this.loading;
    }

    public final long getPositionMs() {
        return this.positionMs;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final float getVolume() {
        return this.volume;
    }

    public final boolean getMuted() {
        return this.muted;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final boolean getShuffle() {
        return this.shuffle;
    }

    public final RepeatMode getRepeat() {
        return this.repeat;
    }

    public final long getSleepDeadlineMs() {
        return this.sleepDeadlineMs;
    }

    public final boolean getDemo() {
        return this.demo;
    }

    public final String getError() {
        return this.error;
    }

    public final int getAudioSessionId() {
        return this.audioSessionId;
    }

    public final int getSampleRate() {
        return this.sampleRate;
    }

    public final int getBitDepth() {
        return this.bitDepth;
    }

    public final String getCodec() {
        return this.codec;
    }

    public final int getBitrateBps() {
        return this.bitrateBps;
    }

    public final EqState getEq() {
        return this.eq;
    }

    public final QueueItem getCurrent() {
        return (QueueItem) CollectionsKt.getOrNull(this.queue, this.index);
    }

    public final boolean getHasQueue() {
        return !this.queue.isEmpty();
    }

    public final long getSleepLeftMs() {
        long j = this.sleepDeadlineMs;
        if (j == 0) {
            return 0L;
        }
        return Math.max(0L, j - System.currentTimeMillis());
    }
}
