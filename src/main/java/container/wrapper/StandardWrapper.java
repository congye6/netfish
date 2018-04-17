package container.wrapper;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.Container;
import container.loader.Loader;
import container.loader.SimpleLoader;
import container.pipeline.Pipeline;
import container.pipeline.StandardPipeline;

import javax.servlet.Servlet;

/**
 * Created by cong on 2018-04-16.
 */
public class StandardWrapper implements Wrapper{

    private Pipeline pipeline;

    private Container parent;

    private Loader loader;

    private String uri;

    public StandardWrapper(String uri) {
        pipeline=new StandardPipeline();
        pipeline.setBasic(new WrapperBasicValve(this));
        pipeline.addValve(new HeaderValve());
        this.loader=new SimpleLoader();
        this.uri=uri;
    }


    public void invoke(HttpRequest request, HttpResponse response) {
        pipeline.invoke(request,response);
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container container) {
        parent=container;
    }

    public ClassLoader getParentClassLoader() {
        return null;
    }

    public void setParentClassLoader(ClassLoader parent) {

    }

    public void addChild(Container child) {

    }

    public void removeChild(Container child) {

    }

    public Container findChild(String name) {
        return null;
    }

    public Container[] findChildren() {
        return new Container[0];
    }

    public void setLoader(Loader loader) {
        this.loader=loader;
    }

    public Loader getLoader() {
        if(loader!=null)
            return loader;
        if(parent!=null&&parent.getLoader()!=null)
            return parent.getLoader();
        return null;
    }

    public void load() {

    }

    public Servlet allocate() {
        return ((SimpleLoader)loader).load(uri);
    }
}
