package org.advin.engine;

import org.advin.library.interfaces.IAdvinModule;
import org.advin.library.interfaces.IModuleInfo;
import org.advin.library.interfaces.AdvinModuleClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ModuleManager
{
    public String modPath = "modules";
    public String modInfoClassName = "org.advin.modules.ModuleInfo";
    public List<ModuleContainer> modList = null; // List of plugin modules
    public List<URL> modFiles = null;            // List of module files
    public SysTools sysTools = new SysTools();

    public ModuleManager()
    {
        modList = new ArrayList<ModuleContainer>();
        modFiles = new ArrayList<URL>();
    };

    private int fillModuleFileList(String aPath)
    {
        modFiles.clear();
        String modDir = SysTools.updateFileSeporator(SysInfo.getUserDir() + SysInfo.getFileSeparator() + aPath);
        File[] dirFiles = null;
        
        sysTools.logMsg("[>] Searching modules in: "+ modDir);
        try
        { dirFiles = (new File(modDir).listFiles()); }
        catch (Exception exc) { sysTools.logMsg(exc.getMessage()); };
        
        if (!(dirFiles == null || dirFiles.length == 0))
        {
            for (File someFile: dirFiles)
            {
                if (someFile.getName().toLowerCase().endsWith(".jar"))
                {
                    getJarProperties(someFile.getPath());
                    try
                    { if (loadInfoClassFromJAR(someFile.toURI().toURL()) != null) modFiles.add(someFile.toURI().toURL()); }
                    catch (Exception vExc)
                    { sysTools.logMsg("Can't load file: "+ someFile.getName() +" - "+ vExc.getMessage()); };
                };
            };
        };

        return modFiles.size();
    };

    /**
     * Load module info class from given JAR file
     * @param aJARFile file which contain module info class
     * @return Info class from module as <code>IModuleInfo</code> interface
     */
    private IModuleInfo loadInfoClassFromJAR(URL aJARFile)
    {
        Class modInfoClass = null;
        IModuleInfo modInfo = null;
        
        try
        { modInfoClass = loadClassFromJAR(aJARFile, modInfoClassName); }
        catch (Exception exc)
        { sysTools.logMsg("[!] Error load "+ modInfoClassName +": " + exc.getMessage()); };

        if (modInfoClass != null)
        {
            // Create module info object
            try
            {
                Object _obj = modInfoClass.newInstance();
                modInfo = (IModuleInfo) _obj;
            }
            catch (Exception exc) { sysTools.logMsg("[!] Can't cast class to IModuleInfo interface\n" + exc.getMessage()); };
        };
        
        return modInfo;
    };

    /**
     * Load module class from given JAR file
     * @param jarFile file which contain class
     * @param className class name in module
     * @return loaded class of module
     * @throws java.lang.ClassNotFoundException
     */
    private Class loadClassFromJAR(URL jarFile, String className) throws Exception
    {
        URLClassLoader loader = null;
        Class _class = null;

        if (jarFile != null)
        {
            try
            { loader = new URLClassLoader(new URL[] {jarFile}); }
            catch (Exception exc)
            { sysTools.logMsg("[!] Error create ClassLoader for class '"+ className +"' in file - '"+ jarFile.getFile() +"' : "+ exc.getMessage()); };

            if (loader != null)
            {
                try
                { _class = loader.loadClass(className); }
                catch (Exception exc)
                { sysTools.logMsg("[!] Can't load class "+ className +" from "+ jarFile.getFile() +" : "+ exc.getMessage()); };
            };
        };
        if (_class.isAnnotationPresent(AdvinModuleClass.class)) sysTools.logMsg("GOT ANNOTATION!!!");
        return _class;
    };

    private void addToList(IModuleInfo info, Class aClass)
    {
        try
        {
            // no difference yet, just add
            switch (info.getModuleType())
            {
                case DataSourceMod:
                    modList.add(new ModuleContainer((IAdvinModule) aClass.newInstance(), info));
                    break;
                case NetWorkMod:
                    modList.add(new ModuleContainer((IAdvinModule) aClass.newInstance(), info));
                    break;
                case GUIMod:
                    modList.add(new ModuleContainer((IAdvinModule) aClass.newInstance(), info));
                    break;
                default:
                    modList.add(new ModuleContainer(aClass, info));
                    break;
            };
        } catch (Exception exc) { System.out.println(exc.getMessage()); };
    };

    public void loadModules()
    {
        if (fillModuleFileList(modPath) != 0)
        {
            for (URL modFile: modFiles)
            {
                IModuleInfo modInfo = loadInfoClassFromJAR(modFile);
                if (modInfo != null)
                {
                    try
                    {
                        Class _class = loadClassFromJAR(modFile, modInfo.getModuleClass());
                        if (_class != null) { addToList(modInfo, _class); };
                    }
                    catch (Exception exc)
                    { sysTools.logMsg("[!] Error load module class " + modInfo.getModuleClass() + " : " + exc.getMessage()); };
                };
            };
        } else { sysTools.logMsg("No modules found."); };
    };

    public Properties getJarProperties(String fileName)
    {
        Properties jarProps = new Properties();
        try
        {
            JarFile jarfile = new JarFile(fileName);
            Manifest manifest = jarfile.getManifest();
            Attributes jarAttr = manifest.getMainAttributes();

            Iterator it = jarAttr.keySet().iterator();
            while (it.hasNext())
            {
                Attributes.Name name = (Attributes.Name)it.next();
                jarProps.put(name.toString(), jarAttr.getValue(name));
            };
            //jarProps.list(System.out); // DEBUG OUTPUT
        } catch (IOException e) { System.out.println(e.getMessage()); };

        return jarProps;
    };
};
