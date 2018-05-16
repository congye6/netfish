import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cong on 2018-04-16.
 */
public class CookieServlet implements Servlet{
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("reading cookie");
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        /*Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+" "+cookie.getValue());
        }
        Cookie cookie=new Cookie("user","val");
        cookie.setMaxAge(100000);*/

//        response.addCookie(cookie);

        HttpSession session=request.getSession();
        System.out.println("session:"+session.getAttribute("username"));
        session.setAttribute("username","hhhs");


    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
