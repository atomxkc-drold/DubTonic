# Keep Media3, Retrofit and kotlinx.serialization metadata used at runtime.
-keep class androidx.media3.session.** { *; }
-keep class com.google.common.util.concurrent.** { *; }
-keepattributes *Annotation*, InnerClasses, Signature
-keep,includedescriptorclasses class com.androidharness.musicplayer.**$$serializer { *; }
-keepclassmembers class com.androidharness.musicplayer.** {
    *** Companion;
}
-keepclasseswithmembers class com.androidharness.musicplayer.** {
    kotlinx.serialization.KSerializer serializer(...);
}
