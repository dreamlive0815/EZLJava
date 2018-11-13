package okhttp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.CookieJar;

public class HashMapCookieJar implements CookieJar
{
    private final Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        String host = httpUrl.host();
        List<Cookie> cookiesList = cookiesMap.get(host);
        if (cookiesList != null) {
            cookiesMap.remove(host);
        }
        cookiesMap.put(host, list);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> cookiesList = cookiesMap.get(httpUrl.host());
        return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
    }
}