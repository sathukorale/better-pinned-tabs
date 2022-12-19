package com.sathukorale.betterpinnedtabs.settings

import com.google.gson.Gson
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xml.ConvertContext
import com.intellij.util.xmlb.Converter
import com.intellij.util.xmlb.XmlSerializerUtil
import com.intellij.util.xmlb.annotations.OptionTag
import com.sathukorale.betterpinnedtabs.model.Color
import com.sathukorale.betterpinnedtabs.model.ColorGsonConverter
import com.sathukorale.betterpinnedtabs.model.ColorToggleableOption
import com.sathukorale.betterpinnedtabs.model.ToggleableOption

@State(
    name = "org.intellij.sdk.settings.AppSettingsState",
    storages = [Storage("SdkSettingsPlugin.xml")]
)
class ApplicationSettings: PersistentStateComponent<ApplicationSettings> {
    @OptionTag(converter = ColorGsonConverter::class)
    var selectedTabColor = ColorToggleableOption(true, DEFAULT_SELECTED_TAB_COLOR)

    @OptionTag(converter = ColorGsonConverter::class)
    var pinnedTabColor = ColorToggleableOption(true, DEFAULT_PINNED_TAB_COLOR)

    override fun getState() = this

    override fun loadState(state: ApplicationSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ApplicationSettings) return false
        if (other.pinnedTabColor != pinnedTabColor) return false
        if (other.selectedTabColor != selectedTabColor) return false
        return true
    }

    companion object {
        val DEFAULT_SELECTED_TAB_COLOR = Color(0, 0, 92)
        val DEFAULT_PINNED_TAB_COLOR = Color(92, 0, 0)

        fun getInstance(): ApplicationSettings? =
            ApplicationManager.getApplication().getService(ApplicationSettings::class.java)
    }
}