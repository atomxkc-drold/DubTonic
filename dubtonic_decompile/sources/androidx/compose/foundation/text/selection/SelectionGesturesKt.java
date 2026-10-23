package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.core.location.LocationRequestCompat;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SelectionGestures.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u0012\u0010\f\u001a\u00020\u0004*\u00020\rH\u0082@¢\u0006\u0002\u0010\u000e\u001a*\u0010\u000f\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0016\u001a*\u0010\u0017\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0016\u001a\u001c\u0010\u0018\u001a\u00020\u0019*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0000\u001a\"\u0010\u001d\u001a\u00020\u0010*\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0080@¢\u0006\u0002\u0010\u001f\u001a\"\u0010 \u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a\"\u0010\"\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a\"\u0010$\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a \u0010%\u001a\u00020\u0019*\u00020\u00192\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100'H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005¨\u0006("}, d2 = {"STATIC_KEY", "", "isPrecisePointer", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "distanceIsTolerable", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "change1", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "change2", "awaitDown", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelection", "", "observer", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "clicksCounter", "Landroidx/compose/foundation/text/selection/ClicksCounter;", "down", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/selection/ClicksCounter;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelectionBtf2", "selectionGestureInput", "Landroidx/compose/ui/Modifier;", "mouseSelectionObserver", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "selectionGesturePointerInputBtf2", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelection", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelectionFirstPress", "downEvent", "touchSelectionSubsequentPress", "updateSelectionTouchMode", "updateTouchMode", "Lkotlin/Function1;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionGesturesKt {
    private static final int STATIC_KEY = 8675309;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0}, l = {425}, m = "awaitDown", n = {"$this$awaitDown"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.awaitDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1}, l = {158, 181}, m = "mouseSelection", n = {"$this$mouseSelection", "observer", "$this$mouseSelection", "observer"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C03551 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03551(Continuation<? super C03551> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelection(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelectionBtf2$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1}, l = {351, 377}, m = "mouseSelectionBtf2", n = {"$this$mouseSelectionBtf2", "observer", "$this$mouseSelectionBtf2", "observer"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C03561 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03561(Continuation<? super C03561> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelectionBtf2(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {124, 128}, m = "touchSelection", n = {"$this$touchSelection", "observer", "firstDown", "$this$touchSelection", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C03581 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C03581(Continuation<? super C03581> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelection(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionFirstPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {238, 241}, m = "touchSelectionFirstPress", n = {"$this$touchSelectionFirstPress", "observer", "firstDown", "$this$touchSelectionFirstPress", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C03601 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C03601(Continuation<? super C03601> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionFirstPress(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 0, 0, 1, 1}, l = {276, 315}, m = "touchSelectionSubsequentPress", n = {"$this$touchSelectionSubsequentPress", "observer", "firstDown", "overSlop", "pointerId", "$this$touchSelectionSubsequentPress", "observer"}, s = {"L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1"})
    static final class C03611 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C03611(Continuation<? super C03611> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionSubsequentPress(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1", f = "SelectionGestures.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    static final class C03621 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03621(Function1<? super Boolean, Unit> function1, Continuation<? super C03621> continuation) {
            super(2, continuation);
            this.$updateTouchMode = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03621 c03621 = new C03621(this.$updateTouchMode, continuation);
            c03621.L$0 = obj;
            return c03621;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C03621) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SelectionGestures.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1", f = "SelectionGestures.kt", i = {0}, l = {93}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
        static final class C00611 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00611(Function1<? super Boolean, Unit> function1, Continuation<? super C00611> continuation) {
                super(2, continuation);
                this.$updateTouchMode = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00611 c00611 = new C00611(this.$updateTouchMode, continuation);
                c00611.L$0 = obj;
                return c00611;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00611) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:11:0x0033 A[RETURN] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0031 -> B:12:0x0034). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:11:0x0033
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final java.lang.Object invokeSuspend(java.lang.Object r5) {
                /*
                    r4 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r4.label
                    r2 = 1
                    if (r1 == 0) goto L1c
                    if (r1 != r2) goto L13
                    java.lang.Object r1 = r4.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    kotlin.ResultKt.throwOnFailure(r5)
                    goto L34
                L13:
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r5.<init>(r0)
                    throw r5
                L1c:
                    kotlin.ResultKt.throwOnFailure(r5)
                    java.lang.Object r5 = r4.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                    r1 = r5
                L24:
                    androidx.compose.ui.input.pointer.PointerEventPass r5 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r3 = r4
                    kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                    r4.L$0 = r1
                    r4.label = r2
                    java.lang.Object r5 = r1.awaitPointerEvent(r5, r3)
                    if (r5 != r0) goto L34
                    return r0
                L34:
                    androidx.compose.ui.input.pointer.PointerEvent r5 = (androidx.compose.ui.input.pointer.PointerEvent) r5
                    kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r3 = r4.$updateTouchMode
                    boolean r5 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r5)
                    r5 = r5 ^ r2
                    java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
                    r3.invoke(r5)
                    goto L24
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C03621.C00611.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((PointerInputScope) this.L$0).awaitPointerEventScope(new C00611(this.$updateTouchMode, null), this) == coroutine_suspended) {
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

    public static final Modifier updateSelectionTouchMode(Modifier modifier, Function1<? super Boolean, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, Integer.valueOf(STATIC_KEY), new C03621(function1, null));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1", f = "SelectionGestures.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {})
    static final class C03571 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03571(MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super C03571> continuation) {
            super(2, continuation);
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03571 c03571 = new C03571(this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            c03571.L$0 = obj;
            return c03571;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C03571) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                ClicksCounter clicksCounter = new ClicksCounter(pointerInputScope.getViewConfiguration());
                this.label = 1;
                if (ForEachGestureKt.awaitEachGesture(pointerInputScope, new C00601(this.$mouseSelectionObserver, clicksCounter, this.$textDragObserver, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SelectionGestures.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1", f = "SelectionGestures.kt", i = {0}, l = {105, 111, 113}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
        static final class C00601 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ClicksCounter $clicksCounter;
            final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
            final /* synthetic */ TextDragObserver $textDragObserver;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00601(MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, TextDragObserver textDragObserver, Continuation<? super C00601> continuation) {
                super(2, continuation);
                this.$mouseSelectionObserver = mouseSelectionObserver;
                this.$clicksCounter = clicksCounter;
                this.$textDragObserver = textDragObserver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00601 c00601 = new C00601(this.$mouseSelectionObserver, this.$clicksCounter, this.$textDragObserver, continuation);
                c00601.L$0 = obj;
                return c00601;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00601) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:30:0x0084  */
            /* JADX WARN: Code duplicated, block: B:32:0x0093 A[RETURN] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                AwaitPointerEventScope awaitPointerEventScope;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = awaitPointerEventScope;
                    this.label = 1;
                    obj = SelectionGesturesKt.awaitDown(awaitPointerEventScope, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i == 1) {
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    } else {
                        if (i != 2 && i != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
                PointerEvent pointerEvent = (PointerEvent) obj;
                if (SelectionGesturesKt.isPrecisePointer(pointerEvent) && PointerEvent_androidKt.m5019isPrimaryPressedaHzCxE(pointerEvent.getButtons())) {
                    List<PointerInputChange> changes = pointerEvent.getChanges();
                    int size = changes.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            this.L$0 = null;
                            this.label = 2;
                            if (SelectionGesturesKt.mouseSelection(awaitPointerEventScope, this.$mouseSelectionObserver, this.$clicksCounter, pointerEvent, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (changes.get(i2).isConsumed()) {
                                break;
                            }
                            i2++;
                        }
                    }
                    if (!SelectionGesturesKt.isPrecisePointer(pointerEvent)) {
                        this.L$0 = null;
                        this.label = 3;
                        if (SelectionGesturesKt.touchSelection(awaitPointerEventScope, this.$textDragObserver, pointerEvent, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else if (!SelectionGesturesKt.isPrecisePointer(pointerEvent)) {
                    this.L$0 = null;
                    this.label = 3;
                    if (SelectionGesturesKt.touchSelection(awaitPointerEventScope, this.$textDragObserver, pointerEvent, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public static final Modifier selectionGestureInput(Modifier modifier, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, mouseSelectionObserver, textDragObserver, new C03571(mouseSelectionObserver, textDragObserver, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:33:0x00aa A[Catch: CancellationException -> 0x00d5, TryCatch #0 {CancellationException -> 0x00d5, blocks: (B:13:0x0032, B:31:0x00a2, B:33:0x00aa, B:35:0x00b9, B:37:0x00c5, B:38:0x00c8, B:39:0x00cb, B:40:0x00cf, B:18:0x004b, B:24:0x0072, B:26:0x0076, B:28:0x0080, B:21:0x0055), top: B:46:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00b9 A[Catch: CancellationException -> 0x00d5, TryCatch #0 {CancellationException -> 0x00d5, blocks: (B:13:0x0032, B:31:0x00a2, B:33:0x00aa, B:35:0x00b9, B:37:0x00c5, B:38:0x00c8, B:39:0x00cb, B:40:0x00cf, B:18:0x004b, B:24:0x0072, B:26:0x0076, B:28:0x0080, B:21:0x0055), top: B:46:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00c5 A[Catch: CancellationException -> 0x00d5, TryCatch #0 {CancellationException -> 0x00d5, blocks: (B:13:0x0032, B:31:0x00a2, B:33:0x00aa, B:35:0x00b9, B:37:0x00c5, B:38:0x00c8, B:39:0x00cb, B:40:0x00cf, B:18:0x004b, B:24:0x0072, B:26:0x0076, B:28:0x0080, B:21:0x0055), top: B:46:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00cf A[Catch: CancellationException -> 0x00d5, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00d5, blocks: (B:13:0x0032, B:31:0x00a2, B:33:0x00aa, B:35:0x00b9, B:37:0x00c5, B:38:0x00c8, B:39:0x00cb, B:40:0x00cf, B:18:0x004b, B:24:0x0072, B:26:0x0076, B:28:0x0080, B:21:0x0055), top: B:46:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object touchSelection(AwaitPointerEventScope awaitPointerEventScope, final TextDragObserver textDragObserver, PointerEvent pointerEvent, Continuation<? super Unit> continuation) {
        C03581 c03581;
        PointerInputChange pointerInputChange;
        List<PointerInputChange> changes;
        int size;
        int i;
        PointerInputChange pointerInputChange2;
        if (continuation instanceof C03581) {
            c03581 = (C03581) continuation;
            if ((c03581.label & Integer.MIN_VALUE) != 0) {
                c03581.label -= Integer.MIN_VALUE;
            } else {
                c03581 = new C03581(continuation);
            }
        } else {
            c03581 = new C03581(continuation);
        }
        Object objM414awaitLongPressOrCancellationrnUCldI = c03581.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c03581.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objM414awaitLongPressOrCancellationrnUCldI);
                pointerInputChange = (PointerInputChange) CollectionsKt.first((List) pointerEvent.getChanges());
                long id = pointerInputChange.getId();
                c03581.L$0 = awaitPointerEventScope;
                c03581.L$1 = textDragObserver;
                c03581.L$2 = pointerInputChange;
                c03581.label = 1;
                objM414awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m414awaitLongPressOrCancellationrnUCldI(awaitPointerEventScope, id, c03581);
                if (objM414awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 == 1) {
                    PointerInputChange pointerInputChange3 = (PointerInputChange) c03581.L$2;
                    textDragObserver = (TextDragObserver) c03581.L$1;
                    AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) c03581.L$0;
                    ResultKt.throwOnFailure(objM414awaitLongPressOrCancellationrnUCldI);
                    pointerInputChange = pointerInputChange3;
                    awaitPointerEventScope = awaitPointerEventScope2;
                } else {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    textDragObserver = (TextDragObserver) c03581.L$1;
                    awaitPointerEventScope = (AwaitPointerEventScope) c03581.L$0;
                    ResultKt.throwOnFailure(objM414awaitLongPressOrCancellationrnUCldI);
                }
                if (((Boolean) objM414awaitLongPressOrCancellationrnUCldI).booleanValue()) {
                    changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                    size = changes.size();
                    for (i = 0; i < size; i++) {
                        pointerInputChange2 = changes.get(i);
                        if (PointerEventKt.changedToUp(pointerInputChange2)) {
                            pointerInputChange2.consume();
                        }
                    }
                    textDragObserver.onStop();
                } else {
                    textDragObserver.onCancel();
                }
                return Unit.INSTANCE;
            }
            PointerInputChange pointerInputChange4 = (PointerInputChange) objM414awaitLongPressOrCancellationrnUCldI;
            if (pointerInputChange4 != null && distanceIsTolerable(awaitPointerEventScope.getViewConfiguration(), pointerInputChange, pointerInputChange4)) {
                textDragObserver.mo1060onStartk4lQ0M(pointerInputChange4.getPosition());
                long id2 = pointerInputChange4.getId();
                Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelection.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange5) {
                        invoke2(pointerInputChange5);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(PointerInputChange pointerInputChange5) {
                        textDragObserver.mo1059onDragk4lQ0M(PointerEventKt.positionChange(pointerInputChange5));
                        pointerInputChange5.consume();
                    }
                };
                c03581.L$0 = awaitPointerEventScope;
                c03581.L$1 = textDragObserver;
                c03581.L$2 = null;
                c03581.label = 2;
                objM414awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m422dragjO51t88(awaitPointerEventScope, id2, function1, c03581);
                if (objM414awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) objM414awaitLongPressOrCancellationrnUCldI).booleanValue()) {
                    changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                    size = changes.size();
                    while (i < size) {
                        pointerInputChange2 = changes.get(i);
                        if (PointerEventKt.changedToUp(pointerInputChange2)) {
                            pointerInputChange2.consume();
                        }
                    }
                    textDragObserver.onStop();
                } else {
                    textDragObserver.onCancel();
                }
            }
            return Unit.INSTANCE;
        } catch (CancellationException e) {
            textDragObserver.onCancel();
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:25:0x008e  */
    /* JADX WARN: Code duplicated, block: B:27:0x009c  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:45:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:47:0x0106  */
    /* JADX WARN: Code duplicated, block: B:49:0x0112  */
    /* JADX WARN: Code duplicated, block: B:56:0x0115 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x00ab A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object mouseSelection(AwaitPointerEventScope awaitPointerEventScope, final MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, PointerEvent pointerEvent, Continuation<? super Unit> continuation) {
        C03551 c03551;
        final SelectionAdjustment none;
        List<PointerInputChange> changes;
        int size;
        PointerInputChange pointerInputChange;
        List<PointerInputChange> changes2;
        int size2;
        PointerInputChange pointerInputChange2;
        if (continuation instanceof C03551) {
            c03551 = (C03551) continuation;
            if ((c03551.label & Integer.MIN_VALUE) != 0) {
                c03551.label -= Integer.MIN_VALUE;
            } else {
                c03551 = new C03551(continuation);
            }
        } else {
            c03551 = new C03551(continuation);
        }
        Object objM422dragjO51t88 = c03551.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c03551.label;
        int i2 = 0;
        if (i == 0) {
            ResultKt.throwOnFailure(objM422dragjO51t88);
            clicksCounter.update(pointerEvent);
            PointerInputChange pointerInputChange3 = pointerEvent.getChanges().get(0);
            if (TextFieldSelectionManager_androidKt.isShiftPressed(pointerEvent)) {
                if (mouseSelectionObserver.mo1273onExtendk4lQ0M(pointerInputChange3.getPosition())) {
                    long id = pointerInputChange3.getId();
                    Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$shouldConsumeUp$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange4) {
                            invoke2(pointerInputChange4);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(PointerInputChange pointerInputChange4) {
                            if (mouseSelectionObserver.mo1274onExtendDragk4lQ0M(pointerInputChange4.getPosition())) {
                                pointerInputChange4.consume();
                            }
                        }
                    };
                    c03551.L$0 = awaitPointerEventScope;
                    c03551.L$1 = mouseSelectionObserver;
                    c03551.label = 1;
                    objM422dragjO51t88 = DragGestureDetectorKt.m422dragjO51t88(awaitPointerEventScope, id, function1, c03551);
                    if (objM422dragjO51t88 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (((Boolean) objM422dragjO51t88).booleanValue()) {
                        changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                        size = changes.size();
                        while (i2 < size) {
                            pointerInputChange = changes.get(i2);
                            if (PointerEventKt.changedToUp(pointerInputChange)) {
                                pointerInputChange.consume();
                            }
                            i2++;
                        }
                    }
                    mouseSelectionObserver.onDragDone();
                }
            } else {
                int clicks = clicksCounter.getClicks();
                if (clicks == 1) {
                    none = SelectionAdjustment.INSTANCE.getNone();
                } else if (clicks == 2) {
                    none = SelectionAdjustment.INSTANCE.getWord();
                } else {
                    none = SelectionAdjustment.INSTANCE.getParagraph();
                }
                if (mouseSelectionObserver.mo1275onStart3MmeM6k(pointerInputChange3.getPosition(), none)) {
                    long id2 = pointerInputChange3.getId();
                    Function1<PointerInputChange, Unit> function2 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$shouldConsumeUp$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange4) {
                            invoke2(pointerInputChange4);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(PointerInputChange pointerInputChange4) {
                            if (mouseSelectionObserver.mo1272onDrag3MmeM6k(pointerInputChange4.getPosition(), none)) {
                                pointerInputChange4.consume();
                            }
                        }
                    };
                    c03551.L$0 = awaitPointerEventScope;
                    c03551.L$1 = mouseSelectionObserver;
                    c03551.label = 2;
                    objM422dragjO51t88 = DragGestureDetectorKt.m422dragjO51t88(awaitPointerEventScope, id2, function2, c03551);
                    if (objM422dragjO51t88 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (((Boolean) objM422dragjO51t88).booleanValue()) {
                        changes2 = awaitPointerEventScope.getCurrentEvent().getChanges();
                        size2 = changes2.size();
                        while (i2 < size2) {
                            pointerInputChange2 = changes2.get(i2);
                            if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                pointerInputChange2.consume();
                            }
                            i2++;
                        }
                    }
                    mouseSelectionObserver.onDragDone();
                }
            }
        } else if (i == 1) {
            mouseSelectionObserver = (MouseSelectionObserver) c03551.L$1;
            awaitPointerEventScope = (AwaitPointerEventScope) c03551.L$0;
            ResultKt.throwOnFailure(objM422dragjO51t88);
            if (((Boolean) objM422dragjO51t88).booleanValue()) {
                changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                size = changes.size();
                while (i2 < size) {
                    pointerInputChange = changes.get(i2);
                    if (PointerEventKt.changedToUp(pointerInputChange)) {
                        pointerInputChange.consume();
                    }
                    i2++;
                }
            }
            mouseSelectionObserver.onDragDone();
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            mouseSelectionObserver = (MouseSelectionObserver) c03551.L$1;
            awaitPointerEventScope = (AwaitPointerEventScope) c03551.L$0;
            ResultKt.throwOnFailure(objM422dragjO51t88);
            if (((Boolean) objM422dragjO51t88).booleanValue()) {
                changes2 = awaitPointerEventScope.getCurrentEvent().getChanges();
                size2 = changes2.size();
                while (i2 < size2) {
                    pointerInputChange2 = changes2.get(i2);
                    if (PointerEventKt.changedToUp(pointerInputChange2)) {
                        pointerInputChange2.consume();
                    }
                    i2++;
                }
            }
            mouseSelectionObserver.onDragDone();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGesturePointerInputBtf2$2, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGesturePointerInputBtf2$2", f = "SelectionGestures.kt", i = {0}, l = {209, 217, 220, 221}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
    static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ClicksCounter $clicksCounter;
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ClicksCounter clicksCounter, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$clicksCounter = clicksCounter;
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$clicksCounter, this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:30:0x0088  */
        /* JADX WARN: Code duplicated, block: B:32:0x0090  */
        /* JADX WARN: Code duplicated, block: B:34:0x009f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:35:0x00a0  */
        /* JADX WARN: Code duplicated, block: B:37:0x00af A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AwaitPointerEventScope awaitPointerEventScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                this.L$0 = awaitPointerEventScope;
                this.label = 1;
                obj = SelectionGesturesKt.awaitDown(awaitPointerEventScope, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i == 1) {
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2 && i != 3 && i != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
            PointerEvent pointerEvent = (PointerEvent) obj;
            this.$clicksCounter.update(pointerEvent);
            boolean zIsPrecisePointer = SelectionGesturesKt.isPrecisePointer(pointerEvent);
            if (zIsPrecisePointer && PointerEvent_androidKt.m5019isPrimaryPressedaHzCxE(pointerEvent.getButtons())) {
                List<PointerInputChange> changes = pointerEvent.getChanges();
                int size = changes.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        this.L$0 = null;
                        this.label = 2;
                        if (SelectionGesturesKt.mouseSelectionBtf2(awaitPointerEventScope, this.$mouseSelectionObserver, this.$clicksCounter, pointerEvent, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (changes.get(i2).isConsumed()) {
                            break;
                        }
                        i2++;
                    }
                }
                if (!zIsPrecisePointer) {
                    if (this.$clicksCounter.getClicks() == 1) {
                        this.L$0 = null;
                        this.label = 3;
                        if (SelectionGesturesKt.touchSelectionFirstPress(awaitPointerEventScope, this.$textDragObserver, pointerEvent, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        this.L$0 = null;
                        this.label = 4;
                        if (SelectionGesturesKt.touchSelectionSubsequentPress(awaitPointerEventScope, this.$textDragObserver, pointerEvent, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
            } else if (!zIsPrecisePointer) {
                if (this.$clicksCounter.getClicks() == 1) {
                    this.L$0 = null;
                    this.label = 3;
                    if (SelectionGesturesKt.touchSelectionFirstPress(awaitPointerEventScope, this.$textDragObserver, pointerEvent, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    this.L$0 = null;
                    this.label = 4;
                    if (SelectionGesturesKt.touchSelectionSubsequentPress(awaitPointerEventScope, this.$textDragObserver, pointerEvent, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final Object selectionGesturePointerInputBtf2(PointerInputScope pointerInputScope, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass2(new ClicksCounter(pointerInputScope.getViewConfiguration()), mouseSelectionObserver, textDragObserver, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:33:0x00aa A[Catch: CancellationException -> 0x00d5, TryCatch #0 {CancellationException -> 0x00d5, blocks: (B:13:0x0032, B:31:0x00a2, B:33:0x00aa, B:35:0x00b9, B:37:0x00c5, B:38:0x00c8, B:39:0x00cb, B:40:0x00cf, B:18:0x004b, B:24:0x0072, B:26:0x0076, B:28:0x0080, B:21:0x0055), top: B:46:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00b9 A[Catch: CancellationException -> 0x00d5, TryCatch #0 {CancellationException -> 0x00d5, blocks: (B:13:0x0032, B:31:0x00a2, B:33:0x00aa, B:35:0x00b9, B:37:0x00c5, B:38:0x00c8, B:39:0x00cb, B:40:0x00cf, B:18:0x004b, B:24:0x0072, B:26:0x0076, B:28:0x0080, B:21:0x0055), top: B:46:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00c5 A[Catch: CancellationException -> 0x00d5, TryCatch #0 {CancellationException -> 0x00d5, blocks: (B:13:0x0032, B:31:0x00a2, B:33:0x00aa, B:35:0x00b9, B:37:0x00c5, B:38:0x00c8, B:39:0x00cb, B:40:0x00cf, B:18:0x004b, B:24:0x0072, B:26:0x0076, B:28:0x0080, B:21:0x0055), top: B:46:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00cf A[Catch: CancellationException -> 0x00d5, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00d5, blocks: (B:13:0x0032, B:31:0x00a2, B:33:0x00aa, B:35:0x00b9, B:37:0x00c5, B:38:0x00c8, B:39:0x00cb, B:40:0x00cf, B:18:0x004b, B:24:0x0072, B:26:0x0076, B:28:0x0080, B:21:0x0055), top: B:46:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object touchSelectionFirstPress(AwaitPointerEventScope awaitPointerEventScope, final TextDragObserver textDragObserver, PointerEvent pointerEvent, Continuation<? super Unit> continuation) {
        C03601 c03601;
        PointerInputChange pointerInputChange;
        List<PointerInputChange> changes;
        int size;
        int i;
        PointerInputChange pointerInputChange2;
        if (continuation instanceof C03601) {
            c03601 = (C03601) continuation;
            if ((c03601.label & Integer.MIN_VALUE) != 0) {
                c03601.label -= Integer.MIN_VALUE;
            } else {
                c03601 = new C03601(continuation);
            }
        } else {
            c03601 = new C03601(continuation);
        }
        Object objM414awaitLongPressOrCancellationrnUCldI = c03601.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c03601.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objM414awaitLongPressOrCancellationrnUCldI);
                pointerInputChange = (PointerInputChange) CollectionsKt.first((List) pointerEvent.getChanges());
                long id = pointerInputChange.getId();
                c03601.L$0 = awaitPointerEventScope;
                c03601.L$1 = textDragObserver;
                c03601.L$2 = pointerInputChange;
                c03601.label = 1;
                objM414awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m414awaitLongPressOrCancellationrnUCldI(awaitPointerEventScope, id, c03601);
                if (objM414awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 == 1) {
                    PointerInputChange pointerInputChange3 = (PointerInputChange) c03601.L$2;
                    textDragObserver = (TextDragObserver) c03601.L$1;
                    AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) c03601.L$0;
                    ResultKt.throwOnFailure(objM414awaitLongPressOrCancellationrnUCldI);
                    pointerInputChange = pointerInputChange3;
                    awaitPointerEventScope = awaitPointerEventScope2;
                } else {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    textDragObserver = (TextDragObserver) c03601.L$1;
                    awaitPointerEventScope = (AwaitPointerEventScope) c03601.L$0;
                    ResultKt.throwOnFailure(objM414awaitLongPressOrCancellationrnUCldI);
                }
                if (((Boolean) objM414awaitLongPressOrCancellationrnUCldI).booleanValue()) {
                    changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                    size = changes.size();
                    for (i = 0; i < size; i++) {
                        pointerInputChange2 = changes.get(i);
                        if (PointerEventKt.changedToUp(pointerInputChange2)) {
                            pointerInputChange2.consume();
                        }
                    }
                    textDragObserver.onStop();
                } else {
                    textDragObserver.onCancel();
                }
                return Unit.INSTANCE;
            }
            PointerInputChange pointerInputChange4 = (PointerInputChange) objM414awaitLongPressOrCancellationrnUCldI;
            if (pointerInputChange4 != null && distanceIsTolerable(awaitPointerEventScope.getViewConfiguration(), pointerInputChange, pointerInputChange4)) {
                textDragObserver.mo1060onStartk4lQ0M(pointerInputChange4.getPosition());
                long id2 = pointerInputChange4.getId();
                Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionFirstPress$dragCompletedWithUp$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange5) {
                        invoke2(pointerInputChange5);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(PointerInputChange pointerInputChange5) {
                        textDragObserver.mo1059onDragk4lQ0M(PointerEventKt.positionChange(pointerInputChange5));
                        pointerInputChange5.consume();
                    }
                };
                c03601.L$0 = awaitPointerEventScope;
                c03601.L$1 = textDragObserver;
                c03601.L$2 = null;
                c03601.label = 2;
                objM414awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m422dragjO51t88(awaitPointerEventScope, id2, function1, c03601);
                if (objM414awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) objM414awaitLongPressOrCancellationrnUCldI).booleanValue()) {
                    changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                    size = changes.size();
                    while (i < size) {
                        pointerInputChange2 = changes.get(i);
                        if (PointerEventKt.changedToUp(pointerInputChange2)) {
                            pointerInputChange2.consume();
                        }
                    }
                    textDragObserver.onStop();
                } else {
                    textDragObserver.onCancel();
                }
            }
            return Unit.INSTANCE;
        } catch (CancellationException e) {
            textDragObserver.onCancel();
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:45:0x00ea A[Catch: CancellationException -> 0x0115, TryCatch #1 {CancellationException -> 0x0115, blocks: (B:13:0x0033, B:43:0x00e2, B:45:0x00ea, B:47:0x00f9, B:49:0x0105, B:50:0x0108, B:51:0x010b, B:52:0x010f, B:26:0x00a3, B:28:0x00a7, B:29:0x00a9, B:31:0x00ad, B:33:0x00b0, B:35:0x00bb, B:37:0x00c1, B:39:0x00c5, B:40:0x00ca, B:23:0x0065), top: B:60:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x00f9 A[Catch: CancellationException -> 0x0115, TryCatch #1 {CancellationException -> 0x0115, blocks: (B:13:0x0033, B:43:0x00e2, B:45:0x00ea, B:47:0x00f9, B:49:0x0105, B:50:0x0108, B:51:0x010b, B:52:0x010f, B:26:0x00a3, B:28:0x00a7, B:29:0x00a9, B:31:0x00ad, B:33:0x00b0, B:35:0x00bb, B:37:0x00c1, B:39:0x00c5, B:40:0x00ca, B:23:0x0065), top: B:60:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x0105 A[Catch: CancellationException -> 0x0115, TryCatch #1 {CancellationException -> 0x0115, blocks: (B:13:0x0033, B:43:0x00e2, B:45:0x00ea, B:47:0x00f9, B:49:0x0105, B:50:0x0108, B:51:0x010b, B:52:0x010f, B:26:0x00a3, B:28:0x00a7, B:29:0x00a9, B:31:0x00ad, B:33:0x00b0, B:35:0x00bb, B:37:0x00c1, B:39:0x00c5, B:40:0x00ca, B:23:0x0065), top: B:60:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x010f A[Catch: CancellationException -> 0x0115, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x0115, blocks: (B:13:0x0033, B:43:0x00e2, B:45:0x00ea, B:47:0x00f9, B:49:0x0105, B:50:0x0108, B:51:0x010b, B:52:0x010f, B:26:0x00a3, B:28:0x00a7, B:29:0x00a9, B:31:0x00ad, B:33:0x00b0, B:35:0x00bb, B:37:0x00c1, B:39:0x00c5, B:40:0x00ca, B:23:0x0065), top: B:60:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0108 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object touchSelectionSubsequentPress(AwaitPointerEventScope awaitPointerEventScope, final TextDragObserver textDragObserver, PointerEvent pointerEvent, Continuation<? super Unit> continuation) {
        C03611 c03611;
        PointerInputChange pointerInputChange;
        long id;
        Ref.LongRef longRef;
        Object objWithTimeoutOrNull;
        List<PointerInputChange> changes;
        int size;
        int i;
        PointerInputChange pointerInputChange2;
        if (continuation instanceof C03611) {
            c03611 = (C03611) continuation;
            if ((c03611.label & Integer.MIN_VALUE) != 0) {
                c03611.label -= Integer.MIN_VALUE;
            } else {
                c03611 = new C03611(continuation);
            }
        } else {
            c03611 = new C03611(continuation);
        }
        Object objM422dragjO51t88 = c03611.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c03611.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objM422dragjO51t88);
                pointerInputChange = (PointerInputChange) CollectionsKt.first((List) pointerEvent.getChanges());
                id = pointerInputChange.getId();
                longRef = new Ref.LongRef();
                longRef.element = Offset.INSTANCE.m3614getUnspecifiedF1C5BW0();
                long longPressTimeoutMillis = awaitPointerEventScope.getViewConfiguration().getLongPressTimeoutMillis();
                SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 = new SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1(id, longRef, null);
                c03611.L$0 = awaitPointerEventScope;
                c03611.L$1 = textDragObserver;
                c03611.L$2 = pointerInputChange;
                c03611.L$3 = longRef;
                c03611.J$0 = id;
                c03611.label = 1;
                objWithTimeoutOrNull = awaitPointerEventScope.withTimeoutOrNull(longPressTimeoutMillis, selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1, c03611);
                if (objWithTimeoutOrNull == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 == 1) {
                    long j = c03611.J$0;
                    Ref.LongRef longRef2 = (Ref.LongRef) c03611.L$3;
                    pointerInputChange = (PointerInputChange) c03611.L$2;
                    TextDragObserver textDragObserver2 = (TextDragObserver) c03611.L$1;
                    AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) c03611.L$0;
                    try {
                        ResultKt.throwOnFailure(objM422dragjO51t88);
                        longRef = longRef2;
                        objWithTimeoutOrNull = objM422dragjO51t88;
                        id = j;
                        textDragObserver = textDragObserver2;
                        awaitPointerEventScope = awaitPointerEventScope2;
                    } catch (CancellationException e) {
                        e = e;
                        textDragObserver = textDragObserver2;
                        textDragObserver.onCancel();
                        throw e;
                    }
                } else {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    textDragObserver = (TextDragObserver) c03611.L$1;
                    awaitPointerEventScope = (AwaitPointerEventScope) c03611.L$0;
                    ResultKt.throwOnFailure(objM422dragjO51t88);
                }
                if (((Boolean) objM422dragjO51t88).booleanValue()) {
                    changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                    size = changes.size();
                    for (i = 0; i < size; i++) {
                        pointerInputChange2 = changes.get(i);
                        if (PointerEventKt.changedToUp(pointerInputChange2)) {
                            pointerInputChange2.consume();
                        }
                    }
                    textDragObserver.onStop();
                } else {
                    textDragObserver.onCancel();
                }
                return Unit.INSTANCE;
            }
            DownResolution downResolution = (DownResolution) objWithTimeoutOrNull;
            if (downResolution == null) {
                downResolution = DownResolution.Timeout;
            }
            if (downResolution == DownResolution.Cancel) {
                return Unit.INSTANCE;
            }
            textDragObserver.mo1060onStartk4lQ0M(pointerInputChange.getPosition());
            if (downResolution == DownResolution.Up) {
                textDragObserver.onStop();
                return Unit.INSTANCE;
            }
            if (downResolution == DownResolution.Drag) {
                textDragObserver.mo1059onDragk4lQ0M(longRef.element);
            }
            Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$dragCompletedWithUp$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange3) {
                    invoke2(pointerInputChange3);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PointerInputChange pointerInputChange3) {
                    textDragObserver.mo1059onDragk4lQ0M(PointerEventKt.positionChange(pointerInputChange3));
                    pointerInputChange3.consume();
                }
            };
            c03611.L$0 = awaitPointerEventScope;
            c03611.L$1 = textDragObserver;
            c03611.L$2 = null;
            c03611.L$3 = null;
            c03611.label = 2;
            objM422dragjO51t88 = DragGestureDetectorKt.m422dragjO51t88(awaitPointerEventScope, id, function1, c03611);
            if (objM422dragjO51t88 == coroutine_suspended) {
                return coroutine_suspended;
            }
            if (((Boolean) objM422dragjO51t88).booleanValue()) {
                changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                size = changes.size();
                while (i < size) {
                    pointerInputChange2 = changes.get(i);
                    if (PointerEventKt.changedToUp(pointerInputChange2)) {
                        pointerInputChange2.consume();
                    }
                }
                textDragObserver.onStop();
            } else {
                textDragObserver.onCancel();
            }
            return Unit.INSTANCE;
        } catch (CancellationException e2) {
            e = e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:33:0x0093 A[Catch: all -> 0x0051, TryCatch #0 {all -> 0x0051, blocks: (B:20:0x004d, B:31:0x008b, B:33:0x0093, B:35:0x00a1, B:37:0x00ad, B:28:0x0070), top: B:66:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00a1 A[Catch: all -> 0x0051, TryCatch #0 {all -> 0x0051, blocks: (B:20:0x004d, B:31:0x008b, B:33:0x0093, B:35:0x00a1, B:37:0x00ad, B:28:0x0070), top: B:66:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00ad A[Catch: all -> 0x0051, TRY_LEAVE, TryCatch #0 {all -> 0x0051, blocks: (B:20:0x004d, B:31:0x008b, B:33:0x0093, B:35:0x00a1, B:37:0x00ad, B:28:0x0070), top: B:66:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x0105 A[Catch: all -> 0x0038, TryCatch #1 {all -> 0x0038, blocks: (B:13:0x0033, B:53:0x00fd, B:55:0x0105, B:57:0x0113, B:59:0x011f, B:50:0x00e2), top: B:66:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x0113 A[Catch: all -> 0x0038, TryCatch #1 {all -> 0x0038, blocks: (B:13:0x0033, B:53:0x00fd, B:55:0x0105, B:57:0x0113, B:59:0x011f, B:50:0x00e2), top: B:66:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x011f A[Catch: all -> 0x0038, TRY_LEAVE, TryCatch #1 {all -> 0x0038, blocks: (B:13:0x0033, B:53:0x00fd, B:55:0x0105, B:57:0x0113, B:59:0x011f, B:50:0x00e2), top: B:66:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0122 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x00b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object mouseSelectionBtf2(AwaitPointerEventScope awaitPointerEventScope, final MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, PointerEvent pointerEvent, Continuation<? super Unit> continuation) {
        C03561 c03561;
        final SelectionAdjustment none;
        List<PointerInputChange> changes;
        int size;
        PointerInputChange pointerInputChange;
        List<PointerInputChange> changes2;
        int size2;
        PointerInputChange pointerInputChange2;
        if (continuation instanceof C03561) {
            c03561 = (C03561) continuation;
            if ((c03561.label & Integer.MIN_VALUE) != 0) {
                c03561.label -= Integer.MIN_VALUE;
            } else {
                c03561 = new C03561(continuation);
            }
        } else {
            c03561 = new C03561(continuation);
        }
        Object objM422dragjO51t88 = c03561.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c03561.label;
        int i2 = 0;
        try {
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(objM422dragjO51t88);
                    PointerInputChange pointerInputChange3 = pointerEvent.getChanges().get(0);
                    if (TextFieldSelectionManager_androidKt.isShiftPressed(pointerEvent)) {
                        if (mouseSelectionObserver.mo1273onExtendk4lQ0M(pointerInputChange3.getPosition())) {
                            pointerInputChange3.consume();
                            long id = pointerInputChange3.getId();
                            Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelectionBtf2$shouldConsumeUp$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange4) {
                                    invoke2(pointerInputChange4);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(PointerInputChange pointerInputChange4) {
                                    if (mouseSelectionObserver.mo1274onExtendDragk4lQ0M(pointerInputChange4.getPosition())) {
                                        pointerInputChange4.consume();
                                    }
                                }
                            };
                            c03561.L$0 = awaitPointerEventScope;
                            c03561.L$1 = mouseSelectionObserver;
                            c03561.label = 1;
                            objM422dragjO51t88 = DragGestureDetectorKt.m422dragjO51t88(awaitPointerEventScope, id, function1, c03561);
                            if (objM422dragjO51t88 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (((Boolean) objM422dragjO51t88).booleanValue()) {
                                changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                                size = changes.size();
                                while (i2 < size) {
                                    pointerInputChange = changes.get(i2);
                                    if (PointerEventKt.changedToUp(pointerInputChange)) {
                                        pointerInputChange.consume();
                                    }
                                    i2++;
                                }
                            }
                            mouseSelectionObserver.onDragDone();
                        }
                    } else {
                        int clicks = clicksCounter.getClicks();
                        if (clicks == 1) {
                            none = SelectionAdjustment.INSTANCE.getNone();
                        } else if (clicks == 2) {
                            none = SelectionAdjustment.INSTANCE.getWord();
                        } else {
                            none = SelectionAdjustment.INSTANCE.getParagraph();
                        }
                        if (mouseSelectionObserver.mo1275onStart3MmeM6k(pointerInputChange3.getPosition(), none)) {
                            pointerInputChange3.consume();
                            long id2 = pointerInputChange3.getId();
                            Function1<PointerInputChange, Unit> function2 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelectionBtf2$shouldConsumeUp$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange4) {
                                    invoke2(pointerInputChange4);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(PointerInputChange pointerInputChange4) {
                                    if (mouseSelectionObserver.mo1272onDrag3MmeM6k(pointerInputChange4.getPosition(), none)) {
                                        pointerInputChange4.consume();
                                    }
                                }
                            };
                            c03561.L$0 = awaitPointerEventScope;
                            c03561.L$1 = mouseSelectionObserver;
                            c03561.label = 2;
                            objM422dragjO51t88 = DragGestureDetectorKt.m422dragjO51t88(awaitPointerEventScope, id2, function2, c03561);
                            if (objM422dragjO51t88 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (((Boolean) objM422dragjO51t88).booleanValue()) {
                                changes2 = awaitPointerEventScope.getCurrentEvent().getChanges();
                                size2 = changes2.size();
                                while (i2 < size2) {
                                    pointerInputChange2 = changes2.get(i2);
                                    if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                        pointerInputChange2.consume();
                                    }
                                    i2++;
                                }
                            }
                            mouseSelectionObserver.onDragDone();
                        }
                    }
                } else if (i == 1) {
                    mouseSelectionObserver = (MouseSelectionObserver) c03561.L$1;
                    awaitPointerEventScope = (AwaitPointerEventScope) c03561.L$0;
                    ResultKt.throwOnFailure(objM422dragjO51t88);
                    if (((Boolean) objM422dragjO51t88).booleanValue()) {
                        changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                        size = changes.size();
                        while (i2 < size) {
                            pointerInputChange = changes.get(i2);
                            if (PointerEventKt.changedToUp(pointerInputChange)) {
                                pointerInputChange.consume();
                            }
                            i2++;
                        }
                    }
                    mouseSelectionObserver.onDragDone();
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    mouseSelectionObserver = (MouseSelectionObserver) c03561.L$1;
                    awaitPointerEventScope = (AwaitPointerEventScope) c03561.L$0;
                    ResultKt.throwOnFailure(objM422dragjO51t88);
                    if (((Boolean) objM422dragjO51t88).booleanValue()) {
                        changes2 = awaitPointerEventScope.getCurrentEvent().getChanges();
                        size2 = changes2.size();
                        while (i2 < size2) {
                            pointerInputChange2 = changes2.get(i2);
                            if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                pointerInputChange2.consume();
                            }
                            i2++;
                        }
                    }
                    mouseSelectionObserver.onDragDone();
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                mouseSelectionObserver.onDragDone();
                throw th;
            }
        } catch (Throwable th2) {
            mouseSelectionObserver.onDragDone();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0046 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x0054  */
    /* JADX WARN: Code duplicated, block: B:23:0x0061 A[LOOP:0: B:19:0x0052->B:23:0x0061, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:26:0x003a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0044 -> B:18:0x0047). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerEvent> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r0 = (androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r0 = new androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2e
            java.lang.Object r7 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r7 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L47
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L37:
            kotlin.ResultKt.throwOnFailure(r8)
        L3a:
            androidx.compose.ui.input.pointer.PointerEventPass r8 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r7.awaitPointerEvent(r8, r0)
            if (r8 != r1) goto L47
            return r1
        L47:
            androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
            java.util.List r2 = r8.getChanges()
            int r4 = r2.size()
            r5 = 0
        L52:
            if (r5 >= r4) goto L64
            java.lang.Object r6 = r2.get(r5)
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            boolean r6 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r6)
            if (r6 != 0) goto L61
            goto L3a
        L61:
            int r5 = r5 + 1
            goto L52
        L64:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean distanceIsTolerable(ViewConfiguration viewConfiguration, PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2) {
        return Offset.m3597getDistanceimpl(Offset.m3603minusMKHz9U(pointerInputChange.getPosition(), pointerInputChange2.getPosition())) < DragGestureDetectorKt.m425pointerSlopE8SPZFQ(viewConfiguration, pointerInputChange.getType());
    }

    public static final boolean isPrecisePointer(PointerEvent pointerEvent) {
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            if (!PointerType.m5118equalsimpl0(changes.get(i).getType(), PointerType.INSTANCE.m5123getMouseT8wyACA())) {
                return false;
            }
        }
        return true;
    }
}
