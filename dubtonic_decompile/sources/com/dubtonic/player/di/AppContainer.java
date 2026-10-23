package com.dubtonic.player.di;

import android.content.Context;
import coil.ImageLoader;
import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.SettingsStore;
import com.dubtonic.player.data.api.SubsonicClient;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: AppContainer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/dubtonic/player/di/AppContainer;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "okHttp", "Lokhttp3/OkHttpClient;", "getOkHttp", "()Lokhttp3/OkHttpClient;", "client", "Lcom/dubtonic/player/data/api/SubsonicClient;", "getClient", "()Lcom/dubtonic/player/data/api/SubsonicClient;", "settings", "Lcom/dubtonic/player/data/SettingsStore;", "getSettings", "()Lcom/dubtonic/player/data/SettingsStore;", "library", "Lcom/dubtonic/player/data/LibraryRepository;", "getLibrary", "()Lcom/dubtonic/player/data/LibraryRepository;", "imageLoader", "Lcoil/ImageLoader;", "getImageLoader", "()Lcoil/ImageLoader;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AppContainer {
    public static final int $stable = 8;
    private final SubsonicClient client;
    private final Context context;
    private final ImageLoader imageLoader;
    private final LibraryRepository library;
    private final OkHttpClient okHttp;
    private final SettingsStore settings;

    public AppContainer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).callTimeout(30L, TimeUnit.SECONDS).followRedirects(true).build();
        this.okHttp = okHttpClientBuild;
        SubsonicClient subsonicClient = new SubsonicClient(okHttpClientBuild);
        this.client = subsonicClient;
        SettingsStore settingsStore = new SettingsStore(context);
        this.settings = settingsStore;
        this.library = new LibraryRepository(subsonicClient, settingsStore);
        this.imageLoader = new ImageLoader.Builder(context).okHttpClient(okHttpClientBuild).crossfade(true).build();
    }

    public final Context getContext() {
        return this.context;
    }

    public final OkHttpClient getOkHttp() {
        return this.okHttp;
    }

    public final SubsonicClient getClient() {
        return this.client;
    }

    public final SettingsStore getSettings() {
        return this.settings;
    }

    public final LibraryRepository getLibrary() {
        return this.library;
    }

    public final ImageLoader getImageLoader() {
        return this.imageLoader;
    }
}
