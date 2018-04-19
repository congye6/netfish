package container.loader;

import java.util.List;

/**
 * Created by cong on 2018-04-19.
 */
public interface Reloader {


    public void addRepository(String repository);

    public List<String> getRepositories();


    public boolean modified();


}
