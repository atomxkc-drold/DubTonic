package com.dubtonic.player.ui.screens;

import com.dubtonic.player.data.model.Song;
import com.dubtonic.player.di.AppContainer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ArtistScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.ui.screens.ArtistScreenKt$TopSongsLoader$2$3$1$1", f = "ArtistScreen.kt", i = {}, l = {227}, m = "invokeSuspend", n = {}, s = {})
final class ArtistScreenKt$TopSongsLoader$2$3$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AppContainer $container;
    final /* synthetic */ Song $song;
    final /* synthetic */ Set<String> $starred;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ArtistScreenKt$TopSongsLoader$2$3$1$1(AppContainer appContainer, Song song, Set<String> set, Continuation<? super ArtistScreenKt$TopSongsLoader$2$3$1$1> continuation) {
        super(2, continuation);
        this.$container = appContainer;
        this.$song = song;
        this.$starred = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtistScreenKt$TopSongsLoader$2$3$1$1(this.$container, this.$song, this.$starred, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ArtistScreenKt$TopSongsLoader$2$3$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.$container.getSettings().setStarred(this.$song.getId(), !this.$starred.contains(this.$song.getId()), this) == coroutine_suspended) {
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
