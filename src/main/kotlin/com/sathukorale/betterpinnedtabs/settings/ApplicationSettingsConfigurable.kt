package com.sathukorale.betterpinnedtabs.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.Configurable
import com.sathukorale.betterpinnedtabs.settings.events.SettingsChangedListener
import com.sathukorale.betterpinnedtabs.settings.ui.SettingsForm
import javax.swing.JComponent

class ApplicationSettingsConfigurable : Configurable {
    private var form: SettingsForm? = null
    override fun createComponent(): JComponent {
        return SettingsForm().also { form = it }
    }

    override fun isModified() = form?.settings != ApplicationSettings.getInstance()

    override fun apply() {
        form?.settings?.let {
            ApplicationSettings.getInstance()?.loadState(it)
            ApplicationManager.getApplication().messageBus.syncPublisher(SettingsChangedListener.TOPIC).settingsChanged(it)
        }
    }

    override fun reset() {
        ApplicationSettings.getInstance()?.let {
            form?.apply(it)
        }
    }

    override fun getDisplayName() = "Better Pinned Tabs"
}