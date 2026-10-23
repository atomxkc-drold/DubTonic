package com.dubtonic.player.data.model;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fBQ\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u000b\u0010\u0011J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JA\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÇ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010%\u001a\u00020\u000eH×\u0001J\t\u0010&\u001a\u00020\u0003H×\u0001J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u00061"}, d2 = {"Lcom/dubtonic/player/data/model/ArtistDetail;", "", TtmlNode.ATTR_ID, "", HintConstants.AUTOFILL_HINT_NAME, "coverArt", "albumCount", "", "album", "", "Lcom/dubtonic/player/data/model/Album;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getName", "getCoverArt", "getAlbumCount$annotations", "()V", "getAlbumCount", "()J", "getAlbum", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class ArtistDetail {
    private final List<Album> album;
    private final long albumCount;
    private final String coverArt;
    private final String id;
    private final String name;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, new ArrayListSerializer(Album$$serializer.INSTANCE)};

    public ArtistDetail() {
        this((String) null, (String) null, (String) null, 0L, (List) null, 31, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArtistDetail copy$default(ArtistDetail artistDetail, String str, String str2, String str3, long j, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = artistDetail.id;
        }
        if ((i & 2) != 0) {
            str2 = artistDetail.name;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = artistDetail.coverArt;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            j = artistDetail.albumCount;
        }
        long j2 = j;
        if ((i & 16) != 0) {
            list = artistDetail.album;
        }
        return artistDetail.copy(str, str4, str5, j2, list);
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
    public final long getAlbumCount() {
        return this.albumCount;
    }

    public final List<Album> component5() {
        return this.album;
    }

    public final ArtistDetail copy(String id, String name, String coverArt, long albumCount, List<Album> album) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(album, "album");
        return new ArtistDetail(id, name, coverArt, albumCount, album);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArtistDetail)) {
            return false;
        }
        ArtistDetail artistDetail = (ArtistDetail) other;
        return Intrinsics.areEqual(this.id, artistDetail.id) && Intrinsics.areEqual(this.name, artistDetail.name) && Intrinsics.areEqual(this.coverArt, artistDetail.coverArt) && this.albumCount == artistDetail.albumCount && Intrinsics.areEqual(this.album, artistDetail.album);
    }

    public int hashCode() {
        return (((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.coverArt.hashCode()) * 31) + Long.hashCode(this.albumCount)) * 31) + this.album.hashCode();
    }

    public String toString() {
        return "ArtistDetail(id=" + this.id + ", name=" + this.name + ", coverArt=" + this.coverArt + ", albumCount=" + this.albumCount + ", album=" + this.album + ")";
    }

    /* JADX INFO: compiled from: Models.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dubtonic/player/data/model/ArtistDetail$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/dubtonic/player/data/model/ArtistDetail;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<ArtistDetail> serializer() {
            return ArtistDetail$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ ArtistDetail(int i, String str, String str2, String str3, long j, List list, SerializationConstructorMarker serializationConstructorMarker) {
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
            this.albumCount = 0L;
        } else {
            this.albumCount = j;
        }
        if ((i & 16) == 0) {
            this.album = CollectionsKt.emptyList();
        } else {
            this.album = list;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_release(ArtistDetail self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.id, "")) {
            output.encodeStringElement(serialDesc, 0, self.id);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.name, "")) {
            output.encodeStringElement(serialDesc, 1, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.coverArt, "")) {
            output.encodeStringElement(serialDesc, 2, self.coverArt);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.albumCount != 0) {
            output.encodeSerializableElement(serialDesc, 3, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.albumCount));
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 4) && Intrinsics.areEqual(self.album, CollectionsKt.emptyList())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 4, kSerializerArr[4], self.album);
    }

    public ArtistDetail(String id, String name, String coverArt, long j, List<Album> album) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(album, "album");
        this.id = id;
        this.name = name;
        this.coverArt = coverArt;
        this.albumCount = j;
        this.album = album;
    }

    public /* synthetic */ ArtistDetail(String str, String str2, String str3, long j, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) == 0 ? str3 : "", (i & 8) != 0 ? 0L : j, (i & 16) != 0 ? CollectionsKt.emptyList() : list);
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

    public final long getAlbumCount() {
        return this.albumCount;
    }

    public final List<Album> getAlbum() {
        return this.album;
    }
}
