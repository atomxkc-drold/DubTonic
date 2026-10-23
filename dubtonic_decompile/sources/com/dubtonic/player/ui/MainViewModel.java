package com.dubtonic.player.ui;

import androidx.core.view.MotionEventCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.di.AppContainer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: MainViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J*\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u001a\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\"0%J*\u0010&\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u001a\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\"0%J\u0006\u0010'\u001a\u00020\"J\u0006\u0010(\u001a\u00020\"J\u000e\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u0010J\u000e\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u0013J\u000e\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000eR\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u000e¨\u00060"}, d2 = {"Lcom/dubtonic/player/ui/MainViewModel;", "Landroidx/lifecycle/ViewModel;", TtmlNode.RUBY_CONTAINER, "Lcom/dubtonic/player/di/AppContainer;", "<init>", "(Lcom/dubtonic/player/di/AppContainer;)V", "library", "Lcom/dubtonic/player/data/LibraryRepository;", "getLibrary", "()Lcom/dubtonic/player/data/LibraryRepository;", "server", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/dubtonic/player/data/api/ServerConfig;", "getServer", "()Lkotlinx/coroutines/flow/StateFlow;", "themeMode", "", "getThemeMode", "statusHidden", "", "getStatusHidden", "_demo", "Lkotlinx/coroutines/flow/MutableStateFlow;", "demo", "getDemo", "_connecting", "connecting", "getConnecting", "_connectError", "connectError", "getConnectError", "configured", "getConfigured", "connect", "", "config", "onResult", "Lkotlin/Function2;", "testServer", "enterDemo", "disconnect", "setTheme", "mode", "setStatusHidden", "hidden", "updateBitrate", "bitrate", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MainViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<String> _connectError;
    private final MutableStateFlow<Boolean> _connecting;
    private final MutableStateFlow<Boolean> _demo;
    private final StateFlow<Boolean> configured;
    private final StateFlow<String> connectError;
    private final StateFlow<Boolean> connecting;
    private final AppContainer container;
    private final StateFlow<Boolean> demo;
    private final LibraryRepository library;
    private final StateFlow<ServerConfig> server;
    private final StateFlow<Boolean> statusHidden;
    private final StateFlow<String> themeMode;

    public MainViewModel(AppContainer container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.container = container;
        this.library = container.getLibrary();
        MainViewModel mainViewModel = this;
        StateFlow<ServerConfig> stateFlowStateIn = FlowKt.stateIn(container.getSettings().getServerFlow(), ViewModelKt.getViewModelScope(mainViewModel), SharingStarted.INSTANCE.getEagerly(), container.getSettings().getServerNow());
        this.server = stateFlowStateIn;
        this.themeMode = FlowKt.stateIn(container.getSettings().getThemeFlow(), ViewModelKt.getViewModelScope(mainViewModel), SharingStarted.INSTANCE.getEagerly(), "system");
        this.statusHidden = FlowKt.stateIn(container.getSettings().getStatusHiddenFlow(), ViewModelKt.getViewModelScope(mainViewModel), SharingStarted.INSTANCE.getEagerly(), false);
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(false);
        this._demo = MutableStateFlow;
        this.demo = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<Boolean> MutableStateFlow2 = StateFlowKt.MutableStateFlow(false);
        this._connecting = MutableStateFlow2;
        this.connecting = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow<String> MutableStateFlow3 = StateFlowKt.MutableStateFlow(null);
        this._connectError = MutableStateFlow3;
        this.connectError = FlowKt.asStateFlow(MutableStateFlow3);
        this.configured = FlowKt.stateIn(FlowKt.combine(stateFlowStateIn, MutableStateFlow, new MainViewModel$configured$1(null)), ViewModelKt.getViewModelScope(mainViewModel), SharingStarted.INSTANCE.getEagerly(), false);
    }

    public final LibraryRepository getLibrary() {
        return this.library;
    }

    public final StateFlow<ServerConfig> getServer() {
        return this.server;
    }

    public final StateFlow<String> getThemeMode() {
        return this.themeMode;
    }

    public final StateFlow<Boolean> getStatusHidden() {
        return this.statusHidden;
    }

    public final StateFlow<Boolean> getDemo() {
        return this.demo;
    }

    public final StateFlow<Boolean> getConnecting() {
        return this.connecting;
    }

    public final StateFlow<String> getConnectError() {
        return this.connectError;
    }

    public final StateFlow<Boolean> getConfigured() {
        return this.configured;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.MainViewModel$connect$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.ui.MainViewModel$connect$1", f = "MainViewModel.kt", i = {}, l = {MotionEventCompat.AXIS_GENERIC_16, 48}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ServerConfig $config;
        final /* synthetic */ Function2<Boolean, String, Unit> $onResult;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(ServerConfig serverConfig, Function2<? super Boolean, ? super String, Unit> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$config = serverConfig;
            this.$onResult = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new AnonymousClass1(this.$config, this.$onResult, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i != 0) {
                    if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                    } else {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    MainViewModel.this._demo.setValue(Boxing.boxBoolean(false));
                    MainViewModel.this.container.getLibrary().reset();
                    MainViewModel.this.container.getLibrary().ensureLoaded(this.$config);
                    this.$onResult.invoke(Boxing.boxBoolean(true), null);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                MainViewModel.this._connecting.setValue(Boxing.boxBoolean(true));
                MainViewModel.this._connectError.setValue(null);
                this.label = 1;
                if (MainViewModel.this.container.getLibrary().ping(this.$config, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.label = 2;
                if (MainViewModel.this.container.getSettings().saveServer(this.$config, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                MainViewModel.this._demo.setValue(Boxing.boxBoolean(false));
                MainViewModel.this.container.getLibrary().reset();
                MainViewModel.this.container.getLibrary().ensureLoaded(this.$config);
                this.$onResult.invoke(Boxing.boxBoolean(true), null);
            } catch (Exception e) {
                String message = e.getMessage();
                if (message == null) {
                    message = "Could not reach the server";
                }
                MainViewModel.this._connectError.setValue(message);
                this.$onResult.invoke(Boxing.boxBoolean(false), message);
            } finally {
                MainViewModel.this._connecting.setValue(Boxing.boxBoolean(false));
            }
            return Unit.INSTANCE;
        }
    }

    public final void connect(ServerConfig config, Function2<? super Boolean, ? super String, Unit> onResult) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(config, onResult, null), 3, null);
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.MainViewModel$testServer$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.ui.MainViewModel$testServer$1", f = "MainViewModel.kt", i = {}, l = {MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "invokeSuspend", n = {}, s = {})
    static final class C06511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ServerConfig $config;
        final /* synthetic */ Function2<Boolean, String, Unit> $onResult;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C06511(ServerConfig serverConfig, Function2<? super Boolean, ? super String, Unit> function2, Continuation<? super C06511> continuation) {
            super(2, continuation);
            this.$config = serverConfig;
            this.$onResult = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C06511(this.$config, this.$onResult, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MainViewModel.this._connecting.setValue(Boxing.boxBoolean(true));
                    this.label = 1;
                    if (MainViewModel.this.container.getLibrary().ping(this.$config, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                this.$onResult.invoke(Boxing.boxBoolean(true), null);
            } catch (Exception e) {
                Function2<Boolean, String, Unit> function2 = this.$onResult;
                Boolean boolBoxBoolean = Boxing.boxBoolean(false);
                String message = e.getMessage();
                if (message == null) {
                    message = "Could not reach the server";
                }
                function2.invoke(boolBoxBoolean, message);
            } finally {
                MainViewModel.this._connecting.setValue(Boxing.boxBoolean(false));
            }
            return Unit.INSTANCE;
        }
    }

    public final void testServer(ServerConfig config, Function2<? super Boolean, ? super String, Unit> onResult) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C06511(config, onResult, null), 3, null);
    }

    public final void enterDemo() {
        this._demo.setValue(true);
        this.container.getLibrary().enterDemo();
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.MainViewModel$disconnect$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.ui.MainViewModel$disconnect$1", f = "MainViewModel.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
    static final class C06481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06481(Continuation<? super C06481> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C06481(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MainViewModel.this.container.getSettings().clearServer(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            MainViewModel.this._demo.setValue(Boxing.boxBoolean(false));
            MainViewModel.this.container.getLibrary().reset();
            return Unit.INSTANCE;
        }
    }

    public final void disconnect() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C06481(null), 3, null);
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.MainViewModel$setTheme$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.ui.MainViewModel$setTheme$1", f = "MainViewModel.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    static final class C06501 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mode;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06501(String str, Continuation<? super C06501> continuation) {
            super(2, continuation);
            this.$mode = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C06501(this.$mode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06501) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MainViewModel.this.container.getSettings().setTheme(this.$mode, this) == coroutine_suspended) {
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

    public final void setTheme(String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C06501(mode, null), 3, null);
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.MainViewModel$setStatusHidden$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.ui.MainViewModel$setStatusHidden$1", f = "MainViewModel.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, s = {})
    static final class C06491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $hidden;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06491(boolean z, Continuation<? super C06491> continuation) {
            super(2, continuation);
            this.$hidden = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C06491(this.$hidden, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MainViewModel.this.container.getSettings().setStatusHidden(this.$hidden, this) == coroutine_suspended) {
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

    public final void setStatusHidden(boolean hidden) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C06491(hidden, null), 3, null);
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.MainViewModel$updateBitrate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.ui.MainViewModel$updateBitrate$1", f = "MainViewModel.kt", i = {}, l = {100}, m = "invokeSuspend", n = {}, s = {})
    static final class C06521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $bitrate;
        final /* synthetic */ ServerConfig $current;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06521(ServerConfig serverConfig, int i, Continuation<? super C06521> continuation) {
            super(2, continuation);
            this.$current = serverConfig;
            this.$bitrate = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new C06521(this.$current, this.$bitrate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MainViewModel.this.container.getSettings().saveServer(ServerConfig.copy$default(this.$current, null, null, null, null, null, this.$bitrate, 31, null), this) == coroutine_suspended) {
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

    public final void updateBitrate(int bitrate) {
        ServerConfig value = this.server.getValue();
        if (value == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C06521(value, bitrate, null), 3, null);
    }
}
