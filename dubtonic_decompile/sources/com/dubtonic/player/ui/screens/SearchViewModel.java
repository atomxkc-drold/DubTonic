package com.dubtonic.player.ui.screens;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.LoadState;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.SearchResult3;
import com.dubtonic.player.di.AppContainer;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: SearchScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\bJ\b\u0010\u0019\u001a\u00020\u0017H\u0002J\u0006\u0010\u001a\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, d2 = {"Lcom/dubtonic/player/ui/screens/SearchViewModel;", "Landroidx/lifecycle/ViewModel;", TtmlNode.RUBY_CONTAINER, "Lcom/dubtonic/player/di/AppContainer;", "<init>", "(Lcom/dubtonic/player/di/AppContainer;)V", "_query", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "query", "getQuery", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "_results", "Lcom/dubtonic/player/data/LoadState;", "Lcom/dubtonic/player/data/model/SearchResult3;", "results", "getResults", "history", "Lkotlinx/coroutines/flow/Flow;", "", "getHistory", "()Lkotlinx/coroutines/flow/Flow;", "setQuery", "", "q", "search", "clearHistory", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SearchViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<String> _query;
    private final MutableStateFlow<LoadState<SearchResult3>> _results;
    private final AppContainer container;
    private final Flow<List<String>> history;
    private final MutableStateFlow<String> query;
    private final MutableStateFlow<LoadState<SearchResult3>> results;

    public SearchViewModel(AppContainer container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.container = container;
        MutableStateFlow<String> MutableStateFlow = StateFlowKt.MutableStateFlow("");
        this._query = MutableStateFlow;
        this.query = MutableStateFlow;
        MutableStateFlow<LoadState<SearchResult3>> MutableStateFlow2 = StateFlowKt.MutableStateFlow(LoadState.Idle.INSTANCE);
        this._results = MutableStateFlow2;
        this.results = MutableStateFlow2;
        this.history = container.getSettings().getSearchHistoryFlow();
    }

    public final MutableStateFlow<String> getQuery() {
        return this.query;
    }

    public final MutableStateFlow<LoadState<SearchResult3>> getResults() {
        return this.results;
    }

    public final Flow<List<String>> getHistory() {
        return this.history;
    }

    public final void setQuery(String q) {
        Intrinsics.checkNotNullParameter(q, "q");
        this._query.setValue(q);
        if (StringsKt.trim((CharSequence) q).toString().length() >= 2) {
            search();
        } else {
            this._results.setValue(LoadState.Idle.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.screens.SearchViewModel$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.ui.screens.SearchViewModel$search$1", f = "SearchScreen.kt", i = {1}, l = {83, TsExtractor.TS_STREAM_TYPE_DVBSUBS, 91}, m = "invokeSuspend", n = {"q"}, s = {"L$0"})
    static final class C06531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C06531(Continuation<? super C06531> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SearchViewModel.this.new C06531(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:32:0x009c A[Catch: Exception -> 0x0016, TryCatch #0 {Exception -> 0x0016, blocks: (B:7:0x0011, B:14:0x0025, B:30:0x0090, B:32:0x009c, B:33:0x00a7, B:25:0x0076, B:27:0x0082, B:36:0x00c6, B:37:0x00cb), top: B:44:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:35:0x00c5 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String string;
            SearchResult3 searchResult3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (DelayKt.delay(300L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                    } else if (i == 2) {
                        string = (String) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        searchResult3 = (SearchResult3) obj;
                        MutableStateFlow mutableStateFlow = SearchViewModel.this._results;
                        if (searchResult3 == null) {
                            searchResult3 = new SearchResult3((List) null, (List) null, (List) null, 7, (DefaultConstructorMarker) null);
                        }
                        mutableStateFlow.setValue(new LoadState.Ready(searchResult3));
                        this.L$0 = null;
                        this.label = 3;
                        if (SearchViewModel.this.container.getSettings().addSearch(string, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
                string = StringsKt.trim((CharSequence) SearchViewModel.this._query.getValue()).toString();
                if (string.length() < 2) {
                    return Unit.INSTANCE;
                }
                SearchViewModel.this._results.setValue(LoadState.Loading.INSTANCE);
                ServerConfig serverNow = SearchViewModel.this.container.getSettings().getServerNow();
                LibraryRepository library = SearchViewModel.this.container.getLibrary();
                if (serverNow != null) {
                    this.L$0 = string;
                    this.label = 2;
                    obj = library.search(serverNow, string, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    searchResult3 = (SearchResult3) obj;
                    MutableStateFlow mutableStateFlow2 = SearchViewModel.this._results;
                    if (searchResult3 == null) {
                        searchResult3 = new SearchResult3((List) null, (List) null, (List) null, 7, (DefaultConstructorMarker) null);
                    }
                    mutableStateFlow2.setValue(new LoadState.Ready(searchResult3));
                    this.L$0 = null;
                    this.label = 3;
                    if (SearchViewModel.this.container.getSettings().addSearch(string, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException();
            } catch (Exception e) {
                MutableStateFlow mutableStateFlow3 = SearchViewModel.this._results;
                String message = e.getMessage();
                if (message == null) {
                    message = "Search failed";
                }
                mutableStateFlow3.setValue(new LoadState.Failed(message));
            }
        }
    }

    private final void search() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C06531(null), 3, null);
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.screens.SearchViewModel$clearHistory$1, reason: invalid class name */
    /* JADX INFO: compiled from: SearchScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.ui.screens.SearchViewModel$clearHistory$1", f = "SearchScreen.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SearchViewModel.this.new AnonymousClass1(continuation);
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
                this.label = 1;
                if (SearchViewModel.this.container.getSettings().clearSearches(this) == coroutine_suspended) {
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

    public final void clearHistory() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }
}
