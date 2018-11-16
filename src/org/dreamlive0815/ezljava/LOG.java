package org.dreamlive0815.ezljava;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


import org.dreamlive0815.util.FileUtil;

public abstract class LOG
{
    private static List<LOG> loggers = new ArrayList<LOG>();

    static {
        loggers.add(new ConsoleLogger());
        loggers.add(new FileLogger("ezl.log"));
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

class FileLogger extends LOG
{
    String path;

    public FileLogger(String path)
    {
        this.path = path;
    }

    public void log(String s)
    {
        String path = ENV.getWorkingDirectory() + this.path;
        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        s = String.format("[%s] %s", datetime, s);
        try {
            FileUtil.append(path, s);
        } catch (Exception e) {
        }
    }   
}