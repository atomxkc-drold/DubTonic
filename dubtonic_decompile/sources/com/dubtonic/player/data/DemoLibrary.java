package com.dubtonic.player.data;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.data.model.Album;
import com.dubtonic.player.data.model.Artist;
import com.dubtonic.player.data.model.Playlist;
import com.dubtonic.player.data.model.Song;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: DemoLibrary.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0003¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\u0016\u001a\u00020\u0011J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\rJV\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112$\u0010\u001f\u001a \u0012\u001c\u0012\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110 \u0012\u0004\u0012\u00020\u001d0 0\u0005H\u0002J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0011H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\b¨\u0006\""}, d2 = {"Lcom/dubtonic/player/data/DemoLibrary;", "", "<init>", "()V", "artists", "", "Lcom/dubtonic/player/data/model/Artist;", "getArtists", "()Ljava/util/List;", "albums", "Lcom/dubtonic/player/data/model/Album;", "getAlbums", "songs", "Lcom/dubtonic/player/data/model/Song;", "getSongs", "playlistEntries", "", "", "playlists", "Lcom/dubtonic/player/data/model/Playlist;", "getPlaylists", "playlistSongs", TtmlNode.ATTR_ID, "albumOf", "song", "album", HintConstants.AUTOFILL_HINT_NAME, "artist", "year", "", "genre", "tracks", "Lkotlin/Pair;", "playlist", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DemoLibrary {
    public static final int $stable;
    public static final DemoLibrary INSTANCE;
    private static final List<Album> albums;
    private static final List<Artist> artists;
    private static final Map<String, List<String>> playlistEntries;
    private static final List<Playlist> playlists;
    private static final List<Song> songs;

    private DemoLibrary() {
    }

    public final List<Artist> getArtists() {
        return artists;
    }

    static {
        DemoLibrary demoLibrary = new DemoLibrary();
        INSTANCE = demoLibrary;
        artists = CollectionsKt.listOf((Object[]) new Artist[]{new Artist("demo-art-1", "Neon Harbour", (String) null, (String) null, 2L, 12, (DefaultConstructorMarker) null), new Artist("demo-art-2", "Lumen Field", (String) null, (String) null, 1L, 12, (DefaultConstructorMarker) null), new Artist("demo-art-3", "Casiopea Drive", (String) null, (String) null, 1L, 12, (DefaultConstructorMarker) null), new Artist("demo-art-4", "Glass Meridian", (String) null, (String) null, 1L, 12, (DefaultConstructorMarker) null), new Artist("demo-art-5", "Velvet Antenna", (String) null, (String) null, 1L, 12, (DefaultConstructorMarker) null), new Artist("demo-art-6", "The Slow Waves", (String) null, (String) null, 1L, 12, (DefaultConstructorMarker) null), new Artist("demo-art-7", "Prism District", (String) null, (String) null, 1L, 12, (DefaultConstructorMarker) null), new Artist("demo-art-8", "Moonside FM", (String) null, (String) null, 1L, 12, (DefaultConstructorMarker) null)});
        List<Album> listListOf = CollectionsKt.listOf((Object[]) new Album[]{demoLibrary.album("demo-alb-1", "Midnight Arcade", "Neon Harbour", 2024L, "Synthwave", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TuplesKt.to("demo-s-1", "Opening Credits"), 214L), TuplesKt.to(TuplesKt.to("demo-s-2", "Quarter Heist"), 198L), TuplesKt.to(TuplesKt.to("demo-s-3", "Neon Rain"), 243L), TuplesKt.to(TuplesKt.to("demo-s-4", "High Score"), 187L), TuplesKt.to(TuplesKt.to("demo-s-5", "Last Life"), 256L), TuplesKt.to(TuplesKt.to("demo-s-6", "Insert Coin"), 164L), TuplesKt.to(TuplesKt.to("demo-s-7", "Continue?"), 231L), TuplesKt.to(TuplesKt.to("demo-s-8", "Boss Fight"), 278L)})), demoLibrary.album("demo-alb-2", "Static Bloom", "Lumen Field", 2023L, "Ambient", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TuplesKt.to("demo-s-9", "Fjord"), 305L), TuplesKt.to(TuplesKt.to("demo-s-10", "Pollen"), 268L), TuplesKt.to(TuplesKt.to("demo-s-11", "Moss"), 291L), TuplesKt.to(TuplesKt.to("demo-s-12", "Slow Static"), 322L), TuplesKt.to(TuplesKt.to("demo-s-13", "Lumen"), 249L), TuplesKt.to(TuplesKt.to("demo-s-14", "Harbor Light"), 274L)})), demoLibrary.album("demo-alb-3", "Coastal Grid", "Casiopea Drive", 2024L, "City Pop", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TuplesKt.to("demo-s-15", "Grid Line"), 226L), TuplesKt.to(TuplesKt.to("demo-s-16", "Sunroof"), 205L), TuplesKt.to(TuplesKt.to("demo-s-17", "Coastal"), 238L), TuplesKt.to(TuplesKt.to("demo-s-18", "Bayside Loop"), 219L), TuplesKt.to(TuplesKt.to("demo-s-19", "Tailwind"), 247L)})), demoLibrary.album("demo-alb-4", "Latitudes", "Glass Meridian", 2022L, "Post-Rock", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TuplesKt.to("demo-s-20", "Meridian"), 342L), TuplesKt.to(TuplesKt.to("demo-s-21", "Latitude 48"), 316L), TuplesKt.to(TuplesKt.to("demo-s-22", "Sea Stack"), 298L), TuplesKt.to(TuplesKt.to("demo-s-23", "Gulf"), 355L)})), demoLibrary.album("demo-alb-5", "Signal Flares", "Velvet Antenna", 2024L, "Indie Pop", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TuplesKt.to("demo-s-24", "Flares"), 192L), TuplesKt.to(TuplesKt.to("demo-s-25", "Antenna"), 208L), TuplesKt.to(TuplesKt.to("demo-s-26", "Velvet Hour"), 223L), TuplesKt.to(TuplesKt.to("demo-s-27", "Signal Lost"), 184L), TuplesKt.to(TuplesKt.to("demo-s-28", "Frequency"), 215L)})), demoLibrary.album("demo-alb-6", "Tidal Memory", "The Slow Waves", 2023L, "Dream Pop", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TuplesKt.to("demo-s-29", "Undertow"), 288L), TuplesKt.to(TuplesKt.to("demo-s-30", "Salt"), 262L), TuplesKt.to(TuplesKt.to("demo-s-31", "Tidal"), 304L), TuplesKt.to(TuplesKt.to("demo-s-32", "Low Water"), 276L)})), demoLibrary.album("demo-alb-7", "Prism City", "Prism District", 2024L, "Electronica", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TuplesKt.to("demo-s-33", "Refraction"), 234L), TuplesKt.to(TuplesKt.to("demo-s-34", "Skyline"), 218L), TuplesKt.to(TuplesKt.to("demo-s-35", "Prism"), 251L), TuplesKt.to(TuplesKt.to("demo-s-36", "Neon District"), 227L), TuplesKt.to(TuplesKt.to("demo-s-37", "Afterglow"), 245L)})), demoLibrary.album("demo-alb-8", "Late Broadcast", "Moonside FM", 2022L, "Lo-Fi", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TuplesKt.to("demo-s-38", "12 AM Signal"), 176L), TuplesKt.to(TuplesKt.to("demo-s-39", "Night Shift"), 189L), TuplesKt.to(TuplesKt.to("demo-s-40", "FM Static"), 168L), TuplesKt.to(TuplesKt.to("demo-s-41", "Moon Dial"), 194L)}))});
        albums = listListOf;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listListOf.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((Album) it.next()).getSong());
        }
        songs = arrayList;
        playlistEntries = MapsKt.mapOf(TuplesKt.to("demo-pl-1", CollectionsKt.listOf((Object[]) new String[]{"demo-s-1", "demo-s-3", "demo-s-16", "demo-s-33", "demo-s-36", "demo-s-38"})), TuplesKt.to("demo-pl-2", CollectionsKt.listOf((Object[]) new String[]{"demo-s-9", "demo-s-10", "demo-s-11", "demo-s-20", "demo-s-21"})), TuplesKt.to("demo-pl-3", CollectionsKt.listOf((Object[]) new String[]{"demo-s-2", "demo-s-15", "demo-s-24", "demo-s-25", "demo-s-34", "demo-s-40"})));
        DemoLibrary demoLibrary2 = INSTANCE;
        playlists = CollectionsKt.listOf((Object[]) new Playlist[]{demoLibrary2.playlist("demo-pl-1", "Night Drive"), demoLibrary2.playlist("demo-pl-2", "Focus Flow"), demoLibrary2.playlist("demo-pl-3", "Weekend Start")});
        $stable = 8;
    }

    public final List<Album> getAlbums() {
        return albums;
    }

    public final List<Song> getSongs() {
        return songs;
    }

    public final List<Playlist> getPlaylists() {
        return playlists;
    }

    public final List<Song> playlistSongs(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        List<String> list = playlistEntries.get(id);
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        List<Song> list2 = songs;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
        for (Object obj : list2) {
            linkedHashMap.put(((Song) obj).getId(), obj);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Song song = (Song) linkedHashMap.get((String) it.next());
            if (song != null) {
                arrayList.add(song);
            }
        }
        return arrayList;
    }

    public final Album albumOf(Song song) {
        Object next;
        Intrinsics.checkNotNullParameter(song, "song");
        Iterator<T> it = albums.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(((Album) next).getId(), song.getParent())) {
                return (Album) next;
            }
        }
        next = null;
        return (Album) next;
    }

    private final Album album(String id, String name, String artist, long year, String genre, List<Pair<Pair<String, String>, Long>> tracks) {
        List<Pair<Pair<String, String>, Long>> list = tracks;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Pair pair = (Pair) obj;
            Pair pair2 = (Pair) pair.component1();
            arrayList.add(new Song((String) pair2.getFirst(), id, (String) pair2.getSecond(), name, artist, false, (String) null, ((Number) pair.component2()).longValue(), i2, 1L, year, genre, 0L, 0L, "demo", "audio/demo", (String) null, 0L, (String) null, 471136, (DefaultConstructorMarker) null));
            i = i2;
        }
        ArrayList arrayList2 = arrayList;
        long size = arrayList2.size();
        Iterator it = arrayList2.iterator();
        long duration = 0;
        while (it.hasNext()) {
            duration += ((Song) it.next()).getDuration();
        }
        return new Album(id, name, artist, "", (String) null, size, duration, (String) null, year, genre, (List) arrayList2, false, 2192, (DefaultConstructorMarker) null);
    }

    private final Playlist playlist(String id, String name) {
        List<String> listEmptyList = playlistEntries.get(id);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List<Song> list = songs;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(((Song) obj).getId(), obj);
        }
        Iterator<T> it = listEmptyList.iterator();
        long duration = 0;
        while (it.hasNext()) {
            Song song = (Song) linkedHashMap.get((String) it.next());
            duration += song != null ? song.getDuration() : 0L;
        }
        return new Playlist(id, name, "DubTonic", "Demo playlist", (String) null, false, listEmptyList.size(), duration, (String) null, (String) null, 816, (DefaultConstructorMarker) null);
    }
}
