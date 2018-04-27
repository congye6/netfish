package container.session;

import util.ThreadUtil;

/**
 * Created by cong on 2018-04-24.
 */
public class StandardSessionManager extends SessionManager{

    public StandardSessionManager() {
        super();
        Thread background=new Thread(new BackgroundTask());
        background.start();
    }

    public void load() {

    }

    public void unload() {

    }

    private class BackgroundTask implements Runnable{
        public void run() {
            ThreadUtil.sleep(getCheckInterval());
            checkExpired();
        }
    }
}
