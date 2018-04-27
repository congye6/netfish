package container.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

/**
 * Created by cong on 2018-04-22.
 */
public class HttpSessionFacade implements HttpSession {

    private HttpSession session;

    public HttpSessionFacade(HttpSession session) {
        this.session = session;
    }

    public long getCreationTime() {
        return session.getCreationTime();
    }

    public String getId() {
        return session.getId();
    }

    public long getLastAccessedTime() {
        return session.getLastAccessedTime();
    }

    public ServletContext getServletContext() {
        return session.getServletContext();
    }

    public void setMaxInactiveInterval(int i) {
        session.setMaxInactiveInterval(i);
    }

    public int getMaxInactiveInterval() {
        return session.getMaxInactiveInterval();
    }

    public HttpSessionContext getSessionContext() {
        return session.getSessionContext();
    }

    public Object getAttribute(String s) {
        return session.getAttribute(s);
    }

    public Object getValue(String s) {
        return session.getValue(s);
    }

    public Enumeration<String> getAttributeNames() {
        return session.getAttributeNames();
    }

    public String[] getValueNames() {
        return session.getValueNames();
    }

    public void setAttribute(String s, Object o) {
        session.setAttribute(s,o);
    }

    public void putValue(String s, Object o) {
        session.putValue(s,o);
    }

    public void removeAttribute(String s) {
        session.removeAttribute(s);
    }

    public void removeValue(String s) {
        session.removeValue(s);
    }

    public void invalidate() {
        session.invalidate();
    }

    public boolean isNew() {
        return session.isNew();
    }
}
