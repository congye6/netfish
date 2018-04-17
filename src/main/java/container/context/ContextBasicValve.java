package container.context;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import container.Mapper;
import container.pipeline.Valve;
import container.pipeline.ValveContext;
import container.wrapper.Wrapper;

/**
 * Created by cong on 2018-04-17.
 */
public class ContextBasicValve implements Valve{

    private Context context;

    public ContextBasicValve(Context context) {
        this.context = context;
    }

    public void invoke(HttpRequest request, HttpResponse response, ValveContext valveContext) {
        System.out.println("context is invoking wrapper");
        Mapper mapper=context.getMapper();
        Wrapper wrapper=(Wrapper) mapper.map(request);
        wrapper.invoke(request,response);
    }
}
