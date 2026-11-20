package com.androidharness.musicplayer.ui.theme

import androidx.compose.animation.core.SnapSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.ui.graphics.Color
import com.androidharness.musicplayer.data.settings.UiSettings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SpringSpecsTest {

    @Test
    fun reducedMotionRemovesAnimationEntirely() {
        val specs = SpringSpecs(UiSettings(reducedMotion = true))

        assertTrue(specs.reducedMotion)
        assertTrue(specs.gentle<Float>() is SnapSpec<*>)
        assertTrue(specs.snappy<Float>() is SnapSpec<*>)
        assertTrue(specs.slow<Float>() is SnapSpec<*>)
    }

    @Test
    fun normalMotionKeepsSpringSpecs() {
        val specs = SpringSpecs(UiSettings())

        assertFalse(specs.reducedMotion)
        assertTrue(specs.gentle<Float>() is SpringSpec<*>)
        assertTrue(specs.snappy<Float>() is SpringSpec<*>)
        assertTrue(specs.slow<Float>() is SpringSpec<*>)
    }

    @Test
    fun animationScaleSlowsSpringsDown() {
        val fast = SpringSpecs(UiSettings(animationScale = 0.25f)).gentle<Float>()
        val normal = SpringSpecs(UiSettings(animationScale = 1f)).gentle<Float>()
        val slow = SpringSpecs(UiSettings(animationScale = 2f)).gentle<Float>()

        val fastStiffness = (fast as SpringSpec<Float>).stiffness
        val normalStiffness = (normal as SpringSpec<Float>).stiffness
        val slowStiffness = (slow as SpringSpec<Float>).stiffness

        assertTrue(fastStiffness > normalStiffness)
        assertTrue(normalStiffness > slowStiffness)
        assertTrue(slowStiffness > 0f)
    }

    @Test
    fun reducedMotionOverridesAnimationScale() {
        val specs = SpringSpecs(UiSettings(animationScale = 2f, reducedMotion = true))
        assertTrue(specs.slow<Float>() is SnapSpec<*>)
    }

    @Test
    fun saturatePullsChannelsTowardsLuminance() {
        val gray = Color(0.5f, 0.5f, 0.5f)
        assertEquals(gray.red, saturate(gray, 2f).red, 0.001f)

        val flat = saturate(Color(0.8f, 0.2f, 0.2f), 0f)
        assertEquals(flat.red, flat.green, 0.001f)
        assertEquals(flat.red, flat.blue, 0.001f)
    }
}
