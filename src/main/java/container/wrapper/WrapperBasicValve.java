package container.wrapper;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.Container;
import container.loader.SimpleLoader;
import container.pipeline.Valve;
import container.pipeline.ValveContext;
import container.wrapper.filter.ApplicationFilterChain;
import container.wrapper.filter.FilterPipeline;
import javafx.application.Application;
import logger.StandardLogger;

import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by cong on 2018-04-16.
 */
public class WrapperBasicValve implements Valve{

    private Wrapper wrapper;

    private FilterPipeline filterPipeline=new FilterPipeline();

    public WrapperBasicValve(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void invoke(HttpRequest request, HttpResponse response, ValveContext valveContext) {
        Servlet servlet=wrapper.allocate();
        filterPipeline.setServlet(servlet);

        try {
            filterPipeline.doFilter(request,response);
        } catch (IOException e) {
            StandardLogger.error("",e);
        } catch (ServletException e) {
            StandardLogger.error("servlet service error",e);
        }

        wrapper.recycle(servlet);
    }


}
