package com.dubtonic.player.ui.screens;

import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.Album;
import com.dubtonic.player.player.PlayerBus;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.ui.screens.HomeScreenKt$HomeScreen$playAlbum$1", f = "HomeScreen.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
final class HomeScreenKt$HomeScreen$playAlbum$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Album $album;
    final /* synthetic */ boolean $demo;
    final /* synthetic */ LibraryRepository $library;
    final /* synthetic */ Function0<Unit> $onOpenPlayer;
    final /* synthetic */ ServerConfig $server;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeScreenKt$HomeScreen$playAlbum$1(ServerConfig serverConfig, boolean z, Function0<Unit> function0, LibraryRepository libraryRepository, Album album, Continuation<? super HomeScreenKt$HomeScreen$playAlbum$1> continuation) {
        super(2, continuation);
        this.$server = serverConfig;
        this.$demo = z;
        this.$onOpenPlayer = function0;
        this.$library = libraryRepository;
        this.$album = album;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HomeScreenKt$HomeScreen$playAlbum$1(this.$server, this.$demo, this.$onOpenPlayer, this.$library, this.$album, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HomeScreenKt$HomeScreen$playAlbum$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Album album;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ServerConfig serverConfig = this.$server;
            if (serverConfig != null) {
                LibraryRepository libraryRepository = this.$library;
                String id = this.$album.getId();
                this.label = 1;
                obj = libraryRepository.album(serverConfig, id, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                album = null;
            }
            if (album != null && !album.getSong().isEmpty()) {
                PlayerBus.INSTANCE.playQueue(album.getSong(), 0, this.$demo);
                this.$onOpenPlayer.invoke();
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        album = (Album) obj;
        if (album != null) {
            PlayerBus.INSTANCE.playQueue(album.getSong(), 0, this.$demo);
            this.$onOpenPlayer.invoke();
        }
        return Unit.INSTANCE;
    }
}
