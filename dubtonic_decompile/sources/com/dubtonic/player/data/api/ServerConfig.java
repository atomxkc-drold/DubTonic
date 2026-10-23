package com.dubtonic.player.data.api;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: SubsonicClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 12\u00020\u0001:\u000201BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bBU\b\u0010\u0012\u0006\u0010\f\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003JE\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÇ\u0001J\u0013\u0010$\u001a\u00020\u00192\b\u0010%\u001a\u0004\u0018\u00010\u0001H×\u0003J\t\u0010&\u001a\u00020\tH×\u0001J\t\u0010'\u001a\u00020\u0003H×\u0001J%\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0001¢\u0006\u0002\b/R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u00062"}, d2 = {"Lcom/dubtonic/player/data/api/ServerConfig;", "", TtmlNode.ATTR_ID, "", HintConstants.AUTOFILL_HINT_NAME, "url", HintConstants.AUTOFILL_HINT_USERNAME, HintConstants.AUTOFILL_HINT_PASSWORD, "bitrate", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getName", "getUrl", "getUsername", "getPassword", "getBitrate", "()I", "configured", "", "getConfigured", "()Z", "normalizedBase", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class ServerConfig {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int bitrate;
    private final String id;
    private final String name;
    private final String password;
    private final String url;
    private final String username;

    public ServerConfig() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, 0, 63, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ ServerConfig copy$default(ServerConfig serverConfig, String str, String str2, String str3, String str4, String str5, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = serverConfig.id;
        }
        if ((i2 & 2) != 0) {
            str2 = serverConfig.name;
        }
        String str6 = str2;
        if ((i2 & 4) != 0) {
            str3 = serverConfig.url;
        }
        String str7 = str3;
        if ((i2 & 8) != 0) {
            str4 = serverConfig.username;
        }
        String str8 = str4;
        if ((i2 & 16) != 0) {
            str5 = serverConfig.password;
        }
        String str9 = str5;
        if ((i2 & 32) != 0) {
            i = serverConfig.bitrate;
        }
        return serverConfig.copy(str, str6, str7, str8, str9, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getBitrate() {
        return this.bitrate;
    }

    public final ServerConfig copy(String id, String name, String url, String username, String password, int bitrate) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        return new ServerConfig(id, name, url, username, password, bitrate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServerConfig)) {
            return false;
        }
        ServerConfig serverConfig = (ServerConfig) other;
        return Intrinsics.areEqual(this.id, serverConfig.id) && Intrinsics.areEqual(this.name, serverConfig.name) && Intrinsics.areEqual(this.url, serverConfig.url) && Intrinsics.areEqual(this.username, serverConfig.username) && Intrinsics.areEqual(this.password, serverConfig.password) && this.bitrate == serverConfig.bitrate;
    }

    public int hashCode() {
        return (((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.url.hashCode()) * 31) + this.username.hashCode()) * 31) + this.password.hashCode()) * 31) + Integer.hashCode(this.bitrate);
    }

    public String toString() {
        return "ServerConfig(id=" + this.id + ", name=" + this.name + ", url=" + this.url + ", username=" + this.username + ", password=" + this.password + ", bitrate=" + this.bitrate + ")";
    }

    /* JADX INFO: compiled from: SubsonicClient.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dubtonic/player/data/api/ServerConfig$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/dubtonic/player/data/api/ServerConfig;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<ServerConfig> serializer() {
            return ServerConfig$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ ServerConfig(int i, String str, String str2, String str3, String str4, String str5, int i2, SerializationConstructorMarker serializationConstructorMarker) {
        this.id = (i & 1) == 0 ? "default" : str;
        if ((i & 2) == 0) {
            this.name = "";
        } else {
            this.name = str2;
        }
        if ((i & 4) == 0) {
            this.url = "";
        } else {
            this.url = str3;
        }
        if ((i & 8) == 0) {
            this.username = "";
        } else {
            this.username = str4;
        }
        if ((i & 16) == 0) {
            this.password = "";
        } else {
            this.password = str5;
        }
        if ((i & 32) == 0) {
            this.bitrate = 0;
        } else {
            this.bitrate = i2;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_release(ServerConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.id, "default")) {
            output.encodeStringElement(serialDesc, 0, self.id);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.name, "")) {
            output.encodeStringElement(serialDesc, 1, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.url, "")) {
            output.encodeStringElement(serialDesc, 2, self.url);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.username, "")) {
            output.encodeStringElement(serialDesc, 3, self.username);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.password, "")) {
            output.encodeStringElement(serialDesc, 4, self.password);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 5) && self.bitrate == 0) {
            return;
        }
        output.encodeIntElement(serialDesc, 5, self.bitrate);
    }

    public ServerConfig(String id, String name, String url, String username, String password, int i) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        this.id = id;
        this.name = name;
        this.url = url;
        this.username = username;
        this.password = password;
        this.bitrate = i;
    }

    public /* synthetic */ ServerConfig(String str, String str2, String str3, String str4, String str5, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "default" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) == 0 ? str5 : "", (i2 & 32) != 0 ? 0 : i);
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getPassword() {
        return this.password;
    }

    public final int getBitrate() {
        return this.bitrate;
    }

    public final boolean getConfigured() {
        return (StringsKt.isBlank(this.url) || StringsKt.isBlank(this.username)) ? false : true;
    }

    public final String normalizedBase() {
        String string = StringsKt.trim((CharSequence) this.url).toString();
        if (string.length() == 0) {
            return null;
        }
        if (!StringsKt.startsWith$default(string, "http://", false, 2, (Object) null) && !StringsKt.startsWith$default(string, "https://", false, 2, (Object) null)) {
            string = "http://" + string;
        }
        HttpUrl httpUrl = HttpUrl.INSTANCE.parse(string);
        if (httpUrl == null) {
            return null;
        }
        String strEncodedPath = httpUrl.encodedPath();
        if (StringsKt.endsWith$default(strEncodedPath, "/", false, 2, (Object) null)) {
            strEncodedPath = StringsKt.dropLast(strEncodedPath, 1);
        }
        if (Intrinsics.areEqual(StringsKt.substringAfterLast$default(strEncodedPath, '/', (String) null, 2, (Object) null), "rest")) {
            strEncodedPath = StringsKt.substringBeforeLast(strEncodedPath, '/', "");
        }
        HttpUrl.Builder builderNewBuilder = httpUrl.newBuilder();
        String str = strEncodedPath;
        if (str.length() == 0) {
            str = "/";
        }
        String url = builderNewBuilder.encodedPath(str).query(null).fragment(null).build().getUrl();
        while (StringsKt.endsWith$default(url, "/", false, 2, (Object) null)) {
            url = StringsKt.dropLast(url, 1);
        }
        return url;
    }
}
