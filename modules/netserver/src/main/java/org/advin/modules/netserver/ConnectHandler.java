package org.advin.modules.netserver;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConnectHandler implements Runnable
{
    private Socket incoming;

    public ConnectHandler(Socket i) { incoming = i; };

    @Override
    public void run()
    {
        try
        {
            try
            {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true);

                out.println("Hello! Enter BYE to exit.");

                boolean done = false;
                while (!done && in.hasNextLine())
                {
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    if (line.trim().equalsIgnoreCase("BYE")) done = true;
                };

            } finally { incoming.close(); };

        } catch(Exception e) { System.out.println(e.getMessage()); };
    };
};
