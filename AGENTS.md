# AGENTS.md

## Project
A Kotlin-first Android application built with Jetpack Compose, Clean Architecture, and an offline-first Room data layer, developed on a Samsung Galaxy S25 using Termux, Shizuku/ADB, and Android Harness[span_0](start_span)[span_0](end_span).

## Build & Run
- Build debug APK with configuration cache: `./gradlew :app:assembleDebug --configuration-cache`
- Run unit tests: `./gradlew test`
- Run lint and style checks: `./gradlew lint detekt spotlessCheck`
- Target toolchain: Kotlin 2.0+ (K2), JVM 17, `minSdk 24`, `targetSdk 35`, `compileSdk 35`, Gradle 8.7+ Kotlin DSL with `gradle/libs.versions.toml` and KSP.

## Conventions
- **Architecture:** Layer Presentation (Compose UI + `StateFlow<UiState>` ViewModels via UDF), pure-Kotlin Domain (`:core:domain` or `:feature:*:domain` interfaces and use cases), and Data (Room DAOs, Retrofit + OkHttp + `kotlinx.serialization`, DTO mappers, and `DataStore` preferences) with Hilt DI.
- **Coroutines & UI:** Use structured concurrency (`viewModelScope`, `lifecycleScope`, injected dispatchers) and `collectAsStateWithLifecycle()`. Keep Composables free of business logic, use `@Immutable`/`@Stable` and `kotlinx.collections.immutable` where applicable, use typed Compose Navigation (`navigation-compose` 2.8+), and wrap UI in a single `AppTheme` with Material 3 dynamic colors (Android 12+) plus a branded fallback.
- **Testing & Skills:** Test ViewModel state transitions with JUnit5, MockK, Turbine, and `kotlinx-coroutines-test`, and check `.github/skills/` (`architecture/`, `ui/`, `performance/`, `migration/`, `testing_and_automation/`, `concurrency_and_networking/`, `build_and_tooling/`) for task-specific conventions.

## Constraints
- Never put business logic inside Composables or use `GlobalScope`.
- Never use `SharedPreferences` in new code (`DataStore` only) or hard-code dependency coordinates when a `gradle/libs.versions.toml` entry applies.
- Never let Room be bypassed as the single source of truth for offline-first state, break `--configuration-cache`, or leave `abortOnError true` lint, detekt, or ktlint issues unresolved.
