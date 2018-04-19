package container.loader;

import container.Container;
import container.lifecycle.LifeCycle;
import container.lifecycle.LifeCycleException;
import container.lifecycle.LifeCycleListener;
import container.lifecycle.LifeCycleUtil;
import util.HttpFormatUtil;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * Created by cong on 2018-04-16.
 */
public class SimpleLoader implements Loader,LifeCycle{

    private LifeCycleUtil lifeCycle=new LifeCycleUtil(this);

    public Servlet load(String uri)  {
        File classPath=new File(HttpFormatUtil.WEB_ROOT);
        try {
            URL url=new URL("file",null,classPath.getCanonicalFile()+ File.separator);
            URLClassLoader classLoader=new URLClassLoader(new URL[]{url});

            int index=uri.lastIndexOf('/');
            String servletName=uri.substring(index+1);
            Class servletClazz= null;
            servletClazz = classLoader.loadClass(servletName);
            Servlet servlet=(Servlet)servletClazz.newInstance();
            return servlet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        System.out.println("loader start");
    }

    public void stop() throws LifeCycleException {
        System.out.println("loader end");
    }

    public ClassLoader getClassLoader() {
        return null;
    }

    public Container getContainer() {
        return null;
    }

    public void setContainer(Container container) {

    }

    public boolean reloadable() {
        return false;
    }

    public void setReloadable(boolean reloadable) {

    }

    public void addRepository(String repository) {

    }

    public List<String> getRepositories() {
        return null;
    }

    public boolean modified() {
        return false;
    }

    public boolean getDelegate() {
        return false;
    }

    public void setDelegate(boolean delegate) {

    }
}
