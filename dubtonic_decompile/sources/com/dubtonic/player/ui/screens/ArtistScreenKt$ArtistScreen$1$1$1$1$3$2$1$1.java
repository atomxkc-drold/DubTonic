package com.dubtonic.player.ui.screens;

import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.ArtistDetail;
import com.dubtonic.player.di.AppContainer;
import com.dubtonic.player.player.PlayerBus;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ArtistScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$1$1$1$1$3$2$1$1", f = "ArtistScreen.kt", i = {}, l = {117}, m = "invokeSuspend", n = {}, s = {})
final class ArtistScreenKt$ArtistScreen$1$1$1$1$3$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArtistDetail $artist;
    final /* synthetic */ AppContainer $container;
    final /* synthetic */ boolean $demo;
    final /* synthetic */ Function0<Unit> $onOpenPlayer;
    final /* synthetic */ ServerConfig $server;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ArtistScreenKt$ArtistScreen$1$1$1$1$3$2$1$1(ServerConfig serverConfig, boolean z, Function0<Unit> function0, AppContainer appContainer, ArtistDetail artistDetail, Continuation<? super ArtistScreenKt$ArtistScreen$1$1$1$1$3$2$1$1> continuation) {
        super(2, continuation);
        this.$server = serverConfig;
        this.$demo = z;
        this.$onOpenPlayer = function0;
        this.$container = appContainer;
        this.$artist = artistDetail;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtistScreenKt$ArtistScreen$1$1$1$1$3$2$1$1(this.$server, this.$demo, this.$onOpenPlayer, this.$container, this.$artist, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ArtistScreenKt$ArtistScreen$1$1$1$1$3$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x003e  */
    /* JADX WARN: Code duplicated, block: B:19:0x004b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List listEmptyList;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ServerConfig serverConfig = this.$server;
            if (serverConfig != null) {
                AppContainer appContainer = this.$container;
                ArtistDetail artistDetail = this.$artist;
                LibraryRepository library = appContainer.getLibrary();
                String name = artistDetail.getName();
                this.label = 1;
                obj = LibraryRepository.topSongs$default(library, serverConfig, name, 0, this, 4, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                listEmptyList = null;
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            if (!listEmptyList.isEmpty()) {
                PlayerBus.INSTANCE.playQueue(CollectionsKt.shuffled(listEmptyList), 0, this.$demo);
                this.$onOpenPlayer.invoke();
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        listEmptyList = (List) obj;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        if (!listEmptyList.isEmpty()) {
            PlayerBus.INSTANCE.playQueue(CollectionsKt.shuffled(listEmptyList), 0, this.$demo);
            this.$onOpenPlayer.invoke();
        }
        return Unit.INSTANCE;
    }
}
