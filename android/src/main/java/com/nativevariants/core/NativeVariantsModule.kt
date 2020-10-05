package com.nativevariants.core

import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.lang.Exception

class NativeVariantsModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  private fun getId(name: String, type: String): Int =
    reactApplicationContext
      .resources
      .getIdentifier(name, type, reactApplicationContext.packageName)

  private fun getString(id: Int, default: String): String {
    val result = reactApplicationContext
      .resources
      .getString(id)
    return if (result.isNullOrEmpty()) default else result
  }

  @ReactMethod
  fun getString(key: String, default: String, promise: Promise) {
    try {
      val id = getId(key, "string")
      promise.resolve(getString(id, default))
    } catch (e: Exception) {
      promise.reject(e)
    }
  }

  @ReactMethod
  fun getColor(key: String, default: String, promise: Promise) {
    try {
      val id = getId(key, "color")
      val color = ContextCompat.getColor(reactApplicationContext, id)
      val result = String.format("#%06x", color and 0xffffff)
      promise.resolve(result)

    } catch (e: Resources.NotFoundException) {
      promise.reject(e.hashCode().toString(), "Invalid Key: $key")
    } catch (e: Exception) {
      promise.reject(e)
    }
  }

  override fun getName(): String = "NativeVariants"
}
