package container.wrapper;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.pipeline.Valve;
import container.pipeline.ValveContext;

/**
 * Created by cong on 2018-04-16.
 */
public class HeaderValve implements Valve{
    public void invoke(HttpRequest request, HttpResponse response, ValveContext valveContext) {
        System.out.println(request.toString());
        valveContext.invokeNext(request,response);
    }
}
