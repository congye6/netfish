package container.lifecycle;

/**
 * Created by cong on 2018-04-18.
 */
public class LifeCycleEvent{


    private LifeCycle lifeCycle;


    private Object data;

    public LifeCycleEvent(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public LifeCycleEvent(LifeCycle lifeCycle,Object data) {
        this.lifeCycle = lifeCycle;
        this.data = data;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    public Object getData() {
        return data;
    }
}
