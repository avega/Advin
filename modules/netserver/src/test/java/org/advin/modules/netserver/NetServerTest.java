package org.advin.modules.netserver;

import org.advin.library.interfaces.IAppInterface;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NetServerTest
{

    public NetServerTest() {};

    @BeforeClass
    public static void setUpClass() throws Exception {};

    @AfterClass
    public static void tearDownClass() throws Exception {};

    @Before
    public void setUp() {};

    @After
    public void tearDown() {};

    /**
     * Test of initModule method, of class NetServer.
     */
    @Test
    public void testInitModule()
    {
        System.out.println("initModule");
        IAppInterface appIFace = null;
        NetServer instance = new NetServer();
        instance.initModule(appIFace);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    };
};