package org.advin.engine.module;

import org.advin.library.interfaces.IModuleInfo;
import org.advin.library.interfaces.IAdvinModule;

public class ModuleContainer
{
    public IModuleInfo moduleInfo = null;
    public IAdvinModule moduleClass = null;
    public Class asClass = null;

    public ModuleContainer() { };

    public ModuleContainer(Class module, IModuleInfo info)
    {
        moduleInfo = info;
        moduleClass = null;
        asClass = module;
    };

    public ModuleContainer(IAdvinModule module, IModuleInfo info)
    {
        moduleInfo = info;
        moduleClass = module;
        asClass = null;
    };
};
