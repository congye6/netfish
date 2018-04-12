package connector.request.cookie;

import util.StringUtil;


import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-12.
 * 将cookie字符串解析成Cookie数组
 * cookie以键值对的形式保存，每个键值对用;分开，键值对可能是cookie，也可能是cookie的属性
 */
public class CookieParser {

    private static final CookiePropertyParser[] PROPERTY_PARSERS={
            new ExpirePropertyParser(),new MaxAgePropertyParser(),new PathPropertyParser(),new SecurePropertyParser()
    };

    private static final String COOKIE_SPLITER=";";

    private static final String COOKIE_VALUE_SPLITER="=";

    private static final int INDEX_OF_NAME=0;

    private static final int INDEX_OF_VALUE=1;

    public List<Cookie> parse(String cookieStr){
        if(StringUtil.isEmpty(cookieStr))
            return new ArrayList<Cookie>();
        List<Cookie> cookies=new ArrayList<Cookie>();
        String[] pairs=cookieStr.split(COOKIE_SPLITER);
        Cookie cookie=parseCookie(pairs[0]);
        cookies.add(cookie);
        out:for(int i=1;i<pairs.length;i++){
            String[] values=pairs[i].split(COOKIE_VALUE_SPLITER);
            if(values.length!=2)
                return null;
            for(CookiePropertyParser propertyParser:PROPERTY_PARSERS){//判断是否为cookie的属性
                if(propertyParser.isMatch(values[INDEX_OF_NAME])){
                    propertyParser.setProperty(cookie,values[INDEX_OF_VALUE]);
                    continue out;
                }
            }
            //不是cookie的属性，创建新cookie
            Cookie newCookie=new Cookie(values[INDEX_OF_NAME],values[INDEX_OF_VALUE]);
            cookies.add(newCookie);
            cookie=newCookie;
        }

        return cookies;
    }

    private Cookie parseCookie(String pair){
        String[] values=pair.split(COOKIE_VALUE_SPLITER);
        if(values.length!=2)
            return null;
        Cookie cookie=new Cookie(values[INDEX_OF_NAME],values[INDEX_OF_VALUE]);
        return cookie;
    }

    public static void main(String[] args){
        String str="name=111;Path=/222;Max-Age=1000;user=cong";
        CookieParser parser=new CookieParser();
        List<Cookie> cookies=parser.parse(str);
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+" "+cookie.getValue()+" "+cookie.getMaxAge());
        }
    }


}

