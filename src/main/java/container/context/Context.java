package container.context;

import container.Container;
import container.Mapper;
import container.pipeline.Pipeline;
import container.session.Session;
import container.session.SessionManager;
import container.wrapper.Wrapper;



/**
 * Created by cong on 2018-04-17.
 */
public interface Context extends Container {

    public void addServletMapping(String url, Wrapper wrapper);

    public Wrapper getServletMapping(String url);

    public void setMapper(Mapper mapper);

    public Mapper getMapper();

    public SessionManager getSessionManager();

    public void setSessionManager(SessionManager manager);

}
