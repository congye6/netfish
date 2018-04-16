package container.pipeline;

import connector.request.HttpRequest;
import connector.response.HttpResponse;

/**
 * Created by cong on 2018-04-16.
 * 过滤器
 */
public interface Valve {


    public void invoke(HttpRequest request, HttpResponse response,ValveContext valveContext);

}
