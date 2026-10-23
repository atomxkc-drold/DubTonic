package com.dubtonic.player;

import android.app.Application;
import android.os.Process;
import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.di.AppContainer;
import java.io.File;
import java.util.Date;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DubTonicApp.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0002R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/dubtonic/player/DubTonicApp;", "Landroid/app/Application;", "<init>", "()V", "value", "Lcom/dubtonic/player/di/AppContainer;", TtmlNode.RUBY_CONTAINER, "getContainer", "()Lcom/dubtonic/player/di/AppContainer;", "onCreate", "", "installCrashLogger", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DubTonicApp extends Application {
    public static final int $stable = 8;
    private AppContainer container;

    public final AppContainer getContainer() {
        AppContainer appContainer = this.container;
        if (appContainer != null) {
            return appContainer;
        }
        Intrinsics.throwUninitializedPropertyAccessException(TtmlNode.RUBY_CONTAINER);
        return null;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        installCrashLogger();
        this.container = new AppContainer(this);
    }

    private final void installCrashLogger() {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.dubtonic.player.DubTonicApp$$ExternalSyntheticLambda0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                DubTonicApp.installCrashLogger$lambda$1(this.f$0, defaultUncaughtExceptionHandler, thread, th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void installCrashLogger$lambda$1(DubTonicApp dubTonicApp, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Thread thread, Throwable th) {
        try {
            File externalFilesDir = dubTonicApp.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                FilesKt.writeText$default(new File(externalFilesDir, "crash.log"), new Date() + "\n" + Log.getStackTraceString(th) + "\n", null, 2, null);
            }
        } catch (Throwable unused) {
        }
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
        }
    }
}
