package com.jimmydaddy.imagemarker.base

import android.graphics.BlendMode
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.ReadableMap
import com.jimmydaddy.imagemarker.base.Constants.DEFAULT_ALPHA
import com.jimmydaddy.imagemarker.base.Constants.DEFAULT_ROTATE
import com.jimmydaddy.imagemarker.base.Constants.DEFAULT_SCALE

class ImageOptions(options: ReadableMap) {
  @JvmField
  var src: ReadableMap?

  @JvmField
  var uri: String?

  @JvmField
  var scale: Float

  @JvmField
  var rotate: Float
  var alpha: Int

  init {
    src = options.getMap("src")
    if (src == null) {
      throw MarkerError(ErrorCode.PARAMS_REQUIRED, "image is required")
    }
    uri = src!!.getString(PROP_ICON_URI)
    scale = if (options.hasKey("scale")) options.getDouble("scale").toFloat() else DEFAULT_SCALE
    rotate = if (options.hasKey("rotate")) options.getInt("rotate").toFloat() else DEFAULT_ROTATE
    alpha = if (options.hasKey("alpha")) (options.getDouble("alpha") * 255).toInt() else DEFAULT_ALPHA
  }

  fun applyStyle(): Paint {
    val paint = Paint()
    paint.alpha = alpha
    //获取更清晰的图像采样
    paint.isDither = true
    paint.colorFilter = PorterDuffColorFilter(Color.TRANSPARENT, PorterDuff.Mode.OVERLAY)
    return paint
  }

  companion object {
    const val PROP_ICON_URI = "uri"
  }
}