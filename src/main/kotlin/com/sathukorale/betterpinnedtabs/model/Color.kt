package com.sathukorale.betterpinnedtabs.model

import java.io.Serializable

data class Color (var r: Int, var g: Int, var b: Int) : Serializable {
    constructor() : this(0, 0, 0)

    fun to() = java.awt.Color(r, g, b)

    companion object {
        fun from(color: java.awt.Color) = Color(color.red, color.green, color.blue)
    }
}