package com.alithya.boilerplate.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.alithya.boilerplate.utils.getDeviceOrientation
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.copy
import kotlinx.cinterop.zeroValue
import platform.CoreGraphics.CGRect
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UINavigationBar
import platform.UIKit.UIView
import platform.UIKit.UIWindow
import platform.UIKit.statusBarManager

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun StatusBarColors(statusBarColor: Color, navBarColor: Color) {
    val statusBarView = statusBarView()

    SideEffect {
        statusBarView.backgroundColor = statusBarColor.toUIColor()
        UIApplication.sharedApplication.keyWindow?.addSubview(statusBarView)
        UINavigationBar.appearance().backgroundColor = navBarColor.toUIColor()
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
private fun statusBarView() = remember {
    val keyWindow: UIWindow? =
        UIApplication.sharedApplication.windows.firstOrNull { (it as? UIWindow)?.isKeyWindow() == true } as? UIWindow
    val safeFrameSize = mutableStateOf(0.0)

    keyWindow?.safeAreaLayoutGuide?.layoutFrame?.copy {
        // Getting safe area size in case of landscape and portrait
        if (getDeviceOrientation().portrait) {
            safeFrameSize.value = origin.y
        } else {
            safeFrameSize.value = origin.x
        }
    }

    val height = keyWindow?.windowScene?.statusBarManager?.statusBarFrame ?: zeroValue<CGRect>()
    val statusBarView = UIView(
        height.copy {
            this.size.height = safeFrameSize.value
        }
    )

    keyWindow?.addSubview(statusBarView)
    statusBarView
}

private fun Color.toUIColor(): UIColor = UIColor(
    red = this.red.toDouble(),
    green = this.green.toDouble(),
    blue = this.blue.toDouble(),
    alpha = this.alpha.toDouble(),
)