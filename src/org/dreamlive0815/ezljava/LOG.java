package org.dreamlive0815.ezljava;

import java.util.ArrayList;
import java.util.List;

public abstract class LOG
{
    private static List<LOG> loggers = new ArrayList<LOG>();

    static {
        loggers.add(new ConsoleLogger());
    }

    public static void addLogger(LOG logger)
    {
        loggers.add(logger);
    }

    public static void L(String s)
    {
        for (LOG logger : loggers) {
            logger.log(s);
        }
    }

    public abstract void log(String s);
}

class ConsoleLogger extends LOG
{
    public void log(String s)
    {
        System.out.println(s);
    }
}