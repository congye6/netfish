package container.lifecycle;

import java.util.List;

/**
 * Created by cong on 2018-04-18.
 */
public interface LifeCycle {

    public void addLifeCycleListener(LifeCycleListener listener);

    public List<LifeCycleListener> getLifeCycleListeners();

    public void removeLifeCycleListener(LifeCycleListener listener);

    public void start();

    public void stop();


}
