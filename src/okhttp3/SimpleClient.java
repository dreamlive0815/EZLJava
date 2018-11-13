package okhttp3;

import java.io.IOException;

import java.net.URLEncoder;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HashMapCookieJar;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SimpleClient
{
    private static final String ProxyHost = "127.0.0.1";
    private static String WriteCharset = "UTF-8";

    public String baseAddress;

    private OkHttpClient client;
    private HashMapCookieJar cookies = new HashMapCookieJar();
    private Request.Builder requestBuilder;

    public static String buildQueryString(Map<String, Object> params) throws Exception
    {
    	StringBuilder sb = new StringBuilder();
    	boolean flag = true;
    	for(Map.Entry<String, Object> entry : params.entrySet()) {
    		if(flag) flag = false; else sb.append('&');
    		sb.append(entry.getKey());
    		sb.append('=');
            String value = entry.getValue() == null ? "" : String.valueOf(entry.getValue());
    		sb.append(URLEncoder.encode(value, WriteCharset));
        }
    	return sb.toString();
    }

    public SimpleClient()
    {
        this(false);
    }

    public SimpleClient(boolean useProxy)
    {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .cookieJar(cookies);
        if (useProxy)
            clientBuilder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyHost, 8888)));
        client = clientBuilder.build();
        requestBuilder = new Request.Builder()
                .addHeader("clienttype", "")
                .addHeader("terminal", "{\"type\":\"mobile\",\"os\":\"APP\"}")
                .addHeader("User-Agent", "okhttp/3.4.1");
    }

    public String getString(String uri) throws Exception
    {
        requestBuilder.get().url(getAbsoluteUrl(uri));
        Request request = requestBuilder.build();
        return getResponseString(request);
    }

    public String getString(String uri, Map<String, Object> params) throws Exception
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), buildQueryString(params));
        requestBuilder.post(requestBody).url(getAbsoluteUrl(uri));
        Request request = requestBuilder.build();
        return getResponseString(request);
    }

    protected String getAbsoluteUrl(String uri) throws Exception
    {
        if(baseAddress == null) return uri;
        return new URL(new URL(baseAddress), uri).toString();
    }

    protected String getResponseString(Request request) throws Exception
    {
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new Exception(response.message());
        return response.body().string();
    }
}