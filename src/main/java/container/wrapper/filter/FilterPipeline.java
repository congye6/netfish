package container.wrapper.filter;

import javafx.application.Application;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by cong on 2018-05-22.
 * 保存service和filter
 */
public class FilterPipeline {

    private Servlet servlet;

    private List<Filter> filters=new ArrayList<>();


    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        ApplicationFilterChain filterChain=new ApplicationFilterChain(servlet,filters);
        filterChain.doFilter(request,response);
    }


    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void setServlet(Servlet servlet) {
        this.servlet = servlet;
    }



}
