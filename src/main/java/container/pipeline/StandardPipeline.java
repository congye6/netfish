package container.pipeline;

import connector.request.HttpRequest;
import connector.response.HttpResponse;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-16.
 */
public class StandardPipeline implements Pipeline{

    private Valve basic;

    private List<Valve> valves=new ArrayList<Valve>();

    public void invoke(HttpRequest request, HttpResponse response) {
        ValveContext valveContext=new StandardValveContext(basic,valves);
        valveContext.invokeNext(request,response);
    }

    public void setBasic(Valve basic) {
        this.basic=basic;
    }

    public Valve getBasic() {
        return basic
    }

    public void addValve(Valve valve) {
        if(valve!=null)
            valves.add(valve);
    }

    public List<Valve> getValves() {
        return new ArrayList<>(valves);
    }

    public void recycle() {

    }
}
