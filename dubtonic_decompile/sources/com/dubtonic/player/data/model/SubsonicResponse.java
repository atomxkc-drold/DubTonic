package com.dubtonic.player.data.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÇ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010\u0014\u001a\u00020\u0007H×\u0001J\t\u0010\u0015\u001a\u00020\u0016H×\u0001J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/dubtonic/player/data/model/SubsonicResponse;", "", "subsonicResponse", "Lcom/dubtonic/player/data/model/SubsonicBody;", "<init>", "(Lcom/dubtonic/player/data/model/SubsonicBody;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/dubtonic/player/data/model/SubsonicBody;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSubsonicResponse$annotations", "()V", "getSubsonicResponse", "()Lcom/dubtonic/player/data/model/SubsonicBody;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class SubsonicResponse {
    private final SubsonicBody subsonicResponse;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static /* synthetic */ SubsonicResponse copy$default(SubsonicResponse subsonicResponse, SubsonicBody subsonicBody, int i, Object obj) {
        if ((i & 1) != 0) {
            subsonicBody = subsonicResponse.subsonicResponse;
        }
        return subsonicResponse.copy(subsonicBody);
    }

    @SerialName("subsonic-response")
    public static /* synthetic */ void getSubsonicResponse$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SubsonicBody getSubsonicResponse() {
        return this.subsonicResponse;
    }

    public final SubsonicResponse copy(SubsonicBody subsonicResponse) {
        Intrinsics.checkNotNullParameter(subsonicResponse, "subsonicResponse");
        return new SubsonicResponse(subsonicResponse);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SubsonicResponse) && Intrinsics.areEqual(this.subsonicResponse, ((SubsonicResponse) other).subsonicResponse);
    }

    public int hashCode() {
        return this.subsonicResponse.hashCode();
    }

    public String toString() {
        return "SubsonicResponse(subsonicResponse=" + this.subsonicResponse + ")";
    }

    /* JADX INFO: compiled from: Models.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dubtonic/player/data/model/SubsonicResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/dubtonic/player/data/model/SubsonicResponse;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<SubsonicResponse> serializer() {
            return SubsonicResponse$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SubsonicResponse(int i, SubsonicBody subsonicBody, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, SubsonicResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.subsonicResponse = subsonicBody;
    }

    public SubsonicResponse(SubsonicBody subsonicResponse) {
        Intrinsics.checkNotNullParameter(subsonicResponse, "subsonicResponse");
        this.subsonicResponse = subsonicResponse;
    }

    public final SubsonicBody getSubsonicResponse() {
        return this.subsonicResponse;
    }
}
