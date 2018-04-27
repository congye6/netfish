package container.wrapper;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.Container;
import container.lifecycle.LifeCycle;
import container.lifecycle.LifeCycleException;
import container.lifecycle.LifeCycleListener;
import container.lifecycle.LifeCycleUtil;
import container.loader.Loader;
import container.loader.SimpleLoader;
import container.pipeline.Pipeline;
import container.pipeline.StandardPipeline;
import logger.StandardLogger;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-16.
 */
public class StandardWrapper implements Wrapper,LifeCycle{

    private Pipeline pipeline;

    private Container parent;

    private Loader loader;

    private String uri;

    private LifeCycleUtil lifeCycle=new LifeCycleUtil(this);

    public StandardWrapper(String uri) {
        pipeline=new StandardPipeline();
        pipeline.setBasic(new WrapperBasicValve(this));
        pipeline.addValve(new HeaderValve());
        this.uri=uri;

        //todo
        addLifeCycleListener(new StandardWrapperListener());
    }


    public void invoke(HttpRequest request, HttpResponse response) {
        pipeline.recycle();
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

    public List<Container> findChildren() {
        return new ArrayList<Container>();
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

    public void setPipeline(Pipeline pipeline) {
        this.pipeline=pipeline;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void load() {

    }

    public Servlet allocate() {
        Class clazz=getLoader().load(uri);
        try {
            return (Servlet)clazz.newInstance();
        } catch (Exception e) {
            StandardLogger.error("get class of:"+uri,e);
        }
        return null;
    }

    public void addLifeCycleListener(LifeCycleListener listener) {
        lifeCycle.addListener(listener);
    }

    public List<LifeCycleListener> getLifeCycleListeners() {
        return lifeCycle.getListeners();
    }

    public void removeLifeCycleListener(LifeCycleListener listener) {
        lifeCycle.removeListener(listener);
    }

    public void start() throws LifeCycleException {
        lifeCycle.beforeStart(null);
        lifeCycle.start(null);
        lifeCycle.afterStart(null);
    }

    public void stop() throws LifeCycleException {
        lifeCycle.beforeStop(null);
        lifeCycle.stop(null);
        lifeCycle.afterStop(null);
    }
}
