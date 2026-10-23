package com.dubtonic.player.ui.screens;

import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.Album;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class HomeScreenKt$HomeScreen$1$1$7 extends FunctionReferenceImpl implements Function1<Album, Unit> {
    final /* synthetic */ boolean $demo;
    final /* synthetic */ LibraryRepository $library;
    final /* synthetic */ Function0<Unit> $onOpenPlayer;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ ServerConfig $server;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeScreenKt$HomeScreen$1$1$7(CoroutineScope coroutineScope, ServerConfig serverConfig, boolean z, Function0<Unit> function0, LibraryRepository libraryRepository) {
        super(1, Intrinsics.Kotlin.class, "playAlbum", "HomeScreen$playAlbum(Lkotlinx/coroutines/CoroutineScope;Lcom/dubtonic/player/data/api/ServerConfig;ZLkotlin/jvm/functions/Function0;Lcom/dubtonic/player/data/LibraryRepository;Lcom/dubtonic/player/data/model/Album;)V", 0);
        this.$scope = coroutineScope;
        this.$server = serverConfig;
        this.$demo = z;
        this.$onOpenPlayer = function0;
        this.$library = libraryRepository;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Album album) {
        invoke2(album);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Album p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        HomeScreenKt.HomeScreen$playAlbum(this.$scope, this.$server, this.$demo, this.$onOpenPlayer, this.$library, p0);
    }
}
