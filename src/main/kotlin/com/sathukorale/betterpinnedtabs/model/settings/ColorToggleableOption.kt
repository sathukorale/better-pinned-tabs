package com.sathukorale.betterpinnedtabs.model

class ColorToggleableOption(enabled: Boolean, defaultValue: Color?) : ToggleableOption<Color>(enabled, defaultValue) {
    constructor() : this(false, null)
}