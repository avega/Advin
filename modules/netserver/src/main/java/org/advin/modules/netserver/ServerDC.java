package org.advin.modules.netserver;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerDC implements Runnable
{
    int acceptCount = 0;
    int listenPort = 0;
    boolean stopListening = false;

    public ServerDC(int serverPort) { listenPort = serverPort; };

    @Override
    public void run()
    {
        try
        {
            ServerSocket s = new ServerSocket(listenPort);

            while (!stopListening)
            {
                Socket incoming = s.accept();
                acceptCount++;
                Runnable cHandlerRunner = new ConnectHandler(incoming);
                Thread cHandlerThread = new Thread(cHandlerRunner);
                cHandlerThread.start();
            };

        } catch(Exception e) { System.out.println(e.getMessage()); };
    };
};
