package com.dubtonic.player.player;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioFx.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J=\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\b\u001a\u00020\u0003HÇ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010\u0018\u001a\u00020\u0006H×\u0001J\t\u0010\u0019\u001a\u00020\u001aH×\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/dubtonic/player/player/EqState;", "", "available", "", "bands", "", "", "bandFreqs", "bassBoost", "<init>", "(ZLjava/util/List;Ljava/util/List;Z)V", "getAvailable", "()Z", "getBands", "()Ljava/util/List;", "getBandFreqs", "getBassBoost", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class EqState {
    public static final int $stable = 8;
    private final boolean available;
    private final List<Integer> bandFreqs;
    private final List<Integer> bands;
    private final boolean bassBoost;

    public EqState() {
        this(false, null, null, false, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EqState copy$default(EqState eqState, boolean z, List list, List list2, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = eqState.available;
        }
        if ((i & 2) != 0) {
            list = eqState.bands;
        }
        if ((i & 4) != 0) {
            list2 = eqState.bandFreqs;
        }
        if ((i & 8) != 0) {
            z2 = eqState.bassBoost;
        }
        return eqState.copy(z, list, list2, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getAvailable() {
        return this.available;
    }

    public final List<Integer> component2() {
        return this.bands;
    }

    public final List<Integer> component3() {
        return this.bandFreqs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getBassBoost() {
        return this.bassBoost;
    }

    public final EqState copy(boolean available, List<Integer> bands, List<Integer> bandFreqs, boolean bassBoost) {
        Intrinsics.checkNotNullParameter(bands, "bands");
        Intrinsics.checkNotNullParameter(bandFreqs, "bandFreqs");
        return new EqState(available, bands, bandFreqs, bassBoost);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EqState)) {
            return false;
        }
        EqState eqState = (EqState) other;
        return this.available == eqState.available && Intrinsics.areEqual(this.bands, eqState.bands) && Intrinsics.areEqual(this.bandFreqs, eqState.bandFreqs) && this.bassBoost == eqState.bassBoost;
    }

    public int hashCode() {
        return (((((Boolean.hashCode(this.available) * 31) + this.bands.hashCode()) * 31) + this.bandFreqs.hashCode()) * 31) + Boolean.hashCode(this.bassBoost);
    }

    public String toString() {
        return "EqState(available=" + this.available + ", bands=" + this.bands + ", bandFreqs=" + this.bandFreqs + ", bassBoost=" + this.bassBoost + ")";
    }

    public EqState(boolean z, List<Integer> bands, List<Integer> bandFreqs, boolean z2) {
        Intrinsics.checkNotNullParameter(bands, "bands");
        Intrinsics.checkNotNullParameter(bandFreqs, "bandFreqs");
        this.available = z;
        this.bands = bands;
        this.bandFreqs = bandFreqs;
        this.bassBoost = z2;
    }

    public final boolean getAvailable() {
        return this.available;
    }

    public /* synthetic */ EqState(boolean z, List list, List list2, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? CollectionsKt.emptyList() : list2, (i & 8) != 0 ? false : z2);
    }

    public final List<Integer> getBands() {
        return this.bands;
    }

    public final List<Integer> getBandFreqs() {
        return this.bandFreqs;
    }

    public final boolean getBassBoost() {
        return this.bassBoost;
    }
}
