package com.dubtonic.player.data.model;

import androidx.core.app.NotificationCompat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0007R\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/dubtonic/player/data/model/SubsonicBody.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/dubtonic/player/data/model/SubsonicBody;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class SubsonicBody$$serializer implements GeneratedSerializer<SubsonicBody> {
    public static final int $stable;
    public static final SubsonicBody$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    private SubsonicBody$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        SubsonicBody$$serializer subsonicBody$$serializer = new SubsonicBody$$serializer();
        INSTANCE = subsonicBody$$serializer;
        $stable = 8;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.dubtonic.player.data.model.SubsonicBody", subsonicBody$$serializer, 14);
        pluginGeneratedSerialDescriptor.addElement(NotificationCompat.CATEGORY_STATUS, true);
        pluginGeneratedSerialDescriptor.addElement("version", true);
        pluginGeneratedSerialDescriptor.addElement("error", true);
        pluginGeneratedSerialDescriptor.addElement("artists", true);
        pluginGeneratedSerialDescriptor.addElement("indexes", true);
        pluginGeneratedSerialDescriptor.addElement("albumList2", true);
        pluginGeneratedSerialDescriptor.addElement("albumList", true);
        pluginGeneratedSerialDescriptor.addElement("album", true);
        pluginGeneratedSerialDescriptor.addElement("artist", true);
        pluginGeneratedSerialDescriptor.addElement("topSongs", true);
        pluginGeneratedSerialDescriptor.addElement("randomSongs", true);
        pluginGeneratedSerialDescriptor.addElement("playlists", true);
        pluginGeneratedSerialDescriptor.addElement("playlist", true);
        pluginGeneratedSerialDescriptor.addElement("searchResult3", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(SubsonicError$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ArtistsContainer$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(IndexesContainer$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(AlbumList$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(AlbumList$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Album$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ArtistDetail$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SongList$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SongList$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(PlaylistsContainer$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(PlaylistDetail$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SearchResult3$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final SubsonicBody deserialize(Decoder decoder) {
        AlbumList albumList;
        int i;
        SongList songList;
        AlbumList albumList2;
        SearchResult3 searchResult3;
        Album album;
        IndexesContainer indexesContainer;
        ArtistDetail artistDetail;
        ArtistsContainer artistsContainer;
        SongList songList2;
        SubsonicError subsonicError;
        PlaylistDetail playlistDetail;
        String str;
        PlaylistsContainer playlistsContainer;
        String str2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
            SubsonicError subsonicError2 = (SubsonicError) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, SubsonicError$$serializer.INSTANCE, null);
            ArtistsContainer artistsContainer2 = (ArtistsContainer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, ArtistsContainer$$serializer.INSTANCE, null);
            IndexesContainer indexesContainer2 = (IndexesContainer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, IndexesContainer$$serializer.INSTANCE, null);
            AlbumList albumList3 = (AlbumList) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, AlbumList$$serializer.INSTANCE, null);
            AlbumList albumList4 = (AlbumList) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, AlbumList$$serializer.INSTANCE, null);
            Album album2 = (Album) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, Album$$serializer.INSTANCE, null);
            ArtistDetail artistDetail2 = (ArtistDetail) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, ArtistDetail$$serializer.INSTANCE, null);
            SongList songList3 = (SongList) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, SongList$$serializer.INSTANCE, null);
            SongList songList4 = (SongList) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, SongList$$serializer.INSTANCE, null);
            PlaylistsContainer playlistsContainer2 = (PlaylistsContainer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, PlaylistsContainer$$serializer.INSTANCE, null);
            playlistDetail = (PlaylistDetail) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, PlaylistDetail$$serializer.INSTANCE, null);
            searchResult3 = (SearchResult3) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, SearchResult3$$serializer.INSTANCE, null);
            songList2 = songList3;
            str2 = strDecodeStringElement2;
            songList = songList4;
            str = strDecodeStringElement;
            playlistsContainer = playlistsContainer2;
            i = 16383;
            album = album2;
            subsonicError = subsonicError2;
            artistDetail = artistDetail2;
            albumList2 = albumList4;
            artistsContainer = artistsContainer2;
            albumList = albumList3;
            indexesContainer = indexesContainer2;
        } else {
            SearchResult3 searchResult4 = null;
            PlaylistDetail playlistDetail2 = null;
            PlaylistsContainer playlistsContainer3 = null;
            SongList songList5 = null;
            ArtistDetail artistDetail3 = null;
            Album album3 = null;
            albumList = null;
            SongList songList6 = null;
            AlbumList albumList5 = null;
            IndexesContainer indexesContainer3 = null;
            String strDecodeStringElement3 = null;
            String strDecodeStringElement4 = null;
            boolean z = true;
            int i2 = 0;
            SubsonicError subsonicError3 = null;
            ArtistsContainer artistsContainer3 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        searchResult4 = searchResult4;
                        break;
                    case 0:
                        strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
                        i2 |= 1;
                        searchResult4 = searchResult4;
                        break;
                    case 1:
                        strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                        i2 |= 2;
                        searchResult4 = searchResult4;
                        break;
                    case 2:
                        subsonicError3 = (SubsonicError) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, SubsonicError$$serializer.INSTANCE, subsonicError3);
                        i2 |= 4;
                        searchResult4 = searchResult4;
                        break;
                    case 3:
                        artistsContainer3 = (ArtistsContainer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, ArtistsContainer$$serializer.INSTANCE, artistsContainer3);
                        i2 |= 8;
                        subsonicError3 = subsonicError3;
                        break;
                    case 4:
                        indexesContainer3 = (IndexesContainer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, IndexesContainer$$serializer.INSTANCE, indexesContainer3);
                        i2 |= 16;
                        subsonicError3 = subsonicError3;
                        break;
                    case 5:
                        albumList = (AlbumList) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, AlbumList$$serializer.INSTANCE, albumList);
                        i2 |= 32;
                        subsonicError3 = subsonicError3;
                        break;
                    case 6:
                        albumList5 = (AlbumList) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, AlbumList$$serializer.INSTANCE, albumList5);
                        i2 |= 64;
                        subsonicError3 = subsonicError3;
                        break;
                    case 7:
                        album3 = (Album) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, Album$$serializer.INSTANCE, album3);
                        i2 |= 128;
                        subsonicError3 = subsonicError3;
                        break;
                    case 8:
                        artistDetail3 = (ArtistDetail) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, ArtistDetail$$serializer.INSTANCE, artistDetail3);
                        i2 |= 256;
                        subsonicError3 = subsonicError3;
                        break;
                    case 9:
                        songList5 = (SongList) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, SongList$$serializer.INSTANCE, songList5);
                        i2 |= 512;
                        subsonicError3 = subsonicError3;
                        break;
                    case 10:
                        songList6 = (SongList) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, SongList$$serializer.INSTANCE, songList6);
                        i2 |= 1024;
                        subsonicError3 = subsonicError3;
                        break;
                    case 11:
                        playlistsContainer3 = (PlaylistsContainer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, PlaylistsContainer$$serializer.INSTANCE, playlistsContainer3);
                        i2 |= 2048;
                        subsonicError3 = subsonicError3;
                        break;
                    case 12:
                        playlistDetail2 = (PlaylistDetail) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, PlaylistDetail$$serializer.INSTANCE, playlistDetail2);
                        i2 |= 4096;
                        subsonicError3 = subsonicError3;
                        break;
                    case 13:
                        searchResult4 = (SearchResult3) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, SearchResult3$$serializer.INSTANCE, searchResult4);
                        i2 |= 8192;
                        subsonicError3 = subsonicError3;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            SearchResult3 searchResult5 = searchResult4;
            i = i2;
            songList = songList6;
            albumList2 = albumList5;
            searchResult3 = searchResult5;
            album = album3;
            indexesContainer = indexesContainer3;
            artistDetail = artistDetail3;
            artistsContainer = artistsContainer3;
            songList2 = songList5;
            subsonicError = subsonicError3;
            String str3 = strDecodeStringElement4;
            playlistDetail = playlistDetail2;
            str = strDecodeStringElement3;
            playlistsContainer = playlistsContainer3;
            str2 = str3;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new SubsonicBody(i, str, str2, subsonicError, artistsContainer, indexesContainer, albumList, albumList2, album, artistDetail, songList2, songList, playlistsContainer, playlistDetail, searchResult3, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, SubsonicBody value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        SubsonicBody.write$Self$app_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
