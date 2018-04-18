package container.lifecycle;

/**
 * Created by cong on 2018-04-18.
 * 负责监听生命周期事件
 */
public interface LifeCycleListener {

    public void start(LifeCycleEvent event);

    public void beforeStart(LifeCycleEvent event);

    public void afterStart(LifeCycleEvent event);

    public void stop(LifeCycleEvent event);

    public void beforeStop(LifeCycleEvent event);

    public void afterStop(LifeCycleEvent event);

}
