package com.dubtonic.player.data.model;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 X2\u00020\u0001:\u0002WXB«\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u001b\u0010\u001cB§\u0001\b\u0010\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b\u001b\u0010!J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u001aHÆ\u0003J\u00ad\u0001\u0010I\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÇ\u0001J\u0013\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010M\u001a\u00020\u001eH×\u0001J\t\u0010N\u001a\u00020\u0003H×\u0001J%\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u00002\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020UH\u0001¢\u0006\u0002\bVR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010,R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b4\u00103R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:¨\u0006Y"}, d2 = {"Lcom/dubtonic/player/data/model/SubsonicBody;", "", NotificationCompat.CATEGORY_STATUS, "", "version", "error", "Lcom/dubtonic/player/data/model/SubsonicError;", "artists", "Lcom/dubtonic/player/data/model/ArtistsContainer;", "indexes", "Lcom/dubtonic/player/data/model/IndexesContainer;", "albumList2", "Lcom/dubtonic/player/data/model/AlbumList;", "albumList", "album", "Lcom/dubtonic/player/data/model/Album;", "artist", "Lcom/dubtonic/player/data/model/ArtistDetail;", "topSongs", "Lcom/dubtonic/player/data/model/SongList;", "randomSongs", "playlists", "Lcom/dubtonic/player/data/model/PlaylistsContainer;", "playlist", "Lcom/dubtonic/player/data/model/PlaylistDetail;", "searchResult3", "Lcom/dubtonic/player/data/model/SearchResult3;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/dubtonic/player/data/model/SubsonicError;Lcom/dubtonic/player/data/model/ArtistsContainer;Lcom/dubtonic/player/data/model/IndexesContainer;Lcom/dubtonic/player/data/model/AlbumList;Lcom/dubtonic/player/data/model/AlbumList;Lcom/dubtonic/player/data/model/Album;Lcom/dubtonic/player/data/model/ArtistDetail;Lcom/dubtonic/player/data/model/SongList;Lcom/dubtonic/player/data/model/SongList;Lcom/dubtonic/player/data/model/PlaylistsContainer;Lcom/dubtonic/player/data/model/PlaylistDetail;Lcom/dubtonic/player/data/model/SearchResult3;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lcom/dubtonic/player/data/model/SubsonicError;Lcom/dubtonic/player/data/model/ArtistsContainer;Lcom/dubtonic/player/data/model/IndexesContainer;Lcom/dubtonic/player/data/model/AlbumList;Lcom/dubtonic/player/data/model/AlbumList;Lcom/dubtonic/player/data/model/Album;Lcom/dubtonic/player/data/model/ArtistDetail;Lcom/dubtonic/player/data/model/SongList;Lcom/dubtonic/player/data/model/SongList;Lcom/dubtonic/player/data/model/PlaylistsContainer;Lcom/dubtonic/player/data/model/PlaylistDetail;Lcom/dubtonic/player/data/model/SearchResult3;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStatus", "()Ljava/lang/String;", "getVersion", "getError", "()Lcom/dubtonic/player/data/model/SubsonicError;", "getArtists", "()Lcom/dubtonic/player/data/model/ArtistsContainer;", "getIndexes", "()Lcom/dubtonic/player/data/model/IndexesContainer;", "getAlbumList2", "()Lcom/dubtonic/player/data/model/AlbumList;", "getAlbumList", "getAlbum", "()Lcom/dubtonic/player/data/model/Album;", "getArtist", "()Lcom/dubtonic/player/data/model/ArtistDetail;", "getTopSongs", "()Lcom/dubtonic/player/data/model/SongList;", "getRandomSongs", "getPlaylists", "()Lcom/dubtonic/player/data/model/PlaylistsContainer;", "getPlaylist", "()Lcom/dubtonic/player/data/model/PlaylistDetail;", "getSearchResult3", "()Lcom/dubtonic/player/data/model/SearchResult3;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class SubsonicBody {
    private final Album album;
    private final AlbumList albumList;
    private final AlbumList albumList2;
    private final ArtistDetail artist;
    private final ArtistsContainer artists;
    private final SubsonicError error;
    private final IndexesContainer indexes;
    private final PlaylistDetail playlist;
    private final PlaylistsContainer playlists;
    private final SongList randomSongs;
    private final SearchResult3 searchResult3;
    private final String status;
    private final SongList topSongs;
    private final String version;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public SubsonicBody() {
        this((String) null, (String) null, (SubsonicError) null, (ArtistsContainer) null, (IndexesContainer) null, (AlbumList) null, (AlbumList) null, (Album) null, (ArtistDetail) null, (SongList) null, (SongList) null, (PlaylistsContainer) null, (PlaylistDetail) null, (SearchResult3) null, 16383, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final SongList getTopSongs() {
        return this.topSongs;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final SongList getRandomSongs() {
        return this.randomSongs;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final PlaylistsContainer getPlaylists() {
        return this.playlists;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final PlaylistDetail getPlaylist() {
        return this.playlist;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final SearchResult3 getSearchResult3() {
        return this.searchResult3;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SubsonicError getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ArtistsContainer getArtists() {
        return this.artists;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final IndexesContainer getIndexes() {
        return this.indexes;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final AlbumList getAlbumList2() {
        return this.albumList2;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final AlbumList getAlbumList() {
        return this.albumList;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Album getAlbum() {
        return this.album;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final ArtistDetail getArtist() {
        return this.artist;
    }

    public final SubsonicBody copy(String status, String version, SubsonicError error, ArtistsContainer artists, IndexesContainer indexes, AlbumList albumList2, AlbumList albumList, Album album, ArtistDetail artist, SongList topSongs, SongList randomSongs, PlaylistsContainer playlists, PlaylistDetail playlist, SearchResult3 searchResult3) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(version, "version");
        return new SubsonicBody(status, version, error, artists, indexes, albumList2, albumList, album, artist, topSongs, randomSongs, playlists, playlist, searchResult3);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubsonicBody)) {
            return false;
        }
        SubsonicBody subsonicBody = (SubsonicBody) other;
        return Intrinsics.areEqual(this.status, subsonicBody.status) && Intrinsics.areEqual(this.version, subsonicBody.version) && Intrinsics.areEqual(this.error, subsonicBody.error) && Intrinsics.areEqual(this.artists, subsonicBody.artists) && Intrinsics.areEqual(this.indexes, subsonicBody.indexes) && Intrinsics.areEqual(this.albumList2, subsonicBody.albumList2) && Intrinsics.areEqual(this.albumList, subsonicBody.albumList) && Intrinsics.areEqual(this.album, subsonicBody.album) && Intrinsics.areEqual(this.artist, subsonicBody.artist) && Intrinsics.areEqual(this.topSongs, subsonicBody.topSongs) && Intrinsics.areEqual(this.randomSongs, subsonicBody.randomSongs) && Intrinsics.areEqual(this.playlists, subsonicBody.playlists) && Intrinsics.areEqual(this.playlist, subsonicBody.playlist) && Intrinsics.areEqual(this.searchResult3, subsonicBody.searchResult3);
    }

    public int hashCode() {
        int iHashCode = ((this.status.hashCode() * 31) + this.version.hashCode()) * 31;
        SubsonicError subsonicError = this.error;
        int iHashCode2 = (iHashCode + (subsonicError == null ? 0 : subsonicError.hashCode())) * 31;
        ArtistsContainer artistsContainer = this.artists;
        int iHashCode3 = (iHashCode2 + (artistsContainer == null ? 0 : artistsContainer.hashCode())) * 31;
        IndexesContainer indexesContainer = this.indexes;
        int iHashCode4 = (iHashCode3 + (indexesContainer == null ? 0 : indexesContainer.hashCode())) * 31;
        AlbumList albumList = this.albumList2;
        int iHashCode5 = (iHashCode4 + (albumList == null ? 0 : albumList.hashCode())) * 31;
        AlbumList albumList2 = this.albumList;
        int iHashCode6 = (iHashCode5 + (albumList2 == null ? 0 : albumList2.hashCode())) * 31;
        Album album = this.album;
        int iHashCode7 = (iHashCode6 + (album == null ? 0 : album.hashCode())) * 31;
        ArtistDetail artistDetail = this.artist;
        int iHashCode8 = (iHashCode7 + (artistDetail == null ? 0 : artistDetail.hashCode())) * 31;
        SongList songList = this.topSongs;
        int iHashCode9 = (iHashCode8 + (songList == null ? 0 : songList.hashCode())) * 31;
        SongList songList2 = this.randomSongs;
        int iHashCode10 = (iHashCode9 + (songList2 == null ? 0 : songList2.hashCode())) * 31;
        PlaylistsContainer playlistsContainer = this.playlists;
        int iHashCode11 = (iHashCode10 + (playlistsContainer == null ? 0 : playlistsContainer.hashCode())) * 31;
        PlaylistDetail playlistDetail = this.playlist;
        int iHashCode12 = (iHashCode11 + (playlistDetail == null ? 0 : playlistDetail.hashCode())) * 31;
        SearchResult3 searchResult3 = this.searchResult3;
        return iHashCode12 + (searchResult3 != null ? searchResult3.hashCode() : 0);
    }

    public String toString() {
        return "SubsonicBody(status=" + this.status + ", version=" + this.version + ", error=" + this.error + ", artists=" + this.artists + ", indexes=" + this.indexes + ", albumList2=" + this.albumList2 + ", albumList=" + this.albumList + ", album=" + this.album + ", artist=" + this.artist + ", topSongs=" + this.topSongs + ", randomSongs=" + this.randomSongs + ", playlists=" + this.playlists + ", playlist=" + this.playlist + ", searchResult3=" + this.searchResult3 + ")";
    }

    /* JADX INFO: compiled from: Models.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dubtonic/player/data/model/SubsonicBody$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/dubtonic/player/data/model/SubsonicBody;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<SubsonicBody> serializer() {
            return SubsonicBody$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SubsonicBody(int i, String str, String str2, SubsonicError subsonicError, ArtistsContainer artistsContainer, IndexesContainer indexesContainer, AlbumList albumList, AlbumList albumList2, Album album, ArtistDetail artistDetail, SongList songList, SongList songList2, PlaylistsContainer playlistsContainer, PlaylistDetail playlistDetail, SearchResult3 searchResult3, SerializationConstructorMarker serializationConstructorMarker) {
        this.status = (i & 1) == 0 ? "ok" : str;
        this.version = (i & 2) == 0 ? "" : str2;
        if ((i & 4) == 0) {
            this.error = null;
        } else {
            this.error = subsonicError;
        }
        if ((i & 8) == 0) {
            this.artists = null;
        } else {
            this.artists = artistsContainer;
        }
        if ((i & 16) == 0) {
            this.indexes = null;
        } else {
            this.indexes = indexesContainer;
        }
        if ((i & 32) == 0) {
            this.albumList2 = null;
        } else {
            this.albumList2 = albumList;
        }
        if ((i & 64) == 0) {
            this.albumList = null;
        } else {
            this.albumList = albumList2;
        }
        if ((i & 128) == 0) {
            this.album = null;
        } else {
            this.album = album;
        }
        if ((i & 256) == 0) {
            this.artist = null;
        } else {
            this.artist = artistDetail;
        }
        if ((i & 512) == 0) {
            this.topSongs = null;
        } else {
            this.topSongs = songList;
        }
        if ((i & 1024) == 0) {
            this.randomSongs = null;
        } else {
            this.randomSongs = songList2;
        }
        if ((i & 2048) == 0) {
            this.playlists = null;
        } else {
            this.playlists = playlistsContainer;
        }
        if ((i & 4096) == 0) {
            this.playlist = null;
        } else {
            this.playlist = playlistDetail;
        }
        if ((i & 8192) == 0) {
            this.searchResult3 = null;
        } else {
            this.searchResult3 = searchResult3;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_release(SubsonicBody self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.status, "ok")) {
            output.encodeStringElement(serialDesc, 0, self.status);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.version, "")) {
            output.encodeStringElement(serialDesc, 1, self.version);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.error != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, SubsonicError$$serializer.INSTANCE, self.error);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.artists != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, ArtistsContainer$$serializer.INSTANCE, self.artists);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.indexes != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, IndexesContainer$$serializer.INSTANCE, self.indexes);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.albumList2 != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, AlbumList$$serializer.INSTANCE, self.albumList2);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.albumList != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, AlbumList$$serializer.INSTANCE, self.albumList);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.album != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, Album$$serializer.INSTANCE, self.album);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.artist != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, ArtistDetail$$serializer.INSTANCE, self.artist);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.topSongs != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, SongList$$serializer.INSTANCE, self.topSongs);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.randomSongs != null) {
            output.encodeNullableSerializableElement(serialDesc, 10, SongList$$serializer.INSTANCE, self.randomSongs);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.playlists != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, PlaylistsContainer$$serializer.INSTANCE, self.playlists);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || self.playlist != null) {
            output.encodeNullableSerializableElement(serialDesc, 12, PlaylistDetail$$serializer.INSTANCE, self.playlist);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 13) && self.searchResult3 == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 13, SearchResult3$$serializer.INSTANCE, self.searchResult3);
    }

    public SubsonicBody(String status, String version, SubsonicError subsonicError, ArtistsContainer artistsContainer, IndexesContainer indexesContainer, AlbumList albumList, AlbumList albumList2, Album album, ArtistDetail artistDetail, SongList songList, SongList songList2, PlaylistsContainer playlistsContainer, PlaylistDetail playlistDetail, SearchResult3 searchResult3) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(version, "version");
        this.status = status;
        this.version = version;
        this.error = subsonicError;
        this.artists = artistsContainer;
        this.indexes = indexesContainer;
        this.albumList2 = albumList;
        this.albumList = albumList2;
        this.album = album;
        this.artist = artistDetail;
        this.topSongs = songList;
        this.randomSongs = songList2;
        this.playlists = playlistsContainer;
        this.playlist = playlistDetail;
        this.searchResult3 = searchResult3;
    }

    public /* synthetic */ SubsonicBody(String str, String str2, SubsonicError subsonicError, ArtistsContainer artistsContainer, IndexesContainer indexesContainer, AlbumList albumList, AlbumList albumList2, Album album, ArtistDetail artistDetail, SongList songList, SongList songList2, PlaylistsContainer playlistsContainer, PlaylistDetail playlistDetail, SearchResult3 searchResult3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "ok" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? null : subsonicError, (i & 8) != 0 ? null : artistsContainer, (i & 16) != 0 ? null : indexesContainer, (i & 32) != 0 ? null : albumList, (i & 64) != 0 ? null : albumList2, (i & 128) != 0 ? null : album, (i & 256) != 0 ? null : artistDetail, (i & 512) != 0 ? null : songList, (i & 1024) != 0 ? null : songList2, (i & 2048) != 0 ? null : playlistsContainer, (i & 4096) != 0 ? null : playlistDetail, (i & 8192) == 0 ? searchResult3 : null);
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getVersion() {
        return this.version;
    }

    public final SubsonicError getError() {
        return this.error;
    }

    public final ArtistsContainer getArtists() {
        return this.artists;
    }

    public final IndexesContainer getIndexes() {
        return this.indexes;
    }

    public final AlbumList getAlbumList2() {
        return this.albumList2;
    }

    public final AlbumList getAlbumList() {
        return this.albumList;
    }

    public final Album getAlbum() {
        return this.album;
    }

    public final ArtistDetail getArtist() {
        return this.artist;
    }

    public final SongList getTopSongs() {
        return this.topSongs;
    }

    public final SongList getRandomSongs() {
        return this.randomSongs;
    }

    public final PlaylistsContainer getPlaylists() {
        return this.playlists;
    }

    public final PlaylistDetail getPlaylist() {
        return this.playlist;
    }

    public final SearchResult3 getSearchResult3() {
        return this.searchResult3;
    }
}
