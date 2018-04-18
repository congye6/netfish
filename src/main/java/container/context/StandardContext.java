package container.context;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.Container;
import container.Mapper;
import container.lifecycle.LifeCycle;
import container.lifecycle.LifeCycleListener;
import container.lifecycle.LifeCycleUtil;
import container.loader.Loader;
import container.pipeline.Pipeline;
import container.pipeline.StandardPipeline;
import container.wrapper.Wrapper;

import java.util.*;

/**
 * Created by cong on 2018-04-17.
 */
public class StandardContext implements Context,LifeCycle{

    private Container parent;

    private Pipeline pipeline;

    private Mapper mapper;

    private Map<String,Wrapper> servletMapping=new HashMap<String,Wrapper>();

    private LifeCycleUtil lifeCycle;

    public StandardContext(){
        pipeline=new StandardPipeline();
        pipeline.setBasic(new ContextBasicValve(this));
        lifeCycle=new LifeCycleUtil(this);
    }

    public void invoke(HttpRequest request, HttpResponse response) {
        pipeline.invoke(request,response);
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container container) {
        this.parent=container;
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

    }

    public Loader getLoader() {
        return null;
    }

    public void addServletMapping(String url, Wrapper wrapper) {
        servletMapping.put(url,wrapper);
    }

    public Wrapper getServletMapping(String url) {
        return servletMapping.get(url);
    }

    public void setMapper(Mapper mapper) {
        this.mapper=mapper;
    }

    public Mapper getMapper() {
        return mapper;
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

    public void start() {
        lifeCycle.start(null);
    }

    public void stop() {
        lifeCycle.stop(null);
    }
}
