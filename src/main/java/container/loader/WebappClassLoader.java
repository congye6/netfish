package container.loader;

import logger.Logger;
import logger.StandardLogger;
import util.HttpFormatUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-19.
 */
public class WebappClassLoader extends URLClassLoader implements Reloader{

    private List<String> repositories=new ArrayList<String>();

    public WebappClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    public WebappClassLoader() {
        super(new URL[0]);
    }

    public void addRepository(String repository) {
        repositories.add(repository);
        URL url=getUrl(repository);
        if(url!=null)
            addURL(url);

    }

    public List<String> getRepositories() {
        return new ArrayList<String>(repositories);
    }

    public boolean modified() {
        return false;
    }

    private URL getUrl(String repository){
        File classPath=new File(HttpFormatUtil.WEB_ROOT+ File.separator+repository);

        try {
            String repositoryPath=classPath.getCanonicalFile()+ File.separator;
            URL url=new URL("file",null,repositoryPath);
            return  url;
        } catch (IOException e) {
            StandardLogger.error("get url fail",e);
        }

        return null;
    }
}
