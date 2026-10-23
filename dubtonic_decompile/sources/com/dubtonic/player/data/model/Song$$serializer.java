package com.dubtonic.player.data.model;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0007R\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/dubtonic/player/data/model/Song.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/dubtonic/player/data/model/Song;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class Song$$serializer implements GeneratedSerializer<Song> {
    public static final int $stable;
    public static final Song$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    private Song$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Song$$serializer song$$serializer = new Song$$serializer();
        INSTANCE = song$$serializer;
        $stable = 8;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.dubtonic.player.data.model.Song", song$$serializer, 19);
        pluginGeneratedSerialDescriptor.addElement(TtmlNode.ATTR_ID, true);
        pluginGeneratedSerialDescriptor.addElement("parent", true);
        pluginGeneratedSerialDescriptor.addElement("title", true);
        pluginGeneratedSerialDescriptor.addElement("album", true);
        pluginGeneratedSerialDescriptor.addElement("artist", true);
        pluginGeneratedSerialDescriptor.addElement("isDir", true);
        pluginGeneratedSerialDescriptor.addElement("coverArt", true);
        pluginGeneratedSerialDescriptor.addElement("duration", true);
        pluginGeneratedSerialDescriptor.addElement("track", true);
        pluginGeneratedSerialDescriptor.addElement("discNumber", true);
        pluginGeneratedSerialDescriptor.addElement("year", true);
        pluginGeneratedSerialDescriptor.addElement("genre", true);
        pluginGeneratedSerialDescriptor.addElement("bitRate", true);
        pluginGeneratedSerialDescriptor.addElement("size", true);
        pluginGeneratedSerialDescriptor.addElement("suffix", true);
        pluginGeneratedSerialDescriptor.addElement("contentType", true);
        pluginGeneratedSerialDescriptor.addElement("path", true);
        pluginGeneratedSerialDescriptor.addElement("playCount", true);
        pluginGeneratedSerialDescriptor.addElement("type", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, BooleanSerializer.INSTANCE, StringSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, StringSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final Song deserialize(Decoder decoder) {
        String str;
        int i;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        boolean zDecodeBooleanElement;
        String str8;
        long j;
        long j2;
        long j3;
        long j4;
        long jLongValue;
        String str9;
        String strDecodeStringElement;
        long j5;
        String str10;
        long j6;
        int i2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        int i3 = 2;
        int i4 = 0;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 4);
            zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 5);
            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 6);
            long jLongValue2 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 7, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            long jLongValue3 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            long jLongValue4 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 9, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            long jLongValue5 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 10, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            String strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 11);
            long jLongValue6 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 12, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            long jLongValue7 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 13, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            String strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 14);
            String strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 15);
            String strDecodeStringElement11 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 16);
            jLongValue = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 17, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 18);
            str8 = strDecodeStringElement10;
            i = 524287;
            str9 = strDecodeStringElement11;
            str7 = strDecodeStringElement7;
            str2 = strDecodeStringElement6;
            j = jLongValue2;
            j6 = jLongValue4;
            str4 = strDecodeStringElement4;
            str6 = strDecodeStringElement5;
            str3 = strDecodeStringElement8;
            str5 = strDecodeStringElement3;
            str = strDecodeStringElement2;
            j5 = jLongValue5;
            j4 = jLongValue7;
            j3 = jLongValue6;
            str10 = strDecodeStringElement9;
            j2 = jLongValue3;
        } else {
            int i5 = 18;
            String strDecodeStringElement12 = null;
            String strDecodeStringElement13 = null;
            String strDecodeStringElement14 = null;
            String strDecodeStringElement15 = null;
            String strDecodeStringElement16 = null;
            String strDecodeStringElement17 = null;
            String strDecodeStringElement18 = null;
            String strDecodeStringElement19 = null;
            String strDecodeStringElement20 = null;
            boolean z = true;
            boolean zDecodeBooleanElement2 = false;
            long jLongValue8 = 0;
            long jLongValue9 = 0;
            long jLongValue10 = 0;
            long jLongValue11 = 0;
            long jLongValue12 = 0;
            long jLongValue13 = 0;
            long jLongValue14 = 0;
            String strDecodeStringElement21 = null;
            String strDecodeStringElement22 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i5 = 18;
                        break;
                    case 0:
                        i4 |= 1;
                        strDecodeStringElement12 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
                        i5 = 18;
                        break;
                    case 1:
                        strDecodeStringElement16 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                        i4 |= 2;
                        i5 = 18;
                        break;
                    case 2:
                        strDecodeStringElement15 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, i3);
                        i4 |= 4;
                        i5 = 18;
                        break;
                    case 3:
                        strDecodeStringElement17 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
                        i4 |= 8;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 4:
                        strDecodeStringElement13 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 4);
                        i4 |= 16;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 5:
                        zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 5);
                        i4 |= 32;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 6:
                        strDecodeStringElement19 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 6);
                        i4 |= 64;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 7:
                        jLongValue8 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 7, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue8))).longValue();
                        i4 |= 128;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 8:
                        jLongValue9 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue9))).longValue();
                        i4 |= 256;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 9:
                        jLongValue10 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 9, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue10))).longValue();
                        i4 |= 512;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 10:
                        jLongValue11 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 10, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue11))).longValue();
                        i4 |= 1024;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 11:
                        strDecodeStringElement14 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 11);
                        i4 |= 2048;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 12:
                        jLongValue12 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 12, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue12))).longValue();
                        i4 |= 4096;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 13:
                        jLongValue13 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 13, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue13))).longValue();
                        i4 |= 8192;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 14:
                        strDecodeStringElement21 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 14);
                        i4 |= 16384;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 15:
                        strDecodeStringElement20 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 15);
                        i4 |= 32768;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 16:
                        strDecodeStringElement22 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 16);
                        i2 = 65536;
                        i4 |= i2;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 17:
                        jLongValue14 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 17, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue14))).longValue();
                        i2 = 131072;
                        i4 |= i2;
                        i3 = 2;
                        i5 = 18;
                        break;
                    case 18:
                        strDecodeStringElement18 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, i5);
                        i4 |= 262144;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            str = strDecodeStringElement12;
            i = i4;
            str2 = strDecodeStringElement13;
            str3 = strDecodeStringElement14;
            str4 = strDecodeStringElement15;
            str5 = strDecodeStringElement16;
            str6 = strDecodeStringElement17;
            str7 = strDecodeStringElement19;
            zDecodeBooleanElement = zDecodeBooleanElement2;
            str8 = strDecodeStringElement20;
            j = jLongValue8;
            j2 = jLongValue9;
            j3 = jLongValue12;
            j4 = jLongValue13;
            jLongValue = jLongValue14;
            str9 = strDecodeStringElement22;
            strDecodeStringElement = strDecodeStringElement18;
            j5 = jLongValue11;
            str10 = strDecodeStringElement21;
            j6 = jLongValue10;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new Song(i, str, str5, str4, str6, str2, zDecodeBooleanElement, str7, j, j2, j6, j5, str3, j3, j4, str10, str8, str9, jLongValue, strDecodeStringElement, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, Song value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        Song.write$Self$app_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
