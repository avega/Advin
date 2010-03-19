package org.advin.library.interfaces;

import javax.swing.JPanel;

public interface IAdvinGUIModule extends IAdvinModule
{
    void addModPanel(String aName, JPanel aPanel);
    void setStatusText(String aText);
};
