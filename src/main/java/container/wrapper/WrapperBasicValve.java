package container.wrapper;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.Container;
import container.loader.SimpleLoader;
import container.pipeline.Valve;
import container.pipeline.ValveContext;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by cong on 2018-04-16.
 */
public class WrapperBasicValve implements Valve{

    private Wrapper wrapper;

    public WrapperBasicValve(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void invoke(HttpRequest request, HttpResponse response, ValveContext valveContext) {
        Servlet servlet=wrapper.allocate();
        try {
            servlet.service(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wrapper.recycle(servlet);
    }


}
