package org.advin.engine.module;

import org.advin.library.interfaces.IAdvinModule;
import java.util.Properties;

public class ModuleContainer
{
    public Properties moduleInfo = null;
    public IAdvinModule moduleClass = null;
    public Class asClass = null;

    private ModuleContainer() { };

    public ModuleContainer(Class module, Properties info)
    {
        moduleInfo = info;
        moduleClass = null;
        asClass = module;
    };

    public ModuleContainer(IAdvinModule module, Properties info)
    {
        moduleInfo = info;
        moduleClass = module;
        asClass = null;
    };

    public String getModuleClassName()
    {
        return moduleInfo.getProperty("AIModule-Class", "");
    };

    public String getModuleName()
    {
        return moduleInfo.getProperty("AIModule-Name", "");
    };

    public String getModuleVersion()
    {
        return moduleInfo.getProperty("AIModule-Version", "");
    };

    public String getModuleInfo()
    {
        return moduleInfo.getProperty("AIModule-Info", "");
    };

    public String getModuleType()
    {
        return moduleInfo.getProperty("AIModule-Type", "");
    };

    public String isVisual()
    {
        return moduleInfo.getProperty("AIModule-Visual", "");
    };
};
