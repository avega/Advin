package org.advin.engine;

import org.advin.engine.module.ModuleContainer;
import org.advin.engine.module.ModuleManager;
import org.advin.engine.sys.SysTools;
import org.advin.library.interfaces.IAppInterface;

public class Main
{
    public static IAppInterface appIFace = new AppInterface();
    public static ModuleManager modManager = new ModuleManager();
    public static SysTools sysTools = new SysTools();

    public static void main(String[] args)
    {
        //sysTools.logMsg("[?] CLASSPATH: "+ sysTools.getCP());
        // Load module files
        modManager.modPath = sysTools.getArgValue(args, "modpath");
        if (modManager.modPath.isEmpty()) { modManager.modPath = "modules"; };
        modManager.loadModules();

        if (!modManager.modList.isEmpty())
        {
            // Check modules list
            System.out.println("");
            for (ModuleContainer aModList: modManager.modList)
            {
                sysTools.logMsg("[*] Module: " + aModList.getModuleName() +" ver. "+ aModList.getModuleVersion());
                sysTools.logMsg("[*] " + aModList.getModuleName() +" (" + aModList.getModuleInfo() +")");
                sysTools.logMsg("[*] Visual: " + aModList.isVisual() +" / Type: " + aModList.getModuleType());
                sysTools.logMsg("[*] ---");
            };
            // Init modules
            for (ModuleContainer aModList: modManager.modList)
            {
                sysTools.logMsg("[!] Init: " + aModList.getModuleName());
                aModList.moduleClass.initModule(appIFace);
            };
            sysTools.logMsg("[!] ---");
            // Start modules
            for (ModuleContainer aModList: modManager.modList)
            {
                sysTools.logMsg("[>] Start: " + aModList.getModuleName());
                aModList.moduleClass.startModule();
            };
            sysTools.logMsg("[>] ---");
            // Start application loop
            boolean allDone = false;

            try
            {
                while (!allDone) // wait until all modules set isActive to false
                {
                    allDone = true;
                    for (ModuleContainer aModList: modManager.modList)
                    {
                        if (aModList.moduleClass.isActive()) { allDone = false; };
                    };
                    try { Thread.sleep(500); } catch (InterruptedException e) { System.out.println(e.getMessage()); };
                };
            }
            catch (Exception E) { sysTools.logMsg("[E] Error: "+ E.getLocalizedMessage()); }
            finally { sysTools.logMsg("[M] All modules done."); };

            // Done modules
            for (ModuleContainer aModList: modManager.modList)
            {
                sysTools.logMsg("[.] Done: " + aModList.getModuleName());
                aModList.moduleClass.doneModule();
            };
            sysTools.logMsg("[.] ---");
        };
    };
};
