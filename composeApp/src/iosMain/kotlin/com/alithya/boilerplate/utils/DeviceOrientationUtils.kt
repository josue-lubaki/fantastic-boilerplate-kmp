package com.alithya.boilerplate.utils

import platform.UIKit.UIDevice
import platform.UIKit.UIDeviceOrientation

/**
 * created by Josue Lubaki
 * date : 2023-12-11
 * version : 1.0.0
 */

fun getDeviceOrientation(): DeviceOrientation {
    return when (UIDevice.currentDevice.orientation) {
        UIDeviceOrientation.UIDeviceOrientationLandscapeRight -> DeviceOrientation(
            portrait = false,
            landscape = true
        )

        UIDeviceOrientation.UIDeviceOrientationPortraitUpsideDown -> DeviceOrientation(
            portrait = true,
            landscape = false
        )

        UIDeviceOrientation.UIDeviceOrientationPortrait -> DeviceOrientation(
            portrait = true,
            landscape = false
        )

        UIDeviceOrientation.UIDeviceOrientationLandscapeLeft -> DeviceOrientation(
            portrait = false,
            landscape = true
        )

        else -> DeviceOrientation(portrait = true, landscape = false)
    }
}

data class DeviceOrientation(val portrait: Boolean, val landscape: Boolean)