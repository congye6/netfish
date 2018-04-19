package container.context;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.Container;
import container.Mapper;
import container.lifecycle.LifeCycle;
import container.lifecycle.LifeCycleException;
import container.lifecycle.LifeCycleListener;
import container.lifecycle.LifeCycleUtil;
import container.loader.Loader;
import container.pipeline.Pipeline;
import container.pipeline.StandardPipeline;
import container.wrapper.StandardWrapper;
import container.wrapper.Wrapper;

import java.util.*;

/**
 * Created by cong on 2018-04-17.
 */
public class StandardContext implements Context,LifeCycle{

    private Container parent;

    private Pipeline pipeline;

    /**
     * wrapper的映射器
     */
    private Mapper mapper;

    private Map<String,Wrapper> servletMapping=new HashMap<String,Wrapper>();

    /**
     * 管理lifecycle监听
     */
    private LifeCycleUtil lifeCycle;

    private boolean isStart=false;

    private List<Container> children=new ArrayList<Container>();

    private Loader loader;

    private ClassLoader parentClassLoader;

    public StandardContext(){
        pipeline=new StandardPipeline();
        pipeline.setBasic(new ContextBasicValve(this));
        lifeCycle=new LifeCycleUtil(this);

        //todo
        addLifeCycleListener(new StandardContextListener());
        addChild(new StandardWrapper("uri"));
        try {
            start();
        } catch (LifeCycleException e) {
            e.printStackTrace();
        }
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
        return parentClassLoader;
    }

    public void setParentClassLoader(ClassLoader parent) {
        this.parentClassLoader=parent;
    }

    public void addChild(Container child) {
        children.add(child);
    }

    public void removeChild(Container child) {
        for(int i=0;i<children.size();i++){
            if(children.get(i)==child){
                children.remove(i);
                return;
            }
        }
    }

    public Container findChild(String name) {
        return null;
    }

    public List<Container> findChildren() {
        return children;
    }

    public void setLoader(Loader loader) {
        this.loader=loader;
    }

    public Loader getLoader() {
        if(loader!=null)
            return loader;
        if(parent!=null)
            return parent.getLoader();
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

    public void start() throws LifeCycleException{
        if(isStart)
            throw new LifeCycleException("standard context start");
        lifeCycle.beforeStart(null);
        lifeCycle.start(null);

        if(getLoader()!=null){
            LifeCycle lifeCycle=(LifeCycle)loader;
            lifeCycle.start();
        }
        for(Container child:children){
            LifeCycle lifeCycle=(LifeCycle)child;
            lifeCycle.start();
        }
        isStart=true;
        lifeCycle.afterStart(null);
    }

    public void stop() throws LifeCycleException{
        if(!isStart)
            throw new LifeCycleException("stop context fail,not start");
        lifeCycle.beforeStop(null);
        lifeCycle.stop(null);

        if(getLoader()!=null){
            LifeCycle lifeCycle=(LifeCycle)loader;
            lifeCycle.stop();
        }
        for(Container child:children){
            LifeCycle lifeCycle=(LifeCycle)child;
            lifeCycle.stop();
        }
        isStart=false;
        lifeCycle.afterStop(null);
    }
}
