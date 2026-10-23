package com.dubtonic.player.data.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/dubtonic/player/data/model/ApiException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "code", "", "<init>", "(Ljava/lang/String;J)V", "getCode", "()J", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ApiException extends Exception {
    public static final int $stable = 0;
    private final long code;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApiException(String message, long j) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        this.code = j;
    }

    public /* synthetic */ ApiException(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? 0L : j);
    }

    public final long getCode() {
        return this.code;
    }
}
