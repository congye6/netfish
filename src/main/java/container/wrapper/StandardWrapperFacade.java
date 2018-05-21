package container.wrapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

/**
 * Created by cong on 2018-05-20.
 */
public class StandardWrapperFacade implements ServletConfig{

    private ServletConfig wrapper;


    public StandardWrapperFacade(ServletConfig wrapper) {
        this.wrapper = wrapper;
    }

    @Override

    public String getServletName() {
        return wrapper.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return wrapper.getServletContext();
    }

    @Override
    public String getInitParameter(String name) {
        return wrapper.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return wrapper.getInitParameterNames();
    }
}
