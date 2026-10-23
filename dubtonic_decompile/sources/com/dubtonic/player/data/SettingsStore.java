package com.dubtonic.player.data;

import android.content.Context;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.core.PreferencesKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.data.api.ServerConfig;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;

/* JADX INFO: compiled from: SettingsStore.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u001d\b\u0007\u0018\u00002\u00020\u0001:\u0001FB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010,J\u000e\u0010-\u001a\u00020*H\u0086@¢\u0006\u0002\u0010.J\u0016\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u00101J\u001e\u00102\u001a\u00020*2\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\"H\u0086@¢\u0006\u0002\u00105J\u001e\u00106\u001a\u00020*2\u0006\u00103\u001a\u00020\u00152\u0006\u00107\u001a\u00020\"H\u0086@¢\u0006\u0002\u00105J\u0016\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u00101J\u0016\u0010:\u001a\u00020*2\u0006\u0010;\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u00101J\u000e\u0010<\u001a\u00020*H\u0086@¢\u0006\u0002\u0010.J\u0016\u0010=\u001a\u00020*2\u0006\u0010>\u001a\u00020\"H\u0086@¢\u0006\u0002\u0010?J\u0016\u0010@\u001a\u00020*2\u0006\u00107\u001a\u00020\"H\u0086@¢\u0006\u0002\u0010?J\u0016\u0010A\u001a\u00020*2\u0006\u0010B\u001a\u00020'H\u0086@¢\u0006\u0002\u0010CJ\u0016\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00150\u001d2\u0006\u0010E\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00180\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00180\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u001d0\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u001d0\u0011¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0011¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0013R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u0011¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0013R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u0011¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0013¨\u0006G"}, d2 = {"Lcom/dubtonic/player/data/SettingsStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "json", "Lkotlinx/serialization/json/Json;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "_serverCache", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dubtonic/player/data/api/ServerConfig;", "serverNow", "getServerNow", "()Lcom/dubtonic/player/data/api/ServerConfig;", "serverFlow", "Lkotlinx/coroutines/flow/Flow;", "getServerFlow", "()Lkotlinx/coroutines/flow/Flow;", "themeFlow", "", "getThemeFlow", "starredFlow", "", "getStarredFlow", "hiddenFlow", "getHiddenFlow", "recentFlow", "", "getRecentFlow", "searchHistoryFlow", "getSearchHistoryFlow", "libHintSeenFlow", "", "getLibHintSeenFlow", "statusHiddenFlow", "getStatusHiddenFlow", "playbackSpeedFlow", "", "getPlaybackSpeedFlow", "saveServer", "", "server", "(Lcom/dubtonic/player/data/api/ServerConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearServer", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setTheme", "theme", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setStarred", TtmlNode.ATTR_ID, "starred", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setHidden", "hidden", "addRecent", "songId", "addSearch", "query", "clearSearches", "setLibHintSeen", "seen", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setStatusHidden", "setPlaybackSpeed", "speed", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decodeList", "raw", "Keys", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SettingsStore {
    public static final int $stable = 8;
    private final MutableStateFlow<ServerConfig> _serverCache;
    private final Context context;
    private final Flow<Set<String>> hiddenFlow;
    private final Json json;
    private final Flow<Boolean> libHintSeenFlow;
    private final Flow<Float> playbackSpeedFlow;
    private final Flow<List<String>> recentFlow;
    private final CoroutineScope scope;
    private final Flow<List<String>> searchHistoryFlow;
    private final Flow<ServerConfig> serverFlow;
    private final Flow<Set<String>> starredFlow;
    private final Flow<Boolean> statusHiddenFlow;
    private final Flow<String> themeFlow;

    public SettingsStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.json = JsonKt.Json$default(null, new Function1() { // from class: com.dubtonic.player.data.SettingsStore$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsStore.json$lambda$0((JsonBuilder) obj);
            }
        }, 1, null);
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));
        this.scope = CoroutineScope;
        this._serverCache = StateFlowKt.MutableStateFlow(null);
        final Flow data = SettingsStoreKt.getDataStore(context).getData();
        this.serverFlow = new Flow<ServerConfig>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$1

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ SettingsStore this$0;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$1$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$1$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, SettingsStore settingsStore) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = settingsStore;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        String str = (String) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getSERVER());
                        ServerConfig serverConfig = null;
                        if (str != null) {
                            try {
                                serverConfig = (ServerConfig) this.this$0.json.decodeFromString(ServerConfig.INSTANCE.serializer(), str);
                            } catch (Exception unused) {
                            }
                        }
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(serverConfig, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super ServerConfig> flowCollector, Continuation continuation) {
                Object objCollect = data.collect(new AnonymousClass2(flowCollector, this), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow data2 = SettingsStoreKt.getDataStore(context).getData();
        this.themeFlow = new Flow<String>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$2

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$2$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$2$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$2$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        String str = (String) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getTHEME());
                        if (str == null) {
                            str = "dark";
                        }
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(str, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super String> flowCollector, Continuation continuation) {
                Object objCollect = data2.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow data3 = SettingsStoreKt.getDataStore(context).getData();
        this.starredFlow = (Flow) new Flow<Set<? extends String>>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$3

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$3$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$3$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$3$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        Set setEmptySet = (Set) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getSTARRED());
                        if (setEmptySet == null) {
                            setEmptySet = SetsKt.emptySet();
                        }
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(setEmptySet, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Set<? extends String>> flowCollector, Continuation continuation) {
                Object objCollect = data3.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow data4 = SettingsStoreKt.getDataStore(context).getData();
        this.hiddenFlow = (Flow) new Flow<Set<? extends String>>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$4

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$4$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$4$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$4$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        Set setEmptySet = (Set) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getHIDDEN());
                        if (setEmptySet == null) {
                            setEmptySet = SetsKt.emptySet();
                        }
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(setEmptySet, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Set<? extends String>> flowCollector, Continuation continuation) {
                Object objCollect = data4.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow data5 = SettingsStoreKt.getDataStore(context).getData();
        this.recentFlow = (Flow) new Flow<List<? extends String>>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$5

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$5$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ SettingsStore this$0;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$5$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$5$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, SettingsStore settingsStore) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = settingsStore;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    List listEmptyList;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        String str = (String) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getRECENT());
                        if (str == null || (listEmptyList = this.this$0.decodeList(str)) == null) {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(listEmptyList, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<? extends String>> flowCollector, Continuation continuation) {
                Object objCollect = data5.collect(new AnonymousClass2(flowCollector, this), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow data6 = SettingsStoreKt.getDataStore(context).getData();
        this.searchHistoryFlow = (Flow) new Flow<List<? extends String>>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$6

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$6$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ SettingsStore this$0;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$6$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$6$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, SettingsStore settingsStore) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = settingsStore;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    List listEmptyList;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        String str = (String) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getSEARCHES());
                        if (str == null || (listEmptyList = this.this$0.decodeList(str)) == null) {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(listEmptyList, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<? extends String>> flowCollector, Continuation continuation) {
                Object objCollect = data6.collect(new AnonymousClass2(flowCollector, this), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow data7 = SettingsStoreKt.getDataStore(context).getData();
        this.libHintSeenFlow = new Flow<Boolean>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$7

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$7$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$7$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$7$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        Boolean bool = (Boolean) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getLIB_HINT_SEEN());
                        Boolean boolBoxBoolean = Boxing.boxBoolean(bool != null ? bool.booleanValue() : false);
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(boolBoxBoolean, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Boolean> flowCollector, Continuation continuation) {
                Object objCollect = data7.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow data8 = SettingsStoreKt.getDataStore(context).getData();
        this.statusHiddenFlow = new Flow<Boolean>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$8

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$8$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$8$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$8$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        Boolean bool = (Boolean) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getSTATUS_HIDDEN());
                        Boolean boolBoxBoolean = Boxing.boxBoolean(bool != null ? bool.booleanValue() : false);
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(boolBoxBoolean, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Boolean> flowCollector, Continuation continuation) {
                Object objCollect = data8.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        final Flow data9 = SettingsStoreKt.getDataStore(context).getData();
        this.playbackSpeedFlow = new Flow<Float>() { // from class: com.dubtonic.player.data.SettingsStore$special$$inlined$map$9

            /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$9$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$special$$inlined$map$9$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$special$$inlined$map$9$2", f = "SettingsStore.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    Float floatOrNull;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        String str = (String) ((Preferences) obj).get(SettingsStore.Keys.INSTANCE.getPLAYBACK_SPEED());
                        Float fBoxFloat = Boxing.boxFloat((str == null || (floatOrNull = StringsKt.toFloatOrNull(str)) == null) ? 1.0f : floatOrNull.floatValue());
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(fBoxFloat, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Float> flowCollector, Continuation continuation) {
                Object objCollect = data9.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        return Unit.INSTANCE;
    }

    public final ServerConfig getServerNow() {
        return this._serverCache.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/dubtonic/player/data/SettingsStore$Keys;", "", "<init>", "()V", "SERVER", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getSERVER", "()Landroidx/datastore/preferences/core/Preferences$Key;", "THEME", "getTHEME", "STARRED", "", "getSTARRED", "HIDDEN", "getHIDDEN", "RECENT", "getRECENT", "SEARCHES", "getSEARCHES", "LIB_HINT_SEEN", "", "getLIB_HINT_SEEN", "STATUS_HIDDEN", "getSTATUS_HIDDEN", "PLAYBACK_SPEED", "getPLAYBACK_SPEED", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class Keys {
        public static final Keys INSTANCE = new Keys();
        private static final Preferences.Key<String> SERVER = PreferencesKeys.stringKey("server_json");
        private static final Preferences.Key<String> THEME = PreferencesKeys.stringKey("theme");
        private static final Preferences.Key<Set<String>> STARRED = PreferencesKeys.stringSetKey("starred_ids");
        private static final Preferences.Key<Set<String>> HIDDEN = PreferencesKeys.stringSetKey("hidden_song_ids");
        private static final Preferences.Key<String> RECENT = PreferencesKeys.stringKey("recent_json");
        private static final Preferences.Key<String> SEARCHES = PreferencesKeys.stringKey("searches_json");
        private static final Preferences.Key<Boolean> LIB_HINT_SEEN = PreferencesKeys.booleanKey("lib_hint_seen");
        private static final Preferences.Key<Boolean> STATUS_HIDDEN = PreferencesKeys.booleanKey("status_hidden");
        private static final Preferences.Key<String> PLAYBACK_SPEED = PreferencesKeys.stringKey("playback_speed");

        private Keys() {
        }

        public final Preferences.Key<String> getSERVER() {
            return SERVER;
        }

        public final Preferences.Key<String> getTHEME() {
            return THEME;
        }

        public final Preferences.Key<Set<String>> getSTARRED() {
            return STARRED;
        }

        public final Preferences.Key<Set<String>> getHIDDEN() {
            return HIDDEN;
        }

        public final Preferences.Key<String> getRECENT() {
            return RECENT;
        }

        public final Preferences.Key<String> getSEARCHES() {
            return SEARCHES;
        }

        public final Preferences.Key<Boolean> getLIB_HINT_SEEN() {
            return LIB_HINT_SEEN;
        }

        public final Preferences.Key<Boolean> getSTATUS_HIDDEN() {
            return STATUS_HIDDEN;
        }

        public final Preferences.Key<String> getPLAYBACK_SPEED() {
            return PLAYBACK_SPEED;
        }
    }

    public final Flow<ServerConfig> getServerFlow() {
        return this.serverFlow;
    }

    public final Flow<String> getThemeFlow() {
        return this.themeFlow;
    }

    public final Flow<Set<String>> getStarredFlow() {
        return this.starredFlow;
    }

    public final Flow<Set<String>> getHiddenFlow() {
        return this.hiddenFlow;
    }

    public final Flow<List<String>> getRecentFlow() {
        return this.recentFlow;
    }

    public final Flow<List<String>> getSearchHistoryFlow() {
        return this.searchHistoryFlow;
    }

    public final Flow<Boolean> getLibHintSeenFlow() {
        return this.libHintSeenFlow;
    }

    public final Flow<Boolean> getStatusHiddenFlow() {
        return this.statusHiddenFlow;
    }

    public final Flow<Float> getPlaybackSpeedFlow() {
        return this.playbackSpeedFlow;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$1", f = "SettingsStore.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsStore.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowM8746catch = FlowKt.m8746catch(SettingsStore.this.getServerFlow(), new C01281(null));
                final SettingsStore settingsStore = SettingsStore.this;
                this.label = 1;
                if (flowM8746catch.collect(new FlowCollector() { // from class: com.dubtonic.player.data.SettingsStore.1.2
                    public final Object emit(ServerConfig serverConfig, Continuation<? super Unit> continuation) {
                        settingsStore._serverCache.setValue(serverConfig);
                        return Unit.INSTANCE;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((ServerConfig) obj2, (Continuation<? super Unit>) continuation);
                    }
                }, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SettingsStore.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/dubtonic/player/data/api/ServerConfig;", "it", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$1$1", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01281 extends SuspendLambda implements Function3<FlowCollector<? super ServerConfig>, Throwable, Continuation<? super Unit>, Object> {
            int label;

            C01281(Continuation<? super C01281> continuation) {
                super(3, continuation);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(FlowCollector<? super ServerConfig> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                return new C01281(continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$saveServer$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "prefs", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$saveServer$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06372 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ ServerConfig $server;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06372(ServerConfig serverConfig, Continuation<? super C06372> continuation) {
            super(2, continuation);
            this.$server = serverConfig;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06372 c06372 = SettingsStore.this.new C06372(this.$server, continuation);
            c06372.L$0 = obj;
            return c06372;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06372) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((MutablePreferences) this.L$0).set(Keys.INSTANCE.getSERVER(), SettingsStore.this.json.encodeToString(ServerConfig.INSTANCE.serializer(), this.$server));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object saveServer(ServerConfig serverConfig, Continuation<? super Unit> continuation) {
        this._serverCache.setValue(serverConfig);
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06372(serverConfig, null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$clearServer$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$clearServer$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06362 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C06362(Continuation<? super C06362> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06362 c06362 = new C06362(continuation);
            c06362.L$0 = obj;
            return c06362;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06362) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((MutablePreferences) this.L$0).remove(Keys.INSTANCE.getSERVER());
            return Unit.INSTANCE;
        }
    }

    public final Object clearServer(Continuation<? super Unit> continuation) {
        this._serverCache.setValue(null);
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06362(null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$setTheme$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$setTheme$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06432 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $theme;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06432(String str, Continuation<? super C06432> continuation) {
            super(2, continuation);
            this.$theme = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06432 c06432 = new C06432(this.$theme, continuation);
            c06432.L$0 = obj;
            return c06432;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06432) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((MutablePreferences) this.L$0).set(Keys.INSTANCE.getTHEME(), this.$theme);
            return Unit.INSTANCE;
        }
    }

    public final Object setTheme(String str, Continuation<? super Unit> continuation) {
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06432(str, null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$setStarred$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "prefs", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$setStarred$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06412 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $id;
        final /* synthetic */ boolean $starred;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06412(boolean z, String str, Continuation<? super C06412> continuation) {
            super(2, continuation);
            this.$starred = z;
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06412 c06412 = new C06412(this.$starred, this.$id, continuation);
            c06412.L$0 = obj;
            return c06412;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06412) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MutablePreferences mutablePreferences = (MutablePreferences) this.L$0;
            Set setEmptySet = (Set) mutablePreferences.get(Keys.INSTANCE.getSTARRED());
            if (setEmptySet == null) {
                setEmptySet = SetsKt.emptySet();
            }
            Set mutableSet = CollectionsKt.toMutableSet(setEmptySet);
            if (this.$starred) {
                mutableSet.add(this.$id);
            } else {
                mutableSet.remove(this.$id);
            }
            mutablePreferences.set(Keys.INSTANCE.getSTARRED(), mutableSet);
            return Unit.INSTANCE;
        }
    }

    public final Object setStarred(String str, boolean z, Continuation<? super Unit> continuation) {
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06412(z, str, null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$setHidden$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "prefs", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$setHidden$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06382 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $hidden;
        final /* synthetic */ String $id;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06382(boolean z, String str, Continuation<? super C06382> continuation) {
            super(2, continuation);
            this.$hidden = z;
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06382 c06382 = new C06382(this.$hidden, this.$id, continuation);
            c06382.L$0 = obj;
            return c06382;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06382) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MutablePreferences mutablePreferences = (MutablePreferences) this.L$0;
            Set setEmptySet = (Set) mutablePreferences.get(Keys.INSTANCE.getHIDDEN());
            if (setEmptySet == null) {
                setEmptySet = SetsKt.emptySet();
            }
            Set mutableSet = CollectionsKt.toMutableSet(setEmptySet);
            if (this.$hidden) {
                mutableSet.add(this.$id);
            } else {
                mutableSet.remove(this.$id);
            }
            mutablePreferences.set(Keys.INSTANCE.getHIDDEN(), mutableSet);
            return Unit.INSTANCE;
        }
    }

    public final Object setHidden(String str, boolean z, Continuation<? super Unit> continuation) {
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06382(z, str, null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$addRecent$2, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "prefs", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$addRecent$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $songId;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SettingsStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, SettingsStore settingsStore, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$songId = str;
            this.this$0 = settingsStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$songId, this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List listEmptyList;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MutablePreferences mutablePreferences = (MutablePreferences) this.L$0;
            String str = (String) mutablePreferences.get(Keys.INSTANCE.getRECENT());
            if (str == null || (listEmptyList = this.this$0.decodeList(str)) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List mutableList = CollectionsKt.toMutableList((Collection) listEmptyList);
            mutableList.remove(this.$songId);
            mutableList.add(0, this.$songId);
            if (mutableList.size() > 50) {
                mutableList.subList(50, mutableList.size()).clear();
            }
            mutablePreferences.set(Keys.INSTANCE.getRECENT(), this.this$0.json.encodeToString(BuiltinSerializersKt.ListSerializer(StringSerializer.INSTANCE), mutableList));
            return Unit.INSTANCE;
        }
    }

    public final Object addRecent(String str, Continuation<? super Unit> continuation) {
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new AnonymousClass2(str, this, null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    public final Object addSearch(String str, Continuation<? super Unit> continuation) {
        Object objEdit;
        String string = StringsKt.trim((CharSequence) str).toString();
        return (string.length() != 0 && (objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06342(string, this, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$addSearch$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "prefs", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$addSearch$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06342 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $q;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SettingsStore this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06342(String str, SettingsStore settingsStore, Continuation<? super C06342> continuation) {
            super(2, continuation);
            this.$q = str;
            this.this$0 = settingsStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06342 c06342 = new C06342(this.$q, this.this$0, continuation);
            c06342.L$0 = obj;
            return c06342;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06342) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List listEmptyList;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MutablePreferences mutablePreferences = (MutablePreferences) this.L$0;
            String str = (String) mutablePreferences.get(Keys.INSTANCE.getSEARCHES());
            if (str == null || (listEmptyList = this.this$0.decodeList(str)) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List mutableList = CollectionsKt.toMutableList((Collection) listEmptyList);
            mutableList.remove(this.$q);
            mutableList.add(0, this.$q);
            if (mutableList.size() > 6) {
                mutableList.subList(6, mutableList.size()).clear();
            }
            mutablePreferences.set(Keys.INSTANCE.getSEARCHES(), this.this$0.json.encodeToString(BuiltinSerializersKt.ListSerializer(StringSerializer.INSTANCE), mutableList));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$clearSearches$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$clearSearches$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06352 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C06352(Continuation<? super C06352> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06352 c06352 = new C06352(continuation);
            c06352.L$0 = obj;
            return c06352;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06352) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((MutablePreferences) this.L$0).remove(Keys.INSTANCE.getSEARCHES());
            return Unit.INSTANCE;
        }
    }

    public final Object clearSearches(Continuation<? super Unit> continuation) {
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06352(null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$setLibHintSeen$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$setLibHintSeen$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06392 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $seen;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06392(boolean z, Continuation<? super C06392> continuation) {
            super(2, continuation);
            this.$seen = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06392 c06392 = new C06392(this.$seen, continuation);
            c06392.L$0 = obj;
            return c06392;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06392) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((MutablePreferences) this.L$0).set(Keys.INSTANCE.getLIB_HINT_SEEN(), Boxing.boxBoolean(this.$seen));
            return Unit.INSTANCE;
        }
    }

    public final Object setLibHintSeen(boolean z, Continuation<? super Unit> continuation) {
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06392(z, null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$setStatusHidden$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$setStatusHidden$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06422 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $hidden;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06422(boolean z, Continuation<? super C06422> continuation) {
            super(2, continuation);
            this.$hidden = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06422 c06422 = new C06422(this.$hidden, continuation);
            c06422.L$0 = obj;
            return c06422;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06422) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((MutablePreferences) this.L$0).set(Keys.INSTANCE.getSTATUS_HIDDEN(), Boxing.boxBoolean(this.$hidden));
            return Unit.INSTANCE;
        }
    }

    public final Object setStatusHidden(boolean z, Continuation<? super Unit> continuation) {
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06422(z, null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.SettingsStore$setPlaybackSpeed$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsStore.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.SettingsStore$setPlaybackSpeed$2", f = "SettingsStore.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06402 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $speed;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06402(float f, Continuation<? super C06402> continuation) {
            super(2, continuation);
            this.$speed = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06402 c06402 = new C06402(this.$speed, continuation);
            c06402.L$0 = obj;
            return c06402;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((C06402) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((MutablePreferences) this.L$0).set(Keys.INSTANCE.getPLAYBACK_SPEED(), String.valueOf(this.$speed));
            return Unit.INSTANCE;
        }
    }

    public final Object setPlaybackSpeed(float f, Continuation<? super Unit> continuation) {
        Object objEdit = PreferencesKt.edit(SettingsStoreKt.getDataStore(this.context), new C06402(f, null), continuation);
        return objEdit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEdit : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> decodeList(String raw) {
        try {
            return (List) this.json.decodeFromString(BuiltinSerializersKt.ListSerializer(StringSerializer.INSTANCE), raw);
        } catch (Exception unused) {
            return CollectionsKt.emptyList();
        }
    }
}
