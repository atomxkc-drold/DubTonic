package com.dubtonic.player.player;

import androidx.media3.common.MediaItem;
import com.dubtonic.player.data.SettingsStore;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PlayerController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.player.PlayerController$2$onMediaItemTransition$2", f = "PlayerController.kt", i = {}, l = {125}, m = "invokeSuspend", n = {}, s = {})
final class PlayerController$2$onMediaItemTransition$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaItem $mediaItem;
    int label;
    final /* synthetic */ PlayerController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlayerController$2$onMediaItemTransition$2(PlayerController playerController, MediaItem mediaItem, Continuation<? super PlayerController$2$onMediaItemTransition$2> continuation) {
        super(2, continuation);
        this.this$0 = playerController;
        this.$mediaItem = mediaItem;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerController$2$onMediaItemTransition$2(this.this$0, this.$mediaItem, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerController$2$onMediaItemTransition$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SettingsStore settings = this.this$0.container.getSettings();
            String mediaId = this.$mediaItem.mediaId;
            Intrinsics.checkNotNullExpressionValue(mediaId, "mediaId");
            this.label = 1;
            if (settings.addRecent(mediaId, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
