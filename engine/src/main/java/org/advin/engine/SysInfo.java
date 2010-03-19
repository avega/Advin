package org.advin.engine;

public class SysInfo
{
    public static String getUserName()      { return System.getProperty("user.name"); };
    public static String getUserHome()      { return System.getProperty("user.home"); };
    public static String getUserLanguage()  { return System.getProperty("user.language"); };
    public static String getUserCountry()   { return System.getProperty("user.country"); };
    public static String getUserDir()       { return System.getProperty("user.dir"); };
    public static String getOSName()        { return System.getProperty("os.name"); };
    public static String getOSArch()        { return System.getProperty("os.arch"); };
    public static String getOSVersion()     { return System.getProperty("os.version"); };
    public static String getFileEncoding()  { return System.getProperty("file.encoding"); };
    public static String getFileSeparator() { return System.getProperty("file.separator"); };
};
