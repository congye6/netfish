import javax.servlet.*;
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
        res.getWriter().println("hhhh");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
