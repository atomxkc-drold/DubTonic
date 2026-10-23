package com.dubtonic.player.ui.screens;

import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.api.ServerConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class LibraryScreenKt$LibraryScreen$1$1$3$1$1 extends FunctionReferenceImpl implements Function1<String, String> {
    final /* synthetic */ LibraryRepository $library;
    final /* synthetic */ ServerConfig $server;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryScreenKt$LibraryScreen$1$1$3$1$1(ServerConfig serverConfig, LibraryRepository libraryRepository) {
        super(1, Intrinsics.Kotlin.class, "artUrl", "LibraryScreen$artUrl(Lcom/dubtonic/player/data/api/ServerConfig;Lcom/dubtonic/player/data/LibraryRepository;Ljava/lang/String;)Ljava/lang/String;", 0);
        this.$server = serverConfig;
        this.$library = libraryRepository;
    }

    @Override // kotlin.jvm.functions.Function1
    public final String invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return LibraryScreenKt.LibraryScreen$artUrl(this.$server, this.$library, p0);
    }
}
