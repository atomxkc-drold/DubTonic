# DubTonic

Android music player app built with Jetpack Compose, Media3, and Kotlin.

## Building via GitHub Actions

This repo includes a GitHub Actions workflow (`.github/workflows/build-apk.yml`) that builds the APK automatically.

### How to use

1. **Push your Android Studio project** to this repo (including `build.gradle.kts`, `settings.gradle.kts`, `app/` source, `gradle/wrapper/`, etc.)
2. **Go to the Actions tab** on GitHub — the build will start automatically on push to `main`
3. **Download the APK** from the workflow run's Artifacts section
4. **Install on your phone** — transfer the APK and enable "Install unknown apps" in Settings

### Manual trigger

You can also trigger a build manually:
- Go to **Actions** → **Build DubTonic APK** → **Run workflow**

### Local build (if you have Android Studio)

```bash
./gradlew assembleRelease
```

The APK will be at `app/build/outputs/apk/release/`.

## Project structure (expected)

```
├── .github/workflows/build-apk.yml
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/dubtonic/player/
│       └── res/
├── build.gradle.kts
├── settings.gradle.kts
└── gradle/wrapper/
```
