package com.dubtonic.player.player;

import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: AudioFx.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0010R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/dubtonic/player/player/AudioFx;", "", "<init>", "()V", "equalizer", "Landroid/media/audiofx/Equalizer;", "bassBoost", "Landroid/media/audiofx/BassBoost;", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dubtonic/player/player/EqState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "attach", "", "sessionId", "", "setBand", "band", "levelMillibels", "setBassBoost", "enabled", "", "release", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AudioFx {
    public static final int $stable = 8;
    private final MutableStateFlow<EqState> _state;
    private BassBoost bassBoost;
    private Equalizer equalizer;
    private final StateFlow<EqState> state;

    public AudioFx() {
        MutableStateFlow<EqState> MutableStateFlow = StateFlowKt.MutableStateFlow(new EqState(false, null, null, false, 15, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final StateFlow<EqState> getState() {
        return this.state;
    }

    public final void attach(int sessionId) {
        release();
        if (sessionId <= 0) {
            return;
        }
        try {
            Equalizer equalizer = new Equalizer(0, sessionId);
            short numberOfBands = equalizer.getNumberOfBands();
            ArrayList arrayList = new ArrayList(numberOfBands);
            ArrayList arrayList2 = new ArrayList(numberOfBands);
            for (int i = 0; i < numberOfBands; i++) {
                short s = (short) i;
                arrayList.add(Integer.valueOf(equalizer.getBandLevel(s)));
                arrayList2.add(Integer.valueOf(equalizer.getCenterFreq(s) / 1000));
            }
            BassBoost bassBoost = new BassBoost(0, sessionId);
            this.equalizer = equalizer;
            this.bassBoost = bassBoost;
            this._state.setValue(new EqState(true, arrayList, arrayList2, bassBoost.getEnabled()));
        } catch (Throwable unused) {
            release();
            this._state.setValue(new EqState(false, null, null, false, 15, null));
        }
    }

    public final void setBand(int band, int levelMillibels) {
        try {
            Equalizer equalizer = this.equalizer;
            if (equalizer != null) {
                equalizer.setBandLevel((short) band, (short) levelMillibels);
            }
            List mutableList = CollectionsKt.toMutableList((Collection) this._state.getValue().getBands());
            if (band >= 0 && band < mutableList.size()) {
                mutableList.set(band, Integer.valueOf(levelMillibels));
            }
            MutableStateFlow<EqState> mutableStateFlow = this._state;
            mutableStateFlow.setValue(EqState.copy$default(mutableStateFlow.getValue(), false, mutableList, null, false, 13, null));
        } catch (Throwable unused) {
        }
    }

    public final void setBassBoost(boolean enabled) {
        try {
            BassBoost bassBoost = this.bassBoost;
            if (bassBoost != null) {
                bassBoost.setEnabled(enabled);
            }
            MutableStateFlow<EqState> mutableStateFlow = this._state;
            mutableStateFlow.setValue(EqState.copy$default(mutableStateFlow.getValue(), false, null, null, enabled, 7, null));
        } catch (Throwable unused) {
        }
    }

    public final void release() {
        try {
            Equalizer equalizer = this.equalizer;
            if (equalizer != null) {
                equalizer.release();
            }
        } catch (Throwable unused) {
        }
        try {
            BassBoost bassBoost = this.bassBoost;
            if (bassBoost != null) {
                bassBoost.release();
            }
        } catch (Throwable unused2) {
        }
        this.equalizer = null;
        this.bassBoost = null;
        this._state.setValue(new EqState(false, null, null, false, 15, null));
    }
}
