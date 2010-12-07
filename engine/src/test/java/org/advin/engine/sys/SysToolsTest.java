package org.advin.engine.sys;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class SysToolsTest
{
    public SysToolsTest() { };

    @BeforeClass
    public static void setUpClass() throws Exception { };

    @AfterClass
    public static void tearDownClass() throws Exception { };

    @Before
    public void setUp() { };

    @After
    public void tearDown() { };

    /**
     * Test of updateFileSeporator method, of class SysTools.
     */
    @Test
    public void updateFileSeporator()
    {
        System.out.println("updateFileSeporator");
        
        String aPath = "/some/path\\to\\file"; // With mixed path separator
        
        String expResult = "\\some\\path\\to\\file"; // With winfows path separator
        if (SysInfo.getFileSeparator().equals("/")) expResult = "/some/path/to/file";  // With unix path separator
        
        String result = SysTools.updateFileSeporator(aPath);

        Assert.assertEquals(expResult, result);
    };
};