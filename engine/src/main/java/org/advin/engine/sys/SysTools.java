package org.advin.engine.sys;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import sun.reflect.Reflection;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.*;

public class SysTools
{
    public static Logger logger;
    public static final String LOG_NAME = updateFileSeporator(SysInfo.getUserDir() + SysInfo.getFileSeparator() + "advin.log");
    public static final String LOG_FOR = "org.advin.engine";
    public static final String LOG_DATETIME_FORMAT = "[dd.MM.yyyy HH:mm:ss]";
    public static final String LOG_MSG_FORMAT = "%1$20s [%2$s]: %3$s";
    public static final String LOG_EMPTY_PLACER = "NONE";

    static
    {
        try
        {
            boolean append = true;
            FileHandler fh = new FileHandler(LOG_NAME, append);

            fh.setFormatter(new Formatter()
            {
                public String format(LogRecord rec)
                {
                    StringBuilder buf = new StringBuilder(1000);
                    buf.append((new SimpleDateFormat(LOG_DATETIME_FORMAT)).format(new java.util.Date()));
                    buf.append(' ');
                    buf.append(rec.getMessage());
                    buf.append('\n');
                    return buf.toString();
                };
            });

            logger = Logger.getLogger(LOG_FOR);
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.FINEST);
        }
        catch (IOException e) { e.printStackTrace(); };
    };

    /**
     * Output formated log messages to console and lg file
     * @param info message info
     * @param message the message for output
     */
    public void logMsg(String info, String message)
    {
        String caller = LOG_EMPTY_PLACER;

        if (Reflection.getCallerClass(3) != null)
            { caller = Reflection.getCallerClass(3).getSimpleName(); }
        else if (Reflection.getCallerClass(2) != null)
            { caller = Reflection.getCallerClass(2).getSimpleName(); }
        else if (Reflection.getCallerClass(1) != null)
            { caller = Reflection.getCallerClass(1).getSimpleName(); };

        if (info == null) { info = LOG_EMPTY_PLACER; };
        if (message == null) { message = LOG_EMPTY_PLACER; };

        String msg = String.format(LOG_MSG_FORMAT, caller, info, message);

        System.out.println(msg);
        logger.info(msg);
    };

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
        catch (ParseException e) { logMsg("E", " Arguments parse error: "+ e.getMessage()); };

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
