package org.advin.library.interfaces;

public interface IModuleInfo
{
    public String getModuleName();
    public String getModuleFullName();
    public String getModuleInfo();
    public String getModuleVersion();
    public String getModuleClass();
    public eModuleType getModuleType();
    public boolean isVisual();
};
