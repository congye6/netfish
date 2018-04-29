package connector.connector;

import util.ThreadUtil;

import java.util.*;

/**
 * Created by cong on 2018-04-29.
 */
public class KeepAliveMonitor implements Runnable{

    private static final int DEFAULT_CHECK_INTERVAL=10000;

    private int checkInterval;

    public KeepAliveMonitor(int checkInterval) {
        this.checkInterval = checkInterval;
    }

    public KeepAliveMonitor() {
        this(DEFAULT_CHECK_INTERVAL);
    }

    private List<ConnectionTask> tasks=new ArrayList<ConnectionTask>();

    public void run() {
        while (true){
            ThreadUtil.sleep(checkInterval);
            List<ConnectionTask> closeTasks=new ArrayList<ConnectionTask>();
            for(ConnectionTask task:tasks){
                boolean alive=task.checkAlive();
                if(!alive)
                    closeTasks.add(task);
            }

            tasks.removeAll(closeTasks);
        }
    }

    public void addTask(ConnectionTask task){
        tasks.add(task);
    }
}
