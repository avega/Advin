package org.advin.engine.sys;

// It's just simple yet

public class SysTools
{
    /**
     * Just invoke System.out.println(message)
     * @param message the message for output
     */
    public void logMsg(String message) { System.out.println(message); };

    /**
     * Change all file path separators to system specific char
     * @param aPath file path that may contain wrong separator char
     * @return changed file path
     */
    public static String updateFileSeporator(String aPath)
    {
        String fromExp = "/";
        String toExp = "\\\\";

        if (SysInfo.getFileSeparator().equals("/")) { fromExp = "\\\\"; toExp = "/"; };

        return aPath.replaceAll(fromExp, toExp);
    };
};