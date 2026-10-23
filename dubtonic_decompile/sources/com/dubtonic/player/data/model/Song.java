package com.dubtonic.player.data.model;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b9\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 _2\u00020\u0001:\u0002^_BÅ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\f\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\f\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003¢\u0006\u0004\b\u0018\u0010\u0019BÉ\u0001\b\u0010\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0011\u001a\u00020\f\u0012\u0006\u0010\u0012\u001a\u00020\f\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0016\u001a\u00020\f\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u0018\u0010\u001eJ\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\tHÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\fHÆ\u0003J\t\u0010F\u001a\u00020\fHÆ\u0003J\t\u0010G\u001a\u00020\fHÆ\u0003J\t\u0010H\u001a\u00020\fHÆ\u0003J\t\u0010I\u001a\u00020\u0003HÆ\u0003J\t\u0010J\u001a\u00020\fHÆ\u0003J\t\u0010K\u001a\u00020\fHÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\fHÆ\u0003J\t\u0010P\u001a\u00020\u0003HÆ\u0003JÇ\u0001\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\u0003HÇ\u0001J\u0013\u0010R\u001a\u00020\t2\b\u0010S\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010T\u001a\u00020\u001bH×\u0001J\t\u0010U\u001a\u00020\u0003H×\u0001J%\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020\u00002\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\H\u0001¢\u0006\u0002\b]R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010%R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u001c\u0010\r\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010(\u001a\u0004\b,\u0010*R\u001c\u0010\u000e\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010(\u001a\u0004\b.\u0010*R\u001c\u0010\u000f\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010(\u001a\u0004\b0\u0010*R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010 R\u001c\u0010\u0011\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010(\u001a\u0004\b3\u0010*R\u001c\u0010\u0012\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010(\u001a\u0004\b5\u0010*R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010 R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010 R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010 R\u001c\u0010\u0016\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b9\u0010(\u001a\u0004\b:\u0010*R\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b;\u0010 R\u0011\u0010<\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b=\u0010 ¨\u0006`"}, d2 = {"Lcom/dubtonic/player/data/model/Song;", "", TtmlNode.ATTR_ID, "", "parent", "title", "album", "artist", "isDir", "", "coverArt", "duration", "", "track", "discNumber", "year", "genre", "bitRate", "size", "suffix", "contentType", "path", "playCount", "type", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;JJJJLjava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;JJJJLjava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getParent", "getTitle", "getAlbum", "getArtist", "()Z", "getCoverArt", "getDuration$annotations", "()V", "getDuration", "()J", "getTrack$annotations", "getTrack", "getDiscNumber$annotations", "getDiscNumber", "getYear$annotations", "getYear", "getGenre", "getBitRate$annotations", "getBitRate", "getSize$annotations", "getSize", "getSuffix", "getContentType", "getPath", "getPlayCount$annotations", "getPlayCount", "getType", "artworkKey", "getArtworkKey", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class Song {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String album;
    private final String artist;
    private final long bitRate;
    private final String contentType;
    private final String coverArt;
    private final long discNumber;
    private final long duration;
    private final String genre;
    private final String id;
    private final boolean isDir;
    private final String parent;
    private final String path;
    private final long playCount;
    private final long size;
    private final String suffix;
    private final String title;
    private final long track;
    private final String type;
    private final long year;

    public Song() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, false, (String) null, 0L, 0L, 0L, 0L, (String) null, 0L, 0L, (String) null, (String) null, (String) null, 0L, (String) null, 524287, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Song copy$default(Song song, String str, String str2, String str3, String str4, String str5, boolean z, String str6, long j, long j2, long j3, long j4, String str7, long j5, long j6, String str8, String str9, String str10, long j7, String str11, int i, Object obj) {
        String str12 = (i & 1) != 0 ? song.id : str;
        String str13 = (i & 2) != 0 ? song.parent : str2;
        String str14 = (i & 4) != 0 ? song.title : str3;
        String str15 = (i & 8) != 0 ? song.album : str4;
        String str16 = (i & 16) != 0 ? song.artist : str5;
        boolean z2 = (i & 32) != 0 ? song.isDir : z;
        String str17 = (i & 64) != 0 ? song.coverArt : str6;
        long j8 = (i & 128) != 0 ? song.duration : j;
        long j9 = (i & 256) != 0 ? song.track : j2;
        long j10 = (i & 512) != 0 ? song.discNumber : j3;
        long j11 = (i & 1024) != 0 ? song.year : j4;
        return song.copy(str12, str13, str14, str15, str16, z2, str17, j8, j9, j10, j11, (i & 2048) != 0 ? song.genre : str7, (i & 4096) != 0 ? song.bitRate : j5, (i & 8192) != 0 ? song.size : j6, (i & 16384) != 0 ? song.suffix : str8, (i & 32768) != 0 ? song.contentType : str9, (i & 65536) != 0 ? song.path : str10, (i & 131072) != 0 ? song.playCount : j7, (i & 262144) != 0 ? song.type : str11);
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getBitRate$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getDiscNumber$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getDuration$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getPlayCount$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getSize$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getTrack$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getYear$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getDiscNumber() {
        return this.discNumber;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getGenre() {
        return this.genre;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getBitRate() {
        return this.bitRate;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getSuffix() {
        return this.suffix;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getContentType() {
        return this.contentType;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final long getPlayCount() {
        return this.playCount;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getParent() {
        return this.parent;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAlbum() {
        return this.album;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsDir() {
        return this.isDir;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getCoverArt() {
        return this.coverArt;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getTrack() {
        return this.track;
    }

    public final Song copy(String id, String parent, String title, String album, String artist, boolean isDir, String coverArt, long duration, long track, long discNumber, long year, String genre, long bitRate, long size, String suffix, String contentType, String path, long playCount, String type) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(type, "type");
        return new Song(id, parent, title, album, artist, isDir, coverArt, duration, track, discNumber, year, genre, bitRate, size, suffix, contentType, path, playCount, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Song)) {
            return false;
        }
        Song song = (Song) other;
        return Intrinsics.areEqual(this.id, song.id) && Intrinsics.areEqual(this.parent, song.parent) && Intrinsics.areEqual(this.title, song.title) && Intrinsics.areEqual(this.album, song.album) && Intrinsics.areEqual(this.artist, song.artist) && this.isDir == song.isDir && Intrinsics.areEqual(this.coverArt, song.coverArt) && this.duration == song.duration && this.track == song.track && this.discNumber == song.discNumber && this.year == song.year && Intrinsics.areEqual(this.genre, song.genre) && this.bitRate == song.bitRate && this.size == song.size && Intrinsics.areEqual(this.suffix, song.suffix) && Intrinsics.areEqual(this.contentType, song.contentType) && Intrinsics.areEqual(this.path, song.path) && this.playCount == song.playCount && Intrinsics.areEqual(this.type, song.type);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.parent.hashCode()) * 31) + this.title.hashCode()) * 31) + this.album.hashCode()) * 31) + this.artist.hashCode()) * 31) + Boolean.hashCode(this.isDir)) * 31) + this.coverArt.hashCode()) * 31) + Long.hashCode(this.duration)) * 31) + Long.hashCode(this.track)) * 31) + Long.hashCode(this.discNumber)) * 31) + Long.hashCode(this.year)) * 31) + this.genre.hashCode()) * 31) + Long.hashCode(this.bitRate)) * 31) + Long.hashCode(this.size)) * 31) + this.suffix.hashCode()) * 31) + this.contentType.hashCode()) * 31) + this.path.hashCode()) * 31) + Long.hashCode(this.playCount)) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "Song(id=" + this.id + ", parent=" + this.parent + ", title=" + this.title + ", album=" + this.album + ", artist=" + this.artist + ", isDir=" + this.isDir + ", coverArt=" + this.coverArt + ", duration=" + this.duration + ", track=" + this.track + ", discNumber=" + this.discNumber + ", year=" + this.year + ", genre=" + this.genre + ", bitRate=" + this.bitRate + ", size=" + this.size + ", suffix=" + this.suffix + ", contentType=" + this.contentType + ", path=" + this.path + ", playCount=" + this.playCount + ", type=" + this.type + ")";
    }

    /* JADX INFO: compiled from: Models.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dubtonic/player/data/model/Song$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/dubtonic/player/data/model/Song;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<Song> serializer() {
            return Song$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Song(int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, long j, long j2, long j3, long j4, String str7, long j5, long j6, String str8, String str9, String str10, long j7, String str11, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.id = "";
        } else {
            this.id = str;
        }
        if ((i & 2) == 0) {
            this.parent = "";
        } else {
            this.parent = str2;
        }
        if ((i & 4) == 0) {
            this.title = "";
        } else {
            this.title = str3;
        }
        if ((i & 8) == 0) {
            this.album = "";
        } else {
            this.album = str4;
        }
        if ((i & 16) == 0) {
            this.artist = "";
        } else {
            this.artist = str5;
        }
        this.isDir = (i & 32) == 0 ? false : z;
        if ((i & 64) == 0) {
            this.coverArt = "";
        } else {
            this.coverArt = str6;
        }
        if ((i & 128) == 0) {
            this.duration = 0L;
        } else {
            this.duration = j;
        }
        if ((i & 256) == 0) {
            this.track = 0L;
        } else {
            this.track = j2;
        }
        this.discNumber = (i & 512) == 0 ? 1L : j3;
        if ((i & 1024) == 0) {
            this.year = 0L;
        } else {
            this.year = j4;
        }
        if ((i & 2048) == 0) {
            this.genre = "";
        } else {
            this.genre = str7;
        }
        if ((i & 4096) == 0) {
            this.bitRate = 0L;
        } else {
            this.bitRate = j5;
        }
        if ((i & 8192) == 0) {
            this.size = 0L;
        } else {
            this.size = j6;
        }
        if ((i & 16384) == 0) {
            this.suffix = "";
        } else {
            this.suffix = str8;
        }
        if ((32768 & i) == 0) {
            this.contentType = "";
        } else {
            this.contentType = str9;
        }
        if ((65536 & i) == 0) {
            this.path = "";
        } else {
            this.path = str10;
        }
        this.playCount = (131072 & i) != 0 ? j7 : 0L;
        if ((i & 262144) == 0) {
            this.type = "";
        } else {
            this.type = str11;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_release(Song self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.id, "")) {
            output.encodeStringElement(serialDesc, 0, self.id);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.parent, "")) {
            output.encodeStringElement(serialDesc, 1, self.parent);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.title, "")) {
            output.encodeStringElement(serialDesc, 2, self.title);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.album, "")) {
            output.encodeStringElement(serialDesc, 3, self.album);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.artist, "")) {
            output.encodeStringElement(serialDesc, 4, self.artist);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.isDir) {
            output.encodeBooleanElement(serialDesc, 5, self.isDir);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || !Intrinsics.areEqual(self.coverArt, "")) {
            output.encodeStringElement(serialDesc, 6, self.coverArt);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.duration != 0) {
            output.encodeSerializableElement(serialDesc, 7, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.duration));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.track != 0) {
            output.encodeSerializableElement(serialDesc, 8, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.track));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.discNumber != 1) {
            output.encodeSerializableElement(serialDesc, 9, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.discNumber));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.year != 0) {
            output.encodeSerializableElement(serialDesc, 10, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.year));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || !Intrinsics.areEqual(self.genre, "")) {
            output.encodeStringElement(serialDesc, 11, self.genre);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || self.bitRate != 0) {
            output.encodeSerializableElement(serialDesc, 12, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.bitRate));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 13) || self.size != 0) {
            output.encodeSerializableElement(serialDesc, 13, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.size));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 14) || !Intrinsics.areEqual(self.suffix, "")) {
            output.encodeStringElement(serialDesc, 14, self.suffix);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 15) || !Intrinsics.areEqual(self.contentType, "")) {
            output.encodeStringElement(serialDesc, 15, self.contentType);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 16) || !Intrinsics.areEqual(self.path, "")) {
            output.encodeStringElement(serialDesc, 16, self.path);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 17) || self.playCount != 0) {
            output.encodeSerializableElement(serialDesc, 17, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.playCount));
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 18) && Intrinsics.areEqual(self.type, "")) {
            return;
        }
        output.encodeStringElement(serialDesc, 18, self.type);
    }

    public Song(String id, String parent, String title, String album, String artist, boolean z, String coverArt, long j, long j2, long j3, long j4, String genre, long j5, long j6, String suffix, String contentType, String path, long j7, String type) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(type, "type");
        this.id = id;
        this.parent = parent;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.isDir = z;
        this.coverArt = coverArt;
        this.duration = j;
        this.track = j2;
        this.discNumber = j3;
        this.year = j4;
        this.genre = genre;
        this.bitRate = j5;
        this.size = j6;
        this.suffix = suffix;
        this.contentType = contentType;
        this.path = path;
        this.playCount = j7;
        this.type = type;
    }

    public /* synthetic */ Song(String str, String str2, String str3, String str4, String str5, boolean z, String str6, long j, long j2, long j3, long j4, String str7, long j5, long j6, String str8, String str9, String str10, long j7, String str11, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? false : z, (i & 64) != 0 ? "" : str6, (i & 128) != 0 ? 0L : j, (i & 256) != 0 ? 0L : j2, (i & 512) != 0 ? 1L : j3, (i & 1024) != 0 ? 0L : j4, (i & 2048) != 0 ? "" : str7, (i & 4096) != 0 ? 0L : j5, (i & 8192) != 0 ? 0L : j6, (i & 16384) != 0 ? "" : str8, (i & 32768) != 0 ? "" : str9, (i & 65536) != 0 ? "" : str10, (i & 131072) != 0 ? 0L : j7, (i & 262144) != 0 ? "" : str11);
    }

    public final String getId() {
        return this.id;
    }

    public final String getParent() {
        return this.parent;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final boolean isDir() {
        return this.isDir;
    }

    public final String getCoverArt() {
        return this.coverArt;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final long getTrack() {
        return this.track;
    }

    public final long getDiscNumber() {
        return this.discNumber;
    }

    public final long getYear() {
        return this.year;
    }

    public final String getGenre() {
        return this.genre;
    }

    public final long getBitRate() {
        return this.bitRate;
    }

    public final long getSize() {
        return this.size;
    }

    public final String getSuffix() {
        return this.suffix;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final String getPath() {
        return this.path;
    }

    public final long getPlayCount() {
        return this.playCount;
    }

    public final String getType() {
        return this.type;
    }

    public final String getArtworkKey() {
        String str = this.coverArt;
        if (StringsKt.isBlank(str)) {
            str = this.parent;
        }
        return str;
    }
}
