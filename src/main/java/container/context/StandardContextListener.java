package container.context;

import container.lifecycle.LifeCycleEvent;
import container.lifecycle.LifeCycleListener;

/**
 * Created by cong on 2018-04-18.
 */
public class StandardContextListener implements LifeCycleListener{
    public void start(LifeCycleEvent event) {
        System.out.println("context starting");
    }

    public void beforeStart(LifeCycleEvent event) {
        System.out.println("context will start");
    }

    public void afterStart(LifeCycleEvent event) {
        System.out.println("context started");
    }

    public void stop(LifeCycleEvent event) {
        System.out.println("context stopping");
    }

    public void beforeStop(LifeCycleEvent event) {
        System.out.println("context will stop");
    }

    public void afterStop(LifeCycleEvent event) {
        System.out.println("context stopped");
    }
}
