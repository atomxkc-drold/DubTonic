package androidx.datastore.core;

import androidx.autofill.HintConstants;
import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.time.Duration;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.SharingStartedKt;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: compiled from: DataStoreImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u0000 R*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002RSBn\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012?\b\u0002\u0010\u0005\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00070\u0006\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013JG\u00100\u001a\u0002H1\"\u0004\b\u0001\u001012\u0006\u00102\u001a\u0002032\u001c\u00104\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H10\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e05H\u0082@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u00106J\u001c\u00107\u001a\u00020\r2\f\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000/H\u0082@¢\u0006\u0002\u00109J\u000e\u0010:\u001a\u00020\rH\u0082@¢\u0006\u0002\u0010;J\u001c\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00000=2\u0006\u0010>\u001a\u000203H\u0082@¢\u0006\u0002\u0010?J\u000e\u0010@\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u0010;J\u001c\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000B2\u0006\u00102\u001a\u000203H\u0082@¢\u0006\u0002\u0010?J\u001c\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000=2\u0006\u0010>\u001a\u000203H\u0082@¢\u0006\u0002\u0010?JI\u0010D\u001a\u00028\u000021\u0010E\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(F\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00072\u0006\u0010G\u001a\u00020HH\u0082@¢\u0006\u0002\u0010IJA\u0010J\u001a\u00028\u000021\u0010E\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(F\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007H\u0096@¢\u0006\u0002\u0010KJ \u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00028\u00002\u0006\u0010O\u001a\u000203H\u0080@¢\u0006\u0004\bP\u0010QR\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f0\"R\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$8@X\u0080\u0084\u0002¢\u0006\f\u001a\u0004\b'\u0010(*\u0004\b%\u0010&R\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\r0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000/0.X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Landroidx/datastore/core/DataStoreImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/datastore/core/DataStore;", "storage", "Landroidx/datastore/core/Storage;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "api", "Lkotlin/coroutines/Continuation;", "", "", "corruptionHandler", "Landroidx/datastore/core/CorruptionHandler;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/datastore/core/Storage;Ljava/util/List;Landroidx/datastore/core/CorruptionHandler;Lkotlinx/coroutines/CoroutineScope;)V", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "coordinator$delegate", "Lkotlin/Lazy;", "data", "Lkotlinx/coroutines/flow/Flow;", "getData", "()Lkotlinx/coroutines/flow/Flow;", "inMemoryCache", "Landroidx/datastore/core/DataStoreInMemoryCache;", "internalDataFlow", "readAndInit", "Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "storageConnection", "Landroidx/datastore/core/StorageConnection;", "getStorageConnection$datastore_core_release$delegate", "(Landroidx/datastore/core/DataStoreImpl;)Ljava/lang/Object;", "getStorageConnection$datastore_core_release", "()Landroidx/datastore/core/StorageConnection;", "storageConnectionDelegate", "Lkotlin/Lazy;", "updateCollection", "Lkotlinx/coroutines/flow/SharedFlow;", "writeActor", "Landroidx/datastore/core/SimpleActor;", "Landroidx/datastore/core/Message$Update;", "doWithWriteFileLock", "R", "hasWriteFileLock", "", "block", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleUpdate", "update", "(Landroidx/datastore/core/Message$Update;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAndInitOrPropagateAndThrowFailure", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDataAndUpdateCache", "Landroidx/datastore/core/State;", "requireLock", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDataFromFileOrDefault", "readDataOrHandleCorruption", "Landroidx/datastore/core/Data;", "readState", "transformAndWrite", "transform", "t", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateData", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeData", "", "newData", "updateCache", "writeData$datastore_core_release", "(Ljava/lang/Object;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "InitDataStore", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DataStoreImpl<T> implements DataStore<T> {
    private static final String BUG_MESSAGE = "This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542";

    /* JADX INFO: renamed from: coordinator$delegate, reason: from kotlin metadata */
    private final Lazy coordinator;
    private final CorruptionHandler<T> corruptionHandler;
    private final Flow<T> data;
    private final DataStoreInMemoryCache<T> inMemoryCache;
    private final Flow<T> internalDataFlow;
    private final DataStoreImpl<T>.InitDataStore readAndInit;
    private final CoroutineScope scope;
    private final Storage<T> storage;
    private final Lazy<StorageConnection<T>> storageConnectionDelegate;
    private final SharedFlow<Unit> updateCollection;
    private final SimpleActor<Message.Update<T>> writeActor;

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$handleUpdate$1, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {1, 1}, l = {245, 251, 254}, m = "handleUpdate", n = {"update", "$this$handleUpdate_u24lambda_u240"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DataStoreImpl<T> dataStoreImpl, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.handleUpdate(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 1, 1}, l = {272, 274}, m = "readAndInitOrPropagateAndThrowFailure", n = {"this", "this", "preReadVersion"}, s = {"L$0", "L$0", "I$0"})
    static final class C05831 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05831(DataStoreImpl<T> dataStoreImpl, Continuation<? super C05831> continuation) {
            super(continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readAndInitOrPropagateAndThrowFailure(this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 0, 0, 1, 2}, l = {295, 304, 312}, m = "readDataAndUpdateCache", n = {"this", "currentState", "requireLock", "this", "this"}, s = {"L$0", "L$1", "Z$0", "L$0", "L$0"})
    static final class C05841 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05841(DataStoreImpl<T> dataStoreImpl, Continuation<? super C05841> continuation) {
            super(continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readDataAndUpdateCache(false, this);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5}, l = {373, 374, 376, 377, 388, 392}, m = "readDataOrHandleCorruption", n = {"this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "ex", "newData", "hasWriteFileLock", "ex", "newData", "version"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2"})
    static final class C05861 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05861(DataStoreImpl<T> dataStoreImpl, Continuation<? super C05861> continuation) {
            super(continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.readDataOrHandleCorruption(false, this);
        }
    }

    public DataStoreImpl(Storage<T> storage, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList, CorruptionHandler<T> corruptionHandler, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
        Intrinsics.checkNotNullParameter(corruptionHandler, "corruptionHandler");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.storage = storage;
        this.corruptionHandler = corruptionHandler;
        this.scope = scope;
        this.updateCollection = FlowKt.shareIn(FlowKt.flow(new DataStoreImpl$updateCollection$1(this, null)), scope, SharingStartedKt.m8759WhileSubscribed5qebJ5I(SharingStarted.INSTANCE, Duration.INSTANCE.m8643getZEROUwyO8pc(), Duration.INSTANCE.m8643getZEROUwyO8pc()), 0);
        this.internalDataFlow = FlowKt.flow(new DataStoreImpl$internalDataFlow$1(this, null));
        this.data = FlowKt.channelFlow(new DataStoreImpl$data$1(this, null));
        this.inMemoryCache = new DataStoreInMemoryCache<>();
        this.readAndInit = new InitDataStore(this, initTasksList);
        this.storageConnectionDelegate = LazyKt.lazy(new Function0<StorageConnection<T>>(this) { // from class: androidx.datastore.core.DataStoreImpl$storageConnectionDelegate$1
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final StorageConnection<T> invoke() {
                return ((DataStoreImpl) this.this$0).storage.createConnection();
            }
        });
        this.coordinator = LazyKt.lazy(new Function0<InterProcessCoordinator>(this) { // from class: androidx.datastore.core.DataStoreImpl$coordinator$2
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final InterProcessCoordinator invoke() {
                return this.this$0.getStorageConnection$datastore_core_release().getCoordinator();
            }
        });
        this.writeActor = new SimpleActor<>(scope, new Function1<Throwable, Unit>(this) { // from class: androidx.datastore.core.DataStoreImpl$writeActor$1
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                if (th != null) {
                    ((DataStoreImpl) this.this$0).inMemoryCache.tryUpdate(new Final(th));
                }
                if (((DataStoreImpl) this.this$0).storageConnectionDelegate.isInitialized()) {
                    this.this$0.getStorageConnection$datastore_core_release().close();
                }
            }
        }, new Function2<Message.Update<T>, Throwable, Unit>() { // from class: androidx.datastore.core.DataStoreImpl$writeActor$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object obj, Throwable th) {
                invoke((Message.Update) obj, th);
                return Unit.INSTANCE;
            }

            public final void invoke(Message.Update<T> msg, Throwable th) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                CompletableDeferred<T> ack = msg.getAck();
                if (th == null) {
                    th = new CancellationException("DataStore scope was cancelled before updateData could complete");
                }
                ack.completeExceptionally(th);
            }
        }, new DataStoreImpl$writeActor$3(this, null));
    }

    public /* synthetic */ DataStoreImpl(Storage storage, List list, NoOpCorruptionHandler noOpCorruptionHandler, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(storage, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? new NoOpCorruptionHandler() : noOpCorruptionHandler, (i & 8) != 0 ? CoroutineScopeKt.CoroutineScope(Actual_jvmKt.ioDispatcher().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null))) : coroutineScope);
    }

    @Override // androidx.datastore.core.DataStore
    public Flow<T> getData() {
        return this.data;
    }

    @Override // androidx.datastore.core.DataStore
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        UpdatingDataContextElement updatingDataContextElement = (UpdatingDataContextElement) continuation.getContext().get(UpdatingDataContextElement.Companion.Key.INSTANCE);
        if (updatingDataContextElement != null) {
            updatingDataContextElement.checkNotUpdating(this);
        }
        return BuildersKt.withContext(new UpdatingDataContextElement(updatingDataContextElement, this), new C05902(this, function2, null), continuation);
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$updateData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$updateData$2", f = "DataStoreImpl.kt", i = {}, l = {177}, m = "invokeSuspend", n = {}, s = {})
    static final class C05902 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function2<T, Continuation<? super T>, Object> $transform;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05902(DataStoreImpl<T> dataStoreImpl, Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super C05902> continuation) {
            super(2, continuation);
            this.this$0 = dataStoreImpl;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05902 c05902 = new C05902(this.this$0, this.$transform, continuation);
            c05902.L$0 = obj;
            return c05902;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((C05902) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                CompletableDeferred completableDeferredCompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                ((DataStoreImpl) this.this$0).writeActor.offer(new Message.Update(this.$transform, completableDeferredCompletableDeferred$default, ((DataStoreImpl) this.this$0).inMemoryCache.getCurrentState(), coroutineScope.getCoroutineContext()));
                this.label = 1;
                obj = completableDeferredCompletableDeferred$default.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    public final StorageConnection<T> getStorageConnection$datastore_core_release() {
        return this.storageConnectionDelegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InterProcessCoordinator getCoordinator() {
        return (InterProcessCoordinator) this.coordinator.getValue();
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readState$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/State;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readState$2", f = "DataStoreImpl.kt", i = {}, l = {226, 234}, m = "invokeSuspend", n = {}, s = {})
    static final class C05882 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super State<T>>, Object> {
        final /* synthetic */ boolean $requireLock;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05882(DataStoreImpl<T> dataStoreImpl, boolean z, Continuation<? super C05882> continuation) {
            super(2, continuation);
            this.this$0 = dataStoreImpl;
            this.$requireLock = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05882(this.this$0, this.$requireLock, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super State<T>> continuation) {
            return ((C05882) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (((DataStoreImpl) this.this$0).inMemoryCache.getCurrentState() instanceof Final) {
                        return ((DataStoreImpl) this.this$0).inMemoryCache.getCurrentState();
                    }
                    this.label = 1;
                    if (this.this$0.readAndInitOrPropagateAndThrowFailure(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                    } else {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return (State) obj;
                }
                this.label = 2;
                obj = this.this$0.readDataAndUpdateCache(this.$requireLock, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return (State) obj;
            } catch (Throwable th) {
                return new ReadException(th, -1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readState(boolean z, Continuation<? super State<T>> continuation) {
        return BuildersKt.withContext(this.scope.getCoroutineContext(), new C05882(this, z, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [androidx.datastore.core.DataStoreImpl] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r8v0, types: [androidx.datastore.core.DataStoreImpl, androidx.datastore.core.DataStoreImpl<T>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v0, types: [androidx.datastore.core.Message$Update, androidx.datastore.core.Message$Update<T>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16, types: [androidx.datastore.core.Message$Update] */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [kotlinx.coroutines.CompletableDeferred] */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /* JADX WARN: Type inference failed for: r9v35 */
    /* JADX WARN: Type inference failed for: r9v36 */
    /* JADX WARN: Type inference failed for: r9v37 */
    /* JADX WARN: Type inference failed for: r9v6 */
    public final Object handleUpdate(Message.Update<T> update, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objM7212constructorimpl;
        ?? r9;
        CompletableDeferred ack;
        ?? r2;
        Object objTransformAndWrite;
        ?? r10;
        CompletableDeferred completableDeferred;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(this, continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(this, continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        boolean z = true;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ack = update.getAck();
                try {
                    Result.Companion companion = Result.INSTANCE;
                    State<T> currentState = this.inMemoryCache.getCurrentState();
                    if (currentState instanceof Data) {
                        Function2 transform = update.getTransform();
                        CoroutineContext callerContext = update.getCallerContext();
                        anonymousClass1.L$0 = ack;
                        anonymousClass1.label = 1;
                        objTransformAndWrite = transformAndWrite(transform, callerContext, anonymousClass1);
                        if (objTransformAndWrite == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (!(currentState instanceof ReadException)) {
                            z = currentState instanceof UnInitialized;
                        }
                        if (z) {
                            if (currentState == update.getLastState()) {
                                anonymousClass1.L$0 = update;
                                anonymousClass1.L$1 = this;
                                anonymousClass1.L$2 = ack;
                                anonymousClass1.label = 2;
                                if (readAndInitOrPropagateAndThrowFailure(anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                r2 = this;
                                r10 = update;
                            } else {
                                Intrinsics.checkNotNull(currentState, "null cannot be cast to non-null type androidx.datastore.core.ReadException<T of androidx.datastore.core.DataStoreImpl.handleUpdate$lambda$0>");
                                throw ((ReadException) currentState).getReadException();
                            }
                        } else {
                            if (currentState instanceof Final) {
                                throw ((Final) currentState).getFinalException();
                            }
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                    CompletableDeferred completableDeferred2 = ack;
                    obj = objTransformAndWrite;
                    update = completableDeferred2;
                    objM7212constructorimpl = Result.m7212constructorimpl(obj);
                    r9 = update;
                } catch (Throwable th) {
                    th = th;
                    update = ack;
                    Result.Companion companion2 = Result.INSTANCE;
                    objM7212constructorimpl = Result.m7212constructorimpl(ResultKt.createFailure(th));
                    r9 = update;
                }
                CompletableDeferredKt.completeWith(r9, objM7212constructorimpl);
                return Unit.INSTANCE;
            }
            if (i == 1) {
                completableDeferred = (CompletableDeferred) anonymousClass1.L$0;
            } else if (i == 2) {
                CompletableDeferred completableDeferred3 = (CompletableDeferred) anonymousClass1.L$2;
                DataStoreImpl dataStoreImpl = (DataStoreImpl) anonymousClass1.L$1;
                Message.Update update2 = (Message.Update) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                ack = completableDeferred3;
                r2 = dataStoreImpl;
                r10 = (Message.Update<T>) update2;
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                completableDeferred = (Message.Update<T>) ((CompletableDeferred) anonymousClass1.L$0);
            }
            ResultKt.throwOnFailure(obj);
            update = completableDeferred;
            objM7212constructorimpl = Result.m7212constructorimpl(obj);
            r9 = update;
            CompletableDeferredKt.completeWith(r9, objM7212constructorimpl);
            return Unit.INSTANCE;
            Function2 transform2 = r10.getTransform();
            CoroutineContext callerContext2 = r10.getCallerContext();
            anonymousClass1.L$0 = ack;
            anonymousClass1.L$1 = null;
            anonymousClass1.L$2 = null;
            anonymousClass1.label = 3;
            objTransformAndWrite = r2.transformAndWrite(transform2, callerContext2, anonymousClass1);
            if (objTransformAndWrite == coroutine_suspended) {
                return coroutine_suspended;
            }
            CompletableDeferred completableDeferred4 = ack;
            obj = objTransformAndWrite;
            update = completableDeferred4;
            objM7212constructorimpl = Result.m7212constructorimpl(obj);
            r9 = update;
        } catch (Throwable th2) {
            th = th2;
        }
        CompletableDeferredKt.completeWith(r9, objM7212constructorimpl);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object readAndInitOrPropagateAndThrowFailure(Continuation<? super Unit> continuation) throws Throwable {
        C05831 c05831;
        DataStoreImpl<T> dataStoreImpl;
        int iIntValue;
        int i;
        Throwable th;
        DataStoreImpl<T> dataStoreImpl2;
        if (continuation instanceof C05831) {
            c05831 = (C05831) continuation;
            if ((c05831.label & Integer.MIN_VALUE) != 0) {
                c05831.label -= Integer.MIN_VALUE;
            } else {
                c05831 = new C05831(this, continuation);
            }
        } else {
            c05831 = new C05831(this, continuation);
        }
        Object version = c05831.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c05831.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(version);
                InterProcessCoordinator coordinator = getCoordinator();
                c05831.L$0 = this;
                c05831.label = 1;
                version = coordinator.getVersion(c05831);
                if (version == coroutine_suspended) {
                    return coroutine_suspended;
                }
                dataStoreImpl = this;
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    i = c05831.I$0;
                    dataStoreImpl2 = (DataStoreImpl) c05831.L$0;
                    try {
                        ResultKt.throwOnFailure(version);
                        return Unit.INSTANCE;
                    } catch (Throwable th2) {
                        th = th2;
                        dataStoreImpl2.inMemoryCache.tryUpdate(new ReadException(th, i));
                        throw th;
                    }
                }
                dataStoreImpl = (DataStoreImpl) c05831.L$0;
                ResultKt.throwOnFailure(version);
            }
            DataStoreImpl<T>.InitDataStore initDataStore = dataStoreImpl.readAndInit;
            c05831.L$0 = dataStoreImpl;
            c05831.I$0 = iIntValue;
            c05831.label = 2;
            if (initDataStore.runIfNeeded(c05831) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            i = iIntValue;
            th = th3;
            dataStoreImpl2 = dataStoreImpl;
            dataStoreImpl2.inMemoryCache.tryUpdate(new ReadException(th, i));
            throw th;
        }
        iIntValue = ((Number) version).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:43:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object readDataAndUpdateCache(boolean z, Continuation<? super State<T>> continuation) {
        C05841 c05841;
        State<T> state;
        Object obj;
        boolean z2;
        DataStoreImpl<T> dataStoreImpl;
        Pair pair;
        State<T> state2;
        if (continuation instanceof C05841) {
            c05841 = (C05841) continuation;
            if ((c05841.label & Integer.MIN_VALUE) != 0) {
                c05841.label -= Integer.MIN_VALUE;
            } else {
                c05841 = new C05841(this, continuation);
            }
        } else {
            c05841 = new C05841(this, continuation);
        }
        Object objTryLock = c05841.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c05841.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objTryLock);
            State<T> currentState = this.inMemoryCache.getCurrentState();
            if (currentState instanceof UnInitialized) {
                throw new IllegalStateException(BUG_MESSAGE.toString());
            }
            InterProcessCoordinator coordinator = getCoordinator();
            c05841.L$0 = this;
            c05841.L$1 = currentState;
            c05841.Z$0 = z;
            c05841.label = 1;
            Object version = coordinator.getVersion(c05841);
            if (version == coroutine_suspended) {
                return coroutine_suspended;
            }
            state = currentState;
            obj = version;
            z2 = z;
            dataStoreImpl = this;
        } else {
            if (i != 1) {
                if (i == 2) {
                    dataStoreImpl = (DataStoreImpl) c05841.L$0;
                    ResultKt.throwOnFailure(objTryLock);
                    pair = (Pair) objTryLock;
                    state2 = (State) pair.component1();
                    if (((Boolean) pair.component2()).booleanValue()) {
                        dataStoreImpl.inMemoryCache.tryUpdate(state2);
                    }
                    return state2;
                }
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                dataStoreImpl = (DataStoreImpl) c05841.L$0;
                ResultKt.throwOnFailure(objTryLock);
                pair = (Pair) objTryLock;
                state2 = (State) pair.component1();
                if (((Boolean) pair.component2()).booleanValue()) {
                    dataStoreImpl.inMemoryCache.tryUpdate(state2);
                }
                return state2;
            }
            boolean z3 = c05841.Z$0;
            State<T> state3 = (State) c05841.L$1;
            DataStoreImpl<T> dataStoreImpl2 = (DataStoreImpl) c05841.L$0;
            ResultKt.throwOnFailure(objTryLock);
            z2 = z3;
            dataStoreImpl = dataStoreImpl2;
            state = state3;
            obj = objTryLock;
        }
        int iIntValue = ((Number) obj).intValue();
        boolean z4 = state instanceof Data;
        int version2 = z4 ? state.getVersion() : -1;
        if (z4 && iIntValue == version2) {
            return state;
        }
        if (z2) {
            InterProcessCoordinator coordinator2 = dataStoreImpl.getCoordinator();
            C05853 c05853 = new C05853(dataStoreImpl, null);
            c05841.L$0 = dataStoreImpl;
            c05841.L$1 = null;
            c05841.label = 2;
            objTryLock = coordinator2.lock(c05853, c05841);
            if (objTryLock == coroutine_suspended) {
                return coroutine_suspended;
            }
            pair = (Pair) objTryLock;
            state2 = (State) pair.component1();
            if (((Boolean) pair.component2()).booleanValue()) {
                dataStoreImpl.inMemoryCache.tryUpdate(state2);
            }
            return state2;
        }
        InterProcessCoordinator coordinator3 = dataStoreImpl.getCoordinator();
        AnonymousClass4 anonymousClass4 = new AnonymousClass4(dataStoreImpl, version2, null);
        c05841.L$0 = dataStoreImpl;
        c05841.L$1 = null;
        c05841.label = 3;
        objTryLock = coordinator3.tryLock(anonymousClass4, c05841);
        if (objTryLock == coroutine_suspended) {
            return coroutine_suspended;
        }
        pair = (Pair) objTryLock;
        state2 = (State) pair.component1();
        if (((Boolean) pair.component2()).booleanValue()) {
            dataStoreImpl.inMemoryCache.tryUpdate(state2);
        }
        return state2;
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Landroidx/datastore/core/State;", ExifInterface.GPS_DIRECTION_TRUE, ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3", f = "DataStoreImpl.kt", i = {}, l = {306, StatusLine.HTTP_PERM_REDIRECT}, m = "invokeSuspend", n = {}, s = {})
    static final class C05853 extends SuspendLambda implements Function1<Continuation<? super Pair<? extends State<T>, ? extends Boolean>>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05853(DataStoreImpl<T> dataStoreImpl, Continuation<? super C05853> continuation) {
            super(1, continuation);
            this.this$0 = dataStoreImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C05853(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Pair<? extends State<T>, Boolean>> continuation) {
            return ((C05853) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Throwable th;
            ReadException readException;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    obj = this.this$0.readDataOrHandleCorruption(true, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        th = (Throwable) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        readException = new ReadException(th, ((Number) obj).intValue());
                        return TuplesKt.to(readException, Boxing.boxBoolean(true));
                    }
                    ResultKt.throwOnFailure(obj);
                }
                readException = (State) obj;
            } catch (Throwable th2) {
                this.L$0 = th2;
                this.label = 2;
                Object version = this.this$0.getCoordinator().getVersion(this);
                if (version == coroutine_suspended) {
                    return coroutine_suspended;
                }
                th = th2;
                obj = version;
            }
            return TuplesKt.to(readException, Boxing.boxBoolean(true));
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0005\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Landroidx/datastore/core/State;", ExifInterface.GPS_DIRECTION_TRUE, "", "locked"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4", f = "DataStoreImpl.kt", i = {0, 1}, l = {314, 317}, m = "invokeSuspend", n = {"locked", "locked"}, s = {"Z$0", "Z$0"})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<Boolean, Continuation<? super Pair<? extends State<T>, ? extends Boolean>>, Object> {
        final /* synthetic */ int $cachedVersion;
        Object L$0;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(DataStoreImpl<T> dataStoreImpl, int i, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.this$0 = dataStoreImpl;
            this.$cachedVersion = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, this.$cachedVersion, continuation);
            anonymousClass4.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Object obj) {
            return invoke(bool.booleanValue(), (Continuation) obj);
        }

        public final Object invoke(boolean z, Continuation<? super Pair<? extends State<T>, Boolean>> continuation) {
            return ((AnonymousClass4) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v14 */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v4, types: [boolean] */
        /* JADX WARN: Type inference failed for: r1v6 */
        /* JADX WARN: Type inference failed for: r1v9 */
        /* JADX WARN: Type inference failed for: r5v0 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Throwable th;
            int iIntValue;
            ?? r0;
            ?? r1;
            ReadException readException;
            ?? r2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r3 = this.label;
            try {
                if (r3 == 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = this.Z$0;
                    this.Z$0 = z;
                    this.label = 1;
                    obj = this.this$0.readDataOrHandleCorruption(z, this);
                    r3 = z;
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (r3 != 1) {
                        if (r3 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        boolean z2 = this.Z$0;
                        th = (Throwable) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        r1 = z2;
                        iIntValue = ((Number) obj).intValue();
                        r0 = r1;
                        readException = new ReadException(th, iIntValue);
                        r2 = r0;
                        return TuplesKt.to(readException, Boxing.boxBoolean(r2));
                    }
                    boolean z3 = this.Z$0;
                    ResultKt.throwOnFailure(obj);
                    r3 = z3;
                }
                readException = (State) obj;
                r2 = r3;
            } catch (Throwable th2) {
                if (r3 != 0) {
                    this.L$0 = th2;
                    this.Z$0 = r3;
                    this.label = 2;
                    Object version = this.this$0.getCoordinator().getVersion(this);
                    if (version == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r1 = r3;
                    th = th2;
                    obj = version;
                } else {
                    ?? r5 = r3;
                    th = th2;
                    iIntValue = this.$cachedVersion;
                    r0 = r5 == true ? 1 : 0;
                }
                readException = new ReadException(th, iIntValue);
                r2 = r0;
                return TuplesKt.to(readException, Boxing.boxBoolean(r2));
            }
            return TuplesKt.to(readException, Boxing.boxBoolean(r2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readDataFromFileOrDefault(Continuation<? super T> continuation) {
        return StorageConnectionKt.readData(getStorageConnection$datastore_core_release(), continuation);
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$transformAndWrite$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\u008a@"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$transformAndWrite$2", f = "DataStoreImpl.kt", i = {1, 2}, l = {338, 339, 345}, m = "invokeSuspend", n = {"curData", "newData"}, s = {"L$0", "L$0"})
    static final class C05892 extends SuspendLambda implements Function1<Continuation<? super T>, Object> {
        final /* synthetic */ CoroutineContext $callerContext;
        final /* synthetic */ Function2<T, Continuation<? super T>, Object> $transform;
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05892(DataStoreImpl<T> dataStoreImpl, CoroutineContext coroutineContext, Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super C05892> continuation) {
            super(1, continuation);
            this.this$0 = dataStoreImpl;
            this.$callerContext = coroutineContext;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C05892(this.this$0, this.$callerContext, this.$transform, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super T> continuation) {
            return ((C05892) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0066  */
        /* JADX WARN: Code duplicated, block: B:22:0x0075 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x0076  */
        /* JADX WARN: Code duplicated, block: B:26:? A[RETURN, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Data data;
            DataStoreImpl<T> dataStoreImpl;
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                } else if (i == 2) {
                    data = (Data) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    data.checkHashCode();
                    if (!Intrinsics.areEqual(data.getValue(), obj)) {
                        return obj;
                    }
                    dataStoreImpl = this.this$0;
                    this.L$0 = obj;
                    this.label = 3;
                    if (dataStoreImpl.writeData$datastore_core_release((T) obj, true, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = obj;
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    obj2 = this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return obj2;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.readDataOrHandleCorruption(true, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            data = (Data) obj;
            this.L$0 = data;
            this.label = 2;
            obj = BuildersKt.withContext(this.$callerContext, new DataStoreImpl$transformAndWrite$2$newData$1(this.$transform, data, null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            data.checkHashCode();
            if (!Intrinsics.areEqual(data.getValue(), obj)) {
                return obj;
            }
            dataStoreImpl = this.this$0;
            this.L$0 = obj;
            this.label = 3;
            if (dataStoreImpl.writeData$datastore_core_release((T) obj, true, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj2 = obj;
            return obj2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object transformAndWrite(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, CoroutineContext coroutineContext, Continuation<? super T> continuation) {
        return getCoordinator().lock(new C05892(this, coroutineContext, function2, null), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object writeData$datastore_core_release(T t, boolean z, Continuation<? super Integer> continuation) {
        DataStoreImpl$writeData$1 dataStoreImpl$writeData$1;
        Ref.IntRef intRef;
        if (continuation instanceof DataStoreImpl$writeData$1) {
            dataStoreImpl$writeData$1 = (DataStoreImpl$writeData$1) continuation;
            if ((dataStoreImpl$writeData$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$writeData$1.label -= Integer.MIN_VALUE;
            } else {
                dataStoreImpl$writeData$1 = new DataStoreImpl$writeData$1(this, continuation);
            }
        } else {
            dataStoreImpl$writeData$1 = new DataStoreImpl$writeData$1(this, continuation);
        }
        Object obj = dataStoreImpl$writeData$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = dataStoreImpl$writeData$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.IntRef intRef2 = new Ref.IntRef();
            StorageConnection<T> storageConnection$datastore_core_release = getStorageConnection$datastore_core_release();
            DataStoreImpl$writeData$2 dataStoreImpl$writeData$2 = new DataStoreImpl$writeData$2(intRef2, this, t, z, null);
            dataStoreImpl$writeData$1.L$0 = intRef2;
            dataStoreImpl$writeData$1.label = 1;
            if (storageConnection$datastore_core_release.writeScope(dataStoreImpl$writeData$2, dataStoreImpl$writeData$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            intRef = intRef2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            intRef = (Ref.IntRef) dataStoreImpl$writeData$1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxInt(intRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:45:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:48:0x00c7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:49:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:58:0x010b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:65:0x012c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:66:0x012d  */
    /* JADX WARN: Code duplicated, block: B:70:0x014f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:71:0x0150  */
    /* JADX WARN: Code duplicated, block: B:74:0x015a  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code duplicated, block: B:88:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [androidx.datastore.core.DataStoreImpl, androidx.datastore.core.DataStoreImpl<T>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v14, types: [androidx.datastore.core.DataStoreImpl, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r6v10, types: [androidx.datastore.core.DataStoreImpl] */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [androidx.datastore.core.DataStoreImpl, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.datastore.core.DataStoreImpl, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v2, types: [androidx.datastore.core.DataStoreImpl] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v5 */
    public final Object readDataOrHandleCorruption(boolean z, Continuation<? super Data<T>> continuation) throws CorruptionException {
        C05861 c05861;
        ?? r7;
        Ref.ObjectRef objectRef;
        Object objHandleCorruption;
        CorruptionException corruptionException;
        Ref.ObjectRef objectRef2;
        ?? r8;
        Ref.IntRef intRef;
        CorruptionException corruptionException2;
        C05873 c05873;
        Ref.IntRef intRef2;
        Ref.ObjectRef objectRef3;
        ?? r2;
        ?? r6;
        int iHashCode;
        Object version;
        boolean z2;
        int i;
        Object obj;
        ?? r9;
        ?? r10;
        if (continuation instanceof C05861) {
            c05861 = (C05861) continuation;
            if ((c05861.label & Integer.MIN_VALUE) != 0) {
                c05861.label -= Integer.MIN_VALUE;
            } else {
                c05861 = new C05861(this, continuation);
            }
        } else {
            c05861 = new C05861(this, continuation);
        }
        Object obj2 = (T) c05861.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r3 = c05861.label;
        try {
            switch (r3) {
                case 0:
                    ResultKt.throwOnFailure(obj2);
                    try {
                        if (z) {
                            c05861.L$0 = this;
                            c05861.Z$0 = z;
                            c05861.label = 1;
                            obj2 = (T) readDataFromFileOrDefault(c05861);
                            if (obj2 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            r6 = this;
                            if (obj2 != null) {
                                try {
                                    iHashCode = obj2.hashCode();
                                } catch (CorruptionException e) {
                                    e = e;
                                    r9 = r6;
                                    r7 = r9;
                                    objectRef = new Ref.ObjectRef();
                                    CorruptionHandler<T> corruptionHandler = r7.corruptionHandler;
                                    c05861.L$0 = r7;
                                    c05861.L$1 = e;
                                    c05861.L$2 = objectRef;
                                    c05861.L$3 = objectRef;
                                    c05861.Z$0 = z;
                                    c05861.label = 5;
                                    objHandleCorruption = corruptionHandler.handleCorruption(e, c05861);
                                    if (objHandleCorruption == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    corruptionException = e;
                                    obj2 = (T) objHandleCorruption;
                                    objectRef2 = objectRef;
                                    r8 = r7;
                                    objectRef2.element = (T) obj2;
                                    intRef = new Ref.IntRef();
                                    try {
                                        c05873 = new C05873(objectRef, r8, intRef, null);
                                        c05861.L$0 = corruptionException;
                                        c05861.L$1 = objectRef;
                                        c05861.L$2 = intRef;
                                        c05861.L$3 = null;
                                        c05861.label = 6;
                                        if (r8.doWithWriteFileLock(z, c05873, c05861) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        intRef2 = intRef;
                                        objectRef3 = objectRef;
                                        T t = objectRef3.element;
                                        T t2 = objectRef3.element;
                                        return new Data(t, t2 != null ? t2.hashCode() : 0, intRef2.element);
                                    } catch (Throwable th) {
                                        th = th;
                                        corruptionException2 = corruptionException;
                                        ExceptionsKt.addSuppressed(corruptionException2, th);
                                        throw corruptionException2;
                                    }
                                }
                            } else {
                                iHashCode = 0;
                            }
                            InterProcessCoordinator coordinator = r6.getCoordinator();
                            c05861.L$0 = r6;
                            c05861.L$1 = obj2;
                            c05861.Z$0 = z;
                            c05861.I$0 = iHashCode;
                            c05861.label = 2;
                            version = coordinator.getVersion(c05861);
                            if (version == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            int i2 = iHashCode;
                            z2 = z;
                            i = i2;
                            obj = obj2;
                            obj2 = (T) version;
                            r10 = r6;
                            return new Data(obj, i, ((Number) obj2).intValue());
                        }
                        InterProcessCoordinator coordinator2 = getCoordinator();
                        c05861.L$0 = this;
                        c05861.Z$0 = z;
                        c05861.label = 3;
                        obj2 = (T) coordinator2.getVersion(c05861);
                        if (obj2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        r2 = this;
                        int iIntValue = ((Number) obj2).intValue();
                        InterProcessCoordinator coordinator3 = r2.getCoordinator();
                        AnonymousClass2 anonymousClass2 = new AnonymousClass2(r2, iIntValue, null);
                        c05861.L$0 = r2;
                        c05861.Z$0 = z;
                        c05861.label = 4;
                        obj2 = (T) coordinator3.tryLock(anonymousClass2, c05861);
                        if (obj2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return (Data) obj2;
                    } catch (CorruptionException e2) {
                        e = e2;
                        r7 = this;
                        objectRef = new Ref.ObjectRef();
                        CorruptionHandler<T> corruptionHandler2 = r7.corruptionHandler;
                        c05861.L$0 = r7;
                        c05861.L$1 = e;
                        c05861.L$2 = objectRef;
                        c05861.L$3 = objectRef;
                        c05861.Z$0 = z;
                        c05861.label = 5;
                        objHandleCorruption = corruptionHandler2.handleCorruption(e, c05861);
                        if (objHandleCorruption == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        corruptionException = e;
                        obj2 = (T) objHandleCorruption;
                        objectRef2 = objectRef;
                        r8 = r7;
                        objectRef2.element = (T) obj2;
                        intRef = new Ref.IntRef();
                        c05873 = new C05873(objectRef, r8, intRef, null);
                        c05861.L$0 = corruptionException;
                        c05861.L$1 = objectRef;
                        c05861.L$2 = intRef;
                        c05861.L$3 = null;
                        c05861.label = 6;
                        if (r8.doWithWriteFileLock(z, c05873, c05861) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        intRef2 = intRef;
                        objectRef3 = objectRef;
                        T t3 = objectRef3.element;
                        T t4 = objectRef3.element;
                        return new Data(t3, t4 != null ? t4.hashCode() : 0, intRef2.element);
                    }
                case 1:
                    z = c05861.Z$0;
                    DataStoreImpl dataStoreImpl = (DataStoreImpl) c05861.L$0;
                    ResultKt.throwOnFailure(obj2);
                    r6 = dataStoreImpl;
                    if (obj2 != null) {
                        iHashCode = obj2.hashCode();
                    } else {
                        iHashCode = 0;
                    }
                    InterProcessCoordinator coordinator4 = r6.getCoordinator();
                    c05861.L$0 = r6;
                    c05861.L$1 = obj2;
                    c05861.Z$0 = z;
                    c05861.I$0 = iHashCode;
                    c05861.label = 2;
                    version = coordinator4.getVersion(c05861);
                    if (version == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int i3 = iHashCode;
                    z2 = z;
                    i = i3;
                    obj = obj2;
                    obj2 = (T) version;
                    r10 = r6;
                    return new Data(obj, i, ((Number) obj2).intValue());
                case 2:
                    i = c05861.I$0;
                    z2 = c05861.Z$0;
                    obj = c05861.L$1;
                    r10 = (DataStoreImpl) c05861.L$0;
                    try {
                        ResultKt.throwOnFailure(obj2);
                        r10 = r10;
                        return new Data(obj, i, ((Number) obj2).intValue());
                    } catch (CorruptionException e3) {
                        e = e3;
                        z = z2;
                        r9 = r10;
                        r7 = r9;
                        objectRef = new Ref.ObjectRef();
                        CorruptionHandler<T> corruptionHandler3 = r7.corruptionHandler;
                        c05861.L$0 = r7;
                        c05861.L$1 = e;
                        c05861.L$2 = objectRef;
                        c05861.L$3 = objectRef;
                        c05861.Z$0 = z;
                        c05861.label = 5;
                        objHandleCorruption = corruptionHandler3.handleCorruption(e, c05861);
                        if (objHandleCorruption == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        corruptionException = e;
                        obj2 = (T) objHandleCorruption;
                        objectRef2 = objectRef;
                        r8 = r7;
                        objectRef2.element = (T) obj2;
                        intRef = new Ref.IntRef();
                        c05873 = new C05873(objectRef, r8, intRef, null);
                        c05861.L$0 = corruptionException;
                        c05861.L$1 = objectRef;
                        c05861.L$2 = intRef;
                        c05861.L$3 = null;
                        c05861.label = 6;
                        if (r8.doWithWriteFileLock(z, c05873, c05861) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        intRef2 = intRef;
                        objectRef3 = objectRef;
                        T t5 = objectRef3.element;
                        T t6 = objectRef3.element;
                        return new Data(t5, t6 != null ? t6.hashCode() : 0, intRef2.element);
                    }
                case 3:
                    z = c05861.Z$0;
                    DataStoreImpl dataStoreImpl2 = (DataStoreImpl) c05861.L$0;
                    ResultKt.throwOnFailure(obj2);
                    r2 = dataStoreImpl2;
                    int iIntValue2 = ((Number) obj2).intValue();
                    InterProcessCoordinator coordinator5 = r2.getCoordinator();
                    AnonymousClass2 anonymousClass3 = new AnonymousClass2(r2, iIntValue2, null);
                    c05861.L$0 = r2;
                    c05861.Z$0 = z;
                    c05861.label = 4;
                    obj2 = (T) coordinator5.tryLock(anonymousClass3, c05861);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return (Data) obj2;
                case 4:
                    boolean z3 = c05861.Z$0;
                    ResultKt.throwOnFailure(obj2);
                    return (Data) obj2;
                case 5:
                    z = c05861.Z$0;
                    Ref.ObjectRef objectRef4 = (Ref.ObjectRef) c05861.L$3;
                    Ref.ObjectRef objectRef5 = (Ref.ObjectRef) c05861.L$2;
                    corruptionException = (CorruptionException) c05861.L$1;
                    DataStoreImpl dataStoreImpl3 = (DataStoreImpl) c05861.L$0;
                    ResultKt.throwOnFailure(obj2);
                    objectRef2 = objectRef4;
                    objectRef = objectRef5;
                    r8 = dataStoreImpl3;
                    objectRef2.element = (T) obj2;
                    intRef = new Ref.IntRef();
                    c05873 = new C05873(objectRef, r8, intRef, null);
                    c05861.L$0 = corruptionException;
                    c05861.L$1 = objectRef;
                    c05861.L$2 = intRef;
                    c05861.L$3 = null;
                    c05861.label = 6;
                    if (r8.doWithWriteFileLock(z, c05873, c05861) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    intRef2 = intRef;
                    objectRef3 = objectRef;
                    T t7 = objectRef3.element;
                    T t8 = objectRef3.element;
                    return new Data(t7, t8 != null ? t8.hashCode() : 0, intRef2.element);
                case 6:
                    intRef2 = (Ref.IntRef) c05861.L$2;
                    objectRef3 = (Ref.ObjectRef) c05861.L$1;
                    corruptionException2 = (CorruptionException) c05861.L$0;
                    try {
                        ResultKt.throwOnFailure(obj2);
                        T t9 = objectRef3.element;
                        T t10 = objectRef3.element;
                        return new Data(t9, t10 != null ? t10.hashCode() : 0, intRef2.element);
                    } catch (Throwable th2) {
                        th = th2;
                        ExceptionsKt.addSuppressed(corruptionException2, th);
                        throw corruptionException2;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (CorruptionException e4) {
            e = e4;
            r7 = r3;
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/Data;", ExifInterface.GPS_DIRECTION_TRUE, "locked", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2", f = "DataStoreImpl.kt", i = {0, 1}, l = {378, 379}, m = "invokeSuspend", n = {"locked", "data"}, s = {"Z$0", "L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Boolean, Continuation<? super Data<T>>, Object> {
        final /* synthetic */ int $preLockVersion;
        Object L$0;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DataStoreImpl<T> dataStoreImpl, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = dataStoreImpl;
            this.$preLockVersion = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$preLockVersion, continuation);
            anonymousClass2.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Object obj) {
            return invoke(bool.booleanValue(), (Continuation) obj);
        }

        public final Object invoke(boolean z, Continuation<? super Data<T>> continuation) {
            return ((AnonymousClass2) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x005f  */
        /* JADX WARN: Code duplicated, block: B:23:0x0064  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            boolean z;
            Object obj2;
            int iIntValue;
            int iHashCode;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                z = this.Z$0;
                this.Z$0 = z;
                this.label = 1;
                obj = this.this$0.readDataFromFileOrDefault(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i == 1) {
                    z = this.Z$0;
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    obj2 = this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                iIntValue = ((Number) obj).intValue();
                if (obj2 != null) {
                    iHashCode = obj2.hashCode();
                } else {
                    iHashCode = 0;
                }
                return new Data(obj2, iHashCode, iIntValue);
            }
            if (z) {
                this.L$0 = obj;
                this.label = 2;
                Object version = this.this$0.getCoordinator().getVersion(this);
                if (version == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = obj;
                obj = version;
                iIntValue = ((Number) obj).intValue();
            } else {
                obj2 = obj;
                iIntValue = this.$preLockVersion;
            }
            if (obj2 != null) {
                iHashCode = obj2.hashCode();
            } else {
                iHashCode = 0;
            }
            return new Data(obj2, iHashCode, iIntValue);
        }
    }

    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3", f = "DataStoreImpl.kt", i = {}, l = {395, 396, 398}, m = "invokeSuspend", n = {}, s = {})
    static final class C05873 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<T> $newData;
        final /* synthetic */ Ref.IntRef $version;
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05873(Ref.ObjectRef<T> objectRef, DataStoreImpl<T> dataStoreImpl, Ref.IntRef intRef, Continuation<? super C05873> continuation) {
            super(1, continuation);
            this.$newData = objectRef;
            this.this$0 = dataStoreImpl;
            this.$version = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C05873(this.$newData, this.this$0, this.$version, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C05873) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.IntRef intRef;
            Ref.ObjectRef<T> objectRef;
            Ref.IntRef intRef2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    objectRef = this.$newData;
                    this.L$0 = objectRef;
                    this.label = 1;
                    obj = (T) this.this$0.readDataFromFileOrDefault(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i == 1) {
                        objectRef = (Ref.ObjectRef) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    } else {
                        if (i != 2) {
                            if (i != 3) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            intRef = (Ref.IntRef) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            intRef.element = ((Number) obj).intValue();
                            return Unit.INSTANCE;
                        }
                        intRef2 = (Ref.IntRef) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    intRef2.element = ((Number) obj).intValue();
                    return Unit.INSTANCE;
                }
                objectRef.element = (T) obj;
                intRef2 = this.$version;
                this.L$0 = intRef2;
                this.label = 2;
                obj = (T) this.this$0.getCoordinator().getVersion(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                intRef2.element = ((Number) obj).intValue();
            } catch (CorruptionException unused) {
                Ref.IntRef intRef3 = this.$version;
                this.L$0 = intRef3;
                this.label = 3;
                Object objWriteData$datastore_core_release = this.this$0.writeData$datastore_core_release(this.$newData.element, true, this);
                if (objWriteData$datastore_core_release == coroutine_suspended) {
                    return coroutine_suspended;
                }
                intRef = intRef3;
                obj = (T) objWriteData$datastore_core_release;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> Object doWithWriteFileLock(boolean z, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        if (z) {
            return function1.invoke(continuation);
        }
        return getCoordinator().lock(new AnonymousClass3(function1, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.datastore.core.DataStoreImpl$doWithWriteFileLock$3, reason: invalid class name */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "R", ExifInterface.GPS_DIRECTION_TRUE}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$doWithWriteFileLock$3", f = "DataStoreImpl.kt", i = {}, l = {424}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3<R> extends SuspendLambda implements Function1<Continuation<? super R>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super R> continuation) {
            return ((AnonymousClass3) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function1<Continuation<? super R>, Object> function1 = this.$block;
                this.label = 1;
                obj = function1.invoke(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001BD\u0012=\u0010\u0002\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00040\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u000e\u001a\u00020\nH\u0094@¢\u0006\u0002\u0010\u000fRG\u0010\r\u001a;\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "Landroidx/datastore/core/RunOnce;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "api", "Lkotlin/coroutines/Continuation;", "", "", "(Landroidx/datastore/core/DataStoreImpl;Ljava/util/List;)V", "initTasks", "doRun", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    final class InitDataStore extends RunOnce {
        private List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasks;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public InitDataStore(DataStoreImpl dataStoreImpl, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList) {
            Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
            this.this$0 = dataStoreImpl;
            this.initTasks = CollectionsKt.toList(initTasksList);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // androidx.datastore.core.RunOnce
        protected Object doRun(Continuation<? super Unit> continuation) throws CorruptionException {
            DataStoreImpl$InitDataStore$doRun$1 dataStoreImpl$InitDataStore$doRun$1;
            InitDataStore initDataStore;
            Data data;
            if (continuation instanceof DataStoreImpl$InitDataStore$doRun$1) {
                dataStoreImpl$InitDataStore$doRun$1 = (DataStoreImpl$InitDataStore$doRun$1) continuation;
                if ((dataStoreImpl$InitDataStore$doRun$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$InitDataStore$doRun$1.label -= Integer.MIN_VALUE;
                } else {
                    dataStoreImpl$InitDataStore$doRun$1 = new DataStoreImpl$InitDataStore$doRun$1(this, continuation);
                }
            } else {
                dataStoreImpl$InitDataStore$doRun$1 = new DataStoreImpl$InitDataStore$doRun$1(this, continuation);
            }
            Object dataOrHandleCorruption = dataStoreImpl$InitDataStore$doRun$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = dataStoreImpl$InitDataStore$doRun$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(dataOrHandleCorruption);
                List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> list = this.initTasks;
                if (list != null) {
                    Intrinsics.checkNotNull(list);
                    if (!list.isEmpty()) {
                        InterProcessCoordinator coordinator = this.this$0.getCoordinator();
                        DataStoreImpl$InitDataStore$doRun$initData$1 dataStoreImpl$InitDataStore$doRun$initData$1 = new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this, null);
                        dataStoreImpl$InitDataStore$doRun$1.L$0 = this;
                        dataStoreImpl$InitDataStore$doRun$1.label = 2;
                        dataOrHandleCorruption = coordinator.lock(dataStoreImpl$InitDataStore$doRun$initData$1, dataStoreImpl$InitDataStore$doRun$1);
                        if (dataOrHandleCorruption == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        initDataStore = this;
                        data = (Data) dataOrHandleCorruption;
                    }
                }
                DataStoreImpl<T> dataStoreImpl = this.this$0;
                dataStoreImpl$InitDataStore$doRun$1.L$0 = this;
                dataStoreImpl$InitDataStore$doRun$1.label = 1;
                dataOrHandleCorruption = dataStoreImpl.readDataOrHandleCorruption(false, dataStoreImpl$InitDataStore$doRun$1);
                if (dataOrHandleCorruption == coroutine_suspended) {
                    return coroutine_suspended;
                }
                initDataStore = this;
                data = (Data) dataOrHandleCorruption;
            } else if (i == 1) {
                initDataStore = (InitDataStore) dataStoreImpl$InitDataStore$doRun$1.L$0;
                ResultKt.throwOnFailure(dataOrHandleCorruption);
                data = (Data) dataOrHandleCorruption;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                initDataStore = (InitDataStore) dataStoreImpl$InitDataStore$doRun$1.L$0;
                ResultKt.throwOnFailure(dataOrHandleCorruption);
                data = (Data) dataOrHandleCorruption;
            }
            ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(data);
            return Unit.INSTANCE;
        }
    }
}
