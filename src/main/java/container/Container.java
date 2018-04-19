package container;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.loader.Loader;

import java.util.List;

/**
 * Created by cong on 2018-04-16.
 */
public interface Container {

    public void invoke(HttpRequest request, HttpResponse response);

    //与容器层级有关
    public Container getParent();
    public void setParent(Container container);
    public ClassLoader getParentClassLoader();
    public void setParentClassLoader(ClassLoader parent);
    public void addChild(Container child);
    public void removeChild(Container child);
    public Container findChild(String name);
    public List<Container> findChildren();

    //loader
    public void setLoader(Loader loader);
    public Loader getLoader();



}
