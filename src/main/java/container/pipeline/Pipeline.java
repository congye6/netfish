package container.pipeline;

import connector.request.HttpRequest;
import connector.response.HttpResponse;

import java.util.List;

/**
 * Created by cong on 2018-04-16.
 * 管道，用于配置container的业务逻辑
 */
public interface Pipeline {


    public void invoke(HttpRequest request, HttpResponse response);

    /**
     * 设置基础阀
     * 过滤器
     * @param basic
     */
    public void setBasic(Valve basic);

    public Valve getBasic();

    public void addValve(Valve valve);

    public List<Valve> getValves();

}
