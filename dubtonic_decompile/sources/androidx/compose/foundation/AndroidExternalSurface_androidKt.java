package androidx.compose.foundation;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AndroidExternalSurface.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aU\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a]\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00052\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\r\u0010\u0016\u001a\u00020\u0017H\u0003¢\u0006\u0002\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\u001aH\u0003¢\u0006\u0002\u0010\u001b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"AndroidEmbeddedExternalSurface", "", "modifier", "Landroidx/compose/ui/Modifier;", "isOpaque", "", "surfaceSize", "Landroidx/compose/ui/unit/IntSize;", "transform", "Landroidx/compose/ui/graphics/Matrix;", "onInit", "Lkotlin/Function1;", "Landroidx/compose/foundation/AndroidExternalSurfaceScope;", "Lkotlin/ExtensionFunctionType;", "AndroidEmbeddedExternalSurface-sv6N_fY", "(Landroidx/compose/ui/Modifier;ZJ[FLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "AndroidExternalSurface", "zOrder", "Landroidx/compose/foundation/AndroidExternalSurfaceZOrder;", "isSecure", "AndroidExternalSurface-58FFMhA", "(Landroidx/compose/ui/Modifier;ZJIZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "rememberAndroidEmbeddedExternalSurfaceState", "Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "rememberAndroidExternalSurfaceState", "Landroidx/compose/foundation/AndroidExternalSurfaceState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidExternalSurfaceState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidExternalSurface_androidKt {
    private static final AndroidExternalSurfaceState rememberAndroidExternalSurfaceState(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -873615933, "C(rememberAndroidExternalSurfaceState)190@7150L24,191@7186L47:AndroidExternalSurface.android.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-873615933, i, -1, "androidx.compose.foundation.rememberAndroidExternalSurfaceState (AndroidExternalSurface.android.kt:189)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart(composer, -954367824, "CC(remember):Effects.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
            composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
            objRememberedValue = compositionScopedCoroutineScopeCanceller;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 1983762950, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new AndroidExternalSurfaceState(coroutineScope);
            composer.updateRememberedValue(objRememberedValue2);
        }
        AndroidExternalSurfaceState androidExternalSurfaceState = (AndroidExternalSurfaceState) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return androidExternalSurfaceState;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0140  */
    /* JADX WARN: Code duplicated, block: B:102:0x0143  */
    /* JADX WARN: Code duplicated, block: B:105:0x014f  */
    /* JADX WARN: Code duplicated, block: B:107:0x0157  */
    /* JADX WARN: Code duplicated, block: B:110:0x017b  */
    /* JADX WARN: Code duplicated, block: B:112:0x0181  */
    /* JADX WARN: Code duplicated, block: B:118:0x018f  */
    /* JADX WARN: Code duplicated, block: B:119:0x0192  */
    /* JADX WARN: Code duplicated, block: B:122:0x019a  */
    /* JADX WARN: Code duplicated, block: B:123:0x019d  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:129:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:134:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:138:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:140:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:33:0x0063  */
    /* JADX WARN: Code duplicated, block: B:36:0x0069  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:39:0x0070  */
    /* JADX WARN: Code duplicated, block: B:41:0x0078  */
    /* JADX WARN: Code duplicated, block: B:42:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x0086  */
    /* JADX WARN: Code duplicated, block: B:48:0x0089  */
    /* JADX WARN: Code duplicated, block: B:50:0x008d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0095  */
    /* JADX WARN: Code duplicated, block: B:53:0x0098  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:68:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:74:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:81:0x00ef A[PHI: r2 r3 r4 r12 r15
      0x00ef: PHI (r2v7 androidx.compose.ui.Modifier) = (r2v3 androidx.compose.ui.Modifier), (r2v9 androidx.compose.ui.Modifier) binds: [B:94:0x0118, B:80:0x00ed] A[DONT_GENERATE, DONT_INLINE]
      0x00ef: PHI (r3v11 boolean) = (r3v5 boolean), (r3v12 boolean) binds: [B:94:0x0118, B:80:0x00ed] A[DONT_GENERATE, DONT_INLINE]
      0x00ef: PHI (r4v20 int) = (r4v16 int), (r4v21 int) binds: [B:94:0x0118, B:80:0x00ed] A[DONT_GENERATE, DONT_INLINE]
      0x00ef: PHI (r12v19 long) = (r12v2 long), (r12v1 long) binds: [B:94:0x0118, B:80:0x00ed] A[DONT_GENERATE, DONT_INLINE]
      0x00ef: PHI (r15v7 int) = (r15v3 int), (r15v2 int) binds: [B:94:0x0118, B:80:0x00ed] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:83:0x00f5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:84:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:85:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:90:0x0105  */
    /* JADX WARN: Code duplicated, block: B:92:0x0110  */
    /* JADX WARN: Code duplicated, block: B:95:0x011a  */
    /* JADX WARN: Code duplicated, block: B:98:0x0126  */
    /* JADX WARN: Instruction removed from duplicated block: B:72:0x00d3, please report this as an issue */
    /* JADX INFO: renamed from: AndroidExternalSurface-58FFMhA, reason: not valid java name */
    public static final void m234AndroidExternalSurface58FFMhA(Modifier modifier, boolean z, long j, int i, boolean z2, final Function1<? super AndroidExternalSurfaceScope, Unit> function1, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        boolean z3;
        long jM6474getZeroYbymL2g;
        int i5;
        int iM230getBehindB_4ceCc;
        int i6;
        int i7;
        boolean z4;
        int i8;
        int i9;
        Modifier.Companion companion;
        boolean z5;
        boolean z6;
        final int i10;
        int i11;
        final long j2;
        final AndroidExternalSurfaceState androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
        boolean z7;
        boolean zChangedInstance;
        Object objRememberedValue;
        boolean z8;
        boolean z9;
        boolean z10;
        Object objRememberedValue2;
        final long j3;
        final int i12;
        final boolean z11;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(640888974);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AndroidExternalSurface)P(2!1,4:c#ui.unit.IntSize,5:c#foundation.AndroidExternalSurfaceZOrder)288@12167L37,291@12241L150,299@12462L774,290@12210L1032:AndroidExternalSurface.android.kt#71ulvw");
        int i14 = i3 & 1;
        if (i14 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        int i15 = i3 & 2;
        if (i15 == 0) {
            if ((i2 & 48) == 0) {
                z3 = z;
                i4 |= composerStartRestartGroup.changed(z3) ? 32 : 16;
            }
            if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                jM6474getZeroYbymL2g = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(jM6474getZeroYbymL2g)) {
                    i13 = 128;
                } else {
                    i13 = 256;
                }
                i4 |= i13;
            } else {
                jM6474getZeroYbymL2g = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    iM230getBehindB_4ceCc = i;
                    if (composerStartRestartGroup.changed(iM230getBehindB_4ceCc)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 16;
                if (i7 != 0) {
                    if ((i2 & 24576) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i4 |= i8;
                    }
                    if ((i3 & 32) != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i4 |= i9;
                    }
                    if ((i4 & 74899) == 74898 || !composerStartRestartGroup.getSkipping()) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i14 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier2;
                            }
                            if (i15 != 0) {
                                z3 = true;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                            }
                            if (i5 != 0) {
                                iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                            }
                            z5 = z3;
                            if (i7 != 0) {
                                z6 = false;
                            }
                            i10 = iM230getBehindB_4ceCc;
                            i11 = i4;
                            j2 = jM6474getZeroYbymL2g;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                            }
                            androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                            if ((458752 & i11) == 131072) {
                                z7 = true;
                            } else {
                                z7 = false;
                            }
                            zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (!zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final SurfaceView invoke(Context context) {
                                        SurfaceView surfaceView = new SurfaceView(context);
                                        Function1<AndroidExternalSurfaceScope, Unit> function2 = function1;
                                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                        function2.invoke(androidExternalSurfaceState);
                                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                        return surfaceView;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            Function1 function2 = (Function1) objRememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$2 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SurfaceView surfaceView) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                    invoke2(surfaceView);
                                    return Unit.INSTANCE;
                                }
                            };
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                            boolean z12 = (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 && composerStartRestartGroup.changed(j2)) || (i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256;
                            if ((i11 & 112) == 32) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            boolean z13 = z12 | z8;
                            if ((i11 & 7168) == 2048) {
                                z9 = true;
                            } else {
                                z9 = false;
                            }
                            z10 = z13 | z9 | ((57344 & i11) == 16384);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z10 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                final boolean z14 = z5;
                                final boolean z15 = z6;
                                objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                        invoke2(surfaceView);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SurfaceView surfaceView) {
                                        if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                            surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                        } else {
                                            surfaceView.getHolder().setSizeFromLayout();
                                        }
                                        surfaceView.getHolder().setFormat(z14 ? -1 : -3);
                                        int i16 = i10;
                                        if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                            surfaceView.setZOrderOnTop(false);
                                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                            surfaceView.setZOrderMediaOverlay(true);
                                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                            surfaceView.setZOrderOnTop(true);
                                        }
                                        surfaceView.setSecure(z15);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            AndroidView_androidKt.AndroidView(function2, companion, androidExternalSurface_androidKt$AndroidExternalSurface$2, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z3 = z5;
                            j3 = j2;
                            i12 = i10;
                            z11 = z6;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            companion = modifier2;
                            z5 = z3;
                        }
                        z6 = z4;
                        i10 = iM230getBehindB_4ceCc;
                        i11 = i4;
                        j2 = jM6474getZeroYbymL2g;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                        }
                        androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                        if ((458752 & i11) == 131072) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!zChangedInstance) {
                            objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final SurfaceView invoke(Context context) {
                                    SurfaceView surfaceView = new SurfaceView(context);
                                    Function1<AndroidExternalSurfaceScope, Unit> function3 = function1;
                                    AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                    function3.invoke(androidExternalSurfaceState);
                                    surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                    return surfaceView;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final SurfaceView invoke(Context context) {
                                    SurfaceView surfaceView = new SurfaceView(context);
                                    Function1<AndroidExternalSurfaceScope, Unit> function3 = function1;
                                    AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                    function3.invoke(androidExternalSurfaceState);
                                    surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                    return surfaceView;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Function1 function3 = (Function1) objRememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$3 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }
                        };
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                        if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                        }
                        if ((i11 & 112) == 32) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        boolean z16 = z12 | z8;
                        if ((i11 & 7168) == 2048) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        z10 = z16 | z9 | ((57344 & i11) == 16384);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z10) {
                            final boolean z17 = z5;
                            final boolean z18 = z6;
                            objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                    invoke2(surfaceView);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SurfaceView surfaceView) {
                                    if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                        surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                    } else {
                                        surfaceView.getHolder().setSizeFromLayout();
                                    }
                                    surfaceView.getHolder().setFormat(z17 ? -1 : -3);
                                    int i16 = i10;
                                    if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                        surfaceView.setZOrderOnTop(false);
                                    } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                        surfaceView.setZOrderMediaOverlay(true);
                                    } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                        surfaceView.setZOrderOnTop(true);
                                    }
                                    surfaceView.setSecure(z18);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            final boolean z19 = z5;
                            final boolean z110 = z6;
                            objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                    invoke2(surfaceView);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SurfaceView surfaceView) {
                                    if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                        surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                    } else {
                                        surfaceView.getHolder().setSizeFromLayout();
                                    }
                                    surfaceView.getHolder().setFormat(z19 ? -1 : -3);
                                    int i16 = i10;
                                    if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                        surfaceView.setZOrderOnTop(false);
                                    } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                        surfaceView.setZOrderMediaOverlay(true);
                                    } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                        surfaceView.setZOrderOnTop(true);
                                    }
                                    surfaceView.setSecure(z110);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        AndroidView_androidKt.AndroidView(function3, companion, androidExternalSurface_androidKt$AndroidExternalSurface$3, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z3 = z5;
                        j3 = j2;
                        i12 = i10;
                        z11 = z6;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        companion = modifier2;
                        j3 = jM6474getZeroYbymL2g;
                        z11 = z4;
                        i12 = iM230getBehindB_4ceCc;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier3 = companion;
                        final boolean z20 = z3;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i16) {
                                AndroidExternalSurface_androidKt.m234AndroidExternalSurface58FFMhA(modifier3, z20, j3, i12, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                    }
                }
                i4 |= 24576;
                z4 = z2;
                if ((i3 & 32) != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    } else {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    }
                    i10 = iM230getBehindB_4ceCc;
                    i11 = i4;
                    j2 = jM6474getZeroYbymL2g;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i11) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function4 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function4.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function4 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function4.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function4 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$4 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i11 & 112) == 32) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean z111 = z12 | z8;
                    if ((i11 & 7168) == 2048) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = z111 | z9 | ((57344 & i11) == 16384);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z10) {
                        final boolean z112 = z5;
                        final boolean z113 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z112 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z113);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        final boolean z114 = z5;
                        final boolean z115 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z114 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z115);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidView_androidKt.AndroidView(function4, companion, androidExternalSurface_androidKt$AndroidExternalSurface$4, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z5;
                    j3 = j2;
                    i12 = i10;
                    z11 = z6;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    } else {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    }
                    i10 = iM230getBehindB_4ceCc;
                    i11 = i4;
                    j2 = jM6474getZeroYbymL2g;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i11) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function5 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function5.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function5 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function5.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function5 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$5 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i11 & 112) == 32) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean z116 = z12 | z8;
                    if ((i11 & 7168) == 2048) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = z116 | z9 | ((57344 & i11) == 16384);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z10) {
                        final boolean z117 = z5;
                        final boolean z118 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z117 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z118);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        final boolean z119 = z5;
                        final boolean z1110 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z119 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z1110);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidView_androidKt.AndroidView(function5, companion, androidExternalSurface_androidKt$AndroidExternalSurface$5, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z5;
                    j3 = j2;
                    i12 = i10;
                    z11 = z6;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier4 = companion;
                    final boolean z21 = z3;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i16) {
                            AndroidExternalSurface_androidKt.m234AndroidExternalSurface58FFMhA(modifier4, z21, j3, i12, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 3072;
            iM230getBehindB_4ceCc = i;
            i7 = i3 & 16;
            if (i7 != 0) {
                if ((i2 & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i4 |= i8;
                }
                if ((i3 & 32) != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    } else {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    }
                    i10 = iM230getBehindB_4ceCc;
                    i11 = i4;
                    j2 = jM6474getZeroYbymL2g;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i11) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function6 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function6.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function6 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function6.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function6 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$6 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i11 & 112) == 32) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean z1111 = z12 | z8;
                    if ((i11 & 7168) == 2048) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = z1111 | z9 | ((57344 & i11) == 16384);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z10) {
                        final boolean z1112 = z5;
                        final boolean z1113 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z1112 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z1113);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        final boolean z1114 = z5;
                        final boolean z1115 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z1114 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z1115);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidView_androidKt.AndroidView(function6, companion, androidExternalSurface_androidKt$AndroidExternalSurface$6, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z5;
                    j3 = j2;
                    i12 = i10;
                    z11 = z6;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    } else {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    }
                    i10 = iM230getBehindB_4ceCc;
                    i11 = i4;
                    j2 = jM6474getZeroYbymL2g;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i11) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function7 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function7.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function7 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function7.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function7 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$7 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i11 & 112) == 32) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean z1116 = z12 | z8;
                    if ((i11 & 7168) == 2048) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = z1116 | z9 | ((57344 & i11) == 16384);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z10) {
                        final boolean z1117 = z5;
                        final boolean z1118 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z1117 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z1118);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        final boolean z1119 = z5;
                        final boolean z11110 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z1119 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z11110);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidView_androidKt.AndroidView(function7, companion, androidExternalSurface_androidKt$AndroidExternalSurface$7, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z5;
                    j3 = j2;
                    i12 = i10;
                    z11 = z6;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = companion;
                    final boolean z22 = z3;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i16) {
                            AndroidExternalSurface_androidKt.m234AndroidExternalSurface58FFMhA(modifier5, z22, j3, i12, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            z4 = z2;
            if ((i3 & 32) != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                } else {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                }
                i10 = iM230getBehindB_4ceCc;
                i11 = i4;
                j2 = jM6474getZeroYbymL2g;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i11) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function8 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function8.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function8 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function8.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function8 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$8 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i11 & 112) == 32) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z11111 = z12 | z8;
                if ((i11 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = z11111 | z9 | ((57344 & i11) == 16384);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z10) {
                    final boolean z11112 = z5;
                    final boolean z11113 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11112 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11113);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final boolean z11114 = z5;
                    final boolean z11115 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11114 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11115);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidView_androidKt.AndroidView(function8, companion, androidExternalSurface_androidKt$AndroidExternalSurface$8, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z5;
                j3 = j2;
                i12 = i10;
                z11 = z6;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                } else {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                }
                i10 = iM230getBehindB_4ceCc;
                i11 = i4;
                j2 = jM6474getZeroYbymL2g;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i11) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function9 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function9.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function9 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function9.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function9 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$9 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i11 & 112) == 32) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z11116 = z12 | z8;
                if ((i11 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = z11116 | z9 | ((57344 & i11) == 16384);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z10) {
                    final boolean z11117 = z5;
                    final boolean z11118 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11117 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11118);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final boolean z11119 = z5;
                    final boolean z111110 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11119 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z111110);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidView_androidKt.AndroidView(function9, companion, androidExternalSurface_androidKt$AndroidExternalSurface$9, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z5;
                j3 = j2;
                i12 = i10;
                z11 = z6;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = companion;
                final boolean z23 = z3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i16) {
                        AndroidExternalSurface_androidKt.m234AndroidExternalSurface58FFMhA(modifier6, z23, j3, i12, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 48;
        z3 = z;
        if ((i2 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            jM6474getZeroYbymL2g = j;
            if ((i3 & 4) == 0) {
                i13 = 128;
            } else {
                i13 = 128;
            }
            i4 |= i13;
        } else {
            jM6474getZeroYbymL2g = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                iM230getBehindB_4ceCc = i;
                if (composerStartRestartGroup.changed(iM230getBehindB_4ceCc)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            i7 = i3 & 16;
            if (i7 != 0) {
                if ((i2 & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i4 |= i8;
                }
                if ((i3 & 32) != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                if ((i4 & 74899) == 74898) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    } else {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    }
                    i10 = iM230getBehindB_4ceCc;
                    i11 = i4;
                    j2 = jM6474getZeroYbymL2g;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i11) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function10 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function10.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function10 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function10.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function10 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$10 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i11 & 112) == 32) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean z111111 = z12 | z8;
                    if ((i11 & 7168) == 2048) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = z111111 | z9 | ((57344 & i11) == 16384);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z10) {
                        final boolean z111112 = z5;
                        final boolean z111113 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z111112 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z111113);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        final boolean z111114 = z5;
                        final boolean z111115 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z111114 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z111115);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidView_androidKt.AndroidView(function10, companion, androidExternalSurface_androidKt$AndroidExternalSurface$10, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z5;
                    j3 = j2;
                    i12 = i10;
                    z11 = z6;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    } else {
                        if (i14 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier2;
                        }
                        if (i15 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        }
                        if (i5 != 0) {
                            iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                        }
                        z5 = z3;
                        if (i7 != 0) {
                            z6 = false;
                        } else {
                            z6 = z4;
                        }
                    }
                    i10 = iM230getBehindB_4ceCc;
                    i11 = i4;
                    j2 = jM6474getZeroYbymL2g;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if ((458752 & i11) == 131072) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance) {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function11 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function11.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final SurfaceView invoke(Context context) {
                                SurfaceView surfaceView = new SurfaceView(context);
                                Function1<AndroidExternalSurfaceScope, Unit> function11 = function1;
                                AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                                function11.invoke(androidExternalSurfaceState);
                                surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                                return surfaceView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function11 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$11 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                    }
                    if ((i11 & 112) == 32) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    boolean z111116 = z12 | z8;
                    if ((i11 & 7168) == 2048) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    z10 = z111116 | z9 | ((57344 & i11) == 16384);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z10) {
                        final boolean z111117 = z5;
                        final boolean z111118 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z111117 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z111118);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        final boolean z111119 = z5;
                        final boolean z1111110 = z6;
                        objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                                invoke2(surfaceView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SurfaceView surfaceView) {
                                if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                    surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                                } else {
                                    surfaceView.getHolder().setSizeFromLayout();
                                }
                                surfaceView.getHolder().setFormat(z111119 ? -1 : -3);
                                int i16 = i10;
                                if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(false);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                    surfaceView.setZOrderMediaOverlay(true);
                                } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                    surfaceView.setZOrderOnTop(true);
                                }
                                surfaceView.setSecure(z1111110);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidView_androidKt.AndroidView(function11, companion, androidExternalSurface_androidKt$AndroidExternalSurface$11, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z3 = z5;
                    j3 = j2;
                    i12 = i10;
                    z11 = z6;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier7 = companion;
                    final boolean z24 = z3;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i16) {
                            AndroidExternalSurface_androidKt.m234AndroidExternalSurface58FFMhA(modifier7, z24, j3, i12, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                }
            }
            i4 |= 24576;
            z4 = z2;
            if ((i3 & 32) != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                } else {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                }
                i10 = iM230getBehindB_4ceCc;
                i11 = i4;
                j2 = jM6474getZeroYbymL2g;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i11) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function12 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function12.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function12 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function12.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function12 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$12 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i11 & 112) == 32) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z1111111 = z12 | z8;
                if ((i11 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = z1111111 | z9 | ((57344 & i11) == 16384);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z10) {
                    final boolean z1111112 = z5;
                    final boolean z1111113 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1111112 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z1111113);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final boolean z1111114 = z5;
                    final boolean z1111115 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1111114 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z1111115);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidView_androidKt.AndroidView(function12, companion, androidExternalSurface_androidKt$AndroidExternalSurface$12, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z5;
                j3 = j2;
                i12 = i10;
                z11 = z6;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                } else {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                }
                i10 = iM230getBehindB_4ceCc;
                i11 = i4;
                j2 = jM6474getZeroYbymL2g;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i11) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function13 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function13.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function13 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function13.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function13 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$13 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i11 & 112) == 32) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z1111116 = z12 | z8;
                if ((i11 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = z1111116 | z9 | ((57344 & i11) == 16384);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z10) {
                    final boolean z1111117 = z5;
                    final boolean z1111118 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1111117 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z1111118);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final boolean z1111119 = z5;
                    final boolean z11111110 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z1111119 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11111110);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidView_androidKt.AndroidView(function13, companion, androidExternalSurface_androidKt$AndroidExternalSurface$13, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z5;
                j3 = j2;
                i12 = i10;
                z11 = z6;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier8 = companion;
                final boolean z25 = z3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i16) {
                        AndroidExternalSurface_androidKt.m234AndroidExternalSurface58FFMhA(modifier8, z25, j3, i12, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 3072;
        iM230getBehindB_4ceCc = i;
        i7 = i3 & 16;
        if (i7 != 0) {
            if ((i2 & 24576) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i4 |= i8;
            }
            if ((i3 & 32) != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            if ((i4 & 74899) == 74898) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                } else {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                }
                i10 = iM230getBehindB_4ceCc;
                i11 = i4;
                j2 = jM6474getZeroYbymL2g;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i11) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function14 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function14.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function14 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function14.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function14 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$14 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i11 & 112) == 32) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z11111111 = z12 | z8;
                if ((i11 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = z11111111 | z9 | ((57344 & i11) == 16384);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z10) {
                    final boolean z11111112 = z5;
                    final boolean z11111113 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11111112 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11111113);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final boolean z11111114 = z5;
                    final boolean z11111115 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11111114 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11111115);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidView_androidKt.AndroidView(function14, companion, androidExternalSurface_androidKt$AndroidExternalSurface$14, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z5;
                j3 = j2;
                i12 = i10;
                z11 = z6;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                } else {
                    if (i14 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i15 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    }
                    if (i5 != 0) {
                        iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                    }
                    z5 = z3;
                    if (i7 != 0) {
                        z6 = false;
                    } else {
                        z6 = z4;
                    }
                }
                i10 = iM230getBehindB_4ceCc;
                i11 = i4;
                j2 = jM6474getZeroYbymL2g;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if ((458752 & i11) == 131072) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function15 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function15.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final SurfaceView invoke(Context context) {
                            SurfaceView surfaceView = new SurfaceView(context);
                            Function1<AndroidExternalSurfaceScope, Unit> function15 = function1;
                            AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                            function15.invoke(androidExternalSurfaceState);
                            surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                            return surfaceView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function15 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$15 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
                }
                if ((i11 & 112) == 32) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                boolean z11111116 = z12 | z8;
                if ((i11 & 7168) == 2048) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                z10 = z11111116 | z9 | ((57344 & i11) == 16384);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z10) {
                    final boolean z11111117 = z5;
                    final boolean z11111118 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11111117 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z11111118);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final boolean z11111119 = z5;
                    final boolean z111111110 = z6;
                    objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                            invoke2(surfaceView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SurfaceView surfaceView) {
                            if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                                surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                            } else {
                                surfaceView.getHolder().setSizeFromLayout();
                            }
                            surfaceView.getHolder().setFormat(z11111119 ? -1 : -3);
                            int i16 = i10;
                            if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                                surfaceView.setZOrderOnTop(false);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                                surfaceView.setZOrderMediaOverlay(true);
                            } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                                surfaceView.setZOrderOnTop(true);
                            }
                            surfaceView.setSecure(z111111110);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidView_androidKt.AndroidView(function15, companion, androidExternalSurface_androidKt$AndroidExternalSurface$15, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z3 = z5;
                j3 = j2;
                i12 = i10;
                z11 = z6;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier9 = companion;
                final boolean z26 = z3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i16) {
                        AndroidExternalSurface_androidKt.m234AndroidExternalSurface58FFMhA(modifier9, z26, j3, i12, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
            }
        }
        i4 |= 24576;
        z4 = z2;
        if ((i3 & 32) != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i9 = 131072;
            } else {
                i9 = 65536;
            }
            i4 |= i9;
        }
        if ((i4 & 74899) == 74898) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i15 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                }
                if (i5 != 0) {
                    iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                }
                z5 = z3;
                if (i7 != 0) {
                    z6 = false;
                } else {
                    z6 = z4;
                }
            } else {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i15 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                }
                if (i5 != 0) {
                    iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                }
                z5 = z3;
                if (i7 != 0) {
                    z6 = false;
                } else {
                    z6 = z4;
                }
            }
            i10 = iM230getBehindB_4ceCc;
            i11 = i4;
            j2 = jM6474getZeroYbymL2g;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
            }
            androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            if ((458752 & i11) == 131072) {
                z7 = true;
            } else {
                z7 = false;
            }
            zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance) {
                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final SurfaceView invoke(Context context) {
                        SurfaceView surfaceView = new SurfaceView(context);
                        Function1<AndroidExternalSurfaceScope, Unit> function16 = function1;
                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                        function16.invoke(androidExternalSurfaceState);
                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                        return surfaceView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final SurfaceView invoke(Context context) {
                        SurfaceView surfaceView = new SurfaceView(context);
                        Function1<AndroidExternalSurfaceScope, Unit> function16 = function1;
                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                        function16.invoke(androidExternalSurfaceState);
                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                        return surfaceView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function16 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$16 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SurfaceView surfaceView) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                    invoke2(surfaceView);
                    return Unit.INSTANCE;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
            }
            if ((i11 & 112) == 32) {
                z8 = true;
            } else {
                z8 = false;
            }
            boolean z111111111 = z12 | z8;
            if ((i11 & 7168) == 2048) {
                z9 = true;
            } else {
                z9 = false;
            }
            z10 = z111111111 | z9 | ((57344 & i11) == 16384);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z10) {
                final boolean z111111112 = z5;
                final boolean z111111113 = z6;
                objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                        if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                            surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                        } else {
                            surfaceView.getHolder().setSizeFromLayout();
                        }
                        surfaceView.getHolder().setFormat(z111111112 ? -1 : -3);
                        int i16 = i10;
                        if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                            surfaceView.setZOrderOnTop(false);
                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                            surfaceView.setZOrderMediaOverlay(true);
                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                            surfaceView.setZOrderOnTop(true);
                        }
                        surfaceView.setSecure(z111111113);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                final boolean z111111114 = z5;
                final boolean z111111115 = z6;
                objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                        if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                            surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                        } else {
                            surfaceView.getHolder().setSizeFromLayout();
                        }
                        surfaceView.getHolder().setFormat(z111111114 ? -1 : -3);
                        int i16 = i10;
                        if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                            surfaceView.setZOrderOnTop(false);
                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                            surfaceView.setZOrderMediaOverlay(true);
                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                            surfaceView.setZOrderOnTop(true);
                        }
                        surfaceView.setSecure(z111111115);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidView_androidKt.AndroidView(function16, companion, androidExternalSurface_androidKt$AndroidExternalSurface$16, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z3 = z5;
            j3 = j2;
            i12 = i10;
            z11 = z6;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i15 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                }
                if (i5 != 0) {
                    iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                }
                z5 = z3;
                if (i7 != 0) {
                    z6 = false;
                } else {
                    z6 = z4;
                }
            } else {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i15 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                }
                if (i5 != 0) {
                    iM230getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc();
                }
                z5 = z3;
                if (i7 != 0) {
                    z6 = false;
                } else {
                    z6 = z4;
                }
            }
            i10 = iM230getBehindB_4ceCc;
            i11 = i4;
            j2 = jM6474getZeroYbymL2g;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(640888974, i11, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:287)");
            }
            androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356109309, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            if ((458752 & i11) == 131072) {
                z7 = true;
            } else {
                z7 = false;
            }
            zChangedInstance = z7 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance) {
                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final SurfaceView invoke(Context context) {
                        SurfaceView surfaceView = new SurfaceView(context);
                        Function1<AndroidExternalSurfaceScope, Unit> function17 = function1;
                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                        function17.invoke(androidExternalSurfaceState);
                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                        return surfaceView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function1) new Function1<Context, SurfaceView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final SurfaceView invoke(Context context) {
                        SurfaceView surfaceView = new SurfaceView(context);
                        Function1<AndroidExternalSurfaceScope, Unit> function17 = function1;
                        AndroidExternalSurfaceState androidExternalSurfaceState = androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
                        function17.invoke(androidExternalSurfaceState);
                        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
                        return surfaceView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function17 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidExternalSurface_androidKt$AndroidExternalSurface$2 androidExternalSurface_androidKt$AndroidExternalSurface$17 = new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SurfaceView surfaceView) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                    invoke2(surfaceView);
                    return Unit.INSTANCE;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1356101613, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            if (((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256) {
            }
            if ((i11 & 112) == 32) {
                z8 = true;
            } else {
                z8 = false;
            }
            boolean z111111116 = z12 | z8;
            if ((i11 & 7168) == 2048) {
                z9 = true;
            } else {
                z9 = false;
            }
            z10 = z111111116 | z9 | ((57344 & i11) == 16384);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!z10) {
                final boolean z111111117 = z5;
                final boolean z111111118 = z6;
                objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                        if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                            surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                        } else {
                            surfaceView.getHolder().setSizeFromLayout();
                        }
                        surfaceView.getHolder().setFormat(z111111117 ? -1 : -3);
                        int i16 = i10;
                        if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                            surfaceView.setZOrderOnTop(false);
                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                            surfaceView.setZOrderMediaOverlay(true);
                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                            surfaceView.setZOrderOnTop(true);
                        }
                        surfaceView.setSecure(z111111118);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                final boolean z111111119 = z5;
                final boolean z1111111110 = z6;
                objRememberedValue2 = (Function1) new Function1<SurfaceView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SurfaceView surfaceView) {
                        invoke2(surfaceView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SurfaceView surfaceView) {
                        if (!IntSize.m6467equalsimpl0(j2, IntSize.INSTANCE.m6474getZeroYbymL2g())) {
                            surfaceView.getHolder().setFixedSize(IntSize.m6469getWidthimpl(j2), IntSize.m6468getHeightimpl(j2));
                        } else {
                            surfaceView.getHolder().setSizeFromLayout();
                        }
                        surfaceView.getHolder().setFormat(z111111119 ? -1 : -3);
                        int i16 = i10;
                        if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m230getBehindB_4ceCc())) {
                            surfaceView.setZOrderOnTop(false);
                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m231getMediaOverlayB_4ceCc())) {
                            surfaceView.setZOrderMediaOverlay(true);
                        } else if (AndroidExternalSurfaceZOrder.m226equalsimpl0(i16, AndroidExternalSurfaceZOrder.INSTANCE.m232getOnTopB_4ceCc())) {
                            surfaceView.setZOrderOnTop(true);
                        }
                        surfaceView.setSecure(z1111111110);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidView_androidKt.AndroidView(function17, companion, androidExternalSurface_androidKt$AndroidExternalSurface$17, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i11 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z3 = z5;
            j3 = j2;
            i12 = i10;
            z11 = z6;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier10 = companion;
            final boolean z27 = z3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidExternalSurface$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i16) {
                    AndroidExternalSurface_androidKt.m234AndroidExternalSurface58FFMhA(modifier10, z27, j3, i12, z11, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                }
            });
        }
    }

    private static final AndroidEmbeddedExternalSurfaceState rememberAndroidEmbeddedExternalSurfaceState(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1057437053, "C(rememberAndroidEmbeddedExternalSurfaceState)384@15036L24,385@15072L55:AndroidExternalSurface.android.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1057437053, i, -1, "androidx.compose.foundation.rememberAndroidEmbeddedExternalSurfaceState (AndroidExternalSurface.android.kt:383)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart(composer, -954367824, "CC(remember):Effects.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
            composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
            objRememberedValue = compositionScopedCoroutineScopeCanceller;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 1142294264, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new AndroidEmbeddedExternalSurfaceState(coroutineScope);
            composer.updateRememberedValue(objRememberedValue2);
        }
        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = (AndroidEmbeddedExternalSurfaceState) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return androidEmbeddedExternalSurfaceState;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0139  */
    /* JADX WARN: Code duplicated, block: B:103:0x0141  */
    /* JADX WARN: Code duplicated, block: B:106:0x015e  */
    /* JADX WARN: Code duplicated, block: B:108:0x0164  */
    /* JADX WARN: Code duplicated, block: B:115:0x0177  */
    /* JADX WARN: Code duplicated, block: B:118:0x017b  */
    /* JADX WARN: Code duplicated, block: B:119:0x0180  */
    /* JADX WARN: Code duplicated, block: B:122:0x018c  */
    /* JADX WARN: Code duplicated, block: B:124:0x0194  */
    /* JADX WARN: Code duplicated, block: B:127:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:131:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:133:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0052  */
    /* JADX WARN: Code duplicated, block: B:31:0x0060  */
    /* JADX WARN: Code duplicated, block: B:33:0x0064  */
    /* JADX WARN: Code duplicated, block: B:36:0x006a  */
    /* JADX WARN: Code duplicated, block: B:37:0x006d  */
    /* JADX WARN: Code duplicated, block: B:39:0x0071 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x0073  */
    /* JADX WARN: Code duplicated, block: B:41:0x0078  */
    /* JADX WARN: Code duplicated, block: B:44:0x007f  */
    /* JADX WARN: Code duplicated, block: B:45:0x0082  */
    /* JADX WARN: Code duplicated, block: B:49:0x008b  */
    /* JADX WARN: Code duplicated, block: B:50:0x008e  */
    /* JADX WARN: Code duplicated, block: B:52:0x0092  */
    /* JADX WARN: Code duplicated, block: B:54:0x0098  */
    /* JADX WARN: Code duplicated, block: B:55:0x009a  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:65:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:77:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:82:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:89:0x011b  */
    /* JADX WARN: Code duplicated, block: B:91:0x0121  */
    /* JADX WARN: Code duplicated, block: B:97:0x012f  */
    /* JADX WARN: Code duplicated, block: B:98:0x0131  */
    /* JADX WARN: Instruction removed from duplicated block: B:63:0x00b3, please report this as an issue */
    /* JADX INFO: renamed from: AndroidEmbeddedExternalSurface-sv6N_fY, reason: not valid java name */
    public static final void m233AndroidEmbeddedExternalSurfacesv6N_fY(Modifier modifier, boolean z, long j, float[] fArr, final Function1<? super AndroidExternalSurfaceScope, Unit> function1, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        boolean z2;
        long jM6474getZeroYbymL2g;
        int i4;
        Matrix matrixM4069boximpl;
        int i5;
        int i6;
        Modifier.Companion companion;
        int i7;
        boolean z3;
        final long j2;
        final AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
        boolean z4;
        boolean z5;
        Object objRememberedValue;
        Matrix matrixM4069boximpl2;
        boolean zChangedInstance;
        Object objRememberedValue2;
        final long j3;
        final float[] fArr2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i8;
        Composer composerStartRestartGroup = composer.startRestartGroup(217541314);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AndroidEmbeddedExternalSurface)P(1!1,3:c#ui.unit.IntSize,4:c#ui.graphics.Matrix)455@18917L45,458@18999L203,467@19273L485,457@18968L796:AndroidExternalSurface.android.kt#71ulvw");
        int i9 = i2 & 1;
        if (i9 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i10 = i2 & 2;
        if (i10 == 0) {
            if ((i & 48) == 0) {
                z2 = z;
                i3 |= composerStartRestartGroup.changed(z2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                jM6474getZeroYbymL2g = j;
                if ((i2 & 4) == 0 || !composerStartRestartGroup.changed(jM6474getZeroYbymL2g)) {
                    i8 = 128;
                } else {
                    i8 = 256;
                }
                i3 |= i8;
            } else {
                jM6474getZeroYbymL2g = j;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                if (fArr != null) {
                    matrixM4069boximpl = Matrix.m4069boximpl(fArr);
                } else {
                    matrixM4069boximpl = null;
                }
                if (composerStartRestartGroup.changedInstance(matrixM4069boximpl)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            if ((i3 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i9 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier2;
                    }
                    if (i10 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                        i3 &= -897;
                    }
                    if (i4 != 0) {
                        i7 = i3;
                        z3 = z2;
                        j2 = jM6474getZeroYbymL2g;
                        fArr = null;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(217541314, i7, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
                    }
                    androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184051342, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                    int i11 = (i7 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK;
                    boolean z6 = ((i11 <= 256 && composerStartRestartGroup.changed(j2)) || (i7 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | zChangedInstance2;
                    if ((57344 & i7) == 16384) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = z6 | z4;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = (Function1) new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final TextureView invoke(Context context) {
                                TextureView textureView = new TextureView(context);
                                AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                                long j4 = j2;
                                Function1<AndroidExternalSurfaceScope, Unit> function2 = function1;
                                androidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j4);
                                function2.invoke(androidEmbeddedExternalSurfaceState);
                                textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
                                return textureView;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function2 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184060392, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                    boolean zChangedInstance3 = ((i11 <= 256 && composerStartRestartGroup.changed(j2)) || (i7 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState) | ((i7 & 112) == 32);
                    if (fArr != null) {
                        matrixM4069boximpl2 = Matrix.m4069boximpl(fArr);
                    } else {
                        matrixM4069boximpl2 = null;
                    }
                    zChangedInstance = zChangedInstance3 | composerStartRestartGroup.changedInstance(matrixM4069boximpl2);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        final long j4 = j2;
                        final boolean z7 = z3;
                        final float[] fArr3 = fArr;
                        objRememberedValue2 = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                                invoke2(textureView);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextureView textureView) {
                                android.graphics.Matrix matrix;
                                SurfaceTexture surfaceTexture;
                                if (!IntSize.m6467equalsimpl0(j4, IntSize.INSTANCE.m6474getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                    surfaceTexture.setDefaultBufferSize(IntSize.m6469getWidthimpl(j4), IntSize.m6468getHeightimpl(j4));
                                }
                                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j4);
                                textureView.setOpaque(z7);
                                float[] fArr4 = fArr3;
                                if (fArr4 != null) {
                                    matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                    AndroidMatrixConversions_androidKt.m3710setFromEL8BTi8(matrix, fArr4);
                                } else {
                                    matrix = null;
                                }
                                textureView.setTransform(matrix);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AndroidView_androidKt.AndroidView(function2, companion, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i7 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z2 = z3;
                    j3 = j2;
                    fArr2 = fArr;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    companion = modifier2;
                }
                i7 = i3;
                z3 = z2;
                j2 = jM6474getZeroYbymL2g;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(217541314, i7, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
                }
                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184051342, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                int i12 = (i7 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK;
                boolean z8 = ((i12 <= 256 && composerStartRestartGroup.changed(j2)) || (i7 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | zChangedInstance4;
                if ((57344 & i7) == 16384) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z8 | z4;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z5) {
                    objRememberedValue = (Function1) new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final TextureView invoke(Context context) {
                            TextureView textureView = new TextureView(context);
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            long j5 = j2;
                            Function1<AndroidExternalSurfaceScope, Unit> function3 = function1;
                            androidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j5);
                            function3.invoke(androidEmbeddedExternalSurfaceState);
                            textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
                            return textureView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = (Function1) new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final TextureView invoke(Context context) {
                            TextureView textureView = new TextureView(context);
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                            long j5 = j2;
                            Function1<AndroidExternalSurfaceScope, Unit> function3 = function1;
                            androidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j5);
                            function3.invoke(androidEmbeddedExternalSurfaceState);
                            textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
                            return textureView;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function3 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }
                };
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184060392, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
                boolean zChangedInstance5 = ((i12 <= 256 && composerStartRestartGroup.changed(j2)) || (i7 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState) | ((i7 & 112) == 32);
                if (fArr != null) {
                    matrixM4069boximpl2 = Matrix.m4069boximpl(fArr);
                } else {
                    matrixM4069boximpl2 = null;
                }
                zChangedInstance = zChangedInstance5 | composerStartRestartGroup.changedInstance(matrixM4069boximpl2);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    final long j5 = j2;
                    final boolean z9 = z3;
                    final float[] fArr4 = fArr;
                    objRememberedValue2 = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m6467equalsimpl0(j5, IntSize.INSTANCE.m6474getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m6469getWidthimpl(j5), IntSize.m6468getHeightimpl(j5));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j5);
                            textureView.setOpaque(z9);
                            float[] fArr5 = fArr4;
                            if (fArr5 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3710setFromEL8BTi8(matrix, fArr5);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    final long j6 = j2;
                    final boolean z10 = z3;
                    final float[] fArr5 = fArr;
                    objRememberedValue2 = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                            invoke2(textureView);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextureView textureView) {
                            android.graphics.Matrix matrix;
                            SurfaceTexture surfaceTexture;
                            if (!IntSize.m6467equalsimpl0(j6, IntSize.INSTANCE.m6474getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                                surfaceTexture.setDefaultBufferSize(IntSize.m6469getWidthimpl(j6), IntSize.m6468getHeightimpl(j6));
                            }
                            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j6);
                            textureView.setOpaque(z10);
                            float[] fArr6 = fArr5;
                            if (fArr6 != null) {
                                matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                                AndroidMatrixConversions_androidKt.m3710setFromEL8BTi8(matrix, fArr6);
                            } else {
                                matrix = null;
                            }
                            textureView.setTransform(matrix);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                AndroidView_androidKt.AndroidView(function3, companion, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i7 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z2 = z3;
                j3 = j2;
                fArr2 = fArr;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                fArr2 = fArr;
                companion = modifier2;
                j3 = jM6474getZeroYbymL2g;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier3 = companion;
                final boolean z11 = z2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i13) {
                        AndroidExternalSurface_androidKt.m233AndroidEmbeddedExternalSurfacesv6N_fY(modifier3, z11, j3, fArr2, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 48;
        z2 = z;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            jM6474getZeroYbymL2g = j;
            if ((i2 & 4) == 0) {
                i8 = 128;
            } else {
                i8 = 128;
            }
            i3 |= i8;
        } else {
            jM6474getZeroYbymL2g = j;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            if (fArr != null) {
                matrixM4069boximpl = Matrix.m4069boximpl(fArr);
            } else {
                matrixM4069boximpl = null;
            }
            if (composerStartRestartGroup.changedInstance(matrixM4069boximpl)) {
                i5 = 2048;
            } else {
                i5 = 1024;
            }
            i3 |= i5;
        }
        if ((i2 & 16) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i6 = 16384;
            } else {
                i6 = 8192;
            }
            i3 |= i6;
        }
        if ((i3 & 9363) == 9362) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i10 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    i3 &= -897;
                }
                if (i4 != 0) {
                    i7 = i3;
                    z3 = z2;
                    j2 = jM6474getZeroYbymL2g;
                    fArr = null;
                } else {
                    i7 = i3;
                    z3 = z2;
                    j2 = jM6474getZeroYbymL2g;
                }
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i10 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    i3 &= -897;
                }
                if (i4 != 0) {
                    i7 = i3;
                    z3 = z2;
                    j2 = jM6474getZeroYbymL2g;
                    fArr = null;
                } else {
                    i7 = i3;
                    z3 = z2;
                    j2 = jM6474getZeroYbymL2g;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(217541314, i7, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
            }
            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184051342, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
            int i13 = (i7 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK;
            boolean z12 = ((i13 <= 256 && composerStartRestartGroup.changed(j2)) || (i7 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | zChangedInstance6;
            if ((57344 & i7) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            z5 = z12 | z4;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z5) {
                objRememberedValue = (Function1) new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        TextureView textureView = new TextureView(context);
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                        long j7 = j2;
                        Function1<AndroidExternalSurfaceScope, Unit> function4 = function1;
                        androidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j7);
                        function4.invoke(androidEmbeddedExternalSurfaceState);
                        textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
                        return textureView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function1) new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        TextureView textureView = new TextureView(context);
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                        long j7 = j2;
                        Function1<AndroidExternalSurfaceScope, Unit> function4 = function1;
                        androidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j7);
                        function4.invoke(androidEmbeddedExternalSurfaceState);
                        textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
                        return textureView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function4 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextureView textureView) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                    invoke2(textureView);
                    return Unit.INSTANCE;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184060392, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            boolean zChangedInstance7 = ((i13 <= 256 && composerStartRestartGroup.changed(j2)) || (i7 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState) | ((i7 & 112) == 32);
            if (fArr != null) {
                matrixM4069boximpl2 = Matrix.m4069boximpl(fArr);
            } else {
                matrixM4069boximpl2 = null;
            }
            zChangedInstance = zChangedInstance7 | composerStartRestartGroup.changedInstance(matrixM4069boximpl2);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance) {
                final long j7 = j2;
                final boolean z13 = z3;
                final float[] fArr6 = fArr;
                objRememberedValue2 = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                        android.graphics.Matrix matrix;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m6467equalsimpl0(j7, IntSize.INSTANCE.m6474getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m6469getWidthimpl(j7), IntSize.m6468getHeightimpl(j7));
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j7);
                        textureView.setOpaque(z13);
                        float[] fArr7 = fArr6;
                        if (fArr7 != null) {
                            matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m3710setFromEL8BTi8(matrix, fArr7);
                        } else {
                            matrix = null;
                        }
                        textureView.setTransform(matrix);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                final long j8 = j2;
                final boolean z14 = z3;
                final float[] fArr7 = fArr;
                objRememberedValue2 = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                        android.graphics.Matrix matrix;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m6467equalsimpl0(j8, IntSize.INSTANCE.m6474getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m6469getWidthimpl(j8), IntSize.m6468getHeightimpl(j8));
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j8);
                        textureView.setOpaque(z14);
                        float[] fArr8 = fArr7;
                        if (fArr8 != null) {
                            matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m3710setFromEL8BTi8(matrix, fArr8);
                        } else {
                            matrix = null;
                        }
                        textureView.setTransform(matrix);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidView_androidKt.AndroidView(function4, companion, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i7 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z2 = z3;
            j3 = j2;
            fArr2 = fArr;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i10 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    i3 &= -897;
                }
                if (i4 != 0) {
                    i7 = i3;
                    z3 = z2;
                    j2 = jM6474getZeroYbymL2g;
                    fArr = null;
                } else {
                    i7 = i3;
                    z3 = z2;
                    j2 = jM6474getZeroYbymL2g;
                }
            } else {
                if (i9 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i10 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    jM6474getZeroYbymL2g = IntSize.INSTANCE.m6474getZeroYbymL2g();
                    i3 &= -897;
                }
                if (i4 != 0) {
                    i7 = i3;
                    z3 = z2;
                    j2 = jM6474getZeroYbymL2g;
                    fArr = null;
                } else {
                    i7 = i3;
                    z3 = z2;
                    j2 = jM6474getZeroYbymL2g;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(217541314, i7, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
            }
            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184051342, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
            int i14 = (i7 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK;
            boolean z15 = ((i14 <= 256 && composerStartRestartGroup.changed(j2)) || (i7 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | zChangedInstance8;
            if ((57344 & i7) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            z5 = z15 | z4;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z5) {
                objRememberedValue = (Function1) new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        TextureView textureView = new TextureView(context);
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                        long j9 = j2;
                        Function1<AndroidExternalSurfaceScope, Unit> function5 = function1;
                        androidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j9);
                        function5.invoke(androidEmbeddedExternalSurfaceState);
                        textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
                        return textureView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = (Function1) new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        TextureView textureView = new TextureView(context);
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
                        long j9 = j2;
                        Function1<AndroidExternalSurfaceScope, Unit> function5 = function1;
                        androidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j9);
                        function5.invoke(androidEmbeddedExternalSurfaceState);
                        textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
                        return textureView;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function5 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$5 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextureView textureView) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                    invoke2(textureView);
                    return Unit.INSTANCE;
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 184060392, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            boolean zChangedInstance9 = ((i14 <= 256 && composerStartRestartGroup.changed(j2)) || (i7 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState) | ((i7 & 112) == 32);
            if (fArr != null) {
                matrixM4069boximpl2 = Matrix.m4069boximpl(fArr);
            } else {
                matrixM4069boximpl2 = null;
            }
            zChangedInstance = zChangedInstance9 | composerStartRestartGroup.changedInstance(matrixM4069boximpl2);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChangedInstance) {
                final long j9 = j2;
                final boolean z16 = z3;
                final float[] fArr8 = fArr;
                objRememberedValue2 = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                        android.graphics.Matrix matrix;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m6467equalsimpl0(j9, IntSize.INSTANCE.m6474getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m6469getWidthimpl(j9), IntSize.m6468getHeightimpl(j9));
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j9);
                        textureView.setOpaque(z16);
                        float[] fArr9 = fArr8;
                        if (fArr9 != null) {
                            matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m3710setFromEL8BTi8(matrix, fArr9);
                        } else {
                            matrix = null;
                        }
                        textureView.setTransform(matrix);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                final long j10 = j2;
                final boolean z17 = z3;
                final float[] fArr9 = fArr;
                objRememberedValue2 = (Function1) new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView textureView) {
                        android.graphics.Matrix matrix;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m6467equalsimpl0(j10, IntSize.INSTANCE.m6474getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m6469getWidthimpl(j10), IntSize.m6468getHeightimpl(j10));
                        }
                        androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.m222setSurfaceSizeozmzZPI(j10);
                        textureView.setOpaque(z17);
                        float[] fArr10 = fArr9;
                        if (fArr10 != null) {
                            matrix = androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m3710setFromEL8BTi8(matrix, fArr10);
                        } else {
                            matrix = null;
                        }
                        textureView.setTransform(matrix);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidView_androidKt.AndroidView(function5, companion, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$5, null, (Function1) objRememberedValue2, composerStartRestartGroup, ((i7 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z2 = z3;
            j3 = j2;
            fArr2 = fArr;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = companion;
            final boolean z18 = z2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i15) {
                    AndroidExternalSurface_androidKt.m233AndroidEmbeddedExternalSurfacesv6N_fY(modifier4, z18, j3, fArr2, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }
}
