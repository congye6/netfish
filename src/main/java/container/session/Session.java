package container.session;

import javax.servlet.http.HttpSession;

/**
 * Created by cong on 2018-04-22.
 */
public interface Session {

    public String getAuthType();

    public void setAuthType(String authType);

    public long getCreationTime();

    public void setCreationTime(long time);

    public String getId();

    public void setId(String id);

    public long getLastAccessedTime();

    public SessionManager getManager();

    public void setManager(SessionManager manager);

    public int getMaxInactiveInterval();

    public void setMaxInactiveInterval(int interval);

    public void setNew(boolean isNew);

    public HttpSession getSession();

    public void setValid(boolean isValid);

    public boolean isValid();

    public void access();

    public void expire();

    public void recycle();

    public void removeAttribute(String var1);

    public void setAttribute(String var1, Object var2);

    public Object getAttribute(String var1);

    public boolean isExpired();
}
