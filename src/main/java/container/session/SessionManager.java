package container.session;

import container.Container;
import util.StringUtil;

import java.util.*;

/**
 * Created by cong on 2018-04-22.
 */
public abstract class SessionManager {

    protected Container container;

    protected int maxInactiveInterval=100000;

    protected int checkInterval=10000;

    protected Map<String,Session> sessionMap=new HashMap<String,Session>();


    public Session createSession(){
        Session session=new StandardSession();
        session.setId(createId());
        addSession(session);
        return session;
    }

    protected String createId(){
        UUID uuid=UUID.randomUUID();
        return uuid.toString();
    }

    public void addSession(Session session){
        if(session==null||!session.isValid()|| StringUtil.isEmpty(session.getId()))
            return;
        synchronized (sessionMap){
            sessionMap.put(session.getId(),session);
        }
    }

    public Session getSession(String id){
        return sessionMap.get(id);
    }

    public Session[] getSessions(){
        return (Session[]) sessionMap.values().toArray();
    }

    public void remove(Session session){
        if(session==null)
            return;
        synchronized (sessionMap){
            sessionMap.remove(session.getId());
        }
    }

    public int getCheckInterval() {
        return checkInterval;
    }

    public void setCheckInterval(int checkInterval) {
        this.checkInterval = checkInterval;
    }

    protected void checkExpired(){
        List<Session> expiredSessions=new ArrayList<Session>();
        for(Session session:sessionMap.values()){
            if(session.isExpired())
                expiredSessions.add(session);
        }

        for(Session session:expiredSessions){
            session.expire();
        }
    }

    public abstract void load();

    public abstract void unload();


    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }
}
