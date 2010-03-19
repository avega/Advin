package org.advin.engine;

import org.advin.library.interfaces.IAppInterface;
import org.advin.library.interfaces.IAdvinModule;

public class AppInterface implements IAppInterface
{
    @Override
    public String getAppVersion() { return "1.0"; };
    @Override
    public void invokeMethod(String param) { System.out.println("Invoked method - ["+ param +"]"); }
    @Override
    public void logMsg(String message) { Main.sysTools.logMsg(message); };
    @Override
    public IAdvinModule getModule(String name)
    {
        for(ModuleContainer modCont: Main.modManager.modList)
        {
            if (modCont.moduleInfo.getModuleName().equalsIgnoreCase(name)) return modCont.moduleClass;
        };
        return null;
    };
};
