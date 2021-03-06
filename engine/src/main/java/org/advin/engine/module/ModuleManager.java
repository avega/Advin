package org.advin.engine.module;

import org.advin.engine.sys.SysInfo;
import org.advin.engine.sys.SysTools;
import org.advin.library.interfaces.AIModuleType;
import org.advin.library.interfaces.IAdvinModule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ModuleManager
{
    public String modPath = "modules";
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
        File[] dirFiles;
        File fmd = new File(modDir);

        try
        {
            modDir = fmd.getCanonicalPath();
        } catch (IOException e) { e.printStackTrace(); };

        sysTools.logMsg(">", "Searching modules in: "+ modDir);
        dirFiles = findAdvinModFiles(modDir);

        if (dirFiles != null)
        {
            for (File someFile: dirFiles)
            {
                try
                { if (getJarProperties(someFile.getPath()).containsKey("AIModule-Class") ) { modFiles.add(someFile.toURI().toURL()); }; }
                catch (Exception vExc)
                { sysTools.logMsg("E", "Can't load file: "+ someFile.getName() +" - "+ vExc.getMessage()); };
            };
        }
        else
        { sysTools.logMsg("E", "Directory not exists: "+ modDir); };

        return modFiles.size();
    };

    /**
     * Goes through all subdirs from <code>startDir</code> as the start level and collect all files that have .jar extension.
     * @param startDir start point to search JAR files.
     * @return List of JAR files or <code>null</code> if no files founded.
     */
    private File[] findAdvinModFiles(String startDir)
    {
        ArrayList<File> ffiles = new ArrayList<File>();
        try
        {
            File[] dfiles = (new File(startDir).listFiles());
            if (dfiles != null)
            {
                for (File someFile: dfiles)
                {
                    if (someFile.isFile())
                    {
                        if (someFile.getName().toLowerCase().endsWith(".jar") && getJarProperties(someFile.getPath()).containsKey("AIModule-Class"))
                            { ffiles.add(someFile); };
                    }
                    else if (someFile.isDirectory())
                    {
                        File[] subJars = findAdvinModFiles(someFile.getPath());
                        ffiles.addAll(Arrays.asList(subJars));
                    };
                };
            };
        }
        catch (Exception exc) { sysTools.logMsg("E", "JAR files search error: "+ exc.getMessage()); };

        File[] jars = new File[ffiles.size()];
        int i = 0;
        for (File f : ffiles)
        {
            jars[i] = f;
            i++;
        };

        return jars;
    };

    /**
     * Load module class from given JAR file
     * @param jarFile file which contain class
     * @param className class name in module
     * @return loaded class of module
     * @throws java.lang.ClassNotFoundException throws class not found exception
     */
    //@SuppressWarnings("unchecked")
    private Class loadClassFromJAR(URL jarFile, String className) throws Exception
    {
        URLClassLoader loader = null;
        Class _class = null;

        if (jarFile != null)
        {
            try
            { loader = new URLClassLoader(new URL[] {jarFile}); }
            catch (Exception exc)
            { sysTools.logMsg("E", "Error create ClassLoader for class '"+ className +"' in file - '"+ jarFile.getFile() +"' : "+ exc.getMessage()); };

            if (loader != null)
            {
                try
                { _class = loader.loadClass(className); }
                catch (Exception exc)
                { sysTools.logMsg("E", "Can't load class "+ className +" from "+ jarFile.getFile() +" : "+ exc.getMessage()); };
            };
        };
        //if ((_class != null) && (_class.isAnnotationPresent(AdvinModuleClass.class))) { sysTools.logMsg("GOT ANNOTATION!!!"); };
        return _class;
    };

    private void addToList(Properties info, Class aClass)
    {
        try
        {
            // no difference yet, just add
            switch (AIModuleType.valueOf(info.getProperty("AIModule-Type", "Module")))
            {
                case DataSource: modList.add(new ModuleContainer((IAdvinModule) aClass.newInstance(), info)); break;
                case NetWork:    modList.add(new ModuleContainer((IAdvinModule) aClass.newInstance(), info)); break;
                case GUI:        modList.add(new ModuleContainer((IAdvinModule) aClass.newInstance(), info)); break;
                default:         modList.add(new ModuleContainer(aClass, info)); break;
            };
        } catch (Exception exc) { sysTools.logMsg("E", exc.getMessage()); };
    };

    public void loadModules()
    {
        if (fillModuleFileList(modPath) != 0)
        {
            for (URL modFile: modFiles)
            {
                Properties modInfo = getJarProperties(modFile.getFile());
                if (modInfo != null)
                {
                    try
                    {
                        Class _class = loadClassFromJAR(modFile, modInfo.getProperty("AIModule-Class", ""));
                        if (_class != null) { addToList(modInfo, _class); };
                    }
                    catch (Exception exc)
                    { sysTools.logMsg("E", "Error load module class " + modInfo.getProperty("AIModule-Class", "") + " : " + exc.getMessage()); };
                };
            };
        } else { sysTools.logMsg("E", "No modules found."); };
    };

    public Properties getJarProperties(String fileName)
    {
        Properties jarProps = new Properties();
        try
        {
            JarFile jarfile = new JarFile(fileName);
            Manifest manifest = jarfile.getManifest();
            Attributes jarAttr = manifest.getMainAttributes();

            for (Object KeySetItem : jarAttr.keySet())
            {
                Attributes.Name name = (Attributes.Name) KeySetItem;
                jarProps.put(name.toString(), jarAttr.getValue(name));
            };
            //jarProps.list(System.out); // DEBUG OUTPUT
        } catch (IOException e) { sysTools.logMsg("E", e.getMessage()); };

        return jarProps;
    };
};
