package connector.response;

import connector.cookie.CookieParser;
import logger.StandardLogger;
import util.HttpFormatUtil;
import util.StringUtil;

import javax.servlet.http.Cookie;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by cong on 2018-04-09.
 */
public class ResponseHeader {


    private Map<String,String> headerMap=new HashMap<String,String>();


    public void addHeader(String key,String value){
        if(StringUtil.isEmpty(key))
            return;
        headerMap.put(key,value);
    }

    void write(BufferedWriter bufferedWriter){
        StringBuilder builder=new StringBuilder();
        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            builder.append(entry.getKey());
            builder.append(HttpFormatUtil.HEADER_SPLITER);
            builder.append(entry.getValue());
            builder.append(HttpFormatUtil.LINE_SPLITER);
        }
        try {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            StandardLogger.error("write header fail,e:"+e.getMessage());
        }
    }

    String getHeader(String name){
        return headerMap.get(name);
    }

    public boolean containsHeader(String name){
        return headerMap.containsKey(name);
    }


    public void addCookie(List<Cookie> cookies) {
        if(cookies==null||cookies.size()==0)
            return;
        CookieParser cookieParser=new CookieParser();
        String cookieStr=cookieParser.parseCookie(cookies);
        if(StringUtil.isEmpty(cookieStr))
            return;
        headerMap.put(ResponseHeaderKey.SET_COOKIE,cookieStr);
    }
}
