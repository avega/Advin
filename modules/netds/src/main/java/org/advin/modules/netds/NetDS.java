package org.advin.modules.netds;

import org.advin.library.interfaces.IAdvinModule;
import org.advin.library.interfaces.IAppInterface;

public class NetDS implements IAdvinModule
{
    public IAppInterface appInterface = null;
    private boolean Active = false;

    @Override
    public void am_init(IAppInterface appIFace)
    {
        appInterface = appIFace;
        appInterface.logMsg("I", "No functionality yet");
    };

    @Override
    public void am_activate()
    {
        appInterface.logMsg("I", "No need to activation");
        Active = false;
    };

    @Override
    public void am_done()
    {
        appInterface.logMsg("I", "done");
    };

    @Override
    public boolean am_isActive() { return Active; };
};
