package org.dreamlive0815.ezljava;

public class ReportException extends Exception
{
    private static final long serialVersionUID = 1L;
    public int code = 0;
    public String exception;
    public String message;
    
    public ReportException(int code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    public ReportException(String exception, String message)
    {
        this.exception = exception;
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return String.format("\"code\":%s,\n\"exception\":\"%s\",\n\"message\":\"%s\"", code, exception, message);
    }
}