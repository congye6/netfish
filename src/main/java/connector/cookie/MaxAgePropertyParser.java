package connector.cookie;

import javax.servlet.http.Cookie;

/**
 * Created by cong on 2018-04-12.
 */
public class MaxAgePropertyParser implements CookiePropertyParser{

    private static final String MAX_AGE_NAME="Max-Age";

    public boolean isMatch(String name) {
        return MAX_AGE_NAME.equals(name);
    }

    public void setProperty(Cookie cookie, String value) {
        if(cookie!=null){
            cookie.setMaxAge(Integer.parseInt(value));
        }
    }

    public void writeProperty(Cookie cookie,StringBuilder builder) {
        CookieParser.buildPair(MAX_AGE_NAME,cookie.getMaxAge()+"",builder);
    }
}
