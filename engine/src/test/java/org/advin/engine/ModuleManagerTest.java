package org.advin.engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.lang.reflect.Method;
import static org.junit.Assert.*;

public class ModuleManagerTest
{
    public ModuleManagerTest() { };

    @BeforeClass
    public static void setUpClass() throws Exception { };

    @AfterClass
    public static void tearDownClass() throws Exception { };

    @Before
    public void setUp() { };

    @After
    public void tearDown() { };

    /**
     * Test of reloadModules method, of class ModuleManager.
     */
    @Test
    public void reloadModules() throws Exception
    {
        System.out.println("fillModuleFileList");
        String aPath = "plugins";
        ModuleManager instance = new ModuleManager();
        int expResult = 0;
        int result = -1;

        final Method[] methods = instance.getClass().getDeclaredMethods();
        for (Method m: methods)
        {
            if (m.getName().equals("fillModuleFileList"))
            {
                final Object params[] = {aPath};
                m.setAccessible(true);
                
                Object ret = m.invoke(instance, params);
                if (ret != null) result = (Integer) ret;
            };
        };
        
        assertEquals(expResult, result);
    };

    /**
     * Test of addToList method, of class ModuleManager.
     */
    /*@Test
    public void addToList() {
        System.out.println("addToList");
        IModuleInfo info = null;
        Class aClass = null;
        ModuleManager instance = new ModuleManager();
        instance.addToList(info, aClass);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    };*/
    
    /**
     * Test of loadModules method, of class ModuleManager.
     */
    /*@Test
    public void loadModules() {
        System.out.println("loadModules");
        //ModuleManager instance = new ModuleManager();
        //instance.loadModules();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    };*/
};