package org.advin.engine.sys;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

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

    public String getArgValue(String[] args, String param)
    {
        String result = "";
        Options options = new Options();
        options.addOption(param, true, "");

        CommandLineParser parser = new GnuParser();
        CommandLine cl = null;
        try
        {
            cl = parser.parse(options, args);
    	}
        catch (ParseException e) { logMsg("[E] Arguments parse error: "+ e.getMessage()); };

        if ((cl != null) && (cl.hasOption(param)))
        {
            result = cl.getOptionValue(param);
        };

        return result;
    };

    public String getCP()
    {
        return System.getProperties().getProperty("java.class.path", null);
    };
};
