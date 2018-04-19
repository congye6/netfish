package container.loader;

import container.Container;
import container.lifecycle.LifeCycle;
import container.lifecycle.LifeCycleException;
import container.lifecycle.LifeCycleListener;
import container.lifecycle.LifeCycleUtil;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-19.
 */
public class WebappLoader implements Loader,LifeCycle{

    private static final String DEFAULT_LOADER_CLASS="container.loader.WebappClassLoader.class";

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
        ((Reloader)classLoader).getRepositories();
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
