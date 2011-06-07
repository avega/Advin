package org.advin.modules.advingui;

import org.advin.library.interfaces.IAdvinModule;
import org.advin.library.interfaces.IAppInterface;

public class AdvinGUI implements IAdvinModule
{
    public IAppInterface appInterface = null;
    private boolean Active = false;
    private MForm mForm;

    @Override
    public void am_init(IAppInterface appIFace)
    {
        appInterface = appIFace;
        appInterface.logMsg("I", "No functionality yet");
    };

    @Override
    public void am_activate()
    {
        mForm = new MForm();
        //( new Thread() { public void run() { mForm.start(); }; } ).start();
        mForm.start();
        Active = true;
    };

    @Override
    public void am_done()
    {
        appInterface.logMsg("I", "done");
    };

    @Override
    public boolean am_isActive()
    {
        Active = mForm.isActive();
        return Active;
    };
};
