package connector.cookie;

import javax.servlet.http.Cookie;

/**
 * Created by cong on 2018-04-12.
 */
public class ExpirePropertyParser implements CookiePropertyParser{

    private static final String EXPIRE_NAME="Expires";

    public boolean isMatch(String name) {
        return EXPIRE_NAME.equals(name);
    }

    public void setProperty(Cookie cookie, String value) {
        //cookie 不保存这个属性
    }

    public void writeProperty(Cookie cookie, StringBuilder builder) {

    }

}
