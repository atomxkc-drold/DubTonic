package com.dubtonic.player.player;

import android.content.Context;
import android.content.Intent;
import com.dubtonic.player.MediaPlaybackService;
import com.dubtonic.player.data.model.Song;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: PlayerState.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0003¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0013\u001a\u00020\u00122\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eJ\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fJ\u0015\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0018J\u0016\u0010\u0019\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\u000eH\u0002J\u0006\u0010\u001b\u001a\u00020\u0012J\u0006\u0010\u001c\u001a\u00020\u0012J\u0006\u0010\u001d\u001a\u00020\u0012J\u0006\u0010\u001e\u001a\u00020\u0012J\u0006\u0010\u001f\u001a\u00020\u0012J\u000e\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"J$\u0010#\u001a\u00020\u00122\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*J\u0014\u0010+\u001a\u00020\u00122\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%J\u000e\u0010,\u001a\u00020\u00122\u0006\u0010'\u001a\u00020(J\u000e\u0010-\u001a\u00020\u00122\u0006\u0010'\u001a\u00020(J\u0016\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020(J\u0006\u00101\u001a\u00020\u0012J\u0006\u00102\u001a\u00020\u0012J\u0006\u00103\u001a\u00020\u0012J\u0006\u00104\u001a\u00020\u0012J\u0015\u00105\u001a\u00020\u00122\b\u00106\u001a\u0004\u0018\u00010(¢\u0006\u0002\u00107J\u0006\u00108\u001a\u00020\u0012J\u000e\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020;J\u0006\u0010<\u001a\u00020\u0012J\u0016\u0010=\u001a\u00020\u00122\u0006\u0010>\u001a\u00020(2\u0006\u0010?\u001a\u00020(J\u000e\u0010@\u001a\u00020\u00122\u0006\u0010A\u001a\u00020*R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000e0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/dubtonic/player/player/PlayerBus;", "", "<init>", "()V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dubtonic/player/player/PlayerUiState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "controller", "Lcom/dubtonic/player/player/PlayerController;", "contextProvider", "Lkotlin/Function0;", "Landroid/content/Context;", "pending", "Lkotlin/collections/ArrayDeque;", "", "setContextProvider", "provider", "attach", "detach", "publish", "publish$app_release", "dispatch", "cmd", "togglePlay", "play", "pause", "next", "prev", "seekTo", "positionMs", "", "playQueue", "songs", "", "Lcom/dubtonic/player/data/model/Song;", "index", "", "demo", "", "addToQueue", "removeAt", "jumpTo", "moveMediaItem", "from", "to", "clearQueue", "toggleShuffle", "cycleRepeat", "cycleSpeed", "setSleep", "minutes", "(Ljava/lang/Integer;)V", "cancelSleep", "setVolume", "volume", "", "toggleMute", "setEqBand", "band", "levelMillibels", "setBassBoost", "enabled", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PlayerBus {
    public static final int $stable;
    public static final PlayerBus INSTANCE = new PlayerBus();
    private static final MutableStateFlow<PlayerUiState> _state;
    private static Function0<? extends Context> contextProvider;
    private static PlayerController controller;
    private static final ArrayDeque<Function0<Unit>> pending;
    private static final StateFlow<PlayerUiState> state;

    private PlayerBus() {
    }

    static {
        MutableStateFlow<PlayerUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(new PlayerUiState(null, 0, false, false, 0L, 0L, 0.0f, false, 0.0f, false, null, 0L, false, null, 0, 0, 0, null, 0, null, 1048575, null));
        _state = MutableStateFlow;
        state = FlowKt.asStateFlow(MutableStateFlow);
        pending = new ArrayDeque<>();
        $stable = 8;
    }

    public final StateFlow<PlayerUiState> getState() {
        return state;
    }

    public final void setContextProvider(Function0<? extends Context> provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        contextProvider = provider;
    }

    public final void attach(PlayerController controller2) {
        Intrinsics.checkNotNullParameter(controller2, "controller");
        controller = controller2;
        controller2.restore();
        while (true) {
            ArrayDeque<Function0<Unit>> arrayDeque = pending;
            if (arrayDeque.isEmpty()) {
                return;
            } else {
                arrayDeque.removeFirst().invoke();
            }
        }
    }

    public final void detach(PlayerController controller2) {
        Intrinsics.checkNotNullParameter(controller2, "controller");
        if (controller == controller2) {
            controller = null;
        }
    }

    public final void publish$app_release(PlayerUiState state2) {
        Intrinsics.checkNotNullParameter(state2, "state");
        _state.setValue(state2);
    }

    private final void dispatch(Function0<Unit> cmd) {
        Context contextInvoke;
        if (controller != null) {
            cmd.invoke();
            return;
        }
        pending.add(cmd);
        Function0<? extends Context> function0 = contextProvider;
        if (function0 == null || (contextInvoke = function0.invoke()) == null) {
            return;
        }
        try {
            contextInvoke.startService(new Intent(contextInvoke, (Class<?>) MediaPlaybackService.class));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit togglePlay$lambda$0() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.togglePlay();
        }
        return Unit.INSTANCE;
    }

    public final void togglePlay() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.togglePlay$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit play$lambda$1() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.play();
        }
        return Unit.INSTANCE;
    }

    public final void play() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.play$lambda$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit pause$lambda$2() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.pause();
        }
        return Unit.INSTANCE;
    }

    public final void pause() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.pause$lambda$2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit next$lambda$3() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.next();
        }
        return Unit.INSTANCE;
    }

    public final void next() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.next$lambda$3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit prev$lambda$4() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.prev();
        }
        return Unit.INSTANCE;
    }

    public final void prev() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.prev$lambda$4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit seekTo$lambda$5(long j) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.seekTo(j);
        }
        return Unit.INSTANCE;
    }

    public final void seekTo(final long positionMs) {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.seekTo$lambda$5(positionMs);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit playQueue$lambda$6(List list, int i, boolean z) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.playQueue(list, i, z);
        }
        return Unit.INSTANCE;
    }

    public final void playQueue(final List<Song> songs, final int index, final boolean demo) {
        Intrinsics.checkNotNullParameter(songs, "songs");
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.playQueue$lambda$6(songs, index, demo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addToQueue$lambda$7(List list) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.addToQueue(list);
        }
        return Unit.INSTANCE;
    }

    public final void addToQueue(final List<Song> songs) {
        Intrinsics.checkNotNullParameter(songs, "songs");
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.addToQueue$lambda$7(songs);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit removeAt$lambda$8(int i) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.removeAt(i);
        }
        return Unit.INSTANCE;
    }

    public final void removeAt(final int index) {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.removeAt$lambda$8(index);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit jumpTo$lambda$9(int i) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.jumpTo(i);
        }
        return Unit.INSTANCE;
    }

    public final void jumpTo(final int index) {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.jumpTo$lambda$9(index);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit moveMediaItem$lambda$10(int i, int i2) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.moveMediaItem(i, i2);
        }
        return Unit.INSTANCE;
    }

    public final void moveMediaItem(final int from, final int to) {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.moveMediaItem$lambda$10(from, to);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearQueue$lambda$11() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.clearQueue();
        }
        return Unit.INSTANCE;
    }

    public final void clearQueue() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.clearQueue$lambda$11();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toggleShuffle$lambda$12() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.toggleShuffle();
        }
        return Unit.INSTANCE;
    }

    public final void toggleShuffle() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.toggleShuffle$lambda$12();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cycleRepeat$lambda$13() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.cycleRepeat();
        }
        return Unit.INSTANCE;
    }

    public final void cycleRepeat() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.cycleRepeat$lambda$13();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cycleSpeed$lambda$14() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.cycleSpeed();
        }
        return Unit.INSTANCE;
    }

    public final void cycleSpeed() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.cycleSpeed$lambda$14();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setSleep$lambda$15(Integer num) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.setSleep(num);
        }
        return Unit.INSTANCE;
    }

    public final void setSleep(final Integer minutes) {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.setSleep$lambda$15(minutes);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelSleep$lambda$16() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.setSleep(null);
        }
        return Unit.INSTANCE;
    }

    public final void cancelSleep() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.cancelSleep$lambda$16();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setVolume$lambda$17(float f) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.setVolume(f);
        }
        return Unit.INSTANCE;
    }

    public final void setVolume(final float volume) {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.setVolume$lambda$17(volume);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toggleMute$lambda$18() {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.toggleMute();
        }
        return Unit.INSTANCE;
    }

    public final void toggleMute() {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.toggleMute$lambda$18();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setEqBand$lambda$19(int i, int i2) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.setEqBand(i, i2);
        }
        return Unit.INSTANCE;
    }

    public final void setEqBand(final int band, final int levelMillibels) {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.setEqBand$lambda$19(band, levelMillibels);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setBassBoost$lambda$20(boolean z) {
        PlayerController playerController = controller;
        if (playerController != null) {
            playerController.setBassBoost(z);
        }
        return Unit.INSTANCE;
    }

    public final void setBassBoost(final boolean enabled) {
        dispatch(new Function0() { // from class: com.dubtonic.player.player.PlayerBus$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerBus.setBassBoost$lambda$20(enabled);
            }
        });
    }
}
