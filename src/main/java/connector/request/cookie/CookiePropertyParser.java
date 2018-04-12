package connector.request.cookie;

import javax.servlet.http.Cookie;

/**
 * Created by cong on 2018-04-12.
 * 解析cookie属性
 */
public interface CookiePropertyParser {

    /**
     * 判断是否是cookie的某个属性
     * @param name
     * @return
     */
    public boolean isMatch(String name);

    /**
     * 设置cookie的属性
     * @param cookie
     */
    public void setProperty(Cookie cookie,String value);

}
