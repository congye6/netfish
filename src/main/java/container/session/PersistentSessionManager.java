package container.session;

import util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-25.
 */
public class PersistentSessionManager extends SessionManager{

    private int maxActive=10;

    private int maxIdle=60000;

    private SessionStore sessionStore=new SessionStore();

    public PersistentSessionManager(){
        super();
        Thread backgroundThread=new Thread(new BackgroundTask());
        backgroundThread.start();
    }

    public void load() {

    }

    public void unload() {

    }

    @Override
    public Session getSession(String id){
        Session session=sessionMap.get(id);
        if(session!=null)
            return session;
        session=sessionStore.load(id);
        if(session!=null){
            sessionMap.put(id,session);
            return session;
        }
        return null;
    }


    private void checkPersistent(){
        if(sessionMap.size()<maxActive)//session数量太少，不需要置换
            return;
        long now=System.currentTimeMillis();
        List<String> swapOutList=new ArrayList<String>();
        for(Session session:sessionMap.values()){
            if(now-session.getLastAccessedTime()>maxIdle){
                swapOutList.add(session.getId());
                sessionStore.save(session);
            }
        }

        for(String swapOutId:swapOutList){
            sessionMap.remove(swapOutId);
        }
    }



    private class BackgroundTask implements Runnable{

        public void run() {
            ThreadUtil.sleep(getCheckInterval());
            checkExpired();
            checkPersistent();
        }
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
}
