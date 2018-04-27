package container.pipeline;

import connector.request.HttpRequest;
import connector.response.HttpResponse;

import java.nio.channels.Pipe;
import java.util.List;

/**
 * Created by cong on 2018-04-16.
 */
public class StandardPipeline implements Pipeline{

    private ValveContext valveContext=new StandardValveContext();

    public void invoke(HttpRequest request, HttpResponse response) {
        valveContext.invokeNext(request,response);
    }

    public void setBasic(Valve basic) {
        valveContext.setBasicValve(basic);
    }

    public Valve getBasic() {
        return valveContext.getBasicValve();
    }

    public void addValve(Valve valve) {
        valveContext.addValve(valve);
    }

    public List<Valve> getValves() {
        return valveContext.getValves();
    }

    public void recycle() {
        valveContext.recycle();
    }
}
