package container.wrapper.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by cong on 2018-05-21.
 * filter 调用链
 */
public class ApplicationFilterChain implements FilterChain {

    private Servlet servlet;

    private List<Filter> filters;

    private int pointer=0;

    public ApplicationFilterChain(Servlet servlet, List<Filter> filters) {
        this.servlet = servlet;
        this.filters = filters;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (pointer==filters.size()) {
            if (servlet != null)
                servlet.service(request, response);
            return;
        }
        Filter current = filters.get(pointer);
        pointer++;
        current.doFilter(request, response, this);
    }

}


