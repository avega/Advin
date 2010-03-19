package org.advin.modules;

import org.advin.library.interfaces.*;

public class ModuleInfo implements IModuleInfo
{
    @Override
    public String getModuleName()      { return "NetServer"; };
    @Override
    public String getModuleFullName()  { return "Network Server"; };
    @Override
    public String getModuleInfo()      { return "Handle network connections"; };
    @Override
    public String getModuleVersion()   { return "1.0"; };
    @Override
    public String getModuleClass()     { return "org.advin.modules.netserver.NetServer"; };
    @Override
    public eModuleType getModuleType() { return eModuleType.NetWorkMod; };
    @Override
    public boolean isVisual()          { return false; };
};
