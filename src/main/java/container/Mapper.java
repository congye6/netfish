package container;

import connector.request.HttpRequest;

/**
 * Created by cong on 2018-04-17.
 * 容器映射器
 */
public interface Mapper {

    public void setContainer(Container container);

    public Container getContainer();

    public Container map(HttpRequest request);

}
