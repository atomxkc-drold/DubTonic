package com.dubtonic.player.ui.screens;

import androidx.compose.runtime.MutableState;
import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.Song;
import com.dubtonic.player.di.AppContainer;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ArtistScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.ui.screens.ArtistScreenKt$TopSongsLoader$1$1", f = "ArtistScreen.kt", i = {}, l = {210}, m = "invokeSuspend", n = {}, s = {})
final class ArtistScreenKt$TopSongsLoader$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $artistName;
    final /* synthetic */ AppContainer $container;
    final /* synthetic */ ServerConfig $server;
    final /* synthetic */ MutableState<List<Song>> $songs$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ArtistScreenKt$TopSongsLoader$1$1(ServerConfig serverConfig, AppContainer appContainer, String str, MutableState<List<Song>> mutableState, Continuation<? super ArtistScreenKt$TopSongsLoader$1$1> continuation) {
        super(2, continuation);
        this.$server = serverConfig;
        this.$container = appContainer;
        this.$artistName = str;
        this.$songs$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtistScreenKt$TopSongsLoader$1$1(this.$server, this.$container, this.$artistName, this.$songs$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ArtistScreenKt$TopSongsLoader$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0046  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableState<List<Song>> mutableState;
        List listEmptyList;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableState<List<Song>> mutableState2 = this.$songs$delegate;
            ServerConfig serverConfig = this.$server;
            if (serverConfig != null) {
                AppContainer appContainer = this.$container;
                String str = this.$artistName;
                LibraryRepository library = appContainer.getLibrary();
                this.L$0 = mutableState2;
                this.label = 1;
                Object obj2 = LibraryRepository.topSongs$default(library, serverConfig, str, 0, this, 4, null);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableState = mutableState2;
                obj = obj2;
            } else {
                mutableState = mutableState2;
                listEmptyList = null;
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            mutableState.setValue(listEmptyList);
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableState = (MutableState) this.L$0;
        ResultKt.throwOnFailure(obj);
        listEmptyList = (List) obj;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        mutableState.setValue(listEmptyList);
        return Unit.INSTANCE;
    }
}
