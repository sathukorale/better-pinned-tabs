package com.sathukorale.betterpinnedtabs.model

import java.io.Serializable

open class ToggleableOption<T: Serializable>(var enabled: Boolean): Serializable {
    var value: T? = null
    private var defaultValue: T? = null

    constructor() : this(false, null)

    constructor(enabled: Boolean, defaultValue: T?) : this(enabled) {
        this.value = defaultValue
        this.defaultValue = defaultValue
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ToggleableOption<*>) return false
        if (other.enabled != enabled) return false
        return (other.value == value)
    }

    override fun hashCode(): Int {
        var result = value?.hashCode() ?: 0
        result = 31 * result + enabled.hashCode()
        result = 31 * result + (defaultValue?.hashCode() ?: 0)
        return result
    }
}