package container.loader;

import container.Container;

import java.util.List;

/**
 * Created by cong on 2018-04-16.
 */
public interface Loader {

    public ClassLoader getClassLoader();

    public Container getContainer();

    public void setContainer(Container container);

    public boolean reloadable();

    public void setReloadable(boolean reloadable);

    public void addRepository(String repository);

    public List<String> getRepositories();

    public boolean modified();

    public boolean getDelegate();

    public void setDelegate(boolean delegate);

    public Class load(String url);
}
