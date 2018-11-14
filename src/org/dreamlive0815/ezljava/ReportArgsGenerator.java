package org.dreamlive0815.ezljava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ReportArgsGenerator
{

    private static final String reportTimeFormat = "HH:mm:ss";

    public static String getNowTime()
    {
        return new SimpleDateFormat(reportTimeFormat).format(new Date());
    }
}
