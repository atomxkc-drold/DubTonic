package com.dubtonic.player.data.model;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 J2\u00020\u0001:\u0002IJB\u0085\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014B\u0091\u0001\b\u0010\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0013\u0010\u0019J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\tHÆ\u0003J\t\u00106\u001a\u00020\tHÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\tHÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0003J\t\u0010;\u001a\u00020\u0012HÆ\u0003J\u0087\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u00032\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÇ\u0001J\u0013\u0010=\u001a\u00020\u00122\b\u0010>\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010?\u001a\u00020\u0016H×\u0001J\t\u0010@\u001a\u00020\u0003H×\u0001J%\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u00002\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0001¢\u0006\u0002\bHR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010$R\u001c\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u001f\u001a\u0004\b&\u0010$R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001bR\u001c\u0010\f\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010\u001f\u001a\u0004\b)\u0010$R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010-R\u0011\u0010.\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b/\u0010\u001b¨\u0006K"}, d2 = {"Lcom/dubtonic/player/data/model/Album;", "", TtmlNode.ATTR_ID, "", HintConstants.AUTOFILL_HINT_NAME, "artist", "artistId", "coverArt", "songCount", "", "duration", "created", "year", "genre", "song", "", "Lcom/dubtonic/player/data/model/Song;", "isDir", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;JLjava/lang/String;Ljava/util/List;Z)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;JLjava/lang/String;Ljava/util/List;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getName", "getArtist", "getArtistId$annotations", "()V", "getArtistId", "getCoverArt", "getSongCount$annotations", "getSongCount", "()J", "getDuration$annotations", "getDuration", "getCreated", "getYear$annotations", "getYear", "getGenre", "getSong", "()Ljava/util/List;", "()Z", "artworkKey", "getArtworkKey", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class Album {
    private final String artist;
    private final String artistId;
    private final String coverArt;
    private final String created;
    private final long duration;
    private final String genre;
    private final String id;
    private final boolean isDir;
    private final String name;
    private final List<Song> song;
    private final long songCount;
    private final long year;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, null, null, null, null, null, new ArrayListSerializer(Song$$serializer.INSTANCE), null};

    public Album() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, 0L, 0L, (String) null, 0L, (String) null, (List) null, false, 4095, (DefaultConstructorMarker) null);
    }

    @Serializable(with = FlexibleStringSerializer.class)
    public static /* synthetic */ void getArtistId$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getDuration$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getSongCount$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getYear$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getGenre() {
        return this.genre;
    }

    public final List<Song> component11() {
        return this.song;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getIsDir() {
        return this.isDir;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getArtistId() {
        return this.artistId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCoverArt() {
        return this.coverArt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getSongCount() {
        return this.songCount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getCreated() {
        return this.created;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getYear() {
        return this.year;
    }

    public final Album copy(String id, String name, String artist, String artistId, String coverArt, long songCount, long duration, String created, long year, String genre, List<Song> song, boolean isDir) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(artistId, "artistId");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(created, "created");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(song, "song");
        return new Album(id, name, artist, artistId, coverArt, songCount, duration, created, year, genre, song, isDir);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Album)) {
            return false;
        }
        Album album = (Album) other;
        return Intrinsics.areEqual(this.id, album.id) && Intrinsics.areEqual(this.name, album.name) && Intrinsics.areEqual(this.artist, album.artist) && Intrinsics.areEqual(this.artistId, album.artistId) && Intrinsics.areEqual(this.coverArt, album.coverArt) && this.songCount == album.songCount && this.duration == album.duration && Intrinsics.areEqual(this.created, album.created) && this.year == album.year && Intrinsics.areEqual(this.genre, album.genre) && Intrinsics.areEqual(this.song, album.song) && this.isDir == album.isDir;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.artist.hashCode()) * 31) + this.artistId.hashCode()) * 31) + this.coverArt.hashCode()) * 31) + Long.hashCode(this.songCount)) * 31) + Long.hashCode(this.duration)) * 31) + this.created.hashCode()) * 31) + Long.hashCode(this.year)) * 31) + this.genre.hashCode()) * 31) + this.song.hashCode()) * 31) + Boolean.hashCode(this.isDir);
    }

    public String toString() {
        return "Album(id=" + this.id + ", name=" + this.name + ", artist=" + this.artist + ", artistId=" + this.artistId + ", coverArt=" + this.coverArt + ", songCount=" + this.songCount + ", duration=" + this.duration + ", created=" + this.created + ", year=" + this.year + ", genre=" + this.genre + ", song=" + this.song + ", isDir=" + this.isDir + ")";
    }

    /* JADX INFO: compiled from: Models.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dubtonic/player/data/model/Album$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/dubtonic/player/data/model/Album;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<Album> serializer() {
            return Album$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Album(int i, String str, String str2, String str3, String str4, String str5, long j, long j2, String str6, long j3, String str7, List list, boolean z, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.id = "";
        } else {
            this.id = str;
        }
        if ((i & 2) == 0) {
            this.name = "";
        } else {
            this.name = str2;
        }
        if ((i & 4) == 0) {
            this.artist = "";
        } else {
            this.artist = str3;
        }
        if ((i & 8) == 0) {
            this.artistId = "";
        } else {
            this.artistId = str4;
        }
        if ((i & 16) == 0) {
            this.coverArt = "";
        } else {
            this.coverArt = str5;
        }
        if ((i & 32) == 0) {
            this.songCount = 0L;
        } else {
            this.songCount = j;
        }
        if ((i & 64) == 0) {
            this.duration = 0L;
        } else {
            this.duration = j2;
        }
        if ((i & 128) == 0) {
            this.created = "";
        } else {
            this.created = str6;
        }
        this.year = (i & 256) != 0 ? j3 : 0L;
        if ((i & 512) == 0) {
            this.genre = "";
        } else {
            this.genre = str7;
        }
        this.song = (i & 1024) == 0 ? CollectionsKt.emptyList() : list;
        this.isDir = (i & 2048) == 0 ? false : z;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_release(Album self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.id, "")) {
            output.encodeStringElement(serialDesc, 0, self.id);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.name, "")) {
            output.encodeStringElement(serialDesc, 1, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.artist, "")) {
            output.encodeStringElement(serialDesc, 2, self.artist);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.artistId, "")) {
            output.encodeSerializableElement(serialDesc, 3, FlexibleStringSerializer.INSTANCE, self.artistId);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.coverArt, "")) {
            output.encodeStringElement(serialDesc, 4, self.coverArt);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.songCount != 0) {
            output.encodeSerializableElement(serialDesc, 5, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.songCount));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.duration != 0) {
            output.encodeSerializableElement(serialDesc, 6, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.duration));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || !Intrinsics.areEqual(self.created, "")) {
            output.encodeStringElement(serialDesc, 7, self.created);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.year != 0) {
            output.encodeSerializableElement(serialDesc, 8, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.year));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || !Intrinsics.areEqual(self.genre, "")) {
            output.encodeStringElement(serialDesc, 9, self.genre);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || !Intrinsics.areEqual(self.song, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 10, kSerializerArr[10], self.song);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.isDir) {
            output.encodeBooleanElement(serialDesc, 11, self.isDir);
        }
    }

    public Album(String id, String name, String artist, String artistId, String coverArt, long j, long j2, String created, long j3, String genre, List<Song> song, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(artistId, "artistId");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(created, "created");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(song, "song");
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.artistId = artistId;
        this.coverArt = coverArt;
        this.songCount = j;
        this.duration = j2;
        this.created = created;
        this.year = j3;
        this.genre = genre;
        this.song = song;
        this.isDir = z;
    }

    public /* synthetic */ Album(String str, String str2, String str3, String str4, String str5, long j, long j2, String str6, long j3, String str7, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? 0L : j, (i & 64) != 0 ? 0L : j2, (i & 128) != 0 ? "" : str6, (i & 256) == 0 ? j3 : 0L, (i & 512) == 0 ? str7 : "", (i & 1024) != 0 ? CollectionsKt.emptyList() : list, (i & 2048) != 0 ? false : z);
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final String getArtistId() {
        return this.artistId;
    }

    public final String getCoverArt() {
        return this.coverArt;
    }

    public final long getSongCount() {
        return this.songCount;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final String getCreated() {
        return this.created;
    }

    public final long getYear() {
        return this.year;
    }

    public final String getGenre() {
        return this.genre;
    }

    public final List<Song> getSong() {
        return this.song;
    }

    public final boolean isDir() {
        return this.isDir;
    }

    public final String getArtworkKey() {
        String str = this.coverArt;
        if (StringsKt.isBlank(str)) {
            str = this.id;
        }
        return str;
    }
}
