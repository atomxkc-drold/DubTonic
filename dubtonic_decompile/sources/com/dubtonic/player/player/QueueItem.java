package com.dubtonic.player.player;

import com.dubtonic.player.data.model.Song;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerState.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÇ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010\u0015\u001a\u00020\u0016H×\u0001J\t\u0010\u0017\u001a\u00020\u0005H×\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/dubtonic/player/player/QueueItem;", "", "song", "Lcom/dubtonic/player/data/model/Song;", "streamUrl", "", "artworkUrl", "<init>", "(Lcom/dubtonic/player/data/model/Song;Ljava/lang/String;Ljava/lang/String;)V", "getSong", "()Lcom/dubtonic/player/data/model/Song;", "getStreamUrl", "()Ljava/lang/String;", "getArtworkUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class QueueItem {
    public static final int $stable = 0;
    private final String artworkUrl;
    private final Song song;
    private final String streamUrl;

    public static /* synthetic */ QueueItem copy$default(QueueItem queueItem, Song song, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            song = queueItem.song;
        }
        if ((i & 2) != 0) {
            str = queueItem.streamUrl;
        }
        if ((i & 4) != 0) {
            str2 = queueItem.artworkUrl;
        }
        return queueItem.copy(song, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Song getSong() {
        return this.song;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStreamUrl() {
        return this.streamUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getArtworkUrl() {
        return this.artworkUrl;
    }

    public final QueueItem copy(Song song, String streamUrl, String artworkUrl) {
        Intrinsics.checkNotNullParameter(song, "song");
        return new QueueItem(song, streamUrl, artworkUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueItem)) {
            return false;
        }
        QueueItem queueItem = (QueueItem) other;
        return Intrinsics.areEqual(this.song, queueItem.song) && Intrinsics.areEqual(this.streamUrl, queueItem.streamUrl) && Intrinsics.areEqual(this.artworkUrl, queueItem.artworkUrl);
    }

    public int hashCode() {
        int iHashCode = this.song.hashCode() * 31;
        String str = this.streamUrl;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.artworkUrl;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "QueueItem(song=" + this.song + ", streamUrl=" + this.streamUrl + ", artworkUrl=" + this.artworkUrl + ")";
    }

    public QueueItem(Song song, String str, String str2) {
        Intrinsics.checkNotNullParameter(song, "song");
        this.song = song;
        this.streamUrl = str;
        this.artworkUrl = str2;
    }

    public /* synthetic */ QueueItem(Song song, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(song, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2);
    }

    public final Song getSong() {
        return this.song;
    }

    public final String getStreamUrl() {
        return this.streamUrl;
    }

    public final String getArtworkUrl() {
        return this.artworkUrl;
    }
}
