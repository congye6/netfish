package container.lifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-18.
 * 辅助lifecycle管理listener
 */
public class LifeCycleUtil {


    private List<LifeCycleListener> listeners=new ArrayList<LifeCycleListener>();

    /**
     * 所辅助的lifecycle
     */
    private LifeCycle lifeCycle;

    public LifeCycleUtil(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public synchronized void addListener(LifeCycleListener listener){
        listeners.add(listener);
    }

    public synchronized void removeListener(LifeCycleListener listener){
        for(int i=0;i<listeners.size();i++){
            if(listeners.get(i)==listener){
                listeners.remove(i);
                return;
            }
        }
    }

    public List<LifeCycleListener> getListeners(){
        return getListenersCopy();
    }

    public void start(Object data){
        LifeCycleEvent event=new LifeCycleEvent(lifeCycle,data);
        List<LifeCycleListener> interetsted=getListenersCopy();
        for(LifeCycleListener listener:interetsted){
            listener.start(event);
        }
    }

    public void beforeStart(Object data){
        LifeCycleEvent event=new LifeCycleEvent(lifeCycle,data);
        List<LifeCycleListener> interetsted=getListenersCopy();
        for(LifeCycleListener listener:interetsted){
            listener.beforeStart(event);
        }
    }

    public void afterStart(Object data){
        LifeCycleEvent event=new LifeCycleEvent(lifeCycle,data);
        List<LifeCycleListener> interetsted=getListenersCopy();
        for(LifeCycleListener listener:interetsted){
            listener.afterStart(event);
        }
    }

    public void stop(Object data){
        LifeCycleEvent event=new LifeCycleEvent(lifeCycle,data);
        List<LifeCycleListener> interetsted=getListenersCopy();
        for(LifeCycleListener listener:interetsted){
            listener.stop(event);
        }
    }

    public void beforeStop(Object data){
        LifeCycleEvent event=new LifeCycleEvent(lifeCycle,data);
        List<LifeCycleListener> interetsted=getListenersCopy();
        for(LifeCycleListener listener:interetsted){
            listener.beforeStop(event);
        }
    }

    public void afterStop(Object data){
        LifeCycleEvent event=new LifeCycleEvent(lifeCycle,data);
        List<LifeCycleListener> interetsted=getListenersCopy();
        for(LifeCycleListener listener:interetsted){
            listener.afterStop(event);
        }
    }


    private synchronized List<LifeCycleListener> getListenersCopy(){
        return new ArrayList<LifeCycleListener>(listeners);
    }

}
