package com.sathukorale.betterpinnedtabs.settings.events

import com.intellij.util.messages.Topic
import com.sathukorale.betterpinnedtabs.settings.ApplicationSettings

interface SettingsChangedListener {
    private fun beforeSettingsChanged(settings: ApplicationSettings) { }
    fun settingsChanged(settings: ApplicationSettings)

    companion object {
        val TOPIC: Topic<SettingsChangedListener> = Topic.create("Highlighter Topic", SettingsChangedListener::class.java)
    }
}