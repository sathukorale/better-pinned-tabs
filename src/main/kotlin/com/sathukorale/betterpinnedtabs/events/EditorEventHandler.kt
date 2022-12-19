package com.sathukorale.betterpinnedtabs.events

import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.ui.tabs.TabInfo
import com.sathukorale.betterpinnedtabs.settings.ApplicationSettings
import com.sathukorale.betterpinnedtabs.settings.events.SettingsChangedListener
import java.awt.Color

class EditorEventHandler : StartupActivity, FileEditorManagerListener, SettingsChangedListener {
    private var project: Project? = null

    override fun runActivity(project: Project) {
        this.project = project
        project.messageBus.connect(project).subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, this)
        project.messageBus.connect(project).subscribe(SettingsChangedListener.TOPIC, this)
    }

    override fun selectionChanged(event: FileEditorManagerEvent) {
        onThingsChange()
    }

    override fun settingsChanged(settings: ApplicationSettings) {
        onThingsChange()
    }

    private fun onThingsChange() {
        if (this.project == null) return

        val settings = ApplicationSettings.getInstance() ?: return
        val windows = FileEditorManagerEx.getInstanceEx(this.project!!).windows

        windows.forEach { window ->
            for (i in 0 until window.tabbedPane.tabCount) {
                val tab = window.tabbedPane.tabs.getTabAt(i)
                val tabColor = getTabColor(window, tab, settings)
                tab.tabColor = tabColor
                tab.defaultForeground = if (tabColor != null) findContrastColor(tabColor) else null
            }
        }
    }

    companion object {
        fun getTabColor(window: EditorWindow, tab: TabInfo, settings: ApplicationSettings) : Color? {
            return if (tab == window.tabbedPane.tabs.selectedInfo) {
                settings.selectedTabColor.let { if (it.enabled) it.value?.to() else null }
            }
            else if (tab.isPinned) {
                settings.pinnedTabColor.let { if (it.enabled) it.value?.to() else null }
            }
            else null
        }

        /* from: https://stackoverflow.com/questions/1855884/determine-font-color-based-on-background-color */
        fun findContrastColor(color: Color): Color {
            val luminance: Double = (0.299 * color.red + 0.587 * color.green + 0.114 * color.blue) / 255
            val d = if (luminance > 0.5) 0 else 255
            return Color(d, d, d)
        }
    }
}