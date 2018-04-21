package container.loader;

import container.Container;
import container.context.StandardContext;
import container.lifecycle.LifeCycle;
import container.lifecycle.LifeCycleException;
import container.lifecycle.LifeCycleListener;
import container.lifecycle.LifeCycleUtil;
import logger.Logger;
import logger.StandardLogger;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-19.
 */
public class WebappLoader implements Loader,LifeCycle{

    private static final String DEFAULT_LOADER_CLASS="container.loader.WebappClassLoader";

    private static final String CLASS_PATH="webinf"+File.separator+"classes"+File.separator;

    private static final String LIB_PATH="webinf"+File.separator+"lib"+File.separator;

    private WebappClassLoader classLoader;

    private Container container;

    private boolean reloadable;

    private boolean delegate;

    private String loaderClass=DEFAULT_LOADER_CLASS;

    private LifeCycleUtil lifeCycle=new LifeCycleUtil(this);

    public String getLoaderClass() {
        return loaderClass;
    }

    public void setLoaderClass(String loaderClass) {
        this.loaderClass = loaderClass;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container=container;
    }

    public boolean reloadable() {
        return reloadable;
    }

    public void setReloadable(boolean reloadable) {
        this.reloadable=reloadable;
    }

    public void addRepository(String repository) {
        ((Reloader)classLoader).addRepository(repository);
    }

    public List<String> getRepositories() {
        return ((Reloader)classLoader).getRepositories();
    }

    public boolean modified() {
        return  ((Reloader)classLoader).modified();
    }

    public boolean getDelegate() {
        return delegate;
    }

    public void setDelegate(boolean delegate) {
        this.delegate=delegate;
    }

    public Class load(String url) {
        try {
            return classLoader.loadClass(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        try {
            classLoader=createClassLoader();
            StandardContext context=(StandardContext)container;
            String docbase=context.getDocbase();
            classLoader.addRepository(docbase+ File.separator+CLASS_PATH);
            classLoader.addRepository(docbase+File.separator+LIB_PATH);
        } catch (Exception e) {
            StandardLogger.error("start webappclassloader fail",e);
            throw new LifeCycleException("start webappclassloader fail");
        }
        lifeCycle.afterStart(null);
    }

    private WebappClassLoader createClassLoader() throws Exception{
        Class clazz = Class.forName(loaderClass);
        ClassLoader parentClassLoader=null;
        if(container!=null)
            parentClassLoader=container.getParentClassLoader();
        if(parentClassLoader==null){
            return (WebappClassLoader)clazz.newInstance();

        }else{
            Class[] argTypes={ClassLoader.class};
            Object[] args={parentClassLoader};
            Constructor constructor=clazz.getConstructor(argTypes);
            return (WebappClassLoader) constructor.newInstance(args);
        }
    }

    public void stop() throws LifeCycleException {

    }
}
