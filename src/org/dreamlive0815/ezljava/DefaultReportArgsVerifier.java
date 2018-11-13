package org.dreamlive0815.ezljava;

import java.util.Calendar;

public class DefaultReportArgsVerifier extends ReportArgsVerifier
{
    private static double SLEEP_MIN_LONGITUDE = 120.345;
    private static double SLEEP_MAX_LONGITUDE = 120.368;
    private static double SLEEP_MIN_LATITUDE = 30.308;
    private static double SLEEP_MAX_LATITUDE = 30.330;
    private static double COURSE_MIN_LONGITUDE = 120.331;
    private static double COURSE_MAX_LONGITUDE = 120.3582;
    private static double COURSE_MIN_LATITUDE = 30.309;
    private static double COURSE_MAX_LATITUDE = 30.32;

    public void Verify(ReportArgs args) throws Exception
    {

    }

    private void assertTimeNotAheadNow(ReportTime time) throws Exception
    {
        ReportTime now = new ReportTime(ReportArgsGenerator.getNowTime());
        if(time.all > now.all)
            throw new Exception(T.G("DRAV.ATNAN.A"));
    }

    private void checkCourseLocation(double longitude, double latitude) throws Exception
    {
        if (longitude < COURSE_MIN_LONGITUDE || longitude > COURSE_MAX_LONGITUDE)
            throw new Exception(String.format(T.G("DRAV.CCL.LON"), COURSE_MIN_LONGITUDE, COURSE_MAX_LONGITUDE));
        if (latitude < COURSE_MIN_LATITUDE || latitude > COURSE_MAX_LATITUDE)
            throw new Exception(String.format(T.G("DRAV.CCL.LA"), COURSE_MIN_LATITUDE, COURSE_MAX_LATITUDE));
    }

    private void checkSleepLocation(double longitude, double latitude) throws Exception
    {
        if (longitude < SLEEP_MIN_LONGITUDE || longitude > SLEEP_MAX_LONGITUDE)
            throw new Exception(String.format(T.G("DRAV.CSL.LON"), SLEEP_MIN_LONGITUDE, SLEEP_MAX_LONGITUDE));
        if (latitude < SLEEP_MIN_LATITUDE || latitude > SLEEP_MAX_LATITUDE)
            throw new Exception(String.format(T.G("DRAV.CSL.LA"), SLEEP_MIN_LATITUDE, SLEEP_MAX_LATITUDE));
    }

    private void checkSleepTime(String time) throws Exception
    {
        ReportTime t = new ReportTime(time);
        assertTimeNotAheadNow(t);
        if(t.all < 213000 || t.all > 235959) throw new Exception(String.format(T.G("DRAV.CST.IL"), time));
    }


}