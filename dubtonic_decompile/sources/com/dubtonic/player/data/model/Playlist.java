package com.dubtonic.player.data.model;

import androidx.autofill.HintConstants;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
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
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 @2\u00020\u0001:\u0002?@Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010By\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u000f\u0010\u0015J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\tHÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\t\u0010/\u001a\u00020\u000bHÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003Jm\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÇ\u0001J\u0013\u00103\u001a\u00020\t2\b\u00104\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u00105\u001a\u00020\u0012H×\u0001J\t\u00106\u001a\u00020\u0003H×\u0001J%\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b>R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001c\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010!R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0011\u0010&\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b'\u0010\u0017¨\u0006A"}, d2 = {"Lcom/dubtonic/player/data/model/Playlist;", "", TtmlNode.ATTR_ID, "", HintConstants.AUTOFILL_HINT_NAME, "owner", "comment", "coverArt", "public", "", "songCount", "", "duration", "created", "changed", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJJLjava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJJLjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getName", "getOwner", "getComment", "getCoverArt", "getPublic", "()Z", "getSongCount$annotations", "()V", "getSongCount", "()J", "getDuration$annotations", "getDuration", "getCreated", "getChanged", "artworkKey", "getArtworkKey", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class Playlist {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String changed;
    private final String comment;
    private final String coverArt;
    private final String created;
    private final long duration;
    private final String id;
    private final String name;
    private final String owner;
    private final boolean public;
    private final long songCount;

    public Playlist() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, false, 0L, 0L, (String) null, (String) null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, (DefaultConstructorMarker) null);
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getDuration$annotations() {
    }

    @Serializable(with = FlexibleLongSerializer.class)
    public static /* synthetic */ void getSongCount$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getChanged() {
        return this.changed;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOwner() {
        return this.owner;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getComment() {
        return this.comment;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCoverArt() {
        return this.coverArt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getPublic() {
        return this.public;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getSongCount() {
        return this.songCount;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getCreated() {
        return this.created;
    }

    public final Playlist copy(String id, String name, String owner, String comment, String coverArt, boolean z, long songCount, long duration, String created, String changed) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(comment, "comment");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(created, "created");
        Intrinsics.checkNotNullParameter(changed, "changed");
        return new Playlist(id, name, owner, comment, coverArt, z, songCount, duration, created, changed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Playlist)) {
            return false;
        }
        Playlist playlist = (Playlist) other;
        return Intrinsics.areEqual(this.id, playlist.id) && Intrinsics.areEqual(this.name, playlist.name) && Intrinsics.areEqual(this.owner, playlist.owner) && Intrinsics.areEqual(this.comment, playlist.comment) && Intrinsics.areEqual(this.coverArt, playlist.coverArt) && this.public == playlist.public && this.songCount == playlist.songCount && this.duration == playlist.duration && Intrinsics.areEqual(this.created, playlist.created) && Intrinsics.areEqual(this.changed, playlist.changed);
    }

    public int hashCode() {
        return (((((((((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.owner.hashCode()) * 31) + this.comment.hashCode()) * 31) + this.coverArt.hashCode()) * 31) + Boolean.hashCode(this.public)) * 31) + Long.hashCode(this.songCount)) * 31) + Long.hashCode(this.duration)) * 31) + this.created.hashCode()) * 31) + this.changed.hashCode();
    }

    public String toString() {
        return "Playlist(id=" + this.id + ", name=" + this.name + ", owner=" + this.owner + ", comment=" + this.comment + ", coverArt=" + this.coverArt + ", public=" + this.public + ", songCount=" + this.songCount + ", duration=" + this.duration + ", created=" + this.created + ", changed=" + this.changed + ")";
    }

    /* JADX INFO: compiled from: Models.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dubtonic/player/data/model/Playlist$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/dubtonic/player/data/model/Playlist;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<Playlist> serializer() {
            return Playlist$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Playlist(int i, String str, String str2, String str3, String str4, String str5, boolean z, long j, long j2, String str6, String str7, SerializationConstructorMarker serializationConstructorMarker) {
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
            this.owner = "";
        } else {
            this.owner = str3;
        }
        if ((i & 8) == 0) {
            this.comment = "";
        } else {
            this.comment = str4;
        }
        if ((i & 16) == 0) {
            this.coverArt = "";
        } else {
            this.coverArt = str5;
        }
        if ((i & 32) == 0) {
            this.public = false;
        } else {
            this.public = z;
        }
        if ((i & 64) == 0) {
            this.songCount = 0L;
        } else {
            this.songCount = j;
        }
        if ((i & 128) == 0) {
            this.duration = 0L;
        } else {
            this.duration = j2;
        }
        if ((i & 256) == 0) {
            this.created = "";
        } else {
            this.created = str6;
        }
        if ((i & 512) == 0) {
            this.changed = "";
        } else {
            this.changed = str7;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_release(Playlist self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.id, "")) {
            output.encodeStringElement(serialDesc, 0, self.id);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.name, "")) {
            output.encodeStringElement(serialDesc, 1, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.owner, "")) {
            output.encodeStringElement(serialDesc, 2, self.owner);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.comment, "")) {
            output.encodeStringElement(serialDesc, 3, self.comment);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.coverArt, "")) {
            output.encodeStringElement(serialDesc, 4, self.coverArt);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.public) {
            output.encodeBooleanElement(serialDesc, 5, self.public);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.songCount != 0) {
            output.encodeSerializableElement(serialDesc, 6, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.songCount));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.duration != 0) {
            output.encodeSerializableElement(serialDesc, 7, FlexibleLongSerializer.INSTANCE, Long.valueOf(self.duration));
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || !Intrinsics.areEqual(self.created, "")) {
            output.encodeStringElement(serialDesc, 8, self.created);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 9) && Intrinsics.areEqual(self.changed, "")) {
            return;
        }
        output.encodeStringElement(serialDesc, 9, self.changed);
    }

    public Playlist(String id, String name, String owner, String comment, String coverArt, boolean z, long j, long j2, String created, String changed) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(comment, "comment");
        Intrinsics.checkNotNullParameter(coverArt, "coverArt");
        Intrinsics.checkNotNullParameter(created, "created");
        Intrinsics.checkNotNullParameter(changed, "changed");
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.comment = comment;
        this.coverArt = coverArt;
        this.public = z;
        this.songCount = j;
        this.duration = j2;
        this.created = created;
        this.changed = changed;
    }

    public /* synthetic */ Playlist(String str, String str2, String str3, String str4, String str5, boolean z, long j, long j2, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? false : z, (i & 64) != 0 ? 0L : j, (i & 128) == 0 ? j2 : 0L, (i & 256) != 0 ? "" : str6, (i & 512) == 0 ? str7 : "");
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOwner() {
        return this.owner;
    }

    public final String getComment() {
        return this.comment;
    }

    public final String getCoverArt() {
        return this.coverArt;
    }

    public final boolean getPublic() {
        return this.public;
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

    public final String getChanged() {
        return this.changed;
    }

    public final String getArtworkKey() {
        String str = this.coverArt;
        if (StringsKt.isBlank(str)) {
            str = this.id;
        }
        return str;
    }
}
