package org.advin.modules.advingui;

public class AdvinGUITest
{
    AdvinGUI advinGUI;

    @org.junit.Before
    public void setUp() throws Exception { advinGUI = new AdvinGUI(); };

    @org.junit.After
    public void tearDown() throws Exception {};

//    @org.junit.Test
//    public void testAm_init() throws Exception {};

    @org.junit.Test
    public void testAm_activate() throws Exception
    {
        advinGUI.am_activate();
    };

//    @org.junit.Test
//    public void testAm_done() throws Exception {};

//    @org.junit.Test
//    public void testAm_isActive() throws Exception {};
};
