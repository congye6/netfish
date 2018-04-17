package container.context;

import connector.request.HttpRequest;
import container.Container;
import container.Mapper;

/**
 * Created by cong on 2018-04-17.
 */
public class ContextMapper implements Mapper{

    private Context context;

    public void setContainer(Container container) {
        context=(Context)container;
    }

    public Container getContainer() {
        return context;
    }

    public Container map(HttpRequest request) {
        String url=request.getURI();
        return context.getServletMapping(url);
    }
}
