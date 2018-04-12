package connector.request.cookie;

import javax.servlet.http.Cookie;

/**
 * Created by cong on 2018-04-12.
 */
public class SecurePropertyParser implements CookiePropertyParser{

    private static final String SECURE_NAME="Secure";

    public boolean isMatch(String name) {
        return SECURE_NAME.equals(name);
    }

    public void setProperty(Cookie cookie, String value) {
        if (cookie!=null){
            cookie.setSecure(Boolean.parseBoolean(value));
        }
    }
}
