import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by cong on 2018-04-10.
 */
public class PrimitiveServlet implements Servlet{


    public void init(ServletConfig config) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        Cookie[] cookies=((HttpServletRequest)req).getCookies();
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+" "+cookie.getValue());
        }
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
