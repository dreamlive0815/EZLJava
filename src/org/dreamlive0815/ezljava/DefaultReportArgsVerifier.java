package org.dreamlive0815.ezljava;

public class DefaultReportArgsVerifier extends ReportArgsVerifier
{
    public static double SLEEP_MIN_LONGITUDE = 120.345;
    public static double SLEEP_MAX_LONGITUDE = 120.368;
    public static double SLEEP_MIN_LATITUDE = 30.308;
    public static double SLEEP_MAX_LATITUDE = 30.330;
    public static double COURSE_MIN_LONGITUDE = 120.331;
    public static double COURSE_MAX_LONGITUDE = 120.3582;
    public static double COURSE_MIN_LATITUDE = 30.309;
    public static double COURSE_MAX_LATITUDE = 30.32;

    public void Verify(ReportArgs args) throws Exception
    {

        int type = args.getType();
        if(type == ReportArgs.TYPE_COURSE) {
            checkCourseLocation(args.longitude, args.latitude);
            checkCourseTime(args.code, args.time);
        } else if (type == ReportArgs.TYPE_SLEEP) {
            checkSleepLocation(args.longitude, args.latitude);
            checkSleepTime(args.time);
        }
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

    private void checkCourseTime(String coursePeriod, String time) throws Exception
    {
        ReportTime t = new ReportTime(time);
        assertTimeNotAheadNow(t);
        int a = t.all;
        boolean f;
        switch (coursePeriod)
        {
            case "A1": f = a >= 75500 && a <= 81000; break;
            case "A2": f = a >= 94500 && a <= 100000; break;
            case "A3": f = a >= 94500 && a <= 100000; break;
            case "A4": f = a >= 131500 && a <= 133000; break;
            case "A5": f = a >= 131500 && a <= 133000; break;
            case "A6": f = a >= 150500 && a <= 151500; break;
            case "A7": f = a >= 181500 && a <= 183000; break;
            case "A8": f = a >= 181500 && a <= 183000; break;
            default: throw new Exception(String.format(T.G("DRAV.CCT.UKP"), coursePeriod));
        }
        if(!f) throw new Exception(String.format(T.G("DRAV.CCT.IL"), time, coursePeriod));
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