package container.pipeline;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import logger.StandardLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-16.
 */
public class StandardValveContext implements ValveContext{

    private Valve basic;

    private List<Valve> valves=new ArrayList<Valve>();

    /**
     * 指向当前要运行的过滤器
     */
    private int currentPointer=0;

    public void invokeNext(HttpRequest request, HttpResponse response) {
        int pointer=currentPointer;
        currentPointer++;
        if(pointer<valves.size()){//小于valva数量，执行valves中的valve
            valves.get(pointer).invoke(request,response,this);
        }else if(pointer==valves.size()&&basic!=null){
            basic.invoke(request,response,this);
        }else{
            StandardLogger.error("invoke valve out of bound,index:"+pointer);
        }
    }

    public void addValve(Valve valve) {
        valves.add(valve);
    }

    public List<Valve> getValves() {
        return new ArrayList<Valve>(valves);
    }

    public void setBasicValve(Valve basic) {
        this.basic=basic;
    }

    public Valve getBasicValve() {
        return basic;
    }

    public void recycle() {
        currentPointer=0;
    }


}
