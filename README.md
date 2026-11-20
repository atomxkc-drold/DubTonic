# DubTonic

A Subsonic-compatible music player for Android, built with Kotlin and Jetpack Compose.

DubTonic talks to any Subsonic / Navidrome style server, builds its own offline index of the
library and plays through Media3 (ExoPlayer) with a MediaSession so playback survives the app
being backgrounded.

## Highlights

- **Home wall** — the hero changes with the time of day (headline, copy, aurora warmth) and,
  while a track is playing, the kicker line reports on that artist using facts read from your
  own tags: how many of their tracks you own, which years, the dominant genre, lossless rips.
  A new song usually lands on a different fact.
- **Library** — search, sort and filter across the synced collection.
- **Player** — Media3 playback service, queue, shuffle/repeat, mini player, notification.
- **Mixes** — "radio from your shelf": local vibe stacks and era dials generated from the
  library itself, no server-side smart playlists required.
- **Settings** — server credentials, audio and appearance options.

## Stack

Kotlin, Jetpack Compose (Material 3), Hilt + KSP, Room, Retrofit + kotlinx.serialization,
DataStore, Media3, Coil. `compileSdk` 36, `minSdk` 26.

## Build

```bash
./gradlew :app:assembleDebug
```

Point `sdk.dir` in `local.properties` at your Android SDK, and use a JDK 17–21 for Gradle.
Unit tests: `./gradlew :app:testDebugUnitTest`.

## Status

Work in progress. The debug build uses the `com.androidharness.musicplayer.debug` application id.
