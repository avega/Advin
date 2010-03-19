package org.advin.engine;

// It's just simple yet

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SysTools
{
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
