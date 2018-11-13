package org.dreamlive0815.ezljava;

public class ReportArgs
{
    public static final double UNSET_DOUBLE = 0;
    public static final int TYPE_SLEEP = 1;
    public static final int TYPE_COURSE = 2;

    public String code;
    public String time;
    public double longitude = UNSET_DOUBLE;
    public double latitude = UNSET_DOUBLE;
    protected int type;

    protected ReportArgs(String time, double longitude, double latitude)
    {
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getType()
    {
        return type;
    }
}

class SleepArgs extends ReportArgs
{
    public SleepArgs(String time, double longitude, double latitude)
    {
        super(time, longitude, latitude);
        type = TYPE_SLEEP;
    }
}

class CourseArgs extends ReportArgs
{
    public CourseArgs(String code, String time, double longitude, double latitude)
    {
        super(time, longitude, latitude);
        this.code = code;
        type = TYPE_COURSE;
    }
}

abstract class ReportArgsVerifier
{
    public abstract void Verify(ReportArgs args) throws Exception;
}