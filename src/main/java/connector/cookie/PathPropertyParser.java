package connector.cookie;

import util.StringUtil;

import javax.servlet.http.Cookie;

/**
 * Created by cong on 2018-04-12.
 */
public class PathPropertyParser implements CookiePropertyParser{

    private static final String PATH_NAME="Path";

    public boolean isMatch(String name) {
        return PATH_NAME.equals(name);
    }

    public void setProperty(Cookie cookie,String value) {
        if(cookie!=null){
            cookie.setPath(value);
        }
    }

    public void writeProperty(Cookie cookie,StringBuilder builder) {
        if(StringUtil.isEmpty(cookie.getPath()))
            return;
        CookieParser.buildPair(PATH_NAME,cookie.getPath(),builder);
    }
}
