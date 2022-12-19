package com.sathukorale.betterpinnedtabs.model

import com.google.gson.Gson
import com.intellij.util.xmlb.Converter

open class ColorGsonConverter : Converter<ColorToggleableOption>() {
    override fun toString(value: ColorToggleableOption) = Gson().toJson(value)!!
    override fun fromString(value: String): ColorToggleableOption? = Gson().fromJson(value, ColorToggleableOption::class.java)
}