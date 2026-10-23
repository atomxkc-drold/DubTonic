package com.dubtonic.player.data.model;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
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
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0007R\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/dubtonic/player/data/model/Album.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/dubtonic/player/data/model/Album;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class Album$$serializer implements GeneratedSerializer<Album> {
    public static final int $stable;
    public static final Album$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    private Album$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Album$$serializer album$$serializer = new Album$$serializer();
        INSTANCE = album$$serializer;
        $stable = 8;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.dubtonic.player.data.model.Album", album$$serializer, 12);
        pluginGeneratedSerialDescriptor.addElement(TtmlNode.ATTR_ID, true);
        pluginGeneratedSerialDescriptor.addElement(HintConstants.AUTOFILL_HINT_NAME, true);
        pluginGeneratedSerialDescriptor.addElement("artist", true);
        pluginGeneratedSerialDescriptor.addElement("artistId", true);
        pluginGeneratedSerialDescriptor.addElement("coverArt", true);
        pluginGeneratedSerialDescriptor.addElement("songCount", true);
        pluginGeneratedSerialDescriptor.addElement("duration", true);
        pluginGeneratedSerialDescriptor.addElement("created", true);
        pluginGeneratedSerialDescriptor.addElement("year", true);
        pluginGeneratedSerialDescriptor.addElement("genre", true);
        pluginGeneratedSerialDescriptor.addElement("song", true);
        pluginGeneratedSerialDescriptor.addElement("isDir", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, FlexibleStringSerializer.INSTANCE, StringSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, StringSerializer.INSTANCE, FlexibleLongSerializer.INSTANCE, StringSerializer.INSTANCE, Album.$childSerializers[10], BooleanSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final Album deserialize(Decoder decoder) {
        String strDecodeStringElement;
        String str;
        int i;
        String str2;
        String str3;
        List list;
        String str4;
        String str5;
        String str6;
        long j;
        long j2;
        boolean zDecodeBooleanElement;
        long j3;
        char c;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        KSerializer[] kSerializerArr = Album.$childSerializers;
        int i2 = 9;
        String strDecodeStringElement2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
            String str7 = (String) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, FlexibleStringSerializer.INSTANCE, null);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 4);
            long jLongValue = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            long jLongValue2 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 7);
            long jLongValue3 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, FlexibleLongSerializer.INSTANCE, 0L)).longValue();
            String strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 9);
            list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 10, kSerializerArr[10], null);
            str6 = strDecodeStringElement8;
            zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 11);
            strDecodeStringElement = strDecodeStringElement4;
            j = jLongValue;
            j3 = jLongValue3;
            str2 = strDecodeStringElement7;
            j2 = jLongValue2;
            str = str7;
            str5 = strDecodeStringElement6;
            str4 = strDecodeStringElement5;
            str3 = strDecodeStringElement3;
            i = 4095;
        } else {
            strDecodeStringElement = null;
            int i3 = 11;
            String strDecodeStringElement9 = null;
            String strDecodeStringElement10 = null;
            boolean z = true;
            int i4 = 0;
            boolean zDecodeBooleanElement2 = false;
            long jLongValue4 = 0;
            long jLongValue5 = 0;
            long jLongValue6 = 0;
            List list2 = null;
            str = null;
            String strDecodeStringElement11 = null;
            String strDecodeStringElement12 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i3 = 11;
                        break;
                    case 0:
                        i4 |= 1;
                        strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 1:
                        i4 |= 2;
                        strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 2:
                        c = 3;
                        strDecodeStringElement11 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
                        i4 |= 4;
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 3:
                        c = 3;
                        str = (String) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, FlexibleStringSerializer.INSTANCE, str);
                        i4 |= 8;
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 4:
                        strDecodeStringElement12 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 4);
                        i4 |= 16;
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 5:
                        jLongValue4 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue4))).longValue();
                        i4 |= 32;
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 6:
                        jLongValue5 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue5))).longValue();
                        i4 |= 64;
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 7:
                        strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 7);
                        i4 |= 128;
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 8:
                        jLongValue6 = ((Number) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, FlexibleLongSerializer.INSTANCE, Long.valueOf(jLongValue6))).longValue();
                        i4 |= 256;
                        i3 = 11;
                        i2 = 9;
                        break;
                    case 9:
                        strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, i2);
                        i4 |= 512;
                        i3 = 11;
                        break;
                    case 10:
                        list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 10, kSerializerArr[10], list2);
                        i4 |= 1024;
                        i3 = 11;
                        break;
                    case 11:
                        zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, i3);
                        i4 |= 2048;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            i = i4;
            str2 = strDecodeStringElement9;
            str3 = strDecodeStringElement2;
            long j4 = jLongValue4;
            list = list2;
            str4 = strDecodeStringElement11;
            str5 = strDecodeStringElement12;
            str6 = strDecodeStringElement10;
            j = j4;
            j2 = jLongValue5;
            zDecodeBooleanElement = zDecodeBooleanElement2;
            j3 = jLongValue6;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new Album(i, str3, strDecodeStringElement, str4, str, str5, j, j2, str2, j3, str6, list, zDecodeBooleanElement, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, Album value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        Album.write$Self$app_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
