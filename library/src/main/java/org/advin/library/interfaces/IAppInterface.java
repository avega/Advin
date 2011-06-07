package org.advin.library.interfaces;

public interface IAppInterface
{
    String getAppVersion();
    void invokeMethod(String param);
    void logMsg(String info, String message);
    IAdvinModule getModule(String name);
};
