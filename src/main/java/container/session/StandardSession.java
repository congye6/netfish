package container.session;

import util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.*;

/**
 * Created by cong on 2018-04-22.
 */
public class StandardSession implements HttpSession,Session{

    private String authType;

    private long creationTime;

    private String id;

    private long lastAccessTime;

    private int maxInactiveInterval;

    private boolean isNew;

    private boolean isValid=true;

    private Map<String,Object> attributeMap=new HashMap<String,Object>();

    private SessionManager manager;

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType=authType;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long time) {
        this.creationTime=time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public long getLastAccessedTime() {
        return lastAccessTime;
    }

    public SessionManager getManager() {
        return manager;
    }

    public void setManager(SessionManager manager) {
        this.manager=manager;
    }


    public ServletContext getServletContext() {
        return null;
    }

    public void setMaxInactiveInterval(int i) {
        this.maxInactiveInterval=i;
    }

    public void setNew(boolean isNew) {
        this.isNew=isNew;
    }

    public HttpSession getSession() {
        return new HttpSessionFacade(this);
    }

    public void setValid(boolean isValid) {
        this.isValid=isValid;
    }

    public boolean isValid() {
        return isValid;
    }

    public void access() {
        lastAccessTime=System.currentTimeMillis();
    }

    public void expire() {
        setValid(false);
        if(manager!=null)
            manager.remove(this);
        attributeMap.clear();
    }


    public void recycle() {

    }


    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public HttpSessionContext getSessionContext() {
        return null;
    }

    public Object getAttribute(String s) {
        return attributeMap.get(s);
    }

    public boolean isExpired() {
        return lastAccessTime+maxInactiveInterval>=System.currentTimeMillis();
    }

    public Object getValue(String s) {
        return null;
    }

    public Enumeration<String> getAttributeNames() {
        return null;
    }

    public String[] getValueNames() {
        return new String[0];
    }

    public void setAttribute(String s, Object o) {
        if(StringUtil.isEmpty(s))
            return;
        attributeMap.put(s,o);
    }

    public void putValue(String s, Object o) {

    }

    public void removeAttribute(String s) {
        attributeMap.remove(s);
    }

    public void removeValue(String s) {

    }

    public void invalidate() {

    }

    public boolean isNew() {
        return isNew;
    }
}
