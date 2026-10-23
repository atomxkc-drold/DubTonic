package com.dubtonic.player.ui.screens;

import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.di.AppContainer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class AlbumScreenKt$AlbumScreen$1$1$4$1$1 extends FunctionReferenceImpl implements Function1<String, String> {
    final /* synthetic */ AppContainer $container;
    final /* synthetic */ ServerConfig $server;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AlbumScreenKt$AlbumScreen$1$1$4$1$1(ServerConfig serverConfig, AppContainer appContainer) {
        super(1, Intrinsics.Kotlin.class, "artUrl", "AlbumScreen$artUrl(Lcom/dubtonic/player/data/api/ServerConfig;Lcom/dubtonic/player/di/AppContainer;Ljava/lang/String;)Ljava/lang/String;", 0);
        this.$server = serverConfig;
        this.$container = appContainer;
    }

    @Override // kotlin.jvm.functions.Function1
    public final String invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return AlbumScreenKt.AlbumScreen$artUrl(this.$server, this.$container, p0);
    }
}
