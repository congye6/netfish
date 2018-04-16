package container.pipeline;

import connector.request.HttpRequest;
import connector.response.HttpResponse;

import java.util.List;

/**
 * Created by cong on 2018-04-16.
 * 管理过滤器的迭代
 */
public interface ValveContext {


    public void invokeNext(HttpRequest request, HttpResponse response);

    public void addValve(Valve valve);

    public List<Valve> getValves();

    public void setBasicValve(Valve basic);

    public Valve getBasicValve();

}
