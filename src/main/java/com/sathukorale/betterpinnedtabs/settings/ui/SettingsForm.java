package com.sathukorale.betterpinnedtabs.settings.ui;

import com.intellij.ui.ColorPanel;
import com.sathukorale.betterpinnedtabs.model.Color;
import com.sathukorale.betterpinnedtabs.settings.ApplicationSettings;

import javax.swing.*;
import java.awt.*;

public class SettingsForm extends JPanel {
    private JCheckBox chkSelectedTabColor;
    private JCheckBox chkPinnedTabColor;
    private ColorPanel btnSelectedTabColor;
    private ColorPanel btnPinnedTabColor;
    private JPanel pnlMain;

    public SettingsForm() {
        super(new BorderLayout());
        add(pnlMain, BorderLayout.CENTER);
    }

    public void apply(ApplicationSettings settings) {
        chkSelectedTabColor.setSelected(settings.getSelectedTabColor().getEnabled());
        chkPinnedTabColor.setSelected(settings.getPinnedTabColor().getEnabled());
        btnSelectedTabColor.setSelectedColor(settings.getSelectedTabColor().getValue().to());
        btnPinnedTabColor.setSelectedColor(settings.getPinnedTabColor().getValue().to());
    }

    public ApplicationSettings getSettings() {
        var settings = new ApplicationSettings();
        settings.getSelectedTabColor().setEnabled(chkSelectedTabColor.isSelected());
        settings.getPinnedTabColor().setEnabled(chkPinnedTabColor.isSelected());
        settings.getSelectedTabColor().setValue(Color.Companion.from(btnSelectedTabColor.getSelectedColor()));
        settings.getPinnedTabColor().setValue(Color.Companion.from(btnPinnedTabColor.getSelectedColor()));
        return settings;
    }
}
