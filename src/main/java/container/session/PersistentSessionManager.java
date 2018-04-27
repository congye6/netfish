package container.session;

import util.ThreadUtil;

/**
 * Created by cong on 2018-04-25.
 */
public class PersistentSessionManager extends SessionManager{


    public PersistentSessionManager(){
        super();
        Thread backgroundThread=new Thread(new BackgroundTask());
        backgroundThread.start();
    }

    public void load() {

    }

    public void unload() {

    }


    private void checkPersistent(){

    }

    private class BackgroundTask implements Runnable{

        public void run() {
            ThreadUtil.sleep(getCheckInterval());
            checkExpired();
            checkPersistent();
        }
    }
}
