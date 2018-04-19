package container.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.List;

/**
 * Created by cong on 2018-04-19.
 */
public class WebappClassLoader extends URLClassLoader implements Reloader{
    public WebappClassLoader(ClassLoader parent) {
        super(null, parent);
    }

    public WebappClassLoader() {
        super(null);
    }

    public void addRepository(String repository) {

    }

    public List<String> getRepositories() {
        return null;
    }

    public boolean modified() {
        return false;
    }
}
