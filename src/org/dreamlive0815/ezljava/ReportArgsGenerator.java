package org.dreamlive0815.ezljava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class ReportArgsGenerator
{

    public static double SLEEP_LONGITUDE = (double)ENV.G("RAG.SLO");
    public static double SLEEP_LATITUDE = (double)ENV.G("RAG.SLA");
    public static double COURSE_LONGITUDE = (double)ENV.G("RAG.CLO");
    public static double COURSE_LATITUDE = (double)ENV.G("RAG.CLA");
    public static double OFFSET = 0.001;

    private static final String reportTimeFormat = "HH:mm:ss";

    public static String getNowTime()
    {
        return new SimpleDateFormat(reportTimeFormat).format(new Date());
    }

    public static double getRandomDouble(double min, double max)
    {
        Random rand = new Random();
        return min + rand.nextDouble() * (max - min);
    }

    public static double getRandomDouble(double base, double offset, double minLimit, double maxLimit) throws Exception
    {
        if(base < minLimit || base > maxLimit)
            throw new Exception(String.format(T.G("RAG.GRD.OFB"), minLimit, maxLimit));
        double o = Math.min(base - minLimit, maxLimit - base);
        o = Math.min(o, offset);
        return getRandomDouble(base - o, base + o);
    }

    public static SleepArgs getSleepReportArgs() throws Exception
    {
        String time = getNowTime();
        double lon = getRandomDouble(SLEEP_LONGITUDE, OFFSET, DefaultReportArgsVerifier.SLEEP_MIN_LONGITUDE, DefaultReportArgsVerifier.SLEEP_MAX_LONGITUDE);
        double la = getRandomDouble(SLEEP_LATITUDE, OFFSET, DefaultReportArgsVerifier.SLEEP_MIN_LATITUDE, DefaultReportArgsVerifier.SLEEP_MAX_LATITUDE);
        return new SleepArgs(time, lon, la);
    }
}
