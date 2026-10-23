package com.dubtonic.player.data.model;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
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
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002,-B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nBK\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J;\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÇ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010\"\u001a\u00020\fH×\u0001J\t\u0010#\u001a\u00020\u0003H×\u0001J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\b+R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006."}, d2 = {"Lcom/dubtonic/player/data/model/Artist;", "", TtmlNode.ATTR_ID, "", HintConstants.AUTOFILL_HINT_NAME, "coverArt", "artistImageUrl", "albumCount", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getName", "getCoverArt", "getArtistImageUrl", "getAlbumCount$annotations", "()V", "getAlbumCount", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class Artist {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long albumCount;
    private final String artistImageUrl;
    private final String coverArt;
    private final String id;
    private final String name;

    public Artist() {
        this((String) null, (String) null, (String) null, (String) null, 0L, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Artist copy$default(Artist artist, String str, String str2, String str3, String str4, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = artist.id;
        }
        if ((i & 2) != 0) {
            str2 = artist.name;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = artist.coverArt;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = artist.artistImageUrl;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            j = artist.albumCount;
        }
        return artist.copy(str, str5, str6, str7, j);
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getAlbumCount$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCoverArt() {
        return this.coverArt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getArtistImageUrl() {
        return this.artistImageUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getAlbumCount() {
        return this.albumCount;
    }

    public final Artist copy(String id, String name, String coverArt, String artistImageUrl, long albumCount) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(artistImageUrl, "artistImageUrl");
        return new Artist(id, name, coverArt, artistImageUrl, albumCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Artist)) {
            return false;
        }
        Artist artist = (Artist) other;
        return Intrinsics.areEqual(this.id, artist.id) && Intrinsics.areEqual(this.name, artist.name) && Intrinsics.areEqual(this.coverArt, artist.coverArt) && Intrinsics.areEqual(this.artistImageUrl, artist.artistImageUrl) && this.albumCount == artist.albumCount;
    }

    public int hashCode() {
        return (((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.coverArt.hashCode()) * 31) + this.artistImageUrl.hashCode()) * 31) + Long.hashCode(this.albumCount);
    }

    public String toString() {
        return "Artist(id=" + this.id + ", name=" + this.name + ", coverArt=" + this.coverArt + ", artistImageUrl=" + this.artistImageUrl + ", albumCount=" + this.albumCount + ")";
    }

    /* JADX INFO: compiled from: Models.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dubtonic/player/data/model/Artist$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/dubtonic/player/data/model/Artist;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<Artist> serializer() {
            return Artist$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Artist(int i, String str, String str2, String str3, String str4, long j, SerializationConstructorMarker serializationConstructorMarker) {
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
            this.coverArt = "";
        } else {
            this.coverArt = str3;
        }
        if ((i & 8) == 0) {
            this.artistImageUrl = "";
        } else {
            this.artistImageUrl = str4;
        }
        if ((i & 16) == 0) {
            this.albumCount = 0L;
        } else {
            this.albumCount = j;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_release(Artist self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.id, "")) {
            output.encodeStringElement(serialDesc, 0, self.id);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.name, "")) {
            output.encodeStringElement(serialDesc, 1, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.coverArt, "")) {
            output.encodeStringElement(serialDesc, 2, self.coverArt);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.artistImageUrl, "")) {
            output.encodeStringElement(serialDesc, 3, self.artistImageUrl);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 4) && self.albumCount == 0) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 4, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.albumCount));
    }

    public Artist(String id, String name, String coverArt, String artistImageUrl, long j) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(artistImageUrl, "artistImageUrl");
        this.id = id;
        this.name = name;
        this.coverArt = coverArt;
        this.artistImageUrl = artistImageUrl;
        this.albumCount = j;
    }

    public /* synthetic */ Artist(String str, String str2, String str3, String str4, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) == 0 ? str4 : "", (i & 16) != 0 ? 0L : j);
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getCoverArt() {
        return this.coverArt;
    }

    public final String getArtistImageUrl() {
        return this.artistImageUrl;
    }

    public final long getAlbumCount() {
        return this.albumCount;
    }
}
