package org.advin.modules.netserver;

import org.advin.library.interfaces.IAdvinModule;
import org.advin.library.interfaces.IAppInterface;
import java.net.Socket;

public class NetServer implements IAdvinModule
{
    public int serverPort     = 1976;
    public ServerDC netServer = null;
    public IAppInterface appInterface = null;
    private boolean Active = false;

    @Override
    public void initModule(IAppInterface appIFace)
    {
        appInterface = appIFace;
        
        //appInterface.logMsg(moduleInfo.getModuleName() + " initialization...");
        netServer = new ServerDC(serverPort);
    }

    @Override
    public void startModule()
    {
        //appInterface.logMsg("Starting server thread... ");
        Thread serverThread = new Thread(netServer);
        serverThread.start();
    };

    @Override
    public void doneModule()
    {
        //appInterface.logMsg(moduleInfo.getModuleName() + " finalization...");
        netServer.stopListening = true;
        try
        { Socket s = new Socket("localhost", serverPort); s.close(); }
        catch (Exception e)
        { appInterface.logMsg(e.getMessage()); };
    }

    @Override
    public boolean isActive() { return Active; };
};
