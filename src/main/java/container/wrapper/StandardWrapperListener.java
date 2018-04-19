package container.wrapper;

import container.lifecycle.LifeCycleEvent;
import container.lifecycle.LifeCycleListener;

/**
 * Created by cong on 2018-04-19.
 */
public class StandardWrapperListener implements LifeCycleListener{
    public void start(LifeCycleEvent event) {
        System.out.println("wrapper starting");
    }

    public void beforeStart(LifeCycleEvent event) {

    }

    public void afterStart(LifeCycleEvent event) {

    }

    public void stop(LifeCycleEvent event) {

    }

    public void beforeStop(LifeCycleEvent event) {

    }

    public void afterStop(LifeCycleEvent event) {

    }
}
